package Absyn;
public class SwitchStmt extends Stmt { public Exp test; public Stmt body; public SwitchStmt(int p, Exp t, Stmt b) { pos=p; test=t; body=b; } }
