package com.example.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public User findByIdAndPass(String id, String pass) {
    	MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);
		param.addValue("pass", pass);
		List<User> list = jdbcTemplate.query("SELECT * FROM users WHERE login_id = :id AND password = :pass", param, new BeanPropertyRowMapper<User>(User.class));
        return list.isEmpty() ? null : list.get(0);
    	
//        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ID_AND_PASS)) {
//        	stmt.setString(1, id);
//            stmt.setString(2, pass);
//
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                return new User(rs.getString("login_id"), rs.getString("password"), rs.getString("name"), rs.getInt("role"));
//            } else {
//                return null;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
    
}
