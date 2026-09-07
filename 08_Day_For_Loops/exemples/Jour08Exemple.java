public class Jour08Exemple {
    public static void main(String[] args) {
        int multiples = 0;
        for (int n = 1; n <= 10; n++) {
            if (n % 3 == 0) {
                multiples++;
                System.out.println(n);
            }
        }
        System.out.println("Nombre de multiples : " + multiples);
    }
}
