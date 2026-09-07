import java.util.ArrayList;
import java.util.List;
public class Jour20Exercice03 {
    public static void main(String[] args) {
        List<Integer> origine = List.of(12, 15);
        List<Integer> copie = new ArrayList<>(origine);
        copie.add(18);
        System.out.println(origine);
        System.out.println(copie);
    }
}
