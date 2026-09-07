import java.time.LocalDate;
public class Jour30Exercice03 {
    static boolean dansSeptJours(LocalDate date, LocalDate reference) {
        return !date.isBefore(reference) && !date.isAfter(reference.plusDays(7));
    }
    public static void main(String[] args) {
        LocalDate reference = LocalDate.of(2026, 9, 15);
        if (!dansSeptJours(reference.plusDays(7), reference)) {
            throw new AssertionError("La borne +7 doit être incluse");
        }
        System.out.println("Borne +7 vérifiée");
    }
}
