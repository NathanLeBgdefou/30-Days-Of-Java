import java.util.Scanner;
public class Jour05Exercice03 {
    public static void main(String[] args) {
        Scanner clavier = new Scanner(System.in);
        System.out.println("Âge ?");
        int age = Integer.parseInt(clavier.nextLine().strip());
        System.out.println("Ville ?");
        String ville = clavier.nextLine().strip();
        System.out.println(age + " ans, " + ville);
        clavier.close();
    }
}
