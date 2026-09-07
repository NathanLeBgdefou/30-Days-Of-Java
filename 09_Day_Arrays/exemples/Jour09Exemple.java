import java.util.Arrays;
public class Jour09Exemple {
    public static void main(String[] args) {
        int[] notes = {12, 15, 9};
        int somme = 0;
        for (int note : notes) {
            somme += note;
        }
        System.out.println(Arrays.toString(notes));
        if (notes.length == 0) {
            System.out.println("Aucune note");
        } else {
            System.out.println("Moyenne : " + (double) somme / notes.length);
        }
    }
}
