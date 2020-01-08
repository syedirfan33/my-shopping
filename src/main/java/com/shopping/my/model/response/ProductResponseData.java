package com.shopping.my.model.response;

import java.util.List;

import lombok.Data;

/**
 * @author Syed Irfan
 *
 */
@Data
public class ProductResponseData {
	
	private Long id;
	
	private String prdUrl;
	
	private String prdTitle;
	
	private List<String> images;
	
	private String ctgrCode;
	
	private Long price;
	
	private Long msrp;
	
	private Boolean isActive;
	
	private String merchantName;
	
	private String description;

	
}
