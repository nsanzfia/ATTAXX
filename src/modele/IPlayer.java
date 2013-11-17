package modele;

import java.util.List;

import modele.exception.InvalidCoord;

/**
 * Interface qui represente aussi bien le joueur humain que la machine.
 * La difference entre un joueur humain et la machine c'est au niveau de la recuperation de leur jeu.
 * A savoir qu'on recupere le jeu d'un joueur via l'IHM alors que le jeu de la machine, 
 * on le recupere via l'algorithme alpha-beta
 * Un joueur connait le plateau sur lequel il joue.
 *
 * @Cons
 * Param :
 *  - Plateau
 * Post : 
 *  coutnOwnPions = 2
 */
public interface IPlayer {
	/**
	 * @return le nombre de pions du joueur qui l'appelle.
	 */
	int countOwnPions(IPlateau plateau);
	/**
	 * renvoie les coordonnees des pions d'un joueur.
	 * @return un tableau des coordonnees
	 */

	List<ICoord> getOwnPions(IPlateau plateau);

	/**
	 * @return vrai si c'est le tour du joueur qui l'appelle, faux sinon.
	 */
	boolean itsOwnTurn();
	/**
	 * @return vrai si le joueur peut encore jouer, faux sinon
	 * @throws InvalidCoord 
	 */
	boolean hasPossibilitiesOfPlay(IPlateau plateau) throws InvalidCoord;
	
//	/**
//	 * enrgistre le nouveau plateau p
//	 * @param p le nouveau plateau
//	 */
//	void setNewPlateau(IPlateau p);
	
	/**
	 *Cette methode verifie si le pion se trouvant e la coordonnee c appartient bien au joueur qui l'appelle
	 * @param c : coordonnee du pion dont on cherche le proprietaire
	 * @return vraie si le pion appartient au joueur appelant, faux sinon
	 * @throws InvalidCoord 
	 */
	boolean itsOwnPion(ICoord c, IPlateau plateau) throws InvalidCoord;
	
	/**
	 * Methode pour verifier si c'est le tours de ce joueur de jouer
	 * @return vrai si c'est le tour du joueur de jouer; faux sinon
	 */
	public boolean isOwnTurn();
	
	/**
	 * Affecter si c'est le tour de ce joueur de jouer
	 * @param ownTurn qui peut etre vrai ou faux
	 */
	public void setOwnTurn(boolean ownTurn);
	
	/**
	 * Obtenir la couleur de ce jouer
	 * @return la valeur de la couleur de ce joueur
	 */
	public int getColor();
	
	/**
	 * Fonction pour definir la couleur du joueur (le type de joueur)
	 * @param color donne une valeur de couleur a ce joueur
	 */
	public void setColor(int color);
}
