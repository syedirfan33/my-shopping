package com.shopping.my.model.request;


import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ConfigRequest {
	@NotBlank(message = "key can not null or empty.")
	private String key;
	@NotBlank(message = "value can not null or empty.")
	private String value;
	private String description;
}
