package Absyn;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.List;

/* Prints any Absyn node using reflection, so new node classes need no printing code. */
public class Print {
  PrintWriter out;
  public Print(PrintWriter o) { out = o; }

  void indent(int d) { for (int i = 0; i < d; i++) out.print("  "); }

  public void prExp(Exp e, int d) { pr(e, d); out.flush(); }

  void pr(Object o, int d) {
    if (o == null) { out.print("null"); return; }
    if (o instanceof Absyn) {
      out.print(o.getClass().getSimpleName() + "(");
      boolean first = true;
      for (Field f : o.getClass().getFields()) {
        if (f.getName().equals("pos")) continue;
        if (!first) out.print(",");
        first = false;
        out.println(); indent(d + 1);
        out.print(f.getName() + "=");
        try { pr(f.get(o), d + 1); } catch (Exception x) { out.print("?"); }
      }
      out.print(")");
    } else if (o instanceof List) {
      out.print("[");
      boolean first = true;
      for (Object x : (List) o) {
        if (!first) out.print(",");
        first = false;
        out.println(); indent(d + 1);
        pr(x, d + 1);
      }
      out.print("]");
    } else out.print(o);
  }
}
