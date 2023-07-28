package in.infantworks.skillswapcircle.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.stereotype.Service;

import in.infantworks.skillswapcircle.exception.InvalidProfileException;
import in.infantworks.skillswapcircle.exception.ProfileAlreadyExitsException;
import in.infantworks.skillswapcircle.exception.ProfileNotFoundException;
import in.infantworks.skillswapcircle.exception.ProfileServiceFailedException;
import in.infantworks.skillswapcircle.model.Profile;
import in.infantworks.skillswapcircle.model.ProfileRequest;
import in.infantworks.skillswapcircle.repository.ProfileRepository;

@Service
public class ProfileServiceImplementation implements ProfileService {

	@Autowired
	ProfileRepository repository;
	
	@Override
	public Profile createProfile(Profile profile) throws ProfileServiceFailedException, ProfileAlreadyExitsException, InvalidProfileException {
		try {
			if(profile.getId() != null) {
				Optional<Profile> existProfile = repository.findById(profile.getId());
				if(existProfile.isPresent()) {
					throw new ProfileAlreadyExitsException();
				}
			}
			profile.setCreateTs(LocalDateTime.now());
			Profile result = repository.save(profile);
			return result;
		}catch(Exception e) {
			throw new ProfileServiceFailedException(e.getMessage());
		}
	}
	
	@Override
	public Profile updateProfile(Profile profile) throws ProfileNotFoundException, ProfileServiceFailedException, InvalidProfileException{
		try {
			
			if(profile.getId() != null) {
				Optional<Profile> existProfile = repository.findById(profile.getId());
				if(existProfile.isPresent()) {
					profile.setUpdateTs(LocalDateTime.now());
					Profile result = repository.save(profile);
					return result;
				}else {
					throw new ProfileNotFoundException();
				}
			}else {
				throw new InvalidProfileException();
			}
		}catch(Exception e) {
			throw new ProfileServiceFailedException(e.getMessage());
		}
	}
	
	@Override
	public boolean deleteProfileById(String id) throws ProfileNotFoundException, ProfileServiceFailedException{
		try {
			Optional<Profile> existProfile = repository.findById(id);
			if(existProfile.isPresent()) {
				repository.deleteById(id);
				return true;
			}else {
				throw new ProfileNotFoundException();
			}
		}catch(Exception e) {
			throw new ProfileServiceFailedException();
		}
	}
	
	@Override
	public List<Profile> getAllProfilesByLocation(ProfileRequest data) throws ProfileServiceFailedException{
		try {
			Distance radius = new Distance(data.getDistanceInKM(), Metrics.KILOMETERS);
	        return repository.findByLocationNear(data.getLocation(), radius);
		}catch(Exception e) {
			throw new ProfileServiceFailedException(e.getMessage());
		}
	}
	
	@Override
	public List<Profile> getAllProfilesByDesignationAndLocation(ProfileRequest data) throws ProfileServiceFailedException {
		try {
			Distance radius = new Distance(data.getDistanceInKM(), Metrics.KILOMETERS);
	        return repository.findByDesignationAndLocationNear(data.getDesignation(), data.getLocation(), radius);
		}catch(Exception e) {
			throw new ProfileServiceFailedException(e.getMessage());
		}
	}
	
	@Override
	public Profile getProfileById(String id) throws ProfileNotFoundException, ProfileServiceFailedException{
		try {
			Optional<Profile> profile = repository.findById(id);
			if(profile.isPresent()) {
				return profile.get();
			}else {
				throw new ProfileNotFoundException();
			}
		}catch(Exception e) {
			throw new ProfileServiceFailedException();
		}
	}
}

