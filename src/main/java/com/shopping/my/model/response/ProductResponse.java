/**
 * 
 */
package com.shopping.my.model.response;

import java.util.List;

import lombok.Data;

/**
 * @author Syed Irfan
 *
 */
@Data
public class ProductResponse extends BaseResponse{
	List<ProductResponseData> data;

}
