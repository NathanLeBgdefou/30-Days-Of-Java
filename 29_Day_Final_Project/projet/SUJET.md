# 🎓 Sujet du projet : mon carnet de révisions

[← Jour 29](../29_final_project.md) · [Guide et corrigé du projet](./README.md)

## Objectif

Construire une application Java dans le terminal qui permet à un étudiant d'organiser ses révisions. Le JDK doit suffire pour la compiler et l'exécuter.

## Données d'une tâche

Une tâche possède un identifiant entier positif unique, un titre non vide limité à 120 unités UTF-16, une date d'échéance et un état A_FAIRE ou TERMINEE. Le titre reste sur une seule ligne, sans tabulation. Une tâche terminée conserve ses autres données.

## Fonctions obligatoires

- Ajouter une tâche avec un identifiant attribué automatiquement.
- Afficher les tâches avec leurs identifiants, titres, dates et états.
- Terminer une tâche choisie par son identifiant.
- Afficher les tâches non terminées dont l'échéance est strictement avant aujourd'hui.
- Sauvegarder les modifications et recharger les données au démarrage suivant.
- Expliquer les erreurs de saisie sans arrêter brutalement une session normale.
- Préserver un fichier existant si son chargement est impossible ou son contenu invalide.

## Contraintes de conception

Sépare le dialogue avec l'utilisateur, les opérations du carnet, la représentation d'une tâche et le stockage. Utilise une date fournie en paramètre pour tester la règle de retard. Ne confonds pas identifiant métier et indice dans une liste. Le programme cible un utilisateur et une instance à la fois.

## Critères de réussite

| Vérification | Résultat attendu |
| --- | --- |
| Premier démarrage sans fichier | Carnet vide utilisable. |
| Ajout d'une tâche valide | Identifiant positif, tâche visible et sauvegardée. |
| Fermeture puis relance | Les mêmes données sont retrouvées. |
| Titre vide ou date impossible | Message de refus, pas d'ajout. |
| Identifiant inconnu | Message de refus, autres tâches intactes. |
| Tâche due aujourd'hui | Pas encore en retard. |
| Tâche terminée avec ancienne échéance | Pas comptée dans les retards. |
| Fichier mal formé | Échec expliqué, fichier conservé. |

## Livrable personnel

Garde tes sources, quelques tests automatiques et un court texte expliquant comment lancer le programme. Prépare une présentation de trois minutes : responsabilités des classes, règle de retard, choix de sauvegarde et un défaut que tes tests ont permis de corriger.
