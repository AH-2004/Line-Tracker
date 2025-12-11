name = lineTracker
src = ./src/main/java
reportName = main
dist = ./bin
file = Main.java FileReader.java FileDiff.java Line.java Pair.java Hash.java
file := $(patsubst %, $(src)/%, $(file))

make:
	mkdir -p $(dist)
	javac -d $(dist) $(file)
	clear
	@java -cp $(dist) $(name)

compile:
	mkdir -p $(dist)
	javac -d $(dist) $(file)

run:
	@java -cp $(dist) $(name) $(args)
