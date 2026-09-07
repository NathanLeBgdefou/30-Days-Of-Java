import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
public class Jour23Exercice04 {
    public static void main(String[] args) throws IOException {
        Path fichier = Files.createTempFile("java-bilan-", ".txt");
        try {
            Files.writeString(fichier, "10\n15\n17\n", StandardCharsets.UTF_8);
            List<String> lignes = Files.readAllLines(fichier, StandardCharsets.UTF_8);
            if (lignes.isEmpty()) {
                System.out.println("Aucune note");
            } else {
                int somme = 0;
                for (String ligne : lignes) somme += Integer.parseInt(ligne.strip());
                System.out.println((double) somme / lignes.size());
            }
        } finally {
            Files.deleteIfExists(fichier);
        }
    }
}
