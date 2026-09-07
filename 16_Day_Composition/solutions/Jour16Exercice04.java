public class Jour16Exercice04 {
    public static void main(String[] args) {
        ProduitJ16E04 produit = new ProduitJ16E04("Cahier", 250);
        LigneCommandeJ16E04 ligne = new LigneCommandeJ16E04(produit, 3);
        System.out.println(ligne.totalCentimes());
    }
}
class ProduitJ16E04 {
    private final String nom;
    private final int prixCentimes;
    ProduitJ16E04(String nom, int prixCentimes) {
        this.nom = nom;
        this.prixCentimes = prixCentimes;
    }
    int getPrixCentimes() { return prixCentimes; }
}
class LigneCommandeJ16E04 {
    private final ProduitJ16E04 produit;
    private final int quantite;
    LigneCommandeJ16E04(ProduitJ16E04 produit, int quantite) {
        this.produit = produit;
        this.quantite = quantite;
    }
    int totalCentimes() { return produit.getPrixCentimes() * quantite; }
}
