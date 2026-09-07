# 🧭 Mode d'emploi

[← Sommaire](../README.md)

## Lire sur GitHub

Le tableau du sommaire ouvre directement le fichier de cours. En haut et en bas de chaque journée, les liens permettent de revenir au sommaire ou de changer de jour. Si tu ouvres un dossier au lieu d'une leçon, sa page d'accueil propose **Ouvrir le cours complet**.

Les blocs de code sont colorés par GitHub. Les zones **Voir un indice** et **Voir le corrigé détaillé** s'ouvrent au clic. Elles sont fermées au départ pour te permettre de chercher. Les réponses sont cependant accessibles : ce mécanisme aide à apprendre, il ne constitue pas un verrou.

## Télécharger pour pratiquer

Sur la page du dépôt, utilise **Code → Download ZIP**, puis extrais l'archive dans tes Documents. Tu peux aussi cloner le dépôt si tu sais déjà utiliser Git. Une fois les outils Java installés, le cours et ses programmes ne nécessitent pas de connexion pour fonctionner ; les liens vers les ressources externes en nécessitent une.

Dans VS Code, ouvre un dossier, pas seulement un fichier isolé. Pour un exemple autonome, ouvre le dossier `exemples` de la journée ; pour un corrigé, son dossier `solutions`. L'extension Java peut alors analyser les fichiers. Attends la fin de son chargement initial.

## Une routine d'au moins une heure

| Temps | Action |
| --- | --- |
| 10 min | Rappeler la veille et lire les notions nouvelles. |
| 15–20 min | Prédire, exécuter et modifier l'exemple. |
| 30–45 min | Résoudre les exercices ; ouvrir un indice seulement après une tentative. |
| 5 min | Comparer, expliquer une erreur et noter ce qui reste à revoir. |

Tu peux prolonger une journée. Les jours 13, 29 et 30 sont plus longs. « 30 jours » décrit trente étapes, pas une date limite.

## Comprendre les fichiers

| Élément | Contenu |
| --- | --- |
| `README.md` | Accueil, sommaire et jour 01. |
| `NN_Day_Theme/NN_theme.md` | Leçon complète et corrigés repliables. |
| `exemples/` | Un exemple complet, avec son main. |
| `exercices/README.md` | Les quatre énoncés seuls. |
| `solutions/` | Trois programmes corrigés pour les exercices de code ; le premier exercice de raisonnement est corrigé dans la leçon. |
| `images/` | Bannières et schémas vectoriels, intégrés aux leçons. |
| `docs/` | Installation, passerelle Python, glossaire et bilan. |
| `PROGRESSION.md` | Cases à cocher et carnet de bord personnel. |
| `29_Day_Final_Project/projet/` | Sujet, corrigé complet et tests du carnet de révisions. |

Crée tes propres exercices dans un dossier séparé. Si tu travailles à la racine du dépôt, le nom `mes-exercices` est ignoré par Git afin de ne pas publier tes brouillons involontairement. Tu peux aussi créer ton propre dépôt d'exercices plus tard.

## Lancer le code

Le nom de la classe publique et celui du fichier doivent correspondre. Le premier exemple est Bonjour.java ; les suivants ont des noms comme Jour02Exemple.java. Dans le terminal du dossier concerné :

```powershell
java .\Jour02Exemple.java
```

Ce mode compile en mémoire un fichier autonome. Les projets des jours 24 et 29 comportent plusieurs fichiers et utilisent une compilation collective ; leur guide donne les commandes exactes.

Un bloc indiqué `java fragment` dans un cours est un petit extrait destiné à l'intérieur de main, pas un fichier autonome. Les blocs « Exemple complet » et les corrigés téléchargeables sont complets. Les extraits incorrects dans les questions sont volontairement à réparer.

## Comprendre plutôt que recopier

Avant de lancer, écris le résultat que tu attends. Après un échec, lis le premier message utile, vérifie les types et les bornes, puis réduis le programme à un cas simple. Avant de passer au jour suivant, essaie de refaire une petite partie sans regarder la réponse.
