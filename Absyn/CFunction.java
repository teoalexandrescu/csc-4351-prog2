package Absyn;
public class CFunction extends Absyn { public Symbol.Symbol name; public Stmt body; public CFunction(int p, Symbol.Symbol n, Stmt b) { pos=p; name=n; body=b; } }
