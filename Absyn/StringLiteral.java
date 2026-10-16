package Absyn;
public class StringLiteral extends Exp {
  public String value;
  public StringLiteral(int p, String v) { pos=p; value=v; }
}
