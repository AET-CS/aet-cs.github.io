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