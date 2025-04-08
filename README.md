# TP 3 : Liste chainée

## 1 INTRODUCTION

Pour le troisième TP, vous allez compléter le code d’une liste chainée. Vous allez chercher le code de
`ListeDoublementChaine.java` (disponible avec l’énoncé). Vous devrez compléter le code pour 4
méthodes. Vous aurez accès à un fichier de tests unitaires pour vérifier la fonctionnalité du code.

## 2 DESCRIPTION

Sur le site Moodle du cours, vous allez trouver deux fichiers de code.
- `ListeDoublementChaine.java` : contiens le code de la classe `ListeDoublementChaine`.
Cette classe a quatre méthodes à compléter :
  - `ListeDoublementChaine< E > extraireSi( Predicate< E > condition )`
  - `boolean sousEnsembleEgal( ListeDoublementChaine< E > droite )`
  - `boolean estDiviseSelon( Predicate< E > condition )`
  - `void diviserSelon( Predicate< E > condition )`
- `ListeDoublementChaineTest.java` : contiens les 24 tests unitaires pour la classe
`ListeDoublementChaine`.

Votre travail consiste à placer le code pour ces méthodes. Il est important de ne pas modifier la
signature des méthodes et des classes. La description de ce que fait chaque méthode est dans les
commentaires de chaque méthode. Les tests vous donnent des exemples.
### 2.1 CODE
Votre code sera placé dans les quatre méthodes. Vous pouvez ajouter des méthodes dans la classe.
Dans ce cas, vos méthodes doivent être documentées. Ne modifiez pas la signature des méthodes
présentes dans le code. IMPORTANT : il y a 5 `imports` au début du fichier
`ListeDoublementChaine.java`. Vous ne pouvez pas modifier ces cinq lignes ET vous ne pouvez
pas ajouter de `import`.
### 2.2 TESTS UNITAIRES
Le fichier de tests unitaires utilise la librairie JUnit 5.

## 3 DIRECTIVES
1. Le TP est à faire seul ou en équipe de deux.
2. Code :

   1. Pas de `goto`, `continue`.
   2. Les `break` ne peuvent apparaitre que dans les `switch`.
   3. Additionnez le nombre de `if`, `for`, `while`, `switch` et `try` pour chaque méthode. Ce
      nombre ne doit pas dépasser 5.
3. Indentez votre code. Assurez-vous que l’indentation est faite avec des espaces.
4. Commentaires
   - Commentez l’en-tête de chaque classe et méthode que vous ajoutez
   - Une ligne contient soit un commentaire, soit du code, pas les deux.
   - Utilisez des noms d’identificateur significatif.
   - Une ligne de commentaire ne devrait pas dépasser 120 caractères. Continuez sur la ligne
   suivante au besoin.
   - Nous utilisons Javadoc :
     - La première ligne d’un commentaire doit contenir une description courte (1 phrase)
     de la méthode ou la classe.
       - Courte.
       - Complète.
       - Commencez la description avec un verbe.
       - Assurez-vous de ne pas simplement répéter le nom de la méthode, donnez
       plus d’information.
       
     - Ensuite, au besoin, une description détaillée de la méthode ou classe va suivre.
       - Indépendant du code. Les commentaires d’en-têtes décrivent ce que la
       méthode fait, ils ne décrivent pas comment c’est fait.
       - Si vous avez besoin de mentionner l’objet courant, utilisez le mot `this`.
     - Ensuite, avant de placer les tags, placez une ligne vide.
     - Placez les tag `@param`, `@return` et `@throws` au besoin.
       - `@param` : décris les valeurs acceptées pour la méthode.
     - Dans les commentaires, placer les noms de variable et autre ligne de code entre les
       tags `{@code … }`.
     - Écrivez les commentaires à la troisième personne. 
   
## 4 REMISE

  Remettre le TP par l’entremise de Moodle. Remettez seulement votre fichier
  `ListeDoublementChaine.java`. Le TP est à remettre avant le 25 avril 23:59. 
  
## 5 ÉVALUATION

  - Fonctionnalité (10 pts) : Les tests unitaires seront utilisés pour vérifier le fonctionnement de
  votre TP. Pour que les tests soient comptés, il faut que les méthodes aient du code qui essaie de
  résoudre le problème.
  - Structure (2 pts) : diviser en méthode simple, ne répéter pas de code.
  - Lisibilité (1 pt) : commentaire, indentation et noms d’identificateur significatif.