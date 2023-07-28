package in.infantworks.skillswapcircle.exception;

public class ProfileServiceFailedException extends Exception{

	private static final long serialVersionUID = 1L;

	public ProfileServiceFailedException() {
		super("Service Failed");
	}
	
	public ProfileServiceFailedException(String message) {
		super(message);
	}
	
}
