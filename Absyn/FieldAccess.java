package Absyn;
public class FieldAccess extends Exp {
  public Exp record;
  public String op;   // "." or "->"
  public Symbol.Symbol field;
  public FieldAccess(int p, Exp r, String o, Symbol.Symbol f) { pos=p; record=r; op=o; field=f; }
}
