import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
public class Jour27Exemple {
    static boolean estEnRetard(LocalDate echeance, EtatJ27Exemple etat, LocalDate reference) {
        return etat == EtatJ27Exemple.A_FAIRE && echeance.isBefore(reference);
    }
    public static void main(String[] args) {
        LocalDate reference = LocalDate.of(2026, 9, 15);
        LocalDate echeance = LocalDate.parse("2026-09-12");
        System.out.println(estEnRetard(echeance, EtatJ27Exemple.A_FAIRE, reference));
        System.out.println(estEnRetard(echeance, EtatJ27Exemple.TERMINEE, reference));
        System.out.println(ChronoUnit.DAYS.between(echeance, reference));
        System.out.println(reference.plusDays(7));
    }
}
enum EtatJ27Exemple { A_FAIRE, TERMINEE }
