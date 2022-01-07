## Important
N'ouvrez pas le projet directement sur IntelliJ, mais plutôt le dossier rendu1 ou rendu2 selon ce que vous voulez regardez.
> Seul moyen pour nous de garder l'ancien rendu pour les professeurs


#Kingdomino
==========

![](rendu1/kingdomino/ressources/kingdomino_elements_small.jpg)

L'objectif de ce projet est de concevoir une application permettant de jouer au jeu de société Kingdomino.


Les règles
----------



### Résumé

Kingdomino se joue de 2 à 4 joueurs. L'objectif est de construire un royaume composé de Dominos :

- Un domino est composé 2 tuiles qui ont chacune un type de terrain (champs, herbe, forêt, etc.) et pour certaines une à 3 couronnes.
- Un royaume est un domaine carré de 5x5 tuiles dont un château

Chaque joueur ajoute les dominos un par un à son royaume. Un domino doit être posé :

- en connectant au moins l'une de ses tuiles à une tuile du royaume de même type
- en restant dans un carré de 5x5

Si un joueur ne peut pas placer le domino choisi, celui-ci est défaussé.

Le gagnant est celui qui construit le royaume rapportant le plus grand nombre de points de prestige, calculé comme la somme des points de chaque domaine. Le nombre de points d'un domaine correspond au produit de son nombre de tuiles avec son nombre de couronnes.

### Règles complètes

Vous trouverez les règles au format texte dans le [Livret de Règles](rendu1/docs/reglesKingdomino.pdf), et en regardant les vidéos d'explications :

- en version courte avec [LudoChrono](https://www.youtube.com/watch?v=jnM9yf65rpo) ou [Touslesprix.com](https://www.youtube.com/watch?v=MBPFPKehV44) : ~ 5 minutes
- en version plus longue avec [Yahndrev](https://www.youtube.com/watch?v=93VxIXxthFU) : ~ 15 minutes

### Matériel

La liste des dominos est disponible sur [ce dépot Github](https://github.com/RuPaulsDataRace/Kingdomino-For-Queens).

### Comment jouer

Dans le menu de choix, vous pouvez seletionner, le mode de jeu dans lequel vous voulez jouer, le nombre de participants, le nom des participants ainsi que la couleur de chaque participants. Une fois votre selection faite, vous pouvez lancer le jeu en cliquant sur continuer. <br><br>
Vous voilà maintenant dans le coeur du jeu, vous pouvez donc distinguer à gauche les plateaux des joueurs et à droite l'éspace d'action. Le joueur indiqué dans l'espace d'action doit à present placer son chateau où il le desir en cliquant simplement sur la case de son choix dans son plateau de jeu. Le plateau de jeu du joueur est distinguable grace au nom et à la couleur du joueur indiqué au dessus de celui-ci.<br><br>
Une fois les chateaux placés, le joueur indiqué dans l'espace d'action choisi sa première tuile en cliquant simplement dessus, cette action est à repéter pour chaques joueurs. Une fois toutes les tuiles choisis, les joueurs les places dans une après l'autre dans l'ordre determiné par les règles (cf. Livret de Règles), pour ce faire, le joueur verra sa tuile dans l'espace d'action, il aura à sa disposition deux bouton pour tourner la tuile dans le sens désiré. Une fois la tuile dans le bon sens, le joueur peut alors seletionner la case où il souhaite la placer en cliquant dessus.<br><br>
Lorsque la partie est terminé les score de tous les joueurs sont affiché au centre de l'écran afin de determiner le classement, les plateaux de jeu sont affiché à gauche et à droite du classement.

