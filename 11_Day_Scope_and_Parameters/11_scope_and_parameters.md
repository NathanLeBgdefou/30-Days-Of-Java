<div align="center">
<h1>☕ 30 Days Of Java : jour 11</h1>
<h3>Portée, paramètres et récursion</h3>
<p>Français · Java 21 · Windows &amp; VS Code</p>
</div>

[← Jour 10](../10_Day_Methods/10_methods.md) | [📚 Sommaire](../README.md) | [Jour 12 →](../12_Day_Debugging/12_debugging.md)

![30 Days Of Java — apprendre à programmer](../images/30DaysOfJava_banner.svg)

![Progression : jour 11 sur 30](../images/progression-11.svg)

**Durée conseillée : 60 à 90 min.** Garde au moins une heure ; prends plus de temps pour coder si nécessaire.

📍 [Objectifs](#objectifs) · [Cours](#cours) · [Exemple](#exemple) · [Exercices](#exercices) · [Bilan](#bilan)

<a id="objectifs"></a>
## 🎯 Objectifs du jour

- Comprendre la portée des variables locales.
- Expliquer le passage par valeur, y compris pour les objets.
- Lire une surcharge et une récursion simple.

> **Ton rythme :** 10 min de rappel et lecture, 15–20 min sur l’exemple, 30–45 min de pratique, 5 min de bilan. Les jours de projet demandent davantage.

<a id="cours"></a>
# 📘 Cours

## Une variable appartient à une portée

Une variable locale existe dans le bloc où elle est déclarée, à partir de sa déclaration. Une variable créée dans un if ne devient pas automatiquement disponible après le if. Deux appels à une méthode ont chacun leurs propres paramètres et variables locales.

Cette séparation est utile : une méthode peut utiliser `resultat` sans écraser une variable locale du même nom dans main. Il ne faut pas confondre ce nom identique avec un partage de mémoire.

## Java passe toujours les arguments par valeur

Pour un int, la valeur copiée est le nombre. Si une méthode reçoit n et exécute `n = 99;`, elle réaffecte son paramètre local. La variable de l'appelant n'est pas modifiée.

Pour un tableau ou un autre objet, la valeur copiée est une **référence**. L'appelant et la méthode peuvent donc accéder au même objet. La méthode peut modifier une case du tableau partagé. En revanche, réaffecter son paramètre à un nouveau tableau ne remplace pas la variable de l'appelant.

| Action dans la méthode | Effet visible pour l'appelant |
| --- | --- |
| Réaffecter un paramètre int | Aucun changement de son int. |
| Modifier une case d'un tableau reçu | La case de l'objet partagé change. |
| Réaffecter le paramètre tableau | Sa variable continue à désigner l'ancien tableau. |

Dire « les objets sont passés par référence » est donc imprécis en Java : c'est **une valeur de référence qui est copiée**. Cette distinction est souvent demandée en contrôle.

## Surcharger un nom de méthode

Deux méthodes peuvent porter le même nom si leurs listes de paramètres diffèrent : `addition(int a, int b)` et `addition(double a, double b)`. C'est une **surcharge**. Le compilateur choisit selon les types des arguments. Le type de retour seul ne permet pas de distinguer deux surcharges.

La surcharge n'est pas la redéfinition d'une méthode héritée, que nous verrons au jour 17. Pour garder un appel lisible, les surcharges d'un même nom devraient représenter la même opération générale.

## Une méthode qui s'appelle elle-même

La **récursion** consiste à résoudre un problème à l'aide d'une version plus petite du même problème. Pour la somme de 1 à n : somme(0) = 0 ; pour n > 0, somme(n) = n + somme(n − 1).

Il faut un **cas de base** qui s'arrête, et une progression vers ce cas. Sans cela, les appels s'empilent jusqu'à une StackOverflowError. Chaque appel conserve son propre n ; pour somme(3), on descend jusqu'à somme(0), puis les résultats remontent : 0, 1, 3, 6.

On limite cet exercice aux petits entiers naturels. Une boucle est plus adaptée pour additionner de très longues suites sans remplir la pile d'appels. La récursion est ici un outil de compréhension, pas un remplacement obligatoire des boucles.


<a id="exemple"></a>
## 🔎 Exemple complet, prêt à exécuter

[Ouvrir Jour11Exemple.java](./exemples/Jour11Exemple.java) · [Lire les exercices sans les réponses](./exercices/README.md)

Dans VS Code, ouvre le dossier `exemples` de cette journée, puis lance dans son terminal :

```powershell
java .\Jour11Exemple.java
```

```java
import java.util.Arrays;
public class Jour11Exemple {
    static void modifierNombre(int n) {
        n = 99;
    }
    static void modifierCase(int[] nombres) {
        nombres[0] = 99;
    }
    static void remplacerTableau(int[] nombres) {
        nombres = new int[] {7, 8};
    }
    public static void main(String[] args) {
        int age = 19;
        int[] notes = {12, 15};
        modifierNombre(age);
        modifierCase(notes);
        remplacerTableau(notes);
        System.out.println(age);
        System.out.println(Arrays.toString(notes));
    }
}
```

**Sortie du programme :**

```text
19
[99, 15]
```

### Comprendre le déroulement

age reste 19 car la méthode a changé une copie du nombre. La première case vaut maintenant 99 car le tableau est partagé. Le nouveau tableau {7, 8} n'a été associé qu'au paramètre local de remplacerTableau ; il n'a pas remplacé la variable notes de main. Dessine les références avant chaque appel si ce point te paraît abstrait.


### ⚠️ Pièges fréquents

- Changer une référence locale et modifier l’objet qu’elle désigne sont deux actions différentes.
- Le type de retour seul ne crée pas une surcharge valide.
- Une récursion doit avancer vers son cas de base, avec un domaine d’entrée défini.

<a id="exercices"></a>
## 💻 Exercices du jour

Essaie d’abord sans ouvrir les indices. Pour un exercice de code, crée ton propre fichier dans un dossier de travail et donne le même nom à la classe publique et au fichier. Les corrigés sont complets pour permettre une comparaison et une exécution indépendantes.

### Niveau 1 — comprendre et appliquer

#### Exercice 01 — Prédire un appel

Une méthode reçoit int x = 5 et exécute x++. La variable passée par main vaut-elle maintenant 6 ?


<details>
<summary>💡 Voir un indice</summary>

Le paramètre contient une copie de la valeur entière.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Non, elle reste à 5. Pour obtenir le nouveau nombre, la méthode peut le renvoyer et l’appelant peut réaffecter sa variable.


</details>

#### Exercice 02 — Renvoyer plutôt que prétendre modifier

Écris incrementer(int n), puis réaffecte compteur à son résultat pour passer de 5 à 6.


<details>
<summary>💡 Voir un indice</summary>

La mise à jour de compteur doit avoir lieu dans main.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le return rend le changement explicite.


```java
public class Jour11Exercice02 {
    static int incrementer(int n) {
        return n + 1;
    }
    public static void main(String[] args) {
        int compteur = 5;
        compteur = incrementer(compteur);
        System.out.println(compteur);
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour11Exercice02.java)

Résultat attendu :

```text
6
```

</details>

### Niveau 2 — corriger et consolider

#### Exercice 03 — Deux surcharges

Écris addition pour deux int et pour deux double. Affiche addition(2, 3) puis addition(2.5, 3.0).


<details>
<summary>💡 Voir un indice</summary>

Les listes de paramètres doivent différer par leurs types.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

L’appel entier choisit la première méthode ; l’appel décimal choisit la seconde.


```java
public class Jour11Exercice03 {
    static int addition(int a, int b) {
        return a + b;
    }
    static double addition(double a, double b) {
        return a + b;
    }
    public static void main(String[] args) {
        System.out.println(addition(2, 3));
        System.out.println(addition(2.5, 3.0));
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour11Exercice03.java)

Résultat attendu :

```text
5
5.5
```

</details>

### Niveau 3 — résoudre un petit problème

#### Exercice 04 — Ta première récursion

Pour un petit entier n ≥ 0, écris somme(n) récursive qui renvoie 1 + … + n. Affiche somme(5). Dessine les appels pour somme(3).


<details>
<summary>💡 Voir un indice</summary>

Traite n == 0 avant de rappeler somme(n - 1).

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le résultat de somme(5) est 15. Pour 3, la remontée calcule 1 + 0, puis 2 + 1, puis 3 + 3. Le contrat de cet exercice exclut les entiers négatifs.


```java
public class Jour11Exercice04 {
    static int somme(int n) {
        if (n == 0) {
            return 0;
        }
        return n + somme(n - 1);
    }
    public static void main(String[] args) {
        System.out.println(somme(5));
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour11Exercice04.java)

Résultat attendu :

```text
15
```

</details>

<a id="bilan"></a>
## ✅ Avant de passer à la suite

- [ ] Je peux comprendre la portée des variables locales.
- [ ] Je peux expliquer le passage par valeur, y compris pour les objets.
- [ ] Je peux lire une surcharge et une récursion simple.

- [ ] J’ai tenté les quatre exercices avant de comparer aux corrigés.
- [ ] Je peux expliquer une erreur rencontrée et la façon dont je l’ai corrigée.

[Noter ma progression](../PROGRESSION.md) · [Consulter le glossaire](../docs/GLOSSAIRE.md)

---

[← Jour 10](../10_Day_Methods/10_methods.md) | [📚 Sommaire](../README.md) | [Jour 12 →](../12_Day_Debugging/12_debugging.md)

**☕ Une étape comprise vaut mieux qu’une journée cochée trop vite.**
