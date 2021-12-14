package edu.miu.cs.cs544.examples.B;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor @Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Flight {
	@Id
	@GeneratedValue
	private int id;
	private String flightnumber;
	
	@Column(name="from")
	private String from;
	
	@Column(name="to")
	private String to;
	@Temporal(TemporalType.DATE)
	private Date date;
	
	public Flight(String flightnumber, String from, String to, Date date) {
		super();
		this.flightnumber = flightnumber;
		this.from = from;
		this.to = to;
		this.date = date;
	}
}
