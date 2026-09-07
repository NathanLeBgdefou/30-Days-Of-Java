<div align="center">
<h1>☕ 30 Days Of Java : jour 26</h1>
<h3>Lambdas et streams</h3>
<p>Français · Java 21 · Windows &amp; VS Code</p>
</div>

[← Jour 25](../25_Day_Algorithms/25_algorithms.md) | [📚 Sommaire](../README.md) | [Jour 27 →](../27_Day_Dates_and_Enums/27_dates_and_enums.md)

![30 Days Of Java — apprendre à programmer](../images/30DaysOfJava_banner.svg)

![Progression : jour 26 sur 30](../images/progression-26.svg)

**Durée conseillée : 60 à 90 min.** Garde au moins une heure ; prends plus de temps pour coder si nécessaire.

📍 [Objectifs](#objectifs) · [Cours](#cours) · [Exemple](#exemple) · [Exercices](#exercices) · [Bilan](#bilan)

<a id="objectifs"></a>
## 🎯 Objectifs du jour

- Lire une lambda comme une petite opération.
- Filtrer, transformer et collecter des valeurs.
- Comprendre l’exécution terminale et éviter les effets de bord.

> **Ton rythme :** 10 min de rappel et lecture, 15–20 min sur l’exemple, 30–45 min de pratique, 5 min de bilan. Les jours de projet demandent davantage.

<a id="cours"></a>
# 📘 Cours

## Une opération passée en paramètre

Une lambda décrit une petite fonction que l'on transmet à une méthode : `note -> note >= 10`. À gauche de la flèche se trouve le paramètre ; à droite, le résultat. Ici, l'opération prend une note et renvoie un boolean.

Une lambda correspond à une **interface fonctionnelle**, c'est-à-dire une interface avec une seule méthode abstraite pertinente. Predicate<T> représente par exemple un test sur une valeur T. Tu n'as pas besoin de connaître tous les noms d'interfaces aujourd'hui pour lire un pipeline simple.

## Un stream décrit un traitement de données

`notes.stream()` crée un flux à partir d'une collection. `filter` conserve les éléments satisfaisant un critère. `map` transforme chaque élément. Une opération terminale comme `toList`, `count` ou `sum` déclenche le traitement et produit un résultat.

```java fragment
java.util.List<Integer> notes = java.util.List.of(8, 12, 15);
java.util.List<Integer> validees = notes.stream()
    .filter(note -> note >= 10)
    .toList();
System.out.println(validees);
```

Les opérations intermédiaires sont généralement **paresseuses** : décrire un filter seul ne suffit pas à parcourir les données. Un stream ne se réutilise pas après une opération terminale ; crée un nouveau stream depuis la source pour un autre traitement.

## Lire de haut en bas

| Opération | Question |
| --- | --- |
| `filter` | Quels éléments garder ? |
| `map` | En quoi transformer chaque élément ? |
| `sorted` | Dans quel ordre les placer ? |
| `distinct` | Quelles valeurs sont distinctes selon equals ? |
| `limit` | Combien d'éléments au maximum conserver ? |
| `toList` | Produire une liste de résultats. |

L'ordre des opérations compte. Filtrer les notes validées puis prendre les deux premières n'a pas le même sens que prendre deux notes quelconques puis filtrer.

## Des flux spécialisés pour les nombres

`mapToInt(note -> note)` convertit un Stream<Integer> en IntStream, qui propose sum et average. average renvoie un OptionalDouble car le flux peut être vide. `orElse(0.0)` choisit un défaut explicite, mais une moyenne zéro ne doit pas être confondue avec « aucune donnée » si le domaine exige de les distinguer.

La notation `String::length` est une **référence de méthode**, équivalente ici à `texte -> texte.length()`. Commence par la lambda écrite entièrement, puis reconnais ce raccourci.

## Garder une lecture simple

Un stream ne rend pas automatiquement un programme plus rapide ou meilleur. Une boucle reste adaptée quand l'état et les étapes se lisent plus clairement ainsi. Évite de modifier une collection extérieure depuis filter ou map. Un traitement sans effets de bord est plus facile à comprendre et à tester.

La liste produite par `Stream.toList()` est non modifiable. Si tu veux ensuite y ajouter des éléments, crée une nouvelle ArrayList à partir du résultat. Nous n'utiliserons pas de flux parallèles dans ce parcours : ils ajoutent des questions de concurrence qui ne sont pas nécessaires à ces exercices.


<a id="exemple"></a>
## 🔎 Exemple complet, prêt à exécuter

[Ouvrir Jour26Exemple.java](./exemples/Jour26Exemple.java) · [Lire les exercices sans les réponses](./exercices/README.md)

Dans VS Code, ouvre le dossier `exemples` de cette journée, puis lance dans son terminal :

```powershell
java .\Jour26Exemple.java
```

```java
import java.util.List;
public class Jour26Exemple {
    public static void main(String[] args) {
        List<Integer> notes = List.of(8, 14, 12, 9, 16);
        List<Integer> validees = notes.stream()
            .filter(note -> note >= 10)
            .sorted()
            .toList();
        int total = validees.stream().mapToInt(note -> note).sum();
        System.out.println(validees);
        System.out.println(total);
        System.out.println(notes);
    }
}
```

**Sortie du programme :**

```text
[12, 14, 16]
42
[8, 14, 12, 9, 16]
```

### Comprendre le déroulement

Le premier pipeline garde 14, 12 et 16, puis les trie. Le second crée un nouveau flux depuis la liste résultat et en calcule la somme. La liste notes n'a pas été modifiée. Cet affichage final vérifie que le traitement a produit une autre collection.


### ⚠️ Pièges fréquents

- Sans opération terminale, un pipeline peut ne jamais parcourir les valeurs.
- Un stream consommé ne doit pas être réutilisé.
- toList produit ici une liste non modifiable.

<a id="exercices"></a>
## 💻 Exercices du jour

Essaie d’abord sans ouvrir les indices. Pour un exercice de code, crée ton propre fichier dans un dossier de travail et donne le même nom à la classe publique et au fichier. Les corrigés sont complets pour permettre une comparaison et une exécution indépendantes.

### Niveau 1 — comprendre et appliquer

#### Exercice 01 — Lire la lambda

Dans `n -> n * 2`, qu’est-ce que n et que produit la partie droite ? L’écriture modifie-t-elle automatiquement la liste d’origine ?


<details>
<summary>💡 Voir un indice</summary>

Une transformation produit un résultat pour une entrée.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

n est le paramètre ; la partie droite produit son double. La lambda ne remplace pas automatiquement les éléments de la liste source.


</details>

#### Exercice 02 — Filtrer les nombres pairs

À partir de [1, 2, 3, 4, 5, 6], construis avec un stream la liste des pairs.


<details>
<summary>💡 Voir un indice</summary>

filter attend une lambda qui renvoie boolean.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le résultat conserve l’ordre des éléments retenus de cette liste.


```java
import java.util.List;
public class Jour26Exercice02 {
    public static void main(String[] args) {
        List<Integer> pairs = List.of(1, 2, 3, 4, 5, 6).stream()
            .filter(n -> n % 2 == 0)
            .toList();
        System.out.println(pairs);
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour26Exercice02.java)

Résultat attendu :

```text
[2, 4, 6]
```

</details>

### Niveau 2 — corriger et consolider

#### Exercice 03 — Transformer en longueurs

Transforme [Java, Python, C] en [4, 6, 1] avec map.


<details>
<summary>💡 Voir un indice</summary>

La lambda transforme chaque String en sa longueur.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le type des éléments change : le résultat est une List<Integer>.


```java
import java.util.List;
public class Jour26Exercice03 {
    public static void main(String[] args) {
        List<Integer> longueurs = List.of("Java", "Python", "C").stream()
            .map(texte -> texte.length())
            .toList();
        System.out.println(longueurs);
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour26Exercice03.java)

Résultat attendu :

```text
[4, 6, 1]
```

</details>

### Niveau 3 — résoudre un petit problème

#### Exercice 04 — Préparer des titres propres

À partir de [" Java ", "", "Maths", "Java", "   "], nettoie les bords, retire les titres vides, déduplique, trie et collecte.


<details>
<summary>💡 Voir un indice</summary>

map(strip) doit passer avant le filtre des chaînes vides.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le nettoyage transforme aussi la chaîne composée d’espaces en chaîne vide. distinct retire le deuxième Java.


```java
import java.util.List;
public class Jour26Exercice04 {
    public static void main(String[] args) {
        List<String> titres = List.of(" Java ", "", "Maths", "Java", "   ").stream()
            .map(String::strip)
            .filter(texte -> !texte.isEmpty())
            .distinct()
            .sorted()
            .toList();
        System.out.println(titres);
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour26Exercice04.java)

Résultat attendu :

```text
[Java, Maths]
```

</details>

<a id="bilan"></a>
## ✅ Avant de passer à la suite

- [ ] Je peux lire une lambda comme une petite opération.
- [ ] Je peux filtrer, transformer et collecter des valeurs.
- [ ] Je peux comprendre l’exécution terminale et éviter les effets de bord.

- [ ] J’ai tenté les quatre exercices avant de comparer aux corrigés.
- [ ] Je peux expliquer une erreur rencontrée et la façon dont je l’ai corrigée.

[Noter ma progression](../PROGRESSION.md) · [Consulter le glossaire](../docs/GLOSSAIRE.md)

### 📎 Pour approfondir

- [API Stream](https://dev.java/learn/api/streams/)

---

[← Jour 25](../25_Day_Algorithms/25_algorithms.md) | [📚 Sommaire](../README.md) | [Jour 27 →](../27_Day_Dates_and_Enums/27_dates_and_enums.md)

**☕ Une étape comprise vaut mieux qu’une journée cochée trop vite.**
