import java.util.List;
public class Jour26Exemple {
    public static void main(String[] args) {
        List<Integer> notes = List.of(8, 14, 12, 9, 16);
        List<Integer> validees = notes.stream()
            .filter(note -> note >= 10)
            .sorted()
            .toList();
        int total = validees.stream().mapToInt(note -> note).sum();
        System.out.println(validees);
        System.out.println(total);
        System.out.println(notes);
    }
}
