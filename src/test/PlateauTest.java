package test;


import modele.Coord;
import modele.IPlateau;
import modele.Plateau;
import modele.exception.InvalidCoord;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PlateauTest {
	private static IPlateau plateauDeTest = new Plateau(8,4);
	
	//constructeurs ok	
	@Test
	public void plateauTest() {
		IPlateau plateau=new Plateau(7);
		Assert.assertEquals(7, plateau.getPlateauSize());
		System.out.println("constructeur plateau(int): \n"+plateau.toString());
		IPlateau p1=new Plateau(7,3);
		Assert.assertEquals(p1.getPlateau()[0][0], 2);
		Assert.assertEquals(p1.getPlateau()[7-1][0], 3);
		Assert.assertEquals(p1.getPlateau()[0][7-1], 3);
		Assert.assertEquals(p1.getPlateau()[7-1][7-1], 2);
		System.out.println("constructeur plateau(int,int): \n"+p1.toString());
	}
	
	//isColorCase OK
	@Test(expected=InvalidCoord.class)
	public void plateauTest1() throws InvalidCoord {
		System.out.println("PLATEAU DE TEST\n" + plateauDeTest.toString());
		Assert.assertTrue(plateauDeTest.isColorCase(2, new Coord(0,0)));
		Assert.assertTrue(plateauDeTest.isColorCase(3, new Coord(0,7)));
		Assert.assertTrue(plateauDeTest.isColorCase(3, new Coord(7,0)));
		Assert.assertTrue(plateauDeTest.isColorCase(2, new Coord(7,7)));
		Assert.assertFalse(plateauDeTest.isColorCase(0, new Coord(7,7)));
		plateauDeTest.isColorCase(1, new Coord(8,9));
	}
		
	//isEmptyCase Ok
	@Test(expected=InvalidCoord.class)
	public void plateauTest2() throws InvalidCoord {
		plateauDeTest.getPlateau()[4][4]=1;
		Assert.assertTrue(plateauDeTest.isEmptyCase(new Coord(4,4)));
		Assert.assertFalse(plateauDeTest.isEmptyCase(new Coord(0,0)));
		plateauDeTest.isEmptyCase(new Coord(-2,4));
	}
	
	//isvalidDistance OK & Coord.getDistance OK
	@Test(expected=InvalidCoord.class)
	public void plateauTest3() throws InvalidCoord {
		Assert.assertTrue(plateauDeTest.isValidDistance(new Coord(0,0), new Coord(0,2)));
		Assert.assertTrue(plateauDeTest.isValidDistance(new Coord(4,4), new Coord(6,4)));
		Assert.assertTrue(plateauDeTest.isValidDistance(new Coord(4,4), new Coord(5,4)));
		Assert.assertFalse(plateauDeTest.isValidDistance(new Coord(0,5), new Coord(0,2)));
		Assert.assertFalse(plateauDeTest.isValidDistance(new Coord(4,4), new Coord(7,4)));
		Assert.assertFalse(plateauDeTest.isValidDistance(new Coord(3,3), new Coord(0,2)));
		Assert.assertFalse(plateauDeTest.isValidDistance(new Coord(5,2), new Coord(6,4)));
		Assert.assertTrue(plateauDeTest.isValidDistance(new Coord(2,2), new Coord(3,3)));
		Assert.assertTrue(plateauDeTest.isValidDistance(new Coord(2,2), new Coord(4,4)));
		Assert.assertTrue(plateauDeTest.isValidDistance(new Coord(5,1), new Coord(3,3)));
		Assert.assertTrue(plateauDeTest.isValidDistance(new Coord(3,3), new Coord(1,1)));
		Assert.assertTrue(plateauDeTest.isValidDistance(new Coord(0,7), new Coord(2,5)));
		Assert.assertFalse(plateauDeTest.isValidDistance(new Coord(0,0), new Coord(4,4)));
		Assert.assertTrue(plateauDeTest.isValidDistance(new Coord(0,0), new Coord(2,2)));
		Assert.assertTrue(plateauDeTest.isValidDistance(new Coord(7,7), new Coord(6,6)));
		Assert.assertTrue(plateauDeTest.isValidDistance(new Coord(7,7), new Coord(5,5)));
		//Déclencheur d'exception
		Assert.assertTrue(plateauDeTest.isValidDistance(new Coord(9,0), new Coord(2,4)));
	}
	
	//moveAndDuplicateOK & ChangeNeighboursColorOK
	@Test(expected=InvalidCoord.class)
	public void plateauTest4() throws InvalidCoord {
		plateauDeTest.moveAndDuplicate(new Coord(0,0), new Coord(4,4));
		System.out.println("VERIFIER (4,4)="+plateauDeTest.getPlateau()[4][4]
				+" ET (0,0)="+plateauDeTest.getPlateau()[0][0]
				+ plateauDeTest.toString());
		Assert.assertEquals(plateauDeTest.getPlateau()[4][4],2);
		Assert.assertEquals(plateauDeTest.getPlateau()[0][0], 2);
		plateauDeTest.moveAndDuplicate(null, new Coord(4,4));
	}
	
	//moveWithoutDuplicateOK & ChangeNeighboursColorOK
	@Test(expected=InvalidCoord.class)
	public void plateauTest5() throws InvalidCoord {
		plateauDeTest.moveWithoutDuplicate(new Coord(0,0), new Coord(4,4));
		System.out.println("VERIFIER (4,4)="+plateauDeTest.getPlateau()[4][4]
				+ plateauDeTest.toString());
		Assert.assertEquals(plateauDeTest.getPlateau()[4][4],2);
		Assert.assertEquals(plateauDeTest.getPlateau()[0][0], 1);
		plateauDeTest.moveAndDuplicate(new Coord(4,4), null);
	}	
	
	//getMovePossibilities OK && isMovable ok
	@Test(expected=InvalidCoord.class)
	public void plateauTest6() throws InvalidCoord {
		//Un plateau de test sans cases grises
		IPlateau plateau=new Plateau(8);
		System.out.println("Mon plateau sans cases grises:\n"+plateau.toString());
		Assert.assertEquals(plateau.getMovePossibilities(new Coord(0,0)).size(), 6);
		Assert.assertNull(plateau.getMovePossibilities(new Coord(3,3)));
		plateau.getPlateau()[3][3]=2;
		Assert.assertEquals(plateau.getMovePossibilities(new Coord(3,3)).size(), 16);
		plateau.getPlateau()[4][2]=plateau.getPlateau()[5][1]=0;
		Assert.assertEquals(plateau.getMovePossibilities(new Coord(3,3)).size(), 14);
		plateau.getPlateau()[5][5]=2;
		Assert.assertEquals(plateau.getMovePossibilities(new Coord(3,3)).size(), 13);
		//Déclencheur d'exception
		plateau.getMovePossibilities(new Coord(9,0));
	}
	
}