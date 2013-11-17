package modele;

import java.util.List;

import modele.exception.InvalidCoord;

/**
 * Classe qui represente le plateau, permet de construire ce dernier et d'implementer toutes les
 * operations qui s'effectuent sur un vrai plateau.
 * 
 * Un plateau est un tableau a deux dimensions d'entiers, comme suit :
	0 : case grise
	1 : case vide
	2 : pion rouge
	3 : pion bleu
	Exemple :
	exemplePlateau[7][7] = {
	  			{2,1,1,1,1,1,3},
				{1,1,1,1,1,1,1},
				{1,1,1,0,1,1,1},
				{1,1,0,1,0,1,1},
				{1,1,1,0,1,1,1},
				{1,1,1,1,1,1,1},
				{3,1,1,1,1,1,2}};
 *
 * @cons
 * Param :
 *    - niveau
 *    - taille
 * Post :
 * Construit un plateau, avec une taille donnee et un nombre de cases grises dependant du niveau.
 * Le nombre de cases grises est egale a 5*Niveau*(taille*taille)/100.
 * Leur emplacement sur le plateau est aleatoire. il ne faut juste pas qu'elle occupe un coin du plateau
 *  car les coins sont occupes par les pions de depart 
 *
*/
public interface IPlateau {
	
	/**
	 * @return le plateau
	 */
	int[][] getPlateau();
	
	/**
	 * @return la taille du plateau : 7 pour 7X7, 11 pour 11X11...
	 */
	int getPlateauSize();

	/**
	 * verifie que la case de coordonnees c contient bien un pion et qu'il est de la couleur pass� en param�tre
	 * 
	 * @param c
	 *            case de depart
	 * @param color
	 *            l'int representant la couleur du pion (2 ou 3)
	 * @return vraie si la case contient un pion valide, faux sinon
	 * @throws InvalidCoord 
	 */
	boolean isColorCase(int color, ICoord c) throws InvalidCoord;

	/**
	 * verifie que la case de coordonnees c est vide
	 * 
	 * @param c la coordonnee de verification
	 * @return vraie si la case est vide, faux sinon
	 * @throws InvalidCoord 
	 */
	boolean isEmptyCase(ICoord c) throws InvalidCoord;

	/**
	 * verifie que la distance qui separe c1 de c2 ne depasse pas une case 
	 * en utilisant la methode getDistance de la classe Coord
	 * 
	 * @param c1 case de depart
	 * @param c2 case d'arrivee
	 * @return vrai si la distance est valide, faux sinon
	 * @throws InvalidCoord 
	 */
	boolean isValidDistance(ICoord c1, ICoord c2) throws InvalidCoord;

	/**
	 * ajoute un pion dans c2 tout en gardant le pion dans c1
	 * change la couleur des voisins
	 * 
	 * @param c1 case de depart
	 * @param c2 case d'arrivee
	 * @throws InvalidCoord 
	 */
	void moveAndDuplicate(ICoord c1, ICoord c2) throws InvalidCoord;

	/**
	 * ajoute un pion dans c2 tout en supprimant celui dans c1
	 * change la couleur des voisins
	 * @param c1 case de depart
	 * @param c2 case d'arrivee
	 * @throws InvalidCoord 
	 */
	void moveWithoutDuplicate(ICoord c1, ICoord c2) throws InvalidCoord;

//Une méthode privée ne fait jamais partie de l'interface	
//	/**
//	 * m�thode priv�e qui change la couleur des voisions d'une case  c donn�e
//	 * @param c coordonn�e de la case dont on souhaite changer la couleur des voisins
//	 */
//	void changeNeighborsColor(ICoord  c);
	
	/**
	 * Fonction pour retoiurner le plateau sous formme d'un string decrivant la matrice plateau
	 * @return un string qui decrit le plateau
	 */
	String toString();
	
	/**
	 * 
	 * @return le nombre de cases grises du plateau
	 */
	public int getGrayCases();
	
	/**
	 * Setteur du nombre de cases grises du plateau
	 * @param grayCases le nombre de cases du plateau
	 */
	public void setGrayCases(int grayCases);
	
	/**
	 * Le nombre de possibilités de bouger de a la coordonnee coord
	 * @param coord la case dont il faut voir les posssibilites de bouger
	 * @return la list de coordonnees possibles ou coord peut se deplacer
	 * @throws InvalidCoord
	 */
	List<ICoord> getMovePossibilities(ICoord coord) throws InvalidCoord;

	/**
	 * 
	 * @param coord coordonnee a verifier
	 * @return vrai si la coordonee contient un pion et peut donc être bouge; Faux sinon
	 * @throws InvalidCoord
	 */
	boolean isMovable(ICoord coord) throws InvalidCoord;
}
