<div align="center">
<h1>☕ 30 Days Of Java : jour 23</h1>
<h3>Lire et écrire des fichiers texte</h3>
<p>Français · Java 21 · Windows &amp; VS Code</p>
</div>

[← Jour 22](../22_Day_Exceptions/22_exceptions.md) | [📚 Sommaire](../README.md) | [Jour 24 →](../24_Day_Packages_and_Projects/24_packages_and_projects.md)

![30 Days Of Java — apprendre à programmer](../images/30DaysOfJava_banner.svg)

![Progression : jour 23 sur 30](../images/progression-23.svg)

**Durée conseillée : 60 à 90 min.** Garde au moins une heure ; prends plus de temps pour coder si nécessaire.

📍 [Objectifs](#objectifs) · [Cours](#cours) · [Exemple](#exemple) · [Exercices](#exercices) · [Bilan](#bilan)

<a id="objectifs"></a>
## 🎯 Objectifs du jour

- Représenter un chemin avec Path.
- Lire et écrire en UTF-8 avec Files.
- Traiter l’absence d’un fichier sans confondre les autres erreurs.

> **Ton rythme :** 10 min de rappel et lecture, 15–20 min sur l’exemple, 30–45 min de pratique, 5 min de bilan. Les jours de projet demandent davantage.

<a id="cours"></a>
# 📘 Cours

## Garder les données après la fermeture

Une variable disparaît avec le processus. Un fichier permet de conserver des données entre deux exécutions. La bibliothèque `java.nio.file` fournit Path, qui représente un chemin, et Files, qui effectue les opérations.

`Path.of("data", "notes.txt")` assemble un chemin sans coder soi-même les séparateurs Windows. Il est **relatif au dossier de travail du processus**, pas forcément au dossier du fichier .java. Dans VS Code, ce dossier dépend de la manière dont tu lances le programme. `chemin.toAbsolutePath()` permet de voir où il cherche réellement.

## Lire et écrire du texte

`Files.writeString(chemin, texte, StandardCharsets.UTF_8)` écrit un texte. Avec les options par défaut, un fichier existant est **remplacé**. `Files.readString` relit le texte entier. Pour traiter les lignes d'un petit fichier, `Files.readAllLines` renvoie une List<String>.

L'encodage UTF-8 permet de conserver les accents. Le préciser rend notre intention claire. Les options `StandardOpenOption.CREATE` et `APPEND` servent à ajouter du texte à la fin au lieu de remplacer. Ajouter plusieurs fois les mêmes données peut cependant créer des doublons : le choix dépend du format et du besoin.

| Opération | Outil |
| --- | --- |
| Créer un chemin | `Path.of(...)` |
| Créer un dossier et ses parents | `Files.createDirectories(...)` |
| Écrire un texte | `Files.writeString(...)` |
| Lire toutes les lignes | `Files.readAllLines(...)` |
| Lire un texte entier | `Files.readString(...)` |

## Traiter les échecs

Une opération peut échouer si le fichier est absent, si le chemin vise un dossier, si les droits manquent ou si le stockage est indisponible. IOException représente cette famille d'échecs. NoSuchFileException permet de traiter spécifiquement l'absence.

Pour une première utilisation, l'absence peut signifier « aucune donnée enregistrée ». Une erreur d'accès ne doit pas être silencieusement interprétée de la même façon. Sinon, un programme pourrait croire à un carnet vide et remplacer ensuite un fichier pourtant existant.

## Un exemple qui ne touche pas tes documents

Les programmes de cette journée créent un dossier temporaire et y écrivent leurs propres fichiers. Ils retirent ensuite uniquement ces fichiers de démonstration. Tu peux afficher `fichier.toAbsolutePath()` et mettre le programme en pause dans le débogueur pour inspecter ce qu'il écrit.

Pour un vrai petit carnet, on garde le fichier à un emplacement stable et on explique où il se trouve. Le projet final utilisera un dossier data, un format décrit et une écriture via un fichier temporaire voisin avant remplacement.

## Un format simple n'est pas magique

Une ligne contenant un entier peut être convertie avec parseInt, mais une ligne endommagée doit être signalée. Un texte séparé par des virgules n'est pas automatiquement un CSV complet : les virgules, guillemets et retours à la ligne dans les champs exigent des règles supplémentaires. Notre projet final utilisera un format tabulé volontairement restreint et validé.


<a id="exemple"></a>
## 🔎 Exemple complet, prêt à exécuter

[Ouvrir Jour23Exemple.java](./exemples/Jour23Exemple.java) · [Lire les exercices sans les réponses](./exercices/README.md)

Dans VS Code, ouvre le dossier `exemples` de cette journée, puis lance dans son terminal :

```powershell
java .\Jour23Exemple.java
```

```java
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
```

**Sortie du programme :**

```text
Somme relue : 36
```

### Comprendre le déroulement

resolve ajoute le nom du fichier au dossier temporaire. Le texte écrit contient trois lignes ; readAllLines ne conserve pas les séparateurs de fin de ligne dans les String obtenues. Chaque ligne est convertie puis additionnée. Ici, main déclare throws IOException afin de laisser un échec inattendu visible. Dans une application utilisateur, nous l'intercepterons pour expliquer l'action à entreprendre.


### ⚠️ Pièges fréquents

- Un chemin relatif dépend du dossier de lancement, pas automatiquement du fichier source.
- writeString remplace un fichier existant avec ses options par défaut.
- L’absence attendue d’un fichier et une erreur d’accès ne doivent pas être confondues.

<a id="exercices"></a>
## 💻 Exercices du jour

Essaie d’abord sans ouvrir les indices. Pour un exercice de code, crée ton propre fichier dans un dossier de travail et donne le même nom à la classe publique et au fichier. Les corrigés sont complets pour permettre une comparaison et une exécution indépendantes.

### Niveau 1 — comprendre et appliquer

#### Exercice 01 — Trouver le bon emplacement

Le programme lancé depuis C:\Cours utilise Path.of("data", "notes.txt"). Quel chemin absolu désigne-t-il ?


<details>
<summary>💡 Voir un indice</summary>

Le chemin relatif part du dossier de travail.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Il désigne C:\Cours\data\notes.txt. Déplacer seulement le fichier Java ne change pas forcément le dossier de lancement.


</details>

#### Exercice 02 — Conserver les accents

Écris puis relis `Révision de Java` dans un fichier temporaire en UTF-8. Affiche le texte relu.


<details>
<summary>💡 Voir un indice</summary>

Utilise le même encodage à l’écriture et à la lecture.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Les accents font partie du contenu attendu et doivent survivre à l’aller-retour.


```java
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
public class Jour23Exercice02 {
    public static void main(String[] args) throws IOException {
        Path fichier = Files.createTempFile("java-texte-", ".txt");
        try {
            Files.writeString(fichier, "Révision de Java", StandardCharsets.UTF_8);
            System.out.println(Files.readString(fichier, StandardCharsets.UTF_8));
        } finally {
            Files.deleteIfExists(fichier);
        }
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour23Exercice02.java)

Résultat attendu :

```text
Révision de Java
```

</details>

### Niveau 2 — corriger et consolider

#### Exercice 03 — Traiter uniquement un fichier absent

Crée un dossier temporaire puis tente d’y lire absent.txt sans le créer. Intercepte NoSuchFileException pour afficher `Pas encore de données`. Laisse les autres IOException remonter.


<details>
<summary>💡 Voir un indice</summary>

Le catch doit viser la situation que tu sais interpréter.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Une erreur différente n’est pas déguisée en absence normale.


```java
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
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour23Exercice03.java)

Résultat attendu :

```text
Pas encore de données
```

</details>

### Niveau 3 — résoudre un petit problème

#### Exercice 04 — Un bilan relu

Dans un fichier temporaire, écris les notes 10, 15 et 17 sur des lignes séparées. Relis-les et affiche leur moyenne 14.0. Prévois le message `Aucune note` si les lignes sont absentes.


<details>
<summary>💡 Voir un indice</summary>

readAllLines renvoie une liste dont tu peux tester isEmpty et size.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le calcul ne divise jamais par zéro. Les données de cet exercice sont contrôlées ; une application complète doit aussi traiter une ligne non numérique.


```java
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
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour23Exercice04.java)

Résultat attendu :

```text
14.0
```

</details>

<a id="bilan"></a>
## ✅ Avant de passer à la suite

- [ ] Je peux représenter un chemin avec Path.
- [ ] Je peux lire et écrire en UTF-8 avec Files.
- [ ] Je peux traiter l’absence d’un fichier sans confondre les autres erreurs.

- [ ] J’ai tenté les quatre exercices avant de comparer aux corrigés.
- [ ] Je peux expliquer une erreur rencontrée et la façon dont je l’ai corrigée.

[Noter ma progression](../PROGRESSION.md) · [Consulter le glossaire](../docs/GLOSSAIRE.md)

### 📎 Pour approfondir

- [API de Files](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/nio/file/Files.html)

---

[← Jour 22](../22_Day_Exceptions/22_exceptions.md) | [📚 Sommaire](../README.md) | [Jour 24 →](../24_Day_Packages_and_Projects/24_packages_and_projects.md)

**☕ Une étape comprise vaut mieux qu’une journée cochée trop vite.**
