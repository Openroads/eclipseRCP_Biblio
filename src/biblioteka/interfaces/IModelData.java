package biblioteka.interfaces;

import java.util.Collection;


public interface IModelData {
	
	public Collection <IBook> getAllBooks();
	public IBook getBookById(int id);
	public void addNewBook(IBook book);
	public void addNewBook(Collection <IBook> books);
	public void deleteBook(int id);
	public void deleteBook(int[] ids);
	public void setBookStatus(int[] ids, String status);
	public Integer[] checkBooksStatus();

}
