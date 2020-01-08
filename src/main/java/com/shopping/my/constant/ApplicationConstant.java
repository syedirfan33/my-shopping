/**
 * 
 */
package com.shopping.my.constant;

/**
 * @author Syed Irfan
 *
 */
public final class ApplicationConstant {
	

//Hide the constructor, Anti Pattern
private ApplicationConstant(){}


	public static String JWT_SECRET = "JWT_SECRET";

	public static String SUCCESS_RESPONSE_CODE = "MS000";
	public static String SUCCESS_RESPONSE_MESSAGE = "Success";
	
	public static String FAIL_RESPONSE_CODE = "MS001";
	public static String FAIL_RESPONSE_MESSAGE = "Fail";
	
	public static String CREATE_PW_RQ_CODE = "MS002";
	public static String CREATE_PW_RQ_MESSAGE = "Password not created yet";

	public static String INVALID_TOKEN_CODE = "MS003";
	public static String INVALID_TOKEN_MESSAGE = "Invalid token";
	
	public static final String ALL_CATEGORIES_NAME = "ALL";
	public static final String ALL_CATEGORIES_CODE = "999";

	public static final String INVALID_REQ_IMAGES = "Not allowed to upload more than 4 images";

	public static final String INVALID_CTGR_MESSAGE = "Invalid Category code";

	public static final String INVALID_PRD_ID = "Invalid Product ID entered";

	public static final String NO_PRD_FOUND_CODE = "MS004";
	public static final String NO_PRD_FOUND_MESSAGE = "No products found";






}
