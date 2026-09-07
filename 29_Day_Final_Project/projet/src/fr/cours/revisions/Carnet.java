package fr.cours.revisions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** Les opérations du carnet ne lisent pas le clavier et n'écrivent pas de fichier. */
public final class Carnet {
    private final List<Tache> taches;

    public Carnet(List<Tache> initiales) {
        if (initiales == null) throw new IllegalArgumentException("Liste requise.");
        Set<Integer> ids = new HashSet<>();
        for (Tache tache : initiales) {
            if (tache == null) throw new IllegalArgumentException("Tâche absente.");
            if (!ids.add(tache.id())) throw new IllegalArgumentException("Identifiant dupliqué : " + tache.id());
        }
        taches = new ArrayList<>(initiales);
    }

    public List<Tache> lister() {
        return List.copyOf(taches);
    }

    public Tache ajouter(String titre, LocalDate echeance) {
        int maximum = 0;
        for (Tache tache : taches) maximum = Math.max(maximum, tache.id());
        if (maximum == Integer.MAX_VALUE) throw new IllegalArgumentException("Plus d'identifiants disponibles.");
        Tache nouvelle = new Tache(maximum + 1, titre, echeance, Etat.A_FAIRE);
        taches.add(nouvelle);
        return nouvelle;
    }

    /** Renvoie false si la tâche était déjà terminée ; refuse un identifiant inconnu. */
    public boolean terminer(int id) {
        for (int i = 0; i < taches.size(); i++) {
            Tache tache = taches.get(i);
            if (tache.id() == id) {
                if (tache.etat() == Etat.TERMINEE) return false;
                taches.set(i, tache.terminer());
                return true;
            }
        }
        throw new IllegalArgumentException("Aucune tâche avec l'identifiant " + id + ".");
    }

    public List<Tache> enRetard(LocalDate reference) {
        return taches.stream()
            .filter(tache -> tache.estEnRetard(reference))
            .sorted(Comparator.comparing(Tache::echeance).thenComparingInt(Tache::id))
            .toList();
    }
}
