package model;

import java.awt.image.BufferedImage;
import java.math.BigInteger;

public class Shoe {
	private int id;
	private String model;
	private String brand;
	private String color;
	private Integer price;

	
	public Shoe(int id, String model, String brand, String color, Integer price) {
		super();
		this.id = id;
		this.model = model;
		this.brand = brand;
		this.color = color;
		this.price = price;
	
	}
	
	public Shoe(String model, String brand, String color, Integer price) {
		super();
		this.model = model;
		this.brand = brand;
		this.color = color;
		this.price = price;
	}
	
	public Shoe(int id, String model) {
		super();
		this.id = id;
		this.model = model;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	
	
	
	
	

}
