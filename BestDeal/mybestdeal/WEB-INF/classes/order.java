 class order
 {
    private int orderid;
    private String username;
    private Double totalamt;
    private String address;
    private int creditcard;
    private String deliverydate;

    
    public order()
    {}

    public order(int orderid, String username,Double totalamt,String address,int creditcard,String deliverydate)
    {
        this.orderid = orderid;
        this.username = username;
        this.totalamt = totalamt;
        this.address = address;
        this.creditcard = creditcard;
        this.deliverydate= deliverydate;
    }
    
    public int getorderid(){
        return this.orderid;
    }
    
    public String getusername(){
        return this.username;
    }

    public Double gettotalamount(){
        return this.totalamt;
    }
    
    public String getaddress(){
        return this.address;
    }
       
    public int getcardnumber(){
        return this.creditcard;
    }

    public String getdeliverydate(){
        return this.deliverydate;
    }

    public String ToString()
    {
         return this.orderid+" "+this.username+" "+this.totalamt+" "+this.address+" "+this.creditcard+" "+this.deliverydate;
    }
     
     // create a tostring method to diplay data
     
}