package com.soc.service.systemsetting.impl;

import global.GlobalThreadPool;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.soc.dao.systemsetting.SettingArchiveDao;
import com.soc.model.alert.alertMessage.AlertMessage;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.systemsetting.SettingArchive;
import com.soc.model.user.User;
import com.soc.service.impl.BaseServiceImpl;
import com.soc.service.systemsetting.SettingArchiveService;
import com.soc.service.systemsetting.SettingService;
import com.soc.webapp.listener.CommunicationContextListener;
import com.util.Base64;
import com.util.DES;
import com.util.FileUtil;
import com.util.SendEmail;
import com.util.StringUtil;
import com.util.ZipUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  yinhaiping
 * @version  [版本号, 2012-12-6]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SettingArchiveServiceImpl extends BaseServiceImpl implements SettingArchiveService
{
    private SettingArchiveDao archiveDao;
    //系统设置
    private SettingService settingManager;
    /**
     * {查询归档的所有的表}
     */
    @Override
    public SearchResult queryPage(Map map, Page page)
    {
        int rowsCount = archiveDao.associationPage(map);
        page.setTotalCount(rowsCount);
        List<SettingArchive> list = archiveDao.queryArchiveList(map, page.getStartIndex(), page.getPageSize());
        
        SearchResult sr = new SearchResult();
        sr.setList(list);
        sr.setPage(page);
        return sr;
    }
    
    /**
     * {更新}
     */
    @Override
    public String updataById(SettingArchive archive, long archiveStatus, long checkid)
    {
        Runtime run = Runtime.getRuntime();
        StringBuffer strBuff = new StringBuffer();
        SettingArchive archivename = archiveDao.queryById(checkid);
        String path = archiveDao.queryById(checkid).getArchivePath();
        //System.out.println("--------开始执行归档----------");
        //执行归档
        if(path.equals("") || path ==null)
        {
            path ="/opt";
        }
        if (archiveStatus == 0)
        {
            try
            {
            	 //System.out.println("--------开始创建文件夹----------");
            	run.exec("mkdir "+path+"/archivefile");
                run.exec("mkdir "+path+"/archivesql");
                run.exec("mkdir "+path+"/archiveZip");
                run.exec("chmod 777 "+path+"/archivefile/");
                run.exec("chmod 777 "+path+"/archivesql/");
                run.exec("chmod 777 "+path+"/archiveZip");
            }
            catch (IOException e2)
            {
                e2.printStackTrace();
            }
            //写文件
            
            //System.out.println("--------开始写脚本----------");
            strBuff.append("#!/usr/bin/expect -f"+"\n");
            strBuff.append("spawn bash -c "+"\"/opt/PostgreSQL/9.2/bin/pg_dump -U soc -t tbl_"+archivename.getArchiveName()+"_original_log"+" soc > "+path+"/archivesql/tbl_"+archivename.getArchiveName()+".sql\""+"\n");
            strBuff.append("expect "+"\"Password: \""+"\n");
            strBuff.append("send "+"\"soc\\r\"\n");
            strBuff.append("interact\n");
            strBuff.append("exit 0");
         //   strBuff.append("send_user \"DatabasesImportOk\n\"");
            //System.out.println(strBuff.toString());
            //System.out.println("------------结束---------------");
         // 写文件
            try
            {
            	//System.out.println("--------生成脚本----------");
                OutputStream os = new FileOutputStream(path+"/archivefile/"+archivename.getArchiveName()+".sh");
                DataOutputStream dos = new DataOutputStream(os);
                try
                {
                    dos.writeBytes(new String(strBuff.toString().getBytes(), "UTF-8"));
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
                try
                {
                    os.close();
                }
                catch (IOException e)
                {
                    //System.out.println(e);
                }
                //System.out.println("--------结束----------");
            }
            catch (FileNotFoundException e)
            {
            }
            //执行文件
            try
            {
            	//System.out.println("--------给脚本赋权限----------");
            	run.exec("chmod 755 "+path+"/archivefile/"+archivename.getArchiveName()+".sh");
                Process pro = run.exec(path+"/archivefile/"+archivename.getArchiveName()+".sh");
                BufferedReader inputBufferedReader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
                String shLine = null;
                
                while ((shLine = inputBufferedReader.readLine()) != null) {
                    /* if(line.equals("DatabasesImportOk")){
                        flag = true;
                        break;
                    }*/
                }
                pro.waitFor();
                
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
           
            File file = new File(path+"/archivesql/"+"tbl_"+archivename.getArchiveName()+".sql");
            if (!file.exists()) {
                return "archiveErr0";
            }else{
            boolean resultOfZip = ZipUtil.doZip(new File(path+"/archivesql/"), path+"/archivefile/"+archivename.getArchiveName()+".zip");
            
            while(resultOfZip = true){
                break;
            }
            archiveDao.updataById(archive);
            }
        }
        //执行下载
        if (archiveStatus == 2)
        {
//            SettingArchive archivename = archiveDao.queryById(checkid);
//            filePath = "/home/archivedate/" + archivename.getArchiveName() + ".sql";
//            filePath = "D:/workspace/" + archivename.getArchiveName() + ".sql";
            try
            {
//                run.exec("rm -rf /home/archiveZip/");
//                run.exec("mkdir /home/archiveZip");
            	 run.exec("rm -rf "+path+"/archiveDownload/");
                 run.exec("mkdir "+path+"/archiveCache");
                 run.exec("mkdir "+path+"/archiveDownload");
                 run.exec("cp "+path+"/archivefile/"+archivename.getArchiveName()+".zip"+" "+path+"/archiveCache/");
                 Thread.sleep(3000);
                 boolean resultOfZip = ZipUtil.doZip(new File(path+"/archiveCache/"), path+"/archiveDownload/archive.zip");
                 while (resultOfZip = true) {
                     break;
                 }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            archiveDao.updataById(archive);
//            boolean resultOfZip = ZipUtil.doZip(new File("F:/apache-tomcat-6.0.16/webapps/compliance/template/te"), "E:/ZipTest/bb.zip");
//            return filePath;
        }
        
        //执行恢复
        if (archiveStatus == 3)
        {
                try
                {
                	run.exec("rm -rf "+path+"/archiveZip/");
                    run.exec("mkdir "+path+"/archiveZip");
                }
                catch (IOException e2)
                {
                    e2.printStackTrace();
                }
                
                File file = new File(path+"/archivefile/"+archivename.getArchiveName()+".zip");
                boolean resultOfUnZip = ZipUtil.unZip(file, path+"/archiveZip/", false);
                while(resultOfUnZip = true){
                    break;
                }
                
                strBuff.append("#!/usr/bin/expect -f"+"\n");
                strBuff.append("spawn bash -c "+"\"/opt/PostgreSQL/9.2/bin/psql -h localhost -U soc soc > "+path+"/archiveZip/"+archivename.getArchiveName()+".sql\""+"\n");
                strBuff.append("expect "+"\"Password: \""+"\n");
                strBuff.append("send "+"\"soc\\r\"\n");
                strBuff.append("interact\n");
                strBuff.append("exit 0");
             // 写文件
                try
                {
                    OutputStream os = new FileOutputStream(path+"/archivefile/"+archivename.getArchiveName()+".sh");
                    DataOutputStream dos = new DataOutputStream(os);
                    try
                    {
                        dos.writeBytes(new String(strBuff.toString().getBytes(), "UTF-8"));
                    }
                    catch (IOException e1)
                    {
                        e1.printStackTrace();
                    }
                    try
                    {
                        os.close();
                    }
                    catch (IOException e)
                    {
                        //System.out.println(e);
                    }
                }
                catch (FileNotFoundException e)
                {
                }
                
              //执行文件
                try
                {
                	run.exec("chmod 755 "+path+"/archivefile/"+archivename.getArchiveName()+".sh");
                    Process pro = run.exec(path+"/archivefile/"+archivename.getArchiveName()+".sh");
                    BufferedReader inputBufferedReader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
                    String shLine = null;
                    while ((shLine = inputBufferedReader.readLine()) != null) {
                        
                    }
                    pro.waitFor();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                
                archiveDao.updataById(archive);
        }
        
        //执行清档
        if (archiveStatus == 4)
        {
            try
            {
            	run.exec("rm -rf "+path+"/archivefile/" + archivename.getArchiveName() + ".sql");
                archiveDao.updataById(archive);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        //delete
        try
        {
        	run.exec("rm -rf "+path+"/archiveCache");
            run.exec("rm -rf "+path+"/archivesql/");
            run.exec("rm -rf "+path+"/archiveZip/");
            run.exec("rm -rf "+path+"/archivefile/"+archivename.getArchiveName()+".sh");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * {插入数据}
     */
    @Override
    public void createArchive(String tableName)
    {
    	//这里要获得归档文件夹的大小 因为这里是每天生成归档信息的信息而不是
    	//真正备份sql的方法 所以这里要

    	//按照名字去查询 找到类取得他的路径
    	Runtime runtime = Runtime.getRuntime();
    	
    		String archiveAutoPath = settingManager.queryByKey("archiveAutoPath");
    		if (!new File(archiveAutoPath).exists()) {
				try {
					FileUtil.createFolder(archiveAutoPath);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
    		long totalCapacity =  Long.parseLong(settingManager.queryByKey("totalCapacity"))*1024;
    		SettingArchive	archive = new SettingArchive();
            archive.setArchiveName(tableName);
            archive.setArchiveStatus(1);
            archive.setTotalCapacity(totalCapacity);
            archive.setArchiveDate(new Date().getTime());
            archive.setArchivePath(archiveAutoPath);
            archive.setThresholds(Long.parseLong(settingManager.queryByKey("thresholds")));
            archiveDao.createArchive(archive);
		//}
    }
    /**
     * {@自动归查询}
     */
    @Override
    public List<SettingArchive> selectArchiveAuto(Map<String,Long> archive)
    {
        return archiveDao.selectArchiveAuto(archive);
    }
    /**
     * {@自动归档}
     */
    @Override
    public void archiveAuto(List<SettingArchive> selectArchive)
    {
        Runtime run = Runtime.getRuntime();
        StringBuffer strBuff = new StringBuffer();
        for(SettingArchive checkid : selectArchive){
        	//归档路径
            String archiveAutoPath = checkid.getArchivePath();
            SettingArchive archivename = archiveDao.queryById(checkid.getArchiveId());
        //执行归档
            try
            {
                run.exec("mkdir "+archiveAutoPath+"/archivefile");
                run.exec("mkdir "+archiveAutoPath+"/archivesql");
                run.exec("mkdir "+archiveAutoPath+"/archiveZip");
                run.exec("chmod 777 "+archiveAutoPath+"/archivefile/ archivefile/ archiveZip/");
            }
            catch (IOException e2)
            {
                e2.printStackTrace();
            }
            //写文件
            strBuff.append("#!/usr/bin/expect -f"+"\n");
            strBuff.append("spawn bash -c "+"\"/opt/PostgreSQL/9.2/bin/pg_dump -U soc -t tbl_"+archivename.getArchiveName()+" soc > /home/archivesql/tbl_"+archivename.getArchiveName()+".sql\""+"\n");
            strBuff.append("expect "+"\"Password: \""+"\n");
            strBuff.append("send "+"\"soc\\r\"\n");
            strBuff.append("interact\n");
            strBuff.append("send_user \"DatabasesImportOk\n\"");
            
         // 写文件
            try
            {
                OutputStream os = new FileOutputStream(archiveAutoPath+"/archivefile/"+archivename.getArchiveName()+".sh");
                DataOutputStream dos = new DataOutputStream(os);
                try
                {
                    dos.writeBytes(new String(strBuff.toString().getBytes(), "GBK"));
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
                try
                {
                    os.close();
                }
                catch (IOException e)
                {
                    //System.out.println(e);
                }
            }
            catch (FileNotFoundException e)
            {
            }
            //执行文件
            try
            {
                run.exec("chmod 755 "+archiveAutoPath+"/archivefile/"+archivename.getArchiveName()+".sh");
                Process pro = run.exec(archiveAutoPath+"/archivefile/"+archivename.getArchiveName()+".sh");
                BufferedReader inputBufferedReader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
                String shLine = null;
                while ((shLine = inputBufferedReader.readLine()) != null) {
                    
                }
                pro.waitFor();
                
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            File file = new File(archiveAutoPath+"/archivesql/tbl_"+archivename.getArchiveName()+".sql");
            boolean resultOfZip = ZipUtil.doZip(new File(archiveAutoPath+"/archivesql/"), archiveAutoPath+"/archivefile/"+archivename.getArchiveName()+".zip");
//            SettingArchive archivename = new SettingArchive();
            archivename.setArchiveId(selectArchive.get(0).getArchiveId());
            archivename.setArchiveStatus(selectArchive.get(0).getArchiveStatus());
            //取出本次归档的大小
          //查出文件大小
    		BufferedReader bufReader = null;
            String bufReadLineString = null;
            String[] arr  = null;
            try {
				bufReader = new BufferedReader(new InputStreamReader( Runtime.getRuntime().exec("du -sk "+archiveAutoPath).getInputStream()));
				while ((bufReadLineString = bufReader.readLine()) != null)
				{
					//返回的是1312321     文件夹名字 这里要获取前面的数字
					arr = bufReadLineString.split("/");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		long capacity =  Long.parseLong(arr[0].trim());
    		archivename.setCapacity(capacity);
    		//然后算出百分比
    		archivename.setPercent(Float.parseFloat(capacity+"")/(Float.parseFloat(this.settingManager.queryByKey("totalCapacity"))*1024));
    		//如果超过了告警阀值则
    		if (archivename.getPercent()*100>Float.parseFloat(this.settingManager.queryByKey("thresholds"))) {
    			emailHandling(this.settingManager.queryByKey("archiveMail"),archivename);
			}
    		archivename.setArchiveStatus(0);
            archiveDao.updataById(archivename);
        }
    }
    /**
	 * 处理发送邮件的内容信息
	 * 
	 */
	public void emailHandling(String archiveMail, SettingArchive archive) {
		StringBuffer message = new StringBuffer(); // 邮件内容
		StringBuffer userRealNames = new StringBuffer(); // 邮件接受人的真实姓名

		if (archive != null) {
			if (archiveMail.length() > 0) {
				message.append("管理员您好！<br/>");
				message.append("归档文件大小超出临界范围：具体情况如下：");
				message.append("<b style=\"margin-left:20px;\">SOC系归档信息：</b>");
				message.append("<table style=\"margin-left:20px;\" width=\"100%\"  cellspacing=\"0\" cellpadding=\"0\" >" +
						"<tr>" +
						"<td width=\"10%\" align=\"center\" style=\"border-top:1px solid;border-left:1px solid; background:#CCCCCC ;\">数据时间</td>" +
						"<td width=\"10%\" align=\"center\" style=\"border-top:1px solid;border-left:1px solid; background:#CCCCCC ;\">归档状态</td>"+
						"<td width=\"10%\" align=\"center\" style=\"border-top:1px solid;border-left:1px solid; background:#CCCCCC ;\">归档目录</td>" +
						"<td width=\"10%\" align=\"center\" style=\"border-top:1px solid;border-left:1px solid; background:#CCCCCC ;\">已用容量</td>" +
						"<td width=\"15%\" align=\"center\" style=\"border-top:1px solid;border-left:1px solid; background:#CCCCCC ;\">总共容量</td>" +
						"<td width=\"10%\" align=\"center\" style=\"border-top:1px solid;border-left:1px solid; background:#CCCCCC ;\">使用率</td>" +
						"<td width=\"10%\" align=\"center\" style=\"border-top:1px solid;border-left:1px solid; background:#CCCCCC ;\">告警的临界值</td>" +
						"</tr>");
				//归档状态
				String struts = ""; 
				switch (Integer.parseInt(archive.getArchiveStatus()+"")) {
				case 0:
					struts = "已归档";
					break;
				case 1:
					struts = "未归档";
					break;
				case 2:
					struts = "已下载";
					break;
				case 3:
					struts = "已恢复";
					break;
				case 4:
					struts = "已清理归档";
					break;
				}
				message.append("<tr>" +
						"<td  align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-bottom:1px solid;\">"
						+ archive.getArchiveName()
						+ "</td><td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-bottom:1px solid;border-right:1px solid;\">"
						+ struts
						+ "</td><td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-bottom:1px solid;border-right:1px solid;\">"
						+ archive.getArchivePath()
						+ "</td><td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-bottom:1px solid;border-right:1px solid;\">"
						+ archive.getCapacity()
						+ "K</td><td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-bottom:1px solid;border-right:1px solid;\">"
						+ archive.getTotalCapacity()
						+ "K</td><td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-bottom:1px solid;border-right:1px solid;\">"
						+ archive.getPercent()
						+ "%</td><td align=\"center\" style=\"border-top:1px solid;border-left:1px solid;border-bottom:1px solid;border-right:1px solid;\">"
						+ archive.getThresholds()
						+ "%</td></tr>");
				message.append("</table>");
				if (CommunicationContextListener.threadPoolId <= GlobalConfig.MAX_THREADPOOL_NUMBER) {
					CommunicationContextListener.threadPoolId++;
					GlobalThreadPool.pool.execute(creatThread(
							message.toString(), archiveMail.toString()));
					CommunicationContextListener.threadPoolId--;
				}
			}
				}
		
			
			message = new StringBuffer();
		}
		

	/**
	 * 发送邮件线程
	 * 
	 * @param message
	 * @param toEmailAdd
	 * @return Runable
	 */
	public Runnable creatThread(final String message, final String toEmailAdd) {
		return new Runnable() {
			public void run() {
				sendEmail(message, toEmailAdd);
			}
		};
	}

	/**
	 * {@inheritDoc}发送邮件
	 */
	public void sendEmail(String message, String toEmialAdds) {
		// 获得邮箱参数
		String smtpServer = settingManager.queryByKey("smtp_server");
		String emailNicky = settingManager.queryByKey("email_nicky"); // 发件人昵称
		String smtpPort = settingManager.queryByKey("smtp_port");
		String smtpIsneedauth = settingManager.queryByKey("smtp_isneedauth");
		String smtpIsneedssl = settingManager.queryByKey("smtp_isneedssl");
		String emailUsername = settingManager.queryByKey("email_username");
		String emailPass = settingManager.queryByKey("email_password");
		String emailAddress = settingManager.queryByKey("email_address");
		if (StringUtil.isNotBlank(smtpServer)
				&& StringUtil.isNotBlank(emailNicky)
				&& StringUtil.isNotBlank(smtpPort)
				&& StringUtil.isNotBlank(smtpIsneedauth)
				&& StringUtil.isNotBlank(emailUsername)
				&& StringUtil.isNotBlank(emailPass)
				&& StringUtil.isNotBlank(emailAddress)) {
			String emailPassword = DES.getEncrypt(Base64
					.decodeString(emailPass));
			String[] emailAdds;
			if (toEmialAdds.indexOf(",") != -1) {
				emailAdds = toEmialAdds.toString().split(",");
			} else {
				emailAdds = new String[1];
				emailAdds[0] = toEmialAdds.toString();
			}
			for (String emailAdd : emailAdds) {
				SendEmail sendEmail = new SendEmail(smtpServer);
				sendEmail.setBody(message);
				sendEmail.setSubject("告警信息");
				sendEmail.setTo(emailAdd);
				if (StringUtil.isNotBlank(smtpIsneedssl)) {
					// 设置发送邮件服务器是否需要安全连接(SSL)
					int isssl = Integer.parseInt(smtpIsneedssl);
					if (isssl == 1) {
						sendEmail.setNeedSsl(true);
						sendEmail.setSSLSecurity(smtpPort);
					} else {
						// sendEmail.setNeedSsl(false);
						sendEmail.setSmtpPort(smtpPort);
					}
				} else {
					sendEmail.setNeedSsl(false);
					sendEmail.setSmtpPort(smtpPort);
				}
				// 发送邮件服务器是否需要身份验证
				if (Integer.parseInt(smtpIsneedauth) == 1) {
					sendEmail.setNeedAuth(true);
				} else {
					sendEmail.setNeedAuth(false);
				}
				sendEmail.setNamePass(emailUsername, emailPassword);
				sendEmail.setFrom(emailAddress);
				sendEmail.sendout();
			}
		}
	}
	
    @Override
	public SettingArchive queryById(long archive) {
    	return archiveDao.queryById(archive);
	}

	public SettingArchiveDao getArchiveDao()
    {
        return archiveDao;
    }
    
    public void setArchiveDao(SettingArchiveDao archiveDao)
    {
        this.archiveDao = archiveDao;
    }

	public SettingService getSettingManager() {
		return settingManager;
	}

	public void setSettingManager(SettingService settingManager) {
		this.settingManager = settingManager;
	}

}