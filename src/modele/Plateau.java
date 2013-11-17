package modele;

import java.util.ArrayList;
import java.util.List;

import modele.exception.InvalidCoord;

public class Plateau implements IPlateau {
	private int size=7;
	private int grayCases=0;
	private int[][] plateau=new int[size][size];
	
	public Plateau(int size) {
		this.size=size;
		plateau=new int [size][size];
		for(int i=0; i<plateau.length; i++) {
			for(int j=0; j<plateau[i].length; j++) {
				if(i==0 && j==0) plateau[i][j]=2;
				if(i==0 && j==size-1) plateau[i][j]=3;
				if(i==size-1 && j==0) plateau[i][j]=3;
				if(i==size-1 && j==size-1) plateau[i][j]=2;
				if(!(i==0 && j==0) && !(i==0 && j==size-1)
						&& !(i==size-1 && j==0) && !(i==size-1 && j==size-1))
					plateau[i][j] = 1;
			}
		}
	}
	
	public Plateau(int size, int grayCases) {
		this.size=size;
		this.setGrayCases(grayCases);
		plateau=new int[size][size];
		for(int i=0; i<plateau.length; i++) {
			for(int j=0; j<plateau[i].length; j++) {
				if(i==0 && j==0) plateau[i][j]=2;
				if(i==0 && j==size-1) plateau[i][j]=3;
				if(i==size-1 && j==0) plateau[i][j]=3;
				if(i==size-1 && j==size-1) plateau[i][j]=2;
				if(!(i==0 && j==0) && !(i==0 && j==size-1)
						&& !(i==size-1 && j==0) && !(i==size-1 && j==size-1))
					plateau[i][j] = 1;
			}
		}
		
		//Générer les cases grises
		for(int i=0; i<grayCases; i++) {
			ICoord c = Utils.randomizeCoord(0, size-1);
			while(c.equals(new Coord(0, 0)) || c.equals(new Coord(0, size-1))
					||c.equals(new Coord(size-1, 0)) 
					|| c.equals(new Coord(size-1, size-1))
					||plateau[c.getX()][c.getY()]==0)
				c = Utils.randomizeCoord(0, size-1);
			plateau[c.getX()][c.getY()] = 0;
			System.out.println("Case grise:" + c.toString());
		}
	}


	@Override
	public int[][] getPlateau() {
		return plateau;
	}

	@Override
	public int getPlateauSize() {
		return size;
	}

	@Override
	public boolean isColorCase(int color, ICoord c) throws InvalidCoord {
		if(c==null || c.getX()>size-1 || c.getX()<0
				|| c.getY()>size-1 || c.getY()< 0)
				throw new InvalidCoord("Les coordonnées rentrées ne sont pas valides");
		if(plateau[c.getX()][c.getY()]==color) return true;
		else return false;
	}

	@Override
	public boolean isEmptyCase(ICoord c) throws InvalidCoord {
		if(c==null || c.getX()>size-1 || c.getX()<0
				|| c.getY()>size-1 || c.getY()< 0)
				throw new InvalidCoord("Les coordonnées rentrées ne sont pas valides");
		if(plateau[c.getX()][c.getY()]==1) return true;
		else return false;
	}

	@Override
	public boolean isValidDistance(ICoord c1, ICoord c2) throws InvalidCoord {
		if(c1==null || c2==null || c1.getX()>size-1 || c1.getX()<0
				|| c1.getY()>size-1 || c1.getY()< 0
				|| c2.getX()>size-1 || c2.getX()< 0
				|| c2.getY()>size-1 || c2.getY()< 0)
				throw new InvalidCoord("L'une des coordonnées rentrées n'est pas valide");
		if(c1.getDistance(c2)==1 || c1.getDistance(c2)==0) return true;
		else return false;
	}

	@Override
	public void moveAndDuplicate(ICoord c1, ICoord c2) throws InvalidCoord {
		if(c1==null || c2==null || c1.getX()>size-1 || c1.getX()<0
				|| c1.getY()>size-1 || c1.getY()< 0
				|| c2.getX()>size-1 || c2.getX()< 0
				|| c2.getY()>size-1 || c2.getY()< 0)
				throw new InvalidCoord("L'une des coordonnées rentrées n'est pas valide");
		plateau[c2.getX()][c2.getY()]=plateau[c1.getX()][c1.getY()];
		changeNeighborsColor(c2);
	}

	@Override
	public void moveWithoutDuplicate(ICoord c1, ICoord c2) throws InvalidCoord {
		if(c1==null || c2==null || c1.getX()>size-1 || c1.getX()<0
				|| c1.getY()>size-1 || c1.getY()< 0
				|| c2.getX()>size-1 || c2.getX()< 0
				|| c2.getY()>size-1 || c2.getY()< 0)
				throw new InvalidCoord("L'une des coordonnées rentrées n'est pas valide");
		plateau[c2.getX()][c2.getY()]=plateau[c1.getX()][c1.getY()];
		plateau[c1.getX()][c1.getY()]=1;
		changeNeighborsColor(c2);
	}

	private boolean changeNeighborsColor(ICoord c) throws InvalidCoord {
		if(c==null || c.getX()>size-1 || c.getX()<0
			|| c.getY()>size-1 || c.getY()< 0)
			throw new InvalidCoord("Les coordonnées rentrées ne sont pas valides");
		
		//La couleur de la case
		int colorCoord=plateau[c.getX()][c.getY()];
		
		//La couleur de l'adversaire
		int enemyColor=0;
		if(colorCoord==2) enemyColor=3;
		if(colorCoord==3) enemyColor=2;
		if(!(c.getX()+1 > size-1) && (plateau[c.getX()+1][c.getY()]==enemyColor)) 
			plateau[c.getX()+1][c.getY()]=colorCoord;
		if(!(c.getX()-1 < 0) && (plateau[c.getX()-1][c.getY()]==enemyColor))
			plateau[c.getX()-1][c.getY()]=colorCoord;
		if(!(c.getY()+1 > size-1) && (plateau[c.getX()][c.getY()+1]==enemyColor))
			plateau[c.getX()][c.getY()+1]=colorCoord;
		if(!(c.getY()-1 < 0) && (plateau[c.getX()][c.getY()-1]==enemyColor))
			plateau[c.getX()][c.getY()-1]=colorCoord;
		if(!(c.getX()-1 < 0) && !(c.getY()-1 < 0)
				&& (plateau[c.getX()-1][c.getY()-1]==enemyColor))
			plateau[c.getX()-1][c.getY()-1]=colorCoord;
		if(!(c.getX()-1 < 0) && !(c.getY()+1 > size-1)
				&& (plateau[c.getX()-1][c.getY()+1]==enemyColor))
			plateau[c.getX()-1][c.getY()+1]=colorCoord;
		if(!(c.getX()+1 > size-1) && !(c.getY()+1 > size-1)
				&& (plateau[c.getX()+1][c.getY()+1]==enemyColor))
			plateau[c.getX()+1][c.getY()+1]=colorCoord;
		if(!(c.getX()+1 > size-1) && !(c.getY()-1 < 0)
				&& (plateau[c.getX()+1][c.getY()-1]==enemyColor))
			plateau[c.getX()+1][c.getY()-1]=colorCoord;
		return true;
	}
	@Override
	public List<ICoord> getMovePossibilities(ICoord coord) throws InvalidCoord {
		if(coord==null || coord.getX()>size-1 || coord.getX()<0
				|| coord.getY()>size-1 || coord.getY()< 0)
				throw new InvalidCoord("Les coordonnées rentrées ne sont pas valides");
		
		if(!isMovable(coord)) return null;
		
		List<ICoord> coords = new ArrayList<ICoord>();
		
		//Déplacement sans sauts
		//horizontal
		if(!(coord.getX()+1 > size-1) && (plateau[coord.getX()+1][coord.getY()]==1)) 
			coords.add(new Coord(coord.getX()+1,coord.getY()));
		if(!(coord.getX()-1 < 0) && (plateau[coord.getX()-1][coord.getY()]==1))
			coords.add(new Coord(coord.getX()-1,coord.getY()));
		//vertical
		if(!(coord.getY()+1 > size-1) && (plateau[coord.getX()][coord.getY()+1]==1))
			coords.add(new Coord(coord.getX(),coord.getY()+1));
		if(!(coord.getY()-1 < 0) && (plateau[coord.getX()][coord.getY()-1]==1))
			coords.add(new Coord(coord.getX(),coord.getY()-1));
		//diagonal
		if(!(coord.getX()-1 < 0) && !(coord.getY()-1 < 0)
				&& plateau[coord.getX()-1][coord.getY()-1]==1)
			coords.add(new Coord(coord.getX()-1,coord.getY()-1));
		if(!(coord.getX()-1 < 0) && !(coord.getY()+1 > size-1) 
				&& plateau[coord.getX()-1][coord.getY()+1]==1)
			coords.add(new Coord(coord.getX()-1,coord.getY()+1));
		if(!(coord.getX()+1 > size-1) && !(coord.getY()+1 > size-1)
				&& plateau[coord.getX()+1][coord.getY()+1]==1)
			coords.add(new Coord(coord.getX()+1,coord.getY()+1));
		if(!(coord.getX()+1 > size-1) && !(coord.getY()-1 < 0)
				&& plateau[coord.getX()+1][coord.getY()-1]==1)
			coords.add(new Coord(coord.getX()+1,coord.getY()-1));
	
		//Deplacements avec sauts de cases
		//horizontal
		if(!(coord.getX()+2 > size-1) && (plateau[coord.getX()+2][coord.getY()]==1)) 
			coords.add(new Coord(coord.getX()+2,coord.getY()));
		if(!(coord.getX()-2 < 0) && (plateau[coord.getX()-2][coord.getY()]==1))
			coords.add(new Coord(coord.getX()-2,coord.getY()));
		//vertical
		if(!(coord.getY()+2 > size-1) && (plateau[coord.getX()][coord.getY()+2]==1))
			coords.add(new Coord(coord.getX(),coord.getY()+2));
		if(!(coord.getY()-2 < 0) && (plateau[coord.getX()][coord.getY()-2]==1))
			coords.add(new Coord(coord.getX(),coord.getY()-2));
		//diagonal
		if(!(coord.getX()-2 < 0) && !(coord.getY()-2 < 0)
				&& plateau[coord.getX()-2][coord.getY()-2]==1)
			coords.add(new Coord(coord.getX()-2,coord.getY()-2));
		if(!(coord.getX()-2 < 0) && !(coord.getY()+2 > size-1) 
				&& plateau[coord.getX()-2][coord.getY()+2]==1)
			coords.add(new Coord(coord.getX()-2,coord.getY()+2));
		if(!(coord.getX()+2 > size-1) && !(coord.getY()+2 > size-1)
				&& plateau[coord.getX()+2][coord.getY()+2]==1)
			coords.add(new Coord(coord.getX()+2,coord.getY()+2));
		if(!(coord.getX()+2 > size-1) && !(coord.getY()-2 < 0)
				&& plateau[coord.getX()+2][coord.getY()-2]==1)
			coords.add(new Coord(coord.getX()+2,coord.getY()-2));

		return coords;
	}
	
	@Override
	public boolean isMovable(ICoord coord) throws InvalidCoord {
		if(coord==null || coord.getX()>size-1 || coord.getX()<0
				|| coord.getY()>size-1 || coord.getY()< 0)
				throw new InvalidCoord("Les coordonnées rentrées ne sont pas valides");
		if(plateau[coord.getX()][coord.getY()]==2
				|| plateau[coord.getX()][coord.getY()]==3) return true;
		else return false;
	}
	@Override
	public String toString() {
		String s = "\n";
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < plateau[i].length; j++) {
				s+="  ";
				s+=plateau[i][j]; 
				if(j == size-1)
					s+="\n";
			}
		}
		return s;
	}

	public int getGrayCases() {
		return grayCases;
	}

	public void setGrayCases(int grayCases) {
		this.grayCases = grayCases;
	}

}
