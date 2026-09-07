import java.util.Scanner;
public class Jour05Exercice02 {
    public static void main(String[] args) {
        Scanner clavier = new Scanner(System.in);
        System.out.println("Prénom ?");
        String prenom = clavier.nextLine().strip();
        System.out.println("Salut " + prenom + " !");
        clavier.close();
    }
}
