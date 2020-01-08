package com.shopping.my.model.response;

import lombok.Data;

@Data
public class ConfigResponseData {
	private String key;
	private String value;
	private String createdBy;
	private String createdDate;
	private String updatedBy;
	private String updatedDate;
}
