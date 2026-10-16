package Absyn;
public class IndexExp extends Exp {
  public Exp array, index;
  public IndexExp(int p, Exp a, Exp i) { pos=p; array=a; index=i; }
}
