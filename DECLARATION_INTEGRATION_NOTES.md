# Experimental declaration grammar integration

Based on user-provided simplified declaration productions. This is an **unverified candidate**: CUP regeneration and compilation could not be performed in the authoring environment because Java CUP was unavailable. Do not replace the working original without compiling and testing.

The original 5 tests remain included. Additional sample: `test6.c`.

## On the Mac

```sh
cd ~/Desktop/<extracted-project-directory>/csc-4351-prog2/Parse
java -cp "$HOME/compiler-tools/java-cup.jar" java_cup.Main -parser Grm < Grm.cup
cd ..
javac -cp ".:$HOME/compiler-tools/java-cup.jar" ErrorMsg/*.java Symbol/*.java Absyn/*.java Parse/*.java
java -cp ".:$HOME/compiler-tools/java-cup.jar" Parse.Main test6.c
```

CUP grammar may require conflict resolution or adjustments. This candidate retains legacy `int` declaration/function rules so earlier tests can be rerun. This is not proof of complete C89 coverage. Also note the supplied 2025 PDF and 2026 assignment may differ.
