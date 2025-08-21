import os
import csv
import numpy as np
import argparse
import tempfile
import shutil
import tarfile
import requests
from io import BytesIO
from datetime import datetime

def download_and_extract_dataset(url, extract_dir):
    """Download and extract the JSB Chorales dataset."""
    print(f"Downloading dataset from {url}...")
    response = requests.get(url)
    response.raise_for_status()

    print("Extracting dataset...")
    with tarfile.open(fileobj=BytesIO(response.content), mode="r:gz") as tar:
        tar.extractall(path=extract_dir)

    # The dataset is nested in a directory
    jsb_dir = os.path.join(extract_dir, "jsb_chorales")
    return jsb_dir

def midi_note_to_abc(midi_note):
    """Convert MIDI note number to ABC notation."""
    # MIDI note 60 is middle C (C4)
    # In ABC notation, middle C is represented as "C"
    # Notes below middle C have commas, notes above have apostrophes

    note_names = ['C', 'C#', 'D', 'D#', 'E', 'F', 'F#', 'G', 'G#', 'A', 'A#', 'B']

    octave = midi_note // 12 - 5  # MIDI octave 5 is the middle octave (60-71)
    note_index = midi_note % 12

    note_name = note_names[note_index]

    # Convert sharps to ABC notation (use ^ instead of #)
    if '#' in note_name:
        note_name = note_name.replace('#', '')
        note_name = '^' + note_name

    # Add octave markers
    if octave < 0:
        # Add commas for lower octaves
        note_name += ',' * abs(octave)
    elif octave > 0:
        # Add apostrophes for higher octaves
        note_name += "'" * octave

    return note_name

def csv_to_abc(csv_file, abc_file, title=None):
    """Convert a CSV file from the JSB Chorales dataset to ABC notation."""
    # Read the CSV file
    with open(csv_file, 'r') as f:
        reader = csv.reader(f)

        # Check if the first line is a header
        first_line = next(reader, None)
        if first_line:
            # Try to convert first line to numbers - if it fails, it's likely a header
            try:
                first_row = list(map(float, first_line))
                # If conversion succeeds, it's data, not a header
                chorale = [first_row]
            except ValueError:
                # It's a header, skip it
                chorale = []
        else:
            chorale = []

        # Read the rest of the file
        for row in reader:
            try:
                chorale.append(list(map(float, row)))
            except ValueError:
                print(f"Warning: Skipping non-numeric row in {csv_file}")

        chorale = np.array(chorale)

    # Extract a title from the filename if none provided
    if title is None:
        title = os.path.splitext(os.path.basename(csv_file))[0]

    # Write ABC notation
    with open(abc_file, 'w') as f:
        # Write ABC header
        f.write(f"X:1\n")  # Reference number
        f.write(f"T:{title}\n")  # Title
        f.write(f"C:J.S. Bach\n")  # Composer
        f.write(f"M:4/4\n")  # Time signature (assuming 4/4 for Bach chorales)
        f.write(f"L:1/4\n")  # Default note length (quarter note)
        f.write(f"K:C\n")  # Key (assuming C major as default)
        f.write(f"%%score {{{1} {2} {3} {4}}}\n")  # Score directive for 4 voices

        # Process each voice separately
        for voice_idx in range(chorale.shape[1]):
            voice_name = ["Soprano", "Alto", "Tenor", "Bass"][voice_idx % 4]
            f.write(f"V:{voice_idx + 1} clef={get_clef_for_voice(voice_name)}\n")

            voice_notes = []
            current_measure = []
            measure_count = 0

            # Process each time step
            for step, row in enumerate(chorale):
                note = row[voice_idx]

                # Handle rests (usually represented as 0 or negative values)
                if note <= 0:
                    note_str = "z"
                else:
                    # Convert MIDI note to ABC notation
                    note_str = midi_note_to_abc(int(note))

                current_measure.append(note_str)

                # Assuming 4 steps per measure (4/4 time with quarter notes)
                if len(current_measure) == 4:
                    voice_notes.append(" ".join(current_measure) + " |")
                    current_measure = []
                    measure_count += 1

                    # Add line breaks every 4 measures for readability
                    if measure_count % 4 == 0:
                        voice_notes.append("\n")

            # Add any remaining notes in the last incomplete measure
            if current_measure:
                voice_notes.append(" ".join(current_measure) + " |")

            # Write voice notes
            f.write(" ".join(voice_notes))
            f.write("\n\n")  # Add spacing between voices

    print(f"Saved ABC file to {abc_file}")

def get_clef_for_voice(voice_name):
    """Return appropriate clef for a given voice."""
    if voice_name == "Soprano" or voice_name == "Alto":
        return "treble"
    elif voice_name == "Tenor":
        return "treble-8"  # Tenor clef (treble clef sounding an octave lower)
    else:  # Bass
        return "bass"

def process_dataset(dataset_dir, output_dir):
    """Process all CSV files in the dataset directory."""
    # Create output directory if it doesn't exist
    os.makedirs(output_dir, exist_ok=True)

    # Get all CSV files recursively
    csv_files = []
    for root, _, files in os.walk(dataset_dir):
        for file in files:
            if file.endswith('.csv'):
                csv_files.append(os.path.join(root, file))

    print(f"Found {len(csv_files)} CSV files")

    # Convert each CSV file to ABC
    for csv_file in csv_files:
        # Create a corresponding output path
        rel_path = os.path.relpath(csv_file, dataset_dir)
        abc_file = os.path.join(output_dir, rel_path.replace('.csv', '.abc'))

        # Create output directory if it doesn't exist
        os.makedirs(os.path.dirname(abc_file), exist_ok=True)

        # Extract title from directory structure (assuming format like train/bwv272.csv)
        parts = rel_path.split(os.path.sep)
        if len(parts) >= 2:
            title = f"Bach Chorale {parts[-1].replace('.csv', '')} ({parts[-2]} set)"
        else:
            title = f"Bach Chorale {os.path.basename(csv_file).replace('.csv', '')}"

        print(f"Converting {rel_path}...")
        csv_to_abc(csv_file, abc_file, title)

def main():
    parser = argparse.ArgumentParser(description='Convert JSB Chorales CSV files to ABC notation')
    parser.add_argument('--url', type=str,
                        default='https://github.com/ageron/handson-ml2/raw/master/datasets/jsb_chorales/jsb_chorales.tgz',
                        help='URL to download the dataset from')
    parser.add_argument('--output', type=str, default='abc_output',
                        help='Directory to save ABC files')
    args = parser.parse_args()

    # Create a temporary directory to extract the dataset
    with tempfile.TemporaryDirectory() as temp_dir:
        # Download and extract the dataset
        # dataset_dir = download_and_extract_dataset(args.url, temp_dir)
        dataset_dir = "../data/bach/train/"
        # Process the dataset
        process_dataset(dataset_dir, args.output)

    print(f"All files have been converted and saved to {args.output}")

if __name__ == "__main__":
    main()
