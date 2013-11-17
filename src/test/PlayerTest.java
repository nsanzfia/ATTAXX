package test;



import org.junit.Assert;
import org.junit.Test;

import modele.Human;
import modele.IPlateau;
import modele.IPlayer;
import modele.Machine;
import modele.Plateau;
import modele.exception.InvalidCoord;

public class PlayerTest {
	private static IPlateau plateauDeTest=new Plateau(8,4);
	private static IPlayer human=new Human("joueur de Test", 2);
	private static IPlayer machine=new Machine(3);
	
	//countOwnPions OK & getOwnPions OK & itsOwnTurn OK
	@Test
	public void playerTest() {
		System.out.println("PLATAU DE TEST: \n"+plateauDeTest.toString());
		Assert.assertEquals(human.countOwnPions(plateauDeTest), 2);
		Assert.assertNotNull(human.getOwnPions(plateauDeTest));
		Assert.assertEquals(machine.getOwnPions(plateauDeTest).size(), 2);
		human.setOwnTurn(true);
		Assert.assertTrue(human.itsOwnTurn());
		Assert.assertFalse(machine.itsOwnTurn());
	}
	
	//hasPossibilitiesOfPlay ok
	@Test
	public void playerTest1() throws InvalidCoord {
		Assert.assertTrue(human.hasPossibilitiesOfPlay(plateauDeTest));
		Assert.assertTrue(machine.hasPossibilitiesOfPlay(plateauDeTest));
		IPlateau p = new Plateau(8);
		for(int i=0; i<p.getPlateau().length; i++)
			for(int j=0; j<p.getPlateau()[i].length; j++)
				p.getPlateau()[i][j]=2;
		System.out.println(p.toString());
		Assert.assertFalse(machine.hasPossibilitiesOfPlay(p));
		Assert.assertFalse(human.hasPossibilitiesOfPlay(p));
		p.getPlateau()[1][2]=3;
		Assert.assertFalse(human.hasPossibilitiesOfPlay(p));
		p.getPlateau()[1][2]=1;
		Assert.assertTrue(human.hasPossibilitiesOfPlay(p));
	}

}
