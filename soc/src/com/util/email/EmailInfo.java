/*
 * 文 件 名:  EmailInfo.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2012-9-10
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.util.email;

/**
 * 邮箱实体类
 * 包括邮箱的一些属性
 * 
 * @author  liuyanxu
 * @version  [版本号, 2012-9-10]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class EmailInfo
{
    private String mailHost;
    
    private String userName;
    
    private String userPassword;
    
    private String mailPort;
    
    private String mailSubject;
    
    private String mailContent;
    
    private String mailFrom;
     
    private String mailTo;
    
    private String mailCopyTo;
    
    private String mailAffixDir;

    private String mailBcc;
    
    public String getMailAffixDir() {
        return mailAffixDir;
    }

    public void setMailAffixDir(String mailAffixDir){
        this.mailAffixDir =mailAffixDir;
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    public String getMailCopyTo() {
        return mailCopyTo;
    }

    public void setMailCopyTo(String mailCopyTo) {
        this.mailCopyTo = mailCopyTo;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getMailHost() {
        return mailHost;
    }

    public void setMailHost(String mailHost) {
        this.mailHost = mailHost;
    }

    public String getMailPort() {
        return mailPort;
    }

    public void setMailPort(String mailPort) {
        this.mailPort = mailPort;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getMailBcc() {
        return mailBcc;
    }

    public void setMailBcc(String mailBcc) {
        this.mailBcc = mailBcc;
    }
}
