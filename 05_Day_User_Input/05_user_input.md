<div align="center">
<h1>☕ 30 Days Of Java : jour 05</h1>
<h3>Lire les saisies au clavier</h3>
<p>Français · Java 21 · Windows &amp; VS Code</p>
</div>

[← Jour 04](../04_Day_Strings/04_strings.md) | [📚 Sommaire](../README.md) | [Jour 06 →](../06_Day_Conditionals/06_conditionals.md)

![30 Days Of Java — apprendre à programmer](../images/30DaysOfJava_banner.svg)

![Progression : jour 5 sur 30](../images/progression-05.svg)

**Durée conseillée : 60 à 90 min.** Garde au moins une heure ; prends plus de temps pour coder si nécessaire.

📍 [Objectifs](#objectifs) · [Cours](#cours) · [Exemple](#exemple) · [Exercices](#exercices) · [Bilan](#bilan)

<a id="objectifs"></a>
## 🎯 Objectifs du jour

- Lire une ligne avec Scanner.
- Convertir du texte en nombre.
- Éviter le piège du mélange nextInt et nextLine.

> **Ton rythme :** 10 min de rappel et lecture, 15–20 min sur l’exemple, 30–45 min de pratique, 5 min de bilan. Les jours de projet demandent davantage.

<a id="cours"></a>
# 📘 Cours

## Faire dialoguer ton programme

Jusqu'ici, les valeurs étaient écrites dans le code. Pour poser une question à l'utilisateur, nous utilisons `Scanner`, une classe de la bibliothèque standard. `import java.util.Scanner;` indique où la trouver. `new Scanner(System.in)` crée un lecteur branché sur l'entrée standard, ici le clavier du terminal.

L'instruction `clavier.nextLine()` attend qu'on saisisse une ligne et qu'on appuie sur Entrée. Son résultat est une `String`, même si la ligne contient seulement des chiffres. En Python, l'idée ressemble à `input()`.

## Séparer lecture et conversion

Pour obtenir un entier : `Integer.parseInt(texte)`. Pour obtenir un décimal : `Double.parseDouble(texte)`. Le préfixe `Integer` est le nom d'une classe fournissant cette opération ; il est différent du type primitif `int` qui reçoit le résultat.

| Étape | Exemple | Type obtenu |
| --- | --- | --- |
| Lire | `String ligne = clavier.nextLine();` | `String` |
| Nettoyer | `ligne = ligne.strip();` | `String` |
| Convertir | `int age = Integer.parseInt(ligne);` | `int` |

Lire toutes les entrées avec `nextLine`, puis convertir celles qui doivent l'être, donne une règle simple et cohérente. Pour ce premier exercice, nous supposons la saisie valide. Au jour 22, nous apprendrons à récupérer les erreurs de conversion sans arrêter le programme.

## Pourquoi ne pas mélanger nextInt et nextLine ?

`nextInt()` lit un nombre mais laisse généralement le séparateur de fin de ligne. Un `nextLine()` juste après peut alors consommer cette fin de ligne et renvoyer une chaîne vide. Ce comportement n'est pas un bug de VS Code. Notre stratégie « toujours lire une ligne » évite ce piège.

Pour les décimales avec `Double.parseDouble`, demande un **point**, par exemple `12.5`. Une chaîne comme `12,5` n'est pas directement acceptée. Les formats régionaux sont un autre sujet ; mieux vaut expliquer le format attendu que laisser l'utilisateur le deviner.

## Où saisir dans VS Code ?

Utilise le **terminal intégré**, en lançant `java .\Jour05Exemple.java`. Le panneau « Output / Sortie » n'est pas une entrée clavier interactive. Si tu lances avec le bouton Run de l'extension Java, vérifie que l'exécution utilise le terminal intégré. Le [guide Windows](../docs/INSTALLATION_WINDOWS.md) explique les réglages utiles.

`System.out.print` affiche sans passer à la ligne : la réponse peut ainsi suivre la question. Pour conserver des sorties faciles à vérifier dans ce cours, les exemples utilisent souvent `println`, puis lisent la ligne suivante.

En fin de petit programme, `clavier.close()` ferme le Scanner et l'entrée standard associée. Ne recrée pas ensuite un autre lecteur sur `System.in` : garde un seul Scanner pour l'ensemble d'une application interactive.


<a id="exemple"></a>
## 🔎 Exemple complet, prêt à exécuter

[Ouvrir Jour05Exemple.java](./exemples/Jour05Exemple.java) · [Lire les exercices sans les réponses](./exercices/README.md)

Dans VS Code, ouvre le dossier `exemples` de cette journée, puis lance dans son terminal :

```powershell
java .\Jour05Exemple.java
```

```java
import java.util.Scanner;

public class Jour05Exemple {
    public static void main(String[] args) {
        Scanner clavier = new Scanner(System.in);
        System.out.println("Ton prénom ?");
        String prenom = clavier.nextLine().strip();
        System.out.println("Combien de minutes aujourd'hui ?");
        int minutes = Integer.parseInt(clavier.nextLine().strip());
        System.out.println("Bonjour " + prenom + ".");
        System.out.println("Sur 5 jours : " + (minutes * 5) + " minutes.");
        clavier.close();
    }
}
```

**Entrées à saisir, une par ligne :**

```text
Nathan
60
```

**Sortie du programme :**

```text
Ton prénom ?
Combien de minutes aujourd'hui ?
Bonjour Nathan.
Sur 5 jours : 300 minutes.
```

### Comprendre le déroulement

Teste avec `Nathan`, puis `60`. Chaque `nextLine()` attend une réponse distincte. Le nettoyage tolère des espaces accidentels au début ou à la fin. La conversion en `int` permet ensuite de multiplier les minutes ; si nous gardions une String, nous ne pourrions pas effectuer cette multiplication.

La sortie attendue ci-dessous contient les messages du programme ; ton terminal affiche aussi les réponses que tu tapes. Elles sont précisées séparément pour éviter de confondre entrée et sortie.


### ⚠️ Pièges fréquents

- Saisir « une heure » quand un entier est demandé provoque une NumberFormatException.
- Une ligne vide n’est pas le nombre zéro.
- La fermeture du Scanner ferme aussi System.in ; on le ferme à la fin du programme.

<a id="exercices"></a>
## 💻 Exercices du jour

Essaie d’abord sans ouvrir les indices. Pour un exercice de code, crée ton propre fichier dans un dossier de travail et donne le même nom à la classe publique et au fichier. Les corrigés sont complets pour permettre une comparaison et une exécution indépendantes.

### Niveau 1 — comprendre et appliquer

#### Exercice 01 — Suivre les types

Après `String ligne = "42"; int nombre = Integer.parseInt(ligne);`, quels sont les types de ligne et de nombre ? La variable ligne change-t-elle ?


<details>
<summary>💡 Voir un indice</summary>

Une conversion peut produire une nouvelle valeur sans modifier la variable source.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

`ligne` reste une String contenant `"42"`. `nombre` est un int valant 42. La conversion n’a pas transformé le type de la variable ligne.


</details>

#### Exercice 02 — Saluer une personne

Demande un prénom, nettoie les espaces extérieurs et affiche `Salut Emma !` si la saisie est ` Emma `.


<details>
<summary>💡 Voir un indice</summary>

Lis une ligne puis utilise strip.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le résultat ne conserve pas les espaces accidentels du clavier.


```java
import java.util.Scanner;
public class Jour05Exercice02 {
    public static void main(String[] args) {
        Scanner clavier = new Scanner(System.in);
        System.out.println("Prénom ?");
        String prenom = clavier.nextLine().strip();
        System.out.println("Salut " + prenom + " !");
        clavier.close();
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour05Exercice02.java)

Entrées de vérification :

```text
 Emma 
```

Résultat attendu :

```text
Prénom ?
Salut Emma !
```

</details>

### Niveau 2 — corriger et consolider

#### Exercice 03 — Lire un nombre puis un texte

Demande un âge, puis une ville, sans que la ville soit sautée. Teste avec 19 et Rennes. Affiche `19 ans, Rennes`.


<details>
<summary>💡 Voir un indice</summary>

Évite nextInt : lis et convertis une ligne entière.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Les deux lectures consomment chacune une ligne complète, ce qui élimine la fin de ligne laissée par nextInt.


```java
import java.util.Scanner;
public class Jour05Exercice03 {
    public static void main(String[] args) {
        Scanner clavier = new Scanner(System.in);
        System.out.println("Âge ?");
        int age = Integer.parseInt(clavier.nextLine().strip());
        System.out.println("Ville ?");
        String ville = clavier.nextLine().strip();
        System.out.println(age + " ans, " + ville);
        clavier.close();
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour05Exercice03.java)

Entrées de vérification :

```text
19
Rennes
```

Résultat attendu :

```text
Âge ?
Ville ?
19 ans, Rennes
```

</details>

### Niveau 3 — résoudre un petit problème

#### Exercice 04 — Calculer à partir du clavier

Demande deux notes décimales valides, avec un point. Affiche leur moyenne. Teste 12.5 et 15.5 ; attendu 14.0.


<details>
<summary>💡 Voir un indice</summary>

Convertis chaque ligne avec Double.parseDouble avant d’additionner.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

La moyenne utilise deux double. Les parenthèses regroupent la somme avant la division.


```java
import java.util.Scanner;
public class Jour05Exercice04 {
    public static void main(String[] args) {
        Scanner clavier = new Scanner(System.in);
        System.out.println("Note 1 (point décimal) ?");
        double a = Double.parseDouble(clavier.nextLine().strip());
        System.out.println("Note 2 (point décimal) ?");
        double b = Double.parseDouble(clavier.nextLine().strip());
        System.out.println("Moyenne : " + (a + b) / 2.0);
        clavier.close();
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour05Exercice04.java)

Entrées de vérification :

```text
12.5
15.5
```

Résultat attendu :

```text
Note 1 (point décimal) ?
Note 2 (point décimal) ?
Moyenne : 14.0
```

</details>

<a id="bilan"></a>
## ✅ Avant de passer à la suite

- [ ] Je peux lire une ligne avec Scanner.
- [ ] Je peux convertir du texte en nombre.
- [ ] Je peux éviter le piège du mélange nextInt et nextLine.

- [ ] J’ai tenté les quatre exercices avant de comparer aux corrigés.
- [ ] Je peux expliquer une erreur rencontrée et la façon dont je l’ai corrigée.

[Noter ma progression](../PROGRESSION.md) · [Consulter le glossaire](../docs/GLOSSAIRE.md)

### 📎 Pour approfondir

- [API de Scanner](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Scanner.html)

---

[← Jour 04](../04_Day_Strings/04_strings.md) | [📚 Sommaire](../README.md) | [Jour 06 →](../06_Day_Conditionals/06_conditionals.md)

**☕ Une étape comprise vaut mieux qu’une journée cochée trop vite.**
