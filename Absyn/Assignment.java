package Absyn;
public class Assignment extends Exp {
  public Exp target;
  public String op;   // = += -= etc.
  public Exp value;
  public Assignment(int p, Exp t, String o, Exp v) { pos=p; target=t; op=o; value=v; }
}
