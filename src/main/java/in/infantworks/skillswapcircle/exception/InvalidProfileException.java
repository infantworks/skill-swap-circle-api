package in.infantworks.skillswapcircle.exception;

public class InvalidProfileException extends Exception {


	private static final long serialVersionUID = 1L;

	public InvalidProfileException() {
		super("The given profile is invalid");
	}
	
	public InvalidProfileException(String message) {
		super(message);
	}
	
}
