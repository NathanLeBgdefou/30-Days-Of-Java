import java.util.Arrays;
public class Jour25Exercice03 {
    public static void main(String[] args) {
        int[] origine = {9, 2, 7};
        int[] copie = Arrays.copyOf(origine, origine.length);
        Arrays.sort(copie);
        System.out.println(Arrays.toString(origine));
        System.out.println(Arrays.toString(copie));
    }
}
