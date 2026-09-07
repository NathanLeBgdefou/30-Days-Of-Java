import java.util.Arrays;
public class Jour09Exercice03 {
    public static void main(String[] args) {
        int[] notes = {10, 12, 15};
        for (int i = 0; i < notes.length; i++) {
            notes[i] += 1;
        }
        System.out.println(Arrays.toString(notes));
    }
}
