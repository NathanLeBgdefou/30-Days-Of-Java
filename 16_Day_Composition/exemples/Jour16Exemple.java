public class Jour16Exemple {
    public static void main(String[] args) {
        AdresseJ16Exemple adresse = new AdresseJ16Exemple("Rennes", "35000");
        EtudiantJ16Exemple etudiant = new EtudiantJ16Exemple("Nathan", adresse);
        System.out.println(etudiant.presentation());
    }
}
class AdresseJ16Exemple {
    private final String ville;
    private final String codePostal;
    AdresseJ16Exemple(String ville, String codePostal) {
        this.ville = ville;
        this.codePostal = codePostal;
    }
    String presentation() {
        return codePostal + " " + ville;
    }
}
class EtudiantJ16Exemple {
    private final String prenom;
    private final AdresseJ16Exemple adresse;
    EtudiantJ16Exemple(String prenom, AdresseJ16Exemple adresse) {
        this.prenom = prenom;
        this.adresse = adresse;
    }
    String presentation() {
        return prenom + " — " + adresse.presentation();
    }
}
