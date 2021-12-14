package edu.miu.cs.cs544.exercise;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Setter
@Getter
public class Student {

	@Id
	@GeneratedValue
	private int studentid;
	private String firstname;
	private String lastname;
	@ManyToMany(mappedBy ="students" )
	@JoinColumn(name="publisher_id")
	private List<Course> courses=new ArrayList<>();
	
	public Student() {}
	public Student(int studentid, String firstname, String lastname, List<Course> courses) {
		super();
		this.studentid = studentid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.courses = courses;
	}
	
	


	public int getStudentid() {
		return studentid;
	}




	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}




	public String getFirstname() {
		return firstname;
	}




	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}




	public String getLastname() {
		return lastname;
	}




	public void setLastname(String lastname) {
		this.lastname = lastname;
	}




	public List<Course> getCourses() {
		return courses;
	}




	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}




	@Override
	public String toString() {
		return "Student [studentid=" + studentid + ", firstname=" + firstname + ", lastname=" + lastname + ", courses="
				+ courses + "]";
	}
	
	

}