package modele;

/**
 * Pas s�r qu'on ait besoin de cette classe. On verra plus tard
 *
 */

public class Human extends Player{

	private String name;

	public Human(String name, int color) {
		super(color);
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
