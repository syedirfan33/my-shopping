/**
 * 
 */
package com.shopping.my.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.my.entity.ProductEntity;

/**
 * @author Syed Irfan
 *
 */
@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, Long> {
	
    Optional<List<ProductEntity>> findByTitleIgnoreCaseContaining(String name);
}

