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
//import org.apache.catalina.User;

public class orderservlet extends HttpServlet 
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
      try {
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

              out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\navigationbar.html"));
          out.println(" <div id='content'> ");
          //  out.println("<h2>Welcome to Best Deal</h2>");
          MySqlDataStoreUtilities mysqlObject;
          mysqlObject = new MySqlDataStoreUtilities();
          int j=0;
          
          HashMap<Integer, order> result = mysqlObject.DisplayOrder(username);
          out.println("<table>");
          for(Integer i : result.keySet())
          {
            j++;
            order odobj = result.get(i);
            out.println("<h4 align='center' STYLE='font-weight: bold; color: black;' >Order  "+j+"</h4>");
            out.println("<table border='1' class='table'>");
            out.println("<tr>");

            out.println("<form  method = 'get' action = 'cancelorder'>");
            out.println("<input  type = 'submit' name = 'buy' value = 'Delete order'>");
            out.println("<input type = 'hidden' name='orderid' value='"+i+"'>");
            out.println("</form>");

           


            out.println("</tr>");


            out.println("<tr>");

            out.println("<th>");
            out.println("Order Number");
            out.println("</th>");

            out.println("<td>");
            out.println(odobj.getorderid());
            out.println("</td>");
            out.println("</tr>");
            out.println("<tr>");

            out.println("<th>");
            out.println("Customer Name");
            out.println("</th>");

            out.println("<td>");
            out.println(odobj.getusername());
            out.println("</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<th>");
            out.println("Order Amount");
            out.println("</th>");

            out.println("<td>");
            out.println(odobj.gettotalamount());
            out.println("</td>");
            out.println("</tr>");


            out.println("<tr>");
            out.println("<th>");
            out.println("Delivery Adress");
            out.println("</th>");

            out.println("<td>");
            out.println(odobj.getaddress());
            out.println("</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<th>");
            out.println("Payment Info");
            out.println("</th>");

            out.println("<td>");
            out.println(odobj.getcardnumber());
            out.println("</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<th>");
            out.println("Delivery Date");
            out.println("</th>");

            out.println("<td>");
            out.println(odobj.getdeliverydate());
            out.println("</td>");
            out.println("</tr>");


            out.println("</table>");


            out.println("<hr>");


           
          }
          
          
          out.println("</div>");
          
          out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\footer.html"));

          }
          
          else
          {
              out.println(hdr);
              out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\navigationbar.html"));
              out.println(" <div id='content'> ");
              out.println("<h3 Style=' color: black;'>Too see the order first do the login");
              out.println("</h3>");
              out.println("</div>");
          
              out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\footer.html"));
              
          }
          
          
          
      } catch (SQLException ex) {
          Logger.getLogger(orderservlet.class.getName()).log(Level.SEVERE, null, ex);
      }

    }

      public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
      {
      try {
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

              out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\navigationbar.html"));
          out.println(" <div id='content'> ");
          //  out.println("<h2>Welcome to Best Deal</h2>");
          MySqlDataStoreUtilities mysqlObject;
          mysqlObject = new MySqlDataStoreUtilities();
          int j=0;
          
          HashMap<Integer, order> result = mysqlObject.DisplayAllOrder();
          out.println("<table>");
          for(Integer i : result.keySet())
          {
            j++;
            order odobj = result.get(i);
            out.println("<h4 align='center' STYLE='font-weight: bold; color: black;' >Order  "+j+"</h4>");
            out.println("<table border='1' class='table'>");
            out.println("<tr>");


             out.println("<form  method = 'get' action = 'cancelorder'>");
            out.println("<input  type = 'submit' name = 'buy' value = 'Delete order'>");
            out.println("<input type = 'hidden' name='orderid' value='"+i+"'>");
            out.println("</form>");

            String orderusrname=odobj.getusername();
           // out.println(orderusrname);

            out.println("<form  method = 'get' action = 'updateorder'>");
            out.println("<input  type = 'submit' name = 'buy' value = 'Update order'>");
            out.println("<input type = 'hidden' name='orderid' value='"+i+"'>");
           out.println("<input type = 'hidden' name='username1' value='"+orderusrname+"'>");
            out.println("</form>");

            out.println("</tr>");


            out.println("<tr>");

            out.println("<th>");
            out.println("Order Number");
            out.println("</th>");

            out.println("<td>");
            out.println(odobj.getorderid());
            out.println("</td>");
            out.println("</tr>");
            out.println("<tr>");

            out.println("<th>");
            out.println("Customer Name");
            out.println("</th>");

            out.println("<td>");
            out.println(odobj.getusername());
            out.println("</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<th>");
            out.println("Order Amount");
            out.println("</th>");

            out.println("<td>");
            out.println(odobj.gettotalamount());
            out.println("</td>");
            out.println("</tr>");


            out.println("<tr>");
            out.println("<th>");
            out.println("Delivery Adress");
            out.println("</th>");

            out.println("<td>");
            out.println(odobj.getaddress());
            out.println("</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<th>");
            out.println("Payment Info");
            out.println("</th>");

            out.println("<td>");
            out.println(odobj.getcardnumber());
            out.println("</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<th>");
            out.println("Delivery Date");
            out.println("</th>");

            out.println("<td>");
            out.println(odobj.getdeliverydate());
            out.println("</td>");
            out.println("</tr>");


            out.println("</table>");


            out.println("<hr>");


           
          }
          
          
          out.println("</div>");
          
          out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\footer.html"));

          }
          
       
          
          
      } catch (SQLException ex) {
          Logger.getLogger(orderservlet.class.getName()).log(Level.SEVERE, null, ex);
      }

    }
  }