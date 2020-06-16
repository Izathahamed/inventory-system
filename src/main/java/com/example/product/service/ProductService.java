package com.example.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
//	Create Product
	public Product saveProduct(Product product) {
		return repository.save(product);
	}
	
	public List<Product> saveAllProduct(List<Product> products){
		return repository.saveAll(products);
	}
	
//	Get Product
	public Product getById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<Product> getAllProducts(){
		return repository.findAll();
	}
	
//	Delete Product
	public String deleteProduct(int id) {
		repository.deleteById(id);
		return "Id Succesfully Deleted";
	}
//	 Update Product 
	public Product updateProduct(Product product) {
		Product existing=repository.findById(product.getId()).orElse(null);
		existing.setName(product.getName());
		existing.setQuantity(product.getQuantity());
		existing.setPrice(product.getPrice());
		return repository.save(existing);
	}

}
