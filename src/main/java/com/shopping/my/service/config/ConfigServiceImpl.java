package com.shopping.my.service.config;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.my.constant.ApplicationConstant;
import com.shopping.my.entity.ConfigEntity;
import com.shopping.my.model.request.ConfigRequest;
import com.shopping.my.model.response.ConfigResponse;
import com.shopping.my.model.response.ConfigResponseData;
import com.shopping.my.model.response.GenericResponse;
import com.shopping.my.repo.ConfigRepo;
import com.shopping.my.util.CacheUtils;
@Service
@org.springframework.transaction.annotation.Transactional
public class ConfigServiceImpl implements ConfigService{

	@Autowired
	private ConfigRepo confRepo;
	
	public ConfigResponse getConfigValues() {
		
		ConfigResponse response = new ConfigResponse();
	
		List<ConfigEntity> configEntities = confRepo.findAll();
		if(configEntities!=null && configEntities.size()>0) {
		List<ConfigResponseData> configList = configEntities.stream().map(entity->{
			ConfigResponseData responseData = new ConfigResponseData();
			responseData.setValue(entity.getConfigValue());
			responseData.setKey(entity.getConfigKey());
			responseData.setCreatedDate(entity.getCreatedDate() + "");
			responseData.setCreatedBy(entity.getCreatedBy());
			responseData.setUpdatedBy(entity.getModifiedBy());
			responseData.setUpdatedDate(entity.getModifiedDate()+"");
			return responseData;		
			}).collect(Collectors.toList());
			response.setData(configList);
		}
		return response;
	}

	
	
	
	public GenericResponse create(ConfigRequest request) {
		GenericResponse response = new GenericResponse();
		ConfigEntity entity = confRepo.getConfigValueByKey(request.getKey());
		if (entity != null) {
			response.setResponseCode("0001");
			response.setResponseMessage("key already exist");
			
		} else {
			entity=new ConfigEntity();
			entity.setConfigValue(request.getValue());
			entity.setModifiedBy("API");
			entity.setModifiedDate(new Date());
			entity.setConfigKey(request.getKey());
			entity.setDescritpyion(request.getDescription());
			confRepo.save(entity);
			
		}
		return response;

	}
	
	public void initConfig() {
		try {
			List<ConfigEntity> configEntities = confRepo.findAll();

			for (ConfigEntity configEntity : configEntities) {

				CacheUtils.CNFIG_MAP.put(configEntity.getConfigKey(), configEntity.getConfigValue());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	

}
