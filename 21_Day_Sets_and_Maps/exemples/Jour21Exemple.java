import java.util.LinkedHashMap;
import java.util.Map;
public class Jour21Exemple {
    public static void main(String[] args) {
        String[] mots = {"java", "python", "java", "algo", "java"};
        Map<String, Integer> frequences = new LinkedHashMap<>();
        for (String mot : mots) {
            int ancien = frequences.getOrDefault(mot, 0);
            frequences.put(mot, ancien + 1);
        }
        for (Map.Entry<String, Integer> entree : frequences.entrySet()) {
            System.out.println(entree.getKey() + " : " + entree.getValue());
        }
    }
}
