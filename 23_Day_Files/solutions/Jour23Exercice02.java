import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
public class Jour23Exercice02 {
    public static void main(String[] args) throws IOException {
        Path fichier = Files.createTempFile("java-texte-", ".txt");
        try {
            Files.writeString(fichier, "Révision de Java", StandardCharsets.UTF_8);
            System.out.println(Files.readString(fichier, StandardCharsets.UTF_8));
        } finally {
            Files.deleteIfExists(fichier);
        }
    }
}
