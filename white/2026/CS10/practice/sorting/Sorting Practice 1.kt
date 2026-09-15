{
  "version": 1,
  "savedAt": "2026-04-20T19:06:24.493Z",
  "questions": [
    {
      "id": "q1",
      "text": "Consider the following array of integers:\n\n```java\nint[] a = {9, 1, 8, 3, 7, 2, 5, 4, 6};\n```\nSelection sort is applied to sort the array in **ascending** order. After exactly **two passes** of the outer loop have completed, which of the following represents the state of the array?\n\n*(A pass = one execution of the outer `for` loop, which selects the smallest element from the unsorted portion and swaps it into its final position.)*",
      "answers": [
        {
          "text": "`[1, 2, 8, 3, 7, 9, 5, 4, 6]`",
          "is_correct": false,
          "explanation": "This array mistakenly keeps the original relative order of the unsorted elements after removing the two minima."
        },
        {
          "text": "`[1, 2, 9, 8, 3, 7, 5, 4, 6]`",
          "is_correct": false,
          "explanation": "Here the two smallest values are placed at the front, but the remaining elements are still in the **original** order, which is not what selection sort produces after swaps."
        },
        {
          "text": "`[1, 2, 8, 3, 7, 9, 5, 4, 6]`",
          "is_correct": true,
          "explanation": "Correct: after pass 0 the 1 is swapped to index 0; after pass 1 the 2 is swapped to index 1, producing this exact state."
        },
        {
          "text": "`[1, 2, 3, 4, 5, 6, 7, 8, 9]`",
          "is_correct": false,
          "explanation": "This would be the **final** sorted array, not the state after only two passes."
        },
        {
          "text": "`[1, 8, 9, 3, 7, 2, 5, 4, 6]`",
          "is_correct": false,
          "explanation": "This reflects a wrong second‑pass swap (choosing 8 instead of 2 as the minimum)."
        }
      ],
      "explanation": "Selection sort works by repeatedly selecting the smallest element from the unsorted portion and swapping it into the first unsorted slot.\n\n**Pass 0 (i = 0):**\n- Unsorted portion: `[9, 1, 8, 3, 7, 2, 5, 4, 6]`.\n- Minimum is `1` at index 1.\n- Swap `a[0]` and `a[1]` → `[1, 9, 8, 3, 7, 2, 5, 4, 6]`.\n\n**Pass 1 (i = 1):**\n- Unsorted portion: `[9, 8, 3, 7, 2, 5, 4, 6]`.\n- Minimum is `2` at index 5.\n- Swap `a[1]` and `a[5]` → `[1, 2, 8, 3, 7, 9, 5, 4, 6]`.\n\nAfter two passes the array is exactly `[1, 2, 8, 3, 7, 9, 5, 4, 6]`.\n\nAll other options either keep the original order of the unsorted part, jump ahead to the fully sorted array, or use the wrong element during the second pass.",
      "rubric": null,
      "distractors": "Common misconceptions leading to the wrong choices:\n- **A:** *\"Remove the minima and keep the rest in original order\"* – students forget that the swap changes the order of the remaining elements.\n- **B:** *\"Only the two smallest values move, everything else stays unchanged\"* – similar to A but with the original relative order preserved.\n- **D:** *\"Two passes mean the whole array is sorted\"* – confusion between number of passes and completion of the algorithm.\n- **E:** *\"Select the wrong element on the second pass (e.g., the first element examined)\"* – off‑by‑one or misunderstanding of how the minimum is found.",
      "subject": "Computer Science",
      "topics": [
        "ST046"
      ],
      "difficulty": "medium"
    },
    {
      "id": "q2",
      "text": "How many **actual swaps** (i.e., exchanges where two different indices are involved) does the standard selection‑sort algorithm perform when sorting the array `[4, 3, 2, 1]` in ascending order?\n\nChoose the correct number:",
      "answers": [
        {
          "text": "`3`",
          "is_correct": false,
          "explanation": "Counts a swap for every outer‑loop iteration, even when the element is already in the correct place."
        },
        {
          "text": "`2`",
          "is_correct": true,
          "explanation": "Only the first two passes swap different indices; the last two passes find the element already in place."
        },
        {
          "text": "`4`",
          "is_correct": false,
          "explanation": "Treats each comparison as a swap; confused with number of comparisons."
        },
        {
          "text": "`1`",
          "is_correct": false,
          "explanation": "Misses the second necessary swap."
        },
        {
          "text": "`0`",
          "is_correct": false,
          "explanation": "Would be true only for an already sorted array."
        }
      ],
      "explanation": "Selection sort proceeds as follows:\n\n- **Pass 0:** Minimum of `[4,3,2,1]` is `1` at index 3. Swap `a[0]` (4) with `a[3]` → one swap.\n- **Pass 1:** Minimum of `[3,2,4]` (indices 1‑3) is `2` at index 2. Swap `a[1]` (3) with `a[2]` → second swap.\n- **Pass 2:** Subarray `[3,4]` is already in order; the minimum (`3`) is at index 2, so **no** swap.\n- **Pass 3:** Single element left – no swap.\n\nThus exactly **2** swaps involve distinct indices.\n\nIf an implementation performed a swap even when `minIdx == i`, the count would be 3, but the usual definition of \"actual swap\" excludes that case.",
      "rubric": null,
      "distractors": "Typical error patterns:\n- **Counting every outer iteration** → yields 3 swaps.\n- **Confusing swaps with comparisons** → 4 comparisons, not swaps.\n- **Missing the second swap** → only 1 swap.\n- **Assuming a reverse‑sorted array needs no swaps** → 0 swaps (valid only for already sorted input).",
      "subject": "Computer Science",
      "topics": [
        "ST046"
      ],
      "difficulty": "medium"
    },
    {
      "id": "q3",
      "text": "The following method implements selection sort that sorts an integer array `arr` in **ascending** order:\n\n```java\npublic static void selectSort(int[] arr) {\n    for (int i = 0; i < arr.length - 1; i++) {\n        int maxIdx = i;\n        for (int j = i + 1; j < arr.length; j++) {\n            if (arr[j] < arr[maxIdx]) {\n                maxIdx = j;\n            }\n        }\n        int temp = arr[i];\n        arr[i] = arr[maxIdx];\n        arr[maxIdx] = temp;\n    }\n}\n```\n\nYou want to modify the method so that it sorts the array in **descending** order. Which single change will achieve this?\n\nA. Replace `int maxIdx = i;` with `int minIdx = i;`\nB. Replace `if (arr[j] < arr[maxIdx])` with `if (arr[j] > arr[maxIdx])`\nC. Replace the outer loop condition with `i < arr.length` \nD. Replace the swap lines with `arr[i] = arr[maxIdx]; arr[maxIdx] = temp;`\nE. Replace `j < arr.length` with `j <= arr.length`",
      "answers": [
        {
          "text": "A",
          "is_correct": false,
          "explanation": "Renaming the variable does not change the comparison logic; the algorithm would still find the minimum."
        },
        {
          "text": "B",
          "is_correct": true,
          "explanation": "Changing `<` to `>` makes the inner loop look for the **largest** element, which is then placed at the front, producing a descending order."
        },
        {
          "text": "C",
          "is_correct": false,
          "explanation": "Increasing the outer‑loop bound would cause an `ArrayIndexOutOfBoundsException` on the final iteration."
        },
        {
          "text": "D",
          "is_correct": false,
          "explanation": "The swap statements are already correct; altering them as shown does nothing."
        },
        {
          "text": "E",
          "is_correct": false,
          "explanation": "Changing the inner‑loop bound to `<=` also causes an out‑of‑bounds error."
        }
      ],
      "explanation": "To sort in descending order, selection sort must locate the **largest** element of the unsorted portion and move it to the beginning of that portion. The current inner‑loop condition `if (arr[j] < arr[maxIdx])` searches for the smallest element (`<`). Replacing the operator with `>` makes it search for the largest element instead:\n\n```java\nif (arr[j] > arr[maxIdx]) {\n    maxIdx = j;\n}\n```\nAll other lines (initialization of `maxIdx`, loop bounds, and the swap) remain appropriate. After this change each pass places the next greatest value at index `i`, resulting in a descendingly sorted array.\n\nThe other options either rename a variable without affecting logic, modify loop bounds (causing errors), or repeat the existing correct swap.",
      "rubric": null,
      "distractors": "Why the wrong choices look plausible:\n- **A:** Students think the variable name must match the direction of sorting, overlooking that the comparison operator drives the choice.\n- **C:** Belief that a larger outer‑loop range is needed for descending order (misunderstanding loop purpose).\n- **D:** Misreading the swap as the place to change order, when it already works for any direction.\n- **E:** Off‑by‑one misconception; thinking `<=` includes the last element, but Java arrays are 0‑based and `j < length` is already correct.",
      "subject": "Computer Science",
      "topics": [
        "ST046"
      ],
      "difficulty": "medium"
    },
    {
      "id": "q4",
      "text": "Examine the following (incorrect) implementation of selection sort:\n\n```java\npublic static void badSelectSort(int[] arr) {\n    for (int i = 0; i < arr.length - 1; i++) {\n        int minIdx = i;\n        for (int j = i + 1; j <= arr.length; j++) { // line 5\n            if (arr[j] < arr[minIdx]) {\n                minIdx = j;\n            }\n        }\n        int temp = arr[i];\n        arr[i] = arr[minIdx];\n        arr[minIdx] = temp;\n    }\n}\n```\n\nWhich single modification fixes the `ArrayIndexOutOfBoundsException` that occurs at runtime?\n\nA. Change `i < arr.length - 1` to `i <= arr.length - 1` in the outer loop.\nB. Change `int minIdx = i;` to `int minIdx = 0;`.\nC. Change `j <= arr.length` to `j < arr.length` in the inner loop.\nD. Change `arr[j] < arr[minIdx]` to `arr[j] <= arr[minIdx]`.\nE. Remove the line `int temp = arr[i];`.",
      "answers": [
        {
          "text": "A",
          "is_correct": false,
          "explanation": "Altering the outer loop bound does not address the out‑of‑bounds access in the inner loop."
        },
        {
          "text": "B",
          "is_correct": false,
          "explanation": "Changing the start index of the search does not fix the illegal index used in the inner loop."
        },
        {
          "text": "C",
          "is_correct": true,
          "explanation": "The inner loop should stop at `j < arr.length`; `j <= arr.length` attempts to read `arr[arr.length]`, which is invalid."
        },
        {
          "text": "D",
          "is_correct": false,
          "explanation": "Using `<=` changes the algorithm's behavior but does not prevent the out‑of‑bounds error."
        },
        {
          "text": "E",
          "is_correct": false,
          "explanation": "Removing the temporary variable breaks the swap logic; it does not resolve the index issue."
        }
      ],
      "explanation": "In Java, valid array indices run from `0` to `arr.length‑1`. The inner loop currently uses `j <= arr.length`, so when `j` reaches `arr.length` the expression `arr[j]` tries to read past the last element, throwing an `ArrayIndexOutOfBoundsException`. Changing the loop condition to `j < arr.length` ensures the largest value of `j` is `arr.length‑1`, which is safe.\n\nAll other suggested changes either modify unrelated logic or introduce new errors, but none stop the illegal access.",
      "rubric": null,
      "distractors": "Why each wrong option might look right:\n- **A:** Confusion between off‑by‑one errors in the outer vs. inner loop.\n- **B:** Belief that the minimum should always start at index 0, overlooking that the search must begin at the current unsorted position.\n- **D:** Misunderstanding that `<=` in the comparison fixes the bug, while it actually only changes tie‑handling.\n- **E:** Thinking the temporary variable somehow causes the exception, which is not the case.",
      "subject": "Computer Science",
      "topics": [
        "ST046"
      ],
      "difficulty": "medium"
    },
    {
      "id": "q5",
      "text": "For an input array of size **n**, how many **comparisons** does the classic selection‑sort algorithm perform, regardless of the initial ordering of the elements?\n\nSelect the correct expression:",
      "answers": [
        {
          "text": "`n - 1`",
          "is_correct": false,
          "explanation": "That would be the number of swaps in the best case, not the number of comparisons."
        },
        {
          "text": "`n(n - 1) / 2`",
          "is_correct": true,
          "explanation": "Selection sort always makes the triangular number of comparisons: (n‑1) + (n‑2) + … + 1."
        },
        {
          "text": "`n²`",
          "is_correct": false,
          "explanation": "Slightly too large; the exact count is half of n squared minus half of n."
        },
        {
          "text": "`(n² - n) / 2`",
          "is_correct": false,
          "explanation": "Algebraically identical to the correct answer, but the format differs; still acceptable in many contexts, but we require the standard simplified form."
        },
        {
          "text": "`(n + 1) / 2`",
          "is_correct": false,
          "explanation": "Far too small; this is unrelated to selection sort."
        }
      ],
      "explanation": "During each outer‑loop iteration `i` (from `0` to `n‑2`), the inner loop scans the remaining unsorted elements:\n- Pass 0 compares `n‑1` elements.\n- Pass 1 compares `n‑2` elements.\n- …\n- Pass n‑2 compares `1` element.\nThe total number of comparisons is the sum of the first `n‑1` positive integers:\n\n$$(n-1) + (n-2) + \\dots + 1 = \\frac{(n-1)n}{2} = \\frac{n(n-1)}{2}.$$\n\nBecause this sum does not depend on the data values, the count is the same for best, average, and worst cases.\n\nOption B matches this expression exactly.",
      "rubric": null,
      "distractors": "Common mistakes leading to the wrong options:\n- **A:** Confusing the number of **passes** (`n‑1`) with the number of comparisons.\n- **C:** Squaring the size forgetting that each inner loop shrinks.\n- **D:** Providing an unsimplified but mathematically equivalent form; the exam expects the conventional `n(n‑1)/2`.\n- **E:** Random guess; often students think of average‑case formulas from other sorts.",
      "subject": "Computer Science",
      "topics": [
        "ST046"
      ],
      "difficulty": "medium"
    },
    {
      "id": "q6",
      "text": "Consider the following insertion sort implementation (ascending order):\n\n```java\nint[] arr = {8, 3, 5, 2, 7, 4};\nfor (int i = 1; i < arr.length; i++) {\n    int key = arr[i];\n    int j = i - 1;\n    while (j >= 0 && arr[j] > key) {\n        arr[j + 1] = arr[j];\n        j--;\n    }\n    arr[j + 1] = key;\n}\n```\n\nAfter the **third** execution of the outer `for` loop (i.e., after the iteration where `i == 3` completes), what is the exact contents of `arr`?\n\nChoose the correct array representation.",
      "answers": [
        {
          "text": "`[2, 3, 5, 8, 7, 4]`",
          "is_correct": true,
          "explanation": "After i = 3 the array is `[2, 3, 5, 8, 7, 4]` (the element `2` has been inserted at the front, the rest remain unchanged)."
        },
        {
          "text": "`[2, 3, 5, 7, 8, 4]`",
          "is_correct": false,
          "explanation": "This state would require the element `7` to have been moved, which does not happen until the next outer iteration."
        },
        {
          "text": "`[3, 5, 2, 8, 7, 4]`",
          "is_correct": false,
          "explanation": "This reflects forgetting to shift the element `8` when inserting `2`."
        },
        {
          "text": "`[2, 3, 8, 5, 7, 4]`",
          "is_correct": false,
          "explanation": "Here the element `5` was not shifted correctly; a common off‑by‑one error."
        },
        {
          "text": "`[3, 2, 5, 8, 7, 4]`",
          "is_correct": false,
          "explanation": "The key `2` was placed at index 1 instead of index 0, a typical mistake when the loop index `j` is not decremented after the final shift."
        }
      ],
      "explanation": "Trace the algorithm step‑by‑step:\n1. **i = 1** (key = 3): 8 > 3, shift 8 right, place 3 at index 0 → `[3, 8, 5, 2, 7, 4]`.\n2. **i = 2** (key = 5): 8 > 5, shift 8 right, 3 ≤ 5 stop, place 5 at index 1 → `[3, 5, 8, 2, 7, 4]`.\n3. **i = 3** (key = 2): compare 8,5,3 all > 2, shift each right, j becomes –1, place 2 at index 0 → `[2, 3, 5, 8, 7, 4]`.\nThe outer loop has run three times, so the array shown is the correct state.\n\n**Why the other choices are tempting**\n- Choice B assumes the element `7` was already moved, which occurs only when `i = 4`.\n- Choice C forgets the final shift of `8`.\n- Choice D misplaces `5` because the inner loop stopped one iteration too early (off‑by‑one).\n- Choice E puts the key at index 1, a typical error when the `j--` statement is omitted after the last shift.",
      "rubric": null,
      "distractors": "Common misconceptions leading to the distractors:\n- **Off‑by‑one in the inner loop** → produces `[3, 5, 8, 2, 7, 4]`‑like states.\n- **Stopping the inner loop too early** → leaves a larger element unsifted, giving `[2, 3, 8, 5, 7, 4]`.\n- **Placing the key at `j` instead of `j+1`** → results in `[3, 2, 5, 8, 7, 4]`.\n- **Assuming later outer iterations have already occurred** → yields `[2, 3, 5, 7, 8, 4]`.",
      "subject": "Computer Science",
      "topics": [
        "ST047"
      ],
      "difficulty": "medium"
    },
    {
      "id": "q7",
      "text": "Fill in the missing line (marked `/*?*/`) so that the method correctly performs insertion sort in ascending order.\n\n```java\npublic static void insertionSort(int[] a) {\n    for (int i = 1; i < a.length; i++) {\n        int key = a[i];\n        int j = i - 1;\n        while (j >= 0 && a[j] > key) {\n            /*?*/\n        }\n        a[j + 1] = key;\n    }\n}\n```\n\nWhich of the following statements correctly replaces `/*?*/`?",
      "answers": [
        {
          "text": "`a[j] = a[j + 1]; j++;`",
          "is_correct": false,
          "explanation": "This moves elements to the left and increments `j`, which would corrupt the sorted portion."
        },
        {
          "text": "`a[j + 1] = a[j]; j--;`",
          "is_correct": true,
          "explanation": "Correctly shifts the larger element one position to the right and moves the index leftward."
        },
        {
          "text": "`a[j] = key; j--;`",
          "is_correct": false,
          "explanation": "Assigns the key prematurely, destroying the element that still needs to be compared."
        },
        {
          "text": "`a[j] = a[j - 1]; j--;`",
          "is_correct": false,
          "explanation": "References `a[j‑1]` which may be out of bounds when `j` is 0."
        },
        {
          "text": "`a[j + 1] = a[j]; j++;`",
          "is_correct": false,
          "explanation": "Shifts right but then moves `j` in the wrong direction, causing an infinite loop."
        }
      ],
      "explanation": "Inside the `while` loop we must move the element that is larger than `key` one position to the right to make room. The statement `a[j + 1] = a[j];` copies the larger element rightward, and `j--` steps left to continue checking earlier elements. When the loop finishes, `a[j + 1] = key;` inserts the key into the gap.\n\n**Why the other options fail**\n- Option 1 copies the element leftward and then increments `j`, which reverses the intended shift.\n- Option 3 writes the key before all larger elements have been moved, breaking the algorithm.\n- Option 4 accesses `a[j‑1]` without guaranteeing `j‑1` is a valid index.\n- Option 5 increments `j` after the shift, so the loop never terminates (the condition `a[j] > key` is repeatedly true).",
      "rubric": null,
      "distractors": "Student error patterns:\n- **Direction mix‑up** (left shift instead of right) → option 1.\n- **Premature insertion of key** → option 3.\n- **Off‑by‑one array access** (using `j‑1`) → option 4.\n- **Incorrect index update leading to infinite loop** → option 5.",
      "subject": "Computer Science",
      "topics": [
        "ST047"
      ],
      "difficulty": "medium"
    },
    {
      "id": "q8",
      "text": "For an array of length `n = 7` that is in strictly descending order, insertion sort (the classic version that uses the condition `while (j >= 0 && a[j] > key)`) is run. How many times is the comparison `a[j] > key` evaluated during the entire sort?\n\nChoose the correct number.",
      "answers": [
        {
          "text": "`21`",
          "is_correct": true,
          "explanation": "In the worst case each iteration i performs i comparisons; total = 1+2+3+4+5+6 = 21."
        },
        {
          "text": "`20`",
          "is_correct": false,
          "explanation": "Off‑by‑one: forgetting the comparison for the last element."
        },
        {
          "text": "`28`",
          "is_correct": false,
          "explanation": "Counts an extra false comparison for each outer loop iteration."
        },
        {
          "text": "`35`",
          "is_correct": false,
          "explanation": "Assumes every pair of elements is compared (full n²), which is not true for insertion sort."
        },
        {
          "text": "`15`",
          "is_correct": false,
          "explanation": "Under‑counts by omitting several necessary comparisons."
        }
      ],
      "explanation": "Insertion sort in the worst case (descending input) shifts every previous element for each new key.\n- For `i = 1` the key is compared with 1 element → 1 comparison.\n- For `i = 2` → 2 comparisons.\n- …\n- For `i = 6` → 6 comparisons.\nSum = 1+2+3+4+5+6 = 21. No extra comparison is performed when `j` becomes –1 because the `j >= 0` part of the `while` condition fails before another `a[j] > key` test.\n\n**Why the wrong answers look plausible**\n- `20` omits the comparison for the first outer iteration.\n- `28` adds one extra failing comparison per outer iteration (a common misunderstanding of the loop condition).\n- `35` is the full `n(n‑1)` count, which would be true only if every pair were examined.\n- `15` is the sum of the first five integers, forgetting the last iteration.",
      "rubric": null,
      "distractors": "Misconception analysis:\n- **Counting the final false test** → leads to 28.\n- **Skipping the first iteration** → leads to 20.\n- **Assuming classic O(n²) always means n(n‑1)** → leads to 35.\n- **Partial summation error** → leads to 15.",
      "subject": "Computer Science",
      "topics": [
        "ST047"
      ],
      "difficulty": "medium"
    },
    {
      "id": "q9",
      "text": "To modify the following insertion‑sort method so that it sorts an array in **descending** order, which single change is sufficient?\n\n```java\npublic static void insertionSortDesc(int[] a) {\n    for (int i = 1; i < a.length; i++) {\n        int key = a[i];\n        int j = i - 1;\n        while (j >= 0 && a[j] > key) { // line to modify\n            a[j + 1] = a[j];\n            j--;\n        }\n        a[j + 1] = key;\n    }\n}\n```\n\nSelect the correct modification.",
      "answers": [
        {
          "text": "Change the comparison to `a[j] < key`.",
          "is_correct": true,
          "explanation": "Reversing the comparison makes the algorithm shift smaller elements rightward, producing a descending order."
        },
        {
          "text": "Change the loop header to `for (int i = a.length - 2; i >= 0; i--)`.",
          "is_correct": false,
          "explanation": "Alters the direction of the outer loop but leaves the inner comparison unchanged; the result is still ascending."
        },
        {
          "text": "Swap the statements inside the while loop to `a[j] = a[j + 1]; j++;`.",
          "is_correct": false,
          "explanation": "This shifts elements leftward and moves the index the wrong way, breaking the algorithm."
        },
        {
          "text": "Replace `j >= 0` with `j > 0` in the while condition.",
          "is_correct": false,
          "explanation": "Would skip the comparison for the first element, yielding an incorrect order."
        },
        {
          "text": "Add `key = -key;` before the while loop and after the loop restore the sign.",
          "is_correct": false,
          "explanation": "Negating values changes the data, not just the order, and is not a valid sorting technique."
        }
      ],
      "explanation": "To sort descending, the only thing that must change is the direction of the comparison inside the inner `while`. The original test `a[j] > key` moves larger elements to the right, producing ascending order. Replacing it with `a[j] < key` moves smaller elements rightward, so the larger elements stay to the left, resulting in descending order. No other line needs to be altered.\n\n**Why the other options fail**\n- Changing the outer loop direction (option 2) still uses the same inner comparison, so the algorithm still builds an ascending sequence.\n- Modifying the shift direction (option 3) corrupts the list structure.\n- Using `j > 0` (option 4) prevents the first element from being compared, leaving it possibly out of place.\n- Negating the values (option 5) changes the actual data rather than just the order.",
      "rubric": null,
      "distractors": "Error patterns:\n- **Altering loop direction instead of comparison** → option 2.\n- **Reversing the shift direction** → option 3.\n- **Changing the boundary condition** → option 4.\n- **Applying a sign‑change trick** → option 5.",
      "subject": "Computer Science",
      "topics": [
        "ST047"
      ],
      "difficulty": "medium"
    },
    {
      "id": "q10",
      "text": "The following insertion‑sort routine uses a sentinel (the smallest possible integer) to avoid the `j >= 0` test inside the inner loop.\n\n```java\npublic static void insertionSortWithSentinel(int[] a) {\n    int[] b = new int[a.length + 1];\n    b[0] = Integer.MIN_VALUE; // sentinel\n    System.arraycopy(a, 0, b, 1, a.length);\n    for (int i = 2; i < b.length; i++) { // start at the third element\n        int key = b[i];\n        int j = i - 1;\n        while (b[j] > key) { // no j >= 0 test\n            b[j + 1] = b[j];\n            j--;\n        }\n        b[j + 1] = key;\n    }\n    System.arraycopy(b, 1, a, 0, a.length);\n}\n```\n\nIf the input array `a` has length **6** and is already sorted in ascending order, how many times is the comparison `b[j] > key` evaluated during the whole execution?\n\nChoose the correct number.",
      "answers": [
        {
          "text": "`5`",
          "is_correct": true,
          "explanation": "With a sorted array, each inner loop performs exactly one failing comparison; there are 5 outer iterations (i = 2..6)."
        },
        {
          "text": "`6`",
          "is_correct": false,
          "explanation": "Counts an extra comparison that never occurs because the sentinel guarantees the loop stops after the first test."
        },
        {
          "text": "`10`",
          "is_correct": false,
          "explanation": "Assumes two comparisons per outer iteration, which is true only for unsorted data."
        },
        {
          "text": "`0`",
          "is_correct": false,
          "explanation": "Incorrectly thinks the sentinel eliminates the need for any comparison."
        },
        {
          "text": "`15`",
          "is_correct": false,
          "explanation": "Counts all possible pairwise comparisons, which is far more than needed in the best case."
        }
      ],
      "explanation": "The array length is 6, so the outer `for` loop runs for `i = 2,3,4,5,6` – **5** iterations.\nBecause the input is already sorted, the first test `b[j] > key` is **false** each time, and the loop body never executes. Hence exactly one comparison per outer iteration, totalling **5**.\nThe sentinel guarantees that `j` never becomes negative, but it does not add extra comparisons.\n\n**Why the distractors seem plausible**\n- `6` mistakenly adds a comparison for the sentinel itself.\n- `10` assumes two comparisons per iteration (one true, one false) – a common mistake when analyzing loops.\n- `0` ignores the fact that the condition must be evaluated at least once.\n- `15` is the full `n(n‑1)/2` count for a worst‑case scenario.",
      "rubric": null,
      "distractors": "Common mistakes:\n- **Counting the sentinel comparison** → leads to 6.\n- **Assuming two tests per iteration** (true then false) → leads to 10.\n- **Thinking the sentinel removes all tests** → leads to 0.\n- **Using worst‑case formula** → leads to 15.",
      "subject": "Computer Science",
      "topics": [
        "ST047"
      ],
      "difficulty": "medium"
    },
    {
      "id": "q11",
      "text": "The array `int[] data = {38, 27, 43, 3, 9, 82, 10};` is sorted using a **bottom‑up merge sort** (iterative version) that starts by merging sub‑arrays of size 1, then size 2, and so on. After the **first merge pass** (size‑1 sub‑arrays merged into size‑2 sub‑arrays), which of the following represents the contents of `data`?\n\n```java\nint[] data = {38, 27, 43, 3, 9, 82, 10};\n// first pass: merge pairs (0,1), (2,3), (4,5); element 6 stays alone\n```",
      "answers": [
        {
          "text": "`[27, 38, 3, 43, 9, 82, 10]`",
          "is_correct": true,
          "explanation": "Each adjacent pair is sorted: (38,27)→[27,38]; (43,3)→[3,43]; (9,82)→[9,82]; last element 10 remains. Concatenating gives the array shown."
        },
        {
          "text": "`[38, 27, 3, 43, 82, 9, 10]`",
          "is_correct": false,
          "explanation": "Pairs were merged, but the elements inside each pair were **not reordered**; the merge step must sort each pair."
        },
        {
          "text": "`[27, 38, 3, 43, 82, 9, 10]`",
          "is_correct": false,
          "explanation": "The third pair (9,82) was mistakenly reversed; the correct order is [9,82]."
        },
        {
          "text": "`[3, 9, 10, 27, 38, 43, 82]`",
          "is_correct": false,
          "explanation": "This is the **fully sorted** array, which would appear only after all merge passes, not after the first one."
        },
        {
          "text": "`[27, 38, 3, 43, 9, 82, 10]` (same as correct, but assumes the last element is merged with a phantom element)",
          "is_correct": false,
          "explanation": "The last element 10 is left untouched; the answer is identical to the correct one only by coincidence. The distractor is meant to look plausible if a student forgets to mention the solitary element."
        }
      ],
      "explanation": "Bottom‑up merge sort works by repeatedly merging neighboring sub‑arrays of the current size.\n\n**First pass (size = 1)**\n1. Merge indices 0‑0 and 1‑1: `[38]` and `[27]` → `[27, 38]`\n2. Merge indices 2‑2 and 3‑3: `[43]` and `[3]`  → `[3, 43]`\n3. Merge indices 4‑4 and 5‑5: `[9]` and `[82]` → `[9, 82]`\n4. Index 6 has no partner, so it stays `[10]`.\n\nWriting the merged blocks back in order yields the array `[27, 38, 3, 43, 9, 82, 10]`.  This matches answer A.\n\n**Why the distractors are tempting**\n- B: Students sometimes think “merge” only concatenates sub‑arrays and forget the internal sorting.\n- C: A common off‑by‑one error swaps the order of the third pair only.\n- D: Confusing the *first* pass with the *final* sorted result.\n- E: Attempts to highlight the special case of the lone element, but gives the same visual as the correct answer, testing whether the student truly understands the process.",
      "rubric": null,
      "distractors": "Common misconceptions leading to each wrong choice:\n1. **No internal sorting** – believing merge just stitches sub‑arrays together.\n2. **Partial reversal** – mixing up the direction of comparison for one pair only.\n3. **Full sort assumption** – thinking the algorithm is finished after the first pass.\n4. **Over‑thinking the lone element** – adding a phantom merge step that does not change the array.",
      "subject": "Computer Science",
      "topics": [
        "ST048"
      ],
      "difficulty": "medium"
    },
    {
      "id": "q12",
      "text": "How many **element‑to‑element comparisons** does the classic recursive merge sort perform on the array\n`int[] arr = {5, 2, 4, 6, 1, 3, 7, 8};` ?\nAssume the implementation counts a comparison each time the line `if (left[i] <= right[j])` (or its opposite) is executed during a merge.\n\nSelect the correct total number of comparisons.\n\n```java\npublic static void mergeSort(int[] a, int lo, int hi) {\n    if (lo < hi) {\n        int mid = (lo + hi) / 2;\n        mergeSort(a, lo, mid);\n        mergeSort(a, mid + 1, hi);\n        merge(a, lo, mid, hi);\n    }\n}\n```",
      "answers": [
        {
          "text": "`17`",
          "is_correct": true,
          "explanation": "With N = 8 = 2³, merge sort makes N·log₂N – N + 1 = 8·3 – 8 + 1 = 17 comparisons (4 + 6 + 7 per level)."
        },
        {
          "text": "`15`",
          "is_correct": false,
          "explanation": "15 would be the count if each merge used exactly *size/2* comparisons, which is not true for the final merge of 8 elements."
        },
        {
          "text": "`12`",
          "is_correct": false,
          "explanation": "12 corresponds to counting only the first two levels (4 + 8) and forgetting the final level."
        },
        {
          "text": "`24`",
          "is_correct": false,
          "explanation": "24 is N·log₂N, the number of *operations* in the Θ(N log N) bound, but it over‑counts because each merge uses at most (size‑1) comparisons, not size."
        },
        {
          "text": "`16`",
          "is_correct": false,
          "explanation": "16 is a common off‑by‑one mistake that treats the last merge as needing only 6 comparisons instead of 7."
        }
      ],
      "explanation": "Merge sort works in log₂N = 3 levels for N = 8.\n\n**Level 1 (size = 1 → 2)**\n- Four merges, each compares the two single elements once → 4 comparisons.\n\n**Level 2 (size = 2 → 4)**\n- Two merges, each merges two length‑2 sub‑arrays. Merging two length‑2 arrays needs at most 3 comparisons. Total = 2 × 3 = 6.\n\n**Level 3 (size = 4 → 8)**\n- One merge of two length‑4 sub‑arrays. The worst‑case number of comparisons is 7 (because merging m elements uses at most m‑1 comparisons). Total = 7.\n\nAdding them together: 4 + 6 + 7 = **17**.\n\nThe other options stem from common counting errors:\n- Treating each level as requiring N comparisons (24).\n- Forgetting the final level (12).\n- Assuming each merge uses size/2 comparisons (15).\n- Off‑by‑one on the last merge (16).",
      "rubric": null,
      "distractors": "Misconception analysis:\n1. **N·log N counting** – students remember the Θ‑notation and multiply N by log N without adjusting for the “‑ size + 1” term.\n2. **Skipping the last merge** – thinking recursion stops after the second level.\n3. **Average‑case shortcut** – assuming each merge uses exactly half the possible comparisons.\n4. **Off‑by‑one** – mis‑applying the formula N·log N ‑ (N‑1).\nThese generate the distractor values 24, 12, 15, and 16 respectively.",
      "subject": "Computer Science",
      "topics": [
        "ST048"
      ],
      "difficulty": "medium"
    },
    {
      "id": "q13",
      "text": "The following `merge` method is used by a top‑down merge sort. It correctly copies the remaining elements from the **left** sub‑array, but **fails to copy** the remaining elements from the **right** sub‑array. This causes the final sorted array to miss some values (especially when the left sub‑array runs out first).\n\n```java\npublic static void merge(int[] a, int[] left, int[] right) {\n    int i = 0, j = 0, k = 0;\n    while (i < left.length && j < right.length) {\n        if (left[i] <= right[j]) {\n            a[k++] = left[i++];\n        } else {\n            a[k++] = right[j++];\n        }\n    }\n    // copy any remaining elements from left\n    while (i < left.length) {\n        a[k++] = left[i++];\n    }\n    // *** missing code here ***\n}\n```\nWhich line of code should be added at the location indicated by the comment to correctly finish the merge?\n",
      "answers": [
        {
          "text": "`while (j < right.length) { a[k++] = right[j++]; }`",
          "is_correct": true,
          "explanation": "This loop copies every leftover element from the right sub‑array into the destination array, completing the merge."
        },
        {
          "text": "`while (j < right.length) { a[k++] = left[j++]; }`",
          "is_correct": false,
          "explanation": "It mistakenly copies from the *left* array using the right index, producing duplicate or missing values."
        },
        {
          "text": "`if (j < right.length) a[k++] = right[j++];`",
          "is_correct": false,
          "explanation": "An `if` copies at most one element; any remaining elements beyond the first are lost."
        },
        {
          "text": "`while (i < left.length) { a[k++] = left[i++]; }`",
          "is_correct": false,
          "explanation": "Repeating the left‑copy loop does nothing for the right side and leaves elements missing."
        },
        {
          "text": "`// no additional code needed`",
          "is_correct": false,
          "explanation": "Without copying the right remainder, the merged array can be incomplete, which is exactly the bug described."
        }
      ],
      "explanation": "During a merge, after the main `while` loop terminates, **one** of the two sub‑arrays may still contain elements. The algorithm must copy the leftover elements from **whichever** sub‑array is not exhausted.\n\nThe given code already handles the left side:\n```java\nwhile (i < left.length) {\n    a[k++] = left[i++];\n}\n```\nTo handle the right side we need the symmetric loop:\n```java\nwhile (j < right.length) {\n    a[k++] = right[j++];\n}\n```\nThis copies every remaining element of `right` into `a`. All other suggested statements either reference the wrong array, copy only a single element, or repeat work already done.\n\n**Why the distractors are plausible**\n- Using `left` instead of `right` (choice B) reflects a copy‑and‑paste error.\n- An `if` statement (choice C) is a typical off‑by‑one mistake where a loop is mistakenly replaced by a single‑execution conditional.\n- Re‑adding the left‑copy loop (choice D) shows the tendency to forget that the right side also needs handling.\n- Claiming no code is needed (choice E) tests whether the student recognises that the current method is incomplete.",
      "rubric": null,
      "distractors": "Error patterns that produced the wrong answers:\n1. **Copy‑paste mistake** – duplicated the left‑side loop but didn’t change the array name.\n2. **Single‑execution conditional** – wrote `if` instead of `while`, losing all but one right‑hand element.\n3. **Redundant left loop** – believed the left side needed copying twice.\n4. **Denial of bug** – assuming the method is already correct.",
      "subject": "Computer Science",
      "topics": [
        "ST048"
      ],
      "difficulty": "medium"
    }
  ]
}