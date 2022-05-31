package com.example.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.CategoryDao;
import com.example.demo.entity.Category;

@Repository
public class CategoryDaoImpl implements CategoryDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Category> findAll() {
		return jdbcTemplate.query("SELECT * FROM categories", new BeanPropertyRowMapper<Category>(Category.class));
	}
    
}
