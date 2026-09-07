import java.util.List;
public class Jour26Exercice04 {
    public static void main(String[] args) {
        List<String> titres = List.of(" Java ", "", "Maths", "Java", "   ").stream()
            .map(String::strip)
            .filter(texte -> !texte.isEmpty())
            .distinct()
            .sorted()
            .toList();
        System.out.println(titres);
    }
}
