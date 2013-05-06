package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.google.gson.Gson;
import java.io.*;
import java.text.*;
import java.util.*;
import db.*;
import log.MyLogger;
import model.Tool;

@WebServlet( name="ToolService", displayName="Tool Service", urlPatterns = {"/toolservice"}, loadOnStartup=1)
public class ToolService extends HttpServlet {



	public  void init() {
		MyLogger.LogMessage("Toolservlet loodi mallu.");  
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {

		  try
		    {
			if (req.getParameter("id")!=null && req.getParameter("id").matches("[+-]?\\d*(\\.\\d+)?"))
				{ 
			    getTool(req,res);
			   }
			}

			catch(Exception ex)
			{  
				MyLogger.Log("PageControl.doGet():",ex.getMessage());
			}
	}

		

			  
		public void getTool(HttpServletRequest req, HttpServletResponse res)
              {  
				 int Tool;
				 java.sql.Connection myConnection = null;
				 try {
					 Tool = Integer.parseInt(req.getParameter("id"));
					 myConnection = dbconnection.getConnection();
					 toolDAO t = new toolDAO(myConnection);
					 Tool tool = new Tool();
					 Gson Gson= new Gson();
					 tool = t.findById(Tool);
					 String json = Gson.toJson(tool);

					 req.setCharacterEncoding("UTF-8");
			         res.setContentType("text/html; charset=utf-8");
			         PrintWriter out = res.getWriter();
			         out.print(json);
				 
				}
				 catch (Exception ex)
		                      {
			              MyLogger.Log("toolservice.getTool():",ex.getMessage());
		                      }
		                    finally
		                       {
		                     	dbconnection.close(myConnection);
		                      }
			  }
		
  
		public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws IOException, ServletException {
			
			doGet(req, res);

		}
}