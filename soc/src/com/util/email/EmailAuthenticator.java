/*
 * 文 件 名:  EmailAuthenticator.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2012-9-10
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.util.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  liuyanxu
 * @version  [版本号, 2012-9-10]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class EmailAuthenticator extends  Authenticator
{
    String  userName,password;
    
    public  EmailAuthenticator(String   user,String   pass) {   
        super();
        
        userName=new   String(user);
        password=new   String(pass);
    }
    
    public   PasswordAuthentication   getPasswordAuthentication() {
        return   new   PasswordAuthentication(userName,password);   
    }   
}
