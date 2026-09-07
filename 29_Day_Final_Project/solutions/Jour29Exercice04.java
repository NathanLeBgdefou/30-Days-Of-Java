import java.util.List;
public class Jour29Exercice04 {
    static int indiceParId(List<TacheJ29E04> taches, int id) {
        for (int i = 0; i < taches.size(); i++) {
            if (taches.get(i).id() == id) return i;
        }
        return -1;
    }
    public static void main(String[] args) {
        List<TacheJ29E04> taches = List.of(new TacheJ29E04(3, "Java"), new TacheJ29E04(8, "Maths"));
        System.out.println(indiceParId(taches, 8));
        System.out.println(indiceParId(taches, 1));
    }
}
record TacheJ29E04(int id, String titre) {}
