/**
 * 
 */
package com.shopping.my.service.common;

import org.springframework.security.core.Authentication;

/**
 * @author Syed Irfan
 *
 */
public interface SecurityService {
	public Authentication getAuth(String userNameOrEmail, String password);
}
