package in.infantworks.skillswapcircle.exception;

public class ProfileNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public ProfileNotFoundException() {
		super("Profile not found");
	}
	
	public ProfileNotFoundException(String message) {
		super(message);
	}
}
