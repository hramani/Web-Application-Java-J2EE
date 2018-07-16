import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.nio.file.Files;

public class showorder extends HttpServlet 
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
                String usernamee= request.getParameter("name");
                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\navigationbar.html"));
                out.println(" <div id='content'> ");
                out.println("<h2>Here is <strong style='color:red;'>"+usernamee+" </strong>order detail</h2>");
                
                int  ordernumber=  Integer.parseInt(request.getParameter("ordernumber"));
                Double totalamt=Double.parseDouble(request.getParameter("totalamount"));
                String address= request.getParameter("address");
                String creditcard= request.getParameter("cardnumber");
                String deliverydate=  request.getParameter("deliverydate");
                
                
             

                // call the method of insert order into table
                MySqlDataStoreUtilities mysqlObject;
                mysqlObject = new MySqlDataStoreUtilities();

                mysqlObject.insertOrder(ordernumber,usernamee,totalamt,address,creditcard,deliverydate);
                
         
               // Double totalamount=
                 out.println("<table class='table' border='1px'>");
                 
                 out.println("<tr>");
                 out.println("<th>");
                 out.println("Order Number");
                 out.println("</th>");
                 out.println("<td>");
                 out.println(ordernumber);
                 out.println("</td>");
                 out.println("</tr>");
                 
                 out.println("<tr>");
                 out.println("<th>");
                  out.println("Name");
                 out.println("</th>");
                 out.println("<td>");
                  out.println(usernamee);
                 out.println("</td>");
                 out.println("</tr>");

                 out.println("<tr>");
                 out.println("<th>");
                  out.println("Total Amount");
                 out.println("</th>");
                 out.println("<td>");
                  out.println(totalamt);
                 out.println("</td>");
                 out.println("</tr>");

                 out.println("<tr>");
                 out.println("<th>");
                  out.println("deliverydate");
                 out.println("</th>");
                 out.println("<td>");
                  out.println(deliverydate);
                 out.println("</td>");
                 out.println("</tr>");

                 out.println("<tr>");
                 out.println("<th>");
                 out.println("Address");
                 out.println("</th>");
                 out.println("<td>");
                 out.println(address);
                 out.println("</td>");
                 out.println("</tr>");

                 out.println("<tr>");
                 out.println("<th>");
                 out.println("<form  method = 'get' action = 'cancelorder'>");
	             out.println("<input  type = 'submit' name = 'buy' value = 'cancel order'>");
	             out.println("<input type = 'hidden' name='orderid' value='"+ordernumber+"'>");
	             out.println("</form>");

                 out.println("</th>");
                 out.println("</tr>");

                 out.println("</table>");



               
                out.println("</div>");
                
                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\footer.html"));

    }

}


