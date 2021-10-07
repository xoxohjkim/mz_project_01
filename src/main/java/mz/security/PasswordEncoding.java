package mz.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.log4j.Log4j;

@Log4j
public class PasswordEncoding implements PasswordEncoder {
	
	private PasswordEncoder passwordEncoder;
	
	//rawPassword - ?ïî?ò∏?ôî?êòÏß??ïä?? Í∞?
	//encodedPassword - ?ïî?ò∏?ôî?êú Í∞? 
	public String encode(CharSequence rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}

	
}
