package Absyn;

public class IfStmt extends Stmt {
    public Exp test;
    public Stmt thenclause;
    public Stmt elseclause;

    public IfStmt(int p, Exp test, Stmt thenclause, Stmt elseclause) {
        pos = p;
        this.test = test;
        this.thenclause = thenclause;
        this.elseclause = elseclause;
    }
}