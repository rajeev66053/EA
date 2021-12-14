package edu.miu.cs.cs544.examples.B;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@NoArgsConstructor @Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Passenger {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	@OneToMany
//	@JoinTable(name="PASSENGER_FLIGHT")
	@JoinColumn(name="passenger_id")
	@OrderColumn(name="sequence")
	private List<Flight> flights = new ArrayList<Flight>();
		
	public Passenger(String name) {
		super();
		this.name = name;
	}
	
	public void addFlight(Flight flight) {
		flights.add(flight);
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name
				+ ", flights.size()=" + flights.size()
				+ "]";
	}
}
