package db;

import model.Tool;
import java.util.*;

public class test {
	
	public static void main(String[] args) {
		try {
			toolDAO toolDAO;
			java.sql.Connection myConnection = null;
			myConnection = dbconnection.getConnection();
			toolDAO = new toolDAO(myConnection);
			Tool [] tool;
			tool=toolDAO.findAll();
			System.out.println("ToolDAO.findAll() - OK!");
		} catch (Exception e) {
			System.out.println("testToolDAO.findAll():" + e);
		}
		try {
			toolDAO toolDAO;
			java.sql.Connection myConnection = null;
			myConnection = dbconnection.getConnection();
			toolDAO = new toolDAO(myConnection);
			Tool tool = null;
			int id = 1;
			tool=toolDAO.findById(id);
			//System.out.println(tool.getAmount());
			System.out.println("toolDAO.findById() - OK!");
		} catch (Exception e) {
			System.out.println("testToolDAO.findByID():" + e);
		}
		try {
			toolDAO toolDAO;
			java.sql.Connection myConnection = null;
			myConnection = dbconnection.getConnection();
			toolDAO = new toolDAO(myConnection);
			Tool tool = new Tool();
			tool.setWeight(100);
			tool.setDescription("");
			tool.setId(1);
			tool.setType("Haamer");
			toolDAO.update(tool);
			System.out.println("ToolDAO.update() - OK!");
		} catch (Exception e) {
			System.out.println("testToolDAO.update():" + e);
		}
	}
}

