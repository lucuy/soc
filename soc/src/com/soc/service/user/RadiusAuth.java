package com.soc.service.user;

import gnu.getopt.Getopt;
import gnu.getopt.LongOpt;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Iterator;

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

public class RadiusAuth
{
    private transient static Log log = LogFactory.getLog(RadiusAuth.class);
    
    private static String getUsage()
    {
        return "usage: TestRadiusClient -s RadiusServerIp -S sharedSecret -u UserName -p Password [--authPort=1812] [--acctPort=1813]";
    }
    
    public static void main(String[] paramArrayOfString)
    {
        /*
        int i = 1812;
        int j = 1813;
        String str1 = "192.168.1.66";
        String str2 = null;
        String str3 = null;
        String str4 = null;
        StringBuffer localStringBuffer = new StringBuffer();
        LongOpt[] arrayOfLongOpt = { new LongOpt("authPort", 1, localStringBuffer, 1), new LongOpt("acctPort", 1, localStringBuffer, 2) };
        Getopt localGetopt = new Getopt("TestRadiusClient", paramArrayOfString, "s:S:u:p:", arrayOfLongOpt, false);
        localGetopt.setOpterr(true);
        int k;
        while ((k = localGetopt.getopt()) != -1)
          switch (k)
          {
          case 115:
            str1 = localGetopt.getOptarg();
            //System.out.print("IP Address: "+ str1);
            break;
          case 83:
            str2 = localGetopt.getOptarg();
            //System.out.print(str2);
            break;
          case 117:
        	str3 = localGetopt.getOptarg();
        	//System.out.print(str3);
        	break;
          case 112:
        	str4 = localGetopt.getOptarg();
        	//System.out.print(str4);
        	break;
          case 1:
            i = new Integer(localStringBuffer.toString()).intValue();
            break;
          case 2:
            j = new Integer(localStringBuffer.toString()).intValue();
            break;
          case 63:
            break;
          default:
            System.err.println(getUsage());
          }
        RadiusClient localRadiusClient = null;
        try
        {
          localRadiusClient = new RadiusClient(str1, i, j, str2.trim());
        }
        catch (RadiusException localRadiusException)
        {
          log(localRadiusException.getMessage());
          log(getUsage());
          System.exit(4);
        }
        catch (InvalidParameterException localInvalidParameterException)
        {
          log("Unable to create Radius Client due to invalid parameter!");
          log(localInvalidParameterException.getMessage());
          log(getUsage());
          System.exit(5);
        }
        ChapUtil localChapUtil = new ChapUtil();
        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int rtn = basicAuthenticate(localRadiusClient, localChapUtil, localBufferedReader, str3, str4);
        if (rtn == 1)
        	//System.out.print("Success!");
        //advAuthenticate(localRadiusClient, localChapUtil, localBufferedReader);
        
        
        int rtn = WebAuthenticate("192.168.1.86", "1812", "123", "chap", "radius", "radius123");
        if (rtn != 0)
            System.out.print("Success!");
        else
            System.out.print("Fail!");
        */
    }
    
    public static int WebAuthenticate(String strIP, String strPort, String strSecret, String strAuthType,
        String strUser, String strPassword)
    {
        int i = Integer.parseInt(strPort);
        int j = 1813;
        
        RadiusClient localRadiusClient = null;
        try
        {
            localRadiusClient = new RadiusClient(strIP, i, j, strSecret.trim());
        }
        catch (RadiusException localRadiusException)
        {
            log(localRadiusException.getMessage());
            log(getUsage());
            
        }
        catch (InvalidParameterException localInvalidParameterException)
        {
            log("Unable to create Radius Client due to invalid parameter!");
            log(localInvalidParameterException.getMessage());
            log(getUsage());
            
        }
        ChapUtil localChapUtil = new ChapUtil();
        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int rtn =
            basicAuthenticate(localRadiusClient, localChapUtil, localBufferedReader, strUser, strPassword, strAuthType);
        return rtn;
        //if (rtn == 1)
        ////System.out.print("Success!");
        //advAuthenticate(localRadiusClient, localChapUtil, localBufferedReader);
    }
    
    private static int basicAuthenticate(RadiusClient paramRadiusClient, ChapUtil paramChapUtil,
        BufferedReader paramBufferedReader, String UserName, String Password, String AuthType)
    {
        int rtn = 0;
        try
        {
            int i = 0;
            int j = 1;
            // radiusClient： username
            String str1 = null;
            // radiusClient:  password
            String str2 = null;
            // radiusClient:  Protocal;
            String str3 = null;
            log("Performing tests using basic classes: ");
            while (j != 0)
            {
                i = 0;
                RadiusPacket localRadiusPacket1 = new RadiusPacket(1);
                ////System.out.print("Username: ");
                //str1 = paramBufferedReader.readLine();
                str1 = UserName;
                str2 = Password;
                str3 = AuthType;
                RadiusAttribute localRadiusAttribute = new RadiusAttribute(1, str1.getBytes());
                localRadiusPacket1.setAttribute(localRadiusAttribute);
                ////System.out.print("Password: ");
                //str2 = paramBufferedReader.readLine();
                ////System.out.print("Authentication method [PAP | chap]: ");
                //str3 = paramBufferedReader.readLine();
                if (str3.equalsIgnoreCase("chap"))
                {
                    byte[] arrayOfByte = paramChapUtil.getNextChapChallenge(16);
                    localRadiusPacket1.setAttribute(new RadiusAttribute(3,
                        chapEncrypt(str2, arrayOfByte, paramChapUtil)));
                    localRadiusPacket1.setAttribute(new RadiusAttribute(60, arrayOfByte));
                    localRadiusPacket1.setAttribute(new RadiusAttribute(61, int2byte(15)));
                }
                else
                {
                    localRadiusPacket1.setAttribute(new RadiusAttribute(2, str2.getBytes()));
                }
                
                Object localObject;
                /*
                将原有需要输入的部分截取
                //System.out.print("Additional Attributes? [y|N]:");
                Object localObject;
                for (int k = (paramBufferedReader.readLine().equalsIgnoreCase("y")) ? 1 : 0; k != 0; k = (paramBufferedReader.readLine().equalsIgnoreCase("y")) ? 1 : 0)
                {
                  //System.out.print("Attribute Type:");
                  int l = Integer.parseInt(paramBufferedReader.readLine());
                  //System.out.print("AttributeValue:");
                  localObject = paramBufferedReader.readLine().getBytes();
                  localRadiusPacket1.setAttribute(new RadiusAttribute(l, (byte[])localObject));
                  
                  //System.out.print("Additional Attributes? [y|N]:");
                  
                
                }
                */
                RadiusPacket localRadiusPacket2 = paramRadiusClient.authenticate(localRadiusPacket1);
                
                //     byte[] b = localRadiusPacket1.getAttribute(61).getValue();
                //     if (b.length > 0)
                //     {
                //     	String str = new String(b,"GB2312");
                //     	//System.out.print(str);
                //     }
                switch (localRadiusPacket2.getPacketType())
                {
                    case 2:
                        log("User " + str1 + " authenticated");
                        printAttributes(localRadiusPacket2);
                        //basicAccount(paramRadiusClient, str1);
                        rtn = 1;
                        break;
                    case 3:
                        log("User " + str1 + " NOT authenticated");
                        printAttributes(localRadiusPacket2);
                        break;
                    case 11:
                        localObject = new String(localRadiusPacket2.getAttribute(18).getValue());
                        log("User " + str1 + " Challenged with " + (String)localObject);
                        break;
                    default:
                        log("Whoa, what kind of RadiusPacket is this " + localRadiusPacket2.getPacketType());
                }
                j = 0;
                /*
                   //System.out.print("Another Basic Test [ Y | n ]: ");
                   str3 = paramBufferedReader.readLine();
                   if (!str3.equalsIgnoreCase("n"))
                     continue;
                  
                */
            }
        }
        catch (InvalidParameterException localInvalidParameterException)
        {
            log(localInvalidParameterException.getMessage());
        }
        catch (RadiusException localRadiusException)
        {
            log(localRadiusException.getMessage());
        }
        /*
        catch (IOException localIOException)
        {
          log(localIOException.getMessage());
        }
        */
        return rtn;
    }
    
    private static byte[] chapEncrypt(String paramString, byte[] paramArrayOfByte, ChapUtil paramChapUtil)
    {
        byte b = paramChapUtil.getNextChapIdentifier();
        byte[] arrayOfByte = new byte[17];
        arrayOfByte[0] = b;
        System.arraycopy(ChapUtil.chapEncrypt(b, paramString.getBytes(), paramArrayOfByte), 0, arrayOfByte, 1, 16);
        return arrayOfByte;
    }
    
    private static void basicAccount(RadiusClient paramRadiusClient, String paramString)
        throws InvalidParameterException, RadiusException
    {
        RadiusPacket localRadiusPacket1 = new RadiusPacket(4);
        localRadiusPacket1.setAttribute(new RadiusAttribute(1, paramString.getBytes()));
        localRadiusPacket1.setAttribute(new RadiusAttribute(40, new byte[] {0, 0, 0, 1}));
        localRadiusPacket1.setAttribute(new RadiusAttribute(44, "bob".getBytes()));
        localRadiusPacket1.setAttribute(new RadiusAttribute(6, new byte[] {0, 0, 0, 1}));
        RadiusPacket localRadiusPacket2 = paramRadiusClient.account(localRadiusPacket1);
        switch (localRadiusPacket2.getPacketType())
        {
            case 10:
                log("User " + paramString + " got ACCOUNTING_MESSAGE response");
                break;
            case 5:
                log("User " + paramString + " got ACCOUNTING_RESPONSE response");
                break;
            case 6:
                log("User " + paramString + " got ACCOUNTING_STATUS response");
                break;
            default:
                log("User " + paramString + " got invalid response " + localRadiusPacket2.getPacketType());
        }
        printAttributes(localRadiusPacket2);
    }
    
    private static void advAuthenticate(RadiusClient paramRadiusClient, ChapUtil paramChapUtil,
        BufferedReader paramBufferedReader)
    {
        try
        {
            int i = 0;
            int j = 1;
            String str1 = null;
            String str2 = null;
            String str3 = null;
            log.debug("Performing tests using advanced classes: ");
            while (j != 0)
            {
                i = 0;
                Object localObject1 = null;
                log("Username: ");
                str1 = paramBufferedReader.readLine();
                log("Password: ");
                str2 = paramBufferedReader.readLine();
                log("Authentication method [PAP | chap]: ");
                str3 = paramBufferedReader.readLine();
                if (str3.equalsIgnoreCase("chap"))
                    localObject1 = new ChapAccessRequest(str1, str2);
                else
                    localObject1 = new PapAccessRequest(str1, str2);
                log("Additional Attributes? [y|N]:");
                Object localObject2;
                for (int k = (paramBufferedReader.readLine().equalsIgnoreCase("y")) ? 1 : 0; k != 0; k =
                    (paramBufferedReader.readLine().equalsIgnoreCase("y")) ? 1 : 0)
                {
                    log("Attribute Type:");
                    int l = Integer.parseInt(paramBufferedReader.readLine());
                    log("AttributeValue:");
                    localObject2 = paramBufferedReader.readLine().getBytes();
                    ((RadiusPacket)localObject1).setAttribute(new RadiusAttribute(l, (byte[])localObject2));
                    log("Additional Attributes? [y|N]:");
                }
                RadiusPacket localRadiusPacket = paramRadiusClient.authenticate((RadiusPacket)localObject1);
                switch (localRadiusPacket.getPacketType())
                {
                    case 2:
                        log("User " + str1 + " authenticated");
                        printAttributes(localRadiusPacket);
                        advAccount(paramRadiusClient, str1);
                        break;
                    case 3:
                        log("User " + str1 + " NOT authenticated");
                        printAttributes(localRadiusPacket);
                        break;
                    case 11:
                        localObject2 = new String(localRadiusPacket.getAttribute(18).getValue());
                        log("User " + str1 + " Challenged with " + (String)localObject2);
                        break;
                    default:
                        log("Whoa, what kind of RadiusPacket is this " + localRadiusPacket.getPacketType());
                }
                log("Another Advanced Test [ Y | n ]: ");
                str3 = paramBufferedReader.readLine();
                if (!str3.equalsIgnoreCase("n"))
                    continue;
                j = 0;
            }
        }
        catch (InvalidParameterException localInvalidParameterException)
        {
            log(localInvalidParameterException.getMessage());
        }
        catch (RadiusException localRadiusException)
        {
            log(localRadiusException.getMessage());
        }
        catch (IOException localIOException)
        {
            log(localIOException.getMessage());
        }
    }
    
    private static byte[] int2byte(int res)
    {
        byte[] targets = new byte[4];
        
        targets[3] = (byte)(res & 0xff);// 最高位 
        targets[2] = (byte)((res >> 8) & 0xff);// 次高位 
        targets[1] = (byte)((res >> 16) & 0xff);// 次低位 
        targets[0] = (byte)(res >>> 24);// 最低位,无符号右移。 
        return targets;
    }
    
    private static void advAccount(RadiusClient paramRadiusClient, String paramString)
        throws InvalidParameterException, RadiusException
    {
        AccountingRequest localAccountingRequest =
            new AccountingRequest(paramString, new byte[] {0, 0, 0, 1}, paramString);
        RadiusPacket localRadiusPacket = paramRadiusClient.account(localAccountingRequest);
        switch (localRadiusPacket.getPacketType())
        {
            case 10:
                log("User " + paramString + " got ACCOUNTING_MESSAGE response");
                break;
            case 5:
                log("User " + paramString + " got ACCOUNTING_RESPONSE response");
                break;
            case 6:
                log("User " + paramString + " got ACCOUNTING_STATUS response");
                break;
            default:
                log("User " + paramString + " got invalid response " + localRadiusPacket.getPacketType());
        }
        printAttributes(localRadiusPacket);
    }
    
    private static void printAttributes(RadiusPacket paramRadiusPacket)
    {
        Iterator localIterator = paramRadiusPacket.getAttributes().iterator();
        log("Response Packet Attributes");
        log("\tType\tValue");
        while (localIterator.hasNext())
        {
            RadiusAttribute localRadiusAttribute = (RadiusAttribute)localIterator.next();
            log("\t" + localRadiusAttribute.getType() + "\t" + new String(localRadiusAttribute.getValue()));
        }
    }
    
    private static void log(String paramString)
    {
        log.info("RadiusAuth:" + paramString);
        
    }
}
