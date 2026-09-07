public class Jour19Exercice02 {
    public static void main(String[] args) {
        CoordonneesJ19E02 a = new CoordonneesJ19E02(1, 4);
        CoordonneesJ19E02 b = new CoordonneesJ19E02(1, 4);
        System.out.println(a.equals(b));
        System.out.println(a.colonne());
    }
}
record CoordonneesJ19E02(int ligne, int colonne) {}
