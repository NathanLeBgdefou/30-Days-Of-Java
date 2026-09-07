public class Jour16Exercice02 {
    public static void main(String[] args) {
        SeanceJ16E02 seance = new SeanceJ16E02(new MatiereJ16E02("Java"), 60);
        System.out.println(seance.description());
    }
}
class MatiereJ16E02 {
    private final String nom;
    MatiereJ16E02(String nom) { this.nom = nom; }
    String getNom() { return nom; }
}
class SeanceJ16E02 {
    private final MatiereJ16E02 matiere;
    private final int minutes;
    SeanceJ16E02(MatiereJ16E02 matiere, int minutes) {
        this.matiere = matiere;
        this.minutes = minutes;
    }
    String description() { return matiere.getNom() + " : " + minutes + " min"; }
}
