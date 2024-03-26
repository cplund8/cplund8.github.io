#!/bin/zsh

# Check if the argument is given
if [ $# -lt 1 ]; then
    echo "Usage: $0 <Package-Name>"
    exit 1
fi

# The package name is also used to infer the main class name
PackageName=$1

# Define the directory where the Java file is expected to be found
DirName="./$PackageName"

# Using zsh globbing to find the Java file
# This assumes there is only one Java file in the specified directory
JavaFiles=($DirName/*.java)

if [ ${#JavaFiles[@]} -eq 0 ]; then
    echo "No Java files found in $DirName."
    exit 2
elif [ ${#JavaFiles[@]} -gt 1 ]; then
    echo "More than one Java file found in $DirName. Please ensure only one Java file exists."
    exit 3
else
    # Extract the base name of the Java file, without the extension
    MainClassFile=$(basename ${JavaFiles[1]} .java)
    
    # Create or overwrite the manifest file
    echo "Manifest-Version: 1.0" > manifest.txt
    echo "Created-By: JustinSaginor" >> manifest.txt
    echo "Package-Name: $PackageName" >> manifest.txt
    # Assuming the package structure mirrors directory structure for the Main-Class attribute
    echo "Main-Class: $PackageName.$MainClassFile" >> manifest.txt
    echo "Manifest file created successfully."
fi
