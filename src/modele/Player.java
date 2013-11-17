package modele;

import java.util.ArrayList;
import java.util.List;

import modele.exception.InvalidCoord;

public class Player implements IPlayer {

	private int color;
	private boolean ownTurn=false;
	
	public Player(int color) {
		this.color=color;
	}
	
	@Override
	public int countOwnPions(IPlateau plateau) {
		int pions=0;
		int [][] p = plateau.getPlateau();
		for(int i=0; i<p.length; i++)
			for(int j=0; j<p[i].length; j++)
				if(p[i][j]==color)
					pions++;
		return pions;
	}

	@Override
	public List<ICoord> getOwnPions(IPlateau plateau) {
		List<ICoord> coords = new ArrayList<ICoord>();
		int [][] p = plateau.getPlateau();
		for(int i=0; i<p.length; i++)
			for(int j=0; j<p[i].length; j++)
				if(p[i][j]==color)
					coords.add(new Coord(i,j));
		return coords;
	}

	@Override
	public boolean itsOwnTurn() {
		if(ownTurn) return true;
		else return false;
	}

	@Override
	public boolean hasPossibilitiesOfPlay(IPlateau plateau) throws InvalidCoord {
		boolean possibility = false;
		List<ICoord> coords=getOwnPions(plateau);
		for(ICoord coord: coords)
			if(plateau.getMovePossibilities(coord).size()!=0) possibility=true;
		return possibility;
	}

//	@Override
//	public void setNewPlateau(IPlateau p) {
//		// TODO Pas la peine d'aggréger le plateau. Gardons le metier propre
//	}

	@Override
	public boolean itsOwnPion(ICoord c, IPlateau plateau) throws InvalidCoord {
		if(c.getX()>plateau.getPlateauSize()-1 || c.getX()<0
				|| c.getY()>plateau.getPlateauSize()-1 || c.getY()< 0)
				throw new InvalidCoord("Les coordonnées rentrées ne sont pas valides");
		if(plateau.getPlateau()[c.getX()][c.getY()]==color) return true;
		else return false;
	}

	/**
	 * Getteur de la couleur du joueur
	 * @return la couleur du joueur
	 */
	public int getColor() {
		return color;
	}

	/**
	 * Setteur de la couleur du joueur
	 * @param color Changer la couleur du joueur
	 */
	public void setColor(int color) {
		this.color=color;
	}

	public boolean isOwnTurn() {
		return ownTurn;
	}

	public void setOwnTurn(boolean ownTurn) {
		this.ownTurn=ownTurn;
	}
}
