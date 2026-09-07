public class Jour13Exercice02 {
    static int minimum(int[] notes) {
        int min = notes[0];
        for (int note : notes) {
            if (note < min) min = note;
        }
        return min;
    }
    public static void main(String[] args) {
        System.out.println(minimum(new int[] {12, 8, 15, 10}));
    }
}
