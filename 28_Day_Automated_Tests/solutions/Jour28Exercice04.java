import java.time.LocalDate;
public class Jour28Exercice04 {
    static boolean enRetard(LocalDate date, boolean terminee, LocalDate reference) {
        return !terminee && date.isBefore(reference);
    }
    static void verifier(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
    public static void main(String[] args) {
        LocalDate ref = LocalDate.of(2026, 9, 15);
        verifier(enRetard(ref.minusDays(1), false, ref), "veille à faire");
        verifier(!enRetard(ref, false, ref), "aujourd'hui");
        verifier(!enRetard(ref.minusDays(1), true, ref), "déjà terminée");
        System.out.println("Retard : 3 vérifications réussies");
    }
}
