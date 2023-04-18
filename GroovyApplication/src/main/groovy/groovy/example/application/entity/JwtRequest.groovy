package groovy.example.application.entity
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
@JsonIgnoreProperties(["metaClass"])
class JwtRequest {
	
	String username
	String password
	public JwtRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public JwtRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
}
