package Absyn;
public class SwitchStmt extends Stmt {
    public SwitchStmt(Exp e, CaseList c, int p) {exp=e;cases=c;pos=p;}
}