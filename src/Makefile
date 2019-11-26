JC=javac
FILES=$(wildcard *.java)
FLAGS=-Xlint:unchecked -Xlint:deprecation

default: *.java
	$(JC) $(FILES)

verbose: *.java
	$(JC) $(FILES) $(FLAGS)

clean:
	$(RM) *.class
