package com.util.linkMethod;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import com.soc.model.asset.Asset;

public class SSHMode {

	private String policyName;
	private Asset asset ; 
public static void main(String[] args) {
	
	//System.out.println(SSHMode.link("root", "123123", "198.9.9.10", 22, "ps -e| grep jjsjsj") );
	Asset asset = new Asset();
	asset.setAssetName("yy") ; 
	String policyName = "D:"+File.separator ;  
	SSHMode m = new SSHMode();
	m.asset = asset ; 
	m.policyName = policyName ; 
	//cat /proc/version 系统版本 //或者 uname -a
	// free -m 以M显示内存信息
	// free b 以字节显示内存信息
	// free -k 以K字节显示内存信息
	//-s 每隔多少秒重复执行,可编写一个脚本用于监控。
	//cat /proc/cpuinfo CPU信息
	//cat /proc/meminfo 内存信息
	//硬盘 df -h -T   
	//debian硬盘 fdisk -l 可以在rehat9.0中看
	//打印文件系统空间使用情况：df -h
	//查看文件大小:du -h filename
	//查看进程:ps -e 或ps -aux显示用户
	//查看进程打开的文件：lsof -p
	m.link("root", "1qaz@WSX","198.9.9.9", 22, "ifconfig");
	
	
}
public  String linkAsset(Asset asset,String script,String policyName) {

		this.asset = asset ; 
		this.policyName = policyName;
	return this.link(asset.getSecurityUserName(), asset.getSecurityPWD(), asset.getAssetMac(), asset.getSecurityPort(), script) ;
		
	}
	/**
	 * SSH远程链接执行脚本
	 * @param username 用户名
	 * @param password 密码
	 * @param host     设备地址
	 * @param port     端口号
	 * @param script   远程执行的脚本命令
	 * @return String
	 */
	public synchronized String link(String username,String password,String host,int port ,String script){
		
		if(username != null && password != null && host != null && port != 0){
			
			Connection conn = new Connection(host,port);
			Session session = null ; 
			StreamGobbler stdout = null ;
			BufferedReader reader = null ; 
			FileOutputStream fos = null ;
			//连接远程设备
			try {
				conn.connect() ;
			} catch (IOException e) {
				
				if(conn != null){
					conn.close() ; 
				}
				return "连接失败" ;
			}
			
			//判断用户名、密码是否正确
			try {
				boolean isAuthenticated = conn.authenticateWithPassword(username , password);
				if(isAuthenticated){
					
					if(script  == null){
						return "连接成功" ;
					}
					
					
					session = conn.openSession() ; 
					session.execCommand(script);
					
					//读取执行的结果
					stdout = new StreamGobbler(session.getStdout());
					reader = new BufferedReader(new InputStreamReader(stdout));
					Thread.sleep(1000);
					if (asset != null) {
						fos = new FileOutputStream(new File(policyName
								+ asset.getAssetId()));
						while (true) {
							String str = reader.readLine();
							System.out.println(str);
							if (str == null)
								break;
							fos.write(new String(str+"\n").getBytes("UTF-8"));
						}
					}
					int exitStatus = session.getExitStatus()==null? 0 : session.getExitStatus(); 
					System.out.println(exitStatus);
					if(exitStatus > 0 && exitStatus != 1){
						return "执行失败";
					}else{
						return "执行成功" ;
					}
					
					
				}else{
					return "SSH用户名或密码有误" ; 
				}
				
			} catch (IOException e) {
				
				e.printStackTrace();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			} finally {
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}
				if (reader != null) {
					try {
						reader.close() ;
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}
				
				if(stdout != null){
					try {
						stdout.close() ;
					} catch (IOException e) {
						
						e.printStackTrace();
					} 
				}
				if(session != null ){
					session.close() ; 
				}
				if(conn != null){
					conn.close() ; 
				}
				
			}
		}else{
			return "缺少参数";
		}
		return null ; 
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	
}
