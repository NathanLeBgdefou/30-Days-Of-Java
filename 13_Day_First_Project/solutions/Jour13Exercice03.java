public class Jour13Exercice03 {
    static boolean toutesValides(int[] notes) {
        for (int note : notes) {
            if (note < 0 || note > 20) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(toutesValides(new int[] {10, 21}));
        System.out.println(toutesValides(new int[] {}));
    }
}
