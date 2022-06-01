package com.example.demo.controller.form;

import javax.validation.constraints.NotBlank;

public class UserForm {

	@NotBlank(message="IDは必須です")
	private String loginId;
    
	@NotBlank(message="PASSは必須です")
    private String password;
    
    private String name;
    private int role;
    
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}

    
}
