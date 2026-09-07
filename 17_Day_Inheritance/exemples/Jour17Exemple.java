public class Jour17Exemple {
    public static void main(String[] args) {
        PersonneJ17Exemple personne = new EtudiantJ17Exemple("Nathan", "Informatique");
        System.out.println(personne.presentation());
    }
}
class PersonneJ17Exemple {
    private final String prenom;
    PersonneJ17Exemple(String prenom) { this.prenom = prenom; }
    String presentation() { return prenom; }
}
class EtudiantJ17Exemple extends PersonneJ17Exemple {
    private final String filiere;
    EtudiantJ17Exemple(String prenom, String filiere) {
        super(prenom);
        this.filiere = filiere;
    }
    @Override
    String presentation() {
        return super.presentation() + " étudie " + filiere;
    }
}
