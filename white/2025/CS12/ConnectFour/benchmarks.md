---
title: Connect Four Benchmarks
---

To help you keep pace in your own coding here are some things to expect, based on my tests. All tests run on a Mac Powerbook M4 Pro. All code includes alpha-beta pruning and *no* heuristic.

1. Minimax-2 vs Random, 100 games. Random wins 5%, run time < 0.01 sec per game.
2. Minimax-4 vs Random, 100 games. Random wins 8%, run time < 0.03 sec per game. (minmax 4 is not worse than minmax 2, random just got lucky. It can always get itself into a forced with that minmax doesn't see, if it's lucky)
3. Minmax-10 vs Random, 10 games. Random wins 0%, run time 10s per game, but highly variable.
4. Minmax-2 vs Minmax-4, 100 games. Minmax-4 wins 100%, run time < 0.03 sec per game