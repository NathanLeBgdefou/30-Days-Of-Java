<div align="center">
<h1>☕ 30 Days Of Java : jour 08</h1>
<h3>Boucles for et premiers algorithmes</h3>
<p>Français · Java 21 · Windows &amp; VS Code</p>
</div>

[← Jour 07](../07_Day_While_Loops/07_while_loops.md) | [📚 Sommaire](../README.md) | [Jour 09 →](../09_Day_Arrays/09_arrays.md)

![30 Days Of Java — apprendre à programmer](../images/30DaysOfJava_banner.svg)

![Progression : jour 8 sur 30](../images/progression-08.svg)

**Durée conseillée : 60 à 90 min.** Garde au moins une heure ; prends plus de temps pour coder si nécessaire.

📍 [Objectifs](#objectifs) · [Cours](#cours) · [Exemple](#exemple) · [Exercices](#exercices) · [Bilan](#bilan)

<a id="objectifs"></a>
## 🎯 Objectifs du jour

- Écrire une répétition contrôlée avec for.
- Repérer les erreurs de borne.
- Décomposer une recherche algorithmique simple.

> **Ton rythme :** 10 min de rappel et lecture, 15–20 min sur l’exemple, 30–45 min de pratique, 5 min de bilan. Les jours de projet demandent davantage.

<a id="cours"></a>
# 📘 Cours

## Regrouper le contrôle de la boucle

Une boucle `for` regroupe l'initialisation, la condition et la mise à jour : `for (int i = 0; i < 5; i++)`. L'initialisation s'effectue une fois. Java teste ensuite la condition, exécute le corps si elle est vraie, réalise la mise à jour, puis reteste.

Cette boucle parcourt 0, 1, 2, 3 et 4 : **cinq valeurs**, même si la dernière n'est pas 5. En Python, tu penserais à `range(5)`. Pour compter de 1 à 5 inclus, écris `int i = 1; i <= 5; i++`.

La variable `i` déclarée dans for n'existe que dans la portée de cette boucle. Pour garder un résultat, déclare une variable comme `somme` à l'extérieur.

## Un algorithme est une méthode de résolution

Avant le Java, écris en français les étapes nécessaires. Pour compter les multiples de 3 entre 1 et 10 : commencer le compteur à zéro ; visiter chaque entier ; tester son reste dans la division par 3 ; augmenter le compteur s'il est nul ; afficher le compteur après le parcours.

Cette séparation t'aide en études : une erreur peut venir de la logique des étapes ou de leur traduction dans le langage. Changer au hasard des symboles ne permet pas de les distinguer.

## Imbriquer des boucles

Une boucle peut contenir une autre boucle. Pour deux lignes de trois colonnes, la boucle extérieure choisit une ligne ; la boucle intérieure traite ses trois colonnes. Il y a donc six passages dans le corps intérieur.

```java fragment
for (int ligne = 1; ligne <= 2; ligne++) {
    for (int colonne = 1; colonne <= 3; colonne++) {
        System.out.print("*");
    }
    System.out.println();
}
```

`println()` sans argument termine la ligne après les trois étoiles. Placé dans la boucle intérieure, il produirait une colonne d'étoiles au lieu d'un rectangle.

## Tester un diviseur et s'arrêter

Pour savoir si un entier n ≥ 2 est premier, on cherche un diviseur entre 2 et n − 1. Si un tel diviseur existe, le nombre n'est pas premier. La première version peut tester tous les candidats : ce sera plus simple à comprendre, même si nous pourrons ensuite optimiser.

Il faut traiter à part les nombres inférieurs à 2. Un programme qui ne trouve aucun diviseur de 1 ne doit pas conclure que 1 est premier. C'est un exemple classique de cas limite qui échappe à une boucle pourtant syntaxiquement correcte.


<a id="exemple"></a>
## 🔎 Exemple complet, prêt à exécuter

[Ouvrir Jour08Exemple.java](./exemples/Jour08Exemple.java) · [Lire les exercices sans les réponses](./exercices/README.md)

Dans VS Code, ouvre le dossier `exemples` de cette journée, puis lance dans son terminal :

```powershell
java .\Jour08Exemple.java
```

```java
public class Jour08Exemple {
    public static void main(String[] args) {
        int multiples = 0;
        for (int n = 1; n <= 10; n++) {
            if (n % 3 == 0) {
                multiples++;
                System.out.println(n);
            }
        }
        System.out.println("Nombre de multiples : " + multiples);
    }
}
```

**Sortie du programme :**

```text
3
6
9
Nombre de multiples : 3
```

### Comprendre le déroulement

La boucle visite dix valeurs. Le if laisse passer seulement 3, 6 et 9. `multiples` ne représente donc pas le nombre de tours de boucle mais le nombre de valeurs répondant au critère. Retire provisoirement le if pour observer cette différence, puis restaure-le.


### ⚠️ Pièges fréquents

- `i <= 5` et `i < 5` ne parcourent pas le même nombre de valeurs.
- Une boucle intérieure recommence pour chaque tour de la boucle extérieure.
- Le fait qu’une boucle ne s’exécute pas peut être normal ; vérifie ce que cela implique pour le résultat.

<a id="exercices"></a>
## 💻 Exercices du jour

Essaie d’abord sans ouvrir les indices. Pour un exercice de code, crée ton propre fichier dans un dossier de travail et donne le même nom à la classe publique et au fichier. Les corrigés sont complets pour permettre une comparaison et une exécution indépendantes.

### Niveau 1 — comprendre et appliquer

#### Exercice 01 — Compter les passages

Combien de passages pour `for (int i = 2; i <= 10; i += 2)` ? Quelles valeurs prend i ?


<details>
<summary>💡 Voir un indice</summary>

Écris la suite avant de compter.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Cinq passages : 2, 4, 6, 8 et 10. La mise à jour ajoute 2 à chaque tour.


</details>

#### Exercice 02 — Table de multiplication

Affiche la table de 7, de 1 × 7 à 10 × 7, au format `1 x 7 = 7`.


<details>
<summary>💡 Voir un indice</summary>

Le facteur variable va de 1 à 10 inclus.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

On calcule le produit entre parenthèses avant de le joindre au texte.


```java
public class Jour08Exercice02 {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(i + " x 7 = " + (i * 7));
        }
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour08Exercice02.java)

Résultat attendu :

```text
1 x 7 = 7
2 x 7 = 14
3 x 7 = 21
4 x 7 = 28
5 x 7 = 35
6 x 7 = 42
7 x 7 = 49
8 x 7 = 56
9 x 7 = 63
10 x 7 = 70
```

</details>

### Niveau 2 — corriger et consolider

#### Exercice 03 — Un rectangle lisible

Affiche exactement trois lignes de quatre étoiles avec deux boucles for.


<details>
<summary>💡 Voir un indice</summary>

Le retour à la ligne se fait après la boucle des colonnes.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

La boucle extérieure gère trois lignes. Chacune contient quatre affichages sans retour à la ligne.


```java
public class Jour08Exercice03 {
    public static void main(String[] args) {
        for (int ligne = 0; ligne < 3; ligne++) {
            for (int colonne = 0; colonne < 4; colonne++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour08Exercice03.java)

Résultat attendu :

```text
****
****
****
```

</details>

### Niveau 3 — résoudre un petit problème

#### Exercice 04 — Reconnaître un nombre premier

Pour n = 29, affiche true si n est premier, false sinon. Traite explicitement n < 2. Teste aussi 1, 2 et 9.


<details>
<summary>💡 Voir un indice</summary>

Un diviseur d donne n % d == 0. Dès qu’on en trouve un, on peut arrêter.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

On part de l’hypothèse « premier » seulement pour n ≥ 2. Un diviseur suffit à l’invalider. Pour 2, aucun candidat n’est testé et l’hypothèse reste vraie.


```java
public class Jour08Exercice04 {
    public static void main(String[] args) {
        int n = 29;
        boolean premier = n >= 2;
        for (int d = 2; d < n; d++) {
            if (n % d == 0) {
                premier = false;
                break;
            }
        }
        System.out.println(premier);
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour08Exercice04.java)

Résultat attendu :

```text
true
```

</details>

<a id="bilan"></a>
## ✅ Avant de passer à la suite

- [ ] Je peux écrire une répétition contrôlée avec for.
- [ ] Je peux repérer les erreurs de borne.
- [ ] Je peux décomposer une recherche algorithmique simple.

- [ ] J’ai tenté les quatre exercices avant de comparer aux corrigés.
- [ ] Je peux expliquer une erreur rencontrée et la façon dont je l’ai corrigée.

[Noter ma progression](../PROGRESSION.md) · [Consulter le glossaire](../docs/GLOSSAIRE.md)

---

[← Jour 07](../07_Day_While_Loops/07_while_loops.md) | [📚 Sommaire](../README.md) | [Jour 09 →](../09_Day_Arrays/09_arrays.md)

**☕ Une étape comprise vaut mieux qu’une journée cochée trop vite.**
