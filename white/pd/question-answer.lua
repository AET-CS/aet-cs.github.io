-- question-answer.lua
function injectDependencies(meta)
  -- Add CSS
  local css = [[
<style>
.question {
  background-color: #eaf2f8;
  padding: 15px;
  margin: 15px 0;
  border-radius: 4px;
  position: relative;
}

.answer-box {
  background-color: #e8f8f5;
  border-left: 4px solid #2ecc71;
  padding: 10px 15px;
  margin-top: 10px;
  border-radius: 0 4px 4px 0;
  display: none; /* Hidden by default */
}

.show-answer {
  background-color: #3498db;
  color: white;
  border: none;
  padding: 5px 10px;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
  margin-top: 10px;
}

.show-answer:hover {
  background-color: #2980b9;
}

.code-question {
  background-color: #f5f5f5;
  padding: 15px;
  margin: 15px 0;
  border-radius: 4px;
  position: relative;
  border-left: 4px solid #3498db;
}

.code-skeleton, .code-solution {
  margin-bottom: 10px;
}

.code-solution {
  display: none;
  background-color: #e8f8f5;
  border-left: 4px solid #2ecc71;
  padding: 10px;
  border-radius: 4px;
}
</style>
  ]]

  -- Add JavaScript
  local js = [[
<script>
function toggleAnswer(button) {
  const answerBox = button.nextElementSibling;

  if (answerBox.style.display === "block") {
    answerBox.style.display = "none";
    button.textContent = "Show Answer";
  } else {
    answerBox.style.display = "block";
    button.textContent = "Hide Answer";
  }
}

function toggleSolution(button) {
  const container = button.parentElement;
  const skeleton = container.querySelector(".code-skeleton");
  const solution = container.querySelector(".code-solution");

  if (solution.style.display === "none" || solution.style.display === "") {
    skeleton.style.display = "none";
    solution.style.display = "block";
    button.textContent = "Show Skeleton";
  } else {
    skeleton.style.display = "block";
    solution.style.display = "none";
    button.textContent = "Show Solution";
  }
}
</script>
  ]]

  -- Inject into document header
  if meta.header == nil then
    meta.header = pandoc.MetaBlocks({})
  end

  table.insert(meta.header, pandoc.RawBlock('html', css))
  table.insert(meta.header, pandoc.RawBlock('html', js))

  return meta
end

function processQuestion(div)
  -- Find title and answer within the question div
  local title = nil
  local answer_content = {}
  local other_content = {}

  for _, block in ipairs(div.content) do
    if block.t == "Div" and block.classes[1] == "answer" then
      answer_content = block.content
    else
      table.insert(other_content, block)
    end
  end

  -- Create button
  local button = pandoc.RawBlock('html',
    '<button class="show-answer" onclick="toggleAnswer(this)">Show Answer</button>')

  -- Create answer box
  local answer_box = pandoc.Div(answer_content, {class = "answer-box"})

  -- Construct final div
  div.content = other_content
  table.insert(div.content, button)
  table.insert(div.content, answer_box)

  return div
end

function processCodeSolution(div)
  -- Find skeleton and solution divs
  local skeleton_content = {}
  local solution_content = {}

  for _, block in ipairs(div.content) do
    if block.t == "Div" and block.classes[1] == "skeleton" then
      skeleton_content = block.content
    elseif block.t == "Div" and block.classes[1] == "solution" then
      solution_content = block.content
    end
  end

  -- Create skeleton and solution divs
  local skeleton_div = pandoc.Div(skeleton_content, {class = "code-skeleton"})
  local solution_div = pandoc.Div(solution_content, {class = "code-solution"})

  -- Create button
  local button = pandoc.RawBlock('html',
    '<button class="show-solution" onclick="toggleSolution(this)">Show Solution</button>')

  -- Construct final div
  div.content = {skeleton_div, solution_div, button}
  div.classes = {"code-question"}

  return div
end

return {
  {Meta = injectDependencies},
  {
    Div = function(div)
      if div.classes[1] == "question" then
        return processQuestion(div)
      elseif div.classes[1] == "codesolution" then
        return processCodeSolution(div)
      end
      return div
    end
  }
}