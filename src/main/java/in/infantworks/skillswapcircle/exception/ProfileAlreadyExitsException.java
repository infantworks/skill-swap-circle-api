package in.infantworks.skillswapcircle.exception;

public class ProfileAlreadyExitsException extends Exception{

	private static final long serialVersionUID = 1L;

	public ProfileAlreadyExitsException() {
		super("Profile already exits");
	}
	
	public ProfileAlreadyExitsException(String message) {
		super(message);
	}
	
}
