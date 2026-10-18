package Absyn;
public class UnaryExp extends Exp {
  public String op;   // ++ -- & * + - ~ !
  public Exp exp;
  public UnaryExp(int p, String o, Exp e) { pos=p; op=o; exp=e; }
}
