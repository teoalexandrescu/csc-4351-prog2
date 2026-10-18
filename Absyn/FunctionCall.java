package Absyn;
public class FunctionCall extends Exp {
  public Exp function;
  public java.util.LinkedList arguments;
  public FunctionCall(int p, Exp f, java.util.LinkedList a) { pos=p; function=f; arguments=a; }
}
