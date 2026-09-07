public class Jour24Exercice04 {
    public static void main(String[] args) {
        System.out.println(StatistiquesJ24E04.moyenne(new int[] {10, 14}));
    }
}
class StatistiquesJ24E04 {
    public static double moyenne(int[] notes) {
        int somme = 0;
        for (int note : notes) somme += note;
        return (double) somme / notes.length;
    }
}
