#!/usr/bin/env python3
"""
Fix compressed dataset files
"""

import os
import gzip
import shutil
import json
from datasets import load_from_disk

def decompress_dataset(dataset_dir="./cifar10_dataset"):
    """Decompress all .gz files in the dataset directory"""
    print(f"Decompressing files in {dataset_dir}...")
    
    # Walk through all subdirectories
    for root, dirs, files in os.walk(dataset_dir):
        for file in files:
            if file.endswith('.gz'):
                gz_path = os.path.join(root, file)
                out_path = gz_path[:-3]  # Remove .gz extension
                
                print(f"Decompressing: {gz_path} -> {out_path}")
                
                # Decompress the file
                with gzip.open(gz_path, 'rb') as f_in:
                    with open(out_path, 'wb') as f_out:
                        shutil.copyfileobj(f_in, f_out)
                
                # Remove the .gz file after successful decompression
                os.remove(gz_path)
    
    print("Decompression complete!")

def check_dataset_structure(dataset_dir="./cifar10_dataset"):
    """Check and display dataset structure"""
    print(f"\nChecking dataset structure in {dataset_dir}:")
    
    for root, dirs, files in os.walk(dataset_dir):
        level = root.replace(dataset_dir, '').count(os.sep)
        indent = ' ' * 2 * level
        print(f"{indent}{os.path.basename(root)}/")
        
        # Show directories
        subindent = ' ' * 2 * (level + 1)
        for d in dirs:
            print(f"{subindent}{d}/")
        
        # Show files (limit to first 10)
        for i, f in enumerate(files):
            if i < 10:
                size = os.path.getsize(os.path.join(root, f)) / (1024 * 1024)  # MB
                print(f"{subindent}{f} ({size:.2f} MB)")
            elif i == 10:
                print(f"{subindent}... and {len(files) - 10} more files")

def load_dataset_safely(dataset_dir="./cifar10_dataset"):
    """Load dataset with error handling"""
    try:
        print(f"\nLoading dataset from {dataset_dir}...")
        dataset = load_from_disk(dataset_dir)
        
        print("Dataset loaded successfully!")
        print(f"Dataset type: {type(dataset)}")
        print(f"Available splits: {list(dataset.keys())}")
        
        if 'train' in dataset:
            print(f"Training samples: {len(dataset['train'])}")
            print(f"Features: {dataset['train'].features}")
            
            # Show a sample
            print("\nFirst sample:")
            first_sample = dataset['train'][0]
            for key, value in first_sample.items():
                if key == 'img':
                    print(f"  {key}: {type(value)} with shape {getattr(value, 'size', 'unknown')}")
                else:
                    print(f"  {key}: {value}")
        
        return dataset
        
    except Exception as e:
        print(f"Error loading dataset: {e}")
        print("\nTrying alternative loading methods...")
        
        # Try to read the metadata
        try:
            metadata_path = os.path.join(dataset_dir, "dataset_dict.json")
            if os.path.exists(metadata_path):
                with open(metadata_path, 'r') as f:
                    metadata = json.load(f)
                print(f"Metadata found: {metadata}")
        except:
            pass
        
        return None

def fix_and_load(dataset_dir="./cifar10_dataset"):
    """Complete fix and load process"""
    # First check if files are compressed
    compressed_files = []
    for root, dirs, files in os.walk(dataset_dir):
        compressed_files.extend([os.path.join(root, f) for f in files if f.endswith('.gz')])
    
    if compressed_files:
        print(f"Found {len(compressed_files)} compressed files")
        decompress_dataset(dataset_dir)
    
    # Check structure
    check_dataset_structure(dataset_dir)
    
    # Try to load
    dataset = load_dataset_safely(dataset_dir)
    
    return dataset

if __name__ == "__main__":
    import sys
    
    if len(sys.argv) > 1:
        dataset_dir = sys.argv[1]
    else:
        dataset_dir = "./cifar10_dataset"
    
    # Fix and load the dataset
    dataset = fix_and_load(dataset_dir)
    
    if dataset:
        print("\nDataset is ready to use!")
        print("You can now load it with:")
        print(f"  from datasets import load_from_disk")
        print(f"  dataset = load_from_disk('{dataset_dir}')")
    else:
        print("\nManual fix needed. Check the error messages above.")
