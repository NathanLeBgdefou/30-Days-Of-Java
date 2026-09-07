public class Jour18Exercice03 {
    public static void main(String[] args) {
        MesurableJ18E03 forme = new TriangleJ18E03(6, 4);
        System.out.println(forme.aire());
    }
}
interface MesurableJ18E03 { double aire(); }
class TriangleJ18E03 implements MesurableJ18E03 {
    private final double base;
    private final double hauteur;
    TriangleJ18E03(double base, double hauteur) {
        this.base = base;
        this.hauteur = hauteur;
    }
    @Override public double aire() { return base * hauteur / 2.0; }
}
