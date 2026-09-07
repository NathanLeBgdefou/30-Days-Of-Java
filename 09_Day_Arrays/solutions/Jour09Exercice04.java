public class Jour09Exercice04 {
    public static void main(String[] args) {
        int[] valeurs = {-8, -3, -12};
        if (valeurs.length == 0) {
            System.out.println("Aucune valeur");
        } else {
            int maximum = valeurs[0];
            for (int i = 1; i < valeurs.length; i++) {
                if (valeurs[i] > maximum) {
                    maximum = valeurs[i];
                }
            }
            System.out.println(maximum);
        }
    }
}
