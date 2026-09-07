# 💻 Jour 24 — exercices

[← Revenir au cours](../24_packages_and_projects.md)

Ces énoncés sont identiques à ceux du cours. Les indices et corrigés restent dans les parties repliables de la leçon.

## Niveau 1 — comprendre et appliquer

#### Exercice 01 — Reconstituer un nom complet

Le fichier src/fr/cours/modele/Etudiant.java déclare package fr.cours.modele et public class Etudiant. Quel est son nom complet ?


#### Exercice 02 — Qualifier sans importer

Affiche la taille d’une liste créée par java.util.List.of("Java", "Maths") sans écrire d’import.


## Niveau 2 — corriger et consolider

#### Exercice 03 — Séparer une responsabilité

Écris une classe Message avec une méthode static texte(String prenom), puis appelle-la depuis la classe principale. Le corrigé autonome les regroupe ; refais ensuite la séparation en deux fichiers dans le projet fourni.


## Niveau 3 — résoudre un petit problème

#### Exercice 04 — Organiser un calcul réutilisable

Crée Statistiques avec public static double moyenne(int[] notes), puis un App qui l’appelle. Les notes sont non vides. Le corrigé autonome montre le calcul ; ajoute ensuite Statistiques.java au package du projet et appelle-la depuis App.
