# 💻 Jour 15 — exercices

[← Revenir au cours](../15_constructors_and_encapsulation.md)

Ces énoncés sont identiques à ceux du cours. Les indices et corrigés restent dans les parties repliables de la leçon.

## Niveau 1 — comprendre et appliquer

#### Exercice 01 — Trouver l’invariant

Une classe stocke une note sur 20. Quelle règle doit être vraie après son constructeur et chaque modification ?


#### Exercice 02 — Un prénom initialisé

Crée une classe Personne avec prénom private, constructeur et getter. Construis une personne `Nathan` et affiche son prénom.


## Niveau 2 — corriger et consolider

#### Exercice 03 — Protéger une note

Une note commence à 10. Une méthode modifier(int nouvelle) renvoie false si nouvelle est hors de [0, 20], sinon elle met à jour et renvoie true. Teste 21 puis 15.


## Niveau 3 — résoudre un petit problème

#### Exercice 04 — Gérer un stock

Crée Stock(quantite), sans quantité négative. vendre(nombre) accepte seulement un nombre strictement positif inférieur ou égal au stock. Avec 5 unités, vends 2 puis tente 4. Affiche les succès et le stock final.
