/**
 * 
 */
package com.shopping.my.service.common;

import com.shopping.my.enumeration.TokenStatus;
import com.shopping.my.model.common.UserContext;

/**
 * @author Syed Irfan
 *
 */
public interface TokenService {
	public String createToken(UserContext userContext);
	
	public void inactiveToken(String token);
	
	public boolean validateTokenByStatus(String token, TokenStatus status) ;

}
