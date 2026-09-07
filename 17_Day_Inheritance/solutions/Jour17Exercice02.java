public class Jour17Exercice02 {
    public static void main(String[] args) {
        AnimalJ17E02 animal = new ChatJ17E02();
        System.out.println(animal.cri());
    }
}
class AnimalJ17E02 { String cri() { return "?"; } }
class ChatJ17E02 extends AnimalJ17E02 {
    @Override
    String cri() { return "Miaou"; }
}
