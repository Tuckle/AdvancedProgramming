
public class Song extends AbstractItem{

	Song(String inputName, String inputPath, int inputYear, String inputAuthor)
	{
		this.name = inputName;
		this.path = inputPath;
		this.year = inputYear;
		this.author = inputAuthor;
	}
	
	public void setName(String inputName) throws CustomException
	{
		if (inputName.equals(""))
			throw new CustomException("Empty string");
		this.name = inputName;
	}
	public  String getName()
	{
		return this.name;
	}
	public  void setPath(String inputPath) throws CustomException
	{
		if (inputPath.equals(""))
			throw new CustomException("Empty string");
		this.path = inputPath;
	}
	public  String getPath()
	{
		return this.path;
	}
	public  void setYear(int inputYear) throws CustomException
	{
		if (year < 0)
			throw new CustomException("Year value bellow zero");
		this.year = inputYear;
	}
	public int getYear()
	{
		return this.year;
	}
	public void setAuthor(String inputAuthor) throws CustomException
	{
		if (inputAuthor.equals(""))
			throw new CustomException("Empty string");
		this.author = inputAuthor;
	}
	public String getAuthor()
	{
		return this.author;
	}
}
