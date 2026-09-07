import java.time.LocalDate;
public class Jour27Exercice04 {
    static boolean estProche(LocalDate echeance, LocalDate reference) {
        return !echeance.isBefore(reference) && !echeance.isAfter(reference.plusDays(7));
    }
    public static void main(String[] args) {
        LocalDate reference = LocalDate.of(2026, 9, 15);
        System.out.println(estProche(reference.minusDays(1), reference));
        System.out.println(estProche(reference, reference));
        System.out.println(estProche(reference.plusDays(7), reference));
    }
}
