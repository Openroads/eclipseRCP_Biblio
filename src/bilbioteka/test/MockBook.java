package bilbioteka.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import biblioteka.book.Book;
import biblioteka.interfaces.IModelData;

public class MockBook implements IModelData {

	private ArrayList<Book> books = new ArrayList<Book>();
	private ArrayList<Book> testBooks;
	
	
	public MockBook() {

	Book[] bookExamle =
	{
			new Book(1,"Oracle Database 12c","McLaughlin, Michael","2015","Wypożyczona"),
			new Book(2,"Clean code","Robert c. martin","2014","Wypożyczona"),
			new Book(3,"Oracle Database 12c","McLaughlin, Michael","2015","Dostępna do wypożyczenia"),
			new Book(4,"Clean code","Robert c. martin","2013","Dostępna do wypożyczenia"),
			new Book(5,"Oracle Database 12c","McLaughlin, Michael","2012","Wypożyczona"),
			new Book(6,"Design patterns","Richard Helm ","2012","Dostępna do wypożyczenia"),
			new Book(7,"Oracle Database 12c","McLaughlin, Michael","2015","Wypożyczona"),
			new Book(8,"Clean code","Robert c. martin","2014","Wypożyczona")
	};
	
	this.books.addAll(Arrays.asList(bookExamle));
	
	this.testBooks = new ArrayList<Book>(this.books.size());
	for(Book book: this.books)
	{
		Book clone = book.clone();
		System.out.println(clone);
		this.testBooks.add(clone);
	}
	
	}
	
	@Override
	public Collection<Book> getAllBooks() {
		if(books == null)
			return Collections.emptyList();
		else
			return books;
	}

	@Override
	public Book getBookById(int id) {
		for(Book book : books)
			if(book.getId() == id)
			{
				return book;
			}
		
		return null;
	}

	@Override
	public void addNewBook(Book book) {
		books.add(book);
		
	}

	@Override
	public void addNewBook(Collection<Book> books) {
		books.addAll(books);
		
	}

	@Override
	public void deleteBook(int id) {
		for(Book book : books)
			if(book.getId() == id)
			{
				this.books.remove(book);
				break;
			}
		
		
	}

	@Override
	public void deleteBook(int[] ids) {
		for(Integer id : ids)
			for(Book book : books)
				if(book.getId() == id)
				{
					this.books.remove(book);
					break;
				}
	}


	
	@Override
	public void setBookStatus(int[] ids,String status) {
		for(Integer id : ids)
			for(Book book : books)
				if(book.getId() == id)
				{
					book.setStatus(status);
					break;
				}
	}

	@Override
	public Integer[] checkBooksStatus() {
		// TODO Auto-generated method stub
		System.out.println("check");
		ArrayList<Integer> ids = new  ArrayList<Integer>(); 
		for(int i = 0; i < this.books.size(); i++)
			if(this.books.get(i).getStatus().equals(this.testBooks.get(i).getStatus()) == false)
				{
					ids.add(this.books.get(i).getId());
					this.books.get(i).setStatus(new String(this.testBooks.get(i).getStatus()));
				}
		Integer[] ret = new Integer[ids.size()];
		ret = ids.toArray(ret);
		//System.out.println(ret.length);
		return ret;
	}
	
	public void changeRandomStatus()
	{
		Random generator = new Random(); 
		int i = generator.nextInt(this.books.size());
		this.testBooks.get(i).setStatus("Changed from thred");
	}

}
