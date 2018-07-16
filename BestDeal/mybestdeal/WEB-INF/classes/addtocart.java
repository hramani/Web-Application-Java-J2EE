import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.nio.file.Files;
import java.util.HashMap;

public class addtocart extends HttpServlet 
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
                    //  e.printStackTrace();
                    }
                    return "";
            
                }

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
    {

           //   HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();
        
        cart shoppingCart;

        shoppingCart = (cart) session.getAttribute("cart");

        if(shoppingCart == null)
        {
          shoppingCart = new cart();
          session.setAttribute("cart", shoppingCart);
        }

        String pname = request.getParameter("name");
        
        double pprice = Double.parseDouble(request.getParameter("price"));

        shoppingCart.addToCart(pname, pprice);

        session.setAttribute("cart", shoppingCart);
                

                

          //      HttpSession session = request.getSession(true);
                String username = (String) session.getAttribute("sessionusername"); // session is set

           
                String msg;
                String hdr = readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\header.html");
                
                if(session!=null)
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
                
                out.println("<h1>Item successfully added </h1>");
                out.println("<form action='index'>Continue shopping...<input type='submit' value='add more item'></form>");
                out.println("<hr>");
                out.println("<br>");
                out.println("<h2>Cart</h2>");

            HashMap<String, Double> items = shoppingCart.getcartitems();


            out.println("<table class='table' border='1px'>");
           
            out.println("<tr>");

            out.println("<th>");
            out.println("Product");
            out.println("</th>");

            out.println("<th>");
            out.println("price");
            out.println("</th>");

           

            out.println("<th>");
            out.println("Action");
            out.println("</th>");

            out.println("</tr>");

            
            for (String key: items.keySet())
            {
                out.println("<tr>");
                out.println("<td>");
                String itemkey =key;

                String value = items.get(key).toString();  
                out.println(itemkey);
                out.println("</td>");

                out.println("<td>");
                out.println(value);
                out.println("</td>");

               

                out.println("<td>");
                out.println("<form  method = 'get' action = 'deletefromcart'>");
                out.println("<input  type = 'submit' name = 'buy' value = 'Remove Item'>");
                out.println("<input type = 'hidden' name='productname' value="+itemkey+">");
                out.println("</form>");
                out.println("</td>");
                out.println("</tr>");
            } 

                out.println("</table>");
                out.println("<form  method = 'get' action = 'checkout'>");
                out.println("<input  type = 'submit' name = 'buy' value = 'Checkout'>");
                out.println("</form>");
                out.println("</div>");
                
                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\footer.html"));

    }

}


