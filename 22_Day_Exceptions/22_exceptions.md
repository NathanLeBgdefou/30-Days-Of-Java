<div align="center">
<h1>☕ 30 Days Of Java : jour 22</h1>
<h3>Exceptions et saisies robustes</h3>
<p>Français · Java 21 · Windows &amp; VS Code</p>
</div>

[← Jour 21](../21_Day_Sets_and_Maps/21_sets_and_maps.md) | [📚 Sommaire](../README.md) | [Jour 23 →](../23_Day_Files/23_files.md)

![30 Days Of Java — apprendre à programmer](../images/30DaysOfJava_banner.svg)

![Progression : jour 22 sur 30](../images/progression-22.svg)

**Durée conseillée : 60 à 90 min.** Garde au moins une heure ; prends plus de temps pour coder si nécessaire.

📍 [Objectifs](#objectifs) · [Cours](#cours) · [Exemple](#exemple) · [Exercices](#exercices) · [Bilan](#bilan)

<a id="objectifs"></a>
## 🎯 Objectifs du jour

- Lire try, catch, throw et throws.
- Distinguer une saisie invalide d’un défaut de programmation.
- Redemander une valeur sans masquer toutes les erreurs.

> **Ton rythme :** 10 min de rappel et lecture, 15–20 min sur l’exemple, 30–45 min de pratique, 5 min de bilan. Les jours de projet demandent davantage.

<a id="cours"></a>
# 📘 Cours

## Une opération peut échouer

Convertir `"bonjour"` en int n'a pas de sens. `Integer.parseInt` signale cet échec par une NumberFormatException. Une exception interrompt le chemin normal et remonte les appels jusqu'à un bloc catch compatible. Sans traitement adapté, le programme s'arrête avec une trace.

`try` contient l'opération susceptible d'échouer ; `catch` traite une famille précise d'exceptions. Après un traitement normal du catch, le code continue après le bloc. Il ne reprend pas automatiquement à l'instruction qui a échoué.

## Quatre mots distincts

| Mot | Rôle |
| --- | --- |
| `try` | Entourer une portion de code dont on veut traiter un échec. |
| `catch` | Intercepter un type d'exception. |
| `throw` | Déclencher une exception précise. |
| `throws` | Annoncer dans la signature qu'une méthode peut laisser remonter certaines exceptions. |

Les **exceptions vérifiées**, comme IOException, doivent être interceptées ou déclarées avec throws. Les sous-classes de RuntimeException, comme NumberFormatException ou IllegalArgumentException, ne sont pas soumises à cette obligation de compilation. Cela ne veut pas dire qu'il faut les ignorer.

## Traiter au bon niveau

Une méthode de calcul peut refuser un argument invalide avec IllegalArgumentException. Le code d'interface avec l'utilisateur peut afficher un message et proposer une autre saisie. Mélanger tous les niveaux dans une même méthode rend les tests difficiles.

N'entoure pas tout main avec `catch (Exception e) {}` vide. Cela peut masquer des erreurs de programmation et faire croire que l'opération a réussi. Capture ce que tu sais réellement traiter, autour de l'opération concernée, et garde un message utile.

## Recommencer une lecture

On place la tentative dans une boucle. Si la conversion réussit mais que la valeur est hors domaine, on explique le domaine attendu. Si la conversion échoue, on explique le format attendu. Si la valeur est correcte, un return la transmet à l'appelant et quitte la méthode.

`hasNextLine()` permet de reconnaître la fin d'entrée, par exemple quand des données sont fournies par un fichier ou un test. Une boucle robuste ne doit pas demander éternellement une ligne qui n'existe plus.

## Fermer une ressource

Le **try-with-resources**, `try (Ressource r = ...) { ... }`, ferme automatiquement une ressource AutoCloseable à la sortie, même si une exception survient. Nous l'utiliserons pour les fichiers et pour notre lecteur clavier unique. `finally` est un bloc exécuté lors de la sortie habituelle d'un try/catch, utile pour certains nettoyages ; ce n'est pas une raison de remplacer le mécanisme automatique prévu pour les ressources.

Les erreurs de type Error, comme un manque grave de mémoire, ne sont pas des erreurs de saisie à masquer avec le même catch.


<a id="exemple"></a>
## 🔎 Exemple complet, prêt à exécuter

[Ouvrir Jour22Exemple.java](./exemples/Jour22Exemple.java) · [Lire les exercices sans les réponses](./exercices/README.md)

Dans VS Code, ouvre le dossier `exemples` de cette journée, puis lance dans son terminal :

```powershell
java .\Jour22Exemple.java
```

```java
import java.util.Scanner;
public class Jour22Exemple {
    static int lireNote(Scanner clavier) {
        while (clavier.hasNextLine()) {
            String ligne = clavier.nextLine().strip();
            try {
                int note = Integer.parseInt(ligne);
                if (note >= 0 && note <= 20) return note;
                System.out.println("La note doit être entre 0 et 20.");
            } catch (NumberFormatException e) {
                System.out.println("Saisis un entier, par exemple 14.");
            }
        }
        throw new IllegalStateException("Entrée terminée avant une note valide");
    }
    public static void main(String[] args) {
        try (Scanner clavier = new Scanner(System.in)) {
            System.out.println("Note ?");
            int note = lireNote(clavier);
            System.out.println("Note retenue : " + note);
        }
    }
}
```

**Entrées à saisir, une par ligne :**

```text
abc
25
14
```

**Sortie du programme :**

```text
Note ?
Saisis un entier, par exemple 14.
La note doit être entre 0 et 20.
Note retenue : 14
```

### Comprendre le déroulement

Teste successivement `abc`, `25`, puis `14`. Le premier cas échoue pendant la conversion et passe dans catch. Le second est bien un entier mais ne respecte pas le domaine ; il est traité par le if. Le troisième réussit et sort via return. Si l'entrée se termine sans note valide, le contrat de cette petite méthode signale explicitement l'impossibilité de fournir une note.


### ⚠️ Pièges fréquents

- Une conversion réussie ne valide pas à elle seule le domaine métier.
- Un catch vide peut cacher la cause réelle du problème.
- throws n’intercepte rien : il annonce une propagation.

<a id="exercices"></a>
## 💻 Exercices du jour

Essaie d’abord sans ouvrir les indices. Pour un exercice de code, crée ton propre fichier dans un dossier de travail et donne le même nom à la classe publique et au fichier. Les corrigés sont complets pour permettre une comparaison et une exécution indépendantes.

### Niveau 1 — comprendre et appliquer

#### Exercice 01 — Deux refus distincts

Pourquoi `abc` et `25` ne doivent-ils pas produire la même explication quand on attend une note entière sur 20 ?


<details>
<summary>💡 Voir un indice</summary>

L’une échoue à la conversion, l’autre est un entier hors domaine.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Pour abc, le format numérique est invalide. Pour 25, le format est valide mais la valeur est hors de [0, 20]. Un message précis permet à l’utilisateur de corriger la bonne chose.


</details>

#### Exercice 02 — Capturer une conversion

Tente de convertir `douze` en entier et affiche `Entier attendu` si la conversion échoue.


<details>
<summary>💡 Voir un indice</summary>

Capture NumberFormatException autour de parseInt.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le programme traite précisément l’erreur prévue.


```java
public class Jour22Exercice02 {
    public static void main(String[] args) {
        try {
            int nombre = Integer.parseInt("douze");
            System.out.println(nombre);
        } catch (NumberFormatException e) {
            System.out.println("Entier attendu");
        }
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour22Exercice02.java)

Résultat attendu :

```text
Entier attendu
```

</details>

### Niveau 2 — corriger et consolider

#### Exercice 03 — Définir le domaine d’une méthode

Écris racine(double x) qui refuse les valeurs non finies ou négatives avec IllegalArgumentException et renvoie Math.sqrt(x) sinon. Affiche racine(9), puis traite le refus de −1.


<details>
<summary>💡 Voir un indice</summary>

Double.isFinite reconnaît les nombres qui ne sont ni NaN ni infinis.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le contrôle est effectué avant le calcul. L’appelant choisit le message de refus.


```java
public class Jour22Exercice03 {
    static double racine(double x) {
        if (!Double.isFinite(x) || x < 0) throw new IllegalArgumentException("Valeur invalide");
        return Math.sqrt(x);
    }
    public static void main(String[] args) {
        System.out.println(racine(9));
        try {
            racine(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("Valeur invalide");
        }
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour22Exercice03.java)

Résultat attendu :

```text
3.0
Valeur invalide
```

</details>

### Niveau 3 — résoudre un petit problème

#### Exercice 04 — Lire un entier positif

Lis des lignes jusqu’à obtenir un entier strictement positif. Teste x, 0, puis 8. Si l’entrée se termine avant, affiche `Entrée terminée`.


<details>
<summary>💡 Voir un indice</summary>

Combine hasNextLine, try/catch et un contrôle de domaine.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le programme sait sortir aussi lorsque le flux d’entrée est fermé, ce qui facilite son utilisation dans un test.


```java
import java.util.Scanner;
public class Jour22Exercice04 {
    public static void main(String[] args) {
        try (Scanner clavier = new Scanner(System.in)) {
            while (clavier.hasNextLine()) {
                try {
                    int valeur = Integer.parseInt(clavier.nextLine().strip());
                    if (valeur > 0) {
                        System.out.println("Retenu : " + valeur);
                        return;
                    }
                    System.out.println("Valeur strictement positive attendue");
                } catch (NumberFormatException e) {
                    System.out.println("Entier attendu");
                }
            }
            System.out.println("Entrée terminée");
        }
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour22Exercice04.java)

Entrées de vérification :

```text
x
0
8
```

Résultat attendu :

```text
Entier attendu
Valeur strictement positive attendue
Retenu : 8
```

</details>

<a id="bilan"></a>
## ✅ Avant de passer à la suite

- [ ] Je peux lire try, catch, throw et throws.
- [ ] Je peux distinguer une saisie invalide d’un défaut de programmation.
- [ ] Je peux redemander une valeur sans masquer toutes les erreurs.

- [ ] J’ai tenté les quatre exercices avant de comparer aux corrigés.
- [ ] Je peux expliquer une erreur rencontrée et la façon dont je l’ai corrigée.

[Noter ma progression](../PROGRESSION.md) · [Consulter le glossaire](../docs/GLOSSAIRE.md)

---

[← Jour 21](../21_Day_Sets_and_Maps/21_sets_and_maps.md) | [📚 Sommaire](../README.md) | [Jour 23 →](../23_Day_Files/23_files.md)

**☕ Une étape comprise vaut mieux qu’une journée cochée trop vite.**
