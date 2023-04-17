package groovy.example.application.config

import static org.springframework.http.HttpMethod.GET

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.web.bind.annotation.PostMapping

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
class SecurityConfiguration extends WebSecurityConfigurerAdapter{


	@Bean
	AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean()
	}

	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder
				.inMemoryAuthentication()
				.withUser("user")
				.password("{noop}balram@1998")
				.roles("USER")
				.and()
				.withUser("admin")
				.password("{noop}balram@1998")
				.roles("ADMIN")
				
	}

	
	//httpBasic() works
/*	When httpBasic() is called, we are telling Spring to authenticate the request using the values passed by the Authorization request header. */
	//addedd authorizeHttpRequests
	
	//http.authorizeRequests() 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeHttpRequests()
				.antMatchers("/v3/api-docs/**","/swagger-ui.html","swagger-ui/**","/swagger-resources/**").permitAll()
				.antMatchers("/").hasRole("ADMIN")
				.antMatchers("/tasks/**").hasRole("USER")
				.anyRequest().authenticated()
			http.cors().and().csrf().disable()
				}
}	