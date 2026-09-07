import java.util.Arrays;
public class Jour11Exemple {
    static void modifierNombre(int n) {
        n = 99;
    }
    static void modifierCase(int[] nombres) {
        nombres[0] = 99;
    }
    static void remplacerTableau(int[] nombres) {
        nombres = new int[] {7, 8};
    }
    public static void main(String[] args) {
        int age = 19;
        int[] notes = {12, 15};
        modifierNombre(age);
        modifierCase(notes);
        remplacerTableau(notes);
        System.out.println(age);
        System.out.println(Arrays.toString(notes));
    }
}
