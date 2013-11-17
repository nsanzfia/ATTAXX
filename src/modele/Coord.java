package modele;

public class Coord implements ICoord {
	private int X;
	private int Y;

	public Coord(int x, int y) {
		X=x;
		Y=y;
	}
	@Override
	public int getX() {
		return X;
	}

	@Override
	public int getY() {
		return Y;
	}

	@Override
	public int getDistance(ICoord c) {
		
		//Distance horizontale
		if(X==c.getX()) return Math.abs(c.getY()-Y)-1;
		//Diastance verticale
		else if(Y==c.getY()) return Math.abs(c.getX()-X)-1;
		//Distance diagonale
		else {
			int distance=-1, i=1;
			//diagonale gauche vers le haut
			while((X-i>=c.getX()) && Y-i>=c.getY()) {
				if((X-i==c.getX()) && (Y-i==c.getY()))
					distance=i-1;
				i++;
			}
			i=1;
			//diagonale droite vers le bas
			while((X+i<=c.getX()) && Y+i<=c.getY()) {
				if((X+i==c.getX()) && (Y+i==c.getY()))
					distance=i-1;
				i++;
			}
			i=1;
			//diagonale gauche vers le bas
			while((X+i<=c.getX()) && Y-i>=c.getY()) {
				if((X+i==c.getX()) && (Y-i==c.getY()))
					distance=i-1;
				i++;
			}
			i=1;
			//diagonale droite vers le haut
			while((X-i>=c.getX()) && Y+i<=c.getY()) {
				if((X-i==c.getX()) && (Y+i==c.getY()))
					distance=i-1;
				i++;
			}
			return distance;
		}
	}

	@Override
	public void setX(int x) {
		X = x;
	}

	@Override
	public void setY(int y) {
		Y = y;
	}
	
	public boolean equals(ICoord c) {
		if(X==c.getX() && Y==c.getY())
			return true;
		else return false;
	}
	
	public String toString() {
		return "("+X+","+Y+")";
	}

}
