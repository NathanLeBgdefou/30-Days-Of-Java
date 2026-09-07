public class Jour18Exercice02 {
    public static void main(String[] args) {
        NotableJ18E02 travail = new DevoirJ18E02();
        System.out.println(travail.score());
    }
}
interface NotableJ18E02 { int score(); }
class DevoirJ18E02 implements NotableJ18E02 {
    @Override public int score() { return 15; }
}
