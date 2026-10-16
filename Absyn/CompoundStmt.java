package Absyn;

public class CompoundStmt extends Stmt {
    public java.util.LinkedList declarations;  // may be empty
    public StmtList statements;                // may be null

    public CompoundStmt(int p, java.util.LinkedList declarations, StmtList statements) {
        pos = p;
        this.declarations = declarations;
        this.statements = statements;
    }
}
