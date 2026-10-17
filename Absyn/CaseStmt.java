package Absyn;
public class CaseStmt extends Stmt { public Exp value; public Stmt body; public CaseStmt(int p, Exp v, Stmt b) { pos=p; value=v; body=b; } }
