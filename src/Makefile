JC=javac
FILES=$(wildcard *.java)
FLAGS=-Xlint:unchecked

default: *.java
	$(JC) $(FILES)

verbose: *.java
	$(JC) $(FILES) $(FLAGS)

clean:
	$(RM) *.class
