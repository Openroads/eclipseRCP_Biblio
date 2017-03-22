package biblioteka.interfaces;

public interface IBook {
	
	public void setId(int id);
	public void setTitle(String title);
	public void setAuthors(String title);
	public void setPublicationYear(String year);
	public void setStatus(String status);
	
	public int getId();
	public String getTitle();
	public String getAuthors();
	public String getPublicationYear();
	public String getStatus();
}
