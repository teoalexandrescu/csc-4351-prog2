# CSC 4351 — Project 2: C89 Parser
## Team
Lizeth Rodriguez
Theodorah Alexandrescu
Emerson Ricketts
Amiyah Humphrey

## Team Contributions

- Lizeth Rodriguez: Integrated the C89 lexer and parser,
  implemented declaration grammar and AST support,
  merged team contributions, and tested the final parser.

- Teodora Alexander: Implemented expression and statement
  AST classes, AST printing support, and initial Makefile.

- Amiah Humphrey: Implemented declaration AST classes
  and integrated declaration rules into the grammar.

- Emerson Ricketts: Worked on grammar productions and
  AST integration for sections 3.6–3.6.5, including
  NullStmt support.
## Description
A parser for a simplified version of ANSI C89 using Java,
JLex, and Java CUP. The program generates an Abstract Syntax
Tree (AST) from C source code.

## Build
Requires Java, JLex, and Java CUP.

Compile:
    make

Clean and rebuild:
    make clean
    make

## Run
    java -cp ".:$HOME/compiler-tools/java-cup.jar" Parse.Main test.c

## Project Files
- Parse/Tiger.lex — Lexer
- Parse/Grm.cup — Grammar and parser
- Absyn/ — AST classes
- Parse/Main.java — Main program
- Makefile — Build instructions

## Features
Supports variable and function declarations, expressions,
assignments, arrays, pointers, function calls, conditionals,
loops, and return statements.

## Testing
- Clean build successful
- 0 parser errors
- 0 parser conflicts
- AST generation tested successfully

## Known Limitations
- Supports a simplified subset of ANSI C89.
- Does not compile or execute C programs.
- Does not perform semantic analysis or type checking.
- Some grammar rules may not support every valid C89 construct.
## Parsing Conflicts and Error Recovery

Java CUP reports 0 parsing conflicts, so no unresolved
shift/reduce or reduce/reduce conflicts remain.

The grammar includes an error production, but comprehensive
error recovery has not been verified. Invalid input may
therefore terminate parsing without recovering.
## AI Usage
AI was used to assist with merging team members' Git branches, understanding the changes and contributions made in each branch,
resolving integration issues, and troubleshooting build errors.