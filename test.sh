#!/bin/bash

# Base directory for the Java files
dataset="./reference/dataset"
result="./dist/result"
# result="./dist/resultNums"

# Loop through each file that matches *_1.java
for f in "$dataset"/*_1.java; do
    mkdir -p "$result"
    f2="${f/_1.java/_2.java}"
    o="${f/_1.java/.out}"
	o=$(basename "$o")
    if [ -e "$f2" ]; then
		java -cp ./dist/ Main "$f" "$f2" > "$result/$o"
    fi
done
