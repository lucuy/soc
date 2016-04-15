/*
 * 文 件 名:  SystemCenterIpImpl.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  Jan 8, 2013
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.soc.service.systemsetting.impl;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.asset.AssetDao;
import com.soc.dao.systemsetting.SettingCollectorDao;
import com.soc.dao.systemsetting.SettingDao;
import com.soc.dao.systemsetting.rules.AnalysisRulesDao;
import com.soc.model.asset.Asset;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.systemsetting.Collector;
import com.soc.model.systemsetting.rules.AnalysisRules;
import com.soc.service.impl.BaseServiceImpl;
import com.soc.service.systemsetting.SystemCenterIpService;
import com.util.IpConvert;
import com.util.ReadAndWriteFileUtil;
import com.util.StringUtil;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  liuyanxu
 * @version  [版本号, Jan 8, 2013]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SystemCenterIpServiceImpl extends BaseServiceImpl implements SystemCenterIpService
{
    
    //采集器处理类
    private SettingCollectorDao settingCollectorDao;
    
    // 资产管理业务类
    private AssetDao assetDao;
    
    private AnalysisRulesDao analysisDao;
    
    private SettingDao settingDao;
    
    public SettingDao getSettingDao()
    {
        return settingDao;
    }
    
    public void setSettingDao(SettingDao settingDao)
    {
        this.settingDao = settingDao;
    }
    
    public AnalysisRulesDao getAnalysisDao()
    {
        return analysisDao;
    }
    
    public void setAnalysisDao(AnalysisRulesDao analysisDao)
    {
        this.analysisDao = analysisDao;
    }
    
    public AssetDao getAssetDao()
    {
        return assetDao;
    }
    
    public void setAssetDao(AssetDao assetDao)
    {
        this.assetDao = assetDao;
    }
    
    /** {@inheritDoc} */
    
    @Override
    public void updateConf()
    {
        // TODO Auto-generated method stub
        List<Collector> collectorList = new ArrayList<Collector>();
     
        //查询所有的采集器
        collectorList = settingCollectorDao.queryCollector();
        
        for (Collector collector : collectorList)
        {
            StringBuffer strBuf = new StringBuffer();
            StringBuffer agentBuf = new StringBuffer();
            StringBuffer host = new StringBuffer();
            StringBuffer assets = new StringBuffer();
            StringBuffer rules = new StringBuffer();
            int j = 0;
            
            int m = 0;
            // 查询当前采集器的所有资产
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("assetCollectorMac", collector.getCollectorMac());
            List<Asset> allAsset = assetDao.query(map);
            
            // 得到配置信息
            for (int i = 0; i < allAsset.size(); i++)
            {
                Asset asset = allAsset.get(i);
                //判断资产是否为启用状态
                if (asset.getAssetStatus() == 1)
                {
                    // 得到所有资产的信息
                    if (m == 0)
                    {
                        //strBuf.append("####################################################################################");
                        // strBuf.append("\n");
                        // strBuf.append("[asset]");
                        strBuf.append("\n");
                        
                        assets.append("assets = " + asset.getAssetId() + ":" + IpConvert.IpString(asset.getAssetIp())
                            + ":FF-FF-FF-FF-FF-FF" + ":" + asset.getAssetDeviceRules());
                    }
                    else
                    {
                        assets.append("," + asset.getAssetId() + ":" + IpConvert.IpString(asset.getAssetIp())
                            + ":FF-FF-FF-FF-FF-FF" + ":" + asset.getAssetDeviceRules());
                    }
                    
                    // 得到agent的配置信息
                    if (StringUtil.equals(asset.getAssetCollectType(), "代理"))
                    {
                        //agentBuf.append("\n");
                        agentBuf.append("\n");
                        agentBuf.append("[agent " + asset.getAssetMac() + "]");//mac地址为FFFFF..的agent的配置
                        agentBuf.append("\n");
                        agentBuf.append("ip = " + IpConvert.IpString(asset.getAssetIp()));
                        if (asset.getAssetPort() != null && (!asset.getAssetPort().equals("")))
                        {
                            agentBuf.append("\n");
                            agentBuf.append("port = " + asset.getAssetPort());
                        }
                        if (asset.getAssetLog() != null && (!asset.getAssetLog().equals("")))
                        {
                            agentBuf.append("\n");
                            agentBuf.append("interval=" + asset.getAssetLog());
                        }
                        if (asset.getAssetAPM() != null && (!asset.getAssetAPM().equals("")))
                        {
                            agentBuf.append("\n");
                            agentBuf.append("apm_port=" + asset.getAssetAPM());
                        }
                        if (asset.getAssetAPMMonitor() != null && (!asset.getAssetAPMMonitor().equals("")))
                        {
                            agentBuf.append("\n");
                            agentBuf.append("apm_interval=" + asset.getAssetAPMMonitor());
                        }
                        if (StringUtil.equals(asset.getAssetSupportDevice().trim(), "Linux"))
                        {
                            if (!StringUtil.equals(asset.getAssetPrivilegeCommand(), ""))
                            {
                                agentBuf.append("\n");
                                agentBuf.append("instruction_Ifo_files = " + asset.getAssetPrivilegeCommand());//特权命令
                                
                            }
                            
                            if (!StringUtil.equals(asset.getFile(), ""))
                            {
                                agentBuf.append("\n");
                                agentBuf.append("Monitor_linux_files = " + asset.getFile());//监视文件(linux)
                                
                            }
                            
                            if (!StringUtil.equals(asset.getDirectorise(), ""))
                            {
                                agentBuf.append("\n");
                                agentBuf.append("Monitor_linux_paths = " + asset.getDirectorise());//监视文件夹
                            }
                            
                        }
                        else if (StringUtil.equals(asset.getAssetSupportDevice().trim(), "Windows"))
                        {
                            if (!StringUtil.equals(asset.getFile(), ""))
                            {
                                agentBuf.append("\n");
                                agentBuf.append("Monitor_linux_files = " + asset.getFile());//监视文件(linux)
                                
                            }
                            
                            if (!StringUtil.equals(asset.getDirectorise(), ""))
                            {
                                agentBuf.append("\n");
                                agentBuf.append("Monitor_linux_paths = " + asset.getDirectorise());//监视文件夹
                            }
                        }
                    }
                    
                    // 得到snmp的配置信息
                    if (StringUtil.equals(asset.getAssetCollectType(), "snmp"))
                    {
                        if (j == 0)
                        {
                            host.append("host = " + IpConvert.IpString(asset.getAssetIp()) + ":"
                                + collector.getCollectorWalkPort() + ":" + asset.getOrganizationName() + ":"
                                + asset.getVersion());
                        }
                        else
                        {
                            host.append("," + IpConvert.IpString(asset.getAssetIp()) + ":"
                                + collector.getCollectorWalkPort() + ":" + asset.getOrganizationName() + ":"
                                + asset.getVersion());
                        }
                        j++;
                    }
                    m++;
                }
                
            }
            
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("analysisType", GlobalConfig.START_USE);
            List<AnalysisRules> analysisRulesList = analysisDao.queryAnalysis(paramMap);
            if (analysisRulesList != null)
            {
                for (int i = 0; i < analysisRulesList.size(); i++)
                {
                    AnalysisRules analysisRules = analysisRulesList.get(i);
                    if (i == (analysisRulesList.size() - 1))
                    {
                        rules.append(analysisRules.getAnalysisName());
                    }
                    else
                    {
                        rules.append(analysisRules.getAnalysisName() + ",");
                    }
                }
            }
            
            /*strBuf.append("[public]");
            strBuf.append("\n");*/
            /* try
             {
                 InetAddress ind = InetAddress.getLocalHost();
                 strBuf.append(" ip = " + ind.getHostAddress());//采集器IP地址
             }
             catch (UnknownHostException e2)
             {
                 e2.printStackTrace();
             }*/
            
            if (collector.getCollectNetwork() == 1)
            {
                strBuf.append("\n");
                strBuf.append("[forward]");
                
                //forwardIP
                String centerIp = settingDao.queryByKey("centerIp");
                
                //forwardPort
                String centerPort = settingDao.queryByKey("centerPort");
                
                if (StringUtil.isNotEmpty(centerIp))
                {
                    strBuf.append("\n");
                    strBuf.append("ip = " + centerIp);
                    
                }
                
                if (StringUtil.isNotEmpty(centerPort))
                {
                    strBuf.append("\n");
                    strBuf.append("dst_port = " + centerPort);
                }
                //upgradeIp
                String upgradeIp = settingDao.queryByKey("centerWwwUpIp");
                
                //upgradePort
                String upgradePort = settingDao.queryByKey("centerWwwUpPort");
                
                strBuf.append("\n");
                strBuf.append("[upgrade]");
                
                if (StringUtil.isNotEmpty(upgradeIp))
                {
                    
                    strBuf.append("\n");
                    strBuf.append("ip= " + upgradeIp);
                }
                if (StringUtil.isNotEmpty(upgradePort))
                {
                    strBuf.append("\n");
                    strBuf.append("port =" + upgradePort);
                }
            }
            //内网
            else
            {
                strBuf.append("\n");
                strBuf.append("[forward]");
                
                String centerNatIp = settingDao.queryByKey("centerNatIp");
                
                String centerNatPort = settingDao.queryByKey("centerNatPort");
                
                if (StringUtil.isNotEmpty(centerNatIp))
                {
                    
                    strBuf.append("\n");
                    strBuf.append("ip = " + centerNatIp);
                    
                }
                if (StringUtil.isNotEmpty(centerNatPort))
                {
                    strBuf.append("\n");
                    strBuf.append("dst_port = " + centerNatPort);
                }
                
                String centerNatUpIp = settingDao.queryByKey("centerNatUpIp");
                
                String centerNatUpPort = settingDao.queryByKey("centerNatUpPort");
                
                strBuf.append("\n");
                strBuf.append("[upgrade]");
                
                if (StringUtil.isNotEmpty(centerNatUpIp))
                {
                    strBuf.append("\n");
                    strBuf.append("ip= " + centerNatUpIp);
                    
                }
                if (StringUtil.isNotEmpty(centerNatUpPort))
                {
                    strBuf.append("\n");
                    strBuf.append("port =" + centerNatUpPort);
                }
            }
            if (agentBuf.length() > 0)
            {
                strBuf.append(agentBuf);
            }
            strBuf.append("\n");
            
            if (rules.length() > 0)
            {
                strBuf.append("[box]");
                strBuf.append("\n");
                strBuf.append("enable_rules = " + rules);//开启规则
                strBuf.append("\n");
            }
            
            strBuf.append("\n");
            strBuf.append("[syslog]");
            strBuf.append("\n");
            strBuf.append("port = " + collector.getCollectorLog());//采集器syslog端口
            strBuf.append("\n");
            strBuf.append("[snmptrap]");
            strBuf.append("\n");
            strBuf.append("port = " + collector.getCollectorTrip());//采集器snmptrap端口
            strBuf.append("\n");
            strBuf.append("\n");
            strBuf.append("[snmpwalk]");
            strBuf.append("\n");
            strBuf.append("oid = " + collector.getCollectorOid());
            strBuf.append("\n");
            
            if (host.length() > 0)
            {
                strBuf.append(host);
            }
            
            strBuf.append("\n");
            strBuf.append("interval = " + collector.getCollectorTime());
            
            if (assets.length() > 0)
            {
                strBuf.append("\n");
                strBuf.append("[asset]");
                strBuf.append("\n");
                strBuf.append(assets);
            }
            
            StringBuffer collectBuffer = new StringBuffer();
            // 写文件
            try
            {
                
                //String path = ServletActionContext.getServletContext().getRealPath("");
                
                OutputStream os =
                    new FileOutputStream("/usr/local/tomcat-upgrade/webapps/soc-upgrade/upgrade_java/"
                        + collector.getCollectorMac() + ".conf");
                
                OutputStream collectVersion =
                    new FileOutputStream("/usr/local/tomcat-upgrade/webapps/soc-upgrade/upgrade_java/"
                        + collector.getCollectorMac() + ".version");
                
                DataOutputStream dos = new DataOutputStream(os);
                
                DataOutputStream dosVersion = new DataOutputStream(collectVersion);
                File version = new File("/usr/local/tomcat-upgrade/webapps/soc-upgrade/upgrade_java/soc.version");
               //判断过滤器的规则文件是否存在
				String filterPath = "/usr/local/tomcat-upgrade/webapps/soc-upgrade/upgrade_java/filter.version";
                
				File filter = new File(filterPath);
				
				String filterDate="";
				
				if(filter.exists())
				{
					filterDate= ReadAndWriteFileUtil.readFileContent(filterPath);
				}
                
                if (version.exists())
                {
                    
                    /*InputStream is = new FileInputStream("/usr/local/tomcat-upgrade/webapps/soc-upgrade/upgrade/soc.version");
                    byte[] b = new byte[100];
                    try
                    {
                        is.read(b);
                        String socVersionInfo = new String(b, "utf-8");
                        if (StringUtil.isNotBlank(socVersionInfo))
                        {
                            String socVersion = socVersionInfo.substring(0, socVersionInfo.indexOf("$"));
                            
                            collectBuffer.append(socVersion);
                        }
                        
                    }
                    catch (IOException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }*/
                    
                    String content =
                        ReadAndWriteFileUtil.readFileContent("/usr/local/tomcat-upgrade/webapps/soc-upgrade/upgrade_java/soc.version");
                    collectBuffer.append(content);
                    /*try
                    {
                        is.close();
                    }
                    catch (IOException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }*/
                }
                
                collectBuffer.append("\n");
                collectBuffer.append("conf=" + new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format((new Date())));
                collectBuffer.append("\n");
                collectBuffer.append("ctl=" + collector.getCollectorStatus());
                collectBuffer.append("\n");
				collectBuffer.append(filterDate);
                
                //OutputStream os = new FileOutput
                /*OutputStream os =
                    new FileOutputStream("/usr/local/tomcat-upgrade/webapps/soc-upgrade/upgrade/"+collector.getCollectorMac()+".conf");
                InputStream is =
                    new FileInputStream("/usr/local/tomcat-upgrade/webapps/soc-upgrade/upgrade/soc.version");
                OutputStream sos =
                    new FileOutputStream("/usr/local/tomcat-upgrade/webapps/soc-upgrade/upgrade/soc.version");
                DataOutputStream dos = new DataOutputStream(os);
                try
                {
                    dos.writeBytes(new String(strBuf.toString().getBytes(), "UTF-8"));
                    //dos.writeUTF(strBuf.toString());
                    byte[] b = new byte[100];
                    is.read(b);
                    String socVersionInfo = new String(b, "utf-8");
                    if(StringUtil.isNotBlank(socVersionInfo))
                    {
                        if(socVersionInfo.indexOf("soc_version=") != -1)
                        {
                            String socVersion = socVersionInfo.substring(socVersionInfo.indexOf("soc_version="));
                            StringBuffer str = new StringBuffer();
                            str.append("conf_version=" + new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format((new Date())));
                            str.append(socVersion);
                            sos.write(str.toString().getBytes());
                        }
                    }
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }*/
                try
                {
                    dos.writeBytes(new String(strBuf.toString().getBytes(), "UTF-8"));
                    
                    dosVersion.writeBytes(new String(collectBuffer.toString().getBytes(), "UTF-8"));
                    
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
                try
                {
                    os.close();
                    collectVersion.close();
                    
                }
                catch (IOException e)
                {
                    //System.out.println(e);
                }
            }
            catch (FileNotFoundException e)
            {
            }
        }
        
    }
    
    public SettingCollectorDao getSettingCollectorDao()
    {
        return settingCollectorDao;
    }
    
    public void setSettingCollectorDao(SettingCollectorDao settingCollectorDao)
    {
        this.settingCollectorDao = settingCollectorDao;
    }
    
}
