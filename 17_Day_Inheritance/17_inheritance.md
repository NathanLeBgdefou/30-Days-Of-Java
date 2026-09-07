<div align="center">
<h1>☕ 30 Days Of Java : jour 17</h1>
<h3>Héritage et redéfinition</h3>
<p>Français · Java 21 · Windows &amp; VS Code</p>
</div>

[← Jour 16](../16_Day_Composition/16_composition.md) | [📚 Sommaire](../README.md) | [Jour 18 →](../18_Day_Interfaces_and_Polymorphism/18_interfaces_and_polymorphism.md)

![30 Days Of Java — apprendre à programmer](../images/30DaysOfJava_banner.svg)

![Progression : jour 17 sur 30](../images/progression-17.svg)

**Durée conseillée : 60 à 90 min.** Garde au moins une heure ; prends plus de temps pour coder si nécessaire.

📍 [Objectifs](#objectifs) · [Cours](#cours) · [Exemple](#exemple) · [Exercices](#exercices) · [Bilan](#bilan)

<a id="objectifs"></a>
## 🎯 Objectifs du jour

- Lire une relation extends.
- Initialiser la partie parente avec super.
- Distinguer redéfinition, surcharge et masquage.

> **Ton rythme :** 10 min de rappel et lecture, 15–20 min sur l’exemple, 30–45 min de pratique, 5 min de bilan. Les jours de projet demandent davantage.

<a id="cours"></a>
# 📘 Cours

## Une relation de spécialisation

Une classe peut **étendre** une autre classe : `class Etudiant extends Personne`. Elle hérite des membres accessibles de la classe parente et peut ajouter des éléments. Une classe Java n'étend directement qu'une seule classe ; elle pourra en revanche implémenter plusieurs interfaces.

L'héritage doit exprimer une relation utile de spécialisation. Si le code qui accepte une Personne peut aussi travailler correctement avec un Etudiant, la substitution est plausible. Réutiliser un champ ne suffit pas à justifier une hiérarchie.

## Le constructeur parent

Le constructeur d'Etudiant appelle `super(prenom)` pour initialiser la partie Personne. Dans la syntaxe Java 21 utilisée ici, cet appel explicite se place au début du constructeur. Si tu ne l'écris pas, Java essaie d'appeler `super()` sans argument. Cela échoue si le parent n'a pas de constructeur accessible correspondant.

Les constructeurs ne sont pas hérités comme des méthodes ordinaires. Chaque classe décrit comment ses propres instances sont initialisées.

## Redéfinir une méthode

Une sous-classe peut fournir une nouvelle implémentation d'une méthode d'instance héritée avec la même signature compatible. L'annotation `@Override` demande au compilateur de vérifier que tu redéfinis bien une méthode existante. Une faute de nom devient alors une erreur visible au lieu de créer silencieusement une nouvelle méthode.

`super.presentation()` appelle explicitement la version du parent. On peut s'en servir pour conserver une partie du comportement et la compléter.

| Notion | Exemple de différence |
| --- | --- |
| Surcharge | Même nom, paramètres différents. |
| Redéfinition | Une sous-classe remplace une méthode d'instance héritée. |
| Masquage d'une méthode static | Choix lié au type utilisé dans le code ; ce n'est pas le polymorphisme d'instance. |

## Garder les champs privés

Un champ private du parent n'est pas directement accessible au code de la sous-classe. Le parent peut fournir des méthodes protégées ou publiques adaptées. `protected` autorise notamment l'accès aux sous-classes, avec des règles qui dépendent aussi du package. Pour nos petits exemples, des getters suffisent.

## Le type déclaré et l'objet réel

`Personne p = new Etudiant(...)` est permis : la variable est déclarée Personne, mais l'objet réel est un Etudiant. Un appel à une méthode d'instance redéfinie utilisera l'implémentation de l'objet réel. En revanche, le type déclaré limite les méthodes que le compilateur autorise à appeler par cette variable.

Une méthode `final` ne peut pas être redéfinie. Une classe `final` ne peut pas être étendue. Ces mots peuvent protéger un choix de conception ; ils ne doivent pas être ajoutés sans comprendre le besoin.


<a id="exemple"></a>
## 🔎 Exemple complet, prêt à exécuter

[Ouvrir Jour17Exemple.java](./exemples/Jour17Exemple.java) · [Lire les exercices sans les réponses](./exercices/README.md)

Dans VS Code, ouvre le dossier `exemples` de cette journée, puis lance dans son terminal :

```powershell
java .\Jour17Exemple.java
```

```java
public class Jour17Exemple {
    public static void main(String[] args) {
        PersonneJ17Exemple personne = new EtudiantJ17Exemple("Nathan", "Informatique");
        System.out.println(personne.presentation());
    }
}
class PersonneJ17Exemple {
    private final String prenom;
    PersonneJ17Exemple(String prenom) { this.prenom = prenom; }
    String presentation() { return prenom; }
}
class EtudiantJ17Exemple extends PersonneJ17Exemple {
    private final String filiere;
    EtudiantJ17Exemple(String prenom, String filiere) {
        super(prenom);
        this.filiere = filiere;
    }
    @Override
    String presentation() {
        return super.presentation() + " étudie " + filiere;
    }
}
```

**Sortie du programme :**

```text
Nathan étudie Informatique
```

### Comprendre le déroulement

La variable est de type Personne, mais presentation exécute la redéfinition d'Etudiant. Cette redéfinition appelle à son tour super.presentation pour récupérer le prénom. Le résultat montre la différence entre le contrat visible dans le code et le comportement de l'objet créé.


### ⚠️ Pièges fréquents

- Une faute dans le nom d’une méthode peut créer une autre méthode : @Override aide à la repérer.
- Les constructeurs ne sont pas hérités.
- Une méthode static n’est pas redéfinie dynamiquement comme une méthode d’instance.

<a id="exercices"></a>
## 💻 Exercices du jour

Essaie d’abord sans ouvrir les indices. Pour un exercice de code, crée ton propre fichier dans un dossier de travail et donne le même nom à la classe publique et au fichier. Les corrigés sont complets pour permettre une comparaison et une exécution indépendantes.

### Niveau 1 — comprendre et appliquer

#### Exercice 01 — Surcharge ou redéfinition ?

Une sous-classe reprend parler() avec le même type de retour. Ailleurs, une classe propose parler(String texte). Nomme les deux mécanismes.


<details>
<summary>💡 Voir un indice</summary>

Compare la signature et la relation entre classes.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Reprendre la méthode d’instance héritée avec la même signature est une redéfinition. Ajouter une version avec un paramètre String est une surcharge.


</details>

#### Exercice 02 — Une spécialisation simple

Crée Animal avec cri() renvoyant `?`, puis Chat qui redéfinit cri() pour renvoyer `Miaou`. Appelle cette méthode via une variable Animal.


<details>
<summary>💡 Voir un indice</summary>

Utilise extends et @Override.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le comportement dépend de l’objet Chat créé.


```java
public class Jour17Exercice02 {
    public static void main(String[] args) {
        AnimalJ17E02 animal = new ChatJ17E02();
        System.out.println(animal.cri());
    }
}
class AnimalJ17E02 { String cri() { return "?"; } }
class ChatJ17E02 extends AnimalJ17E02 {
    @Override
    String cri() { return "Miaou"; }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour17Exercice02.java)

Résultat attendu :

```text
Miaou
```

</details>

### Niveau 2 — corriger et consolider

#### Exercice 03 — Initialiser le parent

Crée Document(titre), puis Cours(titre, duree). La description de Cours complète celle du parent. Attendu : `Java — 60 min`.


<details>
<summary>💡 Voir un indice</summary>

Le constructeur Cours appelle super(titre) en premier.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le parent gère le titre ; la sous-classe ajoute la durée.


```java
public class Jour17Exercice03 {
    public static void main(String[] args) {
        System.out.println(new CoursJ17E03("Java", 60).description());
    }
}
class DocumentJ17E03 {
    private final String titre;
    DocumentJ17E03(String titre) { this.titre = titre; }
    String description() { return titre; }
}
class CoursJ17E03 extends DocumentJ17E03 {
    private final int duree;
    CoursJ17E03(String titre, int duree) {
        super(titre);
        this.duree = duree;
    }
    @Override
    String description() { return super.description() + " — " + duree + " min"; }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour17Exercice03.java)

Résultat attendu :

```text
Java — 60 min
```

</details>

### Niveau 3 — résoudre un petit problème

#### Exercice 04 — Un tableau polymorphe

Crée Animal, Chat et Chien. Parcours un tableau Animal[] contenant un chat puis un chien et affiche leurs cris.


<details>
<summary>💡 Voir un indice</summary>

Le tableau peut contenir des objets de sous-types différents.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

La même instruction animal.cri() appelle les implémentations adaptées à chaque objet.


```java
public class Jour17Exercice04 {
    public static void main(String[] args) {
        AnimalJ17E04[] animaux = {new ChatJ17E04(), new ChienJ17E04()};
        for (AnimalJ17E04 animal : animaux) System.out.println(animal.cri());
    }
}
class AnimalJ17E04 { String cri() { return "?"; } }
class ChatJ17E04 extends AnimalJ17E04 {
    @Override String cri() { return "Miaou"; }
}
class ChienJ17E04 extends AnimalJ17E04 {
    @Override String cri() { return "Ouaf"; }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour17Exercice04.java)

Résultat attendu :

```text
Miaou
Ouaf
```

</details>

<a id="bilan"></a>
## ✅ Avant de passer à la suite

- [ ] Je peux lire une relation extends.
- [ ] Je peux initialiser la partie parente avec super.
- [ ] Je peux distinguer redéfinition, surcharge et masquage.

- [ ] J’ai tenté les quatre exercices avant de comparer aux corrigés.
- [ ] Je peux expliquer une erreur rencontrée et la façon dont je l’ai corrigée.

[Noter ma progression](../PROGRESSION.md) · [Consulter le glossaire](../docs/GLOSSAIRE.md)

---

[← Jour 16](../16_Day_Composition/16_composition.md) | [📚 Sommaire](../README.md) | [Jour 18 →](../18_Day_Interfaces_and_Polymorphism/18_interfaces_and_polymorphism.md)

**☕ Une étape comprise vaut mieux qu’une journée cochée trop vite.**
