package Absyn;
public class GotoStmt extends Stmt {
    public Symbol.Symbol label;
    public GotoStmt(int p, Symbol.Symbol label) { pos = p; this.label = label; }
}
