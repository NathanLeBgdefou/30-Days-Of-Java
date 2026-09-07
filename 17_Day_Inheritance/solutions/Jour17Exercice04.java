public class Jour17Exercice04 {
    public static void main(String[] args) {
        AnimalJ17E04[] animaux = {new ChatJ17E04(), new ChienJ17E04()};
        for (AnimalJ17E04 animal : animaux) System.out.println(animal.cri());
    }
}
class AnimalJ17E04 { String cri() { return "?"; } }
class ChatJ17E04 extends AnimalJ17E04 {
    @Override String cri() { return "Miaou"; }
}
class ChienJ17E04 extends AnimalJ17E04 {
    @Override String cri() { return "Ouaf"; }
}
