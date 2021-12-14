package edu.miu.cs.cs544.exercise;

import java.util.StringTokenizer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="MY_CAR")
public class Car {
	private long id;
	private String brand;
	private int year;
	private double price;

	public Car() {
	}

	public Car(String brand, int year, double price) {
		super();
		this.brand = brand;
		this.year = year;
		this.price = price;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Column
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Car [" + id + ", brand=" + brand + ", year=" + year+ ", price=" + price + "]";
	}
}