package bilbioteka.test;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import biblioteka.book.Book;

@XmlRootElement(name="books")
@XmlAccessorType(XmlAccessType.FIELD)
public class Books {
	@XmlElement(name ="book")
	private List<Book> books;

	
	
	public List<Book> getBooks() {
		return books;
	}

	
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}
