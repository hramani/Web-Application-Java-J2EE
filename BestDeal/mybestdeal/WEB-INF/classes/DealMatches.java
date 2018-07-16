import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;



import java.io.File;
import java.io.IOException;
import java.util.List;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DealMatches extends HttpServlet 
{

	

	public void init() throws ServletException
	{
  				
	}
	public String readFile(String filename) 
				{
		   			File f = new File(filename);
		   			try 
		   			{
			           byte[] bytes = Files.readAllBytes(f.toPath());
				       return new String(bytes, "UTF-8");
	        		} 
	        		catch (Exception e) 
	        		{
		       		//	e.printStackTrace();
	        		}
	        		return "";
	        
	    		}

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
				ResultSet rs = null;
  				Connection conn = null;
  				
				try
				{
	 					PrintWriter out = response.getWriter();
						conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mybestdeal","root","root");
						String products = "SELECT name From product ";					
						PreparedStatement checkuserstmt = conn.prepareStatement(products);
						
						


						HttpSession session = request.getSession(true);
						String username = (String) session.getAttribute("sessionusername"); // session is set

		          
		                String msg;
		                String hdr = readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\header.html");
		                
		                if(username!=null)
		                {       			
		                	out.println(hdr.replaceAll("guest", username));
		                	msg="successfully login";
		            	} 
		            	
		            	else 
		            	{
		            		out.println(hdr);
		            		
		            	}

		               
		                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\navigationbar.html"));
		                out.println(" <div id='content'> ");
		                out.println("<h2>Best Buy Deal</h2>");

						int countproduct = 0;
				
							String line=null;
				
									rs=checkuserstmt.executeQuery();

									BufferedReader br = new BufferedReader(new FileReader (new File("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\images\\DealMatches.txt")));
									StringBuffer stringBuffer = new StringBuffer();
									int counter =0;
							

					HashMap<String,product> selectedproducts=new HashMap<String,product>(); 
					try
					{

				


						//Get all the products into hashmap from Database
						 
						out.println("<br> <br>");
						MySqlDataStoreUtilities object = new MySqlDataStoreUtilities();
						 HashMap<String,product> productmap=object.getData();
				
					
					
					for(Map.Entry<String, product> entry : productmap.entrySet())
					{
						if(selectedproducts.size()<2 && !selectedproducts.containsKey(entry.getKey()))
						{

									BufferedReader reader = new BufferedReader(new FileReader (new File("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\DealMatches.txt")));
									
									line=reader.readLine();		
									
									if(line==null)		
									{ 
										out.println("<h2 align='center'>No Offers Found</h2>");
										break;
									}		
										
							//If no products found that	
									else
									{	
										do 
										{			

											if(line.contains(entry.getKey()))			
											{				
											
												out.println("<h2>"+line+"</h2>");
												out.println("<br>");

												selectedproducts.put(entry.getKey(),entry.getValue());

												
												break;
											}			
										 }while((line = reader.readLine()) != null);		
										//Display the tweets	
									 }	
						}
					}
								// deisplay product
									 for(String  namee : selectedproducts.keySet())
							         {
							           
							            product proobj = selectedproducts.get(namee);

												
												String img = proobj.getImage();
												out.println("<img src='"+img+"' alt='image' height='85' width='85' />");
												out.println("<br>");
												out.println("<h4 style='color:red;'>name : "+proobj.getName()+    "</h4>");
												out.println("<br>");

												out.println("<h4 style='color:red;'>price : "+proobj.getPrice()+"</h4>");
												
												
												out.println(".................................................................");
												out.println("<br>");


									 }
					}		
					
					catch(Exception E)
					{}	


							          	
									
						
							
							out.println("</div>");
                
                			out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\footer.html"));



				}
				catch(Exception E)
				{}


                

	      	
			

               
    }

}


