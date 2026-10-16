package Absyn;
public class BinaryExp extends Exp {
  public Exp left;
  public String op;   // any binary operator, or "," for the comma operator
  public Exp right;
  public BinaryExp(int p, Exp l, String o, Exp r) { pos=p; left=l; op=o; right=r; }
}
