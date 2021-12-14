package edu.miu.cs.cs544.exercise;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Customer {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	@OneToMany
	@JoinColumn(name="customer_id")
	private List<Reservation> reservations=new ArrayList<>();
	
	public Customer() {}
	
	public Customer(String name, List<Reservation> reservations) {
		super();
		this.name = name;
		this.reservations = reservations;
	}
	


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



	public List<Reservation> getReservations() {
		return reservations;
	}



	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}



	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", reservations=" + reservations + "]";
	}
	
		
}
