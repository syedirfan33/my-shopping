package com.shopping.my.model.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ConfigResponse extends BaseResponse {
	private List<ConfigResponseData> data;
}
