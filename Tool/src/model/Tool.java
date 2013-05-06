package model;

public class Tool {
	private int id ;
	private String type ;
	private int weight ;
	private String description ;
	public void setId(int id)
	{
	this.id = id;
	}
	public void setType (String type)
	{
	this.type = type;
	}
	public void setWeight(int weight)
	{
	this.weight = weight;
	}
	public void setDescription(String description)
	{
	this.description = description;
	}
	public int getId()
	{
	return this.id ;
	}
	public String getType ()
	{
	return this.type ;
	}
	public int getWeight ()
	{
	return this.weight ;
	}
	public String getDescription()
	{
	return this.description;
	}

}
