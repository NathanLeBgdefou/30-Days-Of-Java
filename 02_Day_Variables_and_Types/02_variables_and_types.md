<div align="center">
<h1>☕ 30 Days Of Java : jour 02</h1>
<h3>Variables et types</h3>
<p>Français · Java 21 · Windows &amp; VS Code</p>
</div>

[← Jour 01](../01_Day_Introduction/01_introduction.md) | [📚 Sommaire](../README.md) | [Jour 03 →](../03_Day_Operators_and_Conversions/03_operators_and_conversions.md)

![30 Days Of Java — apprendre à programmer](../images/30DaysOfJava_banner.svg)

![Progression : jour 2 sur 30](../images/progression-02.svg)

**Durée conseillée : 60 à 90 min.** Garde au moins une heure ; prends plus de temps pour coder si nécessaire.

📍 [Objectifs](#objectifs) · [Cours](#cours) · [Exemple](#exemple) · [Exercices](#exercices) · [Bilan](#bilan)

<a id="objectifs"></a>
## 🎯 Objectifs du jour

- Déclarer une variable avec son type.
- Choisir entre int, double, boolean, char et String.
- Réaffecter une variable et reconnaître une constante.

> **Ton rythme :** 10 min de rappel et lecture, 15–20 min sur l’exemple, 30–45 min de pratique, 5 min de bilan. Les jours de projet demandent davantage.

<a id="cours"></a>
# 📘 Cours

## Une variable a un type

En Python, tu as peut-être écrit `age = 19`. En Java, une déclaration courante est `int age = 19;`. Le mot `int` annonce que cette variable contient un entier. Ce contrat permet au compilateur de refuser ensuite `age = "dix-neuf";` : ce texte ne correspond pas au type déclaré.

Une déclaration comporte donc un **type**, un **nom**, et souvent une **valeur initiale**. L'opérateur `=` affecte une valeur ; il ne pose pas une équation mathématique. `age = age + 1;` lit l'ancienne valeur, calcule la nouvelle, puis la range dans `age`. Ne remets pas le type lors d'une simple réaffectation.

## Les types utiles au départ

| Type | Exemple | Rôle |
| --- | --- | --- |
| `int` | `int credits = 30;` | Entier signé sur 32 bits, de −2 147 483 648 à 2 147 483 647. |
| `long` | `long population = 8000000000L;` | Entier signé sur 64 bits ; le suffixe `L` précise ce littéral. |
| `double` | `double moyenne = 13.5;` | Nombre à virgule flottante, avec une précision limitée. |
| `boolean` | `boolean inscrit = true;` | L'une des deux valeurs `true` ou `false`. |
| `char` | `char groupe = 'A';` | Une unité UTF-16, entre apostrophes ; pas toujours un caractère Unicode entier. |
| `String` | `String prenom = "Nathan";` | Du texte, entre guillemets doubles. |

Pour les exemples usuels, un `char` comme `'A'` représente bien une lettre. Un emoji peut occuper deux unités UTF-16 : évite de généraliser « un char = tout caractère possible ». `String` est un type objet, les cinq autres types ci-dessus sont des types primitifs. Nous préciserons cette distinction au jour 19.

En Java, les décimales du code utilisent un **point**, même si nous écrivons une virgule en français. `double prix = 12,50;` est incorrect. Les `double` conviennent à nos moyennes, mais ne représentent pas exactement toutes les fractions décimales. Pour de l'argent exact, on utilise par exemple des centimes entiers ou `BigDecimal`.

## Nommer et initialiser

Choisis des noms explicites : `nombreDeNotes`, `moyenneGenerale`, `estInscrit`. La convention est le **camelCase** pour les variables. Une classe commence par une majuscule. On ne peut pas réutiliser un mot réservé comme `class` ou commencer un nom par un chiffre.

Une variable locale doit recevoir une valeur avant d'être lue. `int note;` déclare une variable, mais ne lui attribue pas automatiquement zéro dans une méthode.

## Une valeur qui ne doit pas changer

`final int JOURS_DU_PARCOURS = 30;` interdit de réaffecter cette variable. La convention en majuscules souligne ici une constante. Plus tard, nous verrons que `final` appliqué à une référence d'objet n'empêche pas forcément de modifier l'objet lui-même.

Java possède aussi `var` pour certaines variables locales : le compilateur déduit le type à partir de la valeur initiale. Cela ne rend pas Java dynamiquement typé. Dans ce parcours, on écrit généralement les types pour mieux les apprendre.


<a id="exemple"></a>
## 🔎 Exemple complet, prêt à exécuter

[Ouvrir Jour02Exemple.java](./exemples/Jour02Exemple.java) · [Lire les exercices sans les réponses](./exercices/README.md)

Dans VS Code, ouvre le dossier `exemples` de cette journée, puis lance dans son terminal :

```powershell
java .\Jour02Exemple.java
```

```java
public class Jour02Exemple {
    public static void main(String[] args) {
        String prenom = "Nathan";
        int heures = 1;
        double moyenne = 13.5;
        boolean motive = true;
        final int JOURS = 30;
        heures = heures + 1;
        System.out.println(prenom);
        System.out.println(heures);
        System.out.println(moyenne);
        System.out.println(motive);
        System.out.println(JOURS);
    }
}
```

**Sortie du programme :**

```text
Nathan
2
13.5
true
30
```

### Comprendre le déroulement

Au début, `heures` vaut 1. L'affectation calcule `1 + 1`, puis remplace la valeur par 2. Les autres variables n'ont pas changé. `println` peut afficher plusieurs types, même si leurs possibilités de calcul sont différentes. Essaie de remplacer `moyenne` par du texte dans une affectation : le refus du compilateur t'indique précisément le contrat de type violé.


### ⚠️ Pièges fréquents

- `String` prend une majuscule ; `int`, `double` et `boolean` non.
- `true` et `false` sont en minuscules et sans guillemets.
- Déclarer deux fois une variable du même nom dans le même bloc provoque une erreur.

<a id="exercices"></a>
## 💻 Exercices du jour

Essaie d’abord sans ouvrir les indices. Pour un exercice de code, crée ton propre fichier dans un dossier de travail et donne le même nom à la classe publique et au fichier. Les corrigés sont complets pour permettre une comparaison et une exécution indépendantes.

### Niveau 1 — comprendre et appliquer

#### Exercice 01 — Choisir le bon type

Quel type choisir pour un nombre d’absences, une moyenne sur 20, un prénom et l’état « devoir rendu » ?


<details>
<summary>💡 Voir un indice</summary>

Distingue quantité entière, mesure décimale, texte et réponse oui/non.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

`int` pour le nombre d’absences, `double` pour la moyenne, `String` pour le prénom et `boolean` pour le devoir rendu. Le choix dépend du sens des données, pas de leur apparence quand on les affiche.


</details>

#### Exercice 02 — Prévoir des heures de travail

Déclare `heuresParJour = 1` et `jours = 30` avec des types adaptés. Calcule puis affiche le total d’heures.


<details>
<summary>💡 Voir un indice</summary>

Le total est le produit de deux entiers.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le total dérive des deux variables : changer le rythme recalculera la prévision.


```java
public class Jour02Exercice02 {
    public static void main(String[] args) {
        int heuresParJour = 1;
        int jours = 30;
        int totalHeures = heuresParJour * jours;
        System.out.println(totalHeures);
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour02Exercice02.java)

Résultat attendu :

```text
30
```

</details>

### Niveau 2 — corriger et consolider

#### Exercice 03 — Réparer les types

Corrige les intentions suivantes : une moyenne `int moyenne = 12.5;` et un prénom `char prenom = "Nathan";`. Affiche les valeurs.


<details>
<summary>💡 Voir un indice</summary>

Un entier ne conserve pas une partie décimale ; un prénom comporte plusieurs lettres.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Changer les types permet de conserver les informations. Convertir 12.5 en entier ferait perdre la fraction.


```java
public class Jour02Exercice03 {
    public static void main(String[] args) {
        double moyenne = 12.5;
        String prenom = "Nathan";
        System.out.println(moyenne);
        System.out.println(prenom);
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour02Exercice03.java)

Résultat attendu :

```text
12.5
Nathan
```

</details>

### Niveau 3 — résoudre un petit problème

#### Exercice 04 — Suivre une réserve de places

Un cours possède 25 places libres. Enregistre ce nombre, retire 3 inscriptions, puis ajoute 1 annulation. Affiche le nombre final sans le taper directement.


<details>
<summary>💡 Voir un indice</summary>

Réaffecte la même variable après chaque événement.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

On suit un état qui évolue : 25 → 22 → 23. Cette simulation est une petite version de la logique d’un vrai programme.


```java
public class Jour02Exercice04 {
    public static void main(String[] args) {
        int placesLibres = 25;
        placesLibres = placesLibres - 3;
        placesLibres = placesLibres + 1;
        System.out.println(placesLibres);
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour02Exercice04.java)

Résultat attendu :

```text
23
```

</details>

<a id="bilan"></a>
## ✅ Avant de passer à la suite

- [ ] Je peux déclarer une variable avec son type.
- [ ] Je peux choisir entre int, double, boolean, char et String.
- [ ] Je peux réaffecter une variable et reconnaître une constante.

- [ ] J’ai tenté les quatre exercices avant de comparer aux corrigés.
- [ ] Je peux expliquer une erreur rencontrée et la façon dont je l’ai corrigée.

[Noter ma progression](../PROGRESSION.md) · [Consulter le glossaire](../docs/GLOSSAIRE.md)

### 📎 Pour approfondir

- [Types primitifs](https://dev.java/learn/language-basics/primitive-types/)

---

[← Jour 01](../01_Day_Introduction/01_introduction.md) | [📚 Sommaire](../README.md) | [Jour 03 →](../03_Day_Operators_and_Conversions/03_operators_and_conversions.md)

**☕ Une étape comprise vaut mieux qu’une journée cochée trop vite.**
