name = Main
src = ./src/main/java
dist = ./bin
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

run:
	@java -cp $(dist) $(name)

.SILENT: clean
clean:
	rm -f ./$(dist)/*.class
