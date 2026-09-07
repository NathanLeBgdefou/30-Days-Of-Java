public class Jour25Exemple {
    static int chercher(int[] valeurs, int cible) {
        int gauche = 0;
        int droite = valeurs.length - 1;
        while (gauche <= droite) {
            int milieu = gauche + (droite - gauche) / 2;
            if (valeurs[milieu] == cible) return milieu;
            if (valeurs[milieu] < cible) gauche = milieu + 1;
            else droite = milieu - 1;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] tries = {3, 7, 10, 14, 18};
        System.out.println(chercher(tries, 14));
        System.out.println(chercher(tries, 9));
    }
}
