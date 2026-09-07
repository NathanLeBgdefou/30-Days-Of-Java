<div align="center">
<h1>☕ 30 Days Of Java : jour 27</h1>
<h3>Dates et énumérations</h3>
<p>Français · Java 21 · Windows &amp; VS Code</p>
</div>

[← Jour 26](../26_Day_Lambdas_and_Streams/26_lambdas_and_streams.md) | [📚 Sommaire](../README.md) | [Jour 28 →](../28_Day_Automated_Tests/28_automated_tests.md)

![30 Days Of Java — apprendre à programmer](../images/30DaysOfJava_banner.svg)

![Progression : jour 27 sur 30](../images/progression-27.svg)

**Durée conseillée : 60 à 90 min.** Garde au moins une heure ; prends plus de temps pour coder si nécessaire.

📍 [Objectifs](#objectifs) · [Cours](#cours) · [Exemple](#exemple) · [Exercices](#exercices) · [Bilan](#bilan)

<a id="objectifs"></a>
## 🎯 Objectifs du jour

- Choisir LocalDate pour une échéance sans heure.
- Comparer des dates et calculer un intervalle.
- Remplacer des textes libres par un enum.

> **Ton rythme :** 10 min de rappel et lecture, 15–20 min sur l’exemple, 30–45 min de pratique, 5 min de bilan. Les jours de projet demandent davantage.

<a id="cours"></a>
# 📘 Cours

## Une date n'est pas une simple chaîne

`LocalDate` représente une date du calendrier sans heure ni fuseau. Pour une échéance de devoir au jour près, c'est un choix naturel. `LocalDate.of(2026, 9, 15)` construit une date en donnant année, mois, jour. Le mois commence à 1, pas à zéro.

`LocalDate.parse("2026-09-15")` lit le format ISO année-mois-jour. Une date impossible, comme un 30 février, est refusée. Pour ce parcours, annoncer ce format à l'utilisateur simplifie les saisies et les sauvegardes.

## Comparer sans calculer à la main

| Opération | Sens |
| --- | --- |
| `date.isBefore(autre)` | Strictement avant. |
| `date.isAfter(autre)` | Strictement après. |
| `date.equals(autre)` | Même date. |
| `date.plusDays(7)` | Nouvelle date sept jours plus tard. |
| `ChronoUnit.DAYS.between(a, b)` | Nombre de jours de a à b. |

Les objets java.time sont immuables. `date.plusDays(7)` ne modifie pas date : il faut conserver le résultat. LocalDate gère les changements de mois et les années bissextiles, ce qui évite de coder ces règles nous-mêmes.

Une tâche due aujourd'hui est-elle déjà en retard ? Nous choisissons : **retard seulement si l'échéance est strictement antérieure à la date de référence et si la tâche n'est pas terminée**. Ce choix doit être défini avant le code.

## Rendre les tests reproductibles

`LocalDate.now()` dépend du jour et du fuseau système. Pour un exemple ou un test, passe une date de référence explicite. Le même test donnera alors le même résultat demain. L'application finale peut utiliser now pour l'écran, tout en gardant ses méthodes testables avec une date fournie.

LocalDate ne représente pas un instant mondial. Pour un horodatage absolu, on utiliserait Instant ; pour une heure locale avec fuseau, ZonedDateTime peut convenir. Ne transforme pas une date de devoir en instant arbitraire si tu n'as besoin que du jour.

## enum : un ensemble fermé de valeurs

`enum Etat { A_FAIRE, TERMINEE }` définit deux valeurs autorisées. Une variable Etat ne peut pas contenir le texte mal orthographié `"terminéee"`. Le compilateur et le type t'aident à garder un état cohérent.

On peut comparer deux valeurs d'enum avec `==`. `Etat.valueOf("A_FAIRE")` retrouve une valeur par son nom exact ; un nom inconnu déclenche IllegalArgumentException. Pour un format de sauvegarde, ces noms deviennent une partie du contrat à ne pas changer sans prévoir la lecture des anciens fichiers.

Un enum peut aussi porter des champs et des méthodes, mais deux constantes simples suffisent pour le projet de demain.


<a id="exemple"></a>
## 🔎 Exemple complet, prêt à exécuter

[Ouvrir Jour27Exemple.java](./exemples/Jour27Exemple.java) · [Lire les exercices sans les réponses](./exercices/README.md)

Dans VS Code, ouvre le dossier `exemples` de cette journée, puis lance dans son terminal :

```powershell
java .\Jour27Exemple.java
```

```java
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
public class Jour27Exemple {
    static boolean estEnRetard(LocalDate echeance, EtatJ27Exemple etat, LocalDate reference) {
        return etat == EtatJ27Exemple.A_FAIRE && echeance.isBefore(reference);
    }
    public static void main(String[] args) {
        LocalDate reference = LocalDate.of(2026, 9, 15);
        LocalDate echeance = LocalDate.parse("2026-09-12");
        System.out.println(estEnRetard(echeance, EtatJ27Exemple.A_FAIRE, reference));
        System.out.println(estEnRetard(echeance, EtatJ27Exemple.TERMINEE, reference));
        System.out.println(ChronoUnit.DAYS.between(echeance, reference));
        System.out.println(reference.plusDays(7));
    }
}
enum EtatJ27Exemple { A_FAIRE, TERMINEE }
```

**Sortie du programme :**

```text
true
false
3
2026-09-22
```

### Comprendre le déroulement

L'échéance est trois jours avant la référence. La tâche à faire est donc en retard, mais la tâche terminée ne l'est plus selon notre règle. La date fixe empêche le résultat de changer avec le calendrier réel. plusDays produit une nouvelle valeur pour la semaine suivante.


### ⚠️ Pièges fréquents

- Une date locale n’est pas un instant avec fuseau horaire.
- isBefore est strict : une échéance égale à aujourd’hui n’est pas avant aujourd’hui.
- valueOf exige le nom exact d’une constante enum.

<a id="exercices"></a>
## 💻 Exercices du jour

Essaie d’abord sans ouvrir les indices. Pour un exercice de code, crée ton propre fichier dans un dossier de travail et donne le même nom à la classe publique et au fichier. Les corrigés sont complets pour permettre une comparaison et une exécution indépendantes.

### Niveau 1 — comprendre et appliquer

#### Exercice 01 — Définir la frontière

Avec notre règle, une tâche à faire dont l’échéance est aujourd’hui est-elle en retard ? Et une tâche terminée hier dont l’échéance était avant-hier ?


<details>
<summary>💡 Voir un indice</summary>

Il faut combiner le statut et la comparaison stricte des dates.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

La tâche due aujourd’hui n’est pas en retard. Une tâche terminée n’est pas comptée en retard, même si sa date d’échéance est passée.


</details>

#### Exercice 02 — Passer au mois suivant

À partir du 28 février 2024, ajoute deux jours et affiche la date.


<details>
<summary>💡 Voir un indice</summary>

2024 est bissextile ; LocalDate gère le calendrier.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le résultat est le 1er mars 2024.


```java
import java.time.LocalDate;
public class Jour27Exercice02 {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2024, 2, 28);
        System.out.println(date.plusDays(2));
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour27Exercice02.java)

Résultat attendu :

```text
2024-03-01
```

</details>

### Niveau 2 — corriger et consolider

#### Exercice 03 — Décoder un état contrôlé

Convertis `TERMINEE` en Etat, puis tente `INCONNU` et affiche `État invalide` en cas de refus.


<details>
<summary>💡 Voir un indice</summary>

valueOf renvoie une constante ou déclenche IllegalArgumentException.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

La seconde chaîne n’appartient pas au domaine autorisé.


```java
public class Jour27Exercice03 {
    public static void main(String[] args) {
        System.out.println(EtatJ27E03.valueOf("TERMINEE"));
        try {
            EtatJ27E03.valueOf("INCONNU");
        } catch (IllegalArgumentException e) {
            System.out.println("État invalide");
        }
    }
}
enum EtatJ27E03 { A_FAIRE, TERMINEE }
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour27Exercice03.java)

Résultat attendu :

```text
TERMINEE
État invalide
```

</details>

### Niveau 3 — résoudre un petit problème

#### Exercice 04 — Une échéance dans les sept jours

Écris estProche(echeance, reference), vrai si l’échéance est entre la référence et référence + 7 jours, bornes incluses. Teste la veille, le jour même et +7.


<details>
<summary>💡 Voir un indice</summary>

Exclus les dates avant la borne basse et après la borne haute.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Les deux négations conservent les bornes. On teste la même méthode avec une référence fixe.


```java
import java.time.LocalDate;
public class Jour27Exercice04 {
    static boolean estProche(LocalDate echeance, LocalDate reference) {
        return !echeance.isBefore(reference) && !echeance.isAfter(reference.plusDays(7));
    }
    public static void main(String[] args) {
        LocalDate reference = LocalDate.of(2026, 9, 15);
        System.out.println(estProche(reference.minusDays(1), reference));
        System.out.println(estProche(reference, reference));
        System.out.println(estProche(reference.plusDays(7), reference));
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour27Exercice04.java)

Résultat attendu :

```text
false
true
true
```

</details>

<a id="bilan"></a>
## ✅ Avant de passer à la suite

- [ ] Je peux choisir LocalDate pour une échéance sans heure.
- [ ] Je peux comparer des dates et calculer un intervalle.
- [ ] Je peux remplacer des textes libres par un enum.

- [ ] J’ai tenté les quatre exercices avant de comparer aux corrigés.
- [ ] Je peux expliquer une erreur rencontrée et la façon dont je l’ai corrigée.

[Noter ma progression](../PROGRESSION.md) · [Consulter le glossaire](../docs/GLOSSAIRE.md)

### 📎 Pour approfondir

- [Dates et heures](https://dev.java/learn/date-time/)

---

[← Jour 26](../26_Day_Lambdas_and_Streams/26_lambdas_and_streams.md) | [📚 Sommaire](../README.md) | [Jour 28 →](../28_Day_Automated_Tests/28_automated_tests.md)

**☕ Une étape comprise vaut mieux qu’une journée cochée trop vite.**
