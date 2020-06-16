package com.example.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.model.Product;
import com.example.product.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@PostMapping("/add")
	public Product add(@RequestBody Product product) {
		return service.saveProduct(product);
	}
	
	@PostMapping("/addAll")
	public List<Product> addAll(@RequestBody List<Product> products) {
		return service.saveAllProduct(products);
	}
	
	@GetMapping("/{id}")
	public Product findById(@PathVariable int id) {
		return service.getById(id);
	}
	
	@GetMapping("/findAll")
	public List<Product> findAllProducts(){
		return service.getAllProducts();
	}
	
	@DeleteMapping("/{id}")
	public String deleteProduct(@PathVariable int id) {
		return service.deleteProduct(id);
	}
	
	@PutMapping("/update")
	public Product update(@RequestBody  Product product  ) {
		return service.updateProduct(product);
	}
	
	

}
