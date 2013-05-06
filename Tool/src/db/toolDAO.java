package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.dbconnection;
import log.MyLogger;
import model.Tool;

public class toolDAO {
	ResultSet toolRS ;
	String sql ;
	Connection db ;	
	Statement  st ;
	PreparedStatement ps;
	Tool [] ToolMassiiv  ;
	Tool Current_Tool ;
	
	
	public toolDAO(Connection myConnection1) {
		try
		{
        this.db = myConnection1;

		}
		catch(Exception ex)
		{  
			System.out.println("tooldao:"+ ex.getMessage());
		}
	}

	public Tool [] findAll() {
		try {

		
			List<model.Tool> ToolList = new ArrayList<model.Tool>();	
			this.st = this.db.createStatement();
			sql = "select tool, type, weight_gr, description from tool order by tool" ;
//			MyLogger.Log("DAO: update():",sql);
			toolRS = this.st.executeQuery(sql);
			int cnt = 0;
			while(toolRS.next()) {
//				MyLogger.Log("DAO: update(): counter ",Integer.toString(cnt));
				Current_Tool = new Tool();    
				Current_Tool.setId(toolRS.getInt("tool"));
				Current_Tool.setType(toolRS.getString("type"));
				Current_Tool.setWeight(toolRS.getInt("weight_gr"));
				Current_Tool.setDescription(toolRS.getString("description"));
				ToolList.add(Current_Tool);
				cnt+=1;
	//			MyLogger.Log("DAO: update(): counter end ",Integer.toString(cnt));
			}
			ToolMassiiv = new Tool[cnt];
			     for (int i=0; 1<ToolList.size(); i++) {
			        	ToolMassiiv[i] = (Tool) ToolList.get(i);
                }
			
			
		}

		catch(Exception ex)
		{
			MyLogger.Log("ToolDAO.findAll():",ex.getMessage());

		}
		finally
		{
			dbconnection.closeStatement(st);
			dbconnection.closeResultSet(toolRS);
		}

		return ToolMassiiv;
	}
	
	public Tool findById(int tool_id) {

		try {			

			this.st = this.db.createStatement();
			sql = "select tool, type, weight_gr, description from tool where tool =" + Integer.toString(tool_id) ;
			toolRS = this.st.executeQuery(sql);

			while(toolRS.next()) {    
				Current_Tool = new Tool();
				Current_Tool.setId(toolRS.getInt("tool"));
				Current_Tool.setType(toolRS.getString("type"));
				Current_Tool.setWeight(toolRS.getInt("weight_gr"));
				Current_Tool.setDescription(toolRS.getString("description"));
			}
			this.db.close();
		}

		catch(Exception ex)
		{

			MyLogger.Log("DAO.findById():",ex.getMessage());

		}
		finally
		{
			dbconnection.closeStatement(st);
			dbconnection.closeResultSet(toolRS);
		}


		return Current_Tool;
	}
	
	public void update (Tool updated_tool) {

		try {
			this.st = this.db.createStatement();
			int stmtInt = this.st.executeUpdate("update tool set type='" +  updated_tool.getType() + "',weight_gr=" + Integer.toString(updated_tool.getWeight()) + ",description='" +  updated_tool.getDescription() + "' where tool=" + Integer.toString(updated_tool.getId() ));

		}
		catch(Exception ex)
		{ 
			MyLogger.Log("DAO: update():",ex.getMessage());
		}
		finally
		{
			dbconnection.closeStatement(st);
			dbconnection.closeResultSet(toolRS);
		}
	}
	
}
