name = Main
src = ./src/main/java
reportName = main
report = main.tex
reportSrc = ./src/report
dist = ./dist
file = Main.java FileHandler.java Hash.java
file := $(patsubst %, $(src)/%, $(file))

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
	@java -cp $(dist) $(name)

.SILENT: clean
clean:
	rm -f ./$(dist)/*.class
	rm -f ./$(dist)/*.pdf
