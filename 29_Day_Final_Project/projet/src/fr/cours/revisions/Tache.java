package fr.cours.revisions;

import java.time.LocalDate;

/** Une tâche de valeur, immuable, avec des données validées. */
public record Tache(int id, String titre, LocalDate echeance, Etat etat) {
    public Tache {
        if (id <= 0) throw new IllegalArgumentException("Identifiant strictement positif requis.");
        if (titre == null || titre.isBlank()) throw new IllegalArgumentException("Le titre ne peut pas être vide.");
        // Le format de stockage garde exactement une tâche par ligne.
        if (titre.contains("\t") || titre.contains("\n") || titre.contains("\r")) {
            throw new IllegalArgumentException("Le titre doit rester sur une ligne, sans tabulation.");
        }
        titre = titre.strip();
        if (titre.length() > 120) throw new IllegalArgumentException("Titre limité à 120 unités UTF-16.");
        if (echeance == null) throw new IllegalArgumentException("Échéance requise.");
        if (etat == null) throw new IllegalArgumentException("État requis.");
    }

    public Tache terminer() {
        return new Tache(id, titre, echeance, Etat.TERMINEE);
    }

    public boolean estEnRetard(LocalDate reference) {
        return etat == Etat.A_FAIRE && echeance.isBefore(reference);
    }
}
