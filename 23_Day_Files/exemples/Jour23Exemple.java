import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
public class Jour23Exemple {
    public static void main(String[] args) throws IOException {
        Path dossier = Files.createTempDirectory("java-notes-");
        Path fichier = dossier.resolve("notes.txt");
        try {
            Files.writeString(fichier, "12\n15\n9\n", StandardCharsets.UTF_8);
            int somme = 0;
            for (String ligne : Files.readAllLines(fichier, StandardCharsets.UTF_8)) {
                somme += Integer.parseInt(ligne.strip());
            }
            System.out.println("Somme relue : " + somme);
        } finally {
            Files.deleteIfExists(fichier);
            Files.deleteIfExists(dossier);
        }
    }
}
