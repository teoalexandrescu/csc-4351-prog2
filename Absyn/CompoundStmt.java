package Absyn;

public class CompoundStmt extends Stmt {
    public StmtList statements;

    public CompoundStmt(int p, StmtList statements) {
        pos = p;
        this.statements = statements;
    }
}