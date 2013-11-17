package modele.exception;

@SuppressWarnings("serial")
public class InvalidCoord extends Exception {
	public InvalidCoord() {
		super();
	}
	public InvalidCoord(String s) {
		super(s);
	}
}
