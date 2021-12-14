package edu.miu.cs.cs544.exercise;

import java.util.StringTokenizer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="MY_CAR")
public class Car {

	@Id
	@GeneratedValue
	private long id;
	private String brand;
	private int year;
	private double price;	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="owner_id")
	private Owner owner;

	public Car() {
	}

	public Car(String brand, int year, double price) {
		super();
		this.brand = brand;
		this.year = year;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}	

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Car [" + id + ", brand=" + brand + ", year=" + year+ ", price=" + price + "]";
	}
}