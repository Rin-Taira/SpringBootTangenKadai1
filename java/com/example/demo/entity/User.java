package com.example.demo.entity;

public class User {

    private String loginId;
    private String password;
    private String name;
    private int role;

    public User() {
    }

    public User(String loginId, String password, String name, int role) {
    	this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getUserId() {
        return this.loginId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    
    public void setRole(int role) {
    	this.role = role;
    }
    
    public int getRole() {
    	return this.role;
    }
}
