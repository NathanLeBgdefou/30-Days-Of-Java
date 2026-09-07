public class Jour15Exercice04 {
    public static void main(String[] args) {
        StockJ15E04 stock = new StockJ15E04(5);
        System.out.println(stock.vendre(2));
        System.out.println(stock.vendre(4));
        System.out.println(stock.getQuantite());
    }
}
class StockJ15E04 {
    private int quantite;
    StockJ15E04(int quantite) {
        if (quantite < 0) throw new IllegalArgumentException("StockJ15E04 négatif");
        this.quantite = quantite;
    }
    int getQuantite() { return quantite; }
    boolean vendre(int nombre) {
        if (nombre <= 0 || nombre > quantite) return false;
        quantite -= nombre;
        return true;
    }
}
