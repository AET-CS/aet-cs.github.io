# Binary Search Cases - Answer Key

For these tests, the array contains the numbers `0` through `16`, so `arr[i] = i`.
A **PASS** means the algorithm returned the correct index for a value in the array, or `-1` for a value not in the array.

| Target | A | B | C | D | E | F |
|---:|:---:|:---:|:---:|:---:|:---:|:---:|
| -1 | PASS | HANG | PASS | HANG | PASS | PASS |
| 0 | FAIL | PASS | FAIL | HANG | PASS | PASS |
| 1 | PASS | PASS | FAIL | HANG | PASS | PASS |
| 8 | PASS | PASS | PASS | PASS | PASS | PASS |
| 15 | PASS | PASS | FAIL | HANG | PASS | PASS |
| 16 | FAIL | HANG | FAIL | HANG | PASS | PASS |
| 17 | PASS | HANG | PASS | HANG | PASS | PASS |

## Summary

- **Version F:** Correct binary search.
- **Version E:** Gives correct answers, but is linear search because `mid = low`.
- **Version A:** Fails when the target is the final remaining endpoint, such as `0` or `16`.
- **Version B:** Can hang because `low = mid` and `high = mid` may make no progress.
- **Version C:** Reverses the search direction.
- **Version D:** Calculates `mid` only once, so it usually hangs after the first comparison.

`HANG` means the search does not terminate for that test case.
