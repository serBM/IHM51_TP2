
  

# TP2 IHM - INFO5 Polytech - 2019-2020



**Deadline de remise du TP :** Vendredi 15 novembre 2019 à 10h du matin.

  

**Membres du groupe :** Maxence Brès, Baptiste Bétend, Lucas Reygrobellet

## Architecture

Notre projet comporte trois branches, la master, une branche circular-menu qui implemente le menu circulaire avec click et une branche marking-menu qui implemente le marking menu avec action au mouvement de la souris.

## Éléments implémentés

 Nous sommes partis du code donné au départ qui était une application de dessin (paint miniature).

Nous avons dans un premier temps implémenté un nouveau type de tracé, qui est le tracé par ellipse.
Nous avons ensuite rajouté la possibilité de choisir des couleurs.
 
 Pour finir nous avons implémenté les menu circulaire et marking menu. Notre menu circulaire supporte un nombre variable d'options, il peut afficher jusqu'à 8 options différentes sur le cercle autour du pointeur, ainsi que plusieurs autres options dans une liste sous ce cercle.

## Mode d'utilisation

  Le menu de selection s'ouvre en cliquant avec le **clic doit**. Ce menu est d'abord un cercle composé de plusieurs couleurs qui apparait autour du pointeur. On peut selectionner une de ces couleurs en faisant **glisser la souris** sur l'une d'entre elle.
  
  Après avoir selectionné une couleur, un nouveau cercle apparait autour du pointeur permettant cette fois de selectionner un outil de dessin, en faisant **glisser la souris**, de la même manière.
  
  On peut à tout moment fermer le menu en utilisant le cliquant avec le **clic droit**, ou bien en utilisant le **clic gauche** pour dessiner.
  
  Avant d'utiliser pour la première fois le menu, il faut initialiser la classe tools en selectionnant une première fois un outil avec la barre en haut 
