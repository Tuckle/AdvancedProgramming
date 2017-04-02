package AdvancedProgramming.Lab4.Sources;
import java.io.Serializable;

public abstract class AbstractItem implements Serializable{
	protected String name;
	protected String path;
	protected int year;
	protected String author;
	public abstract void setName(String inputName)throws CustomException;
	public abstract String getName();
	public abstract void setPath(String inputPath)throws CustomException;
	public abstract String getPath();
	public abstract void setYear(int inputYear) throws CustomException;
	public abstract int getYear();
	public abstract void setAuthor(String inputAuthor)throws CustomException;
	public abstract String getAuthor();
}
