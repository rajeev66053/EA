package edu.miu.cs.cs544.exercise;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
 @Setter
 @Getter
 @Table(name="OrderTable")
public class Order {

	@Id
	@GeneratedValue
	private long orderid;
	@Temporal(TemporalType.DATE)
	private Date date;
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name="ordertable_id")
	private List<OrderLine> orderlines=new ArrayList<>();
	
	public void addOrderLine(OrderLine ol) {
		this.orderlines.add(ol);
	}
	
	@Override
	public String toString() {
		return "Order [orderid=" + orderid + ", date=" + date + ", customer=" + customer + ", orderlines=" + orderlines
				+ "]";
	}

	

}