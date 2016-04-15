package com.util;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import gnu.crypto.util.Base64;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.*;
import javax.crypto.spec.*;

import java.io.*;

public class DES
{
    
    Key key;
    
    public DES(String str)
    {
        setKey(str);//生成密匙
    }
    
    public DES()
    {
        setKey("siyue_qi");
    }
    
    /**
       * 根据参数生成KEY
       */
    public void setKey(String strKey)
    {
        try
        {
            KeyGenerator _generator = KeyGenerator.getInstance("DES");
            _generator.init(new SecureRandom(strKey.getBytes()));
            this.key = _generator.generateKey();
            _generator = null;
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
        }
    }
    
    /**
       * 加密String明文输入,String密文输出
       */
    public String getEncString(String strMing)
    {
        byte[] byteMi = null;
        byte[] byteMing = null;
        String strMi = "";
        try
        {
            byteMing = strMing.getBytes("UTF8");
            byteMi = this.getEncCode(byteMing);
            strMi = new String(Base64.encode(byteMi));
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
        }
        finally
        {
            byteMing = null;
            byteMi = null;
        }
        return strMi;
    }
    
    /**
       * 解密 以String密文输入,String明文输出
       * @param strMi
       * @return
       */
    public String getDesString(String strMi)
    {
        byte[] byteMing = null;
        byte[] byteMi = null;
        String strMing = "";
        try
        {
            //System.out.println("step1");
            byteMi = Base64.decode(strMi);
            //System.out.println("step2");
            byteMing = this.getDesCode(byteMi);
            //System.out.println("step3");
            strMing = new String(byteMing, "UTF8");
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
        }
        finally
        {
            byteMing = null;
            byteMi = null;
        }
        return strMing;
    }
    
    /**
       * 加密以byte[]明文输入,byte[]密文输出
       * @param byteS
       * @return
       */
    private byte[] getEncCode(byte[] byteS)
    {
        byte[] byteFina = null;
        Cipher cipher;
        try
        {
            //System.out.println("s1");
            cipher = Cipher.getInstance("DES");
            //System.out.println("s2");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //System.out.println("s3");
            byteFina = cipher.doFinal(byteS);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
        }
        finally
        {
            cipher = null;
        }
        return byteFina;
    }
    
    /**
       * 解密以byte[]密文输入,以byte[]明文输出
       * @param byteD
       * @return
       */
    private byte[] getDesCode(byte[] byteD)
    {
        Cipher cipher;
        byte[] byteFina = null;
        try
        {
            cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byteFina = cipher.doFinal(byteD);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
        }
        finally
        {
            cipher = null;
        }
        return byteFina;
    }
    
    /** 
    加密函数 
    输入： 
    要加密的文件，密码（由0-F组成，共48个字符，表示3个8位的密码）如： 
    AD67EA2F3BE6E5ADD368DFE03120B5DF92A8FD8FEC2F0746 
    其中： 
    AD67EA2F3BE6E5AD DES密码一 
    D368DFE03120B5DF DES密码二 
    92A8FD8FEC2F0746 DES密码三 
    输出： 
    对输入的文件加密后，保存到同一文件夹下增加了".tdes"扩展名的文件中。 
    */
    public void encrypt(File fileIn, String sKey)
    {
        try
        {
            if (sKey.length() == 48)
            {
                byte[] bytK1 = getKeyByStr(sKey.substring(0, 16));
                byte[] bytK2 = getKeyByStr(sKey.substring(16, 32));
                byte[] bytK3 = getKeyByStr(sKey.substring(32, 48));
                
                FileInputStream fis = new FileInputStream(fileIn);
                byte[] bytIn = new byte[(int)fileIn.length()];
                for (int i = 0; i < fileIn.length(); i++)
                {
                    bytIn[i] = (byte)fis.read();
                }
                //加密 
                byte[] bytOut = encryptByDES(encryptByDES(encryptByDES(bytIn, bytK1), bytK2), bytK3);
                String fileOut = fileIn.getPath() + ".mlprop";
                FileOutputStream fos = new FileOutputStream(fileOut);
                for (int i = 0; i < bytOut.length; i++)
                {
                    fos.write((int)bytOut[i]);
                }
                fos.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * @author 王帅
     * @param filepath
     * @param bytIn
     * @param sKey
     */
    public void encrypt2File(String filepath, byte[] bytIn, String sKey)
    {
        try
        {
            if (sKey.length() == 48)
            {
                byte[] bytK1 = getKeyByStr(sKey.substring(0, 16));
                byte[] bytK2 = getKeyByStr(sKey.substring(16, 32));
                byte[] bytK3 = getKeyByStr(sKey.substring(32, 48));
                //加密 
                byte[] bytOut = encryptByDES(encryptByDES(encryptByDES(bytIn, bytK1), bytK2), bytK3);
                FileOutputStream fos = new FileOutputStream(filepath + ".mlprop");
                for (int i = 0; i < bytOut.length; i++)
                {
                    fos.write((int)bytOut[i]);
                }
                fos.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * @author 郭煜玺
     * @param filepath
     * @param bytIn
     * @param sKey
     */
    public byte[] encrypt2Byte(byte[] bytIn, String sKey)
    {
        byte[] bytOut = new byte[1];
        try
        {
            if (sKey.length() == 48)
            {
                byte[] bytK1 = getKeyByStr(sKey.substring(0, 16));
                byte[] bytK2 = getKeyByStr(sKey.substring(16, 32));
                byte[] bytK3 = getKeyByStr(sKey.substring(32, 48));
                //加密 
                bytOut = encryptByDES(encryptByDES(encryptByDES(bytIn, bytK1), bytK2), bytK3);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return bytOut;
    }
    
    /** 
    解密函数 
    输入： 
    要解密的文件，密码（由0-F组成，共48个字符，表示3个8位的密码）如： 
    AD67EA2F3BE6E5ADD368DFE03120B5DF92A8FD8FEC2F0746 
    其中： 
    AD67EA2F3BE6E5AD DES密码一 
    D368DFE03120B5DF DES密码二 
    92A8FD8FEC2F0746 DES密码三 
    输出： 
    对输入的文件解密后，保存到用户指定的文件中。 
    */
    public File decrypt(File fileIn, String sKey, String strFullName)
    {
        File fileOut = null;
        try
        {
            if (sKey.length() == 48)
            {
                /*String strPath = fileIn.getPath();
                if (strPath.substring(strPath.length() - 5).toLowerCase().equals(".mlprop"))
                    strPath = strPath.substring(0, strPath.length() - 5);
                else
                {
                    return null;
                }*/
                
                //用户指定要保存的文件 
                byte[] bytK1 = getKeyByStr(sKey.substring(0, 16));
                byte[] bytK2 = getKeyByStr(sKey.substring(16, 32));
                byte[] bytK3 = getKeyByStr(sKey.substring(32, 48));
                
                FileInputStream fis = new FileInputStream(fileIn);
                byte[] bytIn = new byte[(int)fileIn.length()];
                fis.read(bytIn, 0, bytIn.length);
                /* for (int i = 0; i < fileIn.length(); i++)
                {
                    bytIn[i] = (byte)fis.read();
                }*/
                //解密 
                byte[] bytOut = decryptByDES(decryptByDES(decryptByDES(bytIn, bytK3), bytK2), bytK1);
                fileOut = new File(strFullName);
                fileOut.createNewFile();
                FileOutputStream fos = new FileOutputStream(fileOut);
                fos.write(bytOut, 0, bytOut.length);
                /* for (int i = 0; i < bytOut.length; i++)
                {
                    fos.write((int)bytOut[i]);
                }*/
                fos.close();
                
            }
        }
        catch (Exception e)
        {
            //"解密失败，请核对密码！","提示",JOptionPane.OK_OPTION); 
        }
        return fileOut;
    }
    
    /** 
    用DES方法加密输入的字节 
    bytKey需为8字节长，是加密的密码 
    */
    private byte[] encryptByDES(byte[] bytP, byte[] bytKey)
        throws Exception
    {
        DESKeySpec desKS = new DESKeySpec(bytKey);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        SecretKey sk = skf.generateSecret(desKS);
        Cipher cip = Cipher.getInstance("DES");
        cip.init(Cipher.ENCRYPT_MODE, sk);
        return cip.doFinal(bytP);
    }
    
    /** 
    用DES方法解密输入的字节 
    bytKey需为8字节长，是解密的密码 
    */
    private byte[] decryptByDES(byte[] bytE, byte[] bytKey)
        throws Exception
    {
        DESKeySpec desKS = new DESKeySpec(bytKey);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        SecretKey sk = skf.generateSecret(desKS);
        Cipher cip = Cipher.getInstance("DES");
        cip.init(Cipher.DECRYPT_MODE, sk);
        return cip.doFinal(bytE);
    }
    
    /** 
    输入密码的字符形式，返回字节数组形式。 
    如输入字符串：AD67EA2F3BE6E5AD 
    返回字节数组：{173,103,234,47,59,230,229,173} 
    */
    private byte[] getKeyByStr(String str)
    {
        byte[] bRet = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++)
        {
            Integer itg = new Integer(16 * getChrInt(str.charAt(2 * i)) + getChrInt(str.charAt(2 * i + 1)));
            bRet[i] = itg.byteValue();
        }
        return bRet;
    }
    
    /** 
    计算一个16进制字符的10进制值 
    输入：0-F 
    */
    private int getChrInt(char chr)
    {
        int iRet = 0;
        if (chr == "0".charAt(0))
            iRet = 0;
        if (chr == "1".charAt(0))
            iRet = 1;
        if (chr == "2".charAt(0))
            iRet = 2;
        if (chr == "3".charAt(0))
            iRet = 3;
        if (chr == "4".charAt(0))
            iRet = 4;
        if (chr == "5".charAt(0))
            iRet = 5;
        if (chr == "6".charAt(0))
            iRet = 6;
        if (chr == "7".charAt(0))
            iRet = 7;
        if (chr == "8".charAt(0))
            iRet = 8;
        if (chr == "9".charAt(0))
            iRet = 9;
        if (chr == "A".charAt(0))
            iRet = 10;
        if (chr == "B".charAt(0))
            iRet = 11;
        if (chr == "C".charAt(0))
            iRet = 12;
        if (chr == "D".charAt(0))
            iRet = 13;
        if (chr == "E".charAt(0))
            iRet = 14;
        if (chr == "F".charAt(0))
            iRet = 15;
        return iRet;
    }
    
    public String getDES3Enc(String Ming)
    {
        return getEncString(getEncString(getEncString(Ming)));
    }
    
    public String getDES3Dec(String Mi)
    {
        return getDesString(getDesString(getDesString(Mi)));
    }
    
    /**
     * 使用异或进行简单的密码加密
     * @return <code>String[]</code> 加密后字符串
     * @author Administrator
     * @since 1.0 2005/11/28
     */
    
    public static String setEncrypt(String str)
    {
        String sn = "ziyu"; //密钥
        int[] snNum = new int[str.length()];
        String result = "";
        String temp = "";
        
        for (int i = 0, j = 0; i < str.length(); i++, j++)
        {
            if (j == sn.length())
                j = 0;
            snNum[i] = str.charAt(i) ^ sn.charAt(j);
        }
        
        for (int k = 0; k < str.length(); k++)
        {
            
            if (snNum[k] < 10)
            {
                temp = "00" + snNum[k];
            }
            else
            {
                if (snNum[k] < 100)
                {
                    temp = "0" + snNum[k];
                }
            }
            result += temp;
        }
        return result;
    }
    
    /**
     * 使用异或进行简单的密码解密
     * @return <code>String[]</code> 加密后字符串
     * @author Administrator
     * @since 1.0 2005/11/28
     */
    public static String getEncrypt(String str)
    {
        String sn = "ziyu"; //密钥
        char[] snNum = new char[str.length() / 3];
        String result = "";
        
        for (int i = 0, j = 0; i < str.length() / 3; i++, j++)
        {
            if (j == sn.length())
                j = 0;
            int n = Integer.parseInt(str.substring(i * 3, i * 3 + 3));
            snNum[i] = (char)((char)n ^ sn.charAt(j));
        }
        
        for (int k = 0; k < str.length() / 3; k++)
        {
            result += snNum[k];
        }
        return result;
    }
    
}
