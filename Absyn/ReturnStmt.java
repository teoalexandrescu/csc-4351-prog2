package Absyn;

public class ReturnStmt extends Stmt {
    public Exp value;

    public ReturnStmt(int p, Exp value) {
        pos = p;
        this.value = value;
    }
}