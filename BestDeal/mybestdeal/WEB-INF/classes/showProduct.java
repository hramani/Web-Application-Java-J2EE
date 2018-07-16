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

public class ajaxfunctionProf extends HttpServlet 
{
	public void init() throws ServletException
	{
	}
                
         




	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
            PrintWriter out = response.getWriter();
            try
            {  

             Class.forName("com.mysql.jdbc.Driver").newInstance();
                            Connection conn = null;
                            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mybestdeal","root","root");

                                String getorder = "select name from product ";
                    
                                PreparedStatement checkuserstmt = conn.prepareStatement(getorder);
                

                               
    }
}

