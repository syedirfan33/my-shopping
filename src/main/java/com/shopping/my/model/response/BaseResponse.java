package com.shopping.my.model.response;

import com.shopping.my.constant.ApplicationConstant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
	
	private String responseCode=ApplicationConstant.SUCCESS_RESPONSE_CODE;
	
	private String responseMessage=ApplicationConstant.SUCCESS_RESPONSE_MESSAGE;

}
