public class Jour22Exercice03 {
    static double racine(double x) {
        if (!Double.isFinite(x) || x < 0) throw new IllegalArgumentException("Valeur invalide");
        return Math.sqrt(x);
    }
    public static void main(String[] args) {
        System.out.println(racine(9));
        try {
            racine(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("Valeur invalide");
        }
    }
}
