package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import log.MyLogger;
import db.dbconnection;
import db.toolDAO;

public class ToolServlet extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = -255583846639111275L;

	public  void init() {
		MyLogger.LogMessage("Controller.init() - servleti eksemplar loodi serveri mallu.");  
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
		int user_view = 0 ;

		try
		{


			if (req.getParameter("id") == null)
			{ 
			user_view = getTools(req); 
			}
			else
			{
				if (req.getParameter("id")!=null && req.getParameter("id").matches("[+-]?\\d*(\\.\\d+)?"))
					{ 
				    user_view = getTool(req);
				    }
				else {
					user_view=3;
				}
			}
			if (req.getParameter("action") != null)
			{
				if (req.getParameter("action").equals("save"))
				{	
                  user_view = updateTool(req);
					//user_view = 3;
				}
			}
			


					switch (user_view) {

					case 1 :  
						getServletConfig().getServletContext().getRequestDispatcher("/tools.jsp").forward(req, res);
						break ;

					case 2 :
						getServletConfig().getServletContext().getRequestDispatcher("/tool.jsp").forward(req, res);
						break ;

					case 3 :
						getServletConfig().getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
						break ;	
						
					default: 

						getServletConfig().getServletContext().getRequestDispatcher("/tools.jsp").forward(req, res);
						break ;

					}


			}

			catch(Exception ex)
			{  
				MyLogger.Log("PageControl.doGet():",ex.getMessage());
			}


		}


	public int getTools(HttpServletRequest req) 
			    {int user_view = 0;
				  java.sql.Connection myConnection = null;
				  model.Tool [] toolMassiiv = null;
				 try  {
					 	myConnection = dbconnection.getConnection();
					 	toolDAO f = new toolDAO(myConnection);
					 	toolMassiiv = f.findAll();
					    req.setAttribute ("tools", toolMassiiv);
						user_view = 1;
				}
						catch (Exception ex)
			          {
			      MyLogger.Log("Controller.getTools():",ex.getMessage());
			          }
				return user_view ;
			   }


			  
		public int getTool(HttpServletRequest req)
              {  int tool = 0 ;
				  java.sql.Connection myConnection = null;
				  model.Tool Tool = null;
				  toolForm toolForm= null;
				 int user_view = 0;
				 try {
				tool = Integer.parseInt(req.getParameter("id"));
				 myConnection = dbconnection.getConnection();
				 toolDAO f = new toolDAO(myConnection);
				 toolForm= new toolForm();
				 Tool = f.findById(tool);
				 if(Tool == null){
					 user_view=3;
				 }
				 else {
					 toolForm.getDataFromModel(Tool);			
					 req.setAttribute ("tool", toolForm);
					 user_view = 2;
				}
				}
				 catch (Exception ex)
		                      {
			              MyLogger.Log("Controller.getAuto():",ex.getMessage());
		                      }
		                    finally
		                       {
		                     	dbconnection.close(myConnection);
		                      }
							  
				 return user_view ;
			  }
		
		public int updateTool(HttpServletRequest req)
        {  
			  
			  java.sql.Connection myConnection = null;
			  model.Tool inputTool = null;
			  toolForm toolForm= new toolForm();
			  int user_view = 0;
			 try {
				 
					toolForm= getToolUpdateFormData(req);
					
					myConnection = dbconnection.getConnection();
					inputTool=toolForm.ValidateAndConvertModelData();
			 
					if (toolForm.getModelDataOK() == 1)		{
						 toolDAO f = new toolDAO(myConnection);
						 f.update(inputTool);
						 getTools(req);
						 user_view=1;
			           }
					req.setAttribute ("tool", toolForm);
					user_view = 2;
					
			 	}
			 catch (Exception ex)
	                      {
		              MyLogger.Log("Controller.updateTool2():",ex.getMessage());
	                      }
	                    finally
	                       {
	                     	dbconnection.close(myConnection);
	                      }
						  
			 return user_view ;
		  }

		
		public toolForm getToolUpdateFormData(HttpServletRequest req)
		{
		toolForm toolForm = new toolForm();
		String id = req.getParameter("tool_id");
		String type = req.getParameter("tool_type");
		String weight = req.getParameter("tool_weight"); 
		String description = req.getParameter("tool_desc"); 
		toolForm.setId(id);
		toolForm.setType(type);
		toolForm.setWeight(weight);
		toolForm.setDescription(description);
		return toolForm ;
		}		  
  
		public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws IOException, ServletException {
			
			doGet(req, res);

		}
	}
