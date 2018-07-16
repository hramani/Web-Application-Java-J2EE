import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
//import org.apache.catalina.User;

public class updateorder2 extends HttpServlet 
{
    public void init() throws ServletException
    {
        
    } // Get a set of the entries


    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
    {
       PrintWriter out = response.getWriter();
        System.out.println("update successfully1");

                int oid= Integer.parseInt(request.getParameter("oid"));
       System.out.println("update successfully2"+oid);
                String ouname=request.getParameter("ouname");
                  System.out.println("update successfully3"+ouname);


                System.out.println("update successfully3");
                String cc=request.getParameter("occ");
              
                String dd= request.getParameter("odd");
                System.out.println("update successfully4");
                 System.out.println("value from updateorde"+oid+ouname+cc+dd);
                  
                Connection conn = null;
                
                Statement stmt =null;
          
            System.out.println("update successfully2");

                
        try{

      
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mybestdeal","root","root");
        stmt = conn.createStatement();
         System.out.println("update successfully3");
            
        
        String updateproduct = "UPDATE customerorder SET orderid="+oid+",  username='"+ouname+"',  creditcard='"+cc+"', deliverydate='"+dd+"' WHERE orderid='"+oid+"' ";
        System.out.println("QUERY="+updateproduct);
        stmt.executeUpdate(updateproduct); 
        
        out.println("update successfully");

  

      }
      catch(Exception E)
      {}

        
        
      RequestDispatcher rd = request.getRequestDispatcher("salseman");
        
      rd.forward(request, response);

             

    }
}