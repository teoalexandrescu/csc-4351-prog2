package Absyn;
public class ParenExp extends Exp {
  public Exp exp;
  public ParenExp(int p, Exp e) { pos=p; exp=e; }
}
