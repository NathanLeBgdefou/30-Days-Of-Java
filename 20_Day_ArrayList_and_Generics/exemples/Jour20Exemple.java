import java.util.ArrayList;
import java.util.List;
public class Jour20Exemple {
    public static void main(String[] args) {
        List<String> matieres = new ArrayList<>();
        matieres.add("Java");
        matieres.add("Maths");
        matieres.add("Anglais");
        matieres.set(1, "Algorithmique");
        System.out.println(matieres.get(0));
        System.out.println(matieres.size());
        for (String matiere : matieres) {
            System.out.println(matiere);
        }
    }
}
