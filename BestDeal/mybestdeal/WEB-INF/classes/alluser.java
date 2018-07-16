//package ecom;

import java.util.HashMap;

public class alluser implements java.io.Serializable
{
    
    String username;
    String password;
    String usertype;
   
   // HashMap<String,Accessory> accessories = new HashMap<String,Accessory>();
    
    public alluser(String username, String password, String usertype)
    {
        this.username=username;
        this.password=password;
        this.usertype=usertype;
    }
    
    public alluser()
    {
        
    }
    
    public String getusername() 
    {
        return username;
    }
    public void setusername(String username) 
    {
        this.username = username;
    }

    
    public String getpassword() 
    {
        return password;
    }
    public void setpassword(String password) 
    {
        this.password = password;
    }

    public String getusertype() 
    {
        return usertype;
    }
    public void setusertype(String usertype) 
    {
        this.usertype = usertype;
    }


}

