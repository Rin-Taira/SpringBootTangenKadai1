package com.example.demo.entity;

import java.sql.Timestamp;

public class Product {

	private int productId;
	private int categoryId;
	private String categoryName;
	private String name;
	private int price;
	private String description;
	private Timestamp createdDate;
	private Timestamp updatedDate;

	public Product() {
	}

	public Product(int productId, int categoryId, String categoryName, String name, int price, String description, Timestamp createdDate, Timestamp updatedDate) {
		this.productId = productId;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.name = name;
		this.price = price;
		this.description = description;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

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