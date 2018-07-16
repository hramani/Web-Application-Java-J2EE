import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class checkout extends HttpServlet 
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

           //   HttpSession session = request.getSession();
				response.setContentType("text/html;charset=UTF-8");
        
        		HttpSession session = request.getSession();
        
        		cart shoppingCart;

        		shoppingCart = (cart) session.getAttribute("cart");
        
		
        
			//	HttpSession session = request.getSession(true);
				String username = (String) session.getAttribute("sessionusername"); // session is set

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
              
            	out.println("<hr>");
            
            	out.println("<hr>");
            	out.println("<h2>Cart</h2>");

            	HashMap<String, Double> items = shoppingCart.getcartitems();
            	Double totalamt = 0.0;
             	out.println("<table class='table' border='1px'>");
           
	            out.println("<tr>");
	            out.println("<th>");
	            out.println("Product");
	            out.println("</th>");
	            out.println("<th>");
	            out.println("price");
	            out.println("</th>");  
	            out.println("<th>");
	            out.println("Action");
	            out.println("</th>");       
	            out.println("</tr>");
	           
	           for (String key: items.keySet())
	            {
	             
	                String itemkey =key;

	                String value = items.get(key).toString();  
	                out.println("<tr>");
	                out.println("<td>");
	                out.println(itemkey);
	                out.println("</td>");
	                out.println("<td>");
	                out.println(value);
	                out.println("</td>");

	    			
	    			totalamt=totalamt + Double.parseDouble(value);
	    		//	out.println(totalamt);

	                out.println("<td>"); 
	                out.println("<form  method = 'get' action = 'deletefromcart'>");
	                out.println("<input  type = 'hidden' name = 'name' value = '"+key+"'>");

	                out.println("<input  type = 'submit'  value = 'delete'>");
	                out.println("</form>");
	                out.println("</td>");
	                out.println("</tr>");

	            } 
            
          
         
            		out.println("</table>");
            		out.println("<br><hr><br>");

            		out.println("<h3 align='center'>Please fill your personal detail</h3>");
            		//out.println("<legend align='center'>Register your account</legend><br>");

            		out.println("<form id='register' action='showorder' method='get' >");
            		out.println("<table>");

					
					
					

					out.println("<tr>");
					out.println("<th>");
					out.println("<label for='name' >   Your Full Name*   : </label>");
					out.println("</th>");
					out.println("<td>");
					out.println("<input type='text' name='name' id='name' maxlength='50' /><br>");
					out.println("</td>");
					out.println("</tr>");

					out.println("<tr>");
					out.println("<th>");
					out.println("<label for='address'> Address*          :</label>");
					out.println("</th>");
					out.println("<td>");
					out.println("<input type='text' name='address' id='address' maxlength='50' /><br>");
					out.println("</td>");
					out.println("</tr>");

					out.println("<tr>");
					out.println("<th>");
					out.println("<label for='Eid'>   Email Id*         :</label>");
					out.println("</th>");
					out.println("<td>");
					out.println("<input type='email' name='emailid' id='username' maxlength='50' /><br>");

					out.println("</td>");
					out.println("</tr>");


					out.println("<tr>");
					out.println("<th>");
				
					out.println("<label for='phnumber'>Phone Number*:</label>");
					out.println("</th>");
					out.println("<td>");
					out.println("<input type='telephone' name='phnumber'  maxlength='50' /><br>");
					out.println("</td>");
					out.println("</tr>");

					out.println("<tr>");
					out.println("<th>");
					out.println("<label for='ccnumber'>Credit card Number*:</label>");
					out.println("</th>");
					out.println("<td>");
					out.println("<input type='text' name='cardnumber' id='cardnumber' maxlength='50' /><br>");
					out.println("</td>");
					out.println("</tr>");
					out.println("<tr >");
					out.println("<td colspan='2' align='center'>");
				//	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				//	Date date = new Date();
				//	String dateoforder=dateFormat.format(date);




                                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                                        Calendar c = Calendar.getInstance();
                                        c.setTime(new Date()); // Now use today date.
                                        c.add(Calendar.DATE, 10); // Adding 5 days
                                        String deliverydate = sdf.format(c.getTime());
                                       // out.println(output);

                                        Random rand = new Random();

                                        int  ordernumber = rand.nextInt(50) + 1;

                                        
                                       // out.println(ordernumber);


				//	out.println(dateoforder);
                    out.println("<input  type = 'hidden' name = 'deliverydate' value = '"+deliverydate+"'>");
                    out.println("<input  type = 'hidden' name = 'ordernumber' value = '"+ordernumber+"'>");
                    out.println("<input  type = 'hidden' name = 'totalamount' value = '"+totalamt+"'>");
                    
                    //out.println("<input  type = 'hidden' name = 'creditcard' value = '"+totalamt+"'>");
					out.println("<input type='submit' name='confirmorder' value='Confirm Order'>");
					out.println("</td>");
					out.println("</tr>");


					

					
					out.println("</table>");
					out.println("</form>");



                	out.println("</div>");
                
                	out.println(readFile("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\footer.html"));

    }

}


