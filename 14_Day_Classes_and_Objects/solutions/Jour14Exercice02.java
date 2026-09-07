public class Jour14Exercice02 {
    public static void main(String[] args) {
        LivreJ14E02 livre = new LivreJ14E02();
        livre.titre = "Java";
        livre.pages = 240;
        System.out.println(livre.description());
    }
}
class LivreJ14E02 {
    String titre;
    int pages;
    String description() {
        return titre + " : " + pages + " pages";
    }
}
