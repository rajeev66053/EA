package edu.miu.cs.cs544.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Customer")
@NoArgsConstructor @Getter @Setter @ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@NotEmpty
	@Length(max=30,message = "Firstname cannot be more than 30 character.")
	private String firstname;
	@Length(max=30,message = "Lastname cannot be more than 30 character.")
	private String lastname;
	@Temporal(TemporalType.DATE)
	@Past
	private LocalDate birthdate;
	@NotNull
	@NotEmpty
	@Email(regexp =".+@.+\\..+",message="should be in the email format" )
	private String emailaddress;
	
	public Customer(@NotNull @Length(max = 30) String firstname, @Length(max = 30) String lastname, LocalDate birthdate,
			String emailaddress) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthdate = birthdate;
		this.emailaddress = emailaddress;
	}
	
	
	
	

}
