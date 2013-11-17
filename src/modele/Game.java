package modele;

import modele.exception.InvalidCoord;

public class Game implements IGame {
	private IPlateau plateau;
	private IPlayer human;
	private IPlayer machine;
	private int level=0;

	public Game(int plateauSize, String humanName) {
		setPlateau(new Plateau(plateauSize));
		human=new Human(humanName, 2);
		machine=new Machine(3);
	}
	
	public Game(int plateauSize, int grayCases, String humanName) {
		setPlateau(new Plateau(plateauSize, grayCases));
		human=new Human(humanName, 2);
		machine=new Machine(3);
	}
	
	@Override
	public boolean isValidMove(IPlayer p, ICoord c1, ICoord c2) 
			throws InvalidCoord {
		if(p.itsOwnPion(c1, plateau) && plateau.isEmptyCase(c2) 
				&& plateau.isValidDistance(c1, c2))
			return true;
		else return false;
	}
	
	@Override
	public boolean currentPlayerPlay(ICoord c1, ICoord c2) 
			throws InvalidCoord {
		if(!isValidMove(getCurrentPlayer(), c1, c2)) return false;
		else {
			if(c1.getDistance(c2)==0) plateau.moveAndDuplicate(c1, c2);
			else plateau.moveWithoutDuplicate(c1, c2);
			return true;
		}
	}

	@Override
	public IPlayer getCurrentPlayer() {
		if(human.itsOwnTurn()) return human;
		else if(machine.isOwnTurn()) return machine;
		else return null;
	}

	@Override
	public void setCurrentPlayer(IPlayer p) {
		p.setOwnTurn(true);
	}
	
	@Override
	public void changeCurrentPlayer() {
		IPlayer p = null;
		if(machine.isOwnTurn()) p=machine;
		if(human.isOwnTurn()) p=human;
		p.setOwnTurn(false);
		if(p.getColor()==2) machine.setOwnTurn(true);
		if(p.getColor()==3) human.setOwnTurn(true);
	}
	
	@Override
	public IPlayer getHumanPlayer() {
		return human;
	}

	@Override
	public IPlayer getMachinePlayer() {
		return machine;
	}

	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public void goToNextLevel() {
		// TODO to be performed
		level++;
	}

	@Override
	public boolean itsEndOfTheGame() throws InvalidCoord {
		boolean gameEnd=false;
		if(!human.hasPossibilitiesOfPlay(plateau)) gameEnd=true;
		else if(!machine.hasPossibilitiesOfPlay(plateau)) gameEnd=true;
		else
			for(int i=0; i<plateau.getPlateau().length; i++)
				for(int j=0; i<plateau.getPlateau()[i].length; j++)
					if(plateau.getPlateau()[i][j]==1) 
						{ gameEnd=false; break; }
		return gameEnd;
	}

	@Override
	public IPlayer getTheWinner() {
		if(human.getOwnPions(plateau).size()>machine.getOwnPions(plateau).size())
			return human;
		else if(human.getOwnPions(plateau).size()<machine.getOwnPions(plateau).size())
			return machine;
		else return null;
	}

	public IPlateau getPlateau() {
		return plateau;
	}

	public void setPlateau(IPlateau plateau) {
		this.plateau = plateau;
	}
}
