package com.topo.action.deviceCategory;

import java.io.*;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.net.telnet.TelnetClient;
import org.apache.struts2.ServletActionContext;

import net.sf.cglib.transform.impl.AddDelegateTransformer;
import net.sf.json.JSON;
import net.sf.json.JSONArray;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import com.topo.action.BaseAction;
import com.topo.model.device.Device;
import com.topo.model.deviceCategory.DeviceCategory;
import com.topo.model.deviceCategory.DeviceCategoryPhoto;
import com.topo.service.device.DeviceManageService;
import com.topo.service.deviceCategory.DeviceCategoryManageService;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;



public class DeviceCategoryManageAction extends BaseAction {

	private static final long serialVersionUID = -4146818016498898294L;
	private String deviceCategoryId;
	private DeviceCategory deviceCategory;
	private File deviceCategoryPhoto ;
	private String deviceIp;//该变量为ping、telnet、ssh工具中所公用，在ssh中相当于hostname。
	private String returnMsg="";//该变量为ping、telnet、ssh工具中所公用
	private String photoPath;
	//telnet工具模块
		PrintStream ops= null;  
	    BufferedReader in = null;
	    TelnetClient telnetclient=new TelnetClient();  
	    private Integer devicePort;//该变量为telnet、ssh工具中所公用
	    private String deviceCmd;//该变量为telnet、ssh工具中所共用
	    private String pwd1;
	    private String pwd2;
	 //ssh工具模块
		//private String hostname;
	    private String username;
	    private String password;
	    private Connection conn = null;
	    private Session sess = null;
	
	private List<DeviceCategory> deviceCategoryList = new ArrayList<DeviceCategory>();
	
	private List<DeviceCategoryPhoto> deviceCategoryPhotoList = new ArrayList<DeviceCategoryPhoto>();
	
	private DeviceCategoryManageService deviceCategoryManageService;
	private DeviceManageService deviceManageService;
	
	public String queryAllDeviceCategory(){
		try {
			deviceCategoryList = deviceCategoryManageService.queryAllDeviceCategory();
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ERROR;
		}
	}
	
	public String queryDefaultIp(){
		HttpServletRequest request = super.getRequest();
		this.deviceIp = request.getParameter("deviceip");
		return SUCCESS;
	}

	public String getDeviceCategoryId() {
		return deviceCategoryId;
	}

	public void setDeviceCategoryId(String deviceCategoryId) {
		this.deviceCategoryId = deviceCategoryId;
	}

	public DeviceCategory getDeviceCategory() {
		return deviceCategory;
	}

	public void setDeviceCategory(DeviceCategory deviceCategory) {
		this.deviceCategory = deviceCategory;
	}
	

	public List<DeviceCategory> getDeviceCategoryList() {
		return deviceCategoryList;
	}

	public void setDeviceCategoryList(List<DeviceCategory> deviceCategoryList) {
		this.deviceCategoryList = deviceCategoryList;
	}

	public void setDeviceCategoryManageService(DeviceCategoryManageService deviceCategoryManageService) {
		this.deviceCategoryManageService = deviceCategoryManageService;
	}
	
	//根据设备类型图片路径获取设备类型图片名称
	
	public void queryDiviceCategoryNameByPhoto(){
		try {
			deviceCategoryPhotoList=deviceCategoryManageService.queryAllDeviceCategoryPhoto();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpServletRequest request = super.getRequest();
		this.photoPath = request.getParameter("photoPath");
		if(deviceCategoryPhotoList!=null){
			for (DeviceCategoryPhoto photo : deviceCategoryPhotoList) {
				if(photo.getDeviceCategoryphoto_Path().equalsIgnoreCase(photoPath)){
					try {
						getResponse().getWriter().write(photo.getDeviceCategoryphoto_name());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	
	public   String addDeviceCategory() {
		HttpServletRequest request=super.getRequest();
		try {
//			List<DeviceCategoryPhoto>li = deviceCategoryManageService.queryAllDeviceCategoryPhoto();
//			for (DeviceCategoryPhoto dcp : li) {
//				dcp.setDeviceCategoryphoto_Path("${ctx}/"+dcp.getDeviceCategoryphoto_Path());
//				deviceCategoryPhotoList.add(dcp);
//			}
			deviceCategoryPhotoList=deviceCategoryManageService.queryAllDeviceCategoryPhoto();
			deviceCategoryList=deviceCategoryManageService.queryAllDeviceCategory();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String strId=request.getParameter("id");
		if(StringUtil.isNotBlank(strId)){
			long id=Long.parseLong(strId);
			try {
				this.deviceCategory=deviceCategoryManageService.queryDeviceCategoryById(id);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			deviceCategory=new DeviceCategory();
		}
		return SUCCESS;
	}
	
	public String deleteDeviceCategory(){
		HttpServletRequest request=super.getRequest();
		String ids=request.getParameter("ids");
		String[] strs=ids.split(",");
        long [] num=new long[strs.length];
        long [] num2=new long[strs.length];
        int tag = 0;
        for(int i=0;i<num.length;i++){
            num[i]=Integer.parseInt(strs[i]);
            try {
				int count = deviceManageService.queryDeviceByDeviceCategoryID(num[i]);
				if(count==0){
					num2[tag++] = num[i];
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		try {
			
			deviceCategoryManageService.delDeviceCategory(num2);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public void deviceCategoryStatus(){
		HttpServletRequest request=super.getRequest();
		String ids=request.getParameter("ids");
		String[] strs=ids.split(",");
		int index  =0;
		boolean flag = false;
        long [] num=new long[strs.length];
        for(int i=0;i<num.length;i++){
            num[i]=Integer.parseInt(strs[i]);
            try {
				int count = deviceManageService.queryDeviceByDeviceCategoryID(num[i]);
				if(count>0){
					flag = true;
					index+=1;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        if(flag&&(index==num.length)){
        	try {
				getResponse().getWriter().write("{\"resultByAjax\":1}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }else if(flag&&(index<num.length)){
        	try {
				getResponse().getWriter().write("{\"resultByAjax\":2}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }else{
        	try {
				getResponse().getWriter().write("{\"resultByAjax\":-1}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	//添加设备类型时根据名称判断是否有重名的设备类型
	public void judgeDeviceCategoryNameExist(){
		HttpServletRequest request=super.getRequest();
		String name=request.getParameter("name");
		boolean nameFlag = false;
		try {
			this.deviceCategoryList = deviceCategoryManageService.queryAllDeviceCategory();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		for (DeviceCategory category : deviceCategoryList) {
			if(category.getDeviceCategory_name().toString().equalsIgnoreCase(name)){
				nameFlag = true;
			}
		}
		if(nameFlag){
			try {
				getResponse().getWriter().write("{\"resultByAjax\":1}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				getResponse().getWriter().write("{\"resultByAjax\":0}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public String updateDeviceCategory() throws Exception {
		HttpServletRequest request=super.getRequest();
		String strDeviceCategoryName=request.getParameter("deviceCategoryName");
		
		String strDeviceCategoryDescribe = request.getParameter("deviceCategoryDescribe");
		String picturepath = request.getParameter("deviceCategoryPhotoPath");
		
		//先将图片上传至服务器
		
//		InputStream inputStream = null;
//		OutputStream outputStream  = null;
//		List<String> stringss =new ArrayList<String>();
//		File toFile = null;
//			try {
//				//基于fileRule创建一个文件输入流
//				
//				inputStream = new FileInputStream(deviceCategoryPhoto);
//				//设置上传文件目录
//				String uploadPath1 = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "com/util/picture/temp/";
//				String uploadPath = uploadPath1.substring(1, uploadPath1.length());
//				File file = new File(uploadPath);
//				if(!file.isDirectory()){
//					file.mkdirs();
//				}
//				//String fileName = request.getParameter("deviceCategoryPhoto");
//				String fileName = System.currentTimeMillis()+".jpg";
//				//picturepath = uploadPath+fileName;
//				//String classDir = this.getClass().getResource("/").getPath();
//				picturepath =uploadPath+fileName;
//				//设置目标文件
//				toFile = new File(file,fileName);
//				//上传一个输出流
//				outputStream = new FileOutputStream(toFile);
//				//设置缓存
//				byte[] buffer = new byte[1024];
//				int length = 0;
//				
//				//读取文件输出到toFile文件中
//				while((length = inputStream.read(buffer))>0){
//					outputStream.write(buffer, 0, length);
//				}
//				try {
//					inputStream.close();
//					outputStream.close();
//				} catch (Exception e2) {
//					e2.printStackTrace();
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}	



		String strDeviceCategoryPhoto = picturepath;
		
		long id=deviceCategory.getDeviceCategory_id();
		deviceCategory.setDeviceCategory_name(strDeviceCategoryName);
		deviceCategory.setDeviceCategory_photo(strDeviceCategoryPhoto);
		deviceCategory.setDeviceCategory_describe(strDeviceCategoryDescribe);
		if(id!=0){
			deviceCategoryManageService.editDeviceCategory(deviceCategory);
		}else{
			deviceCategoryManageService.addDeviceCategory(deviceCategory);
		}
		return SUCCESS;
	}
	
	
	
	public String editDeviceCategory() throws Exception {
		deviceCategoryManageService.editDeviceCategory(deviceCategory);
		return SUCCESS;
	}

	

	public DeviceManageService getDeviceManageService() {
		return deviceManageService;
	}

	public void setDeviceManageService(DeviceManageService deviceManageService) {
		this.deviceManageService = deviceManageService;
	}


	public File getDeviceCategoryPhoto() {
		return deviceCategoryPhoto;
	}

	public void setDeviceCategoryPhoto(File deviceCategoryPhoto) {
		this.deviceCategoryPhoto = deviceCategoryPhoto;
	}


	public List<DeviceCategoryPhoto> getDeviceCategoryPhotoList() {
		return deviceCategoryPhotoList;
	}

	public void setDeviceCategoryPhotoList(List<DeviceCategoryPhoto> deviceCategoryPhotoList) {
		this.deviceCategoryPhotoList = deviceCategoryPhotoList;
	}

	
	/**
	 * @Title: execPingCommand
	 * @Description: 执行ping命令，查看设备是否可用
	 * @param @param deviceIp    
	 * @param returnMsg
	 * @throws
	 */
	
	 public void execPingCommand(){
	  System.out.println("进入方法！！！！！");
	  boolean networkUseable = false;
	  HttpServletRequest request = super.getRequest();
	  this.deviceIp = request.getParameter("deviceIp");
	 // this.deviceIp = "192.168.20.14";
	             
	        Process process = null;
	  try {
	   process = Runtime.getRuntime().exec("ping -c 3 "+deviceIp);
	  } catch (IOException e1) {
	   // TODO Auto-generated catch block
	  } 
	        InputStreamReader r = new InputStreamReader(process.getInputStream());  
	        LineNumberReader returnData = new LineNumberReader(r);  
	        String line = ""; 
	        try {
	   while ((line = returnData.readLine()) != null) {  
	        //System.out.println(line);  
	        this.returnMsg += line; 
	        this.returnMsg+="\n";
	   }
	//   session.setAttribute("msg", this.returnMsg);
	   
	   //.HttpSession session = super.getSession();
	   //session.setAttribute("msg", this.returnMsg);
	   if(this.returnMsg.indexOf("Unreachable") != -1 || this.returnMsg.indexOf("100% packet loss") != -1){  
	          networkUseable = false;
	         }  
	         else{  
	          networkUseable = true; 
	         }  
	   if(!networkUseable){
    	   this.returnMsg = "连接超时！";
       }
	   getResponse().getWriter().write(returnMsg);
	   System.out.println("输出ping命令执行结果！！！"+returnMsg);
	   
	  }catch (IOException e) {
	   // TODO Auto-generated catch block
	  } finally{
	   
	   if(returnData != null){
	    try {
	     
	     returnData.close();
	     
	    } catch (IOException e) {
	     // TODO Auto-generated catch block
	    }
	   }
	   
	   if(r != null){
	    try {
	     r.close();
	     
	    } catch (IOException e) {
	     // TODO Auto-generated catch block
	    }
	   }
	   
	  } 
	      
	          
	           System.out.println(returnMsg);
	           this.returnMsg = "";
	           
	           
	 }
	 
	 /**
		 * 以下部分为telnet工具实现
		 */
	 public void connectPort(){
		 HttpServletRequest request = super.getRequest();
	 try { 
		 	this.deviceIp = request.getParameter("deviceIp");
		 	this.devicePort = Integer.parseInt(request.getParameter("devicePort"));
           telnetclient.connect(this.deviceIp,this.devicePort);//连接的服务器和端口  
           System.out.println("test cennection:"+telnetclient.isConnected());          
           ops=new PrintStream(telnetclient.getOutputStream());  
           this.returnMsg = "";
           this.showTelnetCmd();
           System.out.println(this.returnMsg);
           getResponse().getWriter().write(this.returnMsg);//提示连接成功
       } catch (SocketException e) {             
           e.printStackTrace();  
       } catch (IOException e) {         
           e.printStackTrace();  
       } 
}

private void showTelnetCmd(){
	
	 String str="";
	 try {
		
	
	 ops.flush();     
    in = new BufferedReader(new InputStreamReader(telnetclient.getInputStream()));         
   char[] bs=new char[256];  
   int i=0;  
       while((i=in.read(bs))>-1&&bs!=null){  
           if(i==256){  
               str+=new String(bs);  
           }else{  
               char[] bs2=new char[i];  
               for(int j=0;j<i;j++)  
               {  
                   bs2[j]=bs[j];  
               }  
               str+=new String(bs2);  
               
           } 
           this.returnMsg=str;
           if(str.substring(str.length()-9).equalsIgnoreCase("Password:")){
          	 break;
           }else if(str.substring(str.length()-1).equalsIgnoreCase(">")){
          	 break;
           }else if(str.substring(str.length()-1).equalsIgnoreCase("#")){
          	 break;
           }else if(str.substring(str.length()-9).equalsIgnoreCase("--More-- ")){
          	 break;
           }
           System.out.println(this.returnMsg);
       }  
      // System.out.println(this.returnMsg);
	 } catch (Exception e) {
			// TODO: handle exception
		}
	 
}

	 /**
	  * 写
	  * 
	  * @param value
	  */
	 private void writeTelnetCmd(String value) {
	  try {
	   ops.println(value);
	   ops.flush();

	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	 }

	 public void disconnect(){
			if(telnetclient!=null){
				try {
					telnetclient.disconnect();
					getResponse().getWriter().write("连接已经断开");
					System.out.println("连接已经断开");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				try {
					getResponse().getWriter().write("连接已经断开");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("连接已经断开");
			}
		}
	 
	 public void runTelnet(){
			HttpServletRequest request = super.getRequest();
			this.deviceCmd = request.getParameter("deviceCmd");
			System.out.println("============="+this.deviceCmd);
			this.writeTelnetCmd(this.deviceCmd);
			try {
				this.returnMsg = "";
				this.showTelnetCmd();
				//String returnMess = this.returnMsg+"\n";
				System.out.println(this.returnMsg);
				getResponse().getWriter().write(this.returnMsg);
				//getResponse().getWriter().write("执行成功");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//提示连接成功
		}
	 
	 
	 
	 
		
		/**
		 * 以下部分为SSH工具实现
		 */
		
		public void sshConnect(){
			  //建立连接
			HttpServletRequest request = super.getRequest();
			this.deviceIp = request.getParameter("deviceIp");
			this.devicePort = Integer.parseInt(request.getParameter("devicePort"));
			this.username = request.getParameter("username");
			this.password = request.getParameter("password");
			String returnMess = "执行异常！"+"\n";
	        try {
				conn= new Connection(this.deviceIp, this.devicePort);
				System.out.println("开始连接远程主机......");
				returnMess="开始连接远程主机......"+"\n";
				
				conn.connect();
				System.out.println("成功连接远程主机，验证账号密码中......");
				returnMess+="成功连接远程主机，验证账号密码中......"+"\n";
				
				//利用用户名和密码进行授权
				boolean isAuthenticated = conn.authenticateWithPassword(this.username, this.password);
				if(isAuthenticated ==false)
				{
					returnMess+="账号密码验证失败！"+"\n";
				    throw new IOException("账号密码验证失败！");
				}
				System.out.println("账号密码验证通过，准备执行操作命令......");
				returnMess+="账号密码验证通过，准备执行操作命令......"+"\n";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("无法连接远程主机！");
				returnMess+="无法连接远程主机！"+"\n";
			}
	        
	        try {
				getResponse().getWriter().write(returnMess);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void doSshCmd(){
			HttpServletRequest request = super.getRequest();
			this.deviceCmd = request.getParameter("deviceCmd");
			String buffCmd = this.deviceCmd.substring(0, 4);
			/** 如果字符串的长度大于等于4位字符，则执行判断是否为 ping 命令 */
			if(buffCmd.length() >= 4){
				System.out.println(buffCmd);
				if(buffCmd.equalsIgnoreCase("ping")){
					this.deviceCmd+=" -c 3";
				}
			}
			String returnMess = "执行异常！"+"\n";
			try {
				//打开会话
				sess = conn.openSession();
				System.out.println(this.deviceCmd);
				//执行命令
				sess.execCommand(this.deviceCmd);
				//sess.execCommand("ls");
				
				System.out.println("执行命令输出为:");
				returnMess="执行命令输出为:"+"\n";
				InputStream stdout = new StreamGobbler(sess.getStdout());
				BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
				//int index = 0;
				while(true)
				{
				    String line = br.readLine();
				    //System.out.println("123haha");
				    if(line==null) break;
				   // System.out.println("123haha");
				    System.out.println(line);
				    returnMess+=line+"\n";
				    //index++;
				    
				}
				System.out.println("Exit code "+sess.getExitStatus());
				returnMess+="Exit code "+sess.getExitStatus()+"\n";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				getResponse().getWriter().write(returnMess);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void disConnectSsh(){
			if(sess!=null&&conn!=null){
				 sess.close();
		         conn.close();
		         System.out.println("断开连接！"); 
			}else if(sess!=null){
				sess.close();
				System.out.println("断开连接！"); 
			}else if(conn!=null){
				conn.close();
				System.out.println("断开连接！");
			}
	         try {
					getResponse().getWriter().write("连接已断开！");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	 
	 
	public String getDeviceIp() {
		return deviceIp;
	}
	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public Integer getDevicePort() {
		return devicePort;
	}

	public void setDevicePort(Integer devicePort) {
		this.devicePort = devicePort;
	}

	public String getDeviceCmd() {
		return deviceCmd;
	}

	public void setDeviceCmd(String deviceCmd) {
		this.deviceCmd = deviceCmd;
	}

	public String getPwd1() {
		return pwd1;
	}

	public void setPwd1(String pwd1) {
		this.pwd1 = pwd1;
	}

	public String getPwd2() {
		return pwd2;
	}

	public void setPwd2(String pwd2) {
		this.pwd2 = pwd2;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Session getSess() {
		return sess;
	}

	public void setSess(Session sess) {
		this.sess = sess;
	}
	
	
	
	
	
	
/*	public String listDevice() throws Exception {
		if (pagination == null) {
			pagination = new Pagination();
		}
		deviceList = deviceManageService.getDevice(device, pagination);
		return SUCCESS;
	}*/
	
	
}
