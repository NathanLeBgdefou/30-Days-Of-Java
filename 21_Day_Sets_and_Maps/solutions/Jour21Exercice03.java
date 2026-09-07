import java.util.HashMap;
import java.util.Map;
public class Jour21Exercice03 {
    public static void main(String[] args) {
        Map<String, Integer> heures = new HashMap<>();
        heures.put("Java", 3);
        System.out.println(heures.getOrDefault("Maths", 0));
        System.out.println(heures.size());
    }
}
