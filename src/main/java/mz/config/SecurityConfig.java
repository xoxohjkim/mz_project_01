package mz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j;
import mz.security.CustomLoginSuccessHandler;

@Configuration
@EnableWebSecurity
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests()
		 	.antMatchers("/sample/all").permitAll()
		 	.antMatchers("/sample/admin").access("hasRole('ROLE_ADMIN')")
		 	.antMatchers("/sample/member").access("hasRole('ROLE_MEMBER')");
		 	
		 	//access denied인경우 로그인페이지로
		 	http.formLogin().loginPage("/login").loginProcessingUrl("/login");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.info("spring security 적용");
		auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("member").password("{noop}member").roles("MEMBER");
		
	}
	
	
	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
