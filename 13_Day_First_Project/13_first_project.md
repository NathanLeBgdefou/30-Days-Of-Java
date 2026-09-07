<div align="center">
<h1>☕ 30 Days Of Java : jour 13</h1>
<h3>Mini-projet : bilan de notes</h3>
<p>Français · Java 21 · Windows &amp; VS Code</p>
</div>

[← Jour 12](../12_Day_Debugging/12_debugging.md) | [📚 Sommaire](../README.md) | [Jour 14 →](../14_Day_Classes_and_Objects/14_classes_and_objects.md)

![30 Days Of Java — apprendre à programmer](../images/30DaysOfJava_banner.svg)

![Progression : jour 13 sur 30](../images/progression-13.svg)

**Durée conseillée : 90 à 120 min.** Garde au moins une heure ; prends plus de temps pour coder si nécessaire.

📍 [Objectifs](#objectifs) · [Cours](#cours) · [Exemple](#exemple) · [Exercices](#exercices) · [Bilan](#bilan)

<a id="objectifs"></a>
## 🎯 Objectifs du jour

- Combiner tableaux, conditions, boucles et méthodes.
- Lire un cahier des charges avant de coder.
- Traiter les jeux de données vides et les seuils.

> **Ton rythme :** 10 min de rappel et lecture, 15–20 min sur l’exemple, 30–45 min de pratique, 5 min de bilan. Les jours de projet demandent davantage.

<a id="cours"></a>
# 📘 Cours

## Le besoin

Tu veux afficher un bilan à partir de notes entières sur 20. Ce premier projet reprend uniquement ce que tu connais. Il ne lit pas encore de fichier et n'a pas d'interface graphique. Son intérêt est de transformer un besoin en plusieurs opérations simples.

**Cahier des charges :** pour un tableau de notes valides, afficher le nombre de notes, leur moyenne décimale, la meilleure note et le nombre de notes supérieures ou égales à 10. Si le tableau est vide, afficher `Aucune note` et ne pas calculer de moyenne ni de maximum.

Les notes de l'exemple sont valides par contrat. Une amélioration des exercices ajoutera un contrôle du domaine [0, 20]. La saisie robuste au clavier viendra après les exceptions.

## Concevoir avant d'écrire

| Méthode | Entrée | Retour | Précondition |
| --- | --- | --- | --- |
| `moyenne` | Tableau de int | double | Tableau non vide. |
| `maximum` | Tableau de int | int | Tableau non vide. |
| `compterValidees` | Tableau de int | int | Tableau éventuellement vide. |
| `afficherBilan` | Tableau de int | void | Tableau non null. |

Une **précondition** est une exigence que l'appelant doit respecter. `afficherBilan` vérifie la taille avant d'appeler les deux méthodes qui exigent un élément. Le return dans le cas vide empêche la suite de s'exécuter : c'est une **sortie anticipée**.

## Pourquoi plusieurs parcours ?

On pourrait calculer tous les indicateurs en une seule boucle. Ici, on privilégie des méthodes courtes, chacune chargée d'un calcul. Pour quelques notes, trois parcours sont acceptables et faciles à vérifier. Au jour 25, nous parlerons du coût des algorithmes ; optimiser avant de comprendre le besoin n'aide pas toujours.

## Vérifier avec des jeux choisis

Pour {12, 8, 15, 10}, la somme vaut 45, donc la moyenne vaut 11.25, le maximum 15 et le nombre de notes validées 3. Pour {7}, moyenne et maximum valent 7, et le compteur vaut 0. Pour {}, seul le message d'absence doit apparaître.

Écris ces attentes avant de lancer le programme. Elles deviennent ton contrat de vérification. Si tu changes la définition de « validée » dans une méthode, tu dois comprendre quels tests et quels messages sont concernés.

## Ton travail aujourd'hui

Lis le cahier des charges, puis essaie d'écrire au moins deux méthodes sans regarder l'exemple. Compare ensuite les choix : as-tu séparé calcul et affichage ? Traité le cas vide au bon endroit ? Gardé la division décimale ? Le but n'est pas de recopier les mêmes noms de variables mais de pouvoir expliquer chaque décision.

Compte plutôt 90 minutes pour cette journée. Si une méthode te bloque, reviens au jour correspondant et écris un exemple minuscule avant de la réintégrer.


<a id="exemple"></a>
## 🔎 Exemple complet, prêt à exécuter

[Ouvrir Jour13Exemple.java](./exemples/Jour13Exemple.java) · [Lire les exercices sans les réponses](./exercices/README.md)

Dans VS Code, ouvre le dossier `exemples` de cette journée, puis lance dans son terminal :

```powershell
java .\Jour13Exemple.java
```

```java
public class Jour13Exemple {
    static double moyenne(int[] notes) {
        int somme = 0;
        for (int note : notes) somme += note;
        return (double) somme / notes.length;
    }
    static int maximum(int[] notes) {
        int max = notes[0];
        for (int note : notes) {
            if (note > max) max = note;
        }
        return max;
    }
    static int compterValidees(int[] notes) {
        int total = 0;
        for (int note : notes) {
            if (note >= 10) total++;
        }
        return total;
    }
    static void afficherBilan(int[] notes) {
        if (notes.length == 0) {
            System.out.println("Aucune note");
            return;
        }
        System.out.println("Nombre : " + notes.length);
        System.out.println("Moyenne : " + moyenne(notes));
        System.out.println("Maximum : " + maximum(notes));
        System.out.println("Validées : " + compterValidees(notes));
    }
    public static void main(String[] args) {
        afficherBilan(new int[] {12, 8, 15, 10});
    }
}
```

**Sortie du programme :**

```text
Nombre : 4
Moyenne : 11.25
Maximum : 15
Validées : 3
```

### Comprendre le déroulement

Main choisit les données et délègue le travail. afficherBilan organise les appels. Les trois méthodes de calcul ne connaissent ni le texte des messages ni le terminal. Tu pourrais donc réutiliser moyenne dans un autre programme sans emporter tout l'affichage. Le style compact de quelques boucles tient sur une ligne ; tu peux ajouter des accolades pour rester à l'aise.


### ⚠️ Pièges fréquents

- Ne lis pas notes[0] avant d’avoir vérifié qu’un tableau contient au moins un élément.
- Le contrat « notes valides » doit être explicite ; il ne valide pas les données magiquement.
- Une moyenne entière attendue ne permet pas à elle seule de détecter une division entière accidentelle.

<a id="exercices"></a>
## 💻 Exercices du jour

Essaie d’abord sans ouvrir les indices. Pour un exercice de code, crée ton propre fichier dans un dossier de travail et donne le même nom à la classe publique et au fichier. Les corrigés sont complets pour permettre une comparaison et une exécution indépendantes.

### Niveau 1 — comprendre et appliquer

#### Exercice 01 — Valider le cahier des charges

Donne le bilan attendu pour {0, 20}, puis pour un tableau vide.


<details>
<summary>💡 Voir un indice</summary>

Deux notes aux extrêmes rendent le calcul facile à vérifier.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Pour {0, 20} : nombre 2, moyenne 10.0, maximum 20, validées 1. Pour le tableau vide : `Aucune note` seulement.


</details>

#### Exercice 02 — Ajouter le minimum

Écris minimum pour un tableau non vide. Teste {12, 8, 15, 10}.


<details>
<summary>💡 Voir un indice</summary>

Pars de la première valeur réelle, puis cherche une valeur plus petite.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le minimum est 8. La précondition du tableau non vide doit être respectée par l’appelant.


```java
public class Jour13Exercice02 {
    static int minimum(int[] notes) {
        int min = notes[0];
        for (int note : notes) {
            if (note < min) min = note;
        }
        return min;
    }
    public static void main(String[] args) {
        System.out.println(minimum(new int[] {12, 8, 15, 10}));
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour13Exercice02.java)

Résultat attendu :

```text
8
```

</details>

### Niveau 2 — corriger et consolider

#### Exercice 03 — Contrôler les notes

Écris toutesValides(int[] notes) qui renvoie false dès qu’une note est hors de [0, 20]. Pour un tableau vide, renvoie true : aucune note ne viole la règle. Teste {10, 21}.


<details>
<summary>💡 Voir un indice</summary>

Un seul contre-exemple suffit à renvoyer false.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

On ne renvoie true qu’après avoir examiné toutes les valeurs. Retourner true dans la boucle dès la première note valide serait une erreur.


```java
public class Jour13Exercice03 {
    static boolean toutesValides(int[] notes) {
        for (int note : notes) {
            if (note < 0 || note > 20) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(toutesValides(new int[] {10, 21}));
        System.out.println(toutesValides(new int[] {}));
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour13Exercice03.java)

Résultat attendu :

```text
false
true
```

</details>

### Niveau 3 — résoudre un petit problème

#### Exercice 04 — Moyenne pondérée

Pour notes {10, 16} et coefficients {1, 2}, calcule somme(note × coefficient) / somme(coefficients). Les tableaux sont de même taille, non vides et les coefficients strictement positifs.


<details>
<summary>💡 Voir un indice</summary>

Parcours les deux tableaux avec le même indice et garde deux cumuls.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le résultat est (10 × 1 + 16 × 2) / 3 = 14.0. Diviser par le nombre de notes au lieu de la somme des coefficients serait faux.


```java
public class Jour13Exercice04 {
    static double moyennePonderee(int[] notes, int[] coefficients) {
        double sommePonderee = 0;
        int totalCoefficients = 0;
        for (int i = 0; i < notes.length; i++) {
            sommePonderee += (double) notes[i] * coefficients[i];
            totalCoefficients += coefficients[i];
        }
        return sommePonderee / totalCoefficients;
    }
    public static void main(String[] args) {
        System.out.println(moyennePonderee(new int[] {10, 16}, new int[] {1, 2}));
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour13Exercice04.java)

Résultat attendu :

```text
14.0
```

</details>

<a id="bilan"></a>
## ✅ Avant de passer à la suite

- [ ] Je peux combiner tableaux, conditions, boucles et méthodes.
- [ ] Je peux lire un cahier des charges avant de coder.
- [ ] Je peux traiter les jeux de données vides et les seuils.

- [ ] J’ai tenté les quatre exercices avant de comparer aux corrigés.
- [ ] Je peux expliquer une erreur rencontrée et la façon dont je l’ai corrigée.

[Noter ma progression](../PROGRESSION.md) · [Consulter le glossaire](../docs/GLOSSAIRE.md)

---

[← Jour 12](../12_Day_Debugging/12_debugging.md) | [📚 Sommaire](../README.md) | [Jour 14 →](../14_Day_Classes_and_Objects/14_classes_and_objects.md)

**☕ Une étape comprise vaut mieux qu’une journée cochée trop vite.**
