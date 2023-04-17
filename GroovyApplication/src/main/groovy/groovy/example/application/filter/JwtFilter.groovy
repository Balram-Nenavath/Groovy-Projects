package groovy.example.application.filter

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter

import groovy.example.application.service.TaskService
import groovy.example.application.utility.JwtUtility
import io.jsonwebtoken.ExpiredJwtException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class JwtFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtUtility jwtUtility;

	@Autowired
	private TaskService taskService;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
			throws ServletException, IOException {
		def authorization = httpServletRequest.getHeader("Authorization")
        def token = null
        def userName = null
		
		
		if(null != authorization && authorization.startsWith("Bearer ")) {
			token = authorization.substring(7);
			try{
			userName = jwtUtility.getUsernameFromToken(token)
		} catch (IllegalArgumentException e) {
			System.out.println("Unable to get JWT Token")
			}catch (ExpiredJwtException e) {
			System.out.println("JWT Token has expired")
		}
	}
	
	if(null != userName && SecurityContextHolder.getContext().getAuthentication() == null) {
		UserDetails userDetails = taskService.loadUserByUsername(userName)

		if(jwtUtility.validateToken(token,userDetails)) {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
			 new UsernamePasswordAuthenticationToken(userDetails,
					null, userDetails.getAuthorities())

			usernamePasswordAuthenticationToken.setDetails(
					new WebAuthenticationDetailsSource().buildDetails(httpServletRequest)
			)

			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken)
		}

	}
	filterChain.doFilter(httpServletRequest, httpServletResponse)
}
		
}
