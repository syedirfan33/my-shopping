/**
 * 
 */
package com.shopping.my.model.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Syed Irfan
 *
 */
@Getter
@Setter
public class LoginResponse extends BaseResponse{
	private String accessToken;
	private String tokenType = "Bearer";
	

    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
