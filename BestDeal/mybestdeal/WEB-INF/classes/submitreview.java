import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.nio.file.Files;

public class submitreview extends HttpServlet 
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

                String productname = request.getParameter("hname");
                String productprice = request.getParameter("hprice");
                String pcategory = request.getParameter("hcat");
                String pretailer = request.getParameter("hretailer");
                String pretailerzip = request.getParameter("retailerzip");
                String pretailercity = request.getParameter("retailercity");
                String pretailerstate = request.getParameter("retailerstate");
                String ponsale = request.getParameter("onsale");
                String pmanufacturename = request.getParameter("manufacturename");
                String prebate = request.getParameter("rebate");
                String ouserid = request.getParameter("userID");
                String puserage = request.getParameter("userAge");
                String pusergender = request.getParameter("userGender");
                String puseroccupation = request.getParameter("userOccupation");
                String prating = request.getParameter("reviewRating");
                String pdate = request.getParameter("reviewDate");
                String preview = request.getParameter("reviewText");
                
                
                
                MongoDBDataStoreUtilities mongodbobject;
                mongodbobject = new MongoDBDataStoreUtilities();

                mongodbobject.insertreview(productname,pcategory,productprice,pretailer,pretailerzip,pretailercity,pretailerstate,ponsale,pmanufacturename,prebate,ouserid,puserage,pusergender,puseroccupation,prating,pdate,preview);
                
              
                out.println("<h2>Review Successfully added</h2>");
             
                out.println("</div>");
                
                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\footer.html"));

    }
  }