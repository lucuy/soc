package com.util.linkMethod;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.mvel2.util.ThisLiteral;

import ch.ethz.ssh2.Connection;

import com.soc.model.ScanTask.ScanTask;
import com.soc.model.conf.GlobalConfig;

public class TransmitFile {

	//漏扫任务
	private ScanTask scanTask ; 
	//remote driver ip
	private String ip = GlobalConfig.SCAN_IP; 
	//remote driver port
	private int port = GlobalConfig.SCAN_PORT; 
	//driver username
	private String username = GlobalConfig.SCAN_USERNAME;  
	//driver password
	private String password = GlobalConfig.SCAN_PASSWORD ; 
	//remote target file path for SCAN
	private String scanPath = GlobalConfig.SCAN_PATH; 
	//local file
	private File file ;
	//Linux
	//Windows
	//private static String creatPath = "D:"+File.separator ;
	
	private static String SOCIP =  GlobalConfig.SOC_IP; 
	private static String SOCUSERNAME = GlobalConfig.SOC_USRENAME;
	private static String SOCPASSWORD = GlobalConfig.SOC_PASSWORD;
	private static String SOCPOST = GlobalConfig.SOC_PORT ; 
	private static String creatPath = GlobalConfig.SOC_PATH ;
	
	
	public static void main(String[] args) {
		//ScanTask s = new ScanTask("测试机","198.9.9.8,198.8.8.7","198.9.9.5","198.9.9.9","描述",null,null,"201409008_是","pass","sss"," ") ; 
		ScanTask s = new ScanTask();
		s.setEndIP("198.9.9.5");
		s.setStartIP("198.9.9.1");
		s.setFalgFileName("jjsjs");
		s.setIpBunch("198.9.9.6,198.9.9.5,198.9.9.7");
		s.setTaskName("测试任务名称");
		TransmitFile t = new TransmitFile();
		t.SendSCANTask(s);
	
	}
	
	private TransmitFile(){}
	/**
	 * 构造
	 * @param file 
	 * @param scanPath
	 * @param ip
	 * @param port
	 * @param username
	 * @param password
	 */
	public TransmitFile(File file,String scanPath ,String ip, int port, String username, String password) {
		super();
		if(scanPath != null)this.scanPath = scanPath ; 
		this.ip = ip;
		this.port = port;
		this.username = username;
		this.password = password;
		this.file = file ; 
		sendFile();
	} 
	
	public static String RunSCANTask(ScanTask scanTask){
		return new TransmitFile().SendSCANTask(scanTask);
	}
	
	private synchronized String SendSCANTask(ScanTask scanTask){
		//获取漏扫用户、密码等信息......
		this.scanTask= scanTask ;
		this.file = TransmitFile.creatXMLFile(scanTask) ; 
		return  sendFile();
	}
	//漏扫定制：创建XML文件
	private static File creatXMLFile(ScanTask scanTask){
		if(scanTask==null)return null; 
		Document doc = DocumentHelper.createDocument() ; 
		Element tasks = doc.addElement("tasks") ; 
		Element task = tasks.addElement("task");
		task.addElement("name").setText(scanTask.getFalgFileName()) ; 
		Element targets = task.addElement("targets");
		
		//IP串
		Element ips = targets.addElement("ips");
		String ipBunch = scanTask.getIpBunch() ; 
		if(null != ipBunch){
			if(ipBunch.indexOf(",")>0){
				for(String s:ipBunch.split(",")){
					ips.addElement("ip").setText(s);
				}
			}else{
				ips.addElement("ip").setText(ipBunch);
			}
		}
		
		//IP段
		Element iparea = targets.addElement("iparea");
		if(null != scanTask.getStartIP() && null != scanTask.getEndIP()){
			iparea.addElement("startIP").setText(scanTask.getStartIP());
			iparea.addElement("endIP").setText(scanTask.getEndIP());
		}
		//扫描方式
		task.addElement("executeType").setText("1");
		//SOC信息
		Element soc = tasks.addElement("soc") ; 
		soc.addElement("ip").setText(SOCIP) ; 
		soc.addElement("username").setText(SOCUSERNAME);
		soc.addElement("password").setText(SOCPASSWORD);
		soc.addElement("port").setText(SOCPOST) ; 
		OutputFormat opf = OutputFormat. createPrettyPrint () ;   //格式化相应的XML文件
		opf.setEncoding( "UTF-8" );
		XMLWriter xmlWriter;
		String filePath = creatPath + scanTask.getFalgFileName() + ".xml" ; 
		try {
			xmlWriter = new  XMLWriter( new  FileOutputStream( new  File(  //写入文件类
					filePath )),opf);
			xmlWriter .write(doc);
			xmlWriter .close();
			System.out.println("创建XML文件完成");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return  new File(filePath); 
	}
	
	//发送文件方法
	private String sendFile(){
		
		Connection conn = null  ; 
		try {
			conn = new Connection(ip, port);
			conn.connect() ;
			if(!conn.authenticateWithPassword(username, password))return "用户名或密码错误";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("------------------链接失败------------------");
			return "连接失败" ;
		} 
		//判断文件是否存在
		if(null != file && file.exists()){
			try {
				SCPClient scp = new SCPClient(conn);
				scp.put(file.getPath(), scanPath);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("任务上传失败");
				return "任务上传失败" ;
			}finally{
				conn.close() ; 
				file.delete() ; 
				System.out.println("------------------------------"+"上传完成未出现问题finally");
			}
		}
		System.out.println("------------------------------"+"上传完成未出现问题");
		return "任务上传成功" ; 
	}
	


	
	
	
	
}
