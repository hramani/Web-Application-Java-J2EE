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

public class updateorder extends HttpServlet 
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
                String usernameorder= request.getParameter("username1");
               
         
               


                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\navigationbar.html"));
                out.println(" <div id='content'> ");
            //    out.println("<h3 Style=' color: black;'>Update your Order1");
                Connection conn = null;
                ResultSet rs = null;
                Statement stmt =null;
            //    out.println("<h3 Style=' color: black;'>Update your Order2");

                try
                {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
              //      out.println("<h3 Style=' color: black;'>Update your Order3");
                    conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mybestdeal","root","root");
               //     out.println("<h3 Style=' color: black;'>Update your Order4");
          
 
                stmt= conn.createStatement();  
              //  out.println("<h3 Style=' color: black;'>Update your Order5"); 
               
                rs = stmt.executeQuery("SELECT * FROM customerorder WHERE orderid='"+orderId+"' AND username='"+usernameorder+"'");
             //   out.println("<h3 Style=' color: black;'>Update your Order6");
        
                int size= 0;
                if (rs != null)   
                {  
                  rs.beforeFirst();  
                  rs.last();  
                  size = rs.getRow();  
                } 
            //    out.println("result set size"+size);

               //  out.println("Update your Order7");
                              int idd=(rs.getInt("orderid"));
                              String usernameoforder=(rs.getString("username"));
                              double amt= (rs.getDouble("totalamount"));
                              String add=(rs.getString("address"));
                              String cc=(rs.getString("creditcard"));
                              String dd=(rs.getString("deliverydate"));

                            out.println("<form class = 'submit-button' method = 'get' action = 'updateorder2'> ");            
                            out.println("<table>");

                               out.println("<tr>");
                               out.println("<td>Order ID</td><td><input type='text'  name='oid' value='"+idd +"'></td>");
                               out.println("</tr>");

                               out.println("<tr>");
                                out.println("<td>User Name</td>  <td><input type='text' name='ouname' value='"+usernameoforder+"'></td>");
                                out.println("</tr>");

                                out.println("<tr>");
                                 out.println("<td>Total Amount</td><td><input type='text' name='amt' value='"+amt+"'></td>");
                                 out.println("</tr>");

                                 out.println("<tr>");
                                  out.println("<td>Card Number</td><td><input type='text' name='occ' value='"+cc+"'></td>");
                                  out.println("</tr>");

                                  out.println("<tr>");
                                   out.println("<td>Delivery DAte</td>   <td><input type='text' name='odd' value='"+dd+"'></td>");
                                   out.println("</tr>");
                                   
                                   out.println("</table>");
                                   
                                    out.println("<input class = 'submit-button' type = 'submit' name = 'submit' value = 'Update'>");
                                
                                    out.println("</form>");
                    
}
                catch(Exception E)
                {} 

                out.println("</div>");
                
                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\footer.html"));

    }
}