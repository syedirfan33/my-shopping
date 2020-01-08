package com.shopping.my.util;

import java.util.concurrent.ConcurrentHashMap;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheUtils {

	public transient static ConcurrentHashMap<String, String> CNFIG_MAP = new ConcurrentHashMap<String, String>();
	
	public transient static String SERVICE_UR;;


	//Private constructor, as it's an util class
	private CacheUtils() {
		
	}

	public static String getConfig(String key) {
		String value = CNFIG_MAP.get(key);
		log.info("Reading value for key :"+key+" Value :"+value);
		return value;
	}

	public static String getConfig(String key, String defaultValue) {
		String value = CNFIG_MAP.get(key);
		log.info("Reading value for key :"+key+" Value :"+value);
		if (value == null) {
			value = defaultValue;
		}
		return value;
	}


}
