package mz.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.log4j.Log4j;

@Log4j
public class PasswordEncoding implements PasswordEncoder {
	
	private PasswordEncoder passwordEncoder;
	
	//rawPassword - ?��?��?��?���??��?? �?
	//encodedPassword - ?��?��?��?�� �? 
	public String encode(CharSequence rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}

	
}
