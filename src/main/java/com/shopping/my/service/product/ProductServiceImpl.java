package com.shopping.my.service.product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.my.constant.ApplicationConstant;
import com.shopping.my.entity.CategoryEntity;
import com.shopping.my.entity.MerchantDetailEntity;
import com.shopping.my.entity.ProductEntity;
import com.shopping.my.exception.BadRequestException;
import com.shopping.my.model.request.ProductRequest;
import com.shopping.my.model.response.ProductResponse;
import com.shopping.my.model.response.ProductResponseData;
import com.shopping.my.repo.CategoryRepo;
import com.shopping.my.repo.MerchantDetailRepo;
import com.shopping.my.repo.ProductRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {

	@Autowired
	CategoryRepo ctgrRepo;

	@Autowired
	MerchantDetailRepo merchantRepo;
	
	@Autowired
	ProductRepo prdRepo;

	@Override
	public void insertProduct(ProductRequest request) {
		validateProductAndSave(request, new ProductEntity());
	}

	private void validateProductAndSave(ProductRequest request, ProductEntity prdEntity) {
		List<String> images = request.getImages();

		if (images.size() > 4) {
			throw new BadRequestException(ApplicationConstant.INVALID_REQ_IMAGES);
		}

		CategoryEntity cEntity = ctgrRepo.findByCtgrCode(request.getCtgrCode())
				.orElseThrow(() -> new BadRequestException(ApplicationConstant.INVALID_CTGR_MESSAGE));

		String merchantName = request.getMerchantName();
		MerchantDetailEntity mEntity = merchantRepo.findByName(merchantName).orElse(null);

		if (mEntity == null) {
			mEntity = new MerchantDetailEntity();
			mEntity.setName(merchantName);
			mEntity.setEmail("testing@testing.com"); //For testing. Merchant to be registered first with a seperate endpoint, inorder to insert a product
			merchantRepo.save(mEntity);
		}

		prdEntity.setMerchant(mEntity);
		prdEntity.setCtgr(cEntity);
		prdEntity.setTitle(request.getPrdTitle());
		prdEntity.setDescription(request.getDescription());
		prdEntity.setPrice(request.getPrice());
		prdEntity.setIsActive(request.getIsActive());
		prdEntity.setMsrp(request.getMsrp());
		prdEntity.setImages(images);
		prdRepo.save(prdEntity);
	}

	@Override
	public void updateProduct(ProductRequest request,  Long prdId) {
		ProductEntity prdEntity = validateProduct(prdId);
		validateProductAndSave(request, prdEntity);
	}

	@Override
	public void deleteProduct(Long prdId) {
		ProductEntity prdEntity = validateProduct(prdId);
		prdEntity.setIsActive(false);
		prdRepo.save(prdEntity);
	}

	private ProductEntity validateProduct(Long prdId) {
		ProductEntity prdEntity = prdRepo.findById(prdId).orElseThrow(()->new BadRequestException(ApplicationConstant.INVALID_PRD_ID));
		return prdEntity;
	}

	@Override
	public ProductResponse findProduct(String prdName) {
		ProductResponse response = new ProductResponse();
		List<ProductEntity> entity = prdRepo.findByTitleIgnoreCaseContaining(prdName).orElse(null);
		
		if(entity!=null && entity.size()>0) {
			List<ProductResponseData> responseData = entity.stream().map(prd -> {
				ProductResponseData prdData = new ProductResponseData();
				prdData.setCtgrCode(prd.getCtgr().getCtgrCode());
				prdData.setPrdTitle(prd.getTitle());
				prdData.setDescription(prd.getDescription());
				prdData.setPrice(prd.getPrice());
				prdData.setMerchantName(prd.getMerchant().getName());
				prdData.setIsActive(prd.getIsActive());
				prdData.setMsrp(prd.getMsrp());
				prdData.setImages(prd.getImages());
				prdData.setId(prd.getId());
				return prdData;
			}).collect(Collectors.toList());
			response.setData(responseData);
		}else {
			response.setResponseCode(ApplicationConstant.NO_PRD_FOUND_CODE);
			response.setResponseMessage(ApplicationConstant.NO_PRD_FOUND_MESSAGE);
		}
		return response;
	}
}