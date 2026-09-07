public class Jour03Exemple {
    public static void main(String[] args) {
        int somme = 37;
        int nombre = 3;
        double moyenne = (double) somme / nombre;
        int minutes = 135;
        System.out.println("Division entière : " + somme / nombre);
        System.out.println("Moyenne : " + moyenne);
        System.out.println(minutes / 60 + " h " + minutes % 60 + " min");
    }
}
