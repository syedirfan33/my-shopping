/**
 * 
 */
package com.shopping.my.common;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shopping.my.service.category.CategoryService;
import com.shopping.my.service.config.ConfigService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Syed Irfan
 *
 */
@Component
@Slf4j
public class StartUpInit {
	@Autowired
	ConfigService configService;
	
	@Autowired
	CategoryService categoryService;

	@PostConstruct
	public void init() {
		try {
			configService.initConfig();
			categoryService.loadCategories();
		} catch (Exception e) {
			log.info("An error occured while start up initialization!");
			log.info(e.getMessage());
			e.printStackTrace();
		}
	}
}