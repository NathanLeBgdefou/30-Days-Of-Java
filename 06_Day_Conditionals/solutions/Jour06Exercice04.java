public class Jour06Exercice04 {
    public static void main(String[] args) {
        double moyenne = 12.5;
        int absences = 4;
        boolean validee = moyenne >= 10 && absences <= 3;
        if (validee) {
            System.out.println("Validée");
        } else {
            System.out.println("Non validée");
        }
    }
}
