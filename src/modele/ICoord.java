package modele;

/**
 * represente une coordonnee
 *
 */
public interface ICoord {

	/**
	 * @return la position horizontale du pion
	 */
	int getX();

	/**
	 * @return la distance verticale du pion
	 */
	int getY();
	
	/**
	 * setteur de l'abscisse
	 * @param x l'abcisse x
	 */
	void setX(int x);
	
	/**
	 * setteur de l'ordonnee
	 * @param y l'ordonnee
	 */
	void setY(int y);

	/**
	 * @param c la coordonn�e avec laquelle on veut calculer la distance
	 * @return le nombre de cases qui s�parent la case actuelle de la case entr�e en param�tre 
	 */
	int getDistance(ICoord c);
	
	/**
	 * Fonction pour comparer deux coordonnées
	 * @param c la coordonnée avec laquelle comparer
	 * @return true si les deux coordonnées sont égaux false sinon
	 */
	boolean equals(ICoord c);
	
	/**
	 * Fonction pour obtenir la description d'une coordonnée sous forme d'un string
	 * @return un string qui est la description d'une coordonnée et de la forme (x,y)
	 */
	String toString();
}
