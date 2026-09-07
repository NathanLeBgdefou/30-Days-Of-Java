import java.util.Locale;

public class Jour04Exemple {
    public static void main(String[] args) {
        String saisie = "  JAVA  ";
        String propre = saisie.strip().toLowerCase(Locale.ROOT);
        System.out.println(propre);
        System.out.println(propre.length());
        System.out.println("java".equals(propre));
        System.out.println(propre.substring(0, 2));
        System.out.println("Original : [" + saisie + "]");
    }
}
