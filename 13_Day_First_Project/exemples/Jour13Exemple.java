public class Jour13Exemple {
    static double moyenne(int[] notes) {
        int somme = 0;
        for (int note : notes) somme += note;
        return (double) somme / notes.length;
    }
    static int maximum(int[] notes) {
        int max = notes[0];
        for (int note : notes) {
            if (note > max) max = note;
        }
        return max;
    }
    static int compterValidees(int[] notes) {
        int total = 0;
        for (int note : notes) {
            if (note >= 10) total++;
        }
        return total;
    }
    static void afficherBilan(int[] notes) {
        if (notes.length == 0) {
            System.out.println("Aucune note");
            return;
        }
        System.out.println("Nombre : " + notes.length);
        System.out.println("Moyenne : " + moyenne(notes));
        System.out.println("Maximum : " + maximum(notes));
        System.out.println("Validées : " + compterValidees(notes));
    }
    public static void main(String[] args) {
        afficherBilan(new int[] {12, 8, 15, 10});
    }
}
