public class Jour11Exercice02 {
    static int incrementer(int n) {
        return n + 1;
    }
    public static void main(String[] args) {
        int compteur = 5;
        compteur = incrementer(compteur);
        System.out.println(compteur);
    }
}
