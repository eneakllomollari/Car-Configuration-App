package model;

import java.io.Serializable;

public class Option implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private float price;

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected float getPrice() {
		return price;
	}

	protected void setPrice(float price) {
		this.price = price;
	}

	protected void print() {
		System.out.printf("\tOption Name: %s\n\tPrice: $%.2f\n\n", this.name, this.price);
	}
}
