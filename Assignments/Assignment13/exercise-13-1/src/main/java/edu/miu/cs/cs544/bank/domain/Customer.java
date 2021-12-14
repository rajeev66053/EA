package edu.miu.cs.cs544.bank.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor @Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {
	
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private long id;
	private String name;
	
	public Customer(String name) {
		this.name = name;
	}
}
