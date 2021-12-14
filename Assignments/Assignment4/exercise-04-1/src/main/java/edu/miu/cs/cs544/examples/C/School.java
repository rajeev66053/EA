package edu.miu.cs.cs544.examples.C;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
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
public class School {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	@OneToMany
//	@JoinTable(name="School_Student")
	@JoinColumn(name="student_id")
	@MapKey(name="studentid")
	private Map<Long, Student> students = new HashMap<Long, Student>();
		
	public School(String name) {
		super();
		this.name = name;
	}
	
	public void addStudent(Student student) {
		students.put(student.getStudentid(), student);
	}

	@Override
	public String toString() {
		return "School [id=" + id + ", name=" + name + ", students.size()=" + students.size() + "]";
	}
}
