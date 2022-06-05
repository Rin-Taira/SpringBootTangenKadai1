package com.example.demo.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.ProductDao;
import com.example.demo.entity.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public Product findById(String id) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", Integer.parseInt(id));
		List<Product> list = jdbcTemplate.query("SELECT product_id, category_id, c.name AS category_name, p.name AS name, price, description, p.created_at AS created_date, p.updated_at AS updated_datet FROM products AS p INNER JOIN categories AS c ON p.category_id = c.id WHERE product_id = :id", param, new BeanPropertyRowMapper<Product>(Product.class));
        return list.isEmpty() ? null : list.get(0);
	}
	
	@Override //合ってるか不安すぎる！
	public int deleteById(String id) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", Integer.parseInt(id));
		return jdbcTemplate.update("DELETE FROM products WHERE product_id = :id", param);
	}
	
	@Override //どんな型でも addValueでいけるんかな？
	public int updateById(int id, String name, int price, int category, String description, int nowId) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);
		param.addValue("name", name);
		param.addValue("price", price);
		param.addValue("c_id", category);
		param.addValue("description", description);
		param.addValue("up_date", new Timestamp(System.currentTimeMillis()));
		param.addValue("current_id", nowId);
		return jdbcTemplate.update("UPDATE products SET product_id = :id, name = :name, price = :price, category_id = :c_id, description = :description, updated_at = :up_date WHERE product_id = :current_id", param);
	}
	
	@Override
	public List<Product> findAll() {
		return jdbcTemplate.query("SELECT product_id, category_id, c.name AS category_name, p.name AS name, price, description, p.created_at AS created_date, p.updated_at AS updated_date FROM products AS p INNER JOIN categories AS c ON p.category_id = c.id ORDER BY product_id", new BeanPropertyRowMapper<Product>(Product.class));
		// https://loglog.xyz/programming/java/jdbctemplate_query_select で table名とEntityの結びつきについて学べるよ！！
	}
	
	@Override
	public List<Product> findByKeyword(String keyword) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("keyword", "%" + keyword + "%");
		return jdbcTemplate.query("SELECT product_id, category_id, c.name AS category_name, p.name AS name, price, description, p.created_at AS p_created_at, p.updated_at AS p_updated_at FROM products AS p INNER JOIN categories AS c ON p.category_id = c.id WHERE p.name LIKE :keyword OR c.name LIKE :keyword ORDER BY product_id", param, new BeanPropertyRowMapper<Product>(Product.class));
	}
	
	@Override // createdatについては反映されない、それは引数productでそれがnullになっているから。見直し必要かも
	public int insert(Product product) {
		BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(product);
        return jdbcTemplate.update("INSERT INTO products (product_id, category_id, name, price, description, created_at, updated_at) VALUES (:productId, :categoryId, :name, :price, :description, :createdDate, :createdDate)", paramSource);
	}

}