public class Jour07Exemple {
    public static void main(String[] args) {
        int jour = 1;
        int totalMinutes = 0;
        while (jour <= 5) {
            totalMinutes = totalMinutes + 60;
            System.out.println("Jour " + jour + " : " + totalMinutes + " min cumulées");
            jour++;
        }
        System.out.println("Terminé");
    }
}
