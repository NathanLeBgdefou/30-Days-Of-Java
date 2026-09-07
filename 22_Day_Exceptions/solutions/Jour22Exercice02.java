public class Jour22Exercice02 {
    public static void main(String[] args) {
        try {
            int nombre = Integer.parseInt("douze");
            System.out.println(nombre);
        } catch (NumberFormatException e) {
            System.out.println("Entier attendu");
        }
    }
}
