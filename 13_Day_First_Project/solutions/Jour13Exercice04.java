public class Jour13Exercice04 {
    static double moyennePonderee(int[] notes, int[] coefficients) {
        double sommePonderee = 0;
        int totalCoefficients = 0;
        for (int i = 0; i < notes.length; i++) {
            sommePonderee += (double) notes[i] * coefficients[i];
            totalCoefficients += coefficients[i];
        }
        return sommePonderee / totalCoefficients;
    }
    public static void main(String[] args) {
        System.out.println(moyennePonderee(new int[] {10, 16}, new int[] {1, 2}));
    }
}
