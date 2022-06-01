package com.example.demo.controller.form;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;

public class ProductForm {

	@NotBlank(message="商品IDは必須です")
	private int productId;
	
	private int categoryId;
	private String categoryName;
	
	@NotBlank(message="商品名は必須です")
	private String name;
	
	@NotBlank(message="単価は必須です")
	private int price;
	
	private String description;
	private Timestamp createdDate;
	private Timestamp updatedDate;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	

}