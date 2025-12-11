name = Main
src = ./src/main/java
reportName = main
report = main.tex
reportSrc = ./src/report
dist = ./dist
file = Main.java FileReader.java FileDiff.java Line.java Pair.java Hash.java
file := $(patsubst %, $(src)/%, $(file))
old = ./reference/dataset/ResourceCompareInput_1.java
new = ./reference/dataset/ResourceCompareInput_2.java

make:
	mkdir -p $(dist)
	javac -d $(dist) $(file)
	clear
	@java -cp $(dist) $(name)

compile:
	mkdir -p $(dist)
	javac -d $(dist) $(file)

report:
	pdflatex --shell-escape $(reportSrc)/$(report)
	mv $(reportName).pdf $(dist)/
	rm *.log *.aux

run:
	@java -cp $(dist) $(name) $(args) $(old) $(new)

.SILENT: clean
clean:
	rm -f ./$(dist)/*.class
	rm -f ./$(dist)/*.pdf
