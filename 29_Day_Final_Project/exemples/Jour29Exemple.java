import java.time.LocalDate;
public class Jour29Exemple {
    public static void main(String[] args) {
        TacheJ29Exemple tache = new TacheJ29Exemple(1, "Réviser les tableaux", LocalDate.of(2026, 9, 20), false);
        TacheJ29Exemple terminee = tache.terminer();
        System.out.println(tache.titre());
        System.out.println(tache.terminee());
        System.out.println(terminee.terminee());
    }
}
record TacheJ29Exemple(int id, String titre, LocalDate echeance, boolean terminee) {
    TacheJ29Exemple terminer() { return new TacheJ29Exemple(id, titre, echeance, true); }
}
