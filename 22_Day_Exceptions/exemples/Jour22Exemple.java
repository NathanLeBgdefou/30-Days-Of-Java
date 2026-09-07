import java.util.Scanner;
public class Jour22Exemple {
    static int lireNote(Scanner clavier) {
        while (clavier.hasNextLine()) {
            String ligne = clavier.nextLine().strip();
            try {
                int note = Integer.parseInt(ligne);
                if (note >= 0 && note <= 20) return note;
                System.out.println("La note doit être entre 0 et 20.");
            } catch (NumberFormatException e) {
                System.out.println("Saisis un entier, par exemple 14.");
            }
        }
        throw new IllegalStateException("Entrée terminée avant une note valide");
    }
    public static void main(String[] args) {
        try (Scanner clavier = new Scanner(System.in)) {
            System.out.println("Note ?");
            int note = lireNote(clavier);
            System.out.println("Note retenue : " + note);
        }
    }
}
