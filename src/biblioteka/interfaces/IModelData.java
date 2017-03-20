package biblioteka.interfaces;

import java.util.Collection;

import biblioteka.book.Book;

public interface IModelData {
	public Collection <Book> getAllBooks();
	public Book getBookById(int id);
	public void addNewBook(Book book);
	public void addNewBook(Collection <Book> books);
	public void deleteBook(int id);
	public void deleteBook(int[] ids);
	public void setBookStatus(Book book, String status);
	public void setBookStatus(Collection <Book> books, String status);
	public void checkBooksStatus();
}
