<div align="center">
<h1>☕ 30 Days Of Java : jour 04</h1>
<h3>Chaînes de caractères</h3>
<p>Français · Java 21 · Windows &amp; VS Code</p>
</div>

[← Jour 03](../03_Day_Operators_and_Conversions/03_operators_and_conversions.md) | [📚 Sommaire](../README.md) | [Jour 05 →](../05_Day_User_Input/05_user_input.md)

![30 Days Of Java — apprendre à programmer](../images/30DaysOfJava_banner.svg)

![Progression : jour 4 sur 30](../images/progression-04.svg)

**Durée conseillée : 60 à 90 min.** Garde au moins une heure ; prends plus de temps pour coder si nécessaire.

📍 [Objectifs](#objectifs) · [Cours](#cours) · [Exemple](#exemple) · [Exercices](#exercices) · [Bilan](#bilan)

<a id="objectifs"></a>
## 🎯 Objectifs du jour

- Nettoyer et transformer du texte.
- Comparer des chaînes avec equals.
- Comprendre les indices et l’immuabilité de String.

> **Ton rythme :** 10 min de rappel et lecture, 15–20 min sur l’exemple, 30–45 min de pratique, 5 min de bilan. Les jours de projet demandent davantage.

<a id="cours"></a>
# 📘 Cours

## Le texte est un objet

Une `String` représente du texte. On lui demande des opérations à l'aide du point : `prenom.length()` donne une longueur, `prenom.toUpperCase()` produit une version en majuscules. Une opération appelée ainsi sur un objet est une **méthode**. Les parenthèses font partie de l'appel, même si elles sont vides.

| Opération | Exemple | Résultat |
| --- | --- | --- |
| Longueur | `"Java".length()` | `4` unités UTF-16 |
| Accès par indice | `"Java".charAt(0)` | `'J'` |
| Extraction | `"Java".substring(1, 3)` | `"av"` |
| Nettoyage des bords | `"  Java  ".strip()` | `"Java"` |
| Recherche | `"Java".contains("av")` | `true` |
| Égalité | `"Java".equals("java")` | `false` |
| Sans tenir compte de la casse | `"Java".equalsIgnoreCase("java")` | `true` |

Les indices commencent à **zéro**. Dans `substring(debut, fin)`, le début est inclus et la fin exclue. Le caractère d'indice 4 n'existe pas dans `"Java"`. Pour du texte avec des emojis, une unité UTF-16 n'est pas forcément un caractère visuel complet : nous travaillons ici sur des exemples simples.

## equals, pas ==

Pour comparer le **contenu** de deux chaînes, utilise `.equals`. `==` vérifie si les deux références désignent le même objet ; selon la manière dont les chaînes sont obtenues, il peut sembler fonctionner puis produire un autre résultat. Ce n'est pas un test fiable du texte.

`"oui".equals(reponse)` évite aussi une erreur si `reponse` est `null`, notion étudiée au jour 19. Pour l'instant, garde cette habitude quand tu compares à un texte connu.

## Une transformation produit une autre valeur

Les chaînes sont **immuables** : une opération comme `strip()` ne modifie pas l'objet existant. Si tu écris seulement `texte.strip();`, la version nettoyée est calculée mais abandonnée. Pour la conserver : `texte = texte.strip();`. La variable reçoit alors une référence vers le résultat.

Pour des identifiants techniques, précise une locale lors d'une conversion de casse : `toLowerCase(Locale.ROOT)`. Une locale représente des conventions linguistiques ; certaines langues ont des règles de majuscules particulières. Le premier `import` de l'exemple rend disponible le nom `Locale`. Nous apprendrons à organiser les imports au fil du parcours.

## Composer un message

La concaténation `"Bonjour " + prenom` est suffisante pour les messages courts. `"\n"` représente un retour à la ligne dans une chaîne, `"\""` un guillemet et `"\\"` une barre oblique inverse. On appelle cela des **séquences d'échappement**. Dans un chemin Windows, il faudra donc doubler les barres dans le code Java, ou utiliser `Path` plus tard.

En venant de Python : `len(texte)` devient `texte.length()`, et `texte[0]` devient `texte.charAt(0)`. Ne transpose pas seulement les mots : vérifie la forme de l'appel.


<a id="exemple"></a>
## 🔎 Exemple complet, prêt à exécuter

[Ouvrir Jour04Exemple.java](./exemples/Jour04Exemple.java) · [Lire les exercices sans les réponses](./exercices/README.md)

Dans VS Code, ouvre le dossier `exemples` de cette journée, puis lance dans son terminal :

```powershell
java .\Jour04Exemple.java
```

```java
import java.util.Locale;

public class Jour04Exemple {
    public static void main(String[] args) {
        String saisie = "  JAVA  ";
        String propre = saisie.strip().toLowerCase(Locale.ROOT);
        System.out.println(propre);
        System.out.println(propre.length());
        System.out.println("java".equals(propre));
        System.out.println(propre.substring(0, 2));
        System.out.println("Original : [" + saisie + "]");
    }
}
```

**Sortie du programme :**

```text
java
4
true
ja
Original : [  JAVA  ]
```

### Comprendre le déroulement

Les opérations enchaînées se lisent de gauche à droite : nettoyer les bords, puis passer en minuscules. `propre` reçoit la nouvelle chaîne. Les crochets du dernier message rendent visibles les espaces encore présents dans `saisie`. L'exemple démontre ainsi l'immuabilité au lieu de simplement l'affirmer.


### ⚠️ Pièges fréquents

- `length()` est une méthode de String ; n’oublie pas les parenthèses.
- `strip()` ne supprime pas les espaces au milieu du texte.
- Appeler charAt(0) sur une chaîne vide déclenche une erreur.

<a id="exercices"></a>
## 💻 Exercices du jour

Essaie d’abord sans ouvrir les indices. Pour un exercice de code, crée ton propre fichier dans un dossier de travail et donne le même nom à la classe publique et au fichier. Les corrigés sont complets pour permettre une comparaison et une exécution indépendantes.

### Niveau 1 — comprendre et appliquer

#### Exercice 01 — Comprendre substring

Que renvoient `"Bonjour".substring(0, 3)` et `"Bonjour".charAt(3)` ?


<details>
<summary>💡 Voir un indice</summary>

Numérote mentalement les lettres depuis zéro.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

`substring(0, 3)` renvoie `"Bon"` : les indices 0, 1 et 2. `charAt(3)` renvoie le char `'j'`.


</details>

#### Exercice 02 — Nettoyer un prénom

À partir de `"  Nathan  "`, affiche `Bonjour Nathan` puis la longueur du prénom nettoyé.


<details>
<summary>💡 Voir un indice</summary>

Conserve le résultat de strip avant de demander sa longueur.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

On nettoie une fois, puis on réutilise la valeur.


```java
public class Jour04Exercice02 {
    public static void main(String[] args) {
        String prenom = "  Nathan  ".strip();
        System.out.println("Bonjour " + prenom);
        System.out.println(prenom.length());
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour04Exercice02.java)

Résultat attendu :

```text
Bonjour Nathan
6
```

</details>

### Niveau 2 — corriger et consolider

#### Exercice 03 — La transformation oubliée

Le code `String cours = " java "; cours.strip();` laisse les espaces. Répare-le et affiche la valeur entre crochets.


<details>
<summary>💡 Voir un indice</summary>

La méthode renvoie une valeur qu’il faut conserver.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Réaffecter cours ne modifie pas l’ancienne chaîne : cela remplace la valeur référencée par la variable.


```java
public class Jour04Exercice03 {
    public static void main(String[] args) {
        String cours = " java ";
        cours = cours.strip();
        System.out.println("[" + cours + "]");
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour04Exercice03.java)

Résultat attendu :

```text
[java]
```

</details>

### Niveau 3 — résoudre un petit problème

#### Exercice 04 — Construire un identifiant

À partir de `"  NATHAN "` et `" LERAY  "`, construis `nathan.leray` en nettoyant et en mettant en minuscules.


<details>
<summary>💡 Voir un indice</summary>

Nettoie chaque partie séparément ; utilise Locale.ROOT pour un identifiant technique.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le séparateur est ajouté après nettoyage. On ne tente pas ici de gérer les homonymes ou de créer un compte réel.


```java
import java.util.Locale;
public class Jour04Exercice04 {
    public static void main(String[] args) {
        String prenom = "  NATHAN ".strip().toLowerCase(Locale.ROOT);
        String nom = " LERAY  ".strip().toLowerCase(Locale.ROOT);
        System.out.println(prenom + "." + nom);
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour04Exercice04.java)

Résultat attendu :

```text
nathan.leray
```

</details>

<a id="bilan"></a>
## ✅ Avant de passer à la suite

- [ ] Je peux nettoyer et transformer du texte.
- [ ] Je peux comparer des chaînes avec equals.
- [ ] Je peux comprendre les indices et l’immuabilité de String.

- [ ] J’ai tenté les quatre exercices avant de comparer aux corrigés.
- [ ] Je peux expliquer une erreur rencontrée et la façon dont je l’ai corrigée.

[Noter ma progression](../PROGRESSION.md) · [Consulter le glossaire](../docs/GLOSSAIRE.md)

### 📎 Pour approfondir

- [API de String](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/String.html)

---

[← Jour 03](../03_Day_Operators_and_Conversions/03_operators_and_conversions.md) | [📚 Sommaire](../README.md) | [Jour 05 →](../05_Day_User_Input/05_user_input.md)

**☕ Une étape comprise vaut mieux qu’une journée cochée trop vite.**
