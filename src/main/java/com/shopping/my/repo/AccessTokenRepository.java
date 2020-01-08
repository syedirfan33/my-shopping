/**
 * 
 */
package com.shopping.my.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shopping.my.entity.AccessTokenEntity;
import com.shopping.my.entity.UserEntity;
import com.shopping.my.enumeration.TokenStatus;

/**
 * @author Syed Irfan
 *
 */
@Repository
@org.springframework.transaction.annotation.Transactional
public interface AccessTokenRepository extends JpaRepository<AccessTokenEntity, Long> {
	@Modifying
	@Query(" Update AccessTokenEntity c set c.isActive=:isActive,tokenStatus=:tokenStatus where c.accessToken=:accessToken")
	int changeTokenStatus(@Param("accessToken") String accessToken, @Param("isActive") Boolean isActive, @Param("tokenStatus") TokenStatus tokenStatus );

	@Query(" Select c from AccessTokenEntity c where c.isActive=true and c.accessToken=:accessToken")
	AccessTokenEntity checkToken(@Param("accessToken") String accessToken);
	
	@Query(" Select c from AccessTokenEntity c where c.isActive=true and tokenStatus=:tokenStatus and c.accessToken=:accessToken")
	Optional<AccessTokenEntity> checkNewUserToken(@Param("accessToken") String accessToken,@Param("tokenStatus") String tokenStatus);
	
	@Modifying
	@Query(" update AccessTokenEntity c set c.isActive=false, c.tokenStatus='INACTIVE' where c.userid=:userid and c.tokenStatus='ACTIVE'")
	void inActiveOldTokens(@Param("userid") UserEntity userid);
	
	@Modifying
	@Query(" update AccessTokenEntity c set c.isActive=false where c.userid=:userid and c.tokenStatus='ISSUED'")
	void inactiveNewUserTokens(@Param("userid") UserEntity userid);
	
}
