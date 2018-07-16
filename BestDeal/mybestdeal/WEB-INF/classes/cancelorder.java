import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.apache.catalina.User;

public class cancelorder extends HttpServlet 
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
                    //  e.printStackTrace();
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

                MySqlDataStoreUtilities mysqlObject;
                mysqlObject = new MySqlDataStoreUtilities();

                int orderId=Integer.parseInt(request.getParameter("orderid"));
         
                mysqlObject.deleteOrder(orderId);


                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\navigationbar.html"));
                out.println(" <div id='content'> ");
                out.println("<h3 Style=' color: black;'>Order Successfully canceled");
             
                out.println("</div>");
                
                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\footer.html"));

    }
}