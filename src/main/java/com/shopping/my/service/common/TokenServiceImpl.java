/**
 * 
 */
package com.shopping.my.service.common;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.shopping.my.entity.AccessTokenEntity;
import com.shopping.my.entity.UserEntity;
import com.shopping.my.enumeration.TokenStatus;
import com.shopping.my.enumeration.UserStatus;
import com.shopping.my.model.common.UserContext;
import com.shopping.my.repo.AccessTokenRepository;
import com.shopping.my.repo.UserRepo;
import com.shopping.my.util.AppUtils;
import com.shopping.my.util.JwtUtils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Syed Irfan
 *
 */
@Service
@Transactional
@Slf4j
public class TokenServiceImpl implements TokenService {

	@Autowired
	private AccessTokenRepository tokenRepo;

	@Autowired
	private UserRepo userRepo;

	@Value("${app.jwtExpirationInMinutes}")
	private int jwtExpirationInMinutes;

	@Override
	public String createToken(UserContext userContext) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtExpirationInMinutes * 60 * 1000);
		String token = JwtUtils.generateToken(userContext, expiryDate);

		UserEntity user = userRepo.findById(userContext.getId()).orElse(null);
		tokenRepo.inActiveOldTokens(user);
		AccessTokenEntity entity = new AccessTokenEntity();
		entity.setAccessToken(token);
		entity.setUserid(user);
		entity.setTokenStatus(TokenStatus.ACTIVE);
		entity.setUsername(userContext.getUsername());
		entity.setAccessTokenExpiry(expiryDate);
		tokenRepo.save(entity);
		return token;
	}
	

	@Override
	public void inactiveToken(String token) {
		tokenRepo.changeTokenStatus(token, false, TokenStatus.INACTIVE);

	}

	@Override
	public boolean validateTokenByStatus(String token, TokenStatus status) {

		boolean isValid = validateOrThrow(token);
		if (isValid) {
			AccessTokenEntity entity = tokenRepo.checkToken(JwtUtils.getPlainToken(token));
			if (entity != null && status.equals(entity.getTokenStatus())) {
				return true;
			}
		}

		return false;
	}


	private boolean validateOrThrow(String token) {
		try {
			JwtUtils.validateToken(token);
			return true;
		} catch (SignatureException ex) {
			log.error("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			log.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			log.error("Expired JWT tokennnn");
			tokenRepo.changeTokenStatus(JwtUtils.getPlainToken(token), false, TokenStatus.EXPIRED);
		} catch (UnsupportedJwtException ex) {
			log.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			log.error("JWT claims string is empty.");
		}
		return false;
	}
}
