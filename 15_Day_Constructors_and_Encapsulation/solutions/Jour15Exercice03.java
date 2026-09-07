public class Jour15Exercice03 {
    public static void main(String[] args) {
        NoteJ15E03 note = new NoteJ15E03();
        System.out.println(note.modifier(21));
        System.out.println(note.getValeur());
        System.out.println(note.modifier(15));
        System.out.println(note.getValeur());
    }
}
class NoteJ15E03 {
    private int valeur = 10;
    int getValeur() { return valeur; }
    boolean modifier(int nouvelle) {
        if (nouvelle < 0 || nouvelle > 20) return false;
        valeur = nouvelle;
        return true;
    }
}
