/**
 * 
 */
package com.shopping.my.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.my.entity.MerchantDetailEntity;

/**
 * @author Syed Irfan
 *
 */
@Repository
public interface MerchantDetailRepo extends JpaRepository<MerchantDetailEntity, Long> {
	
    Optional<MerchantDetailEntity> findByName(String name);
    
}

