import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.NoSuchFileException;
public class Jour23Exercice03 {
    public static void main(String[] args) throws IOException {
        Path dossier = Files.createTempDirectory("java-absence-");
        try {
            System.out.println(Files.readString(dossier.resolve("absent.txt")));
        } catch (NoSuchFileException e) {
            System.out.println("Pas encore de données");
        } finally {
            Files.deleteIfExists(dossier);
        }
    }
}
