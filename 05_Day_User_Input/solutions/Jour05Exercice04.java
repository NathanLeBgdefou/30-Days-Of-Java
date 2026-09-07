import java.util.Scanner;
public class Jour05Exercice04 {
    public static void main(String[] args) {
        Scanner clavier = new Scanner(System.in);
        System.out.println("Note 1 (point décimal) ?");
        double a = Double.parseDouble(clavier.nextLine().strip());
        System.out.println("Note 2 (point décimal) ?");
        double b = Double.parseDouble(clavier.nextLine().strip());
        System.out.println("Moyenne : " + (a + b) / 2.0);
        clavier.close();
    }
}
