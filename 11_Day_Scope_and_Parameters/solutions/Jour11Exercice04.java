public class Jour11Exercice04 {
    static int somme(int n) {
        if (n == 0) {
            return 0;
        }
        return n + somme(n - 1);
    }
    public static void main(String[] args) {
        System.out.println(somme(5));
    }
}
