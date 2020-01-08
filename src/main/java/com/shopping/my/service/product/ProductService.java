package com.shopping.my.service.product;

import com.shopping.my.model.request.ProductRequest;
import com.shopping.my.model.response.ProductResponse;


public interface ProductService {

	public void insertProduct(ProductRequest request);
	
	public void updateProduct(ProductRequest request, Long prdId);
	
	public void deleteProduct(Long prdId);

	public ProductResponse findProduct(String prdName);
}
