import java.time.LocalDate;
import java.util.List;
public class Jour30Exemple {
    static long compterEnRetard(List<TacheJ30Exemple> taches, LocalDate reference) {
        return taches.stream()
            .filter(tache -> !tache.terminee())
            .filter(tache -> tache.echeance().isBefore(reference))
            .count();
    }
    public static void main(String[] args) {
        LocalDate reference = LocalDate.of(2026, 9, 15);
        List<TacheJ30Exemple> taches = List.of(
            new TacheJ30Exemple(LocalDate.of(2026, 9, 14), false),
            new TacheJ30Exemple(LocalDate.of(2026, 9, 15), false),
            new TacheJ30Exemple(LocalDate.of(2026, 9, 13), true)
        );
        System.out.println(compterEnRetard(taches, reference));
    }
}
record TacheJ30Exemple(LocalDate echeance, boolean terminee) {}
