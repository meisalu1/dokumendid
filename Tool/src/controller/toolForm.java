package controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import log.MyLogger;
import model.Tool;



public class toolForm {
	private String id;
	private String type = "";
	private String weight = "" ;
	private String description = "" ;
	private int ModelDataOK = 0;
	public HashMap format_errors;
	
	public void setId(String id)
	{
	this.id = id;
	}
	public void setType (String type)
	{
	this.type = type;
	}
	public void setWeight(String weight)
	{
	this.weight = weight;
	}
	public void setDescription(String description)
	{
	this.description = description;
	}
	public String getId()
	{
	return this.id ;
	}
	public String getType ()
	{
	return this.type ;
	}
	public String getWeight ()
	{
	return this.weight ;
	}
	public String getDescription ()
	{
		return this.description ;
	}
	public int getModelDataOK()
	{
	return this.ModelDataOK;
	}
	
	public Tool ValidateAndConvertModelData()
	{
	model.Tool Tool = new Tool();
    format_errors = new HashMap() ;
	try
	  {
			    
	    if (getId() != null)
	      {
	      Tool.setId(Integer.parseInt(getId()));
	      }
	    
	     if (getType() == "" || getType().length()>=20)
	        { format_errors.put("type","type puudub voi vales formaadis"); }
	          else
	        {	Tool.setType(getType());  }
	    
		 try
	       {
          Tool.setWeight(Integer.parseInt(getWeight()));
		   }
		 catch(Exception ex)
          {  
		   format_errors.put("kaal","kaal puudub voi formaat vale");
		  }
	 
	 
		 Tool.setDescription(getDescription());
		 
	  if (format_errors.size() == 0)
	  {
      ModelDataOK = 1;
	  }
	}
	   catch(Exception ex)
       {  	  
       MyLogger.Log("Toolform.ConvertToModelData():",ex.getMessage());
       }
	return Tool ;
	}

	public String getErrorByField (String Field) 
	{
	String field_error ="";
	String format_error;
	String chk_field = Field ;
	
	if (format_errors != null)
	   {
	     format_error = (String) format_errors.get(chk_field);
		 if (format_error != null)
		   {
		   field_error = format_error ;
		   }
	   
	   }	   
    return field_error;
	}
	
	public void getDataFromModel(Tool ModelTool)
	{
	setId(Integer.toString(ModelTool.getId()));
    setType(ModelTool.getType());
    setWeight(Integer.toString(ModelTool.getWeight()));
	setDescription(ModelTool.getDescription());	
	}
}
