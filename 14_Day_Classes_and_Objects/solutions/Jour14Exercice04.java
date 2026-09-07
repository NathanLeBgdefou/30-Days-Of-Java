public class Jour14Exercice04 {
    public static void main(String[] args) {
        CompteurJ14E04 premier = new CompteurJ14E04();
        CompteurJ14E04 alias = premier;
        alias.incrementer();
        System.out.println(premier.valeur);
    }
}
class CompteurJ14E04 {
    int valeur;
    void incrementer() {
        valeur++;
    }
}
