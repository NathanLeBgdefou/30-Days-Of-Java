public class Jour06Exemple {
    public static void main(String[] args) {
        double note = 14;
        if (note < 0 || note > 20) {
            System.out.println("Note invalide");
        } else if (note >= 16) {
            System.out.println("Très bien");
        } else if (note >= 14) {
            System.out.println("Bien");
        } else if (note >= 10) {
            System.out.println("Validé");
        } else {
            System.out.println("À retravailler");
        }
    }
}
