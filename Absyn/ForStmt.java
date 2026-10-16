package Absyn;
public class ForStmt extends Stmt {
    public Exp init, test, update;   // each may be null
    public Stmt body;
    public ForStmt(int p, Exp init, Exp test, Exp update, Stmt body) {
        pos = p; this.init = init; this.test = test; this.update = update; this.body = body;
    }
}
