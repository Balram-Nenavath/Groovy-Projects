package groovy.example.application.utility

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.function.Function;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
class JwtUtility implements Serializable{
	
	private static final long serialVersionUID = 234234523523L
	
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60
	
	@Value('${jwt.secret}')
	private String secretKey
	
	
	//retrieve username from jwt token
	String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims.SUBJECT)
	}

	//retrieve expiration date from jwt token
	Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims.EXPIRATION)
	}
//Function<Claims, T>
	public <T> T getClaimFromToken(String token, String claimsResolver)
	{
		final Claims claims = getAllClaimsFromToken(token)
		//return claimsResolver.apply(claims)
	}
	


	//for retrieving any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody()
	}


	//check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token)
		return expiration.before(new Date())
	}


	//generate token for user
	String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>()
		return doGenerateToken(claims, userDetails.getUsername())
	}


	//while creating the token -
	//1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
	//2. Sign the JWT using the HS512 algorithm and secret key.
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secretKey).compact()
	}


	//validate token
	Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token)
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token))
	}
}
