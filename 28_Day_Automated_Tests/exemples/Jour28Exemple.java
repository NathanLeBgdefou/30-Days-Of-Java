public class Jour28Exemple {
    static void verifier(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
    static boolean estValide(int note) {
        return note >= 0 && note <= 20;
    }
    public static void main(String[] args) {
        verifier(!estValide(-1), "-1 doit être refusé");
        verifier(estValide(0), "0 doit être accepté");
        verifier(estValide(20), "20 doit être accepté");
        verifier(!estValide(21), "21 doit être refusé");
        System.out.println("4 vérifications réussies");
    }
}
