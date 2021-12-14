package edu.miu.cs.cs544.exercise;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Setter
@Getter
public class Course {

	@Id
	@GeneratedValue
	private int id;
	private int coursenumber;
	private String name;
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "Course_Student",
	 joinColumns = { @JoinColumn(name = "Course_id") },
	 inverseJoinColumns = { @JoinColumn(name = "Student_id") }
	 )
	private List<Student> students=new ArrayList<>();
	
	public Course() {}
	
	public Course(int coursenumber, String name, List<Student> students) {
		super();
		this.coursenumber = coursenumber;
		this.name = name;
		this.students = students;
	}
	
	


	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public int getCoursenumber() {
		return coursenumber;
	}




	public void setCoursenumber(int coursenumber) {
		this.coursenumber = coursenumber;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public List<Student> getStudents() {
		return students;
	}




	public void setStudents(List<Student> students) {
		this.students = students;
	}




	@Override
	public String toString() {
		return "Course [id=" + id + ", coursenumber=" + coursenumber + ", name=" + name + ", students=" + students
				+ "]";
	}
	
	
}