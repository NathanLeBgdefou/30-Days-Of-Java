public class Jour08Exercice04 {
    public static void main(String[] args) {
        int n = 29;
        boolean premier = n >= 2;
        for (int d = 2; d < n; d++) {
            if (n % d == 0) {
                premier = false;
                break;
            }
        }
        System.out.println(premier);
    }
}
