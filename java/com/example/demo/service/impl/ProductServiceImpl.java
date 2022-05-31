package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductDao;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	@Override
	public List<Product> find() {
		return productDao.findAll();
	}

	@Override
	public List<Product> findByKeyword(String keyword) {
		return productDao.findByKeyword(keyword);
	}

	@Override
	public Product findById(String id) {
		return productDao.findById(id);
	}

	@Override
	public int deleteById(String id) {
		return productDao.deleteById(id);
	}
	
	@Override
	public int updateById(String id, String name, String price, String category, String description, String nowId) {
		return productDao.updateById(id, name, price, category, description, nowId);
	}

	@Override
	public int insert(Product product) {
		return productDao.insert(product);
	}

}