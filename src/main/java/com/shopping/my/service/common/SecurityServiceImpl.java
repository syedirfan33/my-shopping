/**
 * 
 */
package com.shopping.my.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @author Syed Irfan
 *
 */
@Service
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Override
	public Authentication getAuth(String userNameOrEmail, String password) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userNameOrEmail, password));
		return authentication;
	}

}
