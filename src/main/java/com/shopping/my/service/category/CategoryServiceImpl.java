package com.shopping.my.service.category;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.my.constant.ApplicationConstant;
import com.shopping.my.entity.CategoryEntity;
import com.shopping.my.repo.CategoryRepo;
import com.shopping.my.util.AppUtils;

import lombok.extern.slf4j.Slf4j;


@Service
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepo ctgrRepo;

	@Autowired
	ObjectMapper objectMapper;

	@Override
	public void loadCategories() throws Exception {
		
		CategoryEntity allEntity = ctgrRepo.findByCtgrNameAndLevel(ApplicationConstant.ALL_CATEGORIES_NAME, 1);
		if(allEntity!=null) {
			log.info("Categories have loaded already, skipping initialization!!");
			return;
		}
		
	    allEntity = new CategoryEntity();
		allEntity.setCtgrLevel(1);
		allEntity.setCtgrName(ApplicationConstant.ALL_CATEGORIES_NAME);
		allEntity.setCtgrName1(ApplicationConstant.ALL_CATEGORIES_NAME);
		allEntity.setCtgrCode(ApplicationConstant.ALL_CATEGORIES_CODE);
		ctgrRepo.save(allEntity);
		
		String jsonPayload = AppUtils.readFile("json/CategoryList.json");
		CategoryEntity[] ctgrArr = objectMapper.readValue(jsonPayload, CategoryEntity[].class);

		if(ctgrArr == null || ctgrArr.length <=0) {
			log.info("Category list is empty");
			return;
		}
		log.info("Loading categories into Category entity");

		Arrays.asList(ctgrArr).stream().forEach(ctgr->{
			String ctgrCode = ctgr.getCtgrCode();
			
			if(ctgrRepo.existsByCtgrCode(ctgrCode)) {
				return;
			}
			CategoryEntity lowerCtgrEntity = new CategoryEntity();
			
			String ctgrName1 = ctgr.getCtgrName1();
			String ctgrName2 = ctgr.getCtgrName2();
			String ctgrName3 = ctgr.getCtgrName3();
			lowerCtgrEntity.setCtgrCode(ctgrCode);
			lowerCtgrEntity.setCtgrName1(ctgrName1);
			lowerCtgrEntity.setCtgrName2(ctgrName2);
			lowerCtgrEntity.setCtgrName3(ctgrName3);
			lowerCtgrEntity.setCtgrName(ctgrName3);
			lowerCtgrEntity.setCtgrLevel(3);
			
			CategoryEntity upperCtgr = ctgrRepo.findByCtgrNameAndLevel(ctgrName1, 1);
			if(upperCtgr == null) {
			    upperCtgr = insertUpperCategory(ctgrName1, ctgrCode);
			}
			lowerCtgrEntity.setCtgrNo1(upperCtgr.getId());

			CategoryEntity middleCtgr = ctgrRepo.findByCtgrNameAndLevel(ctgrName2, 2);
			
			if(middleCtgr == null) {
			    middleCtgr = insertMiddleCategory(ctgrName2,ctgrName1, ctgrCode, upperCtgr.getId());
			}
			lowerCtgrEntity.setCtgrNo2(middleCtgr.getId());

			ctgrRepo.save(lowerCtgrEntity);
		});
	}
	
	private CategoryEntity insertUpperCategory(String ctgrName, String ctgrCode) {
		CategoryEntity entity = new CategoryEntity();
		entity.setCtgrLevel(1);
		entity.setCtgrName(ctgrName);
		entity.setCtgrName1(ctgrName);
		String code = ctgrCode.substring(0,3);
		entity.setCtgrCode(code);
		return ctgrRepo.save(entity);
	}
	
	private CategoryEntity insertMiddleCategory(String ctgrName, String upperCtgrName, String ctgrCode, Long upperCtgrId ) {
		CategoryEntity entity = new CategoryEntity();
		entity.setCtgrLevel(2);
		entity.setCtgrName(ctgrName);
		entity.setCtgrName1(upperCtgrName);
		entity.setCtgrName2(ctgrName);
		entity.setCtgrNo1(upperCtgrId);
		String code = ctgrCode.substring(0,6);
		entity.setCtgrCode(code);
		return ctgrRepo.save(entity);
	}


	
	
}
