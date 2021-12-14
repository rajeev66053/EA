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
public class Office {

	@Id
	@GeneratedValue
	private long id;
	private int roomnumber;
	private String building;
	@OneToMany(mappedBy="office", cascade = CascadeType.ALL)
	private List<Employee> employees=new ArrayList<>();
	
	public Office() {}
	public Office(int roomnumber, String building, List<Employee> employees) {
		super();
		this.roomnumber = roomnumber;
		this.building = building;
		this.employees = employees;
	}
	
	


	public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}




	public int getRoomnumber() {
		return roomnumber;
	}




	public void setRoomnumber(int roomnumber) {
		this.roomnumber = roomnumber;
	}




	public String getBuilding() {
		return building;
	}




	public void setBuilding(String building) {
		this.building = building;
	}




	public List<Employee> getEmployees() {
		return employees;
	}




	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}




	@Override
	public String toString() {
		return "Office [id=" + id + ", roomnumber=" + roomnumber + ", building=" + building + ", employees=" + employees
				+ "]";
	}

	

}