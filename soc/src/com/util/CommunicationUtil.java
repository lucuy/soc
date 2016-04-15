package com.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Date;

public class CommunicationUtil {
	
	public static String tcpTestConnect(String ip, int port, int timeout) {
		String ret = "";
		Socket socket = null;
		try {
			Date date1 = new Date();
			socket =  new Socket();
			SocketAddress socketAddress = new InetSocketAddress(ip, port);
			socket.connect(socketAddress, timeout);
			Date date2 = new Date();
			
			long l = date2.getTime() - date1.getTime();
			
			if(l < 1)
				ret = "<1";
			else
				ret = String.valueOf(l);
		} catch (IOException e) {
			ret = "";
		} finally {
			try {
				if(!socket.isClosed())
					socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return ret;
	}

}
