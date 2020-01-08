package com.shopping.my.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shopping.my.entity.CategoryEntity;

@Repository
@org.springframework.transaction.annotation.Transactional
public interface CategoryRepo extends JpaRepository<CategoryEntity, Long> {
	
	@Query("Select c from CategoryEntity c where ctgrName=:ctgrName and ctgrLevel=:ctgrLevel")
	CategoryEntity findByCtgrNameAndLevel(@Param("ctgrName")String ctgrName, @Param("ctgrLevel")int ctgrLevel);
	
	Boolean existsByCtgrCode(String ctgrName);

	Optional<List<CategoryEntity>> findByCtgrLevelOrderByCtgrName(int ctgrLevel);
	
	@Query("Select c from CategoryEntity c where ctgrNo1=:ctgrNo1 and ctgrLevel = 2 order by ctgrName")
	List<CategoryEntity> findMiddleCategories(@Param("ctgrNo1")Long ctgrNo1);
	
	@Query("Select c from CategoryEntity c where ctgrNo1=:ctgrNo1 and ctgrNo2=:ctgrNo2 and ctgrLevel = 3 order by ctgrName")
	List<CategoryEntity> findLowerCategories(@Param("ctgrNo1")Long ctgrNo1, @Param("ctgrNo2")Long ctgrNo2);
	
	Optional<CategoryEntity> findByCtgrCode(String ctgrCode);

}
