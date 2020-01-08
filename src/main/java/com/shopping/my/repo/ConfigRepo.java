package com.shopping.my.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shopping.my.entity.ConfigEntity;

@Repository
@org.springframework.transaction.annotation.Transactional
public interface ConfigRepo extends JpaRepository<ConfigEntity, Long> {

	@Query("SELECT a FROM ConfigEntity a where a.configKey=:key ")
	public ConfigEntity getConfigValueByKey(@Param("key") String key);

	@Query("update ConfigEntity a set a.configValue=:configValue where a.configKey=:key ")
	@Modifying
	public int updateConfig(@Param("key") String key, @Param("configValue") String configValue);

}
