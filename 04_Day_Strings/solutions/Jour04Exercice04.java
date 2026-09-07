import java.util.Locale;
public class Jour04Exercice04 {
    public static void main(String[] args) {
        String prenom = "  NATHAN ".strip().toLowerCase(Locale.ROOT);
        String nom = " LERAY  ".strip().toLowerCase(Locale.ROOT);
        System.out.println(prenom + "." + nom);
    }
}
