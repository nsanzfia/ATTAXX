package modele;

import modele.exception.InvalidCoord;

/**
 * Classe qui joue le rele de controleur. 
 * Elle contrele la validite des actions du joueur. 
 * Elle sert de lien entre GameView et les autres classes.
 * Elle procede en delegant une classe pour la realisation de telle ou telle teche. 
 * Et elle ne fait quasiment que de la delegation.
 * 
 *
 *
 * @Cons
 * Param : 
 *  - taille 
 *  - niveau
 *  - nom du joueur
 *  - 
 * Post :
 *  - creation du joueur 
 *  - creation du plateau 
 *  - initialisation du niveau
 */
public interface IGame {

	
	/**
	 * Demande au plateau de verifier que :
	 * - la case de depart contient bien un pion
	 * - que le pion appartient au joueur p
	 * - que la case d'arrivee est vide
	 * - que la distance est valide
	 * 
	 * @param p : un joueur donne (homme ou machine)
	 * @param c1 : coordonnee de la case de depart
	 * @param c2 : coordonnee de la case d'arrivee
	 * @return retourne vrai si le deplacement est valide, faux sinon
	 * @throws InvalidCoord 
	 */
	boolean isValidMove(IPlayer p, ICoord c1, ICoord c2) throws InvalidCoord;
	
	/**
	 * renvoie le joueur (entre la machine et l'humain) e qui c'est le tour de jouer
	 * Pour cela elle appelle les methodes itsOwnTurn de player.
	 * @return Le joueur e qui c'est le tour de jouer.
	 */
	IPlayer getCurrentPlayer();
	
	/**
	 * Ajoute le joueur passe en parametre comme joueur qu a le jeu
	 * @param p le joueur a qui on veut passer la main
	 */
	void setCurrentPlayer(IPlayer p);
	
	/**
	 * Change le joueur courant
	 */
	void changeCurrentPlayer();


	
	/**
	 * @return l'instance du joueur humain
	 */
	IPlayer getHumanPlayer();
	
	/**
	 * @return l'instance de la machine
	 */
	IPlayer getMachinePlayer();
	
	/**
	 * @return le niveau de la partie actuelleles exceptions java test
	 */
	int getLevel();
	
	/**
	 * Verifie que la partie est finie et que l'humain a gagne
	 * incremente de 1 le niveau
	 * reconstruit le plateau
	 * enregistre le plateau chez chaque joueur
	 */
	void goToNextLevel();
	
	/**
	 * Verifie chez le joueur actuel s'il y a encore des possibilites de jeu 
	 * @return vrai si la partie est finie, faux sinon
	 * @throws InvalidCoord 
	 */
	boolean itsEndOfTheGame() throws InvalidCoord;
	
	/**
	 * renvoie le joueur qui a le plus de pions.
	 * Elle appelle la methode de calcul du nombre de pions de chaque joueur.
	 * @return le gagnant de la partie
	 */
	IPlayer getTheWinner();
	
	/**
	 * Obtenir le plateau du jeu
	 * @return le plateau du jeu
	 */
	IPlateau getPlateau();
	
	/**
	 * Ajoute le plateau du jeu
	 * @param plateau qui est ele nouveau plateau a ajouter
	 */
	void setPlateau(IPlateau plateau);

	/**
	 * Methode qui fait joueur le joueur qui a le tour
	 * @param c1 case de depart
	 * @param c2 case d'arrivee
	 * @return vrai si ça a jouer; faux sinon
	 * @throws InvalidCoord
	 */
	boolean currentPlayerPlay(ICoord c1, ICoord c2) throws InvalidCoord;

}
