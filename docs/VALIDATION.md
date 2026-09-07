# Vérification du cours

[← Sommaire](../README.md)

## Résultats de la vérification initiale

Vérification effectuée le 7 septembre 2026.

| Contrôle | Résultat |
| --- | --- |
| 30 exemples et 90 corrigés Java | Compilation réussie. |
| 120 exécutions avec leurs entrées prévues | Sorties conformes à celles des leçons. |
| 4 extraits de code intégrés aux cours | Compilation réussie. |
| Lancement direct de fichiers source | Vérifié sur le premier programme et un exemple utilisant un record. |
| Projet multifichier du jour 24 | Compilation et sortie vérifiées. |
| Carnet final | 52 vérifications de règles et de stockage réussies. |
| Menu du carnet | Ajout, terminaison, relance, saisies invalides et fichier invalide préservé vérifiés. |
| Navigation interne | 1 148 liens et ancres vérifiés. |
| Visuels | SVG rendus et inspectés ; bannières et schémas fournis localement. |

## Environnement et portée

Les vérifications locales utilisent OpenJDK **17.0.20** sous Linux. Elles confirment que les exemples n’utilisent pas de fonctionnalités postérieures à Java 17. Le parcours d’installation vise **JDK 21** ; une vérification GitHub Actions sur Temurin 21 est fournie et s’exécutera lorsque le dépôt sera publié. Ce contrôle distant n’a pas encore été exécuté dans cette préparation.

Les commandes PowerShell suivent la configuration Windows décrite dans les guides, mais n’ont pas été exécutées sur un PC Windows pendant cette vérification. Les visuels ont été inspectés sous forme rendue ; la mise en page Markdown finale dépend du rendu GitHub, qui sera vérifié après publication.

## Reproduire les contrôles

Avec Python 3.10+ et un JDK installé, depuis la racine du dépôt :

```powershell
python scripts/verifier.py
```

Le script ne modifie pas tes fichiers de travail : il compile et teste dans des dossiers temporaires. Les tests du projet peuvent aussi être lancés avec le JDK seul, selon le [guide du projet](../29_Day_Final_Project/projet/README.md).

Le script échoue avec un message explicite en cas de compilation incorrecte, résultat inattendu, scénario invalide ou lien interne absent. Il ne se contente pas de constater que les programmes démarrent.
