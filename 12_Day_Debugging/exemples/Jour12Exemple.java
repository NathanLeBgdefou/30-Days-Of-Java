public class Jour12Exemple {
    static double moyenne(int[] notes) {
        int somme = 0;
        for (int note : notes) {
            somme += note; // Place un point d'arrêt ici.
        }
        return (double) somme / notes.length; // Contrat : tableau non vide.
    }
    public static void main(String[] args) {
        double obtenu = moyenne(new int[] {10, 11});
        double attendu = 10.5;
        System.out.println("Attendu : " + attendu);
        System.out.println("Obtenu : " + obtenu);
        System.out.println("Test réussi : " + (Math.abs(obtenu - attendu) < 0.000001));
    }
}
