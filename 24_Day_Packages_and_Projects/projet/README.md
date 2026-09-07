# Projet à deux fichiers

[← Jour 24](../24_packages_and_projects.md)

Ouvre ce dossier **projet** dans VS Code. Dans son terminal PowerShell :

```powershell
$sourcesJava = Get-ChildItem -Path .\src -Recurse -Filter *.java | ForEach-Object { $_.FullName }
javac -encoding UTF-8 -d out $sourcesJava
java -cp out fr.cours.demo.App
```

Résultat attendu : `Bonjour Nathan`.

[App.java](./src/fr/cours/demo/App.java) utilise [Message.java](./src/fr/cours/demo/Message.java). Les deux fichiers déclarent le même package. Le constructeur privé de Message empêche d’instancier cette classe utilitaire : sa seule opération est static.

Pour les exercices 03 et 04 du jour, tu peux modifier App et ajouter Statistiques.java dans le même package. Recompile tous les fichiers après une modification.
