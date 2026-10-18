package Absyn;
public class DefaultStmt extends Stmt { public Stmt body; public DefaultStmt(int p, Stmt b) { pos=p; body=b; } }
