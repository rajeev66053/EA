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
public class Employee {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private int employeenumber;
	@ManyToOne
	@JoinColumn(name="department_id")
	private Department department;
	@ManyToOne
	@JoinColumn(name="office_id")
	private Office office;
	
	public Employee() {}
	
	public Employee(String name, int employeenumber, Department department, Office office) {
		super();
		this.name = name;
		this.employeenumber = employeenumber;
		this.department = department;
		this.office = office;
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





	public int getEmployeenumber() {
		return employeenumber;
	}





	public void setEmployeenumber(int employeenumber) {
		this.employeenumber = employeenumber;
	}





	public Department getDepartment() {
		return department;
	}





	public void setDepartment(Department department) {
		this.department = department;
	}





	public Office getOffice() {
		return office;
	}





	public void setOffice(Office office) {
		this.office = office;
	}





	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", employeenumber=" + employeenumber + ", department="
				+ department + "]";
	}

	
}
