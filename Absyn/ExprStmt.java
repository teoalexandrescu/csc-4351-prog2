package Absyn;

public class ExprStmt extends Stmt {
    public Exp exp;

    public ExprStmt(int p, Exp exp) {
        pos = p;
        this.exp = exp;
    }
}