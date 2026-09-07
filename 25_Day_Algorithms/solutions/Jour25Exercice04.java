import java.util.Arrays;
public class Jour25Exercice04 {
    static void trier(int[] valeurs) {
        for (int i = 0; i < valeurs.length - 1; i++) {
            int indiceMin = i;
            for (int j = i + 1; j < valeurs.length; j++) {
                if (valeurs[j] < valeurs[indiceMin]) indiceMin = j;
            }
            int temporaire = valeurs[i];
            valeurs[i] = valeurs[indiceMin];
            valeurs[indiceMin] = temporaire;
        }
    }
    public static void main(String[] args) {
        int[] valeurs = {4, 1, 3, 2};
        trier(valeurs);
        System.out.println(Arrays.toString(valeurs));
    }
}
