public class Jour27Exercice03 {
    public static void main(String[] args) {
        System.out.println(EtatJ27E03.valueOf("TERMINEE"));
        try {
            EtatJ27E03.valueOf("INCONNU");
        } catch (IllegalArgumentException e) {
            System.out.println("État invalide");
        }
    }
}
enum EtatJ27E03 { A_FAIRE, TERMINEE }
