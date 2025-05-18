#!/usr/bin/env bash

# Usage: ./preview.sh your-document.md

if [ $# -eq 0 ]; then
    echo "Usage: $0 markdown-file.md"
    exit 1
fi

INPUT_FILE=$1
OUTPUT_FILE="${INPUT_FILE%.*}.html"

echo "Converting $INPUT_FILE to $OUTPUT_FILE..."

pandoc "$INPUT_FILE" \
    --lua-filter=question-answer.lua \
    --include-in-header=includes.html \
    --standalone \
    --katex \
    --metadata title="Preview" \
    -o "$OUTPUT_FILE"

# Open the file in browser
if [[ "$OSTYPE" == "darwin"* ]]; then
    # macOS
    open "$OUTPUT_FILE"
elif [[ "$OSTYPE" == "linux-gnu"* ]]; then
    # Linux
    xdg-open "$OUTPUT_FILE"
elif [[ "$OSTYPE" == "msys" || "$OSTYPE" == "cygwin" ]]; then
    # Windows
    start "$OUTPUT_FILE"
else
    echo "Preview generated at $OUTPUT_FILE"
fi