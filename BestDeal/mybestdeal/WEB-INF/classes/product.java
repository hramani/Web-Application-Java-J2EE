//package ecom;

import java.util.HashMap;

public class product implements java.io.Serializable
{
    Integer id;
    String retailer;
    String image;
    String name;
    String condition;
    double price;
    double discount;
    String type;
   // HashMap<String,Accessory> accessories = new HashMap<String,Accessory>();
    
    public product(Integer id,String type, String image, String name, String condition, double price,double discount)
    {
        this.id=id;
        this.type=type;
        this.image=image;
        this.name=name;
        this.condition=condition;
        this.price=price; 
        this.discount = discount;
       // this.setAccessories(accessories);
    }
     public product(Integer id,String type, String image, String name, double price)
    {
        this.id=id;
        this.type=type;
        this.image=image;
        this.name=name;
      
        this.price=price; 
    
       // this.setAccessories(accessories);
    }
    
    public product(String name)
    {
        this.name=name;
    }

    public product()
    {
        
    }
    
    public Integer getId() 
    {
        return id;
    }
    public void setId(Integer id) 
    {
        this.id = id;
    }

    public String getType() 
    {
        return type;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    
    public String getImage() 
    {
        return image;
    }
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getName() 
    {
        return name;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

     public String getCondition() 
    {
        return condition;
    }
    public void setCondition(String condition) 
    {
        
        this.condition = condition;
    }

     public double getPrice() 
    {
        return price;
    }
    public void setPrice(double price) 
    {
        this.price = price;
    }
    

    public double getDiscount() 
    {
        return discount;
    }

    public void setDiscount(double discount) 
    {
        this.discount = discount;
    }

     public String toString() 
     {
        

        return "Product:: ID="+this.id+"         Name="+this.name+"        Image="+ this.image + "            Price=" + this.price + "           Condition = " + this.condition;        
    }
}

