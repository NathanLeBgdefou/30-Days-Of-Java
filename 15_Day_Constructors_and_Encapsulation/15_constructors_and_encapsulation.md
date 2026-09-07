<div align="center">
<h1>☕ 30 Days Of Java : jour 15</h1>
<h3>Constructeurs et encapsulation</h3>
<p>Français · Java 21 · Windows &amp; VS Code</p>
</div>

[← Jour 14](../14_Day_Classes_and_Objects/14_classes_and_objects.md) | [📚 Sommaire](../README.md) | [Jour 16 →](../16_Day_Composition/16_composition.md)

![30 Days Of Java — apprendre à programmer](../images/30DaysOfJava_banner.svg)

![Progression : jour 15 sur 30](../images/progression-15.svg)

**Durée conseillée : 60 à 90 min.** Garde au moins une heure ; prends plus de temps pour coder si nécessaire.

📍 [Objectifs](#objectifs) · [Cours](#cours) · [Exemple](#exemple) · [Exercices](#exercices) · [Bilan](#bilan)

<a id="objectifs"></a>
## 🎯 Objectifs du jour

- Initialiser un objet grâce à son constructeur.
- Protéger les champs avec private.
- Faire respecter une règle lors de chaque modification.

> **Ton rythme :** 10 min de rappel et lecture, 15–20 min sur l’exemple, 30–45 min de pratique, 5 min de bilan. Les jours de projet demandent davantage.

<a id="cours"></a>
# 📘 Cours

## Construire un objet cohérent

Au jour 14, un objet était créé puis rempli champ par champ. On pouvait oublier une valeur. Un **constructeur** donne les informations nécessaires au moment du new : `new CompteCredits(30)`.

Le constructeur porte exactement le nom de sa classe et **n'a pas de type de retour**, pas même void. Dès que tu écris un constructeur, Java ne fournit plus automatiquement le constructeur sans argument. Si tu veux plusieurs façons de construire, tu peux définir plusieurs constructeurs aux paramètres différents.

## this : l'objet courant

Dans `this.credits = credits;`, la partie gauche désigne le champ de l'objet ; la partie droite désigne le paramètre. Le mot `this` permet de lever l'ambiguïté quand un paramètre porte le même nom que le champ.

L'écriture `credits = credits;` ne fait qu'affecter le paramètre à lui-même. Elle laisse le champ à sa valeur précédente. C'est un défaut fréquent qui compile sans produire le comportement attendu.

## Empêcher les modifications incohérentes

Un champ `private` n'est pas directement accessible depuis une autre classe ordinaire. On fournit des méthodes qui exposent seulement les opérations utiles : lire le solde, ajouter un montant positif, retirer si le solde est suffisant.

C'est l'**encapsulation** : regrouper l'état et les règles qui le protègent. Ajouter automatiquement un setter pour chaque champ ne garantit rien. `setCredits(-100)` resterait incohérent si le setter ne vérifie aucune règle.

## Une règle toujours vraie : l'invariant

Pour notre compte fictif de crédits, l'invariant est `credits >= 0`. Il doit être vrai après la construction et après chaque opération autorisée. Le constructeur refuse donc une valeur négative. La méthode retirer refuse un montant non positif ou supérieur au solde.

Pour refuser la construction, l'exemple emploie `throw new IllegalArgumentException(...)`. Lis-le pour l'instant comme « arrêter cet appel en signalant un argument invalide ». Le détail des exceptions et de leur interception est au jour 22. Pour un retrait impossible, nous choisissons ici un retour boolean : false signifie que l'état n'a pas changé.

| Opération | Résultat attendu |
| --- | --- |
| Construire avec 30 | Solde 30. |
| Retirer 6 | true, solde 24. |
| Retirer 50 | false, solde toujours 24. |
| Retirer 0 ou −3 | false, solde inchangé. |

Le choix entre exception et retour false dépend du contrat. L'essentiel est de documenter le refus et de ne pas laisser une opération échouée modifier partiellement l'objet.


<a id="exemple"></a>
## 🔎 Exemple complet, prêt à exécuter

[Ouvrir Jour15Exemple.java](./exemples/Jour15Exemple.java) · [Lire les exercices sans les réponses](./exercices/README.md)

Dans VS Code, ouvre le dossier `exemples` de cette journée, puis lance dans son terminal :

```powershell
java .\Jour15Exemple.java
```

```java
public class Jour15Exemple {
    public static void main(String[] args) {
        CompteCreditsJ15Exemple compte = new CompteCreditsJ15Exemple(30);
        System.out.println(compte.retirer(6));
        System.out.println(compte.getCredits());
        System.out.println(compte.retirer(50));
        System.out.println(compte.getCredits());
    }
}
class CompteCreditsJ15Exemple {
    private int credits;
    CompteCreditsJ15Exemple(int credits) {
        if (credits < 0) throw new IllegalArgumentException("Crédits négatifs");
        this.credits = credits;
    }
    int getCredits() {
        return credits;
    }
    boolean retirer(int montant) {
        if (montant <= 0 || montant > credits) return false;
        credits -= montant;
        return true;
    }
}
```

**Sortie du programme :**

```text
true
24
false
24
```

### Comprendre le déroulement

Le premier retrait réussit et change l'état. Le second renvoie false avant toute soustraction : la valeur reste 24. Depuis main, `compte.credits = -1` ne compilerait pas car le champ est private. Le getter permet de lire la valeur sans offrir un accès direct à sa modification.


### ⚠️ Pièges fréquents

- Un constructeur ne déclare aucun type de retour.
- `this.champ = parametre` n’est pas la même chose que `parametre = parametre`.
- Les règles doivent être appliquées lors de la construction comme lors des modifications.

<a id="exercices"></a>
## 💻 Exercices du jour

Essaie d’abord sans ouvrir les indices. Pour un exercice de code, crée ton propre fichier dans un dossier de travail et donne le même nom à la classe publique et au fichier. Les corrigés sont complets pour permettre une comparaison et une exécution indépendantes.

### Niveau 1 — comprendre et appliquer

#### Exercice 01 — Trouver l’invariant

Une classe stocke une note sur 20. Quelle règle doit être vraie après son constructeur et chaque modification ?


<details>
<summary>💡 Voir un indice</summary>

Exprime l’intervalle autorisé avec ses bornes.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

La note doit rester entre 0 et 20 inclus. Si le type est double, il faut aussi décider du traitement de NaN et des infinis ; pour cet exercice on utilise int.


</details>

#### Exercice 02 — Un prénom initialisé

Crée une classe Personne avec prénom private, constructeur et getter. Construis une personne `Nathan` et affiche son prénom.


<details>
<summary>💡 Voir un indice</summary>

Le constructeur affecte le champ via this.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le champ n’est plus rempli directement depuis main.


```java
public class Jour15Exercice02 {
    public static void main(String[] args) {
        PersonneJ15E02 personne = new PersonneJ15E02("Nathan");
        System.out.println(personne.getPrenom());
    }
}
class PersonneJ15E02 {
    private String prenom;
    PersonneJ15E02(String prenom) {
        this.prenom = prenom;
    }
    String getPrenom() {
        return prenom;
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour15Exercice02.java)

Résultat attendu :

```text
Nathan
```

</details>

### Niveau 2 — corriger et consolider

#### Exercice 03 — Protéger une note

Une note commence à 10. Une méthode modifier(int nouvelle) renvoie false si nouvelle est hors de [0, 20], sinon elle met à jour et renvoie true. Teste 21 puis 15.


<details>
<summary>💡 Voir un indice</summary>

Vérifie avant d’affecter le champ.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le premier appel ne modifie pas la note ; le second la porte à 15.


```java
public class Jour15Exercice03 {
    public static void main(String[] args) {
        NoteJ15E03 note = new NoteJ15E03();
        System.out.println(note.modifier(21));
        System.out.println(note.getValeur());
        System.out.println(note.modifier(15));
        System.out.println(note.getValeur());
    }
}
class NoteJ15E03 {
    private int valeur = 10;
    int getValeur() { return valeur; }
    boolean modifier(int nouvelle) {
        if (nouvelle < 0 || nouvelle > 20) return false;
        valeur = nouvelle;
        return true;
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour15Exercice03.java)

Résultat attendu :

```text
false
10
true
15
```

</details>

### Niveau 3 — résoudre un petit problème

#### Exercice 04 — Gérer un stock

Crée Stock(quantite), sans quantité négative. vendre(nombre) accepte seulement un nombre strictement positif inférieur ou égal au stock. Avec 5 unités, vends 2 puis tente 4. Affiche les succès et le stock final.


<details>
<summary>💡 Voir un indice</summary>

Le refus doit arriver avant de soustraire.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Après la vente de 2 unités, il en reste 3. La vente de 4 échoue et doit préserver ces 3 unités.


```java
public class Jour15Exercice04 {
    public static void main(String[] args) {
        StockJ15E04 stock = new StockJ15E04(5);
        System.out.println(stock.vendre(2));
        System.out.println(stock.vendre(4));
        System.out.println(stock.getQuantite());
    }
}
class StockJ15E04 {
    private int quantite;
    StockJ15E04(int quantite) {
        if (quantite < 0) throw new IllegalArgumentException("StockJ15E04 négatif");
        this.quantite = quantite;
    }
    int getQuantite() { return quantite; }
    boolean vendre(int nombre) {
        if (nombre <= 0 || nombre > quantite) return false;
        quantite -= nombre;
        return true;
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour15Exercice04.java)

Résultat attendu :

```text
true
false
3
```

</details>

<a id="bilan"></a>
## ✅ Avant de passer à la suite

- [ ] Je peux initialiser un objet grâce à son constructeur.
- [ ] Je peux protéger les champs avec private.
- [ ] Je peux faire respecter une règle lors de chaque modification.

- [ ] J’ai tenté les quatre exercices avant de comparer aux corrigés.
- [ ] Je peux expliquer une erreur rencontrée et la façon dont je l’ai corrigée.

[Noter ma progression](../PROGRESSION.md) · [Consulter le glossaire](../docs/GLOSSAIRE.md)

---

[← Jour 14](../14_Day_Classes_and_Objects/14_classes_and_objects.md) | [📚 Sommaire](../README.md) | [Jour 16 →](../16_Day_Composition/16_composition.md)

**☕ Une étape comprise vaut mieux qu’une journée cochée trop vite.**
