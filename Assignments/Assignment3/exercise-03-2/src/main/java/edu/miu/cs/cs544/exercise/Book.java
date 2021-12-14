package edu.miu.cs.cs544.exercise;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
public class Book {

	@Id
	@GeneratedValue
	private int isbn;
	private String title;
	private String author;
	@ManyToOne
	@JoinColumn(name="publisher_id")
	private Publisher publisher;
	
	
	public Book(){}
	
	public Book(int isbn, String title, String author, Publisher publisher) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
	}

	

	public int getIsbn() {
		return isbn;
	}



	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getAuthor() {
		return author;
	}



	public void setAuthor(String author) {
		this.author = author;
	}



	public Publisher getPublisher() {
		return publisher;
	}



	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}



	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + ", publisher=" + publisher + "]";
	}

	

	

	

}