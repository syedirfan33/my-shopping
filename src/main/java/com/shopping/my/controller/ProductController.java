
package com.shopping.my.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.my.model.request.ProductRequest;
import com.shopping.my.model.response.GenericResponse;
import com.shopping.my.model.response.ProductResponse;
import com.shopping.my.service.product.ProductService;

@RestController
@RequestMapping("/product")
@Secured({"ROLE_ADMIN", "ROLE_USER"})
public class ProductController {

	@Autowired
	ProductService prdService;

	@PostMapping("/insert")
	public ResponseEntity<?> insertProduct(@RequestHeader("Authorization") String authHeader, @RequestBody ProductRequest request) {
	    prdService.insertProduct(request);
		return ResponseEntity.ok(new GenericResponse());
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateProduct(@RequestHeader("Authorization") String authHeader, @RequestBody ProductRequest request, @RequestParam("productId") Long productId) {
	    prdService.updateProduct(request, productId);
		return ResponseEntity.ok(new GenericResponse());
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteProduct(@RequestHeader("Authorization") String authHeader, @RequestParam("productId") Long productId) {
	    prdService.deleteProduct(productId);
		return ResponseEntity.ok(new GenericResponse());
	}
	
	@GetMapping
	public ResponseEntity<?> findProduct(@RequestHeader("Authorization") String authHeader, @RequestParam("productName") String productName) {
	   ProductResponse resposne = prdService.findProduct(productName);
		return ResponseEntity.ok(resposne);
	}





}
