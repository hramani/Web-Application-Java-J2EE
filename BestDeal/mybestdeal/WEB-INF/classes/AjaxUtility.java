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

public class AjaxUtility
{
	Connection conn = null;
	StringBuffer sb = new StringBuffer();
	

	public AjaxUtility()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mybestdeal","root","root");
		}
	
		catch(Exception e)
		{}
	}


	public static HashMap<String,product> getData()
	{ 
		HashMap<String,product> hm=new HashMap<String,product>();

		
			Statement stmt=conn.createStatement();
			String selectCustomerQuery="select * from product";
			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		
		while(rs.next())
		{
			product p = new product(rs.getString("name"));

			hm.put((rs.getString("productId")), p);
		}

		
		return hm;
	}

public StringBuffer readdata(String searchId)
{
	HashMap<String,product> data;

	data=getData();
	

	
	while (Map.Entry pi : data.entrySet())
	{
		

		product p=(product)pi.getValue();

		if (p.getName().toLowerCase().startsWith(searchId))
		{
			sb.append("<product>");
			sb.append("<productName>" + p.getName() + "</ productName >");
			sb.append("</ product >");
		}
	}
	return sb;
}
}