<div align="center">
<h1>☕ 30 Days Of Java : jour 07</h1>
<h3>Répéter avec while et do-while</h3>
<p>Français · Java 21 · Windows &amp; VS Code</p>
</div>

[← Jour 06](../06_Day_Conditionals/06_conditionals.md) | [📚 Sommaire](../README.md) | [Jour 08 →](../08_Day_For_Loops/08_for_loops.md)

![30 Days Of Java — apprendre à programmer](../images/30DaysOfJava_banner.svg)

![Progression : jour 7 sur 30](../images/progression-07.svg)

**Durée conseillée : 60 à 90 min.** Garde au moins une heure ; prends plus de temps pour coder si nécessaire.

📍 [Objectifs](#objectifs) · [Cours](#cours) · [Exemple](#exemple) · [Exercices](#exercices) · [Bilan](#bilan)

<a id="objectifs"></a>
## 🎯 Objectifs du jour

- Identifier l’état et la condition d’une boucle.
- Garantir la progression vers l’arrêt.
- Utiliser une sentinelle pour terminer une saisie.

> **Ton rythme :** 10 min de rappel et lecture, 15–20 min sur l’exemple, 30–45 min de pratique, 5 min de bilan. Les jours de projet demandent davantage.

<a id="cours"></a>
# 📘 Cours

## Répéter tant qu'une condition reste vraie

`while (condition) { ... }` reteste la condition avant chaque passage. Si elle est fausse dès le départ, le corps n'est jamais exécuté. Une boucle a généralement trois ingrédients : un **état initial**, une **condition de poursuite** et une **mise à jour** qui fait avancer cet état.

Pour compter de 1 à 3, initialise `compteur` à 1, poursuis tant qu'il est inférieur ou égal à 3, puis ajoute 1 à chaque passage. L'ordre est important : afficher puis incrémenter ne montre pas les mêmes valeurs qu'incrémenter puis afficher.

| Moment | compteur | Action |
| --- | --- | --- |
| Avant le premier tour | 1 | 1 ≤ 3, on entre. |
| Après le premier tour | 2 | 2 ≤ 3, on continue. |
| Après le deuxième tour | 3 | 3 ≤ 3, on continue. |
| Après le troisième tour | 4 | 4 ≤ 3 est faux, on sort. |

## Accumuler un résultat

Une variable comme `somme` garde un résultat partiel. Elle commence souvent à zéro. À chaque passage, on ajoute une nouvelle valeur. On l'initialise **avant** la boucle : la remettre à zéro dans la boucle ferait perdre les tours précédents.

## Lire jusqu'à une sentinelle

Une **sentinelle** est une valeur spéciale qui signifie « arrêter ». Pour compter des minutes de révision non négatives, on peut choisir −1. Elle ne doit pas être ajoutée au total. Une stratégie simple est de lire une première valeur, puis de boucler tant qu'elle n'est pas la sentinelle, en lisant la suivante à la fin du corps.

Pense au premier cas possible : si l'utilisateur saisit immédiatement −1, le total doit rester zéro. Ce cas « aucune donnée » est une vraie situation à traiter.

## Faire au moins un passage

`do { ... } while (condition);` évalue la condition après le corps. Il y a donc toujours au moins une exécution. C'est pratique pour poser une question avant de savoir s'il faudra la reposer. Note le point-virgule final, nécessaire dans cette forme.

```java fragment
int compteur = 1;
do {
    System.out.println(compteur);
    compteur++;
} while (compteur <= 3);
```

`break` quitte la boucle la plus proche. `continue` passe directement au tour suivant. Avec while, un continue mal placé peut sauter la mise à jour et créer une boucle infinie. Au début, préfère une condition d'arrêt visible et un chemin de mise à jour facile à suivre.

Si ton terminal affiche sans fin, `Ctrl+C` interrompt le programme. Ce n'est pas une correction : cherche ensuite quel état n'a pas été mis à jour ou quelle condition ne peut jamais devenir fausse.


<a id="exemple"></a>
## 🔎 Exemple complet, prêt à exécuter

[Ouvrir Jour07Exemple.java](./exemples/Jour07Exemple.java) · [Lire les exercices sans les réponses](./exercices/README.md)

Dans VS Code, ouvre le dossier `exemples` de cette journée, puis lance dans son terminal :

```powershell
java .\Jour07Exemple.java
```

```java
public class Jour07Exemple {
    public static void main(String[] args) {
        int jour = 1;
        int totalMinutes = 0;
        while (jour <= 5) {
            totalMinutes = totalMinutes + 60;
            System.out.println("Jour " + jour + " : " + totalMinutes + " min cumulées");
            jour++;
        }
        System.out.println("Terminé");
    }
}
```

**Sortie du programme :**

```text
Jour 1 : 60 min cumulées
Jour 2 : 120 min cumulées
Jour 3 : 180 min cumulées
Jour 4 : 240 min cumulées
Jour 5 : 300 min cumulées
Terminé
```

### Comprendre le déroulement

`jour` commande la répétition ; `totalMinutes` mémorise le calcul. Ce sont deux rôles distincts. Après le cinquième passage, jour devient 6, donc la condition est fausse. La dernière instruction est en dehors des accolades de while : elle n'est exécutée qu'une fois.


### ⚠️ Pièges fréquents

- Oublier `jour++` peut empêcher la condition de devenir fausse.
- Initialiser la somme à l’intérieur de la boucle détruit le cumul.
- Une sentinelle sert à piloter l’arrêt ; elle n’est pas une donnée à traiter.

<a id="exercices"></a>
## 💻 Exercices du jour

Essaie d’abord sans ouvrir les indices. Pour un exercice de code, crée ton propre fichier dans un dossier de travail et donne le même nom à la classe publique et au fichier. Les corrigés sont complets pour permettre une comparaison et une exécution indépendantes.

### Niveau 1 — comprendre et appliquer

#### Exercice 01 — Zéro ou un passage ?

Une condition vaut false dès le départ. Combien de fois le corps de while s’exécute-t-il ? Et celui de do-while ?


<details>
<summary>💡 Voir un indice</summary>

Regarde si la condition est évaluée avant ou après le corps.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

while : zéro fois. do-while : une fois. Ensuite la condition fausse empêche une seconde exécution.


</details>

#### Exercice 02 — Compte à rebours

Affiche 5, 4, 3, 2, 1, puis `Départ`.


<details>
<summary>💡 Voir un indice</summary>

Le compteur doit diminuer vers la borne d’arrêt.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

On décrémente à chaque passage et on place le dernier message après la boucle.


```java
public class Jour07Exercice02 {
    public static void main(String[] args) {
        int compteur = 5;
        while (compteur >= 1) {
            System.out.println(compteur);
            compteur--;
        }
        System.out.println("Départ");
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour07Exercice02.java)

Résultat attendu :

```text
5
4
3
2
1
Départ
```

</details>

### Niveau 2 — corriger et consolider

#### Exercice 03 — Réparer le cumul

On veut additionner les entiers de 1 à 5. Écris la version correcte avec somme initialisée une seule fois et un compteur qui avance.


<details>
<summary>💡 Voir un indice</summary>

La somme commence à zéro avant la boucle ; le compteur commence à un.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Chaque tour ajoute le compteur courant. À la sortie, somme vaut 15.


```java
public class Jour07Exercice03 {
    public static void main(String[] args) {
        int somme = 0;
        int n = 1;
        while (n <= 5) {
            somme += n;
            n++;
        }
        System.out.println(somme);
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour07Exercice03.java)

Résultat attendu :

```text
15
```

</details>

### Niveau 3 — résoudre un petit problème

#### Exercice 04 — Cumuler des séances

Lis des durées entières non négatives jusqu’à −1. Affiche le total. On suppose des saisies numériques valides. Teste 30, 45, −1 ; puis teste −1 seul.


<details>
<summary>💡 Voir un indice</summary>

Lis une première valeur avant while, et la suivante à la fin du corps.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

La sentinelle n’entre pas dans la somme. Avec −1 dès le départ, le corps ne s’exécute pas et le total reste zéro.


```java
import java.util.Scanner;
public class Jour07Exercice04 {
    public static void main(String[] args) {
        Scanner clavier = new Scanner(System.in);
        int total = 0;
        System.out.println("Durées en minutes, puis -1 pour terminer :");
        int duree = Integer.parseInt(clavier.nextLine().strip());
        while (duree != -1) {
            if (duree >= 0) {
                total += duree;
            }
            duree = Integer.parseInt(clavier.nextLine().strip());
        }
        System.out.println("Total : " + total);
        clavier.close();
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour07Exercice04.java)

Entrées de vérification :

```text
30
45
-1
```

Résultat attendu :

```text
Durées en minutes, puis -1 pour terminer :
Total : 75
```

</details>

<a id="bilan"></a>
## ✅ Avant de passer à la suite

- [ ] Je peux identifier l’état et la condition d’une boucle.
- [ ] Je peux garantir la progression vers l’arrêt.
- [ ] Je peux utiliser une sentinelle pour terminer une saisie.

- [ ] J’ai tenté les quatre exercices avant de comparer aux corrigés.
- [ ] Je peux expliquer une erreur rencontrée et la façon dont je l’ai corrigée.

[Noter ma progression](../PROGRESSION.md) · [Consulter le glossaire](../docs/GLOSSAIRE.md)

---

[← Jour 06](../06_Day_Conditionals/06_conditionals.md) | [📚 Sommaire](../README.md) | [Jour 08 →](../08_Day_For_Loops/08_for_loops.md)

**☕ Une étape comprise vaut mieux qu’une journée cochée trop vite.**
