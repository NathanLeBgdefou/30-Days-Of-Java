# ☕ Carnet de révisions — projet final

[← Jour 29](../29_final_project.md) · [Lire le sujet avant le corrigé](./SUJET.md)

L'application fonctionne dans le terminal, avec le JDK seul. Elle permet d'ajouter, afficher et terminer des tâches, d'identifier les retards et de retrouver les données au prochain lancement.

## Lancer sous Windows avec VS Code

Ouvre **ce dossier projet** dans VS Code, puis son terminal PowerShell. Copie ces commandes une ligne à la fois :

```powershell
$sourcesJava = Get-ChildItem -Path .src -Recurse -Filter *.java | ForEach-Object { $_.FullName }
javac -encoding UTF-8 -d out $sourcesJava
java -cp out fr.cours.revisions.Main
```

Si la compilation affiche une erreur, corrige-la avant de lancer la dernière ligne. Au menu, saisis le numéro d'une action puis appuie sur Entrée. Les dates sont au format `AAAA-MM-JJ`, par exemple `2026-10-15`. Une tâche due aujourd'hui n'est pas encore comptée en retard.

Pour compiler depuis un terminal Linux ou macOS, utilise :

```bash
javac -encoding UTF-8 -d out src/fr/cours/revisions/*.java
java -cp out fr.cours.revisions.Main
```

## Tester

Depuis le même terminal PowerShell :

```powershell
$fichiersJava = Get-ChildItem -Path .src,.	ests -Recurse -Filter *.java | ForEach-Object { $_.FullName }
javac -encoding UTF-8 -d out $fichiersJava
java -cp out fr.cours.revisions.TestsProjet
```

Le programme annonce le nombre de vérifications réussies. Un échec produit une AssertionError avec une explication. Les tests créent leurs propres fichiers temporaires ; ils ne lisent ni ne modifient ton carnet réel.

## Les sources, dans l'ordre de lecture

1. [Etat.java](./src/fr/cours/revisions/Etat.java) — les deux états possibles.
2. [Tache.java](./src/fr/cours/revisions/Tache.java) — données immuables et règles de validité.
3. [Carnet.java](./src/fr/cours/revisions/Carnet.java) — opérations en mémoire et identifiants.
4. [Stockage.java](./src/fr/cours/revisions/Stockage.java) — lecture et écriture UTF-8.
5. [Main.java](./src/fr/cours/revisions/Main.java) — dialogue et sauvegarde des modifications.
6. [TestsProjet.java](./tests/fr/cours/revisions/TestsProjet.java) — contrats et cas limites.

## Où sont mes données ?

Par défaut, le fichier est `data/taches.tsv`, à partir du dossier de lancement. Son chemin complet est affiché au démarrage. Le dossier data est créé lors de la première sauvegarde. Le fichier de données est ignoré par Git pour que tes révisions personnelles ne partent pas automatiquement dans le dépôt public.

Tu peux donner un autre chemin explicitement :

```powershell
java -cp out fr.cours.revisions.Main "C:UsersTON_NOMDocuments
evisions.tsv"
```

Remplace TON_NOM par le nom de ton dossier Windows. Ferme les autres instances du carnet avant d'utiliser le même fichier : ce projet est conçu pour un seul processus à la fois.

## Format du fichier

La première ligne est `# carnet-revisions-v1`. Chaque ligne suivante contient quatre champs séparés par une vraie tabulation : identifiant, état, échéance ISO, titre. Les titres sont limités à 120 unités UTF-16 et ne peuvent contenir ni tabulation ni retour à la ligne. Les identifiants doivent être positifs et uniques.

| Champ | Exemple |
| --- | --- |
| Identifiant | `1` |
| État | `A_FAIRE` ou `TERMINEE` |
| Échéance | `2026-10-15` |
| Titre | `Réviser les tableaux` |

Un fichier absent ouvre un carnet vide. Un fichier invalide ou inaccessible arrête le chargement et reste intact. Conserve une copie avant de modifier manuellement un fichier de données. Le remplacement tente un déplacement atomique, avec une solution de repli si le système ne le propose pas ; le projet n'apporte pas les garanties transactionnelles d'une base de données.

## Scénario manuel

Ajoute une tâche, affiche-la, quitte et relance. Termine son identifiant, quitte puis relance encore. Essaie aussi un titre vide, une date impossible et un identifiant inexistant : chaque refus doit être expliqué, sans perdre les tâches valides.

Pour le jour 30, ajoute l'affichage des tâches à faire dans les sept prochains jours, bornes incluses, triées par échéance puis identifiant.
