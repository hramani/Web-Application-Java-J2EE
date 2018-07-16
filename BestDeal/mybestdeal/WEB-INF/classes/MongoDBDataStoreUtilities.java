import com.mongodb.MongoClient; 
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.*;
import java.util.ArrayList;
import java.util.HashMap;
import static javafx.scene.Cursor.cursor;

public class MongoDBDataStoreUtilities
{
	MongoClient mongo;
   
	MongoDBDataStoreUtilities()
	{
		
		mongo = new MongoClient("localhost", 27017);
	
	}


                                                                

                                                              
        
    public void insertreview(String productname,String pcategory,String productprice,String pretailer,String pretailerzip,String pretailercity,String pretailerstate,String ponsale,String pmanufacturename,String prebate,String userid,String puserage,String pusergender,String puseroccupation,String prating,String pdate,String preview)
	{ 
			
			DB db= mongo.getDB("customerreview");
	
			DBCollection myreview = db.getCollection("myreview");
			BasicDBObject doc = new BasicDBObject("title", "myreview").
			
			append("productName", productname).
			append("productCategory", pcategory).
			append("productPrice", productprice).
			append("retailorName", pretailer).
			append("retailorZip", pretailerzip).																																	
			append("retailorCity", pretailercity).																																	
			append("retailorState", pretailerstate).																																	
			append("productOnSale", ponsale).
			append("manufacturerName", pmanufacturename).
			append("manufacturerRebate", prebate).
			append("userID", userid).
			append("userAge", puserage).																																	
			append("userGender", pusergender).
			append("userOccupation", puseroccupation).																																	
			append("reviewRating", prating).																																	
			append("reviewDate", pdate).																																	
			append("reviewText", preview);
			myreview.insert(doc);
    }

    public ArrayList<Review> selectReview(String productname)
	{
            
                DB db= mongo.getDB("customerreview");
                DBCollection myreview = db.getCollection("myreview");
                myreview= db.getCollection("myreview");
				BasicDBObject searchQuery = new BasicDBObject();
				searchQuery.put("productName", productname);
		
                DBCursor cursor = myreview.find(searchQuery);
			    ArrayList<Review> listReview= new ArrayList<Review>() ; //reviewHashmap.get(obj.getString("productName"));


		while(cursor.hasNext()) 
		{
			BasicDBObject obj = (BasicDBObject) cursor.next();
			System.out.println("while loop");
			Review review=new Review(obj.getString("productName"),obj.getString("productCategory"),obj.getString("productPrice"),obj.getString("retailorName"),obj.getString("retailorZip"),obj.getString("retailorCity"),obj.getString("retailorState"),obj.getString("productOnSale"),obj.getString("manufacturerName"),obj.getString("manufacturerRebate"),obj.getString("userID"),obj.getString("userAge"),obj.getString("userGender"),obj.getString("userOccupation"),obj.getString("reviewRating"),obj.getString("reviewDate"),obj.getString("reviewText"));
            listReview.add(review);

		}
		return listReview;
	}

	public void query1()
	{
		  		DB db= mongo.getDB("customerreview");
                DBCollection myreview = db.getCollection("myreview");
                myreview= db.getCollection("myreview");
               
               
             
               
                DBObject sort = new BasicDBObject();
                DBObject limit = new BasicDBObject();
                DBObject orderby=new BasicDBObject();
                orderby=new BasicDBObject("$sort",sort);
                limit=new BasicDBObject("$limit",5);
               
                sort.put("reviewRating",-1);

                AggregationOutput aggregate = myreview.aggregate(orderby,limit);
                
                for (DBObject result : aggregate.results()) 
                {
                BasicDBObject bobj= (BasicDBObject) result;
                System.out.println(bobj.getString("productName"));
                System.out.println(bobj.getString("reviewRating"));
                }
	}

	public void query2()
	{
				DB db= mongo.getDB("customerreview");
                DBCollection myreview = db.getCollection("myreview");
                myreview= db.getCollection("myreview");
				DBObject sort = new BasicDBObject();
                DBObject limit = new BasicDBObject();
                DBObject orderby=new BasicDBObject();
				DBObject group=new BasicDBObject();
              	DBObject groupFields=new BasicDBObject();
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
                 
               

                  AggregationOutput aggregate  = myreview.aggregate(group,project,orderby,limit);
                  int k=0;
                  for (DBObject result : aggregate.results()) 
                  {
                      BasicDBObject bobj= (BasicDBObject) result;
                      k++;
                     
                      String zipcode=bobj.getString("value");
                      String reviewrate= bobj.getString("reviewRating");
                      System.out.println("Product zipcode="+zipcode);
                      System.out.println("<br>");
                      System.out.println("Total review="+reviewrate);
                      System.out.println("<br>");
                      System.out.println("<hr>");
                  }
	}


	
}		
																
	
