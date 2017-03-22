package biblioteka.book;

import javax.xml.bind.annotation.XmlRootElement;

import biblioteka.interfaces.IBook;

@XmlRootElement(name = "book")
public class Book implements IBook, Cloneable{
	
	private int id;
	private String title;
	private String authors;
	private String publicationYear;
	private String status;
	
	public Book()
	{}
	
	public Book(int id, String title, String authors, String publicationYear, String status) {
		this.id = id;
		this.title = title;
		this.authors = authors;
		this.publicationYear = publicationYear;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public String getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(String publicationYear) {
		this.publicationYear = publicationYear;
	}
	public boolean equals(Object anObject) {
		if(anObject instanceof Book)
			if(((Book) anObject).getId() == this.id ) return true;
		
		return false;
		
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Book clone()
	{
		Book cloneBook = new Book(this.id,new String(this.title),new String(this.authors),new String(this.publicationYear),new String(this.status));
		return cloneBook;
		
	}

}
