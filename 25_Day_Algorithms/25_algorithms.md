<div align="center">
<h1>☕ 30 Days Of Java : jour 25</h1>
<h3>Rechercher, trier et comprendre le coût</h3>
<p>Français · Java 21 · Windows &amp; VS Code</p>
</div>

[← Jour 24](../24_Day_Packages_and_Projects/24_packages_and_projects.md) | [📚 Sommaire](../README.md) | [Jour 26 →](../26_Day_Lambdas_and_Streams/26_lambdas_and_streams.md)

![30 Days Of Java — apprendre à programmer](../images/30DaysOfJava_banner.svg)

![Progression : jour 25 sur 30](../images/progression-25.svg)

**Durée conseillée : 60 à 90 min.** Garde au moins une heure ; prends plus de temps pour coder si nécessaire.

📍 [Objectifs](#objectifs) · [Cours](#cours) · [Exemple](#exemple) · [Exercices](#exercices) · [Bilan](#bilan)

<a id="objectifs"></a>
## 🎯 Objectifs du jour

- Écrire une recherche linéaire.
- Comprendre la précondition d’une recherche dichotomique.
- Interpréter O(n), O(log n) et O(n²) sans les confondre avec des secondes.

> **Ton rythme :** 10 min de rappel et lecture, 15–20 min sur l’exemple, 30–45 min de pratique, 5 min de bilan. Les jours de projet demandent davantage.

<a id="cours"></a>
# 📘 Cours

## Mesurer les étapes utiles

Un algorithme décrit une manière de résoudre un problème. Sa **complexité** décrit comment le nombre d'opérations ou la mémoire nécessaire évolue avec la taille des données. O(n) n'est pas une durée en secondes : cela exprime une croissance proportionnelle à n, à des constantes près, dans le cadre étudié.

Pour trouver une valeur dans un tableau quelconque, une recherche linéaire compare les cases de gauche à droite. Au pire, elle examine les n éléments. On peut renvoyer l'indice trouvé, ou −1 si la valeur est absente. Ce −1 est une convention de résultat, pas un indice valide à lire ensuite.

## La dichotomie exige des données triées

Dans un tableau trié par ordre croissant, on peut comparer la cible à l'élément central. Si la cible est plus grande, on écarte la moitié gauche ; si elle est plus petite, on écarte la moitié droite. On recommence dans la zone restante.

Les bornes gauche et droite délimitent ici une zone inclusive. Le milieu se calcule avec `gauche + (droite - gauche) / 2`, ce qui évite d'additionner deux grands indices. Si on écarte le milieu, la nouvelle borne est `milieu + 1` ou `milieu - 1`, sinon la zone peut ne pas rétrécir.

Le nombre de comparaisons est en O(log n). Mais si le tableau n'est pas trié, la réponse peut être fausse. Trier juste pour une recherche isolée a aussi un coût : il faut considérer tout le traitement, pas uniquement la recherche finale.

## Trier un tableau

`Arrays.sort(tableau)` trie les cases du tableau lui-même. Si tu dois préserver l'ordre d'origine, copie-le avant de trier. `Arrays.binarySearch` fournit une recherche sur tableau trié. Son résultat négatif en cas d'absence encode aussi un point d'insertion ; il ne vaut pas toujours −1, contrairement à notre méthode pédagogique.

| Situation | Ordre de grandeur courant |
| --- | --- |
| Lire une case par indice | O(1) |
| Chercher linéairement | O(n) au pire |
| Chercher par dichotomie sur tableau trié | O(log n) au pire |
| Comparer chaque élément à tous les autres | O(n²) |

## Comprendre un tri simple

Le tri par sélection cherche le minimum restant, puis le place à la prochaine position. Il est facile à écrire et coûte O(n²) comparaisons dans sa forme classique. On l'étudie pour comprendre les échanges et les invariants, pas pour remplacer systématiquement les tris éprouvés de la bibliothèque standard.

Teste une liste vide, une seule valeur, des doublons et une cible absente. Pour une recherche qui accepte des doublons, précise si n'importe quelle occurrence suffit ou si la première est exigée. Notre dichotomie renvoie une occurrence, sans garantie de première position.


<a id="exemple"></a>
## 🔎 Exemple complet, prêt à exécuter

[Ouvrir Jour25Exemple.java](./exemples/Jour25Exemple.java) · [Lire les exercices sans les réponses](./exercices/README.md)

Dans VS Code, ouvre le dossier `exemples` de cette journée, puis lance dans son terminal :

```powershell
java .\Jour25Exemple.java
```

```java
public class Jour25Exemple {
    static int chercher(int[] valeurs, int cible) {
        int gauche = 0;
        int droite = valeurs.length - 1;
        while (gauche <= droite) {
            int milieu = gauche + (droite - gauche) / 2;
            if (valeurs[milieu] == cible) return milieu;
            if (valeurs[milieu] < cible) gauche = milieu + 1;
            else droite = milieu - 1;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] tries = {3, 7, 10, 14, 18};
        System.out.println(chercher(tries, 14));
        System.out.println(chercher(tries, 9));
    }
}
```

**Sortie du programme :**

```text
3
-1
```

### Comprendre le déroulement

Pour chercher 14, la première comparaison porte sur 10 ; on conserve alors la partie droite. La comparaison suivante trouve 14 à l'indice 3. Pour 9, la zone finit par devenir vide, donc gauche dépasse droite et la méthode renvoie −1. Sur un tableau vide, cette même condition arrête immédiatement la recherche.


### ⚠️ Pièges fréquents

- Une recherche dichotomique sur des données non triées n’est pas correcte.
- Arrays.sort modifie le tableau fourni.
- O(log n) ne dispense pas de compter le coût éventuel du tri préalable.

<a id="exercices"></a>
## 💻 Exercices du jour

Essaie d’abord sans ouvrir les indices. Pour un exercice de code, crée ton propre fichier dans un dossier de travail et donne le même nom à la classe publique et au fichier. Les corrigés sont complets pour permettre une comparaison et une exécution indépendantes.

### Niveau 1 — comprendre et appliquer

#### Exercice 01 — Comparer deux stratégies

Tu dois chercher une seule fois dans un petit tableau non trié. Pourquoi trier puis faire une dichotomie n’est-il pas automatiquement préférable ?


<details>
<summary>💡 Voir un indice</summary>

Le tri est lui-même un travail supplémentaire.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Une recherche linéaire parcourt directement les données. Le tri peut coûter davantage que ce seul parcours. En revanche, un tableau déjà trié ou de nombreuses recherches peuvent rendre la dichotomie intéressante.


</details>

#### Exercice 02 — Recherche linéaire

Écris indiceDe(int[] valeurs, int cible), qui renvoie la première position de la cible ou −1. Teste {4, 7, 4} avec 4 puis 9.


<details>
<summary>💡 Voir un indice</summary>

Un parcours de gauche à droite peut retourner dès la première égalité.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

On renvoie zéro pour la première occurrence de 4 et −1 pour l’absence de 9.


```java
public class Jour25Exercice02 {
    static int indiceDe(int[] valeurs, int cible) {
        for (int i = 0; i < valeurs.length; i++) {
            if (valeurs[i] == cible) return i;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] valeurs = {4, 7, 4};
        System.out.println(indiceDe(valeurs, 4));
        System.out.println(indiceDe(valeurs, 9));
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour25Exercice02.java)

Résultat attendu :

```text
0
-1
```

</details>

### Niveau 2 — corriger et consolider

#### Exercice 03 — Trier sans perdre l’original

Copie {9, 2, 7}, trie la copie avec Arrays.sort, puis affiche les deux tableaux.


<details>
<summary>💡 Voir un indice</summary>

Arrays.copyOf duplique les cases d’un tableau de int.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

L’ordre initial reste [9, 2, 7], la copie devient [2, 7, 9].


```java
import java.util.Arrays;
public class Jour25Exercice03 {
    public static void main(String[] args) {
        int[] origine = {9, 2, 7};
        int[] copie = Arrays.copyOf(origine, origine.length);
        Arrays.sort(copie);
        System.out.println(Arrays.toString(origine));
        System.out.println(Arrays.toString(copie));
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour25Exercice03.java)

Résultat attendu :

```text
[9, 2, 7]
[2, 7, 9]
```

</details>

### Niveau 3 — résoudre un petit problème

#### Exercice 04 — Implémenter le tri par sélection

Trie {4, 1, 3, 2} sans Arrays.sort : à chaque position, trouve l’indice du minimum restant et échange les deux cases.


<details>
<summary>💡 Voir un indice</summary>

Une boucle choisit la position à remplir ; une autre cherche le minimum dans la partie restante.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Après chaque tour extérieur, la partie gauche est définitivement triée. La variable temporaire préserve une valeur pendant l’échange.


```java
import java.util.Arrays;
public class Jour25Exercice04 {
    static void trier(int[] valeurs) {
        for (int i = 0; i < valeurs.length - 1; i++) {
            int indiceMin = i;
            for (int j = i + 1; j < valeurs.length; j++) {
                if (valeurs[j] < valeurs[indiceMin]) indiceMin = j;
            }
            int temporaire = valeurs[i];
            valeurs[i] = valeurs[indiceMin];
            valeurs[indiceMin] = temporaire;
        }
    }
    public static void main(String[] args) {
        int[] valeurs = {4, 1, 3, 2};
        trier(valeurs);
        System.out.println(Arrays.toString(valeurs));
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour25Exercice04.java)

Résultat attendu :

```text
[1, 2, 3, 4]
```

</details>

<a id="bilan"></a>
## ✅ Avant de passer à la suite

- [ ] Je peux écrire une recherche linéaire.
- [ ] Je peux comprendre la précondition d’une recherche dichotomique.
- [ ] Je peux interpréter O(n), O(log n) et O(n²) sans les confondre avec des secondes.

- [ ] J’ai tenté les quatre exercices avant de comparer aux corrigés.
- [ ] Je peux expliquer une erreur rencontrée et la façon dont je l’ai corrigée.

[Noter ma progression](../PROGRESSION.md) · [Consulter le glossaire](../docs/GLOSSAIRE.md)

---

[← Jour 24](../24_Day_Packages_and_Projects/24_packages_and_projects.md) | [📚 Sommaire](../README.md) | [Jour 26 →](../26_Day_Lambdas_and_Streams/26_lambdas_and_streams.md)

**☕ Une étape comprise vaut mieux qu’une journée cochée trop vite.**
