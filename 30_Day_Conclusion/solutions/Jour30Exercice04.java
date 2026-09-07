import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
public class Jour30Exercice04 {
    static List<TacheJ30E04> prochaines(List<TacheJ30E04> taches, LocalDate reference) {
        return taches.stream()
            .filter(t -> !t.terminee())
            .filter(t -> !t.echeance().isBefore(reference))
            .filter(t -> !t.echeance().isAfter(reference.plusDays(7)))
            .sorted(Comparator.comparing(TacheJ30E04::echeance).thenComparingInt(TacheJ30E04::id))
            .toList();
    }
    public static void main(String[] args) {
        LocalDate ref = LocalDate.of(2026, 9, 15);
        List<TacheJ30E04> taches = List.of(
            new TacheJ30E04(1, ref.minusDays(1), false),
            new TacheJ30E04(3, ref.plusDays(7), false),
            new TacheJ30E04(2, ref, false),
            new TacheJ30E04(4, ref.plusDays(8), false),
            new TacheJ30E04(5, ref, true)
        );
        List<TacheJ30E04> resultat = prochaines(taches, ref);
        if (resultat.size() != 2 || resultat.get(0).id() != 2 || resultat.get(1).id() != 3) {
            throw new AssertionError("Filtrage ou tri incorrect");
        }
        System.out.println(resultat.stream().map(TacheJ30E04::id).toList());
    }
}
record TacheJ30E04(int id, LocalDate echeance, boolean terminee) {}
