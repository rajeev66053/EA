package edu.miu.cs.cs544.exercise;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
 @Setter
 @Getter

public class Department {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	@OneToMany(mappedBy="department", cascade = CascadeType.PERSIST)
	private List<Employee> employees=new ArrayList<>();
	
	public Department() {}

	public Department(String name, List<Employee> employees) {
		super();
		this.name = name;
		this.employees = employees;
	}
	



	public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public List<Employee> getEmployees() {
		return employees;
	}




	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}




	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", employees=" + employees + "]";
	}

}