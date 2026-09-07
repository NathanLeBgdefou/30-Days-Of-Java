public class Jour30Exercice02 {
    static int sommePositifs(int[] valeurs) {
        int somme = 0;
        for (int valeur : valeurs) {
            if (valeur > 0) somme += valeur;
        }
        return somme;
    }
    public static void main(String[] args) {
        System.out.println(sommePositifs(new int[] {-2, 0, 3, 5}));
        System.out.println(sommePositifs(new int[] {}));
    }
}
