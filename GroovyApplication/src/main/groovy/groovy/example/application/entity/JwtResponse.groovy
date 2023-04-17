package groovy.example.application.entity
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
@JsonIgnoreProperties(["metaClass"])
class JwtResponse {
	
	String jwtToken

	public JwtResponse(String jwtToken) {
		super();
		this.jwtToken = jwtToken;
	}

	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
}
