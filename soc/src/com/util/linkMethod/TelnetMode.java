package com.util.linkMethod;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.apache.commons.net.telnet.TelnetClient;

import com.soc.model.asset.Asset;

public class TelnetMode {

	private TelnetClient telnet = new TelnetClient("VT220");

	private final int TIMEOUT_SET = 3000; // 链接超时设置

	private final static String flag = "&&";

	private InputStream in = null;

	private PrintStream out = null;

	private char prompt = '>'; // 普通用户结束

	private static String enterSymbol; // Linux换行符 true : linux ; false :
	private static String OSIdentification; // 操作系统分隔符标识(区分操作系统)
	private StringBuffer infoBuffer = new StringBuffer();
	private final String FAILFLAG = "SSH-2.0-OpenSSH" ;
	private final String ORIG_CODEC = "ISO8859-1"; // 系统的编码格式 默认 windowsE
	
	private final String TRANSLATE_CODEC = "GBK"; // 转码格式
	

	private Asset asset;
	private String policyName;
	static {

		if (null == TelnetMode.OSIdentification
				&& null == TelnetMode.enterSymbol) {

			TelnetMode.OSIdentification = System.getProperty("path.separator");

			if (TelnetMode.OSIdentification != null)

				TelnetMode.enterSymbol = TelnetMode.OSIdentification
						.equals(":") ? "\r" : "";

		}

	}

	public static void main(String[] args) {

		TelnetMode t = new TelnetMode();
		Asset a = new Asset() ; 
		a.setAssetName("sss");
		t.asset = a ; 
		t.setPolicyName("D:"+File.separator);

		System.out.println(t.link("Administrator", "root123", "192.168.1.103", 23, "time && 20:00:00 && date && 2014-08-01"));



		/*
		 * System.out.println(TelnetMode.link("admin", "huawei123",
		 * "192.168.54.1", 23,
		 * "system-view  && display ssh server status && ssh server timeout 110 && display ssh server status && ssh server timeout 120"
		 * ));
		 */

		// System.out.println(TelnetMode.link("", "",
		// "192.168.52.1", 23, "system-view && display version"));

		// System.out.println(TelnetMode.link("yuan", "root123", "198.9.9.41",
		// 23, "ifconfig") );

		// System.out.println(TelnetMode.link("hryuan", "root123",
		// "198.9.9.234", 23, "sudo ifconfig") );

	}

	/**
	 * 
	 * Telnet远程链接执行脚本
	 * 
	 * @param username
	 *            用户名
	 * 
	 * @param password
	 *            密码
	 * 
	 * @param host
	 *            设备地址
	 * 
	 * @param port
	 *            端口号
	 * 
	 * @param script
	 *            远程执行的脚本命令
	 * 
	 * @return String
	 */

	public String link(String username, String password, String host, int port,
			String script) {
		String info = "";
		info = linkReal(username, password, host, port, script);
		return info;
	}

	public TelnetMode() {
	}

	private TelnetMode(String username, String password, String host, int port,
			String script) {

		linkReal(username, password, host, port, script);

	}

	public String linkAsset(Asset asset, String script , String policyName) {
		this.policyName   = policyName;
		if (null != asset)this.asset = asset;
		return this.link(asset.getSecurityUserName(), asset.getSecurityPWD(),
				asset.getAssetMac(), asset.getSecurityPort(), script);

	}

	private void createFile() {
		if (null != infoBuffer && infoBuffer.length() > 0) {
			FileWriter fw = null;
			try {
				// OutputStream os = new FileOutputStream();
				fw = new FileWriter(new File(policyName + asset.getAssetId()));
				fw.write(new String(infoBuffer.toString().getBytes(ORIG_CODEC),
						TRANSLATE_CODEC));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {

					if(null != fw)fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 
	 * Telnet远程链接执行脚本
	 * 
	 * @param username
	 *            用户名
	 * 
	 * @param password
	 *            密码
	 * 
	 * @param host
	 *            设备地址
	 * 
	 * @param port
	 *            端口号
	 * 
	 * @param script
	 *            远程执行的脚本命令
	 * 
	 * @return String
	 */

	private synchronized String linkReal(String username, String password,
			String host, int port, String script) {

		// 链接超时

		if (null == username && null == password && host == null && 0 == port)
			return "缺少参数";

		telnet.setConnectTimeout(TIMEOUT_SET);

		try

		{

			telnet.connect(host, port);

		} catch (IOException e) {

			if (telnet != null)

				try {

					telnet.disconnect();

				} catch (IOException e1) {

					// TODO Auto-generated catch block

					e1.printStackTrace();

				}

			return "连接失败";

		}

		try {

			in = telnet.getInputStream();

			out = new PrintStream(telnet.getOutputStream());

			String loginFlag = loginMehtod(username, password);

			if (null == loginFlag) {

				// 登陆成功

				if (script == null) {

					return "连接成功";

				}

				sendCommand(script);

				return "执行成功";

			} else {

				// 登陆失败

				return loginFlag;

			}

		} catch (Exception e) {

			// TODO: handle exception

		} finally {
			if(null != asset){
				createFile();
			}
			if (null != in) {

				try {

					in.close();

				} catch (IOException e) {

					// TODO Auto-generated catch block

					e.printStackTrace();

				}

			}

			if (null != out) {

				out.close();

			}

			try {

				telnet.disconnect();

			} catch (IOException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}

		}

		return null;

	}

	/** 登陆方法 */

	private String loginMehtod(String username, String password) {
		String returnInfo = readUntil("login: ");
		
		if(null != returnInfo && returnInfo.equals(FAILFLAG)){
			return "连接失败";
		}

		write(username);

		readUntil("password: ");

		write(password);

		//this.prompt = username.equals("root") ? '#' : '>';

		String str = readUntil("Login Failed");

		if (null != str) {

			if (str.equalsIgnoreCase("Login Failed")) {

				return "Telnet用户名或密码有误";

			}

		}

		return null;

	}

	/** 读取回执信息 */

	private String readUntil(String pattern) {

		try {

			// 结尾的长度

			int infoLength = pattern.length();

			String subStr = "";

			char lastChar = pattern.charAt(infoLength - 1);

			StringBuffer sb = new StringBuffer();

			char ch = (char) in.read();

			while (true) {

				System.out.print(ch);

				sb.append(ch);
				infoBuffer.append(ch);
				if (ch == lastChar) {

					try {

						subStr = sb.substring(sb.length() - infoLength,
								sb.length());

					} catch (Exception e) {

						// TODO: handle exception

						continue;

					}

					if (subStr.equalsIgnoreCase(pattern)) {

						return pattern;

					}

				}

				if (String.valueOf(ch).equalsIgnoreCase(String.valueOf(prompt))) {

					break;

				} else if (String.valueOf(ch).equalsIgnoreCase("$")) {

					break;

				} else if (sb.toString().endsWith("incorrect")) {

					break;

				} else if (sb.toString().endsWith(":")) {
					if (sb.toString().endsWith("Username:")
							|| sb.toString().endsWith("Password:") || sb.toString().endsWith("ä:")) {
					break;
					}
				} else if ((sb.toString().endsWith("_Access]"))
						|| (sb.toString().endsWith("-IMC]"))) {
					break;
				} else if (sb.toString().endsWith(FAILFLAG)){
					return FAILFLAG ;
					//break ;
				}else if(sb.toString().endsWith(") ")){
					break;
				}

				ch = (char) in.read();

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	private void write(String value) {

		try {

			out.println(value + TelnetMode.enterSymbol);

			out.flush();

			// System.out.println(value);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	private String sendCommand(String command) {

		try {

			if (null != command && command.indexOf(flag) > 0) {

				String arr[] = command.split(flag);

				for (String s : arr) {
					write(s + TelnetMode.enterSymbol);
					Thread.sleep(1000);
					readUntil(prompt + " ");
				}

			} else {
				write(command + TelnetMode.enterSymbol);
				readUntil(prompt + " ");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

}
