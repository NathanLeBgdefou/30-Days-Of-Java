<div align="center">
<h1>☕ 30 Days Of Java : jour 30</h1>
<h3>Bilan, évaluation et suite</h3>
<p>Français · Java 21 · Windows &amp; VS Code</p>
</div>

[← Jour 29](../29_Day_Final_Project/29_final_project.md) | [📚 Sommaire](../README.md)

![30 Days Of Java — apprendre à programmer](../images/30DaysOfJava_banner.svg)

![Progression : jour 30 sur 30](../images/progression-30.svg)

**Durée conseillée : 90 à 120 min.** Garde au moins une heure ; prends plus de temps pour coder si nécessaire.

📍 [Objectifs](#objectifs) · [Cours](#cours) · [Exemple](#exemple) · [Exercices](#exercices) · [Bilan](#bilan)

<a id="objectifs"></a>
## 🎯 Objectifs du jour

- Évaluer tes acquis sur des tâches observables.
- Étendre une application sans casser les règles existantes.
- Choisir la suite selon le programme de tes études.

> **Ton rythme :** 10 min de rappel et lecture, 15–20 min sur l’exemple, 30–45 min de pratique, 5 min de bilan. Les jours de projet demandent davantage.

<a id="cours"></a>
# 📘 Cours

## Ce que tu dois pouvoir faire maintenant

Tu as parcouru les bases du langage, les algorithmes, les objets, les collections, les erreurs, les fichiers et les tests. Le meilleur indicateur n'est pas d'avoir lu trente pages : c'est de pouvoir construire un petit programme, expliquer ses choix et corriger un problème sans changer le code au hasard.

Ouvre le [bilan d'autoévaluation](../docs/AUTOEVALUATION.md). Les critères portent sur des comportements : écrire une méthode, traiter un tableau vide, expliquer equals, préserver un fichier en cas de chargement invalide. Coche seulement ce que tu sais refaire avec une aide raisonnable, puis reviens aux journées indiquées pour les points fragiles.

## Une épreuve de consolidation

Consacre 60 à 90 minutes à une extension du carnet : **afficher les tâches à faire dont l'échéance est comprise entre aujourd'hui et sept jours plus tard, bornes incluses, triées par échéance puis identifiant**.

Avant de coder, écris cinq cas : hier, aujourd'hui, +7 jours, +8 jours, tâche déjà terminée. Utilise une date de référence fixe pour les tests. Le code de filtrage peut être une boucle ou un stream ; le contrat du résultat reste identique.

Le corrigé isolé du quatrième exercice montre cette extension. Intègre-la ensuite au Carnet et au menu, puis relance les tests existants du projet. Une amélioration ne doit pas casser l'ajout, la sauvegarde ou le passage à l'état terminé.

## Une grille indicative sur 20

| Critère | Points |
| --- | --- |
| Règle de filtrage correcte, avec bornes incluses | 5 |
| Tâches terminées exclues | 3 |
| Tri par échéance puis identifiant | 3 |
| Tests couvrant les cinq cas annoncés | 4 |
| Intégration lisible sans dupliquer la logique | 3 |
| Explication orale des choix et limites | 2 |

Pour l'explication, essaie de justifier la date fixe dans les tests, l'absence de mutation de la liste source et la différence entre un identifiant et une position. Si ces réponses ne sont pas claires, les revoir sera plus utile que d'ajouter encore des fonctions.

## Consolider avant d'accumuler

Pendant les semaines suivantes, refais un petit exercice sans corrigé, explique-le à voix haute, puis vérifie-le. Reviens quelques jours plus tard sur le même concept avec des données différentes. Les traces papier restent utiles pour les boucles, les références et la récursion.

Pour aller plus loin selon tes études : **Maven et JUnit** pour organiser un projet et ses tests ; **SQL et JDBC** pour les bases de données ; **structures de données et algorithmique** pour les exercices théoriques ; puis éventuellement interfaces graphiques ou développement web si ton programme les demande. Le [guide de ressources](../docs/RESSOURCES.md) donne les points d'entrée officiels.

Tu n'as pas besoin d'apprendre tous ces sujets d'un coup. Prends le prochain besoin concret de tes études et construis un petit projet qui l'utilise. Conserve ce dépôt comme référence et tes propres exercices comme trace de ta progression.


<a id="exemple"></a>
## 🔎 Exemple complet, prêt à exécuter

[Ouvrir Jour30Exemple.java](./exemples/Jour30Exemple.java) · [Lire les exercices sans les réponses](./exercices/README.md)

Dans VS Code, ouvre le dossier `exemples` de cette journée, puis lance dans son terminal :

```powershell
java .\Jour30Exemple.java
```

```java
import java.time.LocalDate;
import java.util.List;
public class Jour30Exemple {
    static long compterEnRetard(List<TacheJ30Exemple> taches, LocalDate reference) {
        return taches.stream()
            .filter(tache -> !tache.terminee())
            .filter(tache -> tache.echeance().isBefore(reference))
            .count();
    }
    public static void main(String[] args) {
        LocalDate reference = LocalDate.of(2026, 9, 15);
        List<TacheJ30Exemple> taches = List.of(
            new TacheJ30Exemple(LocalDate.of(2026, 9, 14), false),
            new TacheJ30Exemple(LocalDate.of(2026, 9, 15), false),
            new TacheJ30Exemple(LocalDate.of(2026, 9, 13), true)
        );
        System.out.println(compterEnRetard(taches, reference));
    }
}
record TacheJ30Exemple(LocalDate echeance, boolean terminee) {}
```

**Sortie du programme :**

```text
1
```

### Comprendre le déroulement

Une seule tâche remplit les deux critères. La tâche due aujourd'hui n'est pas strictement avant la référence ; la tâche terminée est exclue malgré son ancienne échéance. Le type long correspond au retour de count. La méthode reçoit la date au lieu de la chercher elle-même, ce qui garde le test reproductible.


### ⚠️ Pièges fréquents

- Lire une correction n’équivaut pas à savoir reconstruire le raisonnement.
- Une nouvelle fonctionnalité doit conserver les comportements déjà vérifiés.
- Le sujet « cette semaine » doit préciser ses bornes : sept jours glissants et semaine calendaire ne sont pas la même règle.

<a id="exercices"></a>
## 💻 Exercices du jour

Essaie d’abord sans ouvrir les indices. Pour un exercice de code, crée ton propre fichier dans un dossier de travail et donne le même nom à la classe publique et au fichier. Les corrigés sont complets pour permettre une comparaison et une exécution indépendantes.

### Niveau 1 — comprendre et appliquer

#### Exercice 01 — Expliquer les bases sans code

Explique en une phrase chacun : classe, objet, interface, exception et test. Donne ensuite un exemple de chaque dans le carnet.


<details>
<summary>💡 Voir un indice</summary>

Utilise les responsabilités réellement présentes dans le projet.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Classe : définition d’un type, comme Carnet. Objet : instance concrète, comme le carnet chargé. Interface : contrat d’opérations, comme List utilisé pour les tâches. Exception : signal d’un échec, comme IOException lors d’une lecture. Test : vérification automatique d’un comportement, comme le refus d’un titre vide.


</details>

#### Exercice 02 — Une méthode à refaire seul

Écris sommePositifs(int[] valeurs) pour additionner seulement les valeurs strictement positives. Teste {-2, 0, 3, 5} et le tableau vide.


<details>
<summary>💡 Voir un indice</summary>

Un cumul à zéro et un critère > 0 suffisent.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le tableau vide ne nécessite pas de cas spécial : la boucle ne s’exécute pas et zéro est le résultat attendu.


```java
public class Jour30Exercice02 {
    static int sommePositifs(int[] valeurs) {
        int somme = 0;
        for (int valeur : valeurs) {
            if (valeur > 0) somme += valeur;
        }
        return somme;
    }
    public static void main(String[] args) {
        System.out.println(sommePositifs(new int[] {-2, 0, 3, 5}));
        System.out.println(sommePositifs(new int[] {}));
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour30Exercice02.java)

Résultat attendu :

```text
8
0
```

</details>

### Niveau 2 — corriger et consolider

#### Exercice 03 — Une régression à empêcher

Écris une règle dansSeptJours(date, reference), bornes incluses, puis un test qui échouerait si +7 jours était exclu par erreur.


<details>
<summary>💡 Voir un indice</summary>

Le test doit viser exactement la frontière qui pourrait être cassée.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Tester seulement demain ne détecterait pas cette erreur de borne.


```java
import java.time.LocalDate;
public class Jour30Exercice03 {
    static boolean dansSeptJours(LocalDate date, LocalDate reference) {
        return !date.isBefore(reference) && !date.isAfter(reference.plusDays(7));
    }
    public static void main(String[] args) {
        LocalDate reference = LocalDate.of(2026, 9, 15);
        if (!dansSeptJours(reference.plusDays(7), reference)) {
            throw new AssertionError("La borne +7 doit être incluse");
        }
        System.out.println("Borne +7 vérifiée");
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour30Exercice03.java)

Résultat attendu :

```text
Borne +7 vérifiée
```

</details>

### Niveau 3 — résoudre un petit problème

#### Exercice 04 — L’extension de fin de parcours

Écris prochaines(taches, reference) : conserve les tâches non terminées entre référence et +7 inclus, puis trie par échéance et id. Vérifie hier, aujourd’hui, +7, +8 et terminé.


<details>
<summary>💡 Voir un indice</summary>

Combine deux bornes, un état et Comparator.comparing(...).thenComparingInt(...).

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le filtre garde les deux bornes, le tri est stable dans son contrat grâce au second critère d’identifiant. Le résultat est une nouvelle liste non modifiable.


```java
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
public class Jour30Exercice04 {
    static List<TacheJ30E04> prochaines(List<TacheJ30E04> taches, LocalDate reference) {
        return taches.stream()
            .filter(t -> !t.terminee())
            .filter(t -> !t.echeance().isBefore(reference))
            .filter(t -> !t.echeance().isAfter(reference.plusDays(7)))
            .sorted(Comparator.comparing(TacheJ30E04::echeance).thenComparingInt(TacheJ30E04::id))
            .toList();
    }
    public static void main(String[] args) {
        LocalDate ref = LocalDate.of(2026, 9, 15);
        List<TacheJ30E04> taches = List.of(
            new TacheJ30E04(1, ref.minusDays(1), false),
            new TacheJ30E04(3, ref.plusDays(7), false),
            new TacheJ30E04(2, ref, false),
            new TacheJ30E04(4, ref.plusDays(8), false),
            new TacheJ30E04(5, ref, true)
        );
        List<TacheJ30E04> resultat = prochaines(taches, ref);
        if (resultat.size() != 2 || resultat.get(0).id() != 2 || resultat.get(1).id() != 3) {
            throw new AssertionError("Filtrage ou tri incorrect");
        }
        System.out.println(resultat.stream().map(TacheJ30E04::id).toList());
    }
}
record TacheJ30E04(int id, LocalDate echeance, boolean terminee) {}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour30Exercice04.java)

Résultat attendu :

```text
[2, 3]
```

</details>

<a id="bilan"></a>
## ✅ Avant de passer à la suite

- [ ] Je peux évaluer tes acquis sur des tâches observables.
- [ ] Je peux étendre une application sans casser les règles existantes.
- [ ] Je peux choisir la suite selon le programme de tes études.

- [ ] J’ai tenté les quatre exercices avant de comparer aux corrigés.
- [ ] Je peux expliquer une erreur rencontrée et la façon dont je l’ai corrigée.

[Noter ma progression](../PROGRESSION.md) · [Consulter le glossaire](../docs/GLOSSAIRE.md)

---

[← Jour 29](../29_Day_Final_Project/29_final_project.md) | [📚 Sommaire](../README.md)

**☕ Une étape comprise vaut mieux qu’une journée cochée trop vite.**
