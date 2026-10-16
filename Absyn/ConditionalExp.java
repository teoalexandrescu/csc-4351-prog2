package Absyn;
public class ConditionalExp extends Exp {
  public Exp test, thenExp, elseExp;
  public ConditionalExp(int p, Exp t, Exp a, Exp b) { pos=p; test=t; thenExp=a; elseExp=b; }
}
