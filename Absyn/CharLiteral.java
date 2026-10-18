package Absyn;
public class CharLiteral extends Exp {
  public String value;
  public CharLiteral(int p, String v) { pos=p; value=v; }
}
