# 💻 Jour 17 — exercices

[← Revenir au cours](../17_inheritance.md)

Ces énoncés sont identiques à ceux du cours. Les indices et corrigés restent dans les parties repliables de la leçon.

## Niveau 1 — comprendre et appliquer

#### Exercice 01 — Surcharge ou redéfinition ?

Une sous-classe reprend parler() avec le même type de retour. Ailleurs, une classe propose parler(String texte). Nomme les deux mécanismes.


#### Exercice 02 — Une spécialisation simple

Crée Animal avec cri() renvoyant `?`, puis Chat qui redéfinit cri() pour renvoyer `Miaou`. Appelle cette méthode via une variable Animal.


## Niveau 2 — corriger et consolider

#### Exercice 03 — Initialiser le parent

Crée Document(titre), puis Cours(titre, duree). La description de Cours complète celle du parent. Attendu : `Java — 60 min`.


## Niveau 3 — résoudre un petit problème

#### Exercice 04 — Un tableau polymorphe

Crée Animal, Chat et Chien. Parcours un tableau Animal[] contenant un chat puis un chien et affiche leurs cris.
