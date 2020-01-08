/**
 * 
 */
package com.shopping.my.model.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author Syed Irfan
 *
 */
@Data
public class ProductRequest {
	
	
	@NotBlank
	private String prdUrl;
	
	@NotBlank
	private String prdTitle;
	
	@NotNull
	private List<String> images;
	
	@NotBlank
	private String ctgrCode;
	
	@NotNull
	private Long price;
	
	@NotNull
	private Long msrp;
	
	@NotNull
	private Boolean isActive;
	
	@NotBlank
	private String merchantName;
	
	@NotBlank
	private String description;

	
}
