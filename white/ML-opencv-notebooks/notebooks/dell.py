file = "03.01-Measuring-Model-Performance-with-Scoring-Functions.ipynb"

import json

def delete_last_cell(notebook_path):
	with open(notebook_path, 'r', encoding='utf-8') as file:
		notebook = json.load(file)

	if notebook.get('cells'):
		last_cell = notebook['cells'].pop()
		print(last_cell)
		if last_cell['source'] and len(last_cell['source']) > 0:
			print("NAVIGATION" in last_cell['source'][0])
			# Save the modified notebook back to the file
			with open(notebook_path, 'w', encoding='utf-8') as file:
					json.dump(notebook, file, indent=4)
			print(f"Notebook '{notebook_path}' updated successfully.")
		else:
				print("No navigation cells found in the notebook.", notebook_path)

import glob

for file in glob.glob("*.ipynb"):
	delete_last_cell(file)