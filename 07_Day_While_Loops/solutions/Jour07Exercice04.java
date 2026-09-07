import java.util.Scanner;
public class Jour07Exercice04 {
    public static void main(String[] args) {
        Scanner clavier = new Scanner(System.in);
        int total = 0;
        System.out.println("Durées en minutes, puis -1 pour terminer :");
        int duree = Integer.parseInt(clavier.nextLine().strip());
        while (duree != -1) {
            if (duree >= 0) {
                total += duree;
            }
            duree = Integer.parseInt(clavier.nextLine().strip());
        }
        System.out.println("Total : " + total);
        clavier.close();
    }
}
