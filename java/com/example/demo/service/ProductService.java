package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Product;


public interface ProductService {

	public List<Product> find();

	public List<Product> findByKeyword(String keyword);

	public Product findById(String id);
	
	public int deleteById(String id);
	
	public int updateById(String id, String name, String price, String category, String description, String nowId);

	public int insert(Product product);

}