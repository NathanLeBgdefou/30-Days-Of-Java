import java.util.Locale;
public class Jour18Exercice04 {
    static void afficher(FormateurJ18E04 formateur, String texte) {
        System.out.println(formateur.formater(texte));
    }
    public static void main(String[] args) {
        afficher(new NormalJ18E04(), "Java");
        afficher(new MajusculesJ18E04(), "Java");
    }
}
interface FormateurJ18E04 { String formater(String texte); }
class NormalJ18E04 implements FormateurJ18E04 {
    @Override public String formater(String texte) { return texte; }
}
class MajusculesJ18E04 implements FormateurJ18E04 {
    @Override public String formater(String texte) { return texte.toUpperCase(Locale.ROOT); }
}
