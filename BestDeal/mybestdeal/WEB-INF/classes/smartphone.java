import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


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

public class smartphone extends HttpServlet 
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
				try{
	 			
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mybestdeal","root","root");
				String getorder = "SELECT * From product WHERE type='phone' ";					
				PreparedStatement checkuserstmt = conn.prepareStatement(getorder);
				rs=checkuserstmt.executeQuery();
				


				HttpSession session = request.getSession(true);
				String username = (String) session.getAttribute("sessionusername"); // session is set

                PrintWriter out = response.getWriter();
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
                out.println("<h2>Welcome to Best Deal</h2>");
              //  out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
            
                out.println("<table>");
              while (rs.next()) 
        	  {

                                            String name=(rs.getString("name"));
                                            int price=(rs.getInt("price"));
                                            String image =(rs.getString("image"));

	        		
		        		out.println("<tr>");
		        		out.println("<td>");
		          		out.print("<img src='"+image+"' alt='image' height='85' width='85' />");
		          		out.println("</td>");
		          		out.println("<td>");
		          		out.println("Product Name = "+name+"<br>");
		          		out.println("Price = "+price);
		          		out.println("</td>");
		          		out.println("<td>");
		          		out.println("<form  method = 'get' action = 'addtocart'>");
		                out.println("<input  type = 'submit' name = 'buy' value = 'BUY'>");
		                out.println("<input type = 'hidden' name='name' value="+name+">");
		                out.println("<input type = 'hidden' name='price' value="+price+">");
		                
		                out.println("</form>");

						out.println("<form class = 'submit-button' method = 'get' action = 'writereview'>");
						out.println("<input class = 'submit-button' type = 'submit' name = 'submit' value = 'Write Review'>");
						out.println("<input type = 'hidden' name='productname' value='"+name+"'>");
						out.println("<input type = 'hidden' name='productprice' value='"+price+"'>");
						out.println("<input type = 'hidden' name='category' value='smartphone'>");
		                out.println("<input type = 'hidden' name='retailer' value='sony'>");
						out.println("</form>");

						out.println("<form class = 'submit-button' method = 'get' action = 'viewreview'>");
						out.println("<input class = 'submit-button' type = 'submit' name = 'submit' value = 'View Reviews'>");
						out.println("<input type = 'hidden' name='productname' value='"+name+"'>");
						out.println("</form>");

		               
		          		out.println("</td>");
		          		out.println("</tr>");
		          		

	          	    
	      		}
	      		out.println("</table>");
                		out.println("</div>");
                
                		out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\footer.html"));

	      		}
				catch(Exception E)
				{}

               
    }

}


