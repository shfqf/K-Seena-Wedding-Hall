public class Customer
{
    private String custName;
    private String email;
    private String phoneNum;
    private String address;
    
    public Customer(String custName, String email, String phoneNum, String address)
    {
        this.custName = custName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.address = address;
    }
    
    //setter method [mutator]
    public void setPerson(String custName, String email, String phoneNum, String address) 
    {
        this.custName = custName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.address = address;
    }

    
    //retriever methods [accecor]
    public String getCustName()
    {
        return custName;
    } 
 
    public String getEmail()
    {
        return email;
    }
 
    public String getPhoneNumber()
    {
        return phoneNum;
    }

    public String getAddress()
    {
        return address;
    }
    
    // Processor method to validate email and phone number
    public boolean validateContactInfo() 
    {
        return email.contains("@") && phoneNum.matches("\\d{10}");
    }
    
    @Override
    public String toString()
    {
        return("Name :............."+custName
        +" \nEmail :.......... " + email
        +" \nContact Number :... " + phoneNum);
    }
}