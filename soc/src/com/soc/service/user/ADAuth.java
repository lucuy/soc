package com.soc.service.user;

import gnu.getopt.Getopt;
import gnu.getopt.LongOpt;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sourceforge.jradiusclient.RadiusAttribute;
import net.sourceforge.jradiusclient.RadiusClient;
import net.sourceforge.jradiusclient.RadiusPacket;
import net.sourceforge.jradiusclient.exception.InvalidParameterException;
import net.sourceforge.jradiusclient.exception.RadiusException;
import net.sourceforge.jradiusclient.packets.AccountingRequest;
import net.sourceforge.jradiusclient.packets.ChapAccessRequest;
import net.sourceforge.jradiusclient.packets.PapAccessRequest;
import net.sourceforge.jradiusclient.util.ChapUtil;

public class ADAuth
{
    private transient static Log log = LogFactory.getLog(ADAuth.class);
    
    public static void main(String[] paramArrayOfString)
    {
        String userName = "ceshi"; //用户名称
        String password = "ceshi"; //密码
        String host = "192.168.1.187"; //AD服务器
        String port = "389"; //端口
        String domain = "@sohu.com"; //邮箱的后缀名
        String url = new String("ldap://" + host + ":" + port);
        String user = userName.indexOf(domain) > 0 ? userName : userName + domain;
        Hashtable<String, String> env = new Hashtable<String, String>();
        DirContext ctx;
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, user); //不带邮箱后缀名的话，会报错，具体原因还未探究。高手可以解释分享。
        env.put(Context.SECURITY_CREDENTIALS, password);
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, url);
        try
        {
            ctx = new InitialDirContext(env);
            ctx.close();
            log("验证成功！");
        }
        catch (NamingException err)
        {
            err.printStackTrace();
            log("验证失败！");
        }
    }
    
    public static int Authenticate(String host, String port, String domain, String userName, String password)
    {
        domain = "@" + domain.toLowerCase() + ".com"; //邮箱的后缀名
        String url = new String("ldap://" + host + ":" + port);
        String user = userName.indexOf(domain) > 0 ? userName : userName + domain;
        int ret;
        
        Hashtable<String, String> env = new Hashtable<String, String>();
        DirContext ctx;
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, user); //不带邮箱后缀名的话，会报错，具体原因还未探究。高手可以解释分享。
        env.put(Context.SECURITY_CREDENTIALS, password);
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, url);
        try
        {
            ctx = new InitialDirContext(env);
            ctx.close();
            ret = 0;
        }
        catch (NamingException err)
        {
            ret = 1;
        }
        
        return ret;
    }
    
    private static void log(String paramString)
    {
        log.info("RadiusAuth:" + paramString);
        
    }
}
