import java.util.Scanner;
public class Jour22Exercice04 {
    public static void main(String[] args) {
        try (Scanner clavier = new Scanner(System.in)) {
            while (clavier.hasNextLine()) {
                try {
                    int valeur = Integer.parseInt(clavier.nextLine().strip());
                    if (valeur > 0) {
                        System.out.println("Retenu : " + valeur);
                        return;
                    }
                    System.out.println("Valeur strictement positive attendue");
                } catch (NumberFormatException e) {
                    System.out.println("Entier attendu");
                }
            }
            System.out.println("Entrée terminée");
        }
    }
}
