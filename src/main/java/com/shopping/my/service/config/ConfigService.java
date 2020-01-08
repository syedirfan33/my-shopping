package com.shopping.my.service.config;

import com.shopping.my.model.request.ConfigRequest;
import com.shopping.my.model.response.ConfigResponse;
import com.shopping.my.model.response.GenericResponse;


public interface ConfigService {

	public ConfigResponse getConfigValues();

	public GenericResponse create(ConfigRequest request);

	public void initConfig();

}
