

## Answer Key

1. **80** — sum of all 16 elements
2. **20** — even elements: 4, 2, 6, 8 → 4 + 2 + 6 + 8 = 20
3. **55** — excludes elements where BOTH r and c are odd: arr[1][1]=9, arr[1][3]=6, arr[3][1]=7, arr[3][3]=3 → 80 − 25 = 55
4. **0** — diagonal elements: 3, 9, 5, 3 — none are even
5. **42** — row 0: all 4 (9), row 1: cols 1-3 (17), row 2: cols 2-3 (13), row 3: col 3 (3) → 9+17+13+3 = 42
6. **61** — top row: 9, bottom row: 28, left middle (rows 1,2): 10, right middle (rows 1,2): 14 → 9+28+10+14 = 61
7. **6** — comparisons where right > left: (1→4), (4→1 no), (1→1 no), (5→9), (9→2 no), (2→6), (5→3 no), (3→5), (5→8), (9→7 no), (7→9), (9→3 no) → 6 yes
8. **360** — array mutates row-by-row: row 1 becomes [15,9,8,6], row 2 becomes [75,27,40,48], row 3 becomes [675,189,360,144] → arr[3][2] = 360