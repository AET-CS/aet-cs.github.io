import os
import csv
import numpy as np
from mido import Message, MidiFile, MidiTrack
import argparse
import tempfile
import shutil
import tarfile
import requests
from io import BytesIO

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

def csv_to_midi(csv_file, midi_file, tempo=120):
		"""Convert a CSV file from the JSB Chorales dataset to MIDI."""
		# Create a new MIDI file with a single track
		mid = MidiFile()
		track = MidiTrack()
		mid.tracks.append(track)

		# Set tempo (500000 microseconds per beat = 120 BPM)
		tempo_value = 500000  # in microseconds per beat
		track.append(Message('program_change', program=0, time=0))  # Piano

		# Read the CSV file
		with open(csv_file, 'r') as f:
			reader = csv.reader(f)
			first_line = next(reader, None)
			chorale = np.array([list(map(float, row)) for row in reader])

		# Each row in the CSV represents a time step (e.g., a 16th note)
		# Each column represents a voice (soprano, alto, tenor, bass)
		# The values represent MIDI note numbers

		ticks_per_beat = mid.ticks_per_beat
		# Let's use 16th notes (1/4 of a beat)
		ticks_per_step = ticks_per_beat // 4

		# Process each time step
		active_notes = {}  # To keep track of currently playing notes

		velocity = 100
		prev_notes = [0,0,0,0]
		now = 0
		last_event = 0
		for step, notes in enumerate(chorale):
				# Process each voice
				for voice, note in enumerate(notes):
						note_id = (voice, note)

						# Convert to integer MIDI note number
						midi_note = int(note)
						if midi_note == prev_notes[voice]:
								pass
						else:
								track.append(Message('note_off', note=prev_notes[voice], velocity=0,
																time=now-last_event, channel=voice % 16))
								track.append(Message('note_on', note=midi_note, velocity=velocity,
																time=0, channel=voice % 16))
								last_event = now
						prev_notes[voice] = midi_note
				now = now + ticks_per_step

		# Save the MIDI file
		print(track)
		mid.save(midi_file)
		print(f"Saved MIDI file to {midi_file}")

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

		# Convert each CSV file to MIDI
		for csv_file in csv_files:
				# Create a corresponding output path
				rel_path = os.path.relpath(csv_file, dataset_dir)
				midi_file = os.path.join(output_dir, rel_path.replace('.csv', '.mid'))

				# Create output directory if it doesn't exist
				os.makedirs(os.path.dirname(midi_file), exist_ok=True)

				print(f"Converting {rel_path}...")
				csv_to_midi(csv_file, midi_file)

def main():
		parser = argparse.ArgumentParser(description='Convert JSB Chorales CSV files to MIDI')
		parser.add_argument('--url', type=str,
												default='https://github.com/ageron/handson-ml2/raw/master/datasets/jsb_chorales/jsb_chorales.tgz',
												help='URL to download the dataset from')
		parser.add_argument('--output', type=str, default='midi_output',
												help='Directory to save MIDI files')
		args = parser.parse_args()

		# Create a temporary directory to extract the dataset
		# with tempfile.TemporaryDirectory() as temp_dir:
				# Download and extract the dataset
				# dataset_dir = download_and_extract_dataset(args.url, temp_dir)

				# Process the dataset
		dataset_dir="../data/bach/valid"
		process_dataset(dataset_dir, args.output)
		print(dataset_dir)
		print(f"All files have been converted and saved to {args.output}")

if __name__ == "__main__":
		main()