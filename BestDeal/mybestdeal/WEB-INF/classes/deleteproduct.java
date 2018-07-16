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

public class deleteproduct extends HttpServlet 
{


	
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	 {

     PrintWriter out = response.getWriter();
   
       
        try
        {

           Connection conn = null;
         
          Class.forName("com.mysql.jdbc.Driver").newInstance();
          conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mybestdeal","root","root");
          int id= Integer.parseInt(request.getParameter("pid"));

        String getproduct = "DELETE From product where id='"+id+"' ";
          
        PreparedStatement pst = conn.prepareStatement(getproduct);
        
        pst.executeUpdate();
        out.println("deletion successfully");

         response.sendRedirect("manager");

      }

      catch(Exception E)
      {

      }
        

    }


}


