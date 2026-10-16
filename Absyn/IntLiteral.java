package Absyn;
public class IntLiteral extends Exp {
  public Integer value;
  public IntLiteral(int p, Integer v) { pos=p; value=v; }
}
