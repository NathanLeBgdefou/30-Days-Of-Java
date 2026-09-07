public class Jour19Exercice03 {
    static boolean estRenseigne(String texte) {
        return texte != null && !texte.isBlank();
    }
    public static void main(String[] args) {
        System.out.println(estRenseigne(null));
        System.out.println(estRenseigne("   "));
        System.out.println(estRenseigne("Java"));
    }
}
