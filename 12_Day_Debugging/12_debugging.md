<div align="center">
<h1>☕ 30 Days Of Java : jour 12</h1>
<h3>Déboguer et vérifier son raisonnement</h3>
<p>Français · Java 21 · Windows &amp; VS Code</p>
</div>

[← Jour 11](../11_Day_Scope_and_Parameters/11_scope_and_parameters.md) | [📚 Sommaire](../README.md) | [Jour 13 →](../13_Day_First_Project/13_first_project.md)

![30 Days Of Java — apprendre à programmer](../images/30DaysOfJava_banner.svg)

![Progression : jour 12 sur 30](../images/progression-12.svg)

**Durée conseillée : 60 à 90 min.** Garde au moins une heure ; prends plus de temps pour coder si nécessaire.

📍 [Objectifs](#objectifs) · [Cours](#cours) · [Exemple](#exemple) · [Exercices](#exercices) · [Bilan](#bilan)

<a id="objectifs"></a>
## 🎯 Objectifs du jour

- Distinguer erreur de compilation, d’exécution et de logique.
- Observer les variables avec le débogueur de VS Code.
- Choisir des cas de test qui révèlent les erreurs.

> **Ton rythme :** 10 min de rappel et lecture, 15–20 min sur l’exemple, 30–45 min de pratique, 5 min de bilan. Les jours de projet demandent davantage.

<a id="cours"></a>
# 📘 Cours

## Trois familles d'erreurs

| Famille | Exemple | Quand la vois-tu ? |
| --- | --- | --- |
| Compilation | Point-virgule oublié, type incompatible | Avant que le programme démarre. |
| Exécution | Lecture de tableau avec un indice invalide | Quand cette instruction est atteinte. |
| Logique | Une moyenne tronquée par division entière | Le programme termine mais donne un résultat faux. |

Commence par le premier message significatif du compilateur : une accolade manquante peut provoquer plusieurs erreurs plus bas. Pour une erreur à l'exécution, la **trace de pile** liste les appels concernés avec les fichiers et les lignes. Cherche la première ligne appartenant à ton code, pas seulement le dernier texte affiché.

## Voir le programme avancer dans VS Code

1. Ouvre le dossier contenant l'exemple et son fichier Java. Vérifie qu'il s'exécute normalement dans le terminal.
2. Clique dans la marge à gauche du numéro de ligne de `somme += note;` pour poser un **point d'arrêt**.
3. Lance **Debug** au-dessus de main, ou utilise **Exécuter → Démarrer le débogage**. Le programme s'arrête avant la ligne marquée.
4. Observe les **Variables** : quelle note est courante ? Quelle somme a été accumulée ?
5. Utilise **Pas à pas principal** (`F10`) pour avancer une instruction. **Pas à pas détaillé** (`F11`) entre dans une méthode ; **Continuer** (`F5`) va au point d'arrêt suivant. Certains claviers demandent aussi la touche Fn.

Le débogueur ne répare pas le code. Il permet de comparer **ce que tu pensais qu'il ferait** avec **ce qu'il fait**. Avant chaque pas, prédis la prochaine valeur, puis vérifie-la.

## Choisir des cas qui ont une raison d'être

Pour une règle « valide à partir de 10 », teste 9, 10 et 11. Pour une moyenne de tableau, teste une valeur, plusieurs valeurs et aucun élément. Pour un maximum, teste des valeurs toutes négatives. Chaque cas vise un risque concret.

Une exécution sans message d'erreur ne prouve pas la justesse du programme. On compare un résultat obtenu à un **résultat attendu**, calculé indépendamment. Si tu fais recopier au test le même calcul erroné que le programme, les deux peuvent être d'accord et faux.

## Réduire le problème

Si un grand programme échoue, reproduis le défaut avec les données les plus petites possibles. Une liste de deux notes suffit souvent à révéler une division entière. Une liste vide suffit à révéler un accès à la première case.

Après correction, relance le cas qui échouait, puis les cas voisins utiles. Ne corrige pas en changeant arbitrairement le résultat attendu : cherche si c'est la règle, le programme ou le test qui est incorrect.

Aujourd'hui, les tests sont des comparaisons lisibles. Au jour 28, nous les rendrons automatiques avec un arrêt explicite si une attente n'est pas satisfaite.


<a id="exemple"></a>
## 🔎 Exemple complet, prêt à exécuter

[Ouvrir Jour12Exemple.java](./exemples/Jour12Exemple.java) · [Lire les exercices sans les réponses](./exercices/README.md)

Dans VS Code, ouvre le dossier `exemples` de cette journée, puis lance dans son terminal :

```powershell
java .\Jour12Exemple.java
```

```java
public class Jour12Exemple {
    static double moyenne(int[] notes) {
        int somme = 0;
        for (int note : notes) {
            somme += note; // Place un point d'arrêt ici.
        }
        return (double) somme / notes.length; // Contrat : tableau non vide.
    }
    public static void main(String[] args) {
        double obtenu = moyenne(new int[] {10, 11});
        double attendu = 10.5;
        System.out.println("Attendu : " + attendu);
        System.out.println("Obtenu : " + obtenu);
        System.out.println("Test réussi : " + (Math.abs(obtenu - attendu) < 0.000001));
    }
}
```

**Sortie du programme :**

```text
Attendu : 10.5
Obtenu : 10.5
Test réussi : true
```

### Comprendre le déroulement

Le tableau {10, 11} est choisi pour avoir une moyenne non entière. Retire temporairement `(double)` : le test passe à false, ce qui révèle le défaut. Remets le cast pour corriger. `Math.abs` donne la valeur absolue de l'écart ; la tolérance évite d'exiger une égalité binaire parfaite pour tous les calculs décimaux.


### ⚠️ Pièges fréquents

- Un programme qui compile peut encore être faux.
- Le débogueur s’arrête avant d’exécuter la ligne marquée.
- Une tolérance numérique doit correspondre à la précision utile ; elle ne doit pas cacher une grosse erreur.

<a id="exercices"></a>
## 💻 Exercices du jour

Essaie d’abord sans ouvrir les indices. Pour un exercice de code, crée ton propre fichier dans un dossier de travail et donne le même nom à la classe publique et au fichier. Les corrigés sont complets pour permettre une comparaison et une exécution indépendantes.

### Niveau 1 — comprendre et appliquer

#### Exercice 01 — Classer les défauts

Classe ces erreurs : `int n = "3";`, lecture de la case 5 d’un tableau de longueur 2, et moyenne 5 / 2 stockée dans un double.


<details>
<summary>💡 Voir un indice</summary>

Observe à quel moment Java peut détecter le problème.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Type incompatible : compilation. Indice hors limites : exécution. Division entière au lieu d’une moyenne décimale : logique.


</details>

#### Exercice 02 — Observer un cumul

Pour {3, 5, 2}, affiche la somme après chaque ajout. Retrouve ensuite ces mêmes valeurs dans le débogueur.


<details>
<summary>💡 Voir un indice</summary>

L’affichage provisoire se place après l’ajout.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Les états successifs sont 3, 8 et 10. On peut retirer ces messages quand le raisonnement est validé.


```java
public class Jour12Exercice02 {
    public static void main(String[] args) {
        int somme = 0;
        for (int n : new int[] {3, 5, 2}) {
            somme += n;
            System.out.println(somme);
        }
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour12Exercice02.java)

Résultat attendu :

```text
3
8
10
```

</details>

### Niveau 2 — corriger et consolider

#### Exercice 03 — Un test qui détecte la borne

Écris valide(int note) avec le seuil 10 inclus. Affiche la réponse pour 9, 10 et 11.


<details>
<summary>💡 Voir un indice</summary>

La note 10 distingue >= de >.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Les trois cas encadrent précisément la frontière de la règle.


```java
public class Jour12Exercice03 {
    static boolean valide(int note) {
        return note >= 10;
    }
    public static void main(String[] args) {
        System.out.println(valide(9));
        System.out.println(valide(10));
        System.out.println(valide(11));
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour12Exercice03.java)

Résultat attendu :

```text
false
true
true
```

</details>

### Niveau 3 — résoudre un petit problème

#### Exercice 04 — Débusquer une division entière

Écris demi(int n) avec un retour double. Vérifie dans main que demi(5) donne 2.5 et demi(0) donne 0.0. Affiche deux booléens de vérification.


<details>
<summary>💡 Voir un indice</summary>

Il faut faire une division décimale à l’intérieur de la méthode.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le cas impair détecte la troncature ; le cas zéro vérifie un cas neutre. Ces deux résultats sont représentables exactement, donc l’égalité directe suffit ici.


```java
public class Jour12Exercice04 {
    static double demi(int n) {
        return n / 2.0;
    }
    public static void main(String[] args) {
        System.out.println(demi(5) == 2.5);
        System.out.println(demi(0) == 0.0);
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour12Exercice04.java)

Résultat attendu :

```text
true
true
```

</details>

<a id="bilan"></a>
## ✅ Avant de passer à la suite

- [ ] Je peux distinguer erreur de compilation, d’exécution et de logique.
- [ ] Je peux observer les variables avec le débogueur de VS Code.
- [ ] Je peux choisir des cas de test qui révèlent les erreurs.

- [ ] J’ai tenté les quatre exercices avant de comparer aux corrigés.
- [ ] Je peux expliquer une erreur rencontrée et la façon dont je l’ai corrigée.

[Noter ma progression](../PROGRESSION.md) · [Consulter le glossaire](../docs/GLOSSAIRE.md)

### 📎 Pour approfondir

- [Déboguer Java dans VS Code](https://code.visualstudio.com/docs/java/java-debugging)

---

[← Jour 11](../11_Day_Scope_and_Parameters/11_scope_and_parameters.md) | [📚 Sommaire](../README.md) | [Jour 13 →](../13_Day_First_Project/13_first_project.md)

**☕ Une étape comprise vaut mieux qu’une journée cochée trop vite.**
