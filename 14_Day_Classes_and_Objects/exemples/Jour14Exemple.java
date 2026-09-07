public class Jour14Exemple {
    public static void main(String[] args) {
        EtudiantJ14Exemple alice = new EtudiantJ14Exemple();
        alice.prenom = "Alice";
        alice.credits = 30;
        EtudiantJ14Exemple bob = new EtudiantJ14Exemple();
        bob.prenom = "Bob";
        bob.credits = 12;
        alice.ajouterCredits(6);
        System.out.println(alice.presentation());
        System.out.println(bob.presentation());
    }
}
class EtudiantJ14Exemple {
    String prenom;
    int credits;
    void ajouterCredits(int nombre) {
        credits += nombre;
    }
    String presentation() {
        return prenom + " : " + credits + " crédits";
    }
}
