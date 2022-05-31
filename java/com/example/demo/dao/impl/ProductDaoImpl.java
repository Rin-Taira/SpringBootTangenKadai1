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
		param.addValue("id", id);
		List<Product> list = jdbcTemplate.query("SELECT product_id, category_id, c.name AS c_name, p.name AS p_name, price, description, p.created_at AS p_created_at, p.updated_at AS p_updated_at FROM products AS p INNER JOIN categories AS c ON p.category_id = c.id WHERE product_id = :id", param, new BeanPropertyRowMapper<Product>(Product.class));
        return list.isEmpty() ? null : list.get(0);
	}
	
	@Override //合ってるか不安すぎる！
	public int deleteById(String id) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);
		return jdbcTemplate.update("DELETE FROM products WHERE product_id = :id", param);
	}
	
	@Override //どんな型でも addValueでいけるんかな？
	public int updateById(String id, String name, String price, String category, String description, String nowId) {
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
		return jdbcTemplate.query("SELECT product_id, category_id, c.name AS c_name, p.name AS p_name, price, description, p.created_at AS p_created_at, p.updated_at AS p_updated_at FROM products AS p INNER JOIN categories AS c ON p.category_id = c.id WHERE p.name LIKE :keyword OR c.name LIKE :keyword ORDER BY product_id", param, new BeanPropertyRowMapper<Product>(Product.class));
	}
	
	@Override // createdatについては反映されない、それは引数productでそれがnullになっているから。見直し必要かも
	public int insert(Product product) {
		BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(product);
        return jdbcTemplate.update("INSERT INTO products (product_id, category_id, name, price, description, created_at) VALUES (:productId, :categoryId, :name, :price, :description, :createdDate)", paramSource);
	}
//	private static final String SQL_SELECT_ID = "SELECT product_id, category_id, c.name AS c_name, p.name AS p_name, price, description, p.created_at AS p_created_at, p.updated_at AS p_updated_at FROM products AS p INNER JOIN categories AS c ON p.category_id = c.id WHERE product_id = ?";
//	private static final String SQL_SELECT_ALL = "SELECT product_id, category_id, c.name AS c_name, p.name AS p_name, price, description, p.created_at AS p_created_at, p.updated_at AS p_updated_at FROM products AS p INNER JOIN categories AS c ON p.category_id = c.id ORDER BY product_id";
//	private static final String SQL_SELECT_BY_KEYWORD = "SELECT product_id, category_id, c.name AS c_name, p.name AS p_name, price, description, p.created_at AS p_created_at, p.updated_at AS p_updated_at FROM products AS p INNER JOIN categories AS c ON p.category_id = c.id WHERE p.name LIKE ? OR c.name LIKE ? ORDER BY product_id";
//	private static final String SQL_INSERT = "INSERT INTO products (product_id, category_id, name, price, description, created_at) VALUES (?, ?, ?, ?, ?, ?)";
//	private static final String SQL_DELETE_BY_ID = "DELETE FROM products WHERE product_id = ?";
//	private static final String SQL_UPDATE_BY_ID = "UPDATE products SET product_id = ?, name = ?, price = ?, category_id = ?, description = ?, updated_at = ? WHERE product_id = ?";
//
//	private Connection connection;
//
//	public ProductDao(Connection connection) {
//		this.connection = connection;
//	}
//
//	public Product findById(String id) {
//		try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ID)) {
//			stmt.setInt(1, Integer.parseInt(id));
//
//			ResultSet rs = stmt.executeQuery();
//
//			if (rs.next()) {
//				return new Product(rs.getInt("product_id"), rs.getInt("category_id"), rs.getString("c_name"), rs.getString("p_name"), rs.getInt("price"), rs.getString("description"), rs.getTimestamp("p_created_at"), rs.getTimestamp("p_updated_at"));
//			} else {
//				return null;
//			}
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	public int deleteById(String id) {
//		try (PreparedStatement stmt = connection.prepareStatement(SQL_DELETE_BY_ID)) {
//			stmt.setInt(1, Integer.parseInt(id));
//
//			return stmt.executeUpdate();
//
//		} catch (SQLException e) {
//			return -1;
//		}
//	}
//	
//	public int UpdateById(String id, String name, String price, String category, String description, String nowId) {
//		try (PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE_BY_ID)) {
//			stmt.setInt(1, Integer.parseInt(id));
//			stmt.setString(2, name);
//			stmt.setInt(3, Integer.parseInt(price));
//			stmt.setInt(4, Integer.parseInt(category));
//			stmt.setString(5, description);
//			stmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
//			stmt.setInt(7, Integer.parseInt(nowId));
//
//			return stmt.executeUpdate();
//
//		} catch (SQLException e) {
//			return -1;
//		}
//	}
//
//	public List<Product> findAll() {
//		List<Product> list = new ArrayList<Product>();
//
//		try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL)) {
//			ResultSet rs = stmt.executeQuery();
//
//			while (rs.next()) {
//				Product p = new Product(rs.getInt("product_id"), rs.getInt("category_id"), rs.getString("c_name"), rs.getString("p_name"), rs.getInt("price"), rs.getString("description"), rs.getTimestamp("p_created_at"), rs.getTimestamp("p_updated_at"));
//				list.add(p);
//			}	
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//		return list;
//	}
//
//	public List<Product> findByKeyword(String keyword) {
//		List<Product> list = new ArrayList<Product>();
//
//		try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_KEYWORD)) {
//			stmt.setString(1, "%" + keyword + "%");
//			stmt.setString(2, "%" + keyword + "%");
//			ResultSet rs = stmt.executeQuery();
//
//			while (rs.next()) {
//				Product p = new Product(rs.getInt("product_id"), 0, rs.getString("c_name"), rs.getString("p_name"), rs.getInt("price"), rs.getString("description"), rs.getTimestamp("p_created_at"), rs.getTimestamp("p_updated_at"));
//				list.add(p);
//			}	
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//		return list;
//	}
//
//	public int insert(Product product) {
//		try (PreparedStatement stmt = connection.prepareStatement(SQL_INSERT)) {
//			stmt.setInt(1, product.getProductId());
//			stmt.setInt(2, product.getCategoryId());
//			stmt.setString(3, product.getName());
//			stmt.setInt(4, product.getPrice());
//			stmt.setString(5, product.getDescription());
//			stmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
//
//			return stmt.executeUpdate();
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//	}


}