#!/bin/bash
pandoc \
./calendar.md \
./lessons/hw01.md \
./lessons/hw02.md \
./lessons/wsl.md \
./lessons/cw03.md \
./lessons/Bayes_Theorem_Student.md \
./lessons/jupyter-python-intro.md \
./lessons/Linear_regression_derivation.md \
./lessons/least-squares-01.html \
./lessons/Correlation_Coefficient.md \
-o ML.pdf
