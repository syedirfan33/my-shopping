/**
 * 
 */
package com.shopping.my.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.my.entity.RoleEntity;
import com.shopping.my.enumeration.RoleName;

/**
 * @author Syed Irfan
 *
 */
@Repository
public interface RoleRepo extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(RoleName roleName);
}

