import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class insertproduct extends HttpServlet 
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

             HttpSession session = request.getSession();
			cart shoppingCart;
          
          shoppingCart = (cart) session.getAttribute("cart");
          // HttpSession session = request.getSession(true);
          String username;
          username = (String) session.getAttribute("sessionusername"); 

				
                String usr = "Guest";
                PrintWriter out = response.getWriter();

                String hdr = readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\header.html");

                if (username != null) 
                {
                	out.println(hdr.replaceAll("guest", usr));
            	} 
            	else 
            	{
            		out.println(hdr);
            	}

                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\navigationbar.html"));
                out.println(" <div id='content' align='center'> ");
                
                out.println("<h3 align='center'>Insert product here</h3><hr>");
               // out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
            		//out.println("<legend align='center'>Register your account</legend><br>");
                
            out.println("<form class = 'submit-button' method = 'post' action = 'insertproduct'> ");            
   out.println("<table>");
   out.println("<tr>");
   out.println("<td>ID</td>  <td><input type='text'  name='pid1'></td>");
   out.println("</tr>");
   out.println("<tr>");

    out.println("<td>Type</td>   <td><input type='text' name='ptype' ></td>");
   out.println("</tr>");
    out.println("<tr>");
     out.println("<td>Name</td><td><input type='text' name='pname'  ></td>");
     out.println("</tr>");
     out.println("<tr>");

     out.println(" <td>Price</td>    <td><input type='text' name='pprice'  ></td>");
      out.println("</tr>");
      out.println("<tr>");
      out.println(" <td>Image</td>   <td><input type='text' name='pimg' ></td>");
     out.println(" </tr>");
       out.println("</table>");
       
     out.println(" <input class = 'submit-button' type = 'submit' name = 'submit' value = 'insert'>");
      
        out.println("</form>");

      			

                out.println("</div>");
                
                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\footer.html"));

    }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	 {

     PrintWriter out = response.getWriter();
     System.out.println("1");

             int id= Integer.parseInt(request.getParameter("pid1"));
             System.out.println("2");
      
        String type=request.getParameter("ptype");
        String name= request.getParameter("pname");
        String x=request.getParameter("pprice");
        System.out.println("X="+x);
        int price= Integer.parseInt(x);
        String img= request.getParameter("pimg");
        System.out.println("3");
         
       
        try
        {

           Connection conn = null;
          PreparedStatement pst=null;
          Class.forName("com.mysql.jdbc.Driver").newInstance();
          conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mybestdeal","root","root");
          System.out.println("hii");
      //    out.println("insert successfully2");
              
          String insertproduct = "INSERT INTO product(id,type,name,price,image) VALUES(?,?,?,?,?)";
          System.out.println("4");
          out.println("insert successfully3");
          pst = conn.prepareStatement(insertproduct);
     //     out.println("insert successfully4");
          System.out.println("5");

          pst.setInt(1,id);
    //      out.println("insert successfully5");
          pst.setString(2,type);
          pst.setString(3,name);
          pst.setInt(4,price);
          pst.setString(5,img);
          pst.execute();

          out.println("insert successfully");
         
         response.sendRedirect("manager");
        
       
       System.out.println("7");
               
      

      }

      catch(Exception E)
      {

      }
        

    }


}


