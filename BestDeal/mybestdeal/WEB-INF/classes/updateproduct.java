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


public class updateproduct extends HttpServlet 
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
                
                out.println("<h3 align='center'>Update product here</h3><hr>");
             int id= Integer.parseInt(request.getParameter("pid"));

        try{

        Connection conn = null;
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mybestdeal","root","root");
        ResultSet rs = null;
              System.out.println("..fisrt");
        Statement st = null;
        st = conn.createStatement();
   System.out.println("second");
            rs = st.executeQuery("SELECT * FROM product WHERE id='"+id+"'"); 

System.out.println("..third");
         while(rs.next())
                {
                  

                                            int idd=rs.getInt("id");
                                            String type=rs.getString("type");

                                            String name=rs.getString("name");
                                            Double price=rs.getDouble("price");
                                            String image =rs.getString("image");
                                            System.out.println("fourth");
 out.println("<form class = 'submit-button' method = 'post' action = 'updateproduct'> ");            
   out.println("<table>");
   out.println("<tr>");
   out.println("<td>ID</td>  <td><input type='text'  name='pid1' value='"+idd+"'></td>");
   out.println("</tr>");
   out.println("<tr>");

    out.println("<td>Type</td> <td><input type='text' name='ptype' value='"+type+"'></td>");
    out.println("</tr>");
    out.println("<tr>");
     out.println("<td>Name</td><td><input type='text' name='pname' value='"+name+"' ></td>");
     out.println("</tr>");
     out.println("<tr>");

      out.println("<td>Price</td>  <td><input type='text' name='pprice' value='"+price+"' ></td>");
      out.println("</tr>");
      out.println("<tr>");
       out.println("<td>Image</td>   <td><input type='text' name='pimg' value='"+image+"' ></td>");
       out.println("</tr>");
       out.println("</table>");
       
    out.println("<input class = 'submit-button' type = 'submit' name = 'submit' value = 'Update'>");
    out.println("<input type = 'hidden' name='dbid' value='"+idd+"'>");
    out.println("</form>");
 }}
                catch(Exception E)
                {}
      			

                out.println("</div>");
                
                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\footer.html"));

    }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	 {

        PrintWriter out = response.getWriter();
        int id= Integer.parseInt(request.getParameter("pid1"));
        int dbid= Integer.parseInt(request.getParameter("dbid"));
        String type=request.getParameter("ptype");
        String name= request.getParameter("pname");
        String x=request.getParameter("pprice");
       
        int price= Integer.parseInt(x);
        String img= request.getParameter("pimg");
         

        try{

            Connection conn = null;
            Statement stmt = null;
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mybestdeal","root","root");
            stmt = conn.createStatement();
            String updateproduct = "UPDATE product SET id="+id+", type='"+type+"', name='"+name+"', price="+price+", image='"+img+"' WHERE id="+dbid+" ";
            System.out.println("QUERY="+updateproduct);
            stmt.executeUpdate(updateproduct);            
            out.println("update successfully");
            response.sendRedirect("manager");
        }
        catch(Exception E)
        {}


    }


}


