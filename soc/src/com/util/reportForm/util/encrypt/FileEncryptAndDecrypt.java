package com.util.reportForm.util.encrypt;

import java.io.File;
import java.io.FileInputStream;

public class FileEncryptAndDecrypt {
//	 文件解密
	public byte[] FileDecrypt(byte[] turn) {
		if (turn == null) {
			return null;
		}
		byte[] naRand = new byte[21];
		naRand[0] = 19;
		naRand[1] = 91;
		naRand[2] = 27;
		naRand[3] = 72;
		naRand[4] = 15;
		naRand[5] = 51;
		naRand[6] = 21;
		naRand[7] = 12;
		naRand[8] = 13;
		naRand[9] = 31;
		naRand[10] = 9;
		naRand[11] = 7;
		naRand[12] = 17;
		naRand[13] = 21;
		naRand[14] = 12;
		naRand[15] = 27;
		naRand[16] = 2;
		naRand[17] = 15;
		naRand[18] = 6;
		naRand[19] = 17;
		naRand[20] = 8;
		try {
			for (int i = 0; i < turn.length; i++) {
				turn[i] -= naRand[i % 21];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return turn;
	}

	// 文件加密
	public byte[] FileEncrypt(byte[] turn) {
		if (turn == null) {
			return null;
		}
		byte[] naRand = new byte[21];
		naRand[0] = 19;
		naRand[1] = 91;
		naRand[2] = 27;
		naRand[3] = 72;
		naRand[4] = 15;
		naRand[5] = 51;
		naRand[6] = 21;
		naRand[7] = 12;
		naRand[8] = 13;
		naRand[9] = 31;
		naRand[10] = 9;
		naRand[11] = 7;
		naRand[12] = 17;
		naRand[13] = 21;
		naRand[14] = 12;
		naRand[15] = 27;
		naRand[16] = 2;
		naRand[17] = 15;
		naRand[18] = 6;
		naRand[19] = 17;
		naRand[20] = 8;
		try {
			for (int i = 0; i < turn.length; i++) {
				turn[i] += naRand[i % 21];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return turn;
	}
//	 文件解密操作
	public byte[] fileDecryptOperation(File legafile) {
		byte[] filedecrypt = null;
		try {
			// 验证客户端个数、使用天数、升级服务天数
			FileInputStream fin = new FileInputStream(legafile);
			byte[] filebyte = new byte[(int) legafile.length()];
			byte[] buffer = new byte[1024]; // To hold file contents
			int bytes_read; // 读取的实际字节长度
			int nowbytes = 0; // filebyte当前长度计数
			while ((bytes_read = fin.read(buffer)) != -1) { // Read until EOF
				for (int j = 0; j < bytes_read; j++) {
					filebyte[nowbytes + j] = buffer[j];
				}
				nowbytes += bytes_read;
			}
			fin.close();
			// 文件解密
			filedecrypt = FileDecrypt(filebyte);
			// 文件解析
		} catch (Exception e) {
		}
		return filedecrypt;
	}
//	 文件加密操作
	public byte[] fileEncryptOperation(File legafile) {
		byte[] filedecrypt = null;
		try {
			// 验证客户端个数、使用天数、升级服务天数
			FileInputStream fin = new FileInputStream(legafile);
			byte[] filebyte = new byte[(int) legafile.length()];
			byte[] buffer = new byte[1024]; // To hold file contents
			int bytes_read; // 读取的实际字节长度
			int nowbytes = 0; // filebyte当前长度计数
			while ((bytes_read = fin.read(buffer)) != -1) { // Read until EOF
				for (int j = 0; j < bytes_read; j++) {
					filebyte[nowbytes + j] = buffer[j];
				}
				nowbytes += bytes_read;
			}
			fin.close();
			filedecrypt = FileEncrypt(filebyte);
		} catch (Exception e) {
		}
		
		return filedecrypt;
	}


}
