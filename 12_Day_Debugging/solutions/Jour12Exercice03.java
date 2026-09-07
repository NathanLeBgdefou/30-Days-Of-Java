public class Jour12Exercice03 {
    static boolean valide(int note) {
        return note >= 10;
    }
    public static void main(String[] args) {
        System.out.println(valide(9));
        System.out.println(valide(10));
        System.out.println(valide(11));
    }
}
