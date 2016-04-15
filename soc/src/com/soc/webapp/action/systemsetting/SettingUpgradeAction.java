package com.soc.webapp.action.systemsetting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.soc.model.systemsetting.Setting;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.systemsetting.SettingService;
import com.soc.webapp.action.BaseAction;
import com.util.DES;
import com.util.FileUtil;
import com.util.FileruleCreateFile;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  yinhaiping
 * @version  [版本号, 2012-12-3]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SettingUpgradeAction extends BaseAction
{
    private SettingService settingManager;
    
    // COLLECTOR_PATH 文件路径
    private final static String COLLECTOR_PATH = "/usr/local/tomcat-upgrade/webapps/soc-upgrade/upgrade_java/";
    
    private final static String SOC_SQL = "soc.sql";
    
    private final static String COLLECTOR_TAR = "soc.tar.gz";
    
    private final static String COLLECTOR_VERSION = "soc.version";
    
    private final static String TOMCAT6 = "tomcat6.tar";
    
    private final static String AGENT_TAR= "agent.tar.gz";
    
    private final static String UPGRADE_FILE="upgrade.txt";
  
    private File upTar;
    
    private String upTarFileName;
    
    private String upTarContentType;
    
    private String versionNum;
    
    private String upgradeDate;
    
    private boolean upStatr = false; //上传是否成功
    
    private String fileName; //上传文件名
 // 审计业务类
 	private AuditService auditManager;
    
    public String display()
    {
        BufferedReader br = null;
        
        String path = ServletActionContext.getServletContext().getRealPath("/pages/commons/taglibs.jsp");
        try
        {
            FileInputStream fis = new FileInputStream(new File(path));
            br = new BufferedReader(new InputStreamReader(fis));
            String lineString = "";
            while ((lineString = br.readLine()) != null)
            {
                if (lineString.contains("Ver"))
                {
                    versionNum = lineString.substring(lineString.indexOf("Ver"), lineString.indexOf("/") - 1);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (br != null)
                {
                    br.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        
        upgradeDate = settingManager.queryByKey("upgrade_date");
        
        return SUCCESS;
    }
    
    public String upTar()
    {
        if (upTar != null && upTarFileName != null)
        {
            String path = ServletActionContext.getServletContext().getRealPath("/upgrade");
            
            File upgrade = new File(new File(path), upTarFileName);
            
            fileName = upTarFileName;
            
            try
            {
                upStatr = FileUtil.copyFile(upTar, upgrade);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return SUCCESS;
    }
    
    public void upgrade()
    {
        BufferedReader br = null;
        
        String versionPath = ServletActionContext.getServletContext().getRealPath("/pages/commons/taglibs.jsp");
       
        String version = null;
        try
        {
            FileInputStream fis = new FileInputStream(new File(versionPath));
            br = new BufferedReader(new InputStreamReader(fis));
            String lineString = "";
            while ((lineString = br.readLine()) != null)
            {
                if (lineString.contains("Ver"))
                {
                    version =
                        lineString.substring(lineString.indexOf("Ver"), lineString.indexOf("/") - 1).replaceAll(" ",
                            "-");
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (br != null)
                {
                    br.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        String path = ServletActionContext.getServletContext().getRealPath("/upgrade");
        
        File upgrade = new File(new File(path), fileName);      
        
        String newFileName = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        
        // 解密升级文件并重命令为当前日期
        DES des = new DES();
      
        String password ="J1d1Sec-SOC-"+version +"00000000000000";
        
       System.out.println("length="+password.length());
        
        try{
        
        des.decrypt(upgrade, "J1d1Sec-SOC-" + version + "00000000000000", path + "/" + newFileName + ".tar");
        
        }
        
        catch(Exception e)
        {
            System.out.println("des falid......... liuyanxu");
        }
        
        File file = new File(path + "/" + newFileName + ".tar");
        
        if(file.exists())
        {
            // 删除用户上传的升级包
            upgrade.delete();
            
            Runtime runtime = Runtime.getRuntime();
            
            try
            {
                
                runtime.exec("chmod 755 "+ path + "/" + newFileName + ".tar");
                
                String tarcomm = "tar vxf "+ path +"/"+ newFileName+".tar -C " + path;
                
                runtime.exec(tarcomm);
                
                File f = new File(path);
                
                //读取解包目录下的升级文件
                if (f.isDirectory())
                {
                    int num = 0;
                    while(true)
                    {
                        File[] files = f.listFiles();
                        
                        if (files.length > 1)
                        {
                            break;
                        }
                        else
                        {
                            // 休息2分钟等待文件解包
                            if(num < 5)
                            {
                                try
                                {
                                    Thread.sleep(3000);
                                    num++;
                                }
                                catch (InterruptedException e)
                                {
                                    e.printStackTrace();
                                }
                            }
                            else
                            {
                                break;
                            }
                        }
                        
                    }
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            // 删除升级包
            new File(path+"/"+newFileName + ".tar").delete();
            
            File[] upgradeFiles = null ;
            
            File tomcat6 = null;
            
            upgradeFiles = new File(path).listFiles()  != null ? new File(path).listFiles()  : new File[0] ; 
            
            System.out.println(upgradeFiles.length+"----liuyanxulength");
           
            for (File ff : upgradeFiles)
            {
                System.out.println("123----liuyanxu");
            	System.out.println(ff.getName()+"123-liuyanxu");
            	System.out.println(COLLECTOR_VERSION+"123-liuyanxu");
            	//读取升级内容文件
            	System.out.println(ff.getName());
            	if(UPGRADE_FILE.equalsIgnoreCase(ff.getName())){
            	 	System.out.println("这里是升级内容文件！");
            		FileInputStream fileInputStream=null;
            		BufferedReader bReader=null;
            		List<String> fieldList = new ArrayList<String>();
					try {
						fileInputStream = new FileInputStream(ff);
						bReader  = new BufferedReader(new InputStreamReader(fileInputStream));
						String lineString="";
						try {
							fieldList.add("升级内容如下：");
							System.out.println("开始读取升级内容文件！");
							while((lineString=bReader.readLine())!=null){
								System.out.println(lineString);
								fieldList.add(lineString);
							}
							System.out.println("开始添加至内部审计中");
							System.out.println("----详细内容展示----");
							System.out.println( (super.getSession().getAttribute("SOC_LOGON_USER").toString()));
							System.out.println(super.getRequest().getRemoteAddr());
							System.out.println(fieldList);
							auditManager.insertBySystemOperator(((User) super.getSession()
									.getAttribute("SOC_LOGON_USER")).getUserId(),"升级系统",super
									.getRequest().getRemoteAddr(), fieldList);
							//Thread.sleep(3000);
						} catch (IOException e) {
							e.printStackTrace();
						} /*catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}finally{
						if(bReader!=null){
							try {
								bReader.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						if(fileInputStream!=null){
							try {
								fileInputStream.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
					System.out.println("运行到这里了——————————————————————————————————-");
					boolean b=ff.delete();
					System.out.println("文件存放路径是："+ff.getPath());
					/*String command = "rm -rf /"+ff.getPath();
					try
                    {
                        runtime.exec(command);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }*/
					//System.out.println("是否删除："+b);
            	}
                // 移动升级tar.gz包
                if (COLLECTOR_TAR.equalsIgnoreCase(ff.getName()))
                {
                    System.out.println("移动升级tar.gz包");
                	System.out.println("soc.tar.gz"+ff.getPath());
                    
                    String command = "mv "+ff.getPath()+" "+COLLECTOR_PATH;
                    
                    try
                    {
                        runtime.exec(command);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }else if(AGENT_TAR.equalsIgnoreCase(ff.getName()))
                {
                    String command ="mv "+ff.getPath()+" "+COLLECTOR_PATH;
                  
                    try
                    {
                        runtime.exec(command);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                	  
                }else if(COLLECTOR_VERSION.equalsIgnoreCase(ff.getName()))
                {
                	
                	System.out.println(ff.getName()+"23--liuyanxu");
                	FileruleCreateFile.modifinyConfigFile(ff.getPath(), COLLECTOR_PATH);
                    
                    String command = "mv "+ff.getPath()+" "+COLLECTOR_PATH;
                    
                    try
                    {
                        runtime.exec(command);
                       
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    
                }else if (TOMCAT6.equalsIgnoreCase(ff.getName()))
                {
                    tomcat6 = ff;
                    
                } else if(SOC_SQL.equalsIgnoreCase(ff.getName()))
                {
                    // 存储执行导入sql的脚本
                    File upgradeFile = new File("/home/database-upgrade/");
                    
                    if (!upgradeFile.exists())
                    {
                        try
                        {
                            runtime.exec("mkdir /home/database-upgrade/");
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    
                    try
                    {
                        runtime.exec("chmod 777 /home/database-upgrade/");
                    }
                    catch (IOException e2)
                    {
                        e2.printStackTrace();
                    }
                    
                    // 写出升级数据库脚本文件
                    StringBuffer buffer = new StringBuffer();
                    
                    
                    
                   // buffer.append("#!/usr/bin/expect -f" + "\n");
                    
                   // buffer.append("spawn bash -c "+"\"/opt/PostgreSQL/9.2/bin/psql -d soc -U soc\"\n");
                    
                   // buffer.append("expect " + "\"Password for user soc: \""+ "\n");
                    
                  //  buffer.append("send " + "\"1qaz@WSX\\r\"\n");
                    
                  //  buffer.append("expect " +"\"soc=# \""+"\n");
                    
                   /* buffer.append("send " +"\"DROP SCHEMA soc CASCADE;\\r\"\n" );
                    
                    buffer.append("expect " +"\"soc=# \""+"\n");
                    
                    buffer.append("send "+"\" CREATE SCHEMA soc AUTHORIZATION soc;\\r\"\n");
                    
                    buffer.append("expect " +"\"soc=# \""+"\n");
                    
                    buffer.append("send "+"\"\\q\\r\"\r");
                    
                    buffer.append("interact\n");*/
                    
                    buffer.append("spawn bash -c "
                        + "\"/opt/PostgreSQL/9.2/bin/psql -U soc soc < /usr/local/tomcat6/webapps/soc/upgrade/"
                        + ff.getName() + "\"\n");
                    
                    buffer.append("expect " + "\"Password for user soc: \"" + "\n");
                    
                    buffer.append("send " + "\"soc\\r\"\n");
                    
                    buffer.append("interact");
                    
                    String command = "mv "+ff.getPath()+" /home/database-upgrade/";
                    
                    try {
                    	
						Runtime.getRuntime().exec(command);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    
                    // 写文件
                  /*  try
                    {
                        
                        OutputStream os = new FileOutputStream("/home/database-upgrade/" + ff.getName() + ".sh");
                        
                        
                        DataOutputStream dos = new DataOutputStream(os);
                        
                        try
                        {
                            dos.writeBytes(new String(buffer.toString().getBytes(), "utf-8"));
                        }
                        catch (IOException e1)
                        {
                            e1.printStackTrace();
                            
                            //System.out.println("write files fail .....liuyanxu");
                        }
                        try
                        {
                            os.close();
                        }
                        catch (IOException e)
                        {
                        }
                    }
                    catch (FileNotFoundException e)
                    {
                    }
                    
                    // 执行升级数据库文件
                    try
                    {
                        runtime.exec("chmod 755 /home/database-upgrade/" + ff.getName() + ".sh");
                        runtime.exec("/home/database-upgrade/" + ff.getName() + ".sh");
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }*/
                }
            }
            if (tomcat6 != null)
            {
                
               System.out.println("it is the last buzhou liuyanxu ---------------------------");
                
                Setting setting = new Setting();
                setting.setKey("upgrade_date");
                SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
                String upgradeDate = dFormat.format(new Date());
                setting.setValue(upgradeDate);
                settingManager.updateByKey("upgrade_date", setting);
                
                log.info("updating date...done！");
                
                // 写出一个空文件通知升级程序升级系统
                File upFlagPath = new File("/usr/local/tomcat6/webapps/soc/upgrade/upgrade"); 
                
                FileOutputStream fos = null;
                try
                {
                    fos = new FileOutputStream(upFlagPath);
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    if(fos != null)
                    {
                        try
                        {
                            fos.close();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
                try
                {
                    getResponse().getWriter().write("tomcat6");
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                // 重启服务器升级系统
                try
                {
                    runtime.exec("reboot");
                    System.out.println("123");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    
    public SettingService getSettingManager()
    {
        return settingManager;
    }
    
    public void setSettingManager(SettingService settingManager)
    {
        this.settingManager = settingManager;
    }
    
    public File getUpTar()
    {
        return upTar;
    }
    
    public void setUpTar(File upTar)
    {
        this.upTar = upTar;
    }
    
    public String getUpTarFileName()
    {
        return upTarFileName;
    }
    
    public void setUpTarFileName(String upTarFileName)
    {
        this.upTarFileName = upTarFileName;
    }
    
    public String getVersionNum()
    {
        return versionNum;
    }
    
    public void setVersionNum(String versionNum)
    {
        this.versionNum = versionNum;
    }
    
    public String getUpgradeDate()
    {
        return upgradeDate;
    }
    
    public void setUpgradeDate(String upgradeDate)
    {
        this.upgradeDate = upgradeDate;
    }
    
    public boolean isUpStatr()
    {
        return upStatr;
    }
    
    public void setUpStatr(boolean upStatr)
    {
        this.upStatr = upStatr;
    }
    
    public String getFileName()
    {
        return fileName;
    }
    
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}

	public String getUpTarContentType() {
		return upTarContentType;
	}

	public void setUpTarContentType(String upTarContentType) {
		this.upTarContentType = upTarContentType;
	}
	
    
}