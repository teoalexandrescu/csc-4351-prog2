package Absyn;
public class CAggregateDeclaration extends Absyn { public String kind; public java.util.LinkedList modifiers; public Symbol.Symbol name; public Exp fields; public CAggregateDeclaration(int p,String k,java.util.LinkedList m,Symbol.Symbol n,Exp f){pos=p;kind=k;modifiers=m;name=n;fields=f;} }
