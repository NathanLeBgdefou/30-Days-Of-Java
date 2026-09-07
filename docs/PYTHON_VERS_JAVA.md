# 🐍 → ☕ Repères Python vers Java

[← Sommaire](../README.md)

Ces rapprochements servent de repères. Ils ne signifient pas que les deux langages ont exactement les mêmes règles.

| Intention | Python | Java |
| --- | --- | --- |
| Déclarer un entier | `age = 19` | `int age = 19;` |
| Afficher | `print("Bonjour")` | `System.out.println("Bonjour");` |
| Booléens | `True`, `False` | `true`, `false` |
| Absence de référence | `None` | `null` |
| Bloc conditionnel | `if condition:` puis indentation | `if (condition) { ... }` |
| ET / OU / NON | `and`, `or`, `not` | `&&`, `||`, `!` |
| Égalité de textes | `a == b` | `a.equals(b)` ou `Objects.equals(a, b)` si null est possible |
| Longueur du texte | `len(texte)` | `texte.length()` |
| Accès simple au texte | `texte[0]` | `texte.charAt(0)` ; attention à UTF-16 |
| Extraction | `texte[1:3]` | `texte.substring(1, 3)` |
| Division décimale | `7 / 2` donne 3.5 | `7 / 2.0` donne 3.5 |
| Division entière | `7 // 2` | `7 / 2` donne 3 ; pour les négatifs les arrondis diffèrent |
| Lire au clavier | `input()` | `clavier.nextLine()` avec Scanner |
| Convertir un entier | `int(texte)` | `Integer.parseInt(texte)` |
| Liste modifiable | `liste = []` | `List<String> liste = new ArrayList<>();` |
| Ajouter à une liste | `liste.append(x)` | `liste.add(x);` |
| Nombre d'éléments | `len(liste)` | `liste.size()` |
| Tableau de taille fixe | Pas d'équivalent identique avec list | `int[] notes = {12, 15};` |
| Dictionnaire | `d = {}` | `Map<String, Integer> d = new HashMap<>();` |
| Valeur avec défaut | `d.get(cle, 0)` | `d.getOrDefault(cle, 0)` |
| Fonction | `def carre(n):` | `static int carre(int n) { ... }` dans une classe |
| Retour | `return resultat` | `return resultat;` |
| Parcours de valeurs | `for x in valeurs:` | `for (int x : valeurs) { ... }` |
| Parcours d'indices | `for i in range(n):` | `for (int i = 0; i < n; i++) { ... }` |
| Gestion d'erreur | `try` / `except` | `try` / `catch` |

## Trois différences à vraiment comprendre

**Types :** une variable Java garde son type déclaré. `var` déduit ce type à la compilation ; il ne transforme pas le langage en Python.

**Objets :** Java copie toujours les valeurs des arguments. Pour un objet, cette valeur est une référence. Deux variables peuvent ainsi partager un objet sans que la réaffectation d'une variable remplace l'autre.

**Collections :** ne compte pas sur l'ordre d'insertion de HashMap. Choisis LinkedHashMap si cet ordre est important. Une ArrayList n'utilise pas les crochets pour lire une case : elle utilise get.
