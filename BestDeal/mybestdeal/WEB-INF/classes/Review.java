 public class Review
 {
                public String productname; 
                public String productprice;
                public String pcategory;
                public String pretailer;
                public String pretailerzip;
                public String pretailercity;
                public String pretailerstate;
                public String ponsale;
                public String pmanufacturename;
                public String prebate;
                public String ouserid;
                public String puserage;
                public String pusergender;
                public String puseroccupation;
                public String prating;
                public String pdate;
                public String preview;
    
    public Review()
    {}

    public Review(String productname,String pcategory,String productprice,String pretailer,String pretailerzip,String pretailercity,String pretailerstate,String ponsale,String pmanufacturename,String prebate,String userid,String puserage,String pusergender,String puseroccupation,String prating,String pdate,String preview)
    {
                this.productname=productname; 
                this.productprice=productprice;
                this.pcategory=pcategory;
                this.pretailer=pretailer;
                this.pretailerzip=pretailerzip;
                this.pretailercity=pretailercity;
                this.pretailerstate=pretailerstate;
                this.ponsale=ponsale;
                this.pmanufacturename=pmanufacturename;
                this.prebate=prebate;
                this.ouserid=userid;
                this.puserage=puserage;
                this.pusergender=pusergender;
                this.puseroccupation=puseroccupation;
                this.prating=prating;
                this.pdate=pdate;
                this.preview=preview;
    }
    
    
    public String getproductname(){
        return this.productname;
    }
    public String getproductprice(){
        return this.productprice;
    }
    public String getpcategory(){
        return this.pcategory;
    }
    public String getpretailer(){
        return this.pretailer;
    }
    public String getpretailerzip(){
        return this.pretailerzip;
    }
    public String getpretailercity(){
        return this.pretailercity;
    }
    public String getpretailerstate(){
        return this.pretailerstate;
    }
    public String getponsale(){
        return this.ponsale;
    }
    public String getpmanufacturename(){
        return this.pmanufacturename;
    }
    public String getprebate(){
        return this.prebate;
    }
    public String getouserid(){
        return this.ouserid;
    }
    public String getpuserage(){
        return this.puserage;
    }
    public String getpusergender(){
        return this.pusergender;
    }
    public String getpuseroccupation(){
        return this.puseroccupation;
    }
    public String getprating(){
        return this.prating;
    }
    public String getpdate(){
        return this.pdate;
    }
    public String getpreview(){
        return this.preview;
    }

   

   /* public String ToString()
    {
         return this.orderid+" "+this.username+" "+this.totalamt+" "+this.address+" "+this.creditcard;
    }*/
     
     // create a tostring method to diplay data
     
}