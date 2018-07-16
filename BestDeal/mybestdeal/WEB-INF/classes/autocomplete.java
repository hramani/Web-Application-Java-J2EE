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

public class autocomplete extends HttpServlet 
{
	public void init() throws ServletException
	{
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
            PrintWriter out = response.getWriter();
            String searchid=  request.getParameter("id");
            System.out.println("............1.............");
            System.out.println("SEARCH ID is="+searchid);
            try{  
                           
                              
                              StringBuffer sb = new StringBuffer(); 
                              boolean namesAdded = false;
        
                                String action = request.getParameter("action");
                               // String targetId = request.getParameter("id");
                      

                              

                                boolean namesAdded = false;
                                if (action.equals("complete")) 
                                {
                                    if (!searchid.equals(""))
                                    { 
                                        AjaxUtility a=new AjaxUtility();
                                          sb=a.readdata(searchId);
                                           if(sb!=null || !sb.equals(""))
                                           {
                                                                                    
                                            if (name.toLowerCase().startsWith(searchid))
                                            {
                                                
                                                sb.append("<product>");
                                                sb.append("<productName>" + name + "</productName >");
                                                sb.append("</product>");
                                                namesAdded = true;
                                            }
                                        }
                                        }
                                    }

                                    
                                      /*  if(sb!=null || !sb.equals(""))
                                        {
                                            namesAdded=true;
                                        } */
                                        if (namesAdded)
                                        {
                                            response.setContentType("text/xml");
                                            response.setHeader("Cache-Control", "no-cache");
                                            response.getWriter().write("<products>" + sb.toString() + "</products >");
                                        }
                                } 
                                 if (!namesAdded) 
                                {
                                            String name = request.getParameter("productname");
                                            System.out.println("lookup action"+name);
                                            HttpSession session = request.getSession(true);
                                            String username = (String) session.getAttribute("sessionusername"); // session is set
                                        
                                            String hdr = readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\header.html");
                                            
                                            if(username!=null)
                                            {                   
                                                out.println(hdr.replaceAll("guest", username));
                                               
                                            } 
                                            
                                            else 
                                            {
                                                out.println(hdr);
                                                
                                            }

                                           
                                            out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\navigationbar.html"));
                                            out.println(" <div id='content'> ");
                                            out.println("<h2>Serched Product</h2>");
                                            String displayproduct="select * from product where name='"+name+"'";
                                            rs= stmt.executeQuery(displayproduct);
                                            out.println("<table>");
                                             while(rs.next())
                                             {
                                                    String pname=(rs.getString("name"));
                                                    int price=(rs.getInt("price"));
                                                    String image =(rs.getString("image"));

                    
                                                    out.println("<tr>");
                                                    out.println("<td>");
                                                    out.print("<img src='"+image+"' alt='image' height='85' width='85' />");
                                                    out.println("</td>");
                                                    out.println("<td>");
                                                    out.println("Product Name = "+pname+"<br>");
                                                    out.println("Price = "+price);
                                                    out.println("</td>");
                                                    out.println("<td>");
                                                    out.println("<form  method = 'get' action = 'addtocart'>");
                                                    out.println("<input  type = 'submit' name = 'buy' value = 'BUY'>");
                                                    out.println("<input type = 'hidden' name='name' value="+pname+">");
                                                    out.println("<input type = 'hidden' name='price' value="+price+">");
                                                    
                                                    out.println("</form>");

                                                    out.println("<form class = 'submit-button' method = 'get' action = 'writereview'>");
                                                    out.println("<input class = 'submit-button' type = 'submit' name = 'submit' value = 'Write Review'>");
                                                    out.println("<input type = 'hidden' name='productname' value='"+pname+"'>");
                                                    out.println("<input type = 'hidden' name='productprice' value='"+price+"'>");
                                                    out.println("<input type = 'hidden' name='category' value='smartphone'>");
                                                    out.println("<input type = 'hidden' name='retailer' value='sony'>");
                                                    out.println("</form>");

                                                    out.println("<form class = 'submit-button' method = 'get' action = 'viewreview'>");
                                                    out.println("<input class = 'submit-button' type = 'submit' name = 'submit' value = 'View Reviews'>");
                                                    out.println("<input type = 'hidden' name='productname' value='"+pname+"'>");
                                                    out.println("</form>");

                                                   
                                                    out.println("</td>");
                                                    out.println("</tr>");
                                             }
                                            out.println("</table>");
                                            out.println("</div>");
                
                                            out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\footer.html"));
                                }

                }
                catch(Exception e)
                {
                    
                }  

    }
}

