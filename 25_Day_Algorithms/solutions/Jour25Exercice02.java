public class Jour25Exercice02 {
    static int indiceDe(int[] valeurs, int cible) {
        for (int i = 0; i < valeurs.length; i++) {
            if (valeurs[i] == cible) return i;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] valeurs = {4, 7, 4};
        System.out.println(indiceDe(valeurs, 4));
        System.out.println(indiceDe(valeurs, 9));
    }
}
