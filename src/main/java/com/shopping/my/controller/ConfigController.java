
package com.shopping.my.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.my.model.request.ConfigRequest;
import com.shopping.my.model.response.ConfigResponse;
import com.shopping.my.model.response.GenericResponse;
import com.shopping.my.service.config.ConfigService;

import lombok.extern.slf4j.Slf4j;
@RestController
@RequestMapping("/config")
@Slf4j
public class ConfigController {

	@Autowired
	private ConfigService service;
	
	@GetMapping("All")
	public ResponseEntity<?> getAllConfig() {
		ConfigResponse response =  service.getConfigValues();
		return ResponseEntity.ok(response);

	}
	

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody ConfigRequest request) {
		GenericResponse response = service.create(request);
		return ResponseEntity.ok(response);

	}
	
	@GetMapping("/refresh")
	public ResponseEntity<?> refreshCache() throws Exception {
		service.initConfig();
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
