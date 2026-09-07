package fr.cours.revisions;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** Format v1 : en-tête, puis id, état, échéance ISO et titre séparés par tabulations. */
public final class Stockage {
    private static final String ENTETE = "# carnet-revisions-v1";
    private final Path fichier;

    public Stockage(Path fichier) {
        this.fichier = fichier.toAbsolutePath().normalize();
    }

    public Path chemin() {
        return fichier;
    }

    public List<Tache> charger() throws IOException {
        List<String> lignes;
        try {
            lignes = Files.readAllLines(fichier, StandardCharsets.UTF_8);
        } catch (NoSuchFileException e) {
            return List.of(); // Premier lancement seulement : les autres erreurs remontent.
        }
        if (lignes.isEmpty() || !ENTETE.equals(lignes.get(0))) {
            throw new IOException("Format du carnet inconnu (en-tête absent ou invalide).");
        }
        List<Tache> resultat = new ArrayList<>();
        Set<Integer> ids = new HashSet<>();
        for (int i = 1; i < lignes.size(); i++) {
            String[] champs = lignes.get(i).split("\t", -1);
            if (champs.length != 4) throw new IOException("Ligne " + (i + 1) + " : quatre champs attendus.");
            try {
                int id = Integer.parseInt(champs[0]);
                Etat etat = Etat.valueOf(champs[1]);
                LocalDate echeance = LocalDate.parse(champs[2]);
                Tache tache = new Tache(id, champs[3], echeance, etat);
                if (!ids.add(id)) throw new IllegalArgumentException("Identifiant dupliqué : " + id);
                resultat.add(tache);
            } catch (IllegalArgumentException | DateTimeParseException e) {
                throw new IOException("Ligne " + (i + 1) + " : " + e.getMessage(), e);
            }
        }
        return List.copyOf(resultat);
    }

    public void sauvegarder(List<Tache> taches) throws IOException {
        // Valider tout avant de toucher au fichier existant.
        List<Tache> valides = new Carnet(taches).lister();
        StringBuilder texte = new StringBuilder(ENTETE).append('\n');
        for (Tache tache : valides) {
            texte.append(tache.id()).append('\t')
                .append(tache.etat().name()).append('\t')
                .append(tache.echeance()).append('\t')
                .append(tache.titre()).append('\n');
        }
        Path parent = fichier.getParent();
        Files.createDirectories(parent);
        Path temporaire = Files.createTempFile(parent, "carnet-", ".tmp");
        try {
            Files.writeString(temporaire, texte, StandardCharsets.UTF_8);
            try {
                Files.move(temporaire, fichier, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
            } catch (AtomicMoveNotSupportedException e) {
                Files.move(temporaire, fichier, StandardCopyOption.REPLACE_EXISTING);
            }
        } finally {
            Files.deleteIfExists(temporaire);
        }
    }
}
