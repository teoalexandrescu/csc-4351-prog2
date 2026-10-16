package Absyn;

public class WhileStmt extends Stmt {
    public Exp test;
    public Stmt body;

    public WhileStmt(int p, Exp test, Stmt body) {
        pos = p;
        this.test = test;
        this.body = body;
    }
}
