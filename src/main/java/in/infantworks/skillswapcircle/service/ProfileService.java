package in.infantworks.skillswapcircle.service;

import java.util.List;
import in.infantworks.skillswapcircle.exception.InvalidProfileException;
import in.infantworks.skillswapcircle.exception.ProfileAlreadyExitsException;
import in.infantworks.skillswapcircle.exception.ProfileNotFoundException;
import in.infantworks.skillswapcircle.exception.ProfileServiceFailedException;
import in.infantworks.skillswapcircle.model.Profile;
import in.infantworks.skillswapcircle.model.ProfileRequest;


public interface ProfileService {

	
	public Profile createProfile(Profile profile) throws ProfileServiceFailedException, ProfileAlreadyExitsException, InvalidProfileException;
	
	public Profile updateProfile(Profile profile) throws ProfileNotFoundException, ProfileServiceFailedException, InvalidProfileException;
	
	
	public boolean deleteProfileById(String id) throws ProfileNotFoundException, ProfileServiceFailedException;
	
	public List<Profile> getAllProfilesByLocation(ProfileRequest data) throws ProfileServiceFailedException;
	
	public List<Profile> getAllProfilesByDesignationAndLocation(ProfileRequest data) throws ProfileServiceFailedException;
	
	public Profile getProfileById(String id) throws ProfileNotFoundException, ProfileServiceFailedException;
}
