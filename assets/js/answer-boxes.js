// assets/js/answer-boxes.js
function toggleAnswer(button) {
  // Find the answer box that follows this button
  const answerBox = button.nextElementSibling;

  // Toggle visibility
  if (answerBox.style.display === "block") {
    answerBox.style.display = "none";
    button.textContent = "Show Answer";
  } else {
    answerBox.style.display = "block";
    button.textContent = "Hide Answer";
  }
}

function toggleSolution(button) {
  // Find the parent container
  const container = button.parentElement;

  // Find the skeleton and solution elements
  const skeleton = container.querySelector(".code-skeleton");
  const solution = container.querySelector(".code-solution");

  if (solution.style.display === "none" || solution.style.display === "") {
    // Hide skeleton, show solution
    skeleton.style.display = "none";
    solution.style.display = "block";
    button.textContent = "Show Skeleton";
  } else {
    // Show skeleton, hide solution
    skeleton.style.display = "block";
    solution.style.display = "none";
    button.textContent = "Show Solution";
  }
}
