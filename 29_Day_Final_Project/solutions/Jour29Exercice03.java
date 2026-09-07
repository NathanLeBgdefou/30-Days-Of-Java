import java.util.ArrayList;
import java.util.List;
public class Jour29Exercice03 {
    public static void main(String[] args) {
        List<TacheJ29E03> taches = new ArrayList<>(List.of(new TacheJ29E03("Java", false)));
        taches.set(0, taches.get(0).terminer());
        System.out.println(taches.get(0).terminee());
    }
}
record TacheJ29E03(String titre, boolean terminee) {
    TacheJ29E03 terminer() { return new TacheJ29E03(titre, true); }
}
