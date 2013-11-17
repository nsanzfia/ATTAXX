package modele;

import java.util.Random;
/**
 *Classe utilitaire utilisee par tout le package
 */
public class Utils {
	/**
	 * Generer un nombre aléatoire
	 * @param minValue
	 * @param maxValue
	 * @return int
	 */
	public static int randomize(int minValue, int maxValue) {
		Random r = new Random();
		return minValue + r.nextInt(maxValue - minValue);
	}
	
	/**
	 * Genere une coordonnee aleatoire
	 * @param minValue
	 * @param maxValue
	 * @return la coordonnee generee automatiquement
	 */
	public static Coord randomizeCoord(int minValue, int maxValue) {
		int x = randomize(minValue, maxValue);
		int y = randomize(minValue, maxValue);
		Coord coord = new Coord(x, y);
		return coord;
	}
}
