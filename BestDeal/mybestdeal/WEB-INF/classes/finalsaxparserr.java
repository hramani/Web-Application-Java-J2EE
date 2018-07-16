

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;




public class finalsaxparser 
{

    public static void main(String[] args) 
    {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    
        try 
        {
        
        SAXParser saxParser = saxParserFactory.newSAXParser();

        productsaxparser productsaxparserobj = new productsaxparser();

        saxParser.parse(new File("C:\\apache-tomcat-7.0.34\\webapps\\mybestdeal\\WEB-INF\\product.xml"), productsaxparserobj);
       
       
        List<product> productlist = productsaxparserobj.getproductlist();
       
        for(product productobj : productlist) {
           System.out.println(productobj);
          System.out.println(productobj.getType());
      }
    

        } 

        catch (ParserConfigurationException | SAXException | IOException e) 
        {
            e.printStackTrace();
        }

    }

}