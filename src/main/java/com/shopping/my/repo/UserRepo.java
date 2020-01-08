/**
 * 
 */
package com.shopping.my.repo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shopping.my.entity.UserEntity;
import com.shopping.my.enumeration.UserStatus;

/**
 * @author Syed Irfan
 *
 */
@Repository
@Transactional
public interface UserRepo extends JpaRepository<UserEntity, Long> {
	
    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByUsernameOrEmail(String username, String email);

    List<UserEntity> findByIdIn(List<Long> userIds);
    
    Optional<UserEntity> findById(Long id);
    

    Optional<UserEntity> findByUsername(String username);
    
    Optional<UserEntity> findByUsernameIgnoreCase(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    
    @Query("Select c from UserEntity c where (CAST(id as string)=:userIdOrName or lower(username) like %:userIdOrName%)")
    UserEntity findByIdOrUsername(@Param("userIdOrName") String userIdOrName);
    
    @Query("Update UserEntity c set status=:status where id=:id")
    @Modifying
    void changeStatus(@Param("id") Long id, @Param("status") UserStatus status);

    
}