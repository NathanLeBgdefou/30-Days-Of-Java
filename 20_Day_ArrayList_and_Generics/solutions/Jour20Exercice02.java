import java.util.ArrayList;
import java.util.List;
public class Jour20Exercice02 {
    public static void main(String[] args) {
        List<String> matieres = new ArrayList<>();
        matieres.add("Java");
        matieres.add("Maths");
        matieres.remove("Maths");
        System.out.println(matieres);
    }
}
