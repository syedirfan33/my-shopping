/**
 * 
 */
package com.shopping.my.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.my.constant.ApplicationConstant;
import com.shopping.my.enumeration.UserStatus;
import com.shopping.my.model.common.UserContext;
import com.shopping.my.model.request.LoginRequest;
import com.shopping.my.model.response.BaseResponse;
import com.shopping.my.model.response.LoginResponse;
import com.shopping.my.service.common.SecurityService;
import com.shopping.my.service.common.TokenService;
import com.shopping.my.util.JwtUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Syed Irfan
 *
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

	@Autowired
	SecurityService securityService;
	

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	TokenService tokenService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = securityService.getAuth(loginRequest.getUsernameOrEmail(),
				loginRequest.getPassword());
		UserContext userContext = (UserContext) authentication.getPrincipal();
		if (UserStatus.CREATED.equals(userContext.getStatus())) {
			return new ResponseEntity<BaseResponse>(
					new BaseResponse(ApplicationConstant.CREATE_PW_RQ_CODE, ApplicationConstant.CREATE_PW_RQ_MESSAGE),
					HttpStatus.UNAUTHORIZED);
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenService.createToken(userContext);
		return ResponseEntity.ok(new LoginResponse(jwt));
	}


	@GetMapping("/signout")
	public ResponseEntity<BaseResponse> logout(@RequestHeader("Authorization") String authHeader) {
		tokenService.inactiveToken(JwtUtils.getPlainToken(authHeader));
		return ResponseEntity.ok(new BaseResponse());
	}

}
