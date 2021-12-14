package edu.miu.cs.cs544.exercise;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@NoArgsConstructor
 @Setter
 @Getter

public class Product {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String description;
	@Override
	public String toString() {
		return "Product [name=" + name + ", description=" + description + "]";
	}
	
	
	
}