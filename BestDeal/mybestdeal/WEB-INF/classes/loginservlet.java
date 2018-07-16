import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.apache.catalina.User;

public class loginservlet extends HttpServlet 
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

           //   HttpSession session = request.getSession();
				String username = request.getParameter("uname");
                String userpassword = request.getParameter("upassword");

                PrintWriter out = response.getWriter();

				MySqlDataStoreUtilities mysqlObject;
		        mysqlObject = new MySqlDataStoreUtilities();
				String msg=null;
                try
                {
                      msg = mysqlObject.checkUser(username,userpassword);
                } 

                catch (SQLException ex) 
                {
                    Logger.getLogger(loginservlet.class.getName()).log(Level.SEVERE, null, ex);
                }
              


          //      PrintWriter out = response.getWriter();
            	
                String hdr = readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\header.html");
                if(!msg.equals("User id or password is wrong"))
                {	
               		HttpSession session=request.getSession();  
        			session.setAttribute("sessionusername",username);

                	out.println(hdr.replaceAll("guest", username));
                	
            	} 
            	else             	
                {
            		out.println(hdr);
                    out.println("wrong user name and password");
            		
            	}

                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\navigationbar.html"));
                out.println(" <div id='content'> ");
               // out.println(msg);
             out.println("<h2>"+msg+"</h2>");
			
		        //............................................................................................
                out.println("</div>");
                
                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\footer.html"));

    }
}