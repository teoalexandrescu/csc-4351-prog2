package Absyn;
public class CommaExp extends Exp { public Exp left,right; public CommaExp(int p, Exp l, Exp r) { pos=p; left=l; right=r; } }
