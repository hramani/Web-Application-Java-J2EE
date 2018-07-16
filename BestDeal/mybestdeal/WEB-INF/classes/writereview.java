import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class writereview extends HttpServlet 
{
	public void init() throws ServletException
	{
		
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
                out.println("<h2>Write Review</h2>");
                String productName= request.getParameter("productname");
                double productprice = Double.parseDouble(request.getParameter("productprice"));
                String productcat= request.getParameter("category");
                String productretailer= request.getParameter("retailer");
                //..........................................................Review Form............................................................................................................
                out.println("<h1> Write Review for: "+ productName+ "</h1>");
                                                out.println("<form style='font-color:black;' method='get' action='submitreview'>");

                                                out.println("<table>");
                                                out.println("<tr>");
                                                out.println("<td> Product Name:</td>" );
                                                out.println("<td>"+productName+"</td>" );
                                                out.println("<td><input type = 'hidden' name='hname' value='"+productName+"'></td>" );
                                                out.println("</tr>");

                                                out.println("<tr>");
                                                out.println("<td> Product price:</td>" );
                                                out.println("<td>"+productprice+"</td>" );
                                                out.println("<td><input type = 'hidden' name='hprice' value='"+productprice+"'></td>" );
                                                out.println("</tr>");

                                                out.println("<tr>");
                                                out.println("<td> Product Category:</td>" );
                                                out.println("<td>"+productcat+"</td>" );
                                                out.println("<td><input type = 'hidden' name='hcat' value='"+productcat+"'></td>" );
                                                out.println("</tr>");

                                              

                                                out.println("<tr>");
                                                out.println("<td> Retailer Name:</td>" );
                                                out.println("<td>"+productretailer+"</td>" );
                                                out.println("<td><input type = 'hidden' name ='hretailer' value='"+productretailer+"'></td>" );
                                                out.println("</tr>");

                                                out.println("<tr>");
                                                out.println("<td> Retailer Zip: </td>" );
                                                out.println("<td><input type = 'text' name ='retailerzip' value=''></td>" );
                                               
                                                out.println("</tr>");

                                                out.println("<tr>");
                                                out.println("<td> Retailer city: </td>" );
                                                out.println("<td><input type = 'text' name ='retailercity' value=''></td>" );
                                               
                                                out.println("</tr>");

                                                out.println("<tr>");
                                                out.println("<td> Retailer state: </td>" );
                                                out.println("<td><input type = 'text' name ='retailerstate' value=''></td>" );
                                               
                                                out.println("</tr>");

                                                out.println("<tr>");
                                                out.println("<td> Product on Sale </td>" );
                                                out.println("<td><input type='radio' name='onsale' value='yes'>YES <input type='radio' name='onsale' value='no'>NO</td> ");
                                               
                                                out.println("</tr>");

                                                out.println("<tr>");
                                                out.println("<td> Manufacture Name: </td>" );
                                                out.println("<td><input type = 'text' name ='manufacturename' value=''></td>" );
                                                String manufacturename=request.getParameter("manufacturename");
                                                out.println("<td><input type = 'hidden' name ='manufacturename' value='"+manufacturename+"'></td>" );
                                                out.println("</tr>");

                                                out.println("<tr>");
                                                out.println("<td> Manufacture Rebate </td>" );
                                                out.println("<td><input type='radio' name='rebate' value='yes'>YES <input type='radio' name='rebate' value='no'>NO </td>");              
                                   
                                                out.println("</tr>");


                                                out.println("<tr>");
                                                out.println("<td>User ID:</td>" );
                                                out.println("<td><input type = 'text' name = 'userID' value="+username+" /> </td>" );
                                                out.println("</tr>");

                                                out.println("<tr>");
                                                out.println("<td>User Age:</td>" );
                                                out.println("<td><input type = 'text' name = 'userAge' size = 10 required /> </td>" );
                                                out.println("</tr>");



                                                out.println("<tr>");
                                                out.println("<td>User Gender:</td>" );
                                                out.println("<td><input type = 'radio' name = 'userGender' value = 'Male'/>Male<input type = 'radio' name = 'userGender' value = 'Female'/>Female</td>");
                                                out.println("</tr>");



                                                out.println("<tr>");
                                                out.println("<td>User Occupation:</td>" );
                                                out.println("<td><input type = 'text' name = 'userOccupation' size = 20 required /> </td>" );
                                                out.println("</tr>");

                                                out.println("<tr>");
                                                out.println("<td>Review Rating:</td>");
                                                out.println("<td><select name='reviewRating'><option value='1' selected>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></td>");
                                                out.println("</tr>");

                                                out.println("<tr>");
                                                out.println("<td>Review Date:</td>" );
                                                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                                                Calendar c = Calendar.getInstance();
                                                c.setTime(new Date()); // Now use today date.
                                                 // Adding 5 days
                                                String reviewdate = sdf.format(c.getTime());
                                                out.println("<td><input type = 'text' name = 'reviewDate' value="+reviewdate+" readonly /> </td>" );
                                                out.println("</tr>");

                                                out.println("<tr>");
                                                out.println("<td>Review Text:</td>" );
                                                out.println("<td><textarea name='reviewText' rows='4' cols='50'> </textarea></td>" );
                                                out.println("</tr>");

                                                out.println("<tr>");
                                                out.println("<td>Submit the Review:</td>" );
                                                out.println("<td><input type ='submit' value = 'Submit'/> </td>" );
                                                out.println("</tr>");

                                                out.println("</table>");
                                                out.println("</form>");

               
                out.println("</div>");
                
                out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\footer.html"));

    }
  }
