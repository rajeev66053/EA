package edu.miu.cs.cs544.exercise;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
 @Setter
 @Getter

public class Reservation {

	@Id
	@GeneratedValue
	private long id;
	private Date date;
	@ManyToOne
	@JoinColumn(name="book_id")
	private Book book;
	
	public Reservation() {}
	public Reservation(Date date, Book book) {
		super();
		this.date = date;
		this.book = book;
	}
	
	


	public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}




	public Date getDate() {
		return date;
	}




	public void setDate(Date date) {
		this.date = date;
	}




	public Book getBook() {
		return book;
	}




	public void setBook(Book book) {
		this.book = book;
	}




	@Override
	public String toString() {
		return "Reservation [id=" + id + ", date=" + date + "]";
	}
	
	

}