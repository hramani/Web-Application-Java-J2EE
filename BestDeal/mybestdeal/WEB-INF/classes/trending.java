import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.AggregationOutput;
import java.nio.file.Files;

public class trending extends HttpServlet 
{
MongoClient mongo;

public void init() throws ServletException {
        mongo = new MongoClient("localhost", 27017);
}
     
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

                 DB db= mongo.getDB("customerreview");
                DBCollection myreview = db.getCollection("myreview");
                myreview= db.getCollection("myreview");
               //....................................................Query-1...................................................
                
                out.println("<h4 align='center' STYLE='font-weight: bold; color: red;' >Top Five most liked product</h4>");
               
                DBObject sort = new BasicDBObject();
                DBObject limit = new BasicDBObject();
                DBObject orderby=new BasicDBObject();
                orderby=new BasicDBObject("$sort",sort);
                limit=new BasicDBObject("$limit",5);
               
                sort.put("reviewRating",-1);

                AggregationOutput aggregate = myreview.aggregate(orderby,limit);
                int j=0;
                for (DBObject result : aggregate.results()) 
                {
                    BasicDBObject bobj= (BasicDBObject) result;
                    j++;
                    out.println("<h5 align='center' STYLE='font-weight: bold; color: black;' >Review =  "+j+"</h5>");
                    String pname=bobj.getString("productName");
                    String reviewrate= bobj.getString("reviewRating");
                    out.println("Product Name="+pname);
                    out.println("<br>");
                    out.println("Product rating="+reviewrate);
                    out.println("<br>");
                    out.println("<hr>");
                }
 //....................................................Query-2...................................................
                out.println("<h4 align='center' STYLE='font-weight: bold; color: red;' >Top Five Product with zipcode</h4>");
            
               
                DBObject groupFields=new BasicDBObject();
                DBObject group=new BasicDBObject();
              
                DBObject projectFields=new BasicDBObject();
                DBObject project=new BasicDBObject();
               
                orderby=new BasicDBObject("$sort",sort);
                limit=new BasicDBObject("$limit",5);

                sort.put("reviewRating",-1);
         

                 
                
                  groupFields= new BasicDBObject("_id", 0);
                  groupFields.put("count",new BasicDBObject("$sum",1));
                  groupFields.put("_id", "$retailorZip");
                  group = new BasicDBObject("$group", groupFields);
                  
                  projectFields.put("value", "$_id");
                  projectFields.put("reviewRating","$count");
                  project = new BasicDBObject("$project", projectFields);
                 
               

                  aggregate = myreview.aggregate(group,project,orderby,limit);
                  int k=0;
                  for (DBObject result : aggregate.results()) 
                  {
                      BasicDBObject bobj= (BasicDBObject) result;
                      k++;
                      out.println("<h5 align='center' STYLE='font-weight: bold; color: black;' >Review =  "+k+"</h5>");
                      String zipcode=bobj.getString("value");
                      String reviewrate= bobj.getString("reviewRating");
                      out.println("Product zipcode="+zipcode);
                      out.println("<br>");
                      out.println("Total review="+reviewrate);
                      out.println("<br>");
                      out.println("<hr>");
                  }

//....................................................Query-3...................................................
                out.println("<h4 align='center' STYLE='font-weight: bold; color: red;' >Top Five most sold Product</h4>");
                  groupFields= new BasicDBObject("_id", 0);
                  groupFields.put("count",new BasicDBObject("$sum",1));
                  groupFields.put("_id", "$productName");
                  group = new BasicDBObject("$group", groupFields);
                  orderby=new BasicDBObject("$sort",sort);
                  limit=new BasicDBObject("$limit",5);
                  sort.put("count",-1);

                  aggregate = myreview.aggregate(group,orderby,limit);
                  int m=0;
                  for (DBObject result : aggregate.results()) 
                  {
                      BasicDBObject bobj= (BasicDBObject) result;
                      m++;
                      out.println("<h5 align='center' STYLE='font-weight: bold; color: black;' >Review =  "+m+"</h5>");
                      String productname=bobj.getString("_id");
                      String reviewrate= bobj.getString("count");
                      out.println("Product Name="+productname);
                      out.println("<br>");
                      out.println("Total review="+reviewrate);
                      out.println("<br>");
                      out.println("<hr>");
                  }

                  
                 

                out.println("</div>");
                
                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\footer.html"));

 }
}