import java.util.ArrayList;
import java.util.List;
public class Jour20Exercice04 {
    public static void main(String[] args) {
        List<Integer> notes = new ArrayList<>(List.of(8, 12, 9, 15));
        for (int i = notes.size() - 1; i >= 0; i--) {
            if (notes.get(i) < 10) notes.remove(i);
        }
        System.out.println(notes);
    }
}
