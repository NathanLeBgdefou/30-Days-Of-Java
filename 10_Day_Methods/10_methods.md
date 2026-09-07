<div align="center">
<h1>☕ 30 Days Of Java : jour 10</h1>
<h3>Méthodes et décomposition</h3>
<p>Français · Java 21 · Windows &amp; VS Code</p>
</div>

[← Jour 09](../09_Day_Arrays/09_arrays.md) | [📚 Sommaire](../README.md) | [Jour 11 →](../11_Day_Scope_and_Parameters/11_scope_and_parameters.md)

![30 Days Of Java — apprendre à programmer](../images/30DaysOfJava_banner.svg)

![Progression : jour 10 sur 30](../images/progression-10.svg)

**Durée conseillée : 60 à 90 min.** Garde au moins une heure ; prends plus de temps pour coder si nécessaire.

📍 [Objectifs](#objectifs) · [Cours](#cours) · [Exemple](#exemple) · [Exercices](#exercices) · [Bilan](#bilan)

<a id="objectifs"></a>
## 🎯 Objectifs du jour

- Déclarer une méthode avec paramètres et retour.
- Distinguer afficher et renvoyer une valeur.
- Réutiliser une opération dans plusieurs situations.

> **Ton rythme :** 10 min de rappel et lecture, 15–20 min sur l’exemple, 30–45 min de pratique, 5 min de bilan. Les jours de projet demandent davantage.

<a id="cours"></a>
# 📘 Cours

## Donner un nom à une opération

Une méthode rassemble des instructions qui réalisent une tâche. Tu as déjà appelé `println`, `strip` ou `parseInt`. Tu vas maintenant définir les tiennes. En Python, l'idée ressemble à une fonction déclarée avec `def`, mais Java place ses méthodes dans des classes.

`static double moyenne(int a, int b)` décrit une méthode nommée moyenne, recevant deux int et renvoyant un double. Pour cette première étape, nous utilisons `static` afin de l'appeler directement depuis main, sans créer d'objet. Les méthodes associées aux objets arriveront au jour 14.

| Partie | Rôle |
| --- | --- |
| `double` avant le nom | Type de la valeur renvoyée. |
| `moyenne` | Nom choisi pour exprimer la tâche. |
| `int a, int b` | Paramètres : variables locales qui recevront les valeurs transmises. |
| `return ...;` | Fournit le résultat et termine cet appel. |

Les **arguments** sont les valeurs données à l'appel, comme 12 et 15 dans `moyenne(12, 15)`. Les **paramètres** sont leurs noms dans la déclaration, a et b. On peut appeler la méthode avec d'autres valeurs sans réécrire son calcul.

## Retourner n'est pas afficher

`return resultat;` transmet une valeur au code appelant. `System.out.println(resultat);` écrit un message dans le terminal. Une méthode de calcul qui renvoie son résultat est plus réutilisable : on peut l'afficher, le comparer ou le stocker.

Une méthode `void` ne renvoie pas de valeur. Elle peut par exemple afficher un menu. On ne peut pas affecter son résultat à une variable. `return;` peut terminer une méthode void sans fournir de valeur.

## Placer les méthodes au bon endroit

Dans nos fichiers, main et les méthodes auxiliaires sont côte à côte, **à l'intérieur de la classe**. On ne déclare pas une méthode nommée directement à l'intérieur du corps de main. Les accolades permettent de voir cette structure.

## Définir un contrat simple

Avant de coder une méthode, pose trois questions : quelles entrées attend-elle ? Quel résultat renvoie-t-elle ? Que faire si les entrées sont hors du domaine prévu ? Pour une moyenne de tableau, le tableau vide nécessite par exemple une règle explicite.

Aujourd'hui, choisis des tâches petites : calculer un carré, vérifier si un entier est pair, convertir des minutes. Évite une méthode qui lit au clavier, calcule, affiche et sauvegarde tout à la fois. La décomposition deviendra précieuse quand tu voudras tester une seule règle.


<a id="exemple"></a>
## 🔎 Exemple complet, prêt à exécuter

[Ouvrir Jour10Exemple.java](./exemples/Jour10Exemple.java) · [Lire les exercices sans les réponses](./exercices/README.md)

Dans VS Code, ouvre le dossier `exemples` de cette journée, puis lance dans son terminal :

```powershell
java .\Jour10Exemple.java
```

```java
public class Jour10Exemple {
    static double moyenne(int a, int b) {
        return (a + b) / 2.0;
    }

    static boolean estValidee(double note) {
        return note >= 10;
    }

    public static void main(String[] args) {
        double resultat = moyenne(12, 15);
        System.out.println(resultat);
        System.out.println(estValidee(resultat));
    }
}
```

**Sortie du programme :**

```text
13.5
true
```

### Comprendre le déroulement

L'appel moyenne crée deux paramètres locaux valant 12 et 15. `return` fournit 13.5 à main, qui le conserve dans resultat. Un second appel transmet cette valeur à estValidee. Cette dernière renvoie directement le résultat d'une comparaison : inutile de reconstruire un if qui retourne true dans un cas et false dans l'autre.


### ⚠️ Pièges fréquents

- Une méthode qui annonce un retour doit renvoyer une valeur sur chaque chemin normal possible.
- Afficher un résultat dans la méthode ne remplace pas son return.
- On appelle une méthode avec des valeurs, sans répéter leurs types dans les parenthèses.

<a id="exercices"></a>
## 💻 Exercices du jour

Essaie d’abord sans ouvrir les indices. Pour un exercice de code, crée ton propre fichier dans un dossier de travail et donne le même nom à la classe publique et au fichier. Les corrigés sont complets pour permettre une comparaison et une exécution indépendantes.

### Niveau 1 — comprendre et appliquer

#### Exercice 01 — Paramètre ou argument ?

Dans `static int carre(int n)` appelé par `carre(6)`, identifie le paramètre, l’argument et le type de retour.


<details>
<summary>💡 Voir un indice</summary>

La déclaration nomme les entrées ; l’appel fournit leurs valeurs.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le paramètre est n, de type int. L’argument est 6. Le type de retour est int.


</details>

#### Exercice 02 — Calculer un carré

Écris une méthode carre(int n) qui renvoie n × n. Affiche carre(7) depuis main. On se limite à de petits entiers.


<details>
<summary>💡 Voir un indice</summary>

Le calcul doit être renvoyé, puis affiché à l’appel.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

La méthode ne dépend pas du terminal. La limite aux petits entiers évite de prétendre que int peut contenir tous les carrés.


```java
public class Jour10Exercice02 {
    static int carre(int n) {
        return n * n;
    }
    public static void main(String[] args) {
        System.out.println(carre(7));
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour10Exercice02.java)

Résultat attendu :

```text
49
```

</details>

### Niveau 2 — corriger et consolider

#### Exercice 03 — Réparer le retour

Écris une méthode estPair(int n) de retour boolean. Elle doit renvoyer la réponse, pas seulement l’afficher. Affiche les résultats pour 4 et 5.


<details>
<summary>💡 Voir un indice</summary>

Une comparaison est déjà une expression boolean.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Retourner n % 2 == 0 traite tous les cas en une ligne.


```java
public class Jour10Exercice03 {
    static boolean estPair(int n) {
        return n % 2 == 0;
    }
    public static void main(String[] args) {
        System.out.println(estPair(4));
        System.out.println(estPair(5));
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour10Exercice03.java)

Résultat attendu :

```text
true
false
```

</details>

### Niveau 3 — résoudre un petit problème

#### Exercice 04 — Un maximum réutilisable

Écris max(int a, int b), puis réutilise-la pour trouver le maximum de trois valeurs −4, −9 et −2.


<details>
<summary>💡 Voir un indice</summary>

Le maximum de trois valeurs est le maximum du maximum des deux premières et de la troisième.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

On compose deux appels sans dupliquer les comparaisons. Les nombres négatifs sont traités normalement.


```java
public class Jour10Exercice04 {
    static int max(int a, int b) {
        if (a >= b) {
            return a;
        }
        return b;
    }
    public static void main(String[] args) {
        System.out.println(max(max(-4, -9), -2));
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour10Exercice04.java)

Résultat attendu :

```text
-2
```

</details>

<a id="bilan"></a>
## ✅ Avant de passer à la suite

- [ ] Je peux déclarer une méthode avec paramètres et retour.
- [ ] Je peux distinguer afficher et renvoyer une valeur.
- [ ] Je peux réutiliser une opération dans plusieurs situations.

- [ ] J’ai tenté les quatre exercices avant de comparer aux corrigés.
- [ ] Je peux expliquer une erreur rencontrée et la façon dont je l’ai corrigée.

[Noter ma progression](../PROGRESSION.md) · [Consulter le glossaire](../docs/GLOSSAIRE.md)

---

[← Jour 09](../09_Day_Arrays/09_arrays.md) | [📚 Sommaire](../README.md) | [Jour 11 →](../11_Day_Scope_and_Parameters/11_scope_and_parameters.md)

**☕ Une étape comprise vaut mieux qu’une journée cochée trop vite.**
