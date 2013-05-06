package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import log.MyLogger;



public class dbconnection {
	
	public static Connection getConnection() {
	     Connection db_connection=null  ;	
	     String pwd = "";
	     String usr = "";
	     String url = "";
		 
	    try
	    {            
	      ResourceBundle bundle = ResourceBundle.getBundle("DBConnection");
	      Class.forName(bundle.getString("Driver"));
	      url = bundle.getString("url");
	      usr = bundle.getString("usr");
	      pwd = bundle.getString("pwd");
	      db_connection = DriverManager.getConnection(url, usr, pwd);
	      
	    }
	  catch(Exception ex)
	   {  
		  System.out.println("dbconnection.getConnection():" + ex.getMessage());
	    MyLogger.Log("dbconnection.getConnection():" , ex.getMessage());
	    }
		return db_connection;
	}
	
	public static void close(final Connection conn) {

		if (conn != null)
		{
			try
			{
				conn.close();
			}
			catch (SQLException ex)
			{
				 System.out.println("dbconnection.close()" +  ex.getMessage());
			}
		}

	}
	public static void closeStatement(final Statement stmt)
	{
		if (stmt != null)
		{
			try
			{
				stmt.close();
			}
			catch (SQLException ex)
			{
				 System.out.println("dbconnection.closeStatement()" + ex.getMessage());
			}
		}

	}
	public  static void closeResultSet(final ResultSet rs)
	{
		if (rs != null)
		{
			try
			{
				rs.close();
			}
			catch (SQLException ex)
			{
				 System.out.println("dbconnection.closeResult()" + ex.getMessage());
			}
		}
	}
}
