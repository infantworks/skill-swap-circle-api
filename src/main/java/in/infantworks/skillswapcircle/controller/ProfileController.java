package in.infantworks.skillswapcircle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.infantworks.skillswapcircle.exception.InvalidProfileException;
import in.infantworks.skillswapcircle.exception.ProfileAlreadyExitsException;
import in.infantworks.skillswapcircle.exception.ProfileNotFoundException;
import in.infantworks.skillswapcircle.exception.ProfileServiceFailedException;
import in.infantworks.skillswapcircle.model.Profile;
import in.infantworks.skillswapcircle.model.ProfileRequest;
import in.infantworks.skillswapcircle.service.ProfileService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
	
	@Autowired
	ProfileService service;
	
	public String appRunStatus() {
		return "SKILL SWAP CIRCLE Application is running";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/add-profile")
	public ResponseEntity<?> createProfile(@RequestBody Profile profile) {
		try {
			Profile result = service.createProfile(profile);
			return new ResponseEntity<Profile>(result, HttpStatus.OK);
		}catch(ProfileAlreadyExitsException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}catch(InvalidProfileException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}catch(ProfileServiceFailedException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/update-profile")
	public ResponseEntity<?> updateProfile(@RequestBody Profile profile){
		try {
			Profile result = service.updateProfile(profile);
			return new ResponseEntity<Profile>(result, HttpStatus.OK);
		}catch(ProfileNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}catch(InvalidProfileException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}catch(ProfileServiceFailedException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete-profile-by-id/{id}")
	public ResponseEntity<?> deleteProfile(@PathVariable String id){
		try {
			boolean result = service.deleteProfileById(id);
			if(result) {
				return new ResponseEntity<String>("Ok", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("", HttpStatus.SERVICE_UNAVAILABLE);
			}
		}catch(ProfileNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}catch(ProfileServiceFailedException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/all-profiles-by-location")
	public ResponseEntity<?> getAllProfilesByLocation(@RequestBody ProfileRequest request){
		try {
			List<Profile> result = service.getAllProfilesByLocation(request);
			return new ResponseEntity<List<Profile>>(result, HttpStatus.OK);
		}catch(ProfileServiceFailedException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/all-profiles-by-designation-and-location")
	public ResponseEntity<?> getAllProfilesByDesignationAndLocation(@RequestBody ProfileRequest request){
		try {
			List<Profile> result = service.getAllProfilesByDesignationAndLocation(request);
			return new ResponseEntity<List<Profile>>(result, HttpStatus.OK);
		}catch(ProfileServiceFailedException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping("/profile-by-id/{id}")
	public ResponseEntity<?> getProfileById(@PathVariable String id){
		try {
			Profile result = service.getProfileById(id);
			return new ResponseEntity<Profile>(result, HttpStatus.OK);
		}catch(ProfileNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}catch(ProfileServiceFailedException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
