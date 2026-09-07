public class Jour28Exercice02 {
    static int doubleValeur(int n) { return n * 2; }
    static void verifier(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
    public static void main(String[] args) {
        verifier(doubleValeur(0) == 0, "zéro");
        verifier(doubleValeur(3) == 6, "positif");
        verifier(doubleValeur(-2) == -4, "négatif");
        System.out.println("3 vérifications réussies");
    }
}
