import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
//import org.apache.catalina.User;

public class manager extends HttpServlet 
{
	public void init() throws ServletException
	{
		
    } // Get a set of the entries
     
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
     PrintWriter out = response.getWriter();
    ResultSet rs = null;
        try{
        Connection conn = null;
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mybestdeal","root","root");
        
            
        
        String getproduct = "SELECT * From product ";
          
          PreparedStatement checkuserstmt = conn.prepareStatement(getproduct);
        

          rs=checkuserstmt.executeQuery();
       

				HttpSession session = request.getSession();
        
        		cart shoppingCart;

        		shoppingCart = (cart) session.getAttribute("cart");
               // HttpSession session = request.getSession(true);
                String username;
                username = (String) session.getAttribute("sessionusername"); // session is set

              
                String msg;
                String hdr = readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\header.html");
                
                if(username!=null)
                {                   
                    out.println(hdr.replaceAll("guest", username));
                   
                } 
                
                else 
                {
                    out.println(hdr);
                    
                }

                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\navigationbar.html"));
                out.println(" <div id='content'> ");
             
                 
                
                 if(username==null)
                 { 
                   out.println("<h3 align='center'> Do login as a manager first</h3>");
                 } 

                 else
                  {
                        if(username.equalsIgnoreCase("manager1"))
                        { 

out.println("<h3>Insert product here</h3>");       
out.println("<form class = 'submit-button' method = 'get' action = 'insertproduct'>");
    out.println("<input class = 'submit-button' type = 'submit' name = 'submit' value = 'insert'>");
    out.println("</form>");
    out.println("<hr> <table>");
   out.println("<tr>");
   out.println("<td>ID</td>");
    out.println("<td>Type</td>");
     out.println("<td>Name</td>");
     out.println(" <td>Price</td>");
       out.println("<td>Image</td>");
       out.println(" <td>Delete</td>");
        out.println(" <td>Update</td>");
         
  out.println(" </tr>");
   while (rs.next()) 
        {
                              int id=(rs.getInt("id"));
                              String type=(rs.getString("type"));

                                            String name=(rs.getString("name"));
                                            Double price=(rs.getDouble("price"));
                                            String image =(rs.getString("image"));

        
   
   out.println("<tr>");
    out.println("<td>'"+id+"'</td>");
     out.println("<td>'"+type+"'</td>");
     out.println("<td>'"+name+"'</td>");
     out.println("<td>'"+price+"'</td>");
     out.println("<td>'"+image+"'</td>");

    out.println("<td>");
      out.println("<form class = 'submit-button' method = 'get' action = 'deleteproduct'>");
  out.println("<input class = 'submit-button' type = 'submit' name = 'submit' value = 'Delete'>");
    out.println("<input type = 'hidden' name='pid' value='"+id+"'>");
    out.println("</form>");
   out.println("</td>");
    out.println("<td>");
      out.println("<form class = 'submit-button' method = 'get' action = 'updateproduct'>");
    out.println("<input class = 'submit-button' type = 'submit' name = 'submit' value = 'Update'>");
  out.println("<input type = 'hidden' name='pid' value='"+id+"'>");
  out.println("</form>");
    out.println("</td>");
   
   out.println("</tr>");
   } 
   out.println("</table>");
   
                        }
                      else
                      { 
                       out.println("<h3 align='center'>you are not manager..you do not have access here</h3>");
                      } 
                      }       

                       }
        catch(Exception E)
        {}       
                  
                
               
                out.println("</div>");
                
                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\footer.html"));

    }
  }