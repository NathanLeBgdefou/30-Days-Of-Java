import java.util.Arrays;
public class Jour16Exercice03 {
    public static void main(String[] args) {
        int[] origine = {12, 15};
        CarnetJ16E03 carnet = new CarnetJ16E03(origine);
        origine[0] = 0;
        System.out.println(carnet.premiereNote());
    }
}
class CarnetJ16E03 {
    private final int[] notes;
    CarnetJ16E03(int[] notes) { this.notes = Arrays.copyOf(notes, notes.length); }
    int premiereNote() { return notes[0]; } // Contrat : au moins une note.
}
