<div align="center">
<h1>☕ 30 Days Of Java : jour 21</h1>
<h3>Ensembles et dictionnaires</h3>
<p>Français · Java 21 · Windows &amp; VS Code</p>
</div>

[← Jour 20](../20_Day_ArrayList_and_Generics/20_arraylist_and_generics.md) | [📚 Sommaire](../README.md) | [Jour 22 →](../22_Day_Exceptions/22_exceptions.md)

![30 Days Of Java — apprendre à programmer](../images/30DaysOfJava_banner.svg)

![Progression : jour 21 sur 30](../images/progression-21.svg)

**Durée conseillée : 60 à 90 min.** Garde au moins une heure ; prends plus de temps pour coder si nécessaire.

📍 [Objectifs](#objectifs) · [Cours](#cours) · [Exemple](#exemple) · [Exercices](#exercices) · [Bilan](#bilan)

<a id="objectifs"></a>
## 🎯 Objectifs du jour

- Choisir entre List, Set et Map.
- Compter des occurrences avec une Map.
- Comprendre l’absence de garantie d’ordre dans HashSet et HashMap.

> **Ton rythme :** 10 min de rappel et lecture, 15–20 min sur l’exemple, 30–45 min de pratique, 5 min de bilan. Les jours de projet demandent davantage.

<a id="cours"></a>
# 📘 Cours

## Trois besoins différents

| Structure | Question à laquelle elle répond | Propriété importante |
| --- | --- | --- |
| List | Quelle valeur est à cette position ? | Ordre et doublons possibles. |
| Set | Cette valeur appartient-elle à l'ensemble ? | Pas de doublons selon equals. |
| Map | Quelle valeur est associée à cette clé ? | Une valeur par clé. |

Un Set ressemble à un `set` Python, une Map à un `dict`. La ressemblance ne garantit pas les mêmes règles d'ordre : **HashMap ne promet pas l'ordre d'insertion**, contrairement aux dictionnaires des versions modernes de Python.

## Set : ne conserver qu'une occurrence

`Set<String> matieres = new HashSet<>();` construit un ensemble. add renvoie true si l'élément a été ajouté, false s'il était déjà présent. contains teste l'appartenance. La notion de doublon dépend d'equals et hashCode, d'où le chapitre précédent.

HashSet ne garantit pas l'ordre de parcours. Si tu veux conserver l'ordre d'insertion, utilise LinkedHashSet. Pour un ordre trié, TreeSet est un autre outil, avec un coût et des règles de comparaison différents. Choisis l'ordre voulu explicitement au lieu de compter sur ce que montre une exécution.

## Map : associer une clé à une valeur

`Map<String, Integer> frequences = new HashMap<>();` associe un mot à son nombre d'apparitions. `put(cle, valeur)` ajoute ou remplace l'association. `get(cle)` renvoie la valeur, ou null si aucune association n'est trouvée dans notre cas.

`getOrDefault(mot, 0)` permet de commencer un compteur à zéro quand le mot est absent. On peut alors calculer une nouvelle valeur puis la réécrire avec put. Une Map ne met pas à jour une variable externe par magie.

Les clés sont uniques, pas forcément les valeurs. Deux matières peuvent avoir le même nombre d'heures. Mettre deux fois la même clé remplace sa valeur au lieu de créer une deuxième entrée.

## Parcourir les associations

`entrySet()` donne les paires clé-valeur. Dans la boucle, `entree.getKey()` lit la clé et `entree.getValue()` lit la valeur. Ce parcours évite une recherche séparée pour chaque clé.

Pour un exemple dont la sortie doit rester dans l'ordre des premières apparitions, nous choisissons LinkedHashMap. HashMap conviendrait au calcul si cet ordre n'avait aucune importance. Ne trie pas mentalement un résultat sans l'avoir demandé au programme.

## Des clés stables

Les String et les records composés de valeurs immuables font des clés faciles à raisonner. Un objet mutable dont hashCode dépend d'un champ modifié après insertion pose problème. La Map ne « suit » pas automatiquement toutes les mutations possibles d'un objet-clé.


<a id="exemple"></a>
## 🔎 Exemple complet, prêt à exécuter

[Ouvrir Jour21Exemple.java](./exemples/Jour21Exemple.java) · [Lire les exercices sans les réponses](./exercices/README.md)

Dans VS Code, ouvre le dossier `exemples` de cette journée, puis lance dans son terminal :

```powershell
java .\Jour21Exemple.java
```

```java
import java.util.LinkedHashMap;
import java.util.Map;
public class Jour21Exemple {
    public static void main(String[] args) {
        String[] mots = {"java", "python", "java", "algo", "java"};
        Map<String, Integer> frequences = new LinkedHashMap<>();
        for (String mot : mots) {
            int ancien = frequences.getOrDefault(mot, 0);
            frequences.put(mot, ancien + 1);
        }
        for (Map.Entry<String, Integer> entree : frequences.entrySet()) {
            System.out.println(entree.getKey() + " : " + entree.getValue());
        }
    }
}
```

**Sortie du programme :**

```text
java : 3
python : 1
algo : 1
```

### Comprendre le déroulement

La première apparition d'un mot lit le défaut 0, puis enregistre 1. Les suivantes récupèrent le compteur existant. LinkedHashMap conserve l'ordre des premières insertions, même quand la valeur associée à java est remplacée. La taille de la Map vaut trois, car elle contient trois clés distinctes.


### ⚠️ Pièges fréquents

- HashMap ne garantit ni le tri ni l’ordre d’insertion.
- get peut renvoyer null ; le déballage automatique en int peut alors échouer.
- Les éléments d’un Set et les clés d’une Map dépendent du contrat equals/hashCode.

<a id="exercices"></a>
## 💻 Exercices du jour

Essaie d’abord sans ouvrir les indices. Pour un exercice de code, crée ton propre fichier dans un dossier de travail et donne le même nom à la classe publique et au fichier. Les corrigés sont complets pour permettre une comparaison et une exécution indépendantes.

### Niveau 1 — comprendre et appliquer

#### Exercice 01 — Choisir la collection

Quelle collection pour une file de notes avec doublons, une liste de codes uniques et une association identifiant → prénom ?


<details>
<summary>💡 Voir un indice</summary>

Cherche si le besoin principal est la position, l’unicité ou l’association.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

List pour les notes, Set pour les codes uniques et Map pour l’association identifiant-prénom. Le choix d’implémentation dépend ensuite de l’ordre et des opérations utiles.


</details>

#### Exercice 02 — Retirer les doublons en gardant l’ordre

Transforme [Java, Maths, Java, Anglais] en un ensemble conservant la première apparition.


<details>
<summary>💡 Voir un indice</summary>

LinkedHashSet combine unicité et ordre d’insertion.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

On obtient trois valeurs dans l’ordre Java, Maths, Anglais.


```java
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
public class Jour21Exercice02 {
    public static void main(String[] args) {
        Set<String> uniques = new LinkedHashSet<>(List.of("Java", "Maths", "Java", "Anglais"));
        System.out.println(uniques);
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour21Exercice02.java)

Résultat attendu :

```text
[Java, Maths, Anglais]
```

</details>

### Niveau 2 — corriger et consolider

#### Exercice 03 — Une clé absente

Crée une Map associant Java à 3 heures. Lis les heures de Maths avec une valeur par défaut zéro.


<details>
<summary>💡 Voir un indice</summary>

Utilise getOrDefault pour éviter de déballer null.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le résultat est zéro, sans ajouter automatiquement une entrée Maths.


```java
import java.util.HashMap;
import java.util.Map;
public class Jour21Exercice03 {
    public static void main(String[] args) {
        Map<String, Integer> heures = new HashMap<>();
        heures.put("Java", 3);
        System.out.println(heures.getOrDefault("Maths", 0));
        System.out.println(heures.size());
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour21Exercice03.java)

Résultat attendu :

```text
0
1
```

</details>

### Niveau 3 — résoudre un petit problème

#### Exercice 04 — Trouver les doublons

Pour [A, B, A, C, B, B], construis un ensemble des codes déjà vus et un ensemble des codes apparus plusieurs fois. Affiche [A, B] dans l’ordre de découverte du doublon.


<details>
<summary>💡 Voir un indice</summary>

Si vus.add(code) renvoie false, le code était déjà connu.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le second ensemble évite de compter trois fois le même doublon.


```java
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
public class Jour21Exercice04 {
    public static void main(String[] args) {
        Set<String> vus = new HashSet<>();
        Set<String> doublons = new LinkedHashSet<>();
        for (String code : List.of("A", "B", "A", "C", "B", "B")) {
            if (!vus.add(code)) doublons.add(code);
        }
        System.out.println(doublons);
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour21Exercice04.java)

Résultat attendu :

```text
[A, B]
```

</details>

<a id="bilan"></a>
## ✅ Avant de passer à la suite

- [ ] Je peux choisir entre List, Set et Map.
- [ ] Je peux compter des occurrences avec une Map.
- [ ] Je peux comprendre l’absence de garantie d’ordre dans HashSet et HashMap.

- [ ] J’ai tenté les quatre exercices avant de comparer aux corrigés.
- [ ] Je peux expliquer une erreur rencontrée et la façon dont je l’ai corrigée.

[Noter ma progression](../PROGRESSION.md) · [Consulter le glossaire](../docs/GLOSSAIRE.md)

---

[← Jour 20](../20_Day_ArrayList_and_Generics/20_arraylist_and_generics.md) | [📚 Sommaire](../README.md) | [Jour 22 →](../22_Day_Exceptions/22_exceptions.md)

**☕ Une étape comprise vaut mieux qu’une journée cochée trop vite.**
