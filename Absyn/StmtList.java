package Absyn;

public class StmtList extends Absyn {
    public Stmt head;
    public StmtList tail;

    public StmtList(Stmt head, StmtList tail) {
        this.head = head;
        this.tail = tail;
    }
}