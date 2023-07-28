package in.infantworks.skillswapcircle.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "profiles")
public class Profile {
	
	@Id
	private String id;
	
	private String name;
	
	private String profilePictureUrl;
	
	private String designation;
	
	private String positionDescription;
	
	private String workplace;
	
	private String currentCity;
	
	private String gender;
	
	@GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
	private GeoJsonPoint location;
	
	private LocalDateTime createTs;
	
	private LocalDateTime updateTs;
}
