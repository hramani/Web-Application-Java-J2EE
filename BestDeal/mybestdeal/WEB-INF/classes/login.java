import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.nio.file.Files;

public class login extends HttpServlet 
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

           //   HttpSession session = request.getSession();

				

                PrintWriter out = response.getWriter();

                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\header.html"));
                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\navigationbar.html"));
                out.println(" <div id='content'> ");
                out.println("<h2>Welcome to Best Deal</h2>");
                out.println("<h4>...................Login.................... </h4>");

                                
                                out.println("<FORM action='loginservlet' method='get'>");
                                out.println("<P>");
                                        out.println("<LABEL for='username'>Username </LABEL>");
                                        out.println("<INPUT type='text' id='Username' name='uname'><BR>");

                                        out.println("<LABEL for='password' >Password </LABEL>");
                                        out.println("<INPUT type='password' name='upassword' id='Password'><BR>");
                                        
                                        out.println("<INPUT type='submit' value='Go'> <INPUT type='reset'>");
                                out.println("</P>");
                                out.println("</FORM>");

                out.println("</div>");
                
                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\footer.html"));

    }

}


