# C89 AST integration candidate

This is an **unverified integration candidate**, not a fully tested or complete ANSI C89 parser. The original ZIP is unchanged.

## Changes
- Added `Program`, `CFunction`, `CDeclaration`, `SwitchStmt`, `CaseStmt`, `DefaultStmt`, `CommaExp`, `SizeofExp` AST classes.
- Connected AST construction across the grammar's supported expressions, statements, declarations and top-level functions.
- Kept statement lists in source order, and corrected `&&` / `||` lexer token alignment.
- Did not replace existing Tiger `FunctionDec` or teammate AST classes.

## Important limits
- The grammar is a **subset of C89**, not all ANSI C89. In particular, function signatures are limited to `int name()`, declarations to `int name;`, and C type/declarator syntax is incomplete.
- The existing grammar contains legacy Tiger tokens and placeholder `type_name`.
- `sizeof` is only modeled using a placeholder type name; array/function declarations, casts, pointer declarators, initializers, enums, and more need further work.
- The Java AST classes were compiled successfully in the sandbox. **Java CUP generation and end-to-end parser tests were not possible here because the CUP generator and runtime JAR were not included in the upload.**

## Test on your Mac
From the project root (where your existing `~/compiler-tools/java-cup.jar` is installed):

```sh
cd Parse
java -cp "$HOME/compiler-tools/java-cup.jar" java_cup.Main -parser Grm < Grm.cup
cd ..
javac -cp ".:$HOME/compiler-tools/java-cup.jar" ErrorMsg/*.java Symbol/*.java Absyn/*.java Parse/*.java
java -cp ".:$HOME/compiler-tools/java-cup.jar" Parse.Main test.c
```

**Do not merge this candidate until the CUP generation and sample tests pass.** If the generator reports errors, share its exact output for a targeted correction.
