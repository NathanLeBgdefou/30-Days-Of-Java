public class Jour14Exercice03 {
    public static void main(String[] args) {
        CompteurJ14E03 a = new CompteurJ14E03();
        CompteurJ14E03 b = new CompteurJ14E03();
        a.incrementer();
        a.incrementer();
        b.incrementer();
        System.out.println(a.valeur);
        System.out.println(b.valeur);
    }
}
class CompteurJ14E03 {
    int valeur;
    void incrementer() {
        valeur++;
    }
}
