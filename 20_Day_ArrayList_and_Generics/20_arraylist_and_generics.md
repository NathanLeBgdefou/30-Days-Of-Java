<div align="center">
<h1>☕ 30 Days Of Java : jour 20</h1>
<h3>ArrayList et génériques</h3>
<p>Français · Java 21 · Windows &amp; VS Code</p>
</div>

[← Jour 19](../19_Day_Equality_and_Immutability/19_equality_and_immutability.md) | [📚 Sommaire](../README.md) | [Jour 21 →](../21_Day_Sets_and_Maps/21_sets_and_maps.md)

![30 Days Of Java — apprendre à programmer](../images/30DaysOfJava_banner.svg)

![Progression : jour 20 sur 30](../images/progression-20.svg)

**Durée conseillée : 60 à 90 min.** Garde au moins une heure ; prends plus de temps pour coder si nécessaire.

📍 [Objectifs](#objectifs) · [Cours](#cours) · [Exemple](#exemple) · [Exercices](#exercices) · [Bilan](#bilan)

<a id="objectifs"></a>
## 🎯 Objectifs du jour

- Choisir une liste de taille variable.
- Utiliser List<T> et les types enveloppes.
- Éviter les confusions entre indice et valeur lors d’une suppression.

> **Ton rythme :** 10 min de rappel et lecture, 15–20 min sur l’exemple, 30–45 min de pratique, 5 min de bilan. Les jours de projet demandent davantage.

<a id="cours"></a>
# 📘 Cours

## Quand la taille doit évoluer

Un tableau est utile si sa taille est connue. Pour une liste de révisions qui reçoit de nouvelles tâches, ArrayList est souvent plus pratique. Elle conserve un ordre, accepte des doublons et peut grandir ou rétrécir.

`List<String> matieres = new ArrayList<>();` sépare le **contrat List** de son **implémentation ArrayList**. C'est le même principe qu'au jour 18 : le code s'appuie sur les opérations promises par List. L'import `java.util.List` et l'import `java.util.ArrayList` rendent ces noms disponibles.

## Le type entre chevrons

`<String>` signifie que la liste contient des String. Ces paramètres de type sont les **génériques**. Ils permettent au compilateur de refuser l'ajout d'un int dans cette liste, plutôt que de laisser l'erreur apparaître beaucoup plus tard.

Les paramètres génériques utilisent des types de référence. On écrit `List<Integer>`, pas `List<int>`. Integer enveloppe un int. Java effectue souvent les conversions automatiquement, appelées boxing et unboxing. Attention : déballer un Integer null en int provoque une NullPointerException.

| Besoin | Opération |
| --- | --- |
| Ajouter | `liste.add(valeur)` |
| Lire une position | `liste.get(indice)` |
| Remplacer | `liste.set(indice, valeur)` |
| Nombre d'éléments | `liste.size()` |
| Liste vide ? | `liste.isEmpty()` |
| Présence d'une valeur | `liste.contains(valeur)` |
| Retirer par position | `liste.remove(indice)` |

## Retirer un entier : attention à la surcharge

Dans une List<Integer>, `remove(1)` retire l'élément d'indice 1, pas forcément la valeur 1. Pour retirer la première occurrence de la valeur entière 1, utilise `remove(Integer.valueOf(1))`. Le type de l'argument choisit la surcharge.

Quand tu parcours une ArrayList avec for-each, ne supprime pas directement dans la liste au milieu du parcours. Cela peut provoquer une ConcurrentModificationException. Une boucle à indices parcourus **en sens inverse** convient pour des suppressions simples ; nous découvrirons aussi des opérations fonctionnelles au jour 26.

## Modifiable ou non modifiable

`List.of("Java", "Python")` crée une liste non modifiable qui n'accepte pas null. add ou remove y déclenchent une UnsupportedOperationException. Pour une copie modifiable : `new ArrayList<>(liste)`.

`List.copyOf(liste)` produit une liste non modifiable des éléments et refuse null. Comme avec les tableaux, cela ne clone pas les objets contenus. La liste et ses éléments n'ont pas forcément la même mutabilité.

Pour protéger un objet, renvoyer une copie non modifiable de sa liste est souvent préférable à exposer directement son ArrayList interne.


<a id="exemple"></a>
## 🔎 Exemple complet, prêt à exécuter

[Ouvrir Jour20Exemple.java](./exemples/Jour20Exemple.java) · [Lire les exercices sans les réponses](./exercices/README.md)

Dans VS Code, ouvre le dossier `exemples` de cette journée, puis lance dans son terminal :

```powershell
java .\Jour20Exemple.java
```

```java
import java.util.ArrayList;
import java.util.List;
public class Jour20Exemple {
    public static void main(String[] args) {
        List<String> matieres = new ArrayList<>();
        matieres.add("Java");
        matieres.add("Maths");
        matieres.add("Anglais");
        matieres.set(1, "Algorithmique");
        System.out.println(matieres.get(0));
        System.out.println(matieres.size());
        for (String matiere : matieres) {
            System.out.println(matiere);
        }
    }
}
```

**Sortie du programme :**

```text
Java
3
Java
Algorithmique
Anglais
```

### Comprendre le déroulement

La liste conserve l'ordre d'ajout. set remplace une valeur sans changer la taille. get utilise un indice commençant à zéro. Le contrat List suffit pour ces opérations ; main n'a pas besoin d'accéder à des détails internes d'ArrayList.


### ⚠️ Pièges fréquents

- List<int> est invalide : utilise List<Integer>.
- remove(1) choisit la surcharge qui reçoit un indice int.
- List.of crée une liste non modifiable, pas une ArrayList vide à remplir.

<a id="exercices"></a>
## 💻 Exercices du jour

Essaie d’abord sans ouvrir les indices. Pour un exercice de code, crée ton propre fichier dans un dossier de travail et donne le même nom à la classe publique et au fichier. Les corrigés sont complets pour permettre une comparaison et une exécution indépendantes.

### Niveau 1 — comprendre et appliquer

#### Exercice 01 — Deux suppressions différentes

Dans [10, 20, 30], que retire remove(1) ? Que retirerait remove(Integer.valueOf(20)) dans une autre copie de la même liste ?


<details>
<summary>💡 Voir un indice</summary>

Le premier argument est un indice, le second un objet Integer.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Les deux retirent ici 20, mais pour des raisons différentes : l’élément d’indice 1 pour le premier, la première occurrence de la valeur 20 pour le second. Sur d’autres données, les effets diffèrent.


</details>

#### Exercice 02 — Faire évoluer une liste

Crée une ArrayList de matières, ajoute Java et Maths, puis retire Maths par valeur et affiche la liste.


<details>
<summary>💡 Voir un indice</summary>

Avec une String, remove reçoit une valeur objet.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

La liste finale contient uniquement Java.


```java
import java.util.ArrayList;
import java.util.List;
public class Jour20Exercice02 {
    public static void main(String[] args) {
        List<String> matieres = new ArrayList<>();
        matieres.add("Java");
        matieres.add("Maths");
        matieres.remove("Maths");
        System.out.println(matieres);
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour20Exercice02.java)

Résultat attendu :

```text
[Java]
```

</details>

### Niveau 2 — corriger et consolider

#### Exercice 03 — Rendre une copie modifiable

À partir de List.of(12, 15), crée une copie modifiable, ajoute 18, puis affiche l’original et la copie.


<details>
<summary>💡 Voir un indice</summary>

Le constructeur ArrayList peut copier les éléments d’une collection.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

L’ajout ne change pas la liste d’origine.


```java
import java.util.ArrayList;
import java.util.List;
public class Jour20Exercice03 {
    public static void main(String[] args) {
        List<Integer> origine = List.of(12, 15);
        List<Integer> copie = new ArrayList<>(origine);
        copie.add(18);
        System.out.println(origine);
        System.out.println(copie);
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour20Exercice03.java)

Résultat attendu :

```text
[12, 15]
[12, 15, 18]
```

</details>

### Niveau 3 — résoudre un petit problème

#### Exercice 04 — Supprimer les notes insuffisantes

Dans une liste modifiable [8, 12, 9, 15], retire toutes les notes inférieures à 10 en parcourant les indices de la fin vers le début.


<details>
<summary>💡 Voir un indice</summary>

En reculant, la suppression ne déplace pas les cases restant à visiter.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

On obtient [12, 15]. Un parcours croissant avec suppression peut sauter la valeur qui vient de se déplacer dans la case courante.


```java
import java.util.ArrayList;
import java.util.List;
public class Jour20Exercice04 {
    public static void main(String[] args) {
        List<Integer> notes = new ArrayList<>(List.of(8, 12, 9, 15));
        for (int i = notes.size() - 1; i >= 0; i--) {
            if (notes.get(i) < 10) notes.remove(i);
        }
        System.out.println(notes);
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour20Exercice04.java)

Résultat attendu :

```text
[12, 15]
```

</details>

<a id="bilan"></a>
## ✅ Avant de passer à la suite

- [ ] Je peux choisir une liste de taille variable.
- [ ] Je peux utiliser List<T> et les types enveloppes.
- [ ] Je peux éviter les confusions entre indice et valeur lors d’une suppression.

- [ ] J’ai tenté les quatre exercices avant de comparer aux corrigés.
- [ ] Je peux expliquer une erreur rencontrée et la façon dont je l’ai corrigée.

[Noter ma progression](../PROGRESSION.md) · [Consulter le glossaire](../docs/GLOSSAIRE.md)

### 📎 Pour approfondir

- [Collections](https://dev.java/learn/api/collections-framework/)

---

[← Jour 19](../19_Day_Equality_and_Immutability/19_equality_and_immutability.md) | [📚 Sommaire](../README.md) | [Jour 21 →](../21_Day_Sets_and_Maps/21_sets_and_maps.md)

**☕ Une étape comprise vaut mieux qu’une journée cochée trop vite.**
