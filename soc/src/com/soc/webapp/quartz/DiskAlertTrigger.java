package com.soc.webapp.quartz;

import java.io.IOException;
import java.util.List;

import com.soc.model.conf.GlobalConfig;
import com.soc.service.systemsetting.SettingService;
import com.soc.service.systemsetting.impl.SettingServiceImpl;
import com.util.Base64;
import com.util.DES;
import com.util.OSUtil;
import com.util.StringUtil;
import com.util.SystemInfoHandle;
import com.util.ThreadPool;
import com.util.email.SendEmail;

/**
 * 磁盘空间报警
 * @author 王亚男
 *
 */
public class DiskAlertTrigger
{
    
    //线程池
    public static ThreadPool threadPool = null;
    
    //系统设置业务类
    private SettingService settingManager = new SettingServiceImpl();
    
    //磁盘信息
    private List<String[]> diskInfo = null;
    
    //判断是否已发过邮件
    public static boolean flagEmail = false;
    public void diskAlertTask()
        throws IOException
    {
        
        //System.out.println("DiskAlertTask start...");
        String diskAlertUse = settingManager.queryByKey("diskalert_use");
        //改动过--刘延旭
        String emailStr = settingManager.queryByKey("email_disk");
        
        if (StringUtil.equals(OSUtil.getOSName().trim().toLowerCase(), "linux"))
        {
            if (diskAlertUse != null)
            {
                if (diskAlertUse.equals("1"))
                {
                    
                    Double diskalertCriticalPoint =
                        Double.valueOf(settingManager.queryByKey("diskalert_criticalPoint").toString());
                    if (diskalertCriticalPoint != null)
                    {
                        diskInfo = SystemInfoHandle.getDiskInfo();
                        
                        String[] str = diskInfo.get(0);
                        
                        if (str == null)
                        {
                            System.out.println("命令未执行...");
                        }
                        else
                        {
                            double newDiskUse = Double.parseDouble(str[4].split("%")[0]);
                            
                           // System.out.println("oldDiskUse!=0, newDiskUse=" + newDiskUse + ", diskalertCriticalPoint="+ diskalertCriticalPoint + ", flagEmail=" + flagEmail);
                            
                            if (newDiskUse >= diskalertCriticalPoint)
                            {
                            	GlobalConfig.diskFalg=false;
                                if (flagEmail == false)
                                {
                                    System.out.println("execute createThread methos before........");
                                    threadPool = new com.util.ThreadPool(1);
                                    try
                                    {
                                        Thread.sleep(100);
                                    }
                                    catch (InterruptedException e)
                                    {
                                        e.printStackTrace();
                                    }
                                    System.out.println("active pool count is:" + threadPool.activeCount());
                                    threadPool.execute(createThread());
                                    threadPool.waitFinish();
                                    threadPool.closePool();
                                    //System.out.println("active pool count is:" + threadPool.activeCount());
                                   // System.out.println("execute createThread methos after........");
                                  // flagEmail = true;
                                }
                            }else{
                            	  GlobalConfig.diskFalg=true;
                            }
                        }
                    }
                    
                }
            }
            else
            {
               // System.out.println("未启用磁盘空间告警功能...");
            }
        }
        else
        {
           // System.out.println("磁盘空间告警功能没有部署在linux系统上...");
        }
    }
    
    
    public Runnable createThread()
    {
        return new Runnable()
        {
            public void run()
            {
                try
                {
                    sendEmail("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您的磁盘已达到临界点  "
                        + settingManager.queryByKey("diskalert_criticalPoint") + "%，磁盘信息如下表：");
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
    }
    
    /**
     * 报表title样式
     * @param item报表名称
     * @param grade表格编号
     * @return
     */
    public String chapter(String[] item, String grade)
    {
        
        StringBuffer sb = new StringBuffer();
        sb.append("<TR>");
        for (int i = 0; i < item.length; i++)
        {
            if ("0".equals(grade))
            {
                sb.append("<TD class='z" + grade + "' align='center'><B>" + item[i] + "</B></TD>");
            }
            else
            {
                sb.append("<TD class='z" + grade + "' align='center'>" + item[i] + "</TD>");
            }
        }
        return sb.toString();
    }
    
    /**
     * 报表table串生成
     * @param allReportTimer
     * @return
     */
    public String export(List<String[]> mailContentList)
    {
        StringBuffer html = new StringBuffer();
        String[] str = new String[6];
        str[0] = "文件系统";
        str[1] = "磁盘容量";
        str[2] = "已使用空间";
        str[3] = "未使用空间";
        str[4] = "使用率";
        str[5] = "挂载点";
        mailContentList.add(0, str);
        for (int i = 0; i < 2; i++)
        {
            // 报表以邮件正文方式发送
            html.append(chapter(mailContentList.get(i), String.valueOf(i)));
            
        }
        html.append("</TR></TABLE></CENTER>");
        return html.toString();
    }
    
    /**
     * 发送邮件
     * @param message
     */
    public void sendEmail(String message)
        throws IOException
    {
        
        //查询是否开启邮箱警告
        String mailAlarm = settingManager.queryByKey("alertTerrace");
        System.out.println("gsgsgs"+mailAlarm);
        if (StringUtil.isNotBlank(mailAlarm))
        {
            
            int mailAlarmInt = Integer.parseInt(mailAlarm);
           
            if (mailAlarmInt == 1)
            {
                //获得邮箱参数
                String smtpServer = settingManager.queryByKey("smtp_server");
                String emailNicky = settingManager.queryByKey("email_nicky");
                String smtpPort = settingManager.queryByKey("smtp_port");
                String emailStr = settingManager.queryByKey("email_disk");//改动过--刘延旭
                emailStr = emailStr.substring(0, emailStr.length());
                
                System.out.println(emailStr);
                
                String smtpIsneedauth = settingManager.queryByKey("smtp_isneedauth");
                String smtpIsneedssl = settingManager.queryByKey("smtp_isneedssl");
                String emailUsername = settingManager.queryByKey("email_username");
                DES des = new DES();
                String emailPass = settingManager.queryByKey("email_password");
                String emailAddress = settingManager.queryByKey("email_address");
                //
                if (StringUtil.isNotBlank(smtpServer) && StringUtil.isNotBlank(emailNicky)
                    && StringUtil.isNotBlank(smtpPort) && StringUtil.isNotBlank(smtpIsneedauth)
                    && StringUtil.isNotBlank(emailUsername) && StringUtil.isNotBlank(emailPass)
                    && StringUtil.isNotBlank(emailAddress))
                {
                    String emailPassword = DES.getEncrypt(Base64.decodeString(emailPass));
                    
                    if (emailStr.indexOf(",") == -1)
                    {
                       try
                        {
                            Thread.sleep(20000);
                        }
                        catch (InterruptedException e)
                        {
                           e.printStackTrace();
                        }
                        
                        SendEmail sendEmail = new SendEmail(smtpServer);
                        
                        String address = settingManager.queryByKey("card1_ip");
                        
                        if (!"".equals(address) && address != null)
                        {
                           // System.out.println("diskInfo.size:"+diskInfo.size());
                            sendEmail.setBody(emailNicky + "您好：<br/><br/>" + message
                                + "<br/><CENTER><TABLE border=1 cellPadding=0 cellSpacing=0 width=97% > "
                                + export(diskInfo) 
                                + "<br/><SPAN STYLE='PADDING-LEFT:85%;'><a href=\"https://"
                                + address + "/soc\" target=\"_blank\">访问soc主机</a></SPAN>");
                        }
                        else
                        {
                            sendEmail.setBody(emailNicky + "您好：<br/><br/>" + message
                                + "<br/><CENTER><TABLE border=1 cellPadding=0 cellSpacing=0 width=97% > "
                                + export(diskInfo) + "<br/><SPAN STYLE='PADDING-LEFT:70%;'>您还未设置soc主机网卡信息！！！</SPAN>");
                        }
                        sendEmail.setSubject("磁盘空间告警");
                        
                        sendEmail.setTo(emailStr);
                        
                        if (StringUtil.isNotBlank(smtpIsneedssl))
                        {
                            int isssl = Integer.parseInt(smtpIsneedssl);
                            if (isssl == 1)
                            {
                                sendEmail.setNeedSsl(true);
                                sendEmail.setSSLSecurity(smtpPort);
                            }
                            else
                            {
                            	 // sendEmail.setNeedSsl(false);
                                sendEmail.setSmtpPort(smtpPort);
                            }
                        }
                        else
                        {
                            sendEmail.setNeedSsl(false);
                            sendEmail.setSmtpPort(smtpPort);
                        }
                        if (Integer.parseInt(smtpIsneedauth) == 1)
                        {
                            sendEmail.setNeedAuth(true);
                        }
                        else
                        {
                            sendEmail.setNeedAuth(false);
                        }
                        sendEmail.setNamePass(emailUsername, emailPassword);
                        sendEmail.setFrom(emailAddress);
                        sendEmail.sendout();
                       // System.out.println("邮件已发送成功，发送至：" + emailStr);
                    }
                    else
                    {
                        String[] str = emailStr.split(",");
                        
                        for (String email : str)
                        {
                            SendEmail sendEmail = new SendEmail(smtpServer);
                            String address = settingManager.queryByKey("card1_ip");
                            if (!"".equals(address) && address != null)
                            {
                                sendEmail.setBody(emailNicky + "您好：<br/><br/>" + message
                                    + "<br/><CENTER><TABLE border=1 cellPadding=0 cellSpacing=0 width=97% >"
                                    + export(diskInfo) + "<br/><SPAN STYLE='PADDING-LEFT:85%;'><a href='https://"
                                    + address + "/soc' target=\"_blank\">访问soc主机</a></SPAN>");
                            }
                            else
                            {
                                sendEmail.setBody(emailNicky + "您好：<br/><br/>" + message
                                    + "<br/><CENTER><TABLE border=1 cellPadding=0 cellSpacing=0 width=97% >"
                                    + export(diskInfo) + "<br/><SPAN STYLE='PADDING-LEFT:70%;'>您还未设置soc主机网卡信息！！！</SPAN>");
                            }
                            sendEmail.setSubject("磁盘空间告警");
                            sendEmail.setTo(email);
                            if (StringUtil.isNotBlank(smtpIsneedssl))
                            {
                                int isssl = Integer.parseInt(smtpIsneedssl);
                                if (isssl == 1)
                                {
                                    sendEmail.setNeedSsl(true);
                                    sendEmail.setSSLSecurity(smtpPort);
                                }
                                else
                                {
                                   // sendEmail.setNeedSsl(false);
                                    sendEmail.setSmtpPort(smtpPort);
                                }
                            }
                            else
                            {
                                sendEmail.setNeedSsl(false);
                                sendEmail.setSmtpPort(smtpPort);
                            }
                            if (Integer.parseInt(smtpIsneedauth) == 1)
                            {
                                sendEmail.setNeedAuth(true);
                            }
                            else
                            {
                                sendEmail.setNeedAuth(false);
                            }
                            sendEmail.setNamePass(emailUsername, emailPassword);
                            sendEmail.setFrom(emailAddress);
                            sendEmail.sendout();
                            System.out.println("邮件已发送成功，发送至：" + emailStr);
                            try
                            {
                                Thread.sleep(20000);
                            }
                            catch (InterruptedException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
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
    
}
