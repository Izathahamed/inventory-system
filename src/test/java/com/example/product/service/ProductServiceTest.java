package com.example.product.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(value=ProductService.class)
class ProductServiceTest {
	@MockBean
	private ProductRepository repository;
	
	@Autowired
	private ProductService service;

	@Test
	void testSaveProduct() {
		Product product = new Product();
		product.setId(1);
		product.setName("book");
		product.setQuantity(20);
		product.setPrice(300);
		
		Mockito.when(repository.save(product)).thenReturn(product);
		assertEquals(service.saveProduct(product),product);
		
	}
	
	@Test
	void testGetAllProducts() {
		Product product = new Product();
		product.setId(1);
		product.setName("book");
		product.setQuantity(20);
		product.setPrice(300);
		
		Product product1 = new Product();
		product1.setId(2);
		product1.setName("charger");
		product1.setQuantity(5);
		product1.setPrice(500);
		
		List<Product> productList = new ArrayList<>();
		productList.add(product);
		productList.add(product1);
		
		Mockito.when(repository.findAll()).thenReturn(productList);
		assertEquals(productList,service.getAllProducts());
		
	}
	
	

}
