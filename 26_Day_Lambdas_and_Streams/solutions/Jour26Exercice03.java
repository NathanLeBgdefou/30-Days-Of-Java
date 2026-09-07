import java.util.List;
public class Jour26Exercice03 {
    public static void main(String[] args) {
        List<Integer> longueurs = List.of("Java", "Python", "C").stream()
            .map(texte -> texte.length())
            .toList();
        System.out.println(longueurs);
    }
}
