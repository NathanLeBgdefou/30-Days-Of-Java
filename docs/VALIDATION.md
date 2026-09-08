# Vérification du cours

[← Sommaire](../README.md)

## Résultats de la vérification

Vérification effectuée le 7 septembre 2026. Le [contrôle GitHub Actions sur Java 21](https://github.com/NathanLeBgdefou/30-Days-Of-Java/actions/runs/34147050882) a réussi sur la version initiale publiée du cours (`f59d686`).

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
| Publication | Les 339 fichiers de la version initiale publiée sont identiques aux fichiers préparés, empreintes Git vérifiées. |
| GitHub Actions | Tous les contrôles automatiques réussis sur Temurin 21, Ubuntu et Python 3.12. |
| Visuels | SVG rendus et inspectés ; bannières et schémas fournis localement. |

## Environnement et portée

Les premières vérifications locales ont utilisé OpenJDK **17.0.20** sous Linux. Elles confirment que les exemples n’utilisent pas de fonctionnalités postérieures à Java 17. Après publication, le même script a réussi dans **GitHub Actions avec Temurin 21**, sous Ubuntu et Python 3.12. Le parcours d’installation vise **JDK 21**. Le workflow relance ces contrôles à chaque push et pull request.

Les commandes PowerShell suivent la configuration Windows décrite dans les guides, mais n’ont pas été exécutées sur un PC Windows pendant cette vérification. Les visuels ont été rendus et inspectés avant publication. Le dernier contrôle de l’affichage des pages sur GitHub n’a pas pu aboutir, la session du navigateur ayant été interrompue ; les liens et les fichiers d’images publiés ont été vérifiés.

## Reproduire les contrôles

Avec Python 3.10+ et un JDK installé, depuis la racine du dépôt :

```powershell
python scripts/verifier.py
```

Le script ne modifie pas tes fichiers de travail : il compile et teste dans des dossiers temporaires. Les tests du projet peuvent aussi être lancés avec le JDK seul, selon le [guide du projet](../29_Day_Final_Project/projet/README.md).

Le script échoue avec un message explicite en cas de compilation incorrecte, résultat inattendu, scénario invalide ou lien interne absent. Il ne se contente pas de constater que les programmes démarrent.
