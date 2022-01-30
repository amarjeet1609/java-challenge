package jp.co.axa.apidemo.services;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	private static final Logger LOG = LoggerFactory.getLogger(JwtUserDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(LOG.isDebugEnabled()) {
			LOG.debug("Inside JwtUserDetailsService - loadUserByUsername -- username {}", username);
		}
		if ("testuser".equals(username)) {
			return new User("testuser", "$2a$10$a7bVaWR2e4Zz9C64AHzPMehfsB0SGHXDasEAqBcYIiS/LrPxGTAH2",
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}