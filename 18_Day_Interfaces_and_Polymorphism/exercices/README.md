# 💻 Jour 18 — exercices

[← Revenir au cours](../18_interfaces_and_polymorphism.md)

Ces énoncés sont identiques à ceux du cours. Les indices et corrigés restent dans les parties repliables de la leçon.

## Niveau 1 — comprendre et appliquer

#### Exercice 01 — Identifier le contrat

Une méthode reçoit une variable de type Mesurable. Peut-elle appeler aire() ? Peut-elle appeler directement une méthode propre à Rectangle absente de Mesurable ?


#### Exercice 02 — Implémenter un score

Crée interface Notable avec int score(), puis Devoir qui renvoie 15. Affiche le score via une variable Notable.


## Niveau 2 — corriger et consolider

#### Exercice 03 — Une seconde forme

Ajoute une classe Triangle(base, hauteur) respectant Mesurable. Avec 6 et 4, aire() doit renvoyer 12.0.


## Niveau 3 — résoudre un petit problème

#### Exercice 04 — Changer la présentation sans changer l’appelant

Crée Formateur avec formater(String texte), puis Normal et Majuscules. Une méthode afficher(Formateur, String) affiche le résultat. Teste les deux avec `Java`.
