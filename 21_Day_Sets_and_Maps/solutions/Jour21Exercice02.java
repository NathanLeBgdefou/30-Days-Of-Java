import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
public class Jour21Exercice02 {
    public static void main(String[] args) {
        Set<String> uniques = new LinkedHashSet<>(List.of("Java", "Maths", "Java", "Anglais"));
        System.out.println(uniques);
    }
}
