import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

public class viewreview extends HttpServlet 
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


                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\navigationbar.html"));
                out.println(" <div id='content'> ");
               
                String productname = request.getParameter("productname");
               // out.println(productname);
                MongoDBDataStoreUtilities mongodbobject;
                mongodbobject = new MongoDBDataStoreUtilities();

                 ArrayList<Review> listReview = mongodbobject.selectReview(productname);
                int j=0;
                for(Review rv : listReview)
                {
                   j++;
                  out.println("<h4 align='center' STYLE='font-weight: bold; color: black;' >Review  "+j+"</h4>");
                  out.println("Product Name="+rv.getproductname());
                  out.println("<br>");
                  out.println("Product Price="+rv.getproductprice());
                  out.println("<br>");
                  out.println("Product Category="+ rv.getpcategory());
                  out.println("<br>");
                  out.println("Retailer="+ rv.getpretailer());
                  out.println("<br>");
                   out.println("Retailer Zip="+ rv.getpretailerzip());
                  out.println("<br>");
                  out.println("Retailer city"+ rv.getpretailercity());
                  out.println("<br>");
                  out.println("Retailer state"+ rv.getpretailerstate());
                  out.println("<br>");
                  out.println("Product on Sale?"+ rv.getponsale());
                  out.println("<br>");
                  out.println("Manufacturer Name"+ rv.getpmanufacturename());
                  out.println("<br>");
                  out.println("Manufacturer Rebate?"+ rv.getprebate());
                  out.println("<br>");
                  out.println("User Id =" + rv.getouserid());
                  out.println("<br>");
                   out.println("User Age="+ rv.getpuserage());
                  out.println("<br>");
                  out.println("User Gender="+ rv.getpusergender());
                  out.println("<br>");
                  out.println("User Occupation="+ rv.getpuseroccupation());
                  out.println("<br>");
                  out.println("Product Rating="+rv.getprating());
                  out.println("<br>");
                  out.println("Review date="+rv.getpdate());
                  out.println("<br>");
                  out.println("Review Text="+ rv.getpreview());
                  out.println("<br>");

                  out.println("<hr>");
                

                }
                out.println("</div>");
                
                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\footer.html"));

 }
}