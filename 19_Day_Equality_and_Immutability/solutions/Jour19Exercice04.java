public class Jour19Exercice04 {
    public static void main(String[] args) {
        NoteJ19E04 note = new NoteJ19E04(14);
        System.out.println(note.valeur());
    }
}
record NoteJ19E04(int valeur) {
    NoteJ19E04 {
        if (valeur < 0 || valeur > 20) {
            throw new IllegalArgumentException("NoteJ19E04 hors de [0, 20]");
        }
    }
}
