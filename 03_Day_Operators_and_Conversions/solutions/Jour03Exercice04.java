public class Jour03Exercice04 {
    public static void main(String[] args) {
        int total = 3671;
        int heures = total / 3600;
        int minutes = (total % 3600) / 60;
        int secondes = total % 60;
        System.out.println(heures + " h " + minutes + " min " + secondes + " s");
    }
}
