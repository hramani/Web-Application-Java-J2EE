import java.util.HashMap;

public class cart 
{
    HashMap<String, Double> cartitems;

    public cart()
    {
     
     cartitems = new HashMap<String, Double>();
      
    }
    
    public HashMap getcartitems()
    {
        return cartitems;
    }
    
    public void addToCart(String itemid, double price)
    {
        cartitems.put(itemid, price);
    }

    public void deleteFromCart(String itemid)
    {
        cartitems.remove(itemid);
    }
     
}