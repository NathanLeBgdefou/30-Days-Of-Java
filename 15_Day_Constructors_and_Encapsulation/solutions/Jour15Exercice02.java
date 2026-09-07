public class Jour15Exercice02 {
    public static void main(String[] args) {
        PersonneJ15E02 personne = new PersonneJ15E02("Nathan");
        System.out.println(personne.getPrenom());
    }
}
class PersonneJ15E02 {
    private String prenom;
    PersonneJ15E02(String prenom) {
        this.prenom = prenom;
    }
    String getPrenom() {
        return prenom;
    }
}
