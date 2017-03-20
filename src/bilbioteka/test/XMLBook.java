package bilbioteka.test;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import biblioteka.book.Book;
import biblioteka.interfaces.IModelData;

public class XMLBook implements IModelData{
	
	private ArrayList<Book> books = new ArrayList<Book>();
	private String filePath;
	private File file;
	private JAXBContext context;
	private Marshaller marshaller;
	private Unmarshaller unMarshaller;
	

	public XMLBook(String filePath) throws JAXBException {
		this.filePath = filePath;
		this.file = new File(filePath);
		this.context = JAXBContext.newInstance(Books.class);
		this.marshaller = context.createMarshaller();
		this.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		this.unMarshaller = context.createUnmarshaller();  
	
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
		this.file = new File(this.filePath);
	}
	
	@Override
	public Collection<Book> getAllBooks() {
		
		return null;
	}

	@Override
	public Book getBookById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNewBook(Book book) {
		Books books = new Books();
		books = readXMLFile();
		
		List<Book> bookL = new ArrayList<Book>(1);
		bookL = books.getBooks();
		bookL.add(book);
		books.setBooks(bookL);
		
		writeToXMLFile(books);
		
		
	}



	@Override
	public void addNewBook(Collection<Book> books) {
		
		try {
			this.marshaller.marshal(books, this.file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteBook(int id) {
		
		Books bks =  readXMLFile();
		List<Book> books = bks.getBooks();
		
		for(Book book : books)
			if(book.getId() == id)
			{
				books.remove(book);
				break;
			}
		writeToXMLFile(bks);
	}

	@Override
	public void deleteBook(int[] ids) {
		
		Books bks =  readXMLFile();
		List<Book> books = bks.getBooks();
		
		for(Integer id : ids)
			for(Book book : books)
				if(book.getId() == id)
				{
					books.remove(book);
					break;
				}
		
		writeToXMLFile(bks);
		
	}

	@Override
	public void setBookStatus(Book book, String status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBookStatus(Collection<Book> books, String status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkBooksStatus() {
		// TODO Auto-generated method stub
		
	}
	
	private Books readXMLFile(){
		Books books = null;
		try {
			 books =  (Books)unMarshaller.unmarshal(this.file);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}
	
	private void writeToXMLFile(Books books) {
		
		try {
			this.marshaller.marshal(books, System.out);
			this.marshaller.marshal(books, this.file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
