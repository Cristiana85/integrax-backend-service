package com.integrax.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.integrax.config.security.jwt.AuthEntryPointJwt;
import com.integrax.security.jwt.AuthTokenFilter;
import com.integrax.security.services.UserDetailsServiceImpl;

@Configuration
@EnableMethodSecurity
// (securedEnabled = true,
// jsr250Enabled = true,
// prePostEnabled = true) // by default
public class JWTWebSecurityConfig { // extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	//  @Override
	//  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	//    authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	//  }

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	//  @Bean
	//  @Override
	//  public AuthenticationManager authenticationManagerBean() throws Exception {
	//    return super.authenticationManagerBean();
	//  }

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//  @Override
	//  protected void configure(HttpSecurity http) throws Exception {
	//    http.cors().and().csrf().disable()
	//      .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
	//      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	//      .authorizeRequests().antMatchers("/api/auth/**").permitAll()
	//      .antMatchers("/api/test/**").permitAll()
	//      .anyRequest().authenticated();
	//
	//    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	//  }


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.ignoringRequestMatchers("/authenticate/verify/**").disable())
		.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests(auth -> 
		auth.requestMatchers("/**").permitAll()
		.requestMatchers("/authenticate/**").permitAll()
		.anyRequest().authenticated() 
				);

		http.authenticationProvider(authenticationProvider());
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
	
	@Bean   
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Arrays.asList("*")); //or * if you want to allow all 
	    configuration.setAllowCredentials(true);
	    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
	    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration); 
	    return source; 
	}

	@Bean
	public WebMvcConfigurer corsConfig() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				 registry.addMapping("/**");
//				registry.addMapping("/**")
//					.allowedOrigins("*")
//					.allowedMethods("GET", "POST", "OPTIONS", "PUT")
//					.allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
//						"Access-Control-Request-Headers")
//					.exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
//					.allowCredentials(true).maxAge(3600);
				//				registry.addMapping("/*")
				//				.allowedOrigins("http://localhost:4200",
				//						"https://integrax-backend-gcl-3hkbcb6pua-uc.a.run.app",
				//						"https://integrax-frontend-service-3hkbcb6pua-uc.a.run.app")
				//				.allowedMethods(HttpMethod.GET.name(),
				//						HttpMethod.POST.name(),
				//						HttpMethod.OPTIONS.name(),
				//						HttpMethod.DELETE.name())
				//				.allowedHeaders(HttpHeaders.CONTENT_TYPE,
				//						HttpHeaders.AUTHORIZATION);
			}
		};
	}
}