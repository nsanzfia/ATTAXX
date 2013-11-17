package test;

import modele.Coord;
import modele.Game;
import modele.IGame;
import modele.exception.InvalidCoord;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class GameTest {
private static IGame gameDeTest=new Game(8, 3, "Azzedine");
private static IGame gameDeTest1=new Game(8, "Azzedine");

	
	//Constructeurs ok	
	@Test
	public void gameTest() {
		Assert.assertNotNull(gameDeTest);
		Assert.assertNotNull(gameDeTest1);
		System.out.println("GAME DE TEST PLATEAU: "
				+gameDeTest.getPlateau().toString());
		System.out.println("GAME DE TEST PLATEAU1: "
				+gameDeTest1.getPlateau().toString());
	}

	//isvalidMove OK
	//Une simulation de jeu OK
	//Pour bien voir la prise en compte des cases grises:
	//Remplacer l'attribut l'objet gameDeTest1 par gameDeTest
	//Les erreurs qui seront causés seront du aux cases grises 
	@Test(expected=InvalidCoord.class)
	public void gameTest1() throws InvalidCoord {
		gameDeTest1.getPlateau().getPlateau()[1][1]=
				gameDeTest1.getPlateau().getPlateau()[2][2]=
				gameDeTest1.getPlateau().getPlateau()[6][1]=1;
		Assert.assertFalse(gameDeTest1.isValidMove(gameDeTest1.getHumanPlayer(), 
				new Coord(0,0), new Coord(0,0)));
		Assert.assertTrue(gameDeTest1.isValidMove(gameDeTest1.getHumanPlayer(), 
				new Coord(0,0), new Coord(1,1)));
		Assert.assertTrue(gameDeTest1.isValidMove(gameDeTest1.getHumanPlayer(), 
				new Coord(0,0), new Coord(2,2)));
		Assert.assertTrue(gameDeTest1.isValidMove(gameDeTest1.getMachinePlayer(), 
				new Coord(0,7), new Coord(1,6)));
		Assert.assertFalse(gameDeTest1.isValidMove(gameDeTest1.getHumanPlayer(), 
				new Coord(7,0), new Coord(6,1)));
		Assert.assertTrue(gameDeTest1.isValidMove(gameDeTest1.getMachinePlayer(), 
				new Coord(7,0), new Coord(6,1)));
		//Déclencheur de l'exception
		gameDeTest1.isValidMove(gameDeTest1.getHumanPlayer(), new Coord(9, 0), null);
	}
	
	//Une simulation de jeu OK
	//Pour bien voir la prise en compte des cases grises:
	//Remplacer l'attribut l'objet gameDeTest1 par gameDeTest
	//Les erreurs qui seront causés seront du aux cases grises 
	@Test
	public void gameTest2() throws InvalidCoord {
		Assert.assertNull(gameDeTest1.getCurrentPlayer());
		gameDeTest1.setCurrentPlayer(gameDeTest1.getHumanPlayer());
		Assert.assertNotNull(gameDeTest1.getCurrentPlayer());
		gameDeTest1.changeCurrentPlayer();
		gameDeTest1.setCurrentPlayer(gameDeTest1.getMachinePlayer());
		Assert.assertEquals(gameDeTest1.getCurrentPlayer().getColor(), 3);
		Assert.assertNull(gameDeTest1.getTheWinner());
		
		//La machine se bouge
		Assert.assertTrue(gameDeTest1.isValidMove(gameDeTest1.getCurrentPlayer(), 
				new Coord(7, 0), new Coord(6,1)));
		Assert.assertFalse(gameDeTest1.currentPlayerPlay(new Coord(7, 0), 
				new Coord(3, 3)));
		Assert.assertTrue(gameDeTest1.currentPlayerPlay(new Coord(7, 0), 
				new Coord(6, 1)));
		//Le nombre de pions de la machine est nickel
		System.out.println("ETAT DE JEUX GAME MACHINE 1: "
				+gameDeTest1.getPlateau().toString());
		Assert.assertEquals(gameDeTest1.getCurrentPlayer().
				countOwnPions(gameDeTest1.getPlateau()), 3);
		//Le jeu est il fini
		Assert.assertFalse(gameDeTest1.itsEndOfTheGame());
		
		//A l'humain de jouer
		gameDeTest1.changeCurrentPlayer();
		Assert.assertTrue(gameDeTest1.currentPlayerPlay(new Coord(7, 7), 
				new Coord(5, 5)));
		//Le nombre de pions de l'humain est nickel
		System.out.println("ETAT DE JEUX GAME HUMAIN 1: "
				+gameDeTest1.getPlateau().toString());
		Assert.assertEquals(gameDeTest1.getCurrentPlayer().
				countOwnPions(gameDeTest1.getPlateau()), 2);
		Assert.assertFalse(gameDeTest1.currentPlayerPlay(new Coord(7, 7), 
				new Coord(4, 4)));
		
		//A la machine de jouer
		gameDeTest1.changeCurrentPlayer();
		Assert.assertTrue(gameDeTest1.currentPlayerPlay(new Coord(0, 7), 
				new Coord(2, 5)));
		//Lee nombre de pions de l'humain est nickel
		System.out.println("ETAT DE JEUX GAME MACHINE 2: "
				+gameDeTest1.getPlateau().toString());
		Assert.assertEquals(gameDeTest1.getCurrentPlayer().
				countOwnPions(gameDeTest1.getPlateau()), 3);
		
		//A l'humain de jouer
		gameDeTest1.changeCurrentPlayer();
		Assert.assertTrue(gameDeTest1.currentPlayerPlay(new Coord(5, 5), 
				new Coord(6, 4)));
		//Le nombre de pions de l'humain est nickel
		System.out.println("ETAT DE JEUX GAME HUMAIN 2: "
				+gameDeTest1.getPlateau().toString());
		Assert.assertEquals(gameDeTest1.getCurrentPlayer().
				countOwnPions(gameDeTest1.getPlateau()), 3);
		
		//Le jeu est il fini
		Assert.assertFalse(gameDeTest1.itsEndOfTheGame());
		//Le gagnant à ce niveau: machine
		Assert.assertNull(gameDeTest1.getTheWinner());
		
		//A la machine de jouer
		gameDeTest1.changeCurrentPlayer();
		Assert.assertTrue(gameDeTest1.currentPlayerPlay(new Coord(6, 1), 
				new Coord(5,2)));
		//Lee nombre de pions de l'humain est nickel
		System.out.println("ETAT DE JEUX GAME MACHINE 3: "
				+gameDeTest1.getPlateau().toString());
		Assert.assertEquals(gameDeTest1.getCurrentPlayer().
				countOwnPions(gameDeTest1.getPlateau()), 4);
		
		//A l'humain de jouer
		gameDeTest1.changeCurrentPlayer();
		Assert.assertTrue(gameDeTest1.currentPlayerPlay(new Coord(6, 4), 
				new Coord(5, 3)));
		//Le nombre de pions de l'humain est nickel
		System.out.println("ETAT DE JEUX GAME HUMAIN 3: "
				+gameDeTest1.getPlateau().toString());
		Assert.assertEquals(gameDeTest1.getCurrentPlayer().
				countOwnPions(gameDeTest1.getPlateau()), 5);
		Assert.assertFalse(gameDeTest1.currentPlayerPlay(new Coord(5,2), new Coord(6,1)));
		
		//Le jeu est il fini
		Assert.assertFalse(gameDeTest1.itsEndOfTheGame());
		//Le gagnant à ce niveau: machine
		Assert.assertNotNull(gameDeTest1.getTheWinner());
		Assert.assertEquals(gameDeTest1.getTheWinner().getColor(), 2);
	}
	
}
