import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.nio.file.Files;
import java.util.HashMap;

public class register extends HttpServlet 
{
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
          username = (String) session.getAttribute("sessionusername"); 

				
                String usr = "Guest";
                PrintWriter out = response.getWriter();

                String hdr = readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\header.html");

                if (username != null) 
                {
                	out.println(hdr.replaceAll("guest", usr));
            	} 
            	else 
            	{
            		out.println(hdr);
            	}

                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\navigationbar.html"));
                out.println(" <div id='content' align='center'> ");
                
                out.println("<h3 align='center'>Please fill your personal detail</h3><hr>");
               // out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
            		//out.println("<legend align='center'>Register your account</legend><br>");
                
            	out.println("<form method='post' action='index' style='color:black;'>");

		          out.println("Type of user<br><br>");
		          out.println("<input type='radio' name='usertype' value='customer'>Customer &nbsp; &nbsp ");
		          out.println("<input type='radio' name='usertype' value='salseman'>Salseman &nbsp; &nbsp");
		          out.println("<input type='radio' name='usertype' value='manager'>Product Manager<br><br>");


		          out.println("User Name:<br>");
		          out.println("<input type='text' name='username'><br><br>");

		         
		          out.println("Password:<br>");
		          out.println("<input type='password' name='password'><br><br>");
		          
		          out.println("<input type='submit' value='Register'><br>");

      			out.println("</form>");

      			

                out.println("</div>");
                
                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\footer.html"));

    }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{

          
				 HttpSession session = request.getSession();
				cart shoppingCart;
          
          		shoppingCart = (cart) session.getAttribute("cart");
          // HttpSession session = request.getSession(true);
          		String username;
          		username = (String) session.getAttribute("sessionusername"); 

				
                String usr = "SALSEMAN1";
                PrintWriter out = response.getWriter();

                String hdr = readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\header.html");

                if (username != null) 
                {
                	out.println(hdr.replaceAll("guest", usr));
            	} 
            	else 
            	{
            		out.println(hdr);
            	}

                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\navigationbar.html"));
                out.println(" <div id='content' align='center'> ");
                
                out.println("<h3 align='center'>Please fill Customer's personal detail</h3><hr>");
               // out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
            		//out.println("<legend align='center'>Register your account</legend><br>")

             
            	out.println("<form method='post' action='index' style='color:black;'>");


		          out.println("Type of user<br><br>");
		          out.println("<input type='radio' name='usertype' value='customer'>Customer <br> ");
		


		          out.println("User Name:<br>");
		          out.println("<input type='text' name='username'><br><br>");

		         
		          out.println("Password:<br>");
		          out.println("<input type='password' name='password'><br><br>");
		          
		          out.println("<input type='submit' value='Register'><br>");

      			out.println("</form>");
      			out.println("<br>");
      	/*		out.println("All the customer");

      			out.println("<table class='table' border='1px'>");
           
            out.println("<tr>");

            out.println("<th>");
            out.println("Username");
            out.println("</th>");

            out.println("<th>");
            out.println("Password");
            out.println("</th>");

           

            out.println("<th>");
            out.println("Action");
            out.println("</th>");

            out.println("</tr>");


            MySqlDataStoreUtilities mysqlObject;
          	mysqlObject = new MySqlDataStoreUtilities();
          	
          
          	HashMap<String, User> result;
            result = mysqlObject.DisplayAllUser(); 

            
            for (String key :  result.keySet())
          		{

                User uobject = result.get(key);
                out.println("<tr>");
                out.println("<td>");
                username =uobject.getusername();

                String userpassword =uobject.getuserpassword(); 
                out.println(username);
                out.println("</td>");

                out.println("<td>");
                out.println(userpassword);
                out.println("</td>");

               

                out.println("<td>");
                out.println("<form  method = 'get' action = 'deletecustomer'>");
                out.println("<input  type = 'submit' name = 'deleteuser' value = 'DeleteUser'>");
                out.println("<input type = 'hidden' name='username' value="+username+">");
                out.println("</form>");
                out.println("</td>");
                out.println("</tr>");
            } */

                out.println("</table>");

      			

                out.println("</div>");
                
                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\footer.html"));

    }


}


