package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class EncodeUtil {

	public static String xorEncode(String str, String key) {
		return "";
	}

	/**
	 * 使用异或进行简单的密码解密
	 * 
	 * @return <code>String[]</code> 加密后字符串
	 * @author Administrator
	 * @since 1.0 2005/11/28
	 */
	public static byte[] xorDecode(byte[] str, char[] key) {
		byte[] buf = new byte[str.length];
		for (int i = 0, j = 0; j < str.length; j++) {
			if (i >= 8)
				i = 0;
			buf[j] = (byte) ((char) str[j] ^ (char) key[i]);
			i++;
		}

		return buf;
	}

	/**
	 * 使用取反进行简单的密码解密
	 * 
	 * @return <code>String[]</code> 加密后字符串
	 * @author Administrator
	 * @since 1.0 2005/11/28
	 */
	public static String notDecode(String str) {
		byte[] buf = new byte[str.getBytes().length];
		for (int i = 0; i < str.getBytes().length; i++) {
			buf[i] = (byte) (~str.getBytes()[i]);
		}

		return new String(buf);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			File file = new File("C:\\Users\\leiya\\Desktop\\license\\MyXml.xml");// c:/abc.txt
			FileInputStream in = null;

			in = new FileInputStream(file);
			byte[] buf = new byte[4096];
			int len = in.read(buf, 0, 4096);
			in.close();

			byte[] buffer = new byte[len];
			for (int i = 0; i < len; i++) {
				buffer[i] = buf[i];
			}

			String str = new String(buffer);
			char[] key1 = { 0x3F, 0xEE, 0x3F, 0x5A, 0xAE, 0xFA, 0x1F, 0x0A };
			char[] key2 = { 0x3D, 0xAE, 0x3A, 0x5B, 0x3F, 0x6A, 0x11, 0xAA };
			char[] key3 = { 0x0B, 0x9E, 0xDF, 0x2A, 0xAA, 0xF0, 0x7D, 0x6E };

			buffer = EncodeUtil.xorDecode(buffer, key3);

			buffer = EncodeUtil.xorDecode(buffer, key2);

			buffer = EncodeUtil.xorDecode(buffer, key1);

			//System.out.println(new String(buffer));
			FileOutputStream f = new FileOutputStream(new File("C:\\Users\\leiya\\Desktop\\license\\compliance.licence"));
			f.write(buffer);
			f.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
