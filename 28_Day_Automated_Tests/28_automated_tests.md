<div align="center">
<h1>☕ 30 Days Of Java : jour 28</h1>
<h3>Tests automatisés et préparation du projet</h3>
<p>Français · Java 21 · Windows &amp; VS Code</p>
</div>

[← Jour 27](../27_Day_Dates_and_Enums/27_dates_and_enums.md) | [📚 Sommaire](../README.md) | [Jour 29 →](../29_Day_Final_Project/29_final_project.md)

![30 Days Of Java — apprendre à programmer](../images/30DaysOfJava_banner.svg)

![Progression : jour 28 sur 30](../images/progression-28.svg)

**Durée conseillée : 60 à 90 min.** Garde au moins une heure ; prends plus de temps pour coder si nécessaire.

📍 [Objectifs](#objectifs) · [Cours](#cours) · [Exemple](#exemple) · [Exercices](#exercices) · [Bilan](#bilan)

<a id="objectifs"></a>
## 🎯 Objectifs du jour

- Transformer une attente en test exécutable.
- Tester les bornes, les refus et les effets sur l’état.
- Préparer un contrat de tâche de révision.

> **Ton rythme :** 10 min de rappel et lecture, 15–20 min sur l’exemple, 30–45 min de pratique, 5 min de bilan. Les jours de projet demandent davantage.

<a id="cours"></a>
# 📘 Cours

## Un test peut s'arrêter tout seul quand le résultat est faux

Au jour 12, nous comparions les résultats en les affichant. Pour automatiser, une méthode de vérification peut déclencher AssertionError lorsque l'attente n'est pas satisfaite. Le processus échoue alors clairement au lieu d'afficher un simple false au milieu d'autres messages.

Nous utilisons un petit outil écrit en Java pour que le parcours fonctionne **sans dépendance externe**. Le principe reste le même dans JUnit : préparer les données, appeler le comportement, vérifier le résultat. JUnit apporte ensuite une organisation et des assertions plus riches.

## Arrange, Act, Assert

| Étape | Exemple |
| --- | --- |
| Préparer (Arrange) | Construire une tâche à faire avec une échéance donnée. |
| Agir (Act) | Appeler sa méthode terminer. |
| Vérifier (Assert) | Constater l'état terminé et la conservation des autres données. |

Un bon test vérifie une règle observable. Il n'a pas besoin d'exiger le nom d'une variable interne ou l'exacte forme d'une boucle. Si on améliore l'implémentation en gardant le contrat, le test devrait rester utile.

## Ne pas dépendre du jour réel

Pour la règle de retard, utilise une référence fixe, puis teste la veille, le jour même et le lendemain. Ajoute le cas d'une tâche terminée. Ces cas ciblent une condition frontière et une condition d'état.

Pour les fichiers, utilise un dossier temporaire. Le test ne doit pas lire ou remplacer le carnet réel de l'utilisateur. Il écrit ses propres données, les relit, compare et nettoie uniquement ce qu'il a créé.

## Vérifier les échecs attendus

Si un constructeur doit refuser un titre vide, le test doit vérifier qu'il refuse réellement. Ne considère pas toute exception comme une réussite : un NullPointerException accidentel n'est pas forcément le refus prévu. On cherche la famille d'exception attendue et on laisse remonter les autres.

Le mot-clé Java `assert` existe aussi, mais ses vérifications ne sont pas activées par défaut à l'exécution : il faut `-ea`. Notre méthode verifier lance explicitement AssertionError et reste active sans option, ce qui évite ce piège pour les premières vérifications.

## Contrat du projet final

Une tâche possède un identifiant positif, un titre non vide sur une seule ligne, une échéance LocalDate et un Etat. Les objets seront immuables : terminer une tâche produit une nouvelle tâche avec le même identifiant, titre et échéance, mais l'état TERMINEE.

Le carnet gère la collection et l'unicité des identifiants. Le stockage gère les fichiers. Le menu gère les saisies et les messages. Cette séparation permet de tester la règle « terminer une tâche » sans simuler tout le clavier.

Le [sujet du projet](../29_Day_Final_Project/projet/SUJET.md) détaille les fonctionnalités. Lis-le aujourd'hui, puis garde le corrigé complet fermé le temps de dessiner tes classes.


<a id="exemple"></a>
## 🔎 Exemple complet, prêt à exécuter

[Ouvrir Jour28Exemple.java](./exemples/Jour28Exemple.java) · [Lire les exercices sans les réponses](./exercices/README.md)

Dans VS Code, ouvre le dossier `exemples` de cette journée, puis lance dans son terminal :

```powershell
java .\Jour28Exemple.java
```

```java
public class Jour28Exemple {
    static void verifier(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
    static boolean estValide(int note) {
        return note >= 0 && note <= 20;
    }
    public static void main(String[] args) {
        verifier(!estValide(-1), "-1 doit être refusé");
        verifier(estValide(0), "0 doit être accepté");
        verifier(estValide(20), "20 doit être accepté");
        verifier(!estValide(21), "21 doit être refusé");
        System.out.println("4 vérifications réussies");
    }
}
```

**Sortie du programme :**

```text
4 vérifications réussies
```

### Comprendre le déroulement

Les quatre valeurs entourent les deux bornes du domaine. Une seule condition fausse déclenche une erreur et empêche le message final de réussite. Modifie temporairement <= 20 en < 20 : le test portant sur 20 doit échouer. Puis rétablis le code correct. Tu prouves ainsi que le test est capable de détecter ce défaut.


### ⚠️ Pièges fréquents

- Un test qui ne peut jamais échouer ne vérifie rien.
- Ne capture pas toutes les exceptions dans le test pour les annoncer comme un refus normal.
- Utiliser LocalDate.now dans une règle testée peut rendre les résultats dépendants du calendrier.

<a id="exercices"></a>
## 💻 Exercices du jour

Essaie d’abord sans ouvrir les indices. Pour un exercice de code, crée ton propre fichier dans un dossier de travail et donne le même nom à la classe publique et au fichier. Les corrigés sont complets pour permettre une comparaison et une exécution indépendantes.

### Niveau 1 — comprendre et appliquer

#### Exercice 01 — Reconnaître un test fragile

Pourquoi attendre une valeur calculée avec exactement la même expression que le code testé peut-il laisser passer une erreur ?


<details>
<summary>💡 Voir un indice</summary>

Le défaut peut avoir été copié dans le calcul attendu.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Les deux expressions peuvent produire le même résultat faux. Choisis des données simples dont le résultat attendu est déterminé indépendamment.


</details>

#### Exercice 02 — Vérifier une fonction

Écris doubleValeur(int n), puis vérifie 0 → 0, 3 → 6 et −2 → −4 avec une méthode verifier.


<details>
<summary>💡 Voir un indice</summary>

Chaque appel de verifier doit avoir un message identifiant le cas.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Les trois signes sont couverts pour cette opération sur de petits int.


```java
public class Jour28Exercice02 {
    static int doubleValeur(int n) { return n * 2; }
    static void verifier(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
    public static void main(String[] args) {
        verifier(doubleValeur(0) == 0, "zéro");
        verifier(doubleValeur(3) == 6, "positif");
        verifier(doubleValeur(-2) == -4, "négatif");
        System.out.println("3 vérifications réussies");
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour28Exercice02.java)

Résultat attendu :

```text
3 vérifications réussies
```

</details>

### Niveau 2 — corriger et consolider

#### Exercice 03 — Tester un refus précis

Une méthode exigerPositif(int n) lance IllegalArgumentException si n <= 0. Écris un test qui vérifie le refus de zéro et l’acceptation de 1.


<details>
<summary>💡 Voir un indice</summary>

Si l’appel sur zéro termine normalement, le test doit échouer lui-même.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

AssertionError n’est pas capturée par le catch IllegalArgumentException, donc une absence de refus est bien visible.


```java
public class Jour28Exercice03 {
    static void exigerPositif(int n) {
        if (n <= 0) throw new IllegalArgumentException("Positif attendu");
    }
    public static void main(String[] args) {
        try {
            exigerPositif(0);
            throw new AssertionError("Zéro accepté par erreur");
        } catch (IllegalArgumentException attendu) {
            // Le refus précis demandé a bien eu lieu.
        }
        exigerPositif(1);
        System.out.println("Refus et acceptation vérifiés");
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour28Exercice03.java)

Résultat attendu :

```text
Refus et acceptation vérifiés
```

</details>

### Niveau 3 — résoudre un petit problème

#### Exercice 04 — Tester les bornes du retard

Écris enRetard(date, terminee, reference), puis vérifie la veille à faire, le jour même à faire et la veille terminée.


<details>
<summary>💡 Voir un indice</summary>

Le retard combine une comparaison stricte et un état non terminé.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Les tests ciblent les deux parties de la règle et la frontière temporelle.


```java
import java.time.LocalDate;
public class Jour28Exercice04 {
    static boolean enRetard(LocalDate date, boolean terminee, LocalDate reference) {
        return !terminee && date.isBefore(reference);
    }
    static void verifier(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
    public static void main(String[] args) {
        LocalDate ref = LocalDate.of(2026, 9, 15);
        verifier(enRetard(ref.minusDays(1), false, ref), "veille à faire");
        verifier(!enRetard(ref, false, ref), "aujourd'hui");
        verifier(!enRetard(ref.minusDays(1), true, ref), "déjà terminée");
        System.out.println("Retard : 3 vérifications réussies");
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour28Exercice04.java)

Résultat attendu :

```text
Retard : 3 vérifications réussies
```

</details>

<a id="bilan"></a>
## ✅ Avant de passer à la suite

- [ ] Je peux transformer une attente en test exécutable.
- [ ] Je peux tester les bornes, les refus et les effets sur l’état.
- [ ] Je peux préparer un contrat de tâche de révision.

- [ ] J’ai tenté les quatre exercices avant de comparer aux corrigés.
- [ ] Je peux expliquer une erreur rencontrée et la façon dont je l’ai corrigée.

[Noter ma progression](../PROGRESSION.md) · [Consulter le glossaire](../docs/GLOSSAIRE.md)

### 📎 Pour approfondir

- [Guide JUnit pour aller plus loin](https://docs.junit.org/current/user-guide/)

---

[← Jour 27](../27_Day_Dates_and_Enums/27_dates_and_enums.md) | [📚 Sommaire](../README.md) | [Jour 29 →](../29_Day_Final_Project/29_final_project.md)

**☕ Une étape comprise vaut mieux qu’une journée cochée trop vite.**
