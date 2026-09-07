public class Jour17Exercice03 {
    public static void main(String[] args) {
        System.out.println(new CoursJ17E03("Java", 60).description());
    }
}
class DocumentJ17E03 {
    private final String titre;
    DocumentJ17E03(String titre) { this.titre = titre; }
    String description() { return titre; }
}
class CoursJ17E03 extends DocumentJ17E03 {
    private final int duree;
    CoursJ17E03(String titre, int duree) {
        super(titre);
        this.duree = duree;
    }
    @Override
    String description() { return super.description() + " — " + duree + " min"; }
}
