<div align="center">
<h1>☕ 30 Days Of Java : jour 03</h1>
<h3>Opérateurs et conversions</h3>
<p>Français · Java 21 · Windows &amp; VS Code</p>
</div>

[← Jour 02](../02_Day_Variables_and_Types/02_variables_and_types.md) | [📚 Sommaire](../README.md) | [Jour 04 →](../04_Day_Strings/04_strings.md)

![30 Days Of Java — apprendre à programmer](../images/30DaysOfJava_banner.svg)

![Progression : jour 3 sur 30](../images/progression-03.svg)

**Durée conseillée : 60 à 90 min.** Garde au moins une heure ; prends plus de temps pour coder si nécessaire.

📍 [Objectifs](#objectifs) · [Cours](#cours) · [Exemple](#exemple) · [Exercices](#exercices) · [Bilan](#bilan)

<a id="objectifs"></a>
## 🎯 Objectifs du jour

- Comprendre la division entière et le reste.
- Maîtriser les priorités de calcul.
- Convertir un nombre sans oublier la perte de précision.

> **Ton rythme :** 10 min de rappel et lecture, 15–20 min sur l’exemple, 30–45 min de pratique, 5 min de bilan. Les jours de projet demandent davantage.

<a id="cours"></a>
# 📘 Cours

## Calculer avec les bons types

Java propose `+`, `-`, `*`, `/` et `%`. Les quatre premiers servent à l'addition, la soustraction, la multiplication et la division. `%` donne le reste d'une division entière : `17 % 5` vaut 2, car 17 = 3 × 5 + 2.

Le piège le plus fréquent en venant de Python est la division : **si les deux opérandes sont entiers, `/` réalise une division entière**. `7 / 2` vaut 3. Avec au moins un `double`, `7 / 2.0` vaut 3.5. La division entière tronque vers zéro : pour les négatifs, elle ne se comporte donc pas toujours comme `//` en Python.

Le type de la variable qui reçoit le résultat ne change pas rétroactivement le calcul. `double resultat = 7 / 2;` stocke 3.0, parce que 7 / 2 a déjà été calculé comme une division entière.

## Convertir avant le calcul

`(double) somme / nombre` convertit `somme` en décimal **avant** la division. Cette conversion explicite s'appelle un **cast**. Inversement, `(int) 13.9` donne 13 : ce n'est pas un arrondi au plus proche.

Certaines conversions sont automatiques : un `int` peut devenir un `double`. Pour les entiers plus grands, attention : un `long` ne peut pas toujours être représenté exactement par un `double`. Élargir le type n'est pas synonyme de précision illimitée.

## Priorité et parenthèses

La multiplication et la division sont prioritaires sur l'addition. Pour calculer une moyenne de deux notes, écris `(note1 + note2) / 2.0`. Sans parenthèses, `note1 + note2 / 2.0` divise seulement la deuxième note.

Avec du texte, `+` peut concaténer : il assemble une représentation textuelle. `"Total : " + 2 + 3` construit `Total : 23`, de gauche à droite. `"Total : " + (2 + 3)` construit `Total : 5`. Les parenthèses ne sont donc pas seulement décoratives.

| Expression | Valeur |
| --- | --- |
| `8 / 3` | `2` |
| `8 / 3.0` | Environ `2.6666666666666665` |
| `8 % 3` | `2` |
| `(int) 2.9` | `2` |
| `2 + 3 * 4` | `14` |
| `(2 + 3) * 4` | `20` |

## Des raccourcis raisonnables

`compteur += 2;` est ici un raccourci de `compteur = compteur + 2;`. `compteur++;` ajoute 1. On utilise ces raccourcis seuls sur une ligne pour garder les premiers programmes lisibles. Les détails de `++` dans une expression peuvent attendre.

Une division entière par zéro déclenche une erreur pendant l'exécution. Avec des nombres flottants, Java peut produire une valeur spéciale comme `Infinity` ou `NaN`. Dans les deux cas, un dénominateur nul mérite une règle métier explicite ; nous apprendrons à le vérifier avec `if`.


<a id="exemple"></a>
## 🔎 Exemple complet, prêt à exécuter

[Ouvrir Jour03Exemple.java](./exemples/Jour03Exemple.java) · [Lire les exercices sans les réponses](./exercices/README.md)

Dans VS Code, ouvre le dossier `exemples` de cette journée, puis lance dans son terminal :

```powershell
java .\Jour03Exemple.java
```

```java
public class Jour03Exemple {
    public static void main(String[] args) {
        int somme = 37;
        int nombre = 3;
        double moyenne = (double) somme / nombre;
        int minutes = 135;
        System.out.println("Division entière : " + somme / nombre);
        System.out.println("Moyenne : " + moyenne);
        System.out.println(minutes / 60 + " h " + minutes % 60 + " min");
    }
}
```

**Sortie du programme :**

```text
Division entière : 12
Moyenne : 12.333333333333334
2 h 15 min
```

### Comprendre le déroulement

La première division perd le reste fractionnaire car ses deux opérandes sont des `int`. Le cast préserve une moyenne décimale dans le second calcul. Pour convertir des minutes, la division entière devient au contraire utile : elle donne le nombre d'heures complètes ; `%` fournit les minutes restantes. Le choix de l'opération dépend du résultat que l'on veut représenter.


### ⚠️ Pièges fréquents

- `(double) (7 / 2)` arrive trop tard : le résultat entier est déjà 3.
- Un cast vers int tronque ; `Math.round` répond à un autre besoin.
- Un int peut déborder sa capacité sans lever automatiquement d’exception.

<a id="exercices"></a>
## 💻 Exercices du jour

Essaie d’abord sans ouvrir les indices. Pour un exercice de code, crée ton propre fichier dans un dossier de travail et donne le même nom à la classe publique et au fichier. Les corrigés sont complets pour permettre une comparaison et une exécution indépendantes.

### Niveau 1 — comprendre et appliquer

#### Exercice 01 — Prédire quatre valeurs

Donne les résultats de `9 / 4`, `9 % 4`, `9 / 4.0` et `(int) 9.8`.


<details>
<summary>💡 Voir un indice</summary>

Sépare quotient entier, reste, quotient décimal et troncature.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Les valeurs sont 2, 1, 2.25 et 9. Le cast final ne cherche pas l’entier le plus proche.


</details>

#### Exercice 02 — Une moyenne juste

Calcule la moyenne de 11, 14 et 16. Affiche une valeur décimale.


<details>
<summary>💡 Voir un indice</summary>

La somme doit être divisée par 3.0 ou convertie avant la division.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le dénominateur décimal force la division flottante.


```java
public class Jour03Exercice02 {
    public static void main(String[] args) {
        int somme = 11 + 14 + 16;
        double moyenne = somme / 3.0;
        System.out.println(moyenne);
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour03Exercice02.java)

Résultat attendu :

```text
13.666666666666666
```

</details>

### Niveau 2 — corriger et consolider

#### Exercice 03 — Réparer un affichage

Un programme affiche `Somme : 1215` avec `"Somme : " + 12 + 15`. Corrige-le pour calculer la somme.


<details>
<summary>💡 Voir un indice</summary>

Une addition à faire avant le texte se groupe entre parenthèses.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

La première concaténation transformait toute la suite en texte. Les parenthèses font calculer 27 avant son affichage.


```java
public class Jour03Exercice03 {
    public static void main(String[] args) {
        System.out.println("Somme : " + (12 + 15));
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour03Exercice03.java)

Résultat attendu :

```text
Somme : 27
```

</details>

### Niveau 3 — résoudre un petit problème

#### Exercice 04 — Décomposer une durée

Transforme 3671 secondes en heures, minutes restantes et secondes restantes. Attendu : `1 h 1 min 11 s`.


<details>
<summary>💡 Voir un indice</summary>

Une heure contient 3600 secondes ; récupère ensuite le reste avant de calculer les minutes.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

On retire implicitement les heures avec `% 3600`, puis on décompose le reste. Utiliser directement `total / 60` donnerait les minutes totales, pas celles restant après les heures.


```java
public class Jour03Exercice04 {
    public static void main(String[] args) {
        int total = 3671;
        int heures = total / 3600;
        int minutes = (total % 3600) / 60;
        int secondes = total % 60;
        System.out.println(heures + " h " + minutes + " min " + secondes + " s");
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour03Exercice04.java)

Résultat attendu :

```text
1 h 1 min 11 s
```

</details>

<a id="bilan"></a>
## ✅ Avant de passer à la suite

- [ ] Je peux comprendre la division entière et le reste.
- [ ] Je peux maîtriser les priorités de calcul.
- [ ] Je peux convertir un nombre sans oublier la perte de précision.

- [ ] J’ai tenté les quatre exercices avant de comparer aux corrigés.
- [ ] Je peux expliquer une erreur rencontrée et la façon dont je l’ai corrigée.

[Noter ma progression](../PROGRESSION.md) · [Consulter le glossaire](../docs/GLOSSAIRE.md)

---

[← Jour 02](../02_Day_Variables_and_Types/02_variables_and_types.md) | [📚 Sommaire](../README.md) | [Jour 04 →](../04_Day_Strings/04_strings.md)

**☕ Une étape comprise vaut mieux qu’une journée cochée trop vite.**
