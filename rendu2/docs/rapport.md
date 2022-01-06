# Rapport de décision
#
Plusieurs changements et ajouts ont été apportés au projet après le rendu de la première version. 
# Plateau de jeu

Concernant le plateau de jeu, la représentation en un tableau deux dimensions 5x5 reste inchangée. Cependant nous avons ajouté deux fonctions : 
getPositionnable(x: Integer, y: Integer): Positionnable = qui va retourner la position de la case. Cette fonction est utile notamment pour le calcul des points.
getBoard(): Positionnable = cette fonction va nous retourner le plateau du joueur indispensable pour calculer le score. 


## Tuiles

Les tuiles sont composées de deux terrains (gauche et droite). Les tuiles sont directement triés par ordre croissant lorsqu’elles sont posées sur le plateau (la tuile la plus petite est la tuile qui se trouve en première position en partant vers le haut). Elle sera permise grâce à la méthode compareTo qui va comparer.

## Modes de jeu 
Les modes de jeu sont modélisés à l’aide du pattern Decorator. Nous avons opéré un changement de direction à ce sujet car, le pattern strategy ne correspondait pas exactement à nos besoin. En effet, cela nous empêchai de pouvoir seléctionner les deux modes de jeux en même temps. <br>
Le ModeDecorator possède un attribut privée wrapee de type GameMode qui est une interface. Les deux modes de jeux Harmony et MiddleKingdom, qui représentent les deux règle de calcul, hérite de cette classe. <br>
Cela permettra donc au joueur de choisir, sans problème, entre les modes : <br>
<br>
- Normal 
- Harmony 
- MiddleKingdom
- Harmony+MiddleKingdom
<br> 
Le calcul de points est réalisé grâce à la méthode calculeScore(p:PlayerBoard) qui retourne le résultat à l’aide du calcul : Nbre de case * Nbre de couronnes où Nbre de case correspond au nombre de case formant un domaine contenant au moins une couronne.<br><br>
Dans le cas du mode de jeu MiddleKingdom, une méthode calculateKingdomBonus (p : PlayerBoard) est ajouté permettant d’ajouter 10 points si le chateau est au milieu du plateau (en position x=2 et y=2)<br><br>
Dans le cas du mode de jeu Harmony, une méthode isHarmony(p:PlayerBoard) va vérifier si le plateau du joueur est complet ou non (qui ne présente aucune case vide) et la méthode calculateHarmonyBonus va permet de retourner la valeur +5 si il est complet sinon -5. Le joueur perdra ou gagnera 5 points en fonction du remplissage de son plateau. 


## Modèle MVC

Nous avons modélisé notre application en modèle MVC, c'est-à-dire un controller (GameController) qui va modifier le modèle lorsqu’un joueur va faire une action, une vue qui va transmettre au controller les demandes utilisateurs et afficher continuellement l’état du modèle.<br>

Les vues ordonnent la création des objets métiers au controller, qui lui, va ordonner à la classe maître (GameContext) la création des objets métiers. \
Le controller ne crée pas lui-même les objets métiers car il y aurait une trop forte dépendance entre le modèle et le controller.

Voici comment sont disposées les classes en appliquant le modèle MVC :

<p align="center">
  <img src="MVC.png" width="150" title="hover text">
</p>



## Modèle maître

La classe GameContext est notre modèle maître et se chargera de récolter les informations utiles pour le déroulement d’une partie.<br> 
De nouvelles méthodes ont été ajouté à cette classe: <br>
<br>
-skipturn(): cette méthode va permettre au joueur de sauter le tour seulement si le joueur ne peut pas placer la tuile sur son plateau. <br>
-rotateCurrentTile() et reverseCurrentTile() sont deux méthodes qui permettent respectivement de pivoter la tuile (4 types de direction) et d’inverser le côté gauche vers le côté droit de la tuile.<br>
<br>
Nous avons également des méthodes dans cette classe qui va permettre de choisir aléatoirement qui commence à choisir la tuile dès le début de la tuile mais aussi qui commencera au tour suivant en fonction du numéro de la tuile.


## Vues

Les vues sont toutes créées à partir d’une vue principale nommée MyWindow, qui va créer différentes fenêtres :<br> 
<br>
-MainMenu qui affiche le menu principal <br>
-ChoosingMenu qui affiche le menu permettant de choisir le nombre de joueur et le mode de jeu <br>
-GameView qui affiche la partie déroulante<br>
-EndView qui affiche la fin de la partie ainsi que le score de chaque joueur <br>
<br>
La classe Log va permettre de notifier le tour du joueur ainsi que sa couleur sur le terminal. <br>
<br>
Les vues ont été réfléchies à l’aide de Figma où différents prototypes sont réalisés afin de réaliser une jolie vue pour le jeu Kingdomino. <br>
<br>
Ci-dessous un extrait de nos prototypes<br>
<p align="center">
  <img src="Prototype_Views.png" width="350" title="hover text">
</p>


## Contrôleur

Le contrôleur fait le lien entre le modèle et la vue, il récolte les demandes utilisateurs à partir de la vue, et les transmets en modifiant les modèles.
Le contrôleur connaît donc le modèle, et est connu par la vue.<br><br>
Il va donc permettre d’initialiser la partie grâce à la méthode initializeGame(), de pouvoir placer le château, de pouvoir choisir les tuiles et de les placer sur le plateau du joueur. 

## Utilities

Nous avons pris de nouvelles décisions concernant les utilitaires.
Nous avons créé une nouvelle classe FontReader qui utilise le pattern Singleton. Le patron de conception Singleton va s’assurer de gérer lui-même son instance et interdire aux autres classes de la construire. Cette classe permet aux utilisateurs d’avoir des styles de police spécifiques. <br><br>
De plus, IMGReader et CSVReader ont été mis en place au premier rendu. IMGReader permet d’afficher les images que ce soit pour le fond d’écran, les plateaux des joueurs mais aussi les tuiles. Quant au CSVReader, il va permettre de lire un fichier CSV contenant les numéros des tuiles ainsi que leur terrain et le nombre de couronnes qu'il contient.


## Stratégie par nombre de joueur

Concernant la stratégie par nombre de joueurs, rien a été modifié après la première version. 
Pour rappel, le nombre de tuiles, de plateaux et de roi changent en fonction du nombre de joueurs.


## Tests Unitaires

Nous avons décidé de faire des tests unitaires afin d’assurer le bon fonctionnement de notre application dès le début mais aussi plus tard. Ces tests là imite différents scénarios de partie où l’on pose des tuiles sur un plateau.


## Autres décisions

La fonction getColor() de l’enum GroundColor renvoie, à partir d’un String, une variable de type GroundColor. Cela a été validé que cette manière de faire était assez générique par le prof.

Nous avons voulu laisser au contrôleur de créer les éléments du jeu c’est-à-dire joueurs, plateau, tuiles, etc… par exemple une classe Deck qui créera les tuiles.  \
Cependant, après maintes réflexions et discussions avec le prof, si le contrôleur connaît tous les objets métiers alors il y aura un grand nombre de dépendances. Il faudrait alors qu’on crée une ou plusieurs classes ordonnées par le contrôleur (patron de structure - _Façade)._

Nous avons créé aussi des classes utilitaires comme CSVReader et IMGReader qui permettent de lire des fichiers ressources. Nous avons décidé de les mettre en tant que Modèle dans le MVC, préféré à l’idée de la séparer du MVC et d’en créer un nouveau package.


## Exécutable

Nous avons un exécutable au nom de KingDomino.jar qui se trouve dans le dossier out/artifacts/KingDomino_jar
L’exécutable affiche le jeu KingDomino qui peut etre joué. A la fin de chaque partie, le score du joueur est affiché. L’utilisateur peut décider de quitter le jeu en plein milieu de la partie, mais aussi rejouer à la fin de la partie. 


## Encapsulation

### Problème
Nous avions un problème d’encapsulation, en effet dans nos vues, la méthode utilisé pour récupérer était celle ci :

<p align="center">
  <img src="Encapsulation_problem.png" width="450" title="hover text">
</p>

Et la fonction utilisé pour avoir les joueurs dans GameContext :

<p align="center">
  <img src="getPlayers.png" width="450" title="hover text">
</p>

Cela nous faisait trop dépendre de l’implémentation des données choisies.

### Solution

Faire 2 fonctions différentes qui vont renvoyer respectivement le ième joueur et le nombre de joueurs dans la partie.

<p align="center">
  <img src="Solution.png" width="450" title="hover text">
</p>

### Avantage
L’avantage de faire cela est que nous n’allons plus dépendre de l’implémentation que nous avons faite à propos des joueurs dans les vues. Que ce soit une Arraylist, une Map ou un Array[], la vue aura quoiqu’il arrive aucun souci grâce à ses méthodes dans GameContext (qui elle devront bien sûr être modifiées si l’on change la structure des données utilisées).










