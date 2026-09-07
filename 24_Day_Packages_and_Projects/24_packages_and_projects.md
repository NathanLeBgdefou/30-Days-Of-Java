<div align="center">
<h1>☕ 30 Days Of Java : jour 24</h1>
<h3>Packages et projets à plusieurs fichiers</h3>
<p>Français · Java 21 · Windows &amp; VS Code</p>
</div>

[← Jour 23](../23_Day_Files/23_files.md) | [📚 Sommaire](../README.md) | [Jour 25 →](../25_Day_Algorithms/25_algorithms.md)

![30 Days Of Java — apprendre à programmer](../images/30DaysOfJava_banner.svg)

![Progression : jour 24 sur 30](../images/progression-24.svg)

**Durée conseillée : 60 à 90 min.** Garde au moins une heure ; prends plus de temps pour coder si nécessaire.

📍 [Objectifs](#objectifs) · [Cours](#cours) · [Exemple](#exemple) · [Exercices](#exercices) · [Bilan](#bilan)

<a id="objectifs"></a>
## 🎯 Objectifs du jour

- Répartir les classes publiques dans des fichiers.
- Relier package, dossier source et nom complet.
- Compiler un petit projet sans outil supplémentaire.

> **Ton rythme :** 10 min de rappel et lecture, 15–20 min sur l’exemple, 30–45 min de pratique, 5 min de bilan. Les jours de projet demandent davantage.

<a id="cours"></a>
# 📘 Cours

## Sortir du fichier unique

Les petits exemples regroupaient plusieurs classes dans un fichier pour simplifier le lancement. Dans un projet réel, on range généralement chaque classe publique dans son propre fichier. Une classe `public class Etudiant` vit dans Etudiant.java.

Un **package** regroupe des classes sous un nom comme `fr.cours.demo`. La déclaration `package fr.cours.demo;` se place avant les imports. Le nom complet de la classe devient `fr.cours.demo.Etudiant`, ce qui permet de distinguer des classes homonymes dans des packages différents.

Les dossiers sous la racine source suivent cette structure. Nous utilisons des noms de packages en minuscules. Un import évite de répéter un nom complet ; il ne télécharge pas une bibliothèque.

## Le projet fourni

Ouvre le [petit projet de cette journée](./projet/README.md). Il contient deux classes publiques, App et Message, dans un package `fr.cours.demo`.

| Fichier | Rôle |
| --- | --- |
| `projet/src/fr/cours/demo/App.java` | Contient main et appelle Message. |
| `projet/src/fr/cours/demo/Message.java` | Produit le texte du message. |
| `projet/out/` | Dossier créé à la compilation pour les fichiers .class. |

Dans VS Code, ouvre le dossier **projet**, puis son terminal PowerShell. Tu peux compiler et lancer ainsi :

```powershell
$sourcesJava = Get-ChildItem -Path .\src -Recurse -Filter *.java | ForEach-Object { $_.FullName }
javac -encoding UTF-8 -d out $sourcesJava
java -cp out fr.cours.demo.App
```

La première ligne récupère les chemins des fichiers Java. `-d out` choisit où placer les classes compilées. `-cp out` précise le **classpath**, c'est-à-dire un emplacement où Java cherche les classes. Le nom après le classpath est le nom complet de la classe principale, sans `.java` ni `.class`.

Tu ne dois pas ajouter `src` au nom de package : src est la racine de rangement, pas un morceau du nom logique de la classe.

## Visibilité entre fichiers

Un membre public peut être utilisé depuis un autre package si sa classe est elle-même accessible. Un membre sans modificateur de visibilité est accessible dans le même package. private reste limité à la classe selon les règles du langage. Le fait que deux fichiers se trouvent dans un même dossier Windows quelconque ne remplace pas leurs déclarations de package.

## Bibliothèque standard et dépendance externe

`java.util.List` est fourni par le JDK. Une bibliothèque tierce comme JUnit doit être ajoutée au projet. Des outils comme Maven organisent les sources, résolvent les dépendances et lancent les tests. Ils sont utiles, mais notre premier parcours reste exécutable avec le JDK seul. Tu trouveras des liens vers ces outils dans les ressources pour la suite.

Un projet Maven utilise habituellement `src/main/java`, `src/test/java` et un fichier pom.xml. Il ne faut pas déplacer au hasard les sources de ce petit projet pour imiter ce format : on choisit et configure une organisation cohérente.


<a id="exemple"></a>
## 🔎 Exemple complet, prêt à exécuter

[Ouvrir Jour24Exemple.java](./exemples/Jour24Exemple.java) · [Lire les exercices sans les réponses](./exercices/README.md)

Dans VS Code, ouvre le dossier `exemples` de cette journée, puis lance dans son terminal :

```powershell
java .\Jour24Exemple.java
```

```java
public class Jour24Exemple {
    public static void main(String[] args) {
        String nomComplet = java.time.LocalDate.class.getName();
        System.out.println(nomComplet);
        System.out.println("Projet complet : ouvrir le dossier projet de cette journée.");
    }
}
```

**Sortie du programme :**

```text
java.time.LocalDate
Projet complet : ouvrir le dossier projet de cette journée.
```

### Comprendre le déroulement

Cet exemple autonome affiche un nom de classe complet : java.time est le package et LocalDate le nom simple. `.class` permet ici d'obtenir la représentation d'une classe et getName son nom ; tu n'as pas à apprendre la réflexion aujourd'hui. Le travail principal consiste à compiler et exécuter les deux fichiers du dossier projet, où la séparation des responsabilités est visible.


### ⚠️ Pièges fréquents

- Le nom après java -cp est un nom de classe complet, pas un chemin Windows.
- Un import ne télécharge aucune dépendance.
- Le dossier out contient des résultats de compilation, pas les sources à modifier.

<a id="exercices"></a>
## 💻 Exercices du jour

Essaie d’abord sans ouvrir les indices. Pour un exercice de code, crée ton propre fichier dans un dossier de travail et donne le même nom à la classe publique et au fichier. Les corrigés sont complets pour permettre une comparaison et une exécution indépendantes.

### Niveau 1 — comprendre et appliquer

#### Exercice 01 — Reconstituer un nom complet

Le fichier src/fr/cours/modele/Etudiant.java déclare package fr.cours.modele et public class Etudiant. Quel est son nom complet ?


<details>
<summary>💡 Voir un indice</summary>

src n’appartient pas au nom du package.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le nom complet est fr.cours.modele.Etudiant. La racine à placer sur le classpath après compilation est le dossier contenant fr, pas le sous-dossier modele.


</details>

#### Exercice 02 — Qualifier sans importer

Affiche la taille d’une liste créée par java.util.List.of("Java", "Maths") sans écrire d’import.


<details>
<summary>💡 Voir un indice</summary>

Un nom complet est utilisable directement dans le code.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

L’import est un confort de nommage ; son absence n’empêche pas d’utiliser la classe avec son nom complet.


```java
public class Jour24Exercice02 {
    public static void main(String[] args) {
        java.util.List<String> matieres = java.util.List.of("Java", "Maths");
        System.out.println(matieres.size());
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour24Exercice02.java)

Résultat attendu :

```text
2
```

</details>

### Niveau 2 — corriger et consolider

#### Exercice 03 — Séparer une responsabilité

Écris une classe Message avec une méthode static texte(String prenom), puis appelle-la depuis la classe principale. Le corrigé autonome les regroupe ; refais ensuite la séparation en deux fichiers dans le projet fourni.


<details>
<summary>💡 Voir un indice</summary>

Dans le projet fourni, les deux classes publiques doivent être chacune dans leur fichier et déclarer le même package.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le résultat reste identique, mais les responsabilités sont rangées séparément. Compile tous les fichiers du projet ensemble.


```java
public class Jour24Exercice03 {
    public static void main(String[] args) {
        System.out.println(MessageJ24E03.texte("Nathan"));
    }
}
class MessageJ24E03 {
    static String texte(String prenom) { return "Bonjour " + prenom; }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour24Exercice03.java)

Résultat attendu :

```text
Bonjour Nathan
```

</details>

### Niveau 3 — résoudre un petit problème

#### Exercice 04 — Organiser un calcul réutilisable

Crée Statistiques avec public static double moyenne(int[] notes), puis un App qui l’appelle. Les notes sont non vides. Le corrigé autonome montre le calcul ; ajoute ensuite Statistiques.java au package du projet et appelle-la depuis App.


<details>
<summary>💡 Voir un indice</summary>

Les classes sont compilées ensemble et la méthode devient un point d’accès explicite.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le petit programme sépare données d’entrée et opération réutilisable. Pour le projet multifichier, garde une seule classe publique par fichier.


```java
public class Jour24Exercice04 {
    public static void main(String[] args) {
        System.out.println(StatistiquesJ24E04.moyenne(new int[] {10, 14}));
    }
}
class StatistiquesJ24E04 {
    public static double moyenne(int[] notes) {
        int somme = 0;
        for (int note : notes) somme += note;
        return (double) somme / notes.length;
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour24Exercice04.java)

Résultat attendu :

```text
12.0
```

</details>

<a id="bilan"></a>
## ✅ Avant de passer à la suite

- [ ] Je peux répartir les classes publiques dans des fichiers.
- [ ] Je peux relier package, dossier source et nom complet.
- [ ] Je peux compiler un petit projet sans outil supplémentaire.

- [ ] J’ai tenté les quatre exercices avant de comparer aux corrigés.
- [ ] Je peux expliquer une erreur rencontrée et la façon dont je l’ai corrigée.

[Noter ma progression](../PROGRESSION.md) · [Consulter le glossaire](../docs/GLOSSAIRE.md)

### 📎 Pour approfondir

- [Organiser un projet Java dans VS Code](https://code.visualstudio.com/docs/java/java-project)
- [Introduction à Maven](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)

---

[← Jour 23](../23_Day_Files/23_files.md) | [📚 Sommaire](../README.md) | [Jour 25 →](../25_Day_Algorithms/25_algorithms.md)

**☕ Une étape comprise vaut mieux qu’une journée cochée trop vite.**
