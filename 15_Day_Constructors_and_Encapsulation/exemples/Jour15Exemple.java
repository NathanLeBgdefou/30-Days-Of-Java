public class Jour15Exemple {
    public static void main(String[] args) {
        CompteCreditsJ15Exemple compte = new CompteCreditsJ15Exemple(30);
        System.out.println(compte.retirer(6));
        System.out.println(compte.getCredits());
        System.out.println(compte.retirer(50));
        System.out.println(compte.getCredits());
    }
}
class CompteCreditsJ15Exemple {
    private int credits;
    CompteCreditsJ15Exemple(int credits) {
        if (credits < 0) throw new IllegalArgumentException("Crédits négatifs");
        this.credits = credits;
    }
    int getCredits() {
        return credits;
    }
    boolean retirer(int montant) {
        if (montant <= 0 || montant > credits) return false;
        credits -= montant;
        return true;
    }
}
