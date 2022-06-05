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
    	
    }
    
}
