public class Jour10Exercice04 {
    static int max(int a, int b) {
        if (a >= b) {
            return a;
        }
        return b;
    }
    public static void main(String[] args) {
        System.out.println(max(max(-4, -9), -2));
    }
}
