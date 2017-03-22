package bilbioteka.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import biblioteka.book.Book;
import biblioteka.interfaces.IBook;
import biblioteka.interfaces.IModelData;

public class XMLBook implements IModelData{
	
	private List<Book> books;
	private String filePath;
	private File file;
	private JAXBContext context;
	private Marshaller marshaller;
	private Unmarshaller unMarshaller;
	

	public XMLBook() throws JAXBException {
		this.filePath = "/home/dariusz/ProjecsWorkspace/book.xml";
		this.file = new File(filePath);
		this.context = JAXBContext.newInstance(Books.class);
		this.marshaller = context.createMarshaller();
		this.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		this.unMarshaller = context.createUnmarshaller();
		
		Books bks = readXMLFile();
		if( bks != null)
			this.books = bks.getBooks();
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
		this.file = new File(this.filePath);
	}
	
	@Override
	public Collection<IBook> getAllBooks() {
		
		if(this.books == null)
			return Collections.emptyList();
		else
			return this.books.stream().map(
					x-> (IBook) x).collect(Collectors.toList());
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
	public void addNewBook(IBook book) {
		
		Books bks = readXMLFile();
		if(bks == null)
			bks = new Books();
		
		this.books = bks.getBooks();
		System.out.println("null?? "+this.books);
		if(this.books == null){
			this.books = new ArrayList<Book>();
			bks.setBooks(this.books);
		}
		this.books.add((Book) book);
		System.out.println(this.books);
		
		writeToXMLFile(bks);
		
		
	}

	@Override
	public void setBookStatus(int[] ids, String status) {
		
		Books bks = readXMLFile();
		if(bks == null)
			bks = new Books();

		this.books = bks.getBooks();

		if(this.books != null){
			
			for(Integer id : ids)
				for(Book book : this.books)
					if(book.getId() == id)
					{
						book.setStatus(status);
						break;
					}
			
			writeToXMLFile(bks);
		}
		
	}


	@Override
	public void deleteBook(int id) {
		
		Books bks =  readXMLFile();
		this.books = bks.getBooks();
		
		for(Book book : this.books)
			if(book.getId() == id)
			{
				this.books.remove(book);
				break;
			}
		writeToXMLFile(bks);
	}

	@Override
	public void deleteBook(int[] ids) {
		
		Books bks =  readXMLFile();
		this.books = bks.getBooks();
		
		for(Integer id : ids)
			for(Book book : this.books)
				if(book.getId() == id)
				{
					this.books.remove(book);
					break;
				}
		
		writeToXMLFile(bks);
		
	}


	@Override
	public Integer[] checkBooksStatus() {
		
		ArrayList<Integer> ids = new  ArrayList<Integer>(); 
		Books bks = readXMLFile();
		List<Book> xmlBooks = bks.getBooks();
		System.out.println("checkBookstatusmethod");
		if(xmlBooks != null){
			int booksAmount = xmlBooks.size();
			if(booksAmount != this.books.size())
				return new Integer[0];
			
			for(int i = 0; i < xmlBooks.size(); i++)
				if(xmlBooks.get(i).getStatus().equals(this.books.get(i).getStatus()) == false)
					{
						ids.add(this.books.get(i).getId());
						this.books = xmlBooks;
					}
		}
		
		
		Integer[] ret = new Integer[ids.size()];
		ret = ids.toArray(ret);
		return ret;
		
	}
	
	private Books readXMLFile(){
		Books books = null;
		try {
			 books =  (Books)unMarshaller.unmarshal(this.file);
		} catch (JAXBException e) {
			books = null;
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

	@Override
	public void addNewBook(Collection<IBook> books) {
		// TODO Auto-generated method stub
		
	}



}
