import java.util.Objects;
public class Jour19Exemple {
    public static void main(String[] args) {
        PointJ19Exemple a = new PointJ19Exemple(2, 3);
        PointJ19Exemple b = new PointJ19Exemple(2, 3);
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(a.hashCode() == b.hashCode());
        String inconnu = null;
        System.out.println(Objects.equals(inconnu, "Java"));
        System.out.println(a.x());
    }
}
record PointJ19Exemple(int x, int y) {}
