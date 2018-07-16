
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class MySqlDataStoreUtilities
{
	Connection conn = null;
	public String msg;

	public MySqlDataStoreUtilities()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mybestdeal","root","root");
		}
	
		catch(Exception e)
		{}
	}

	public HashMap<Integer,order> DisplayOrder(String currentusername) throws SQLException
		{
			HashMap<Integer,order> map = new HashMap<Integer,order>();
	        
	        Statement st = null;
	        ResultSet rs = null;
	    
	       // order orderobject;
	        
	        try{
		            st = conn.createStatement();
		            rs = st.executeQuery("SELECT * FROM customerorder WHERE username='"+currentusername+"'"); 
		           
		            while(rs.next())
		            {
		            	
		            	System.out.println("success");
		                int orderid = rs.getInt("orderid");
		                String username = rs.getString("username");
		                double totalamt = rs.getDouble("totalamount");
		                String address = rs.getString("address");
		                int creditcard = rs.getInt("creditcard");
		                String deliverydate = rs.getString("deliverydate");
		                
		                order orderobject;
		                orderobject = new order(orderid, username,totalamt,address,creditcard,deliverydate);
		                
		                // set data in the hashmap
		                map.put(orderid, orderobject);
			  
			    	}
		    	}
		    	catch(Exception ex)
		    	{
            		ex.printStackTrace();
        		}
		return map;
        } 

        public HashMap<String,User> DisplayAllUser() throws SQLException
        {
			HashMap<String,User> umap = new HashMap<String,User>();
	        
	        Statement st11 = null;
	        ResultSet rs11 = null;
	    
	       // order orderobject;
	        
	        try{
		            st11 = conn.createStatement();
		            rs11 = st11.executeQuery("SELECT * FROM registration WHERE usertype='customer'"); 
		           
		            while(rs11.next())
		            {
		            	
		            	System.out.println("success");
		               
		                String username = rs11.getString("username");
		                String userpassword = rs11.getString("userpassword");
		                String usertype = rs11.getString("usertype");
		                User userobject;
		                userobject = new User(username,userpassword,usertype);
		              
		                umap.put(username, userobject);
			  
			    	}
		    	}
		    	catch(Exception ex)
		    	{
            		ex.printStackTrace();
        		}
		return umap;
        } 
        
        public HashMap<Integer,order> DisplayAllOrder() throws SQLException
		{
			HashMap<Integer,order> map = new HashMap<Integer,order>();
	        
	        Statement st = null;
	        ResultSet rs = null;
	    
	       // order orderobject;
	        
	        try{
		            st = conn.createStatement();
		            rs = st.executeQuery("SELECT * FROM customerorder"); 
		           
		            while(rs.next())
		            {
		            	
		            	//
		            	System.out.println("success");
		                int orderid = rs.getInt("orderid");
		                String username = rs.getString("username");
		                double totalamt = rs.getDouble("totalamount");
		                String address = rs.getString("address");
		                int creditcard = rs.getInt("creditcard");
		                String deliverydate= rs.getString("deliverydate");
		                
		                order orderobject;
		                orderobject = new order(orderid, username,totalamt,address,creditcard,deliverydate);
		                
		                // set data in the hashmap
		                map.put(orderid, orderobject);
			  
			    	}
		    	}
		    	catch(Exception ex)
		    	{
            		ex.printStackTrace();
        		}
		return map;
        } 

	/* public HashMap<Integer,order> Insertuserr() throws SQLException
		{
			HashMap<Integer,order> map = new HashMap<Integer,order>();
	        
	        Statement st = null;
	        ResultSet rs = null;
	    userr userobject;
	       
		                userobject = new userr(username,passwword);
		                
		                // set data in the hashmap
		                map.put(orderid, userobject);
	       // order orderobject;
	        
	        try{
		            st = conn.createStatement();
		          String insertIntoCustomerRegisterQuery = "INSERT INTO registration(userobject) VALUES (?,?,?);";
					String checkqry = "SELECT username From registration ";
					PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
					PreparedStatement pstcheckqry = conn.prepareStatement(checkqry);

					pst.setString(1,userobject);
				           
		           
		                
		             
			  
			    	
		    	}
		    	catch(Exception ex)
		    	{
            		ex.printStackTrace();
        		}
		return map;
        } */
	public  String insertUser(String username,String password,String usertype) throws SQLException
	{	
        ResultSet rs = null;
        Boolean userexist= false;

		
		
			
			String insertIntoCustomerRegisterQuery = "INSERT INTO registration(username,userpassword,usertype) VALUES (?,?,?);";
			String checkqry = "SELECT username From registration ";
			PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
			PreparedStatement pstcheckqry = conn.prepareStatement(checkqry);

			pst.setString(1,username);
			pst.setString(2,password);
			pst.setString(3,usertype);

			rs=pstcheckqry.executeQuery();
			while (rs.next()) 
                        {
                            String exisiting_username=(rs.getString("username"));

                            if(username.equals(exisiting_username))
                            {
                            	msg= "user name is already exist";
                            	userexist = true;
                            }

                           
                        }
                      if(!userexist)
                      {
                      	  pst.execute();
                          msg= "User is successfully registered";
                          return msg;   
                      }
               
                
                return msg;
        }
        int i=0;
		public  int getUser(String username,String password) throws SQLException
		{
				System.out.println(username);
				System.out.println(password);
		
		        
                ResultSet rs = null;
            

		//System.out.println(username+"-"+password+"-"+usertype);
				
					
					String checkuser = "SELECT username,userpassword From registration ";
					
					PreparedStatement checkuserstmt = conn.prepareStatement(checkuser);
				

					rs=checkuserstmt.executeQuery();
					
					while (rs.next()) 
                    {
                                            String exisiting_username=(rs.getString("username"));
                                            String upwd=(rs.getString("userpassword"));


                                            if(username.equals(exisiting_username) && password.equals(upwd) )
                                            {
                                            	i=1;
                                            }
                                            else
                                            {
                                            	i=0;
                                            }
                                            
			   		}
			   	
			   	return i;
		}
		public  String checkUser(String username,String password) throws SQLException
		{
				System.out.println(username);
				System.out.println(password);
				
		        
                ResultSet rs = null;
                String msg=null;
                Statement stmt = null;
            	Boolean userexist =false;

			
				
					
					String checkuser = "SELECT * From registration ";
					stmt = conn.createStatement();		
							
				
				

					rs=stmt.executeQuery(checkuser);	
					while(rs.next())
					{
						  
                            String exisiting_username=(rs.getString("username"));
                            String existingpwd=(rs.getString("userpassword"));

                            if(username.equals(exisiting_username) && password.equals(existingpwd) )
                            {
                            	msg=username;
                            	userexist = true;
                            }

                           
                    }
                      if(!userexist)
                      {
                      	 
                          msg= "User id or password is wrong";
                          return msg;   
                      }
								
				
			   	
			   	return msg;
		} 

		// store customer order into database
		public void insertOrder(int orderId,String userName,Double totalamount,String address,String creditcard,String deliverydate)
		{ 
			try
			{
				
				String insertIntoCustomerOrderQuery = "INSERT INTO customerorder(orderid,username,totalamount,address,creditcard,deliverydate) VALUES (?,?,?,?,?,?);";
				PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);

				pst.setInt(1,orderId);
				pst.setString(2,userName);
				pst.setDouble(3,totalamount);
				pst.setString(4,address);
				pst.setString(5,creditcard);
				pst.setString(6,deliverydate);
				pst.execute();
			}
			catch(Exception e)
			{}
		}

		public void deleteOrder(int orderId)
		{ 
			try
			{
				System.out.println("delete query is here");
				Statement stmt = null;
				String deleteorder = "delete from customerorder where orderid ="+orderId;
				stmt = conn.createStatement();
				
				stmt.executeUpdate(deleteorder);
				
			}
			catch(Exception e)
			{}
		}

	

		public HashMap<String,product> getData() throws SQLException
		{
			HashMap<String,product> prohashmap =new HashMap<String,product>();

			Statement st1 = null;
		
			ResultSet rss = null;
			product p ;

			try{
					st1 = conn.createStatement(); 
					rss = st1.executeQuery("select * from product");
					
				           
				            while(rss.next())
				            {
				            	
				            	
				                int id = rss.getInt("id");
				                String type = rss.getString("type");
				                double price = rss.getDouble("price");
				                String name = rss.getString("name");
				                String image = rss.getString("image");

				                p= new product(id,type,image,name,price);
				                prohashmap.put(name,p);

				            }
				            
		        }
		        catch(Exception E)
		        {}
		        return prohashmap;
		           
		}
		
}

		               


		


