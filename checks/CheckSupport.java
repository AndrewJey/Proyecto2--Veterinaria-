import java.lang.reflect.*;
public class CheckSupport {
    private static int count;
    public static void check(boolean value, String message) { count++; if (!value) throw new AssertionError(message); }
    public static void equal(Object a, Object b) { check(java.util.Objects.equals(a,b), "Expected " + b + ", got " + a); }
    public static void close(double a, double b) { check(Math.abs(a-b) <= Math.max(0.0001, Math.abs(b)*0.000001), "Expected " + b + ", got " + a); }
    public static Object field(Object target, String name) throws Exception { Field f=target.getClass().getDeclaredField(name);f.setAccessible(true);return f.get(target); }
    public static void event(Object target, String name) throws Exception { Method m=target.getClass().getDeclaredMethod(name,java.awt.event.ActionEvent.class);m.setAccessible(true);m.invoke(target,new Object[]{null}); }
    public static void done() { System.out.println("PASS: " + count + " assertions"); }
}
