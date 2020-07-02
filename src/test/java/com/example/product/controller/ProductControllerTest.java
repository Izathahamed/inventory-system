package com.example.product.controller;

import static org.assertj.core.api.Assertions.assertThat;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.product.model.Product;
import com.example.product.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(value=ProductController.class)
class ProductControllerTest {
	

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ProductController controller;
	
	@MockBean
	private ProductService service;
	
	@Test
	void testUpdate() throws Exception {
		
		Product mockProduct = new Product();
		mockProduct.setId(2);
		mockProduct.setName("pen");
		mockProduct.setQuantity(1);
		mockProduct.setPrice(25);
		
		String inputJson = this.mapToJson(mockProduct);
		String url = "/api/product/update";
		Mockito.when(service.updateProduct(Mockito.any(Product.class))).thenReturn(mockProduct);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put(url).accept(MediaType.APPLICATION_JSON)
				.content(inputJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String outputInJson = response.getContentAsString();
		
		assertThat(outputInJson).isEqualTo(inputJson);
		assertEquals(HttpStatus.OK.value(),response.getStatus());
	}
	
	@Test
	void testAdd() throws Exception {
		
		Product mockProduct = new Product();
		mockProduct.setId(2);
		mockProduct.setName("pen");
		mockProduct.setQuantity(1);
		mockProduct.setPrice(25);
		
		String inputJson = this.mapToJson(mockProduct);
		String url = "/api/product/add";
		Mockito.when(service.saveProduct(Mockito.any(Product.class))).thenReturn(mockProduct);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(url).accept(MediaType.APPLICATION_JSON)
				.content(inputJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String outputInJson = response.getContentAsString();
		
		assertThat(outputInJson).isEqualTo(inputJson);
		assertEquals(HttpStatus.OK.value(),response.getStatus());
		
	}
	
	@Test
	void testAdd1() {
		Product product = new Product();
		product.setId(1);
		product.setName("book");
		product.setQuantity(20);
		product.setPrice(300);
		
		Mockito.when(service.saveProduct(product)).thenReturn(product);
		assertEquals(controller.add(product),product);
		
	}
	
	@Test
	void testUpdate1() {
		Product product = new Product();
		product.setId(1);
		product.setName("book");
		product.setQuantity(20);
		product.setPrice(300);
		
		Mockito.when(service.updateProduct(product)).thenReturn(product);
		assertEquals(controller.update(product),product);
		
	}
	

	@Test
	void testfindById() throws Exception {
		Product mockProduct = new Product();
	    mockProduct.setId(2);
		mockProduct.setName("mobile");
		mockProduct.setQuantity(1);
		mockProduct.setPrice(15000);
		
		Mockito.when(service.getById(Mockito.anyInt())).thenReturn(mockProduct);
		String url = "/api/product/2";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(url).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String inputJson = this.mapToJson(mockProduct);

		
		String outputInJson = result.getResponse().getContentAsString();
		
		assertThat(outputInJson).isEqualTo(inputJson);
		
	}
	
	@Test
	void testfindAllProducts() throws Exception {
		Product mockProduct1 = new Product();
		mockProduct1.setId(1);
		mockProduct1.setName("pen");
		mockProduct1.setQuantity(1);
		mockProduct1.setPrice(25);
		
		Product mockProduct2 = new Product();
	    mockProduct2.setId(2);
		mockProduct2.setName("mobile");
		mockProduct2.setQuantity(1);
		mockProduct2.setPrice(15000);
		
		List<Product> productList = new ArrayList<>();
		productList.add(mockProduct1);
		productList.add(mockProduct2);
		
		Mockito.when(service.getAllProducts()).thenReturn(productList);
		String url = "/api/product/findAll";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(url).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String inputJson = this.mapToJson(productList);
		String outputInJson = result.getResponse().getContentAsString();
		
		assertThat(outputInJson).isEqualTo(inputJson);
		
	}
	

	
	
	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

}
