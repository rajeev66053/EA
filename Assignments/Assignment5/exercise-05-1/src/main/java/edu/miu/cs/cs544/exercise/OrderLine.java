package edu.miu.cs.cs544.exercise;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class OrderLine {
	
	@Id
	@GeneratedValue
	private int id;
	private double quantity;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="product_id")
	private Product product;
	@Override
	public String toString() {
		return "OrderLine [quantity=" + quantity + ", product=" + product + "]";
	}
}
