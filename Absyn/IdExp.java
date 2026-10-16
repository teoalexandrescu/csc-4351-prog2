package Absyn;
public class IdExp extends Exp {
  public Symbol.Symbol name;
  public IdExp(int p, Symbol.Symbol n) { pos=p; name=n; }
}
