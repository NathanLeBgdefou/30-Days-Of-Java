<div align="center">
<h1>☕ 30 Days Of Java : jour 19</h1>
<h3>Égalité, null et objets immuables</h3>
<p>Français · Java 21 · Windows &amp; VS Code</p>
</div>

[← Jour 18](../18_Day_Interfaces_and_Polymorphism/18_interfaces_and_polymorphism.md) | [📚 Sommaire](../README.md) | [Jour 20 →](../20_Day_ArrayList_and_Generics/20_arraylist_and_generics.md)

![30 Days Of Java — apprendre à programmer](../images/30DaysOfJava_banner.svg)

![Progression : jour 19 sur 30](../images/progression-19.svg)

**Durée conseillée : 60 à 90 min.** Garde au moins une heure ; prends plus de temps pour coder si nécessaire.

📍 [Objectifs](#objectifs) · [Cours](#cours) · [Exemple](#exemple) · [Exercices](#exercices) · [Bilan](#bilan)

<a id="objectifs"></a>
## 🎯 Objectifs du jour

- Distinguer identité et égalité de contenu.
- Respecter le lien entre equals et hashCode.
- Gérer une référence absente et découvrir record.

> **Ton rythme :** 10 min de rappel et lecture, 15–20 min sur l’exemple, 30–45 min de pratique, 5 min de bilan. Les jours de projet demandent davantage.

<a id="cours"></a>
# 📘 Cours

## Même objet ou même valeur ?

Deux objets peuvent contenir les mêmes informations sans être le même objet. Pour les références, `==` compare l'identité. La méthode `equals` peut définir une égalité de valeur adaptée à une classe. Sans redéfinition, equals hérité d'Object se comporte comme une comparaison d'identité.

Pour String, equals compare déjà le texte. Pour une classe Etudiant, il faut choisir la règle : deux étudiants sont-ils égaux parce qu'ils ont le même identifiant ? Leur prénom ne suffit probablement pas. Cette décision dépend du domaine.

## Le contrat hashCode

`hashCode()` produit un entier utilisé notamment par HashSet et HashMap. **Deux objets égaux avec equals doivent avoir le même hashCode.** L'inverse n'est pas obligatoire : deux objets différents peuvent avoir le même hash, ce qu'on appelle une collision.

Si tu redéfinis equals à la main, redéfinis aussi hashCode de manière cohérente. Évite de modifier les champs utilisés par ces méthodes pendant qu'un objet sert de clé ou d'élément dans une collection de hachage : il pourrait devenir introuvable de manière surprenante.

## null n'est pas un objet vide

Une référence peut valoir `null`, c'est-à-dire ne désigner aucun objet. Ce n'est ni la chaîne vide `""`, ni le nombre zéro. Appeler `texte.length()` quand texte vaut null déclenche une NullPointerException.

Pour une comparaison tolérant l'absence, `Objects.equals(a, b)` traite les références null. Pour interdire l'absence dans un constructeur, `Objects.requireNonNull(valeur, "message")` échoue immédiatement. Choisis selon le contrat : absence autorisée ou objet obligatoire.

Le court-circuit aide aussi : `texte != null && !texte.isBlank()` n'appelle pas isBlank sur null. Ne transforme pas systématiquement toute absence en chaîne vide, car tu pourrais masquer une donnée réellement manquante.

## record pour une donnée de valeur

Un `record` déclare simplement un regroupement de valeurs. `record Point(int x, int y) {}` fournit un constructeur, des accesseurs x() et y(), ainsi que equals, hashCode et toString fondés sur les composants.

Les composants sont stockés dans des champs final. Le record est **superficiellement immuable** : si un composant est un tableau modifiable, ce tableau peut encore changer. Un record composé d'int ou de String immuables est plus facile à raisonner.

Les records sont utiles pour des coordonnées, résultats ou identifiants. Ils ne remplacent pas automatiquement toutes les classes, notamment celles dont l'identité et l'état évolutif sont essentiels. Dans les études, apprends aussi à reconnaître une implémentation classique d'equals et hashCode.


<a id="exemple"></a>
## 🔎 Exemple complet, prêt à exécuter

[Ouvrir Jour19Exemple.java](./exemples/Jour19Exemple.java) · [Lire les exercices sans les réponses](./exercices/README.md)

Dans VS Code, ouvre le dossier `exemples` de cette journée, puis lance dans son terminal :

```powershell
java .\Jour19Exemple.java
```

```java
import java.util.Objects;
public class Jour19Exemple {
    public static void main(String[] args) {
        PointJ19Exemple a = new PointJ19Exemple(2, 3);
        PointJ19Exemple b = new PointJ19Exemple(2, 3);
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(a.hashCode() == b.hashCode());
        String inconnu = null;
        System.out.println(Objects.equals(inconnu, "Java"));
        System.out.println(a.x());
    }
}
record PointJ19Exemple(int x, int y) {}
```

**Sortie du programme :**

```text
false
true
true
false
2
```

### Comprendre le déroulement

Les deux new donnent des identités différentes, d'où false pour ==. Le record compare les deux composants et trouve les valeurs égales. Le même hash est alors obligatoire. Objects.equals accepte inconnu sans tenter d'appeler une méthode sur null. L'accesseur du record s'appelle x(), pas getX().


### ⚠️ Pièges fréquents

- Des hashCode égaux ne prouvent pas que les objets sont égaux.
- Un record contenant une référence mutable n’est pas profondément immuable.
- null signifie absence de référence, pas une String contenant le mot « null ».

<a id="exercices"></a>
## 💻 Exercices du jour

Essaie d’abord sans ouvrir les indices. Pour un exercice de code, crée ton propre fichier dans un dossier de travail et donne le même nom à la classe publique et au fichier. Les corrigés sont complets pour permettre une comparaison et une exécution indépendantes.

### Niveau 1 — comprendre et appliquer

#### Exercice 01 — Une implication à retenir

Si a.equals(b) est vrai, que doit-on savoir de leurs hashCode ? Et si leurs hashCode sont égaux ?


<details>
<summary>💡 Voir un indice</summary>

Le contrat ne fonctionne que dans un sens.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

L’égalité impose le même hashCode. Des hashCode égaux n’imposent pas l’égalité, car les collisions sont possibles.


</details>

#### Exercice 02 — Une donnée de valeur

Crée record Coordonnees(int ligne, int colonne). Vérifie l’égalité de deux instances (1, 4) et affiche leur colonne.


<details>
<summary>💡 Voir un indice</summary>

Les records génèrent les méthodes de valeur.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

equals compare les composants ; l’accesseur se nomme colonne().


```java
public class Jour19Exercice02 {
    public static void main(String[] args) {
        CoordonneesJ19E02 a = new CoordonneesJ19E02(1, 4);
        CoordonneesJ19E02 b = new CoordonneesJ19E02(1, 4);
        System.out.println(a.equals(b));
        System.out.println(a.colonne());
    }
}
record CoordonneesJ19E02(int ligne, int colonne) {}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour19Exercice02.java)

Résultat attendu :

```text
true
4
```

</details>

### Niveau 2 — corriger et consolider

#### Exercice 03 — Une vérification sans NullPointerException

Écris estRenseigne(String texte), vrai seulement si texte est non null et contient autre chose que des espaces. Teste null, `   ` et `Java`.


<details>
<summary>💡 Voir un indice</summary>

Place le contrôle null avant l’appel isBlank avec &&.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le court-circuit empêche l’appel de méthode dans le cas absent.


```java
public class Jour19Exercice03 {
    static boolean estRenseigne(String texte) {
        return texte != null && !texte.isBlank();
    }
    public static void main(String[] args) {
        System.out.println(estRenseigne(null));
        System.out.println(estRenseigne("   "));
        System.out.println(estRenseigne("Java"));
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour19Exercice03.java)

Résultat attendu :

```text
false
false
true
```

</details>

### Niveau 3 — résoudre un petit problème

#### Exercice 04 — Construire une valeur valide

Crée record Note(int valeur) avec un constructeur compact qui refuse les nombres hors de [0, 20] via IllegalArgumentException. Affiche la valeur de new Note(14).


<details>
<summary>💡 Voir un indice</summary>

Le constructeur compact s’écrit Note { ... } sans liste de paramètres.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le contrôle est exécuté avant l’affectation automatique du composant. Un record permet donc de vérifier des invariants, pas seulement de raccourcir une déclaration.


```java
public class Jour19Exercice04 {
    public static void main(String[] args) {
        NoteJ19E04 note = new NoteJ19E04(14);
        System.out.println(note.valeur());
    }
}
record NoteJ19E04(int valeur) {
    NoteJ19E04 {
        if (valeur < 0 || valeur > 20) {
            throw new IllegalArgumentException("NoteJ19E04 hors de [0, 20]");
        }
    }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour19Exercice04.java)

Résultat attendu :

```text
14
```

</details>

<a id="bilan"></a>
## ✅ Avant de passer à la suite

- [ ] Je peux distinguer identité et égalité de contenu.
- [ ] Je peux respecter le lien entre equals et hashCode.
- [ ] Je peux gérer une référence absente et découvrir record.

- [ ] J’ai tenté les quatre exercices avant de comparer aux corrigés.
- [ ] Je peux expliquer une erreur rencontrée et la façon dont je l’ai corrigée.

[Noter ma progression](../PROGRESSION.md) · [Consulter le glossaire](../docs/GLOSSAIRE.md)

### 📎 Pour approfondir

- [Classes record](https://dev.java/learn/records/)

---

[← Jour 18](../18_Day_Interfaces_and_Polymorphism/18_interfaces_and_polymorphism.md) | [📚 Sommaire](../README.md) | [Jour 20 →](../20_Day_ArrayList_and_Generics/20_arraylist_and_generics.md)

**☕ Une étape comprise vaut mieux qu’une journée cochée trop vite.**
