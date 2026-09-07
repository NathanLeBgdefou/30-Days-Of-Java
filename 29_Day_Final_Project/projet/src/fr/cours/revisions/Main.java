package fr.cours.revisions;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/** Point d'entrée du carnet de révisions. */
public final class Main {
    private Main() {}

    private static String lire(Scanner clavier, String question) {
        System.out.println(question);
        if (!clavier.hasNextLine()) throw new NoSuchElementException("Fin de l'entrée");
        return clavier.nextLine().strip();
    }

    private static void afficher(List<Tache> taches) {
        if (taches.isEmpty()) {
            System.out.println("Aucune tâche.");
            return;
        }
        for (Tache tache : taches) {
            String etat = tache.etat() == Etat.TERMINEE ? "terminée" : "à faire";
            System.out.println(tache.id() + " | " + etat + " | " + tache.echeance() + " | " + tache.titre());
        }
    }

    public static void main(String[] args) {
        Path chemin = args.length == 0 ? Path.of("data", "taches.tsv") : Path.of(args[0]);
        Stockage stockage = new Stockage(chemin);
        Carnet carnet;
        try {
            carnet = new Carnet(stockage.charger());
        } catch (IOException e) {
            System.err.println("Ouverture impossible : " + e.getMessage());
            System.err.println("Le fichier existant est conservé. Vérifie-le avant de relancer.");
            System.exit(1);
            return;
        }
        System.out.println("CARNET DE RÉVISIONS");
        System.out.println("Fichier : " + stockage.chemin());
        try (Scanner clavier = new Scanner(System.in)) {
            while (true) {
                try {
                    String choix = lire(clavier, "1 Ajouter | 2 Afficher | 3 Terminer | 4 En retard | 0 Quitter");
                    switch (choix) {
                        case "1" -> {
                            String titre = lire(clavier, "Titre (1 à 120 caractères, sans tabulation) ?");
                            LocalDate date = LocalDate.parse(lire(clavier, "Échéance (AAAA-MM-JJ) ?"));
                            Carnet candidat = new Carnet(carnet.lister());
                            Tache nouvelle = candidat.ajouter(titre, date);
                            stockage.sauvegarder(candidat.lister());
                            carnet = candidat;
                            System.out.println("Tâche ajoutée et enregistrée (#" + nouvelle.id() + ").");
                        }
                        case "2" -> afficher(carnet.lister());
                        case "3" -> {
                            int id = Integer.parseInt(lire(clavier, "Identifiant à terminer ?"));
                            Carnet candidat = new Carnet(carnet.lister());
                            if (candidat.terminer(id)) {
                                stockage.sauvegarder(candidat.lister());
                                carnet = candidat;
                                System.out.println("Tâche terminée et enregistrée.");
                            } else {
                                System.out.println("Cette tâche était déjà terminée.");
                            }
                        }
                        case "4" -> afficher(carnet.enRetard(LocalDate.now()));
                        case "0" -> {
                            System.out.println("À bientôt !");
                            return;
                        }
                        default -> System.out.println("Choisis 0, 1, 2, 3 ou 4.");
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Date invalide : utilise une date réelle au format AAAA-MM-JJ.");
                } catch (NumberFormatException e) {
                    System.out.println("L'identifiant doit être un entier.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Saisie refusée : " + e.getMessage());
                } catch (IOException e) {
                    System.out.println("Enregistrement impossible : " + e.getMessage());
                    System.out.println("La modification n'a pas été retenue dans le carnet en mémoire.");
                } catch (NoSuchElementException e) {
                    System.out.println("Entrée terminée. À bientôt !");
                    return;
                }
            }
        }
    }
}
