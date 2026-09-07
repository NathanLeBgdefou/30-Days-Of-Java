<div align="center">
<h1>☕ 30 Days Of Java : jour 06</h1>
<h3>Conditions et choix</h3>
<p>Français · Java 21 · Windows &amp; VS Code</p>
</div>

[← Jour 05](../05_Day_User_Input/05_user_input.md) | [📚 Sommaire](../README.md) | [Jour 07 →](../07_Day_While_Loops/07_while_loops.md)

![30 Days Of Java — apprendre à programmer](../images/30DaysOfJava_banner.svg)

![Progression : jour 6 sur 30](../images/progression-06.svg)

**Durée conseillée : 60 à 90 min.** Garde au moins une heure ; prends plus de temps pour coder si nécessaire.

📍 [Objectifs](#objectifs) · [Cours](#cours) · [Exemple](#exemple) · [Exercices](#exercices) · [Bilan](#bilan)

<a id="objectifs"></a>
## 🎯 Objectifs du jour

- Exprimer une condition booléenne.
- Choisir entre if, else if et else.
- Combiner les conditions sans oublier les bornes.

> **Ton rythme :** 10 min de rappel et lecture, 15–20 min sur l’exemple, 30–45 min de pratique, 5 min de bilan. Les jours de projet demandent davantage.

<a id="cours"></a>
# 📘 Cours

## Exécuter seulement dans certains cas

Un `if` exécute son bloc si une expression vaut `true`. La condition est entre parenthèses, le bloc entre accolades. Le `else` décrit le cas contraire. Avec `else if`, on teste une autre condition uniquement si les précédentes ont échoué.

| Opérateur | Sens | Exemple |
| --- | --- | --- |
| `==` | Égalité de valeurs primitives | `note == 10` |
| `!=` | Différence | `absences != 0` |
| `<`, `<=` | Inférieur, inférieur ou égal | `note <= 20` |
| `>`, `>=` | Supérieur, supérieur ou égal | `note >= 10` |
| `&&` | ET logique | `note >= 0 && note <= 20` |
| `||` | OU logique | `jour == 6 || jour == 7` |
| `!` | Négation | `!termine` |

Pour le texte, conserve `.equals` : le `==` du tableau ne devient pas un test de contenu des String.

## Traduire une règle, puis la tester

Pour une note valide entre 0 et 20 inclus, écris `note >= 0 && note <= 20`. L'écriture mathématique `0 <= note <= 20` ne fonctionne pas en Java. Chaque comparaison doit produire un booléen, que l'on combine ensuite.

Pour reconnaître une note invalide, on utilise le OU : `note < 0 || note > 20`. Avec ET, il faudrait que la note soit à la fois négative et supérieure à 20, ce qui est impossible.

Une chaîne de `if / else if / else` choisit **au plus une branche**. Plusieurs `if` séparés peuvent en exécuter plusieurs. Pour attribuer une seule mention, commence par le seuil le plus élevé : tester d'abord `note >= 10` absorberait aussi les notes 16, 18 ou 20.

## Court-circuit

`&&` n'évalue pas sa partie droite si la partie gauche est déjà fausse. `||` ne l'évalue pas si la partie gauche est déjà vraie. C'est le **court-circuit**. Ainsi, `texte.length() > 0 && texte.charAt(0) == 'J'` n'appelle pas `charAt(0)` sur un texte vide. Le texte est ici supposé non null.

## Plusieurs valeurs connues : switch

Quand on compare une même valeur à plusieurs cas, un `switch` peut être lisible. Cette forme à flèches évite de continuer accidentellement dans le cas suivant :

```java fragment
int choix = 2;
switch (choix) {
    case 1 -> System.out.println("Ajouter");
    case 2 -> System.out.println("Afficher");
    default -> System.out.println("Choix inconnu");
}
```

Le mot `default` couvre les valeurs non prévues. Nous garderons `if` pour les intervalles et `switch` pour des choix discrets. Dans les deux cas, pense aux limites : 0, 10 et 20 doivent être testés explicitement pour une note.


<a id="exemple"></a>
## 🔎 Exemple complet, prêt à exécuter

[Ouvrir Jour06Exemple.java](./exemples/Jour06Exemple.java) · [Lire les exercices sans les réponses](./exercices/README.md)

Dans VS Code, ouvre le dossier `exemples` de cette journée, puis lance dans son terminal :

```powershell
java .\Jour06Exemple.java
```

```java
public class Jour06Exemple {
    public static void main(String[] args) {
        double note = 14;
        if (note < 0 || note > 20) {
            System.out.println("Note invalide");
        } else if (note >= 16) {
            System.out.println("Très bien");
        } else if (note >= 14) {
            System.out.println("Bien");
        } else if (note >= 10) {
            System.out.println("Validé");
        } else {
            System.out.println("À retravailler");
        }
    }
}
```

**Sortie du programme :**

```text
Bien
```

### Comprendre le déroulement

Avec 14, le contrôle d'invalidité échoue, puis le seuil 16 échoue. Le seuil 14 réussit : `Bien` est affiché et les branches suivantes sont ignorées. Remplace successivement la note par −1, 0, 9.5, 10, 16, 20 et 21. Tu vérifies ainsi les frontières des règles, pas seulement un cas confortable.


### ⚠️ Pièges fréquents

- `=` affecte ; `==` compare. Une condition attend un boolean.
- N’ajoute pas de point-virgule juste après `if (...)` : il formerait une instruction vide.
- Utilise des accolades même pour une instruction : les ajouts ultérieurs resteront clairs.

<a id="exercices"></a>
## 💻 Exercices du jour

Essaie d’abord sans ouvrir les indices. Pour un exercice de code, crée ton propre fichier dans un dossier de travail et donne le même nom à la classe publique et au fichier. Les corrigés sont complets pour permettre une comparaison et une exécution indépendantes.

### Niveau 1 — comprendre et appliquer

#### Exercice 01 — ET ou OU ?

Pour une note entière, quelle condition détecte une valeur hors de [0, 20] ? Explique pourquoi `note < 0 && note > 20` est fausse pour tout entier.


<details>
<summary>💡 Voir un indice</summary>

Une valeur hors intervalle peut être trop petite OU trop grande.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

La bonne condition est `note < 0 || note > 20`. Une même valeur ne peut pas être simultanément inférieure à zéro et supérieure à vingt.


</details>

#### Exercice 02 — Pair ou impair

Déclare un entier n = 17 et affiche `Pair` ou `Impair`. Teste aussi 0 et 18.


<details>
<summary>💡 Voir un indice</summary>

Un entier pair a un reste nul lorsqu’on le divise par 2.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le test fonctionne aussi pour zéro et les entiers négatifs.


```java
public class Jour06Exercice02 {
    public static void main(String[] args) {
        int n = 17;
        if (n % 2 == 0) {
            System.out.println("Pair");
        } else {
            System.out.println("Impair");
        }
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour06Exercice02.java)

Résultat attendu :

```text
Impair
```

</details>

### Niveau 2 — corriger et consolider

#### Exercice 03 — Remettre les seuils dans l’ordre

On teste d’abord note >= 10, puis note >= 16 dans un else if. Pourquoi 18 n’obtient-il jamais `Très bien` ? Corrige pour afficher `Très bien` dès 16, `Validé` dès 10, sinon `À retravailler`.


<details>
<summary>💡 Voir un indice</summary>

La première condition vraie arrête la chaîne.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le seuil le plus exigeant passe en premier.


```java
public class Jour06Exercice03 {
    public static void main(String[] args) {
        int note = 18;
        if (note >= 16) {
            System.out.println("Très bien");
        } else if (note >= 10) {
            System.out.println("Validé");
        } else {
            System.out.println("À retravailler");
        }
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour06Exercice03.java)

Résultat attendu :

```text
Très bien
```

</details>

### Niveau 3 — résoudre un petit problème

#### Exercice 04 — Une règle de validation

Une UE est validée si la moyenne est au moins 10 ET s’il y a au plus 3 absences. Avec 12.5 et 4, affiche `Non validée`. Teste ensuite 10 et 3.


<details>
<summary>💡 Voir un indice</summary>

Transforme séparément les deux critères, puis combine-les.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Les bornes sont incluses : 10 et 3 valident l’UE. Il s’agit d’une règle fictive d’exercice, pas du règlement de ton établissement.


```java
public class Jour06Exercice04 {
    public static void main(String[] args) {
        double moyenne = 12.5;
        int absences = 4;
        boolean validee = moyenne >= 10 && absences <= 3;
        if (validee) {
            System.out.println("Validée");
        } else {
            System.out.println("Non validée");
        }
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour06Exercice04.java)

Résultat attendu :

```text
Non validée
```

</details>

<a id="bilan"></a>
## ✅ Avant de passer à la suite

- [ ] Je peux exprimer une condition booléenne.
- [ ] Je peux choisir entre if, else if et else.
- [ ] Je peux combiner les conditions sans oublier les bornes.

- [ ] J’ai tenté les quatre exercices avant de comparer aux corrigés.
- [ ] Je peux expliquer une erreur rencontrée et la façon dont je l’ai corrigée.

[Noter ma progression](../PROGRESSION.md) · [Consulter le glossaire](../docs/GLOSSAIRE.md)

---

[← Jour 05](../05_Day_User_Input/05_user_input.md) | [📚 Sommaire](../README.md) | [Jour 07 →](../07_Day_While_Loops/07_while_loops.md)

**☕ Une étape comprise vaut mieux qu’une journée cochée trop vite.**
