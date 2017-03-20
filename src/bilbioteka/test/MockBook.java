package bilbioteka.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import biblioteka.book.Book;
import biblioteka.interfaces.IModelData;

public class MockBook implements IModelData {

	private ArrayList<Book> books = new ArrayList<Book>();; 
	
	
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
	
	books.addAll(Arrays.asList(bookExamle));	
	
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
	public void setBookStatus(Book book, String status) {
		for(Book b : books)
		{
			if(b.equals(book))
			{
				b.setStatus(status);
			}
		}
	}

	@Override
	public void setBookStatus(Collection<Book> books, String status) {
		for(Book book : books)
			for(Book cBook : this.books)
				if (cBook.equals(book))
				{
					cBook.setStatus(status);
				}
	}

	@Override
	public void checkBooksStatus() {
		// TODO Auto-generated method stub
		
	}

}
