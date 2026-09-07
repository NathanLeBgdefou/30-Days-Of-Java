public class Jour18Exemple {
    static void afficherAire(MesurableJ18Exemple forme) {
        System.out.println(forme.aire());
    }
    public static void main(String[] args) {
        afficherAire(new RectangleJ18Exemple(3, 4));
        afficherAire(new CarreJ18Exemple(5));
    }
}
interface MesurableJ18Exemple {
    double aire();
}
class RectangleJ18Exemple implements MesurableJ18Exemple {
    private final double largeur;
    private final double hauteur;
    RectangleJ18Exemple(double largeur, double hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
    }
    @Override public double aire() { return largeur * hauteur; }
}
class CarreJ18Exemple implements MesurableJ18Exemple {
    private final double cote;
    CarreJ18Exemple(double cote) { this.cote = cote; }
    @Override public double aire() { return cote * cote; }
}
