package com.util;

/**  
 * 通信格式转换  
 *  
 * Java和一些windows编程语言如c、c++、delphi所写的网络程序进行通讯时，需要进行相应的转换  
 * 高、低字节之间的转换  
 * windows的字节序为低字节开头  
 * linux,unix的字节序为高字节开头  
 * java则无论平台变化，都是高字节开头  
   */   
public class TransferUtil {


    /**  
      * 将int转为低字节在前，高字节在后的byte数组  
      * @param n int  
      * @return byte[]  
      */   
    public static byte[] toLH(int n) {   
      byte[] b = new byte[4];   
      b[0] = (byte) (n & 0xff);   
      b[1] = (byte) (n >> 8 & 0xff);   
      b[2] = (byte) (n >> 16 & 0xff);   
      b[3] = (byte) (n >> 24 & 0xff);   
      return b;   
    }    
       
    /**  
      * 将int转为高字节在前，低字节在后的byte数组  
      * @param n int  
      * @return byte[]  
      */   
    public static byte[] toHH(int n) {   
      byte[] b = new byte[4];   
      b[3] = (byte) (n & 0xff);   
      b[2] = (byte) (n >> 8 & 0xff);   
      b[1] = (byte) (n >> 16 & 0xff);   
      b[0] = (byte) (n >> 24 & 0xff);   
      return b;   
    }    
       
    /**  
      * 将short转为低字节在前，高字节在后的byte数组  
      * @param n short  
      * @return byte[]  
      */   
    public static byte[] toLH(short n) {   
      byte[] b = new byte[2];   
      b[0] = (byte) (n & 0xff);   
      b[1] = (byte) (n >> 8 & 0xff);   
      return b;   
    }    
       
    /**  
      * 将short转为高字节在前，低字节在后的byte数组  
      * @param n short  
      * @return byte[]  
      */   
    public static byte[] toHH(short n) {   
      byte[] b = new byte[2];   
      b[1] = (byte) (n & 0xff);   
      b[0] = (byte) (n >> 8 & 0xff);   
      return b;   
    }    
       
       
       
    /**  
      * 将将int转为高字节在前，低字节在后的byte数组   
      
    public static byte[] toHH(int number) {  
      int temp = number;  
      byte[] b = new byte[4];  
      for (int i = b.length - 1; i > -1; i--) {  
        b = new Integer(temp & 0xff).byteValue();  
        temp = temp >> 8;  
      }  
      return b;  
    }   
      
    public static byte[] IntToByteArray(int i) {  
        byte[] abyte0 = new byte[4];  
        abyte0[3] = (byte) (0xff & i);  
        abyte0[2] = (byte) ((0xff00 & i) >> 8);  
        abyte0[1] = (byte) ((0xff0000 & i) >> 16);  
        abyte0[0] = (byte) ((0xff000000 & i) >> 24);  
        return abyte0;  
    }   
      
      
    */    
       
    /**  
      * 将float转为低字节在前，高字节在后的byte数组  
      */   
    public static byte[] toLH(float f) {   
      return toLH(Float.floatToRawIntBits(f));   
    }    
       
    /**  
      * 将float转为高字节在前，低字节在后的byte数组  
      */   
    public static byte[] toHH(float f) {   
      return toHH(Float.floatToRawIntBits(f));   
    }    
       
    /**  
      * 将String转为byte数组  
      */   
    public static byte[] stringToBytes(String s, int length) {   
      while (s.getBytes().length < length) {   
        s += " ";   
      }   
      return s.getBytes();   
    }    
       
       
       
    /**  
      * 将字符串转换为byte数组  
      * @param s String  
      * @return byte[]  
      */   
    public static byte[] stringToBytes(String s) {   
      return s.getBytes();   
    }    
       
       
    /**  
      * 将低字节数组转换为int  
      * @param b byte[]  
      * @return int  
      */   
    public static int lBytesToInt(byte[] b) {   
      int s = 0;   
      for (int i = 0; i < 3; i++) {   
        if (b[3-i] >= 0) {   
        s = s + b[3-i];   
        } else {   
        s = s + 256 + b[3-i];   
        }   
        s = s * 256;   
      }   
      if (b[0] >= 0) {   
        s = s + b[0];   
      } else {   
        s = s + 256 + b[0];   
      }   
      return s;   
    }    
       
       
    /**  
      * 高字节数组到short的转换  
      * @param b byte[]  
      * @return short  
      */   
    public static short hBytesToShort(byte[] b) {   
      int s = 0;   
      if (b[0] >= 0) {   
        s = s + b[0];   
        } else {   
        s = s + 256 + b[0];   
        }   
        s = s * 256;   
      if (b[1] >= 0) {   
        s = s + b[1];   
      } else {   
        s = s + 256 + b[1];   
      }   
      short result = (short)s;   
      return result;   
    }    
       
    /**  
      * 低字节数组到short的转换  
      * @param b byte[]  
      * @return short  
      */   
    public static short lBytesToShort(byte[] b) {   
      int s = 0;   
      if (b[1] >= 0) {   
        s = s + b[1];   
        } else {   
        s = s + 256 + b[1];   
        }   
        s = s * 256;   
      if (b[0] >= 0) {   
        s = s + b[0];   
      } else {   
        s = s + 256 + b[0];   
      }   
      short result = (short)s;   
      return result;   
    }    
       
    /**  
      * 高字节数组转换为float  
      * @param b byte[]  
      * @return float  
      */   
    public static float hBytesToFloat(byte[] b) {   
      int i = 0;   
      Float F = new Float(0.0);   
      i = ((((b[0]&0xff)<<8 | (b[1]&0xff))<<8) | (b[2]&0xff))<<8 | (b[3]&0xff);   
      return F.intBitsToFloat(i);   
    }    
       
    /**  
      * 低字节数组转换为float  
      * @param b byte[]  
      * @return float  
      */   
    public static float lBytesToFloat(byte[] b) {   
      int i = 0;   
      Float F = new Float(0.0);   
      i = ((((b[3]&0xff)<<8 | (b[2]&0xff))<<8) | (b[1]&0xff))<<8 | (b[0]&0xff);   
      return F.intBitsToFloat(i);   
    }    
    
       
    /**  
      * 打印byte数组  
      */   
    public static void printBytes(byte[] bb) {   
      int length = bb.length;   
      for (int i=0; i<length; i++) {   
        //System.out.print(bb + " ");   
      }   
      //System.out.println("");   
    }    
       
    public static void logBytes(byte[] bb) {   
      int length = bb.length;   
      String out = "";   
      for (int i=0; i<length; i++) {   
        out = out + bb + " ";   
      }    
       
    }    
 
       
    /**  
      * 将short类型的值转换为字节序颠倒过来对应的short值  
      * @param s short  
      * @return short  
      */   
    public static short reverseShort(short s) {   
      short result = TransferUtil.hBytesToShort(TransferUtil.toLH(s));   
      return result;   
    }    
       
    /**  
      * 将float类型的值转换为字节序颠倒过来对应的float值  
      * @param f float  
      * @return float  
      */   
    public static float reverseFloat(float f) {   
      float result = TransferUtil.hBytesToFloat(TransferUtil.toLH(f));   
      return result;   
    }    
	       
}
