# 💻 Jour 06 — exercices

[← Revenir au cours](../06_conditionals.md)

Ces énoncés sont identiques à ceux du cours. Les indices et corrigés restent dans les parties repliables de la leçon.

## Niveau 1 — comprendre et appliquer

#### Exercice 01 — ET ou OU ?

Pour une note entière, quelle condition détecte une valeur hors de [0, 20] ? Explique pourquoi `note < 0 && note > 20` est fausse pour tout entier.


#### Exercice 02 — Pair ou impair

Déclare un entier n = 17 et affiche `Pair` ou `Impair`. Teste aussi 0 et 18.


## Niveau 2 — corriger et consolider

#### Exercice 03 — Remettre les seuils dans l’ordre

On teste d’abord note >= 10, puis note >= 16 dans un else if. Pourquoi 18 n’obtient-il jamais `Très bien` ? Corrige pour afficher `Très bien` dès 16, `Validé` dès 10, sinon `À retravailler`.


## Niveau 3 — résoudre un petit problème

#### Exercice 04 — Une règle de validation

Une UE est validée si la moyenne est au moins 10 ET s’il y a au plus 3 absences. Avec 12.5 et 4, affiche `Non validée`. Teste ensuite 10 et 3.
