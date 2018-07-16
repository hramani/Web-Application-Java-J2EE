import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
//import org.apache.catalina.User;

public class index extends HttpServlet 
{
	public void init() throws ServletException
	{
		productsaxparser productsaxparserobj=null;
        try 
        {


                
                    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            
                    SAXParser saxParser = saxParserFactory.newSAXParser();

                    productsaxparserobj = new productsaxparser();

                    saxParser.parse(new File("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\WEB-INF\\product.xml"), productsaxparserobj);

            
                HashMap<String,product> map = productsaxparserobj.getproductlist();
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection conn = null;
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mybestdeal","root","root");

                Statement stTruncate = conn.createStatement();
                stTruncate.executeUpdate("truncate table product");


                 for(String key : map.keySet()) 
                 {
                    product productobj=map.get(key);
                    String type = productobj.getType();
                    

                    String img = productobj.getImage();
                    String name = productobj.getName();
                    Double price = productobj.getPrice();
                    int id = productobj.getId();
   
                    String insertIntoCustomerRegisterQuery = "INSERT INTO product(id,type,name,price,image) VALUES (?,?,?,?,?);";
            
                    PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
                    
                    pst.setInt(1,id);
                    pst.setString(2,type);
                    pst.setString(3,name);
                    pst.setDouble(4,price);
                    pst.setString(5,img);

                    pst .executeUpdate();
                   // System.out.println("product has been inserted");
                    }
                    }
                    catch(Exception E){}
                
         
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
              //  out.println("<h2>Welcome to Best Deal</h2>");
                out.println("<img src='images\\tablet_laptop.jpg'  alt='smiley face' style='width: 673px; height: 313px; margin-top: 10px;'>");
                out.println("</div>");
                
                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\footer.html"));

    }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{

	    String username = request.getParameter("username");
		String password = request.getParameter("password");
		String usertype = request.getParameter("usertype");

		PrintWriter out = response.getWriter();

		MySqlDataStoreUtilities mysqlObject;
                mysqlObject = new MySqlDataStoreUtilities();
		String msg = null;
            try {
                msg = mysqlObject.insertUser(username,password,usertype);
              
            } catch (Exception E) {
                
            }

				

               

                String hdr = readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\header.html");

         
            	out.println(hdr);

            	

                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\navigationbar.html"));
                out.println(" <div id='content'> ");

                out.println("<h2>"+msg+"</h2>");
                
            
                out.println("</div>");
              
                
                
                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\footer.html"));
	} 
}


