# 💻 Jour 19 — exercices

[← Revenir au cours](../19_equality_and_immutability.md)

Ces énoncés sont identiques à ceux du cours. Les indices et corrigés restent dans les parties repliables de la leçon.

## Niveau 1 — comprendre et appliquer

#### Exercice 01 — Une implication à retenir

Si a.equals(b) est vrai, que doit-on savoir de leurs hashCode ? Et si leurs hashCode sont égaux ?


#### Exercice 02 — Une donnée de valeur

Crée record Coordonnees(int ligne, int colonne). Vérifie l’égalité de deux instances (1, 4) et affiche leur colonne.


## Niveau 2 — corriger et consolider

#### Exercice 03 — Une vérification sans NullPointerException

Écris estRenseigne(String texte), vrai seulement si texte est non null et contient autre chose que des espaces. Teste null, `   ` et `Java`.


## Niveau 3 — résoudre un petit problème

#### Exercice 04 — Construire une valeur valide

Crée record Note(int valeur) avec un constructeur compact qui refuse les nombres hors de [0, 20] via IllegalArgumentException. Affiche la valeur de new Note(14).
