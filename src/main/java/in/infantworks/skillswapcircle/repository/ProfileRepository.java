package in.infantworks.skillswapcircle.repository;

import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import in.infantworks.skillswapcircle.model.Profile;

@Repository
public interface ProfileRepository extends MongoRepository<Profile, String>{
	
	List<Profile> findByLocationNear(GeoJsonPoint location, Distance distance);
	
	
	List<Profile> findByDesignationAndLocationNear(String designation, GeoJsonPoint Location, Distance distance);
	
}
