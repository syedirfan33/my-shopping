/**
 * 
 */
package com.shopping.my.util;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shopping.my.constant.ApplicationConstant;
import com.shopping.my.model.common.UserContext;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Syed Irfan
 *
 */
@Slf4j
@Service
public class JwtUtils {


	//Private constructor, as it's an util class
	private JwtUtils() {
		
	}
	
	public static String generateToken(UserContext userContext, Date expiryDate) {
		return Jwts.builder().setSubject(Long.toString(userContext.getId())).setIssuedAt(new Date())
				.setIssuer(userContext.getUsername())
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, CacheUtils.getConfig(ApplicationConstant.JWT_SECRET)).compact();
	}

	public static Long getUserIdFromJWT(String token) {
		String plainToken = getPlainToken(token);
		Claims claims = Jwts.parser().setSigningKey(CacheUtils.getConfig(ApplicationConstant.JWT_SECRET)).parseClaimsJws(plainToken).getBody();

		return Long.parseLong(claims.getSubject());
	}

	public static String getUserNameFromJWT(String token) {
		String plainToken = getPlainToken(token);
		Claims claims = Jwts.parser().setSigningKey(CacheUtils.getConfig(ApplicationConstant.JWT_SECRET)).parseClaimsJws(plainToken).getBody();

		return claims.getIssuer();
	}

	public static boolean validateToken(String authToken) {
		String plainToken = getPlainToken(authToken);
		Jwts.parser().setSigningKey(CacheUtils.getConfig(ApplicationConstant.JWT_SECRET)).parseClaimsJws(plainToken);
		return true;
	}

	public static String getPlainToken(String bearerToken) {

		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}
	
}
