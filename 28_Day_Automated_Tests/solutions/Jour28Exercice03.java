public class Jour28Exercice03 {
    static void exigerPositif(int n) {
        if (n <= 0) throw new IllegalArgumentException("Positif attendu");
    }
    public static void main(String[] args) {
        try {
            exigerPositif(0);
            throw new AssertionError("Zéro accepté par erreur");
        } catch (IllegalArgumentException attendu) {
            // Le refus précis demandé a bien eu lieu.
        }
        exigerPositif(1);
        System.out.println("Refus et acceptation vérifiés");
    }
}
