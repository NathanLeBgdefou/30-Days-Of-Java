package fr.cours.revisions;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/** Tests de contrats, sans dépendance externe et sans accès au carnet réel. */
public final class TestsProjet {
    private static int verifications;
    private static final LocalDate REF = LocalDate.of(2026, 9, 15);

    @FunctionalInterface
    private interface Action {
        void executer() throws Exception;
    }

    private static void verifier(boolean condition, String message) {
        verifications++;
        if (!condition) throw new AssertionError(message);
    }

    private static void refuse(Class<? extends Exception> type, Action action) throws Exception {
        verifications++;
        try {
            action.executer();
        } catch (Exception e) {
            if (type.isInstance(e)) return;
            throw new AssertionError("Exception inattendue : " + e, e);
        }
        throw new AssertionError("Exception attendue : " + type.getSimpleName());
    }

    private static void modele() throws Exception {
        Tache t = new Tache(1, " Réviser Java ", REF, Etat.A_FAIRE);
        verifier(t.titre().equals("Réviser Java"), "Nettoyage du titre");
        refuse(IllegalArgumentException.class, () -> new Tache(0, "Java", REF, Etat.A_FAIRE));
        refuse(IllegalArgumentException.class, () -> new Tache(1, "   ", REF, Etat.A_FAIRE));
        refuse(IllegalArgumentException.class, () -> new Tache(1, null, REF, Etat.A_FAIRE));
        refuse(IllegalArgumentException.class, () -> new Tache(1, "A\tB", REF, Etat.A_FAIRE));
        refuse(IllegalArgumentException.class, () -> new Tache(1, "A\nB", REF, Etat.A_FAIRE));
        refuse(IllegalArgumentException.class, () -> new Tache(1, "A\rB", REF, Etat.A_FAIRE));
        refuse(IllegalArgumentException.class, () -> new Tache(1, "x".repeat(121), REF, Etat.A_FAIRE));
        refuse(IllegalArgumentException.class, () -> new Tache(1, "Java", null, Etat.A_FAIRE));
        refuse(IllegalArgumentException.class, () -> new Tache(1, "Java", REF, null));
        Tache finie = t.terminer();
        verifier(t.etat() == Etat.A_FAIRE, "L'original reste immuable");
        verifier(finie.etat() == Etat.TERMINEE, "Nouvel état");
        verifier(finie.id() == t.id() && finie.titre().equals(t.titre()) && finie.echeance().equals(t.echeance()), "Identité métier conservée");
        verifier(!t.estEnRetard(REF), "Échéance du jour incluse sans retard");
        verifier(t.estEnRetard(REF.plusDays(1)), "Échéance dépassée");
        verifier(!finie.estEnRetard(REF.plusDays(1)), "Tâche terminée exclue");
    }

    private static void collection() throws Exception {
        List<Tache> origine = new ArrayList<>();
        origine.add(new Tache(3, "Java", REF, Etat.A_FAIRE));
        Carnet carnet = new Carnet(origine);
        origine.clear();
        verifier(carnet.lister().size() == 1, "Copie de la liste initiale");
        Tache nouvelle = carnet.ajouter("Maths", REF.plusDays(1));
        verifier(nouvelle.id() == 4, "Identifiant à partir du maximum chargé");
        refuse(UnsupportedOperationException.class, () -> carnet.lister().clear());
        refuse(IllegalArgumentException.class, () -> carnet.ajouter(" ", REF));
        verifier(carnet.lister().size() == 2, "Ajout invalide sans mutation");
        refuse(IllegalArgumentException.class, () -> carnet.terminer(99));
        verifier(carnet.lister().get(0).etat() == Etat.A_FAIRE, "Identifiant inconnu sans mutation");
        verifier(carnet.terminer(3), "Première terminaison");
        verifier(!carnet.terminer(3), "Terminaison répétée");
        refuse(IllegalArgumentException.class, () -> new Carnet(List.of(nouvelle, nouvelle)));
        Carnet plein = new Carnet(List.of(new Tache(Integer.MAX_VALUE, "Limite", REF, Etat.A_FAIRE)));
        refuse(IllegalArgumentException.class, () -> plein.ajouter("Suivante", REF));
        Carnet retards = new Carnet(List.of(
            new Tache(3, "B", REF.minusDays(1), Etat.A_FAIRE),
            new Tache(2, "A", REF.minusDays(1), Etat.A_FAIRE),
            new Tache(1, "Aujourd'hui", REF, Etat.A_FAIRE),
            new Tache(4, "Finie", REF.minusDays(2), Etat.TERMINEE),
            new Tache(5, "Demain", REF.plusDays(1), Etat.A_FAIRE)
        ));
        verifier(retards.enRetard(REF).stream().map(Tache::id).toList().equals(List.of(2, 3)), "Retards : filtre et tri");
        verifier(new Carnet(List.of()).enRetard(REF).isEmpty(), "Carnet vide");
    }

    private static void fichiers(Path dossier) throws Exception {
        Path fichier = dossier.resolve("data/taches.tsv");
        Stockage stockage = new Stockage(fichier);
        verifier(stockage.charger().isEmpty(), "Premier lancement");
        List<Tache> attendues = List.of(new Tache(8, "Réviser les méthodes ☕", REF, Etat.TERMINEE));
        stockage.sauvegarder(attendues);
        verifier(stockage.charger().equals(attendues), "Aller-retour UTF-8 et données");
        String intact = Files.readString(fichier);
        refuse(IllegalArgumentException.class, () -> stockage.sauvegarder(List.of(attendues.get(0), attendues.get(0))));
        verifier(Files.readString(fichier).equals(intact), "Validation avant remplacement");
        stockage.sauvegarder(List.of());
        verifier(stockage.charger().isEmpty(), "Carnet vide enregistré avec en-tête");
        String entete = "# carnet-revisions-v1\n";
        List<String> invalides = List.of(
            "", "format inconnu\n", entete + "1\tA_FAIRE\t2026-09-15\n",
            entete + "1\tINCONNU\t2026-09-15\tJava\n",
            entete + "1\tA_FAIRE\t2026-02-30\tJava\n",
            entete + "0\tA_FAIRE\t2026-09-15\tJava\n",
            entete + "1\tA_FAIRE\t2026-09-15\t   \n",
            entete + "1\tA_FAIRE\t2026-09-15\tJava\n1\tTERMINEE\t2026-09-15\tMaths\n"
        );
        for (String invalide : invalides) {
            Files.writeString(fichier, invalide, StandardCharsets.UTF_8);
            refuse(IOException.class, stockage::charger);
            verifier(Files.readString(fichier).equals(invalide), "Chargement invalide sans écrasement");
        }
        Path obstacle = dossier.resolve("parent-fichier");
        Files.writeString(obstacle, "à conserver");
        Stockage impossible = new Stockage(obstacle.resolve("taches.tsv"));
        refuse(IOException.class, () -> impossible.sauvegarder(attendues));
        verifier(Files.readString(obstacle).equals("à conserver"), "Échec d'écriture sans remplacement du parent");
    }

    public static void main(String[] args) throws Exception {
        Path dossier = Files.createTempDirectory("tests-carnet-");
        try {
            modele();
            collection();
            fichiers(dossier);
            System.out.println(verifications + " vérifications du projet réussies.");
        } finally {
            try (Stream<Path> chemins = Files.walk(dossier)) {
                for (Path chemin : chemins.sorted(Comparator.reverseOrder()).toList()) Files.deleteIfExists(chemin);
            }
        }
    }
}
