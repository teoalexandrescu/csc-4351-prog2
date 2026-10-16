package Absyn;
public class LabelStmt extends Stmt {
    public Symbol.Symbol label;
    public Stmt stmt;
    public LabelStmt(int p, Symbol.Symbol label, Stmt stmt) { pos = p; this.label = label; this.stmt = stmt; }
}
