# 🪟 Windows et VS Code : installation et dépannage

[← Sommaire](../README.md) · [Jour 01](../01_Day_Introduction/01_introduction.md)

## Installation de référence

Le cours vise **JDK 21**, sans options expérimentales. Télécharge une distribution JDK depuis [Eclipse Temurin](https://adoptium.net/temurin/releases/?version=21&os=windows). Dans Windows, vérifie le type de processeur dans **Paramètres → Système → Informations système** avant de choisir x64 ou ARM64. L'installateur MSI facilite la configuration.

Choisis **JDK**, pas seulement JRE. Dans les options de l'installateur, active l'ajout au PATH et la définition de JAVA_HOME si proposées. Relance VS Code et les terminaux après installation.

Dans les extensions de VS Code (`Ctrl+Maj+X`), installe **Extension Pack for Java**, publié par Microsoft, identifiant `vscjava.vscode-java-pack`. [Guide officiel VS Code](https://code.visualstudio.com/docs/java/java-tutorial).

## Vérifier la configuration

Ouvre un terminal PowerShell :

```powershell
java --version
javac --version
Get-Command java
Get-Command javac
```

Les deux premières commandes doivent annoncer 21.x pour la configuration de référence. Les deux suivantes indiquent les exécutables choisis par le terminal. Si tu as plusieurs versions, ces chemins permettent de voir ce qui est réellement utilisé.

Dans VS Code, `Ctrl+Maj+P` → **Java: Configure Java Runtime** permet de vérifier le JDK du projet. Les versions actuelles de l'extension peuvent gérer leur propre environnement de lancement : ne confonds pas celui-ci avec le JDK sélectionné pour ton code. [Gestion des runtimes](https://code.visualstudio.com/docs/java/java-project#_configure-runtime-for-projects).

## Dépannage ciblé

| Symptôme | Vérification | Correction |
| --- | --- | --- |
| `java` ou `javac` introuvable | Un JDK est-il installé ? Le terminal date-t-il d'avant l'installation ? | Relancer terminal et VS Code ; vérifier l'option PATH du JDK. |
| java existe mais pas javac | Un simple environnement d'exécution peut avoir été installé. | Installer un JDK complet. |
| `class X is public, should be declared in a file named X.java` | Nom du fichier et de la classe publique. | Faire correspondre exactement les deux noms. |
| `Could not find or load main class` | Dossier courant, nom de classe, classpath. | Pour un fichier autonome, utiliser `java .\Nom.java` ; pour un projet, suivre son guide. |
| `UnsupportedClassVersionError` | Le runtime est plus ancien que le compilateur ayant produit les .class. | Choisir un JDK cohérent et recompiler les sources. |
| Le programme ne prend pas le clavier | Panneau Output au lieu du terminal. | Lancer depuis le terminal intégré ou configurer la console Java sur integratedTerminal. |
| Un fichier est introuvable | Dossier de travail du programme. | Vérifier le chemin absolu et lancer depuis le dossier indiqué. |
| Lettres accentuées incorrectes | Encodage du fichier et terminal. | Enregistrer en UTF-8 ; compiler avec `-encoding UTF-8` ; vérifier l'encodage du terminal. |
| VS Code ne reconnaît pas le projet | Un fichier seul a-t-il été ouvert ? | Ouvrir son dossier et attendre la fin du chargement Java. |

Ne supprime pas une version Java utilisée par tes études ou un autre logiciel pour faire disparaître une erreur. Commence par sélectionner les bons chemins. Le cours n'impose aucune extension payante.

## Premier test

Crée un dossier dans tes Documents, ouvre-le dans VS Code, puis crée Bonjour.java :

```java
public class Bonjour {
    public static void main(String[] args) {
        System.out.println("Java fonctionne !");
    }
}
```

Dans le terminal du dossier :

```powershell
java .\Bonjour.java
```

Résultat : `Java fonctionne !`. Tu peux maintenant commencer le [jour 01](../01_Day_Introduction/01_introduction.md).
