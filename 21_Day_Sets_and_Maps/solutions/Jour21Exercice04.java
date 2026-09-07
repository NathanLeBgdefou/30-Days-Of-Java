import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
public class Jour21Exercice04 {
    public static void main(String[] args) {
        Set<String> vus = new HashSet<>();
        Set<String> doublons = new LinkedHashSet<>();
        for (String code : List.of("A", "B", "A", "C", "B", "B")) {
            if (!vus.add(code)) doublons.add(code);
        }
        System.out.println(doublons);
    }
}
