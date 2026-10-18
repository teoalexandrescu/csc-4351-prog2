JAVAC = javac
JAVA = java

CUP_JAR ?= $(HOME)/compiler-tools/java-cup.jar
JLEX_CP ?= $(HOME)/compiler-tools
CLASSPATH = .:$(CUP_JAR)

.PHONY: all clean

all: Parse/Yylex.java Parse/Grm.java
	$(JAVAC) -cp "$(CLASSPATH)" ErrorMsg/*.java Symbol/*.java Absyn/*.java Parse/*.java

Parse/Yylex.java: Parse/Tiger.lex
	cd Parse && $(JAVA) -cp "$(JLEX_CP)" JLex.Main Tiger.lex && mv Tiger.lex.java Yylex.java

Parse/Grm.java: Parse/Grm.cup
	cd Parse && $(JAVA) -cp "$(CUP_JAR)" java_cup.Main -parser Grm -expect 0 < Grm.cup

clean:
	rm -f ErrorMsg/*.class Symbol/*.class Absyn/*.class Parse/*.class
	rm -f Parse/Grm.java Parse/sym.java Parse/Yylex.java