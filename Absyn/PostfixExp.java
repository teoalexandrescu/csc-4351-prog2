package Absyn;
public class PostfixExp extends Exp {
  public Exp exp;
  public String op;   // "++" or "--"
  public PostfixExp(int p, Exp e, String o) { pos=p; exp=e; op=o; }
}
