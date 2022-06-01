package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Product;

public interface ProductDao {

	public Product findById(String id);

	public int deleteById(String id);
	
	public int updateById(int id, String name, int price, int category, String description, int nowId);

	public List<Product> findAll();

	public List<Product> findByKeyword(String keyword);

	public int insert(Product product);

}