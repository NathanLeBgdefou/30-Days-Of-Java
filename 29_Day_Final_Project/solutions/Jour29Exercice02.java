public class Jour29Exercice02 {
    static String nettoyerTitre(String titre) {
        if (titre == null || titre.isBlank()) throw new IllegalArgumentException("Titre requis");
        return titre.strip();
    }
    public static void main(String[] args) {
        System.out.println(nettoyerTitre(" Réviser Java "));
    }
}
