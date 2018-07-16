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
import static javafx.scene.Cursor.cursor;



public class dataanalyticservlet extends HttpServlet 
{
	MongoClient mongo;
	public void init() throws ServletException
	{
		  mongo = new MongoClient("localhost", 27017);
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
                out.println("<h2>Data Analytic</h2>");

                String productname = request.getParameter("productName");
                

                String productprice  = request.getParameter("pprice");
                String pricecmpr = request.getParameter("ppricecompare");

                String pretailerzip = request.getParameter("pzipcode");
                String pretailercity = request.getParameter("pcity");

                String prating = request.getParameter("previewrating");

                String pcategory  = request.getParameter("productCategory");
                String pretailer  = request.getParameter("pretailername");
               
                String pretailerstate = request.getParameter("pretailerstate");
                String ponsale  = request.getParameter("productonsale");

                String userid  = request.getParameter("puserID");
                String puserage  = request.getParameter("puserAge");
                String pusergender  = request.getParameter("puserGender");
                String puseroccupation = request.getParameter("puserOccupation");

                String pmanufacturename = request.getParameter("pmanuName");
                String prebate  = request.getParameter("manuRebate");
               
                
                
                String pdate = request.getParameter("previewDate");
               


        		
                 DB db= mongo.getDB("customerreview");
                DBCollection myreview = db.getCollection("myreview");
                myreview= db.getCollection("myreview");
               //....................................................Query-1...................................................
                
                out.println("<h4 align='center' STYLE='font-weight: bold; color: red;' >All product with maximum rating</h4>");
               
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
                out.println("<h4 align='center' STYLE='font-weight: bold; color: red;' >Product with zipcode high rating</h4>");
            
               
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
                out.println("<h4 align='center' STYLE='font-weight: bold; color: red;' >display 5 product with maximum reiew</h4>");
                  groupFields= new BasicDBObject("_id", 0);
                  groupFields.put("count",new BasicDBObject("$sum",1));
                  groupFields.put("_id", "$productName");
                  group = new BasicDBObject("$group", groupFields);
               

                  aggregate = myreview.aggregate(group,orderby,limit);
                  int m=0;
                  for (DBObject result : aggregate.results()) 
                  {
                      BasicDBObject bobj= (BasicDBObject) result;
                      m++;
                      out.println("<h5 align='center' STYLE='font-weight: bold; color: black;' >Review =  "+m+"</h5>");
                      productname=bobj.getString("_id");
                      String reviewrate= bobj.getString("count");
                      out.println("Product Name="+productname);
                      out.println("<br>");
                      out.println("Total review="+reviewrate);
                      out.println("<br>");
                      out.println("<hr>");
                  }


                /*

                //...................................................................Query-1.............................................................................
                //.............................................Print the list of all products which is smart-phone.......................................................
                out.println("<h4 align='center' STYLE='font-weight: bold; color: red;' >All the review according to product category</h4>");
                DB db= mongo.getDB("customerreview");
                DBCollection myreview = db.getCollection("myreview");
                myreview= db.getCollection("myreview");
				BasicDBObject searchQuery = new BasicDBObject();
				searchQuery.put("productCategory", pcategory);
		
                DBCursor cursor = myreview.find(searchQuery);
			   


				while(cursor.hasNext()) 
				{
					BasicDBObject obj = (BasicDBObject) cursor.next();
							out.println("<br>");
							out.println(obj.getString("productName"));
							out.println("<br>");
							out.println(obj.getString("reviewRating"));

				} */
            	            	


                
                out.println("</div>");
                
                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\footer.html"));

	}}