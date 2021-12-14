package edu.miu.cs.cs544.exercise;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;

@Entity
@SecondaryTables(
		@SecondaryTable(name="Address", pkJoinColumns = {
		@PrimaryKeyJoinColumn(name="patient_id", referencedColumnName="id") 
}))

public class Patient {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	@Column(table="Address")
	private String street;
	@Column(table="Address")
	private String zip;
	@Column(table="Address")
	private String city;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
