package com.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class EmailAuthenticator   extends   Authenticator   {   
    
    String  userName,password;   
    
    public  EmailAuthenticator(String user,String pass) {   
    	super();
    	
    	userName=new  String(user);
    	password=new  String(pass);
    }   

    public   PasswordAuthentication   getPasswordAuthentication() {
    	return   new   PasswordAuthentication(userName,password);   
    }   
}