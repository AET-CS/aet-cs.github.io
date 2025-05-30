#!/usr/bin/env python3
"""
Script to recursively find .md files that start with # TITLE and convert them
to Jekyll front matter format with --- title: "TITLE" ---

Usage:
  python convert_md_titles.py                    # Test mode (default)
  python convert_md_titles.py --apply           # Apply changes
  python convert_md_titles.py --path /custom/path --apply  # Custom path
"""

import os
import re
import argparse
from pathlib import Path


def extract_h1_title(content):
    """Extract the H1 title from markdown content if it's the first non-empty line."""
    lines = content.split("\n")

    # Skip empty lines and find first non-empty line
    for line in lines:
        stripped = line.strip()
        if not stripped:
            continue

        # Check if it's an H1 heading
        if stripped.startswith("# "):
            title = stripped[2:].strip()  # Remove '# ' prefix
            return title
        else:
            # First non-empty line is not H1, return None
            return None

    return None


def has_front_matter(content):
    """Check if content already has Jekyll front matter."""
    return content.strip().startswith("---")


def convert_content(content, title):
    """Convert content by adding front matter and removing H1."""
    lines = content.split("\n")
    new_lines = []
    h1_removed = False

    # Add front matter at the beginning
    new_lines.append("---")
    new_lines.append(f'title: "{title}"')
    new_lines.append("---")
    new_lines.append("")  # Empty line after front matter

    # Process existing content, removing the first H1
    for line in lines:
        if not h1_removed and line.strip().startswith("# "):
            h1_removed = True
            continue  # Skip this line
        new_lines.append(line)

    return "\n".join(new_lines)


def process_markdown_files(directory, test_mode=True):
    """Process all .md files in the directory recursively."""
    changes = []

    # Find all .md files recursively
    md_files = Path(directory).rglob("*.md")

    for md_file in md_files:
        # Skip files in env/ directory
        if "env/" in str(md_file):
            continue
        try:
            # Read file content
            with open(md_file, "r", encoding="utf-8") as f:
                content = f.read()

            # Skip if already has front matter
            if has_front_matter(content):
                continue

            # Extract H1 title
            title = extract_h1_title(content)
            if not title:
                continue

            # Record the change
            first_line = content.split("\n")[0] if content else ""
            change_info = {
                "file": str(md_file),
                "title": title,
                "first_line": first_line,
                "content": content,
            }
            changes.append(change_info)

            # Apply changes if not in test mode
            if not test_mode:
                new_content = convert_content(content, title)
                with open(md_file, "w", encoding="utf-8") as f:
                    f.write(new_content)

        except Exception as e:
            print(f"Error processing {md_file}: {e}")

    return changes


def main():
    parser = argparse.ArgumentParser(
        description="Convert markdown H1 titles to Jekyll front matter",
        formatter_class=argparse.RawDescriptionHelpFormatter,
        epilog="""
Examples:
  python convert_md_titles.py                    # Test mode
  python convert_md_titles.py --apply           # Apply changes
  python convert_md_titles.py --path /docs --apply  # Custom path with changes
        """,
    )

    parser.add_argument(
        "--path", default=".", help="Directory to search (default: current directory)"
    )
    parser.add_argument(
        "--apply", action="store_true", help="Apply changes (default: test mode)"
    )

    args = parser.parse_args()

    # Validate directory
    if not os.path.isdir(args.path):
        print(f"Error: Directory '{args.path}' does not exist.")
        return 1

    # Process files
    print(
        f"{'Applying changes to' if args.apply else 'Scanning'} markdown files in: {os.path.abspath(args.path)}"
    )
    print()

    changes = process_markdown_files(args.path, test_mode=not args.apply)

    if not changes:
        print("No markdown files found that need conversion.")
        return 0

    # Display results
    print(
        f"Found {len(changes)} file(s) to {'convert' if args.apply else 'be converted'}:"
    )
    print()

    for change in changes:
        print(f"File: {change['file']}")
        print(f"Title: {change['title']}")
        print(f"First line: {change['first_line']}")
        print("-" * 50)

    if not args.apply:
        print()
        print("This was a test run. Use --apply to make actual changes.")
        print("Example: python convert_md_titles.py --apply")
    else:
        print()
        print(f"Successfully converted {len(changes)} file(s)!")

    return 0


if __name__ == "__main__":
    exit(main())
