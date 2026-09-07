<div align="center">
<h1>☕ 30 Days Of Java : jour 16</h1>
<h3>Associer des objets : la composition</h3>
<p>Français · Java 21 · Windows &amp; VS Code</p>
</div>

[← Jour 15](../15_Day_Constructors_and_Encapsulation/15_constructors_and_encapsulation.md) | [📚 Sommaire](../README.md) | [Jour 17 →](../17_Day_Inheritance/17_inheritance.md)

![30 Days Of Java — apprendre à programmer](../images/30DaysOfJava_banner.svg)

![Progression : jour 16 sur 30](../images/progression-16.svg)

**Durée conseillée : 60 à 90 min.** Garde au moins une heure ; prends plus de temps pour coder si nécessaire.

📍 [Objectifs](#objectifs) · [Cours](#cours) · [Exemple](#exemple) · [Exercices](#exercices) · [Bilan](#bilan)

<a id="objectifs"></a>
## 🎯 Objectifs du jour

- Représenter une relation « possède un ».
- Déléguer une opération à un autre objet.
- Identifier un état partagé et une copie défensive.

> **Ton rythme :** 10 min de rappel et lecture, 15–20 min sur l’exemple, 30–45 min de pratique, 5 min de bilan. Les jours de projet demandent davantage.

<a id="cours"></a>
# 📘 Cours

## Un objet peut contenir des références vers d'autres objets

Un étudiant possède une adresse. Plutôt que de répéter les champs ville et code postal dans chaque classe, on peut créer une classe Adresse, puis placer un champ de type Adresse dans Etudiant. C'est une façon de construire des objets plus riches à partir d'objets plus simples.

On parle ici de **composition au sens de conception** : un objet s'appuie sur un autre pour représenter une partie de son état ou de son comportement. En UML, la composition au sens strict ajoute une notion de propriété et de cycle de vie ; toute référence Java n'implique pas automatiquement cette relation stricte.

## Choisir une responsabilité

Adresse sait présenter une ville et un code postal. Etudiant sait présenter son prénom et demander à son adresse de se présenter. Cette **délégation** évite que la classe Etudiant connaisse tous les détails du formatage de l'adresse.

| Classe | Données | Responsabilité |
| --- | --- | --- |
| Adresse | ville, code postal | Présenter un lieu. |
| Etudiant | prénom, adresse | Présenter une personne et son lieu. |

Le code postal est une String, car il s'agit d'un identifiant, pas d'une quantité à additionner. Cela préserve notamment les éventuels zéros initiaux.

## Partager ou copier ?

Si deux étudiants reçoivent la même référence d'Adresse et que l'adresse est modifiable, modifier cet objet change ce que voient les deux étudiants. Ce partage peut être voulu, mais il doit être compris.

Une solution simple pour les petits objets de valeur est de les rendre **immuables** : champs private final, aucune méthode de modification, et composants eux-mêmes immuables. Un final n'empêche pas la mutation d'un objet référencé ; l'ensemble de la conception doit préserver l'état.

Pour un tableau modifiable reçu dans un constructeur, une **copie défensive** empêche un appelant de modifier ensuite l'état interne avec sa référence d'origine. Si un getter renvoie ce tableau, il doit aussi renvoyer une copie pour ne pas réouvrir l'accès.

## Composition ou héritage ?

Pose une question en français. « Un étudiant **possède une** adresse » suggère un champ. « Un chien **est un** animal » peut suggérer une relation d'héritage, sous réserve que le comportement soit compatible. On ne fait pas hériter Etudiant de Adresse pour réutiliser quelques lignes : un étudiant n'est pas une adresse.

La composition permet de remplacer une pièce sans changer toute la hiérarchie. Au prochain jour, nous verrons l'héritage avec ses propres avantages et contraintes. Les deux outils ne servent pas à résoudre exactement le même problème.


<a id="exemple"></a>
## 🔎 Exemple complet, prêt à exécuter

[Ouvrir Jour16Exemple.java](./exemples/Jour16Exemple.java) · [Lire les exercices sans les réponses](./exercices/README.md)

Dans VS Code, ouvre le dossier `exemples` de cette journée, puis lance dans son terminal :

```powershell
java .\Jour16Exemple.java
```

```java
public class Jour16Exemple {
    public static void main(String[] args) {
        AdresseJ16Exemple adresse = new AdresseJ16Exemple("Rennes", "35000");
        EtudiantJ16Exemple etudiant = new EtudiantJ16Exemple("Nathan", adresse);
        System.out.println(etudiant.presentation());
    }
}
class AdresseJ16Exemple {
    private final String ville;
    private final String codePostal;
    AdresseJ16Exemple(String ville, String codePostal) {
        this.ville = ville;
        this.codePostal = codePostal;
    }
    String presentation() {
        return codePostal + " " + ville;
    }
}
class EtudiantJ16Exemple {
    private final String prenom;
    private final AdresseJ16Exemple adresse;
    EtudiantJ16Exemple(String prenom, AdresseJ16Exemple adresse) {
        this.prenom = prenom;
        this.adresse = adresse;
    }
    String presentation() {
        return prenom + " — " + adresse.presentation();
    }
}
```

**Sortie du programme :**

```text
Nathan — 35000 Rennes
```

### Comprendre le déroulement

Le constructeur d'Etudiant reçoit une adresse déjà créée. Sa méthode presentation délègue une partie du message à adresse.presentation. L'Adresse de cet exemple ne propose aucune modification et ses champs sont des String immuables. Le partage de cette instance ne permet donc pas d'en changer la ville après construction.


### ⚠️ Pièges fréquents

- Un champ final empêche sa réaffectation ; il ne rend pas automatiquement l’objet référencé immuable.
- Renvoyer directement un tableau interne peut casser l’encapsulation.
- Une relation « possède un » ne justifie pas une relation extends.

<a id="exercices"></a>
## 💻 Exercices du jour

Essaie d’abord sans ouvrir les indices. Pour un exercice de code, crée ton propre fichier dans un dossier de travail et donne le même nom à la classe publique et au fichier. Les corrigés sont complets pour permettre une comparaison et une exécution indépendantes.

### Niveau 1 — comprendre et appliquer

#### Exercice 01 — Choisir la relation

Une Voiture utilise un Moteur. Faut-il écrire Voiture extends Moteur ou donner un champ Moteur à Voiture ? Pourquoi ?


<details>
<summary>💡 Voir un indice</summary>

Une voiture n’est pas un moteur.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Un champ Moteur représente la relation. La voiture peut déléguer le démarrage au moteur. L’héritage exprimerait une substitution qui n’a pas de sens ici.


</details>

#### Exercice 02 — Une séance et sa matière

Crée Matiere(nom), puis Seance(matiere, minutes). Une description doit donner `Java : 60 min`.


<details>
<summary>💡 Voir un indice</summary>

La séance garde une référence vers la matière.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

La classe Matiere reste responsable de son nom ; Seance ajoute la durée.


```java
public class Jour16Exercice02 {
    public static void main(String[] args) {
        SeanceJ16E02 seance = new SeanceJ16E02(new MatiereJ16E02("Java"), 60);
        System.out.println(seance.description());
    }
}
class MatiereJ16E02 {
    private final String nom;
    MatiereJ16E02(String nom) { this.nom = nom; }
    String getNom() { return nom; }
}
class SeanceJ16E02 {
    private final MatiereJ16E02 matiere;
    private final int minutes;
    SeanceJ16E02(MatiereJ16E02 matiere, int minutes) {
        this.matiere = matiere;
        this.minutes = minutes;
    }
    String description() { return matiere.getNom() + " : " + minutes + " min"; }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour16Exercice02.java)

Résultat attendu :

```text
Java : 60 min
```

</details>

### Niveau 2 — corriger et consolider

#### Exercice 03 — Copier les données reçues

Une classe Carnet reçoit int[] notes. Copie ce tableau dans le constructeur. Modifie ensuite le tableau d’origine et vérifie que la première note du carnet reste 12.


<details>
<summary>💡 Voir un indice</summary>

Arrays.copyOf copie les cases d’un tableau de int.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

Le constructeur sépare l’état interne du tableau externe. Un getter de case renvoie ici un int, donc aucune référence modifiable ne s’échappe.


```java
import java.util.Arrays;
public class Jour16Exercice03 {
    public static void main(String[] args) {
        int[] origine = {12, 15};
        CarnetJ16E03 carnet = new CarnetJ16E03(origine);
        origine[0] = 0;
        System.out.println(carnet.premiereNote());
    }
}
class CarnetJ16E03 {
    private final int[] notes;
    CarnetJ16E03(int[] notes) { this.notes = Arrays.copyOf(notes, notes.length); }
    int premiereNote() { return notes[0]; } // Contrat : au moins une note.
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour16Exercice03.java)

Résultat attendu :

```text
12
```

</details>

### Niveau 3 — résoudre un petit problème

#### Exercice 04 — Composer une commande

Crée Produit(nom, prixEnCentimes), puis LigneCommande(produit, quantite). Calcule son total en centimes. Avec un produit à 250 et une quantité de 3, affiche 750. On suppose des valeurs positives modestes.


<details>
<summary>💡 Voir un indice</summary>

Le produit connaît son prix ; la ligne connaît la quantité.

</details>

<details>
<summary>✅ Voir le corrigé détaillé</summary>

On utilise des centimes entiers pour éviter l’arrondi binaire des double dans ce calcul d’argent.


```java
public class Jour16Exercice04 {
    public static void main(String[] args) {
        ProduitJ16E04 produit = new ProduitJ16E04("Cahier", 250);
        LigneCommandeJ16E04 ligne = new LigneCommandeJ16E04(produit, 3);
        System.out.println(ligne.totalCentimes());
    }
}
class ProduitJ16E04 {
    private final String nom;
    private final int prixCentimes;
    ProduitJ16E04(String nom, int prixCentimes) {
        this.nom = nom;
        this.prixCentimes = prixCentimes;
    }
    int getPrixCentimes() { return prixCentimes; }
}
class LigneCommandeJ16E04 {
    private final ProduitJ16E04 produit;
    private final int quantite;
    LigneCommandeJ16E04(ProduitJ16E04 produit, int quantite) {
        this.produit = produit;
        this.quantite = quantite;
    }
    int totalCentimes() { return produit.getPrixCentimes() * quantite; }
}
```

[Ouvrir le fichier Java du corrigé](./solutions/Jour16Exercice04.java)

Résultat attendu :

```text
750
```

</details>

<a id="bilan"></a>
## ✅ Avant de passer à la suite

- [ ] Je peux représenter une relation « possède un ».
- [ ] Je peux déléguer une opération à un autre objet.
- [ ] Je peux identifier un état partagé et une copie défensive.

- [ ] J’ai tenté les quatre exercices avant de comparer aux corrigés.
- [ ] Je peux expliquer une erreur rencontrée et la façon dont je l’ai corrigée.

[Noter ma progression](../PROGRESSION.md) · [Consulter le glossaire](../docs/GLOSSAIRE.md)

---

[← Jour 15](../15_Day_Constructors_and_Encapsulation/15_constructors_and_encapsulation.md) | [📚 Sommaire](../README.md) | [Jour 17 →](../17_Day_Inheritance/17_inheritance.md)

**☕ Une étape comprise vaut mieux qu’une journée cochée trop vite.**
