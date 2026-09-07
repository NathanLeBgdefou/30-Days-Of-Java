public class Jour10Exemple {
    static double moyenne(int a, int b) {
        return (a + b) / 2.0;
    }

    static boolean estValidee(double note) {
        return note >= 10;
    }

    public static void main(String[] args) {
        double resultat = moyenne(12, 15);
        System.out.println(resultat);
        System.out.println(estValidee(resultat));
    }
}
