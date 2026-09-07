import java.util.Scanner;

public class Jour05Exemple {
    public static void main(String[] args) {
        Scanner clavier = new Scanner(System.in);
        System.out.println("Ton prénom ?");
        String prenom = clavier.nextLine().strip();
        System.out.println("Combien de minutes aujourd'hui ?");
        int minutes = Integer.parseInt(clavier.nextLine().strip());
        System.out.println("Bonjour " + prenom + ".");
        System.out.println("Sur 5 jours : " + (minutes * 5) + " minutes.");
        clavier.close();
    }
}
