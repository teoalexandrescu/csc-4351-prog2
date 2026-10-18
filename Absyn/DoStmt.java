package Absyn;
public class DoStmt extends Stmt {
    public Stmt body;
    public Exp test;
    public DoStmt(int p, Stmt body, Exp test) { pos = p; this.body = body; this.test = test; }
}
