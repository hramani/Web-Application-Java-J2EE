 class User
 {
    
    private String username;
    private String userpassword;
    private String usertype;
   
    
    public User()
    {}

    public User(String username,String userpassword,String usertype)
    {
        this.username = username;
        this.userpassword = userpassword;
        this.usertype = usertype;
       
    }
    
   
    
    public String getusername(){
        return this.username;
    }

   
    
    public String getuserpassword(){
        return this.userpassword;
    }
       
  

    public String getusertype()
    {
         return this.usertype;
    }
     
     // create a tostring method to diplay data
     
}