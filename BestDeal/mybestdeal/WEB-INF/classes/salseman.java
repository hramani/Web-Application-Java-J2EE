import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.apache.catalina.User;

public class salseman extends HttpServlet 
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
				HttpSession session = request.getSession();
        
        		cart shoppingCart;

        		shoppingCart = (cart) session.getAttribute("cart");
               // HttpSession session = request.getSession(true);
                String username;
                username = (String) session.getAttribute("sessionusername"); // session is set

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
                 if(username.equalsIgnoreCase("salseman1"))
                 {
                  out.println("<h3>Welcome Salsemanager</h3>");
                  out.println("<br>");
                  out.println("<h4 style='color: black;'>You can see all the customer order here</h4>");
                  out.println("<br>");
                  out.println("<form class = 'submit-button' method = 'post' action = 'orderservlet'>");
                  out.println("<input class = 'submit-button' type = 'submit' name = 'submit' value = 'all orders'>");
                  out.println("<input type = 'hidden' name='currentlogger' value='salseman1'>");
                  out.println("</form>");
                  out.println("<br>");

                  out.println("<h4 style='color: black;'>You can Create Customre Accounts here</h4>");
                  out.println("<br>");
                  out.println("<form class = 'submit-button' method = 'post' action = 'register'>");
                  out.println("<input class = 'submit-button' type = 'submit' name = 'submit' value = 'Create Customer Accounts'>");
                  out.println("<input type = 'hidden' name='currentlogger' value='salseman1'>");
                  out.println("</form>");

                  
                  
                 }
                 else
                 {
                    out.println("you are not salseman..you do not have access here");
                 }
              //  out.println("<h2>Welcome to Best Deal</h2>");
               
                out.println("</div>");
                
                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\footer.html"));

    }
  }