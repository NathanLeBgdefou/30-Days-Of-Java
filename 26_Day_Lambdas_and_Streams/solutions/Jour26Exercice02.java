import java.util.List;
public class Jour26Exercice02 {
    public static void main(String[] args) {
        List<Integer> pairs = List.of(1, 2, 3, 4, 5, 6).stream()
            .filter(n -> n % 2 == 0)
            .toList();
        System.out.println(pairs);
    }
}
