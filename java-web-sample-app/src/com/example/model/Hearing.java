package com.example.model;  
  
public class Hearing {  
    private String customerName;  
    private String content;  
    private String date;  
  
    // ゲッタ・セッタ  
    public String getCustomerName() {  
        return customerName;  
    }  
    public void setCustomerName(String customerName){  
        this.customerName = customerName;  
    }  
    public String getContent(){  
        return content;  
    }  
    public void setContent(String content){  
        this.content = content;  
    }  
    public String getDate(){  
        return date;  
    }  
    public void setDate(String date){  
        this.date = date;  
    }  
}
