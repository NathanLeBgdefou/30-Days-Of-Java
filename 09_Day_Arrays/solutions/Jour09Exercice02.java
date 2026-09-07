public class Jour09Exercice02 {
    public static void main(String[] args) {
        int[] notes = {8, 10, 14, 9, 20};
        int validees = 0;
        for (int note : notes) {
            if (note >= 10) {
                validees++;
            }
        }
        System.out.println(validees);
    }
}
