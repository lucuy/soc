package com.soc.service.alert.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.PDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableUtils;

import com.soc.dao.alert.AlertMessageDao;
import com.soc.dao.alert.AlertRuleDao;
import com.soc.dao.systemsetting.SettingDao;
import com.soc.model.alert.AlertRule;
import com.soc.model.alert.alertMessage.AlertMessage;
import com.soc.model.conf.GlobalConfig;
import com.soc.service.alert.AlertMessageSerive;
import com.soc.service.impl.BaseServiceImpl;
import com.topo.dao.device.CommonDao;
import com.topo.model.device.Device;
import com.util.StringUtil;
import com.util.SysLogSender;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <告警信息Service实现类>
 * <实现告警信息的查看，搜索，快速搜索>
 * 
 * @author  liuyanxu
 * @version  [版本号, 2012-11-4]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AlertMessageServiceImpl extends BaseServiceImpl implements AlertMessageSerive
{
    private AlertMessageDao alertMessgeDao;
    private AlertRuleDao alertRuleDao;
    private CommonDao commonDao;
    private SettingDao settingDao;
   
    
    public AlertMessageDao getAlertMessgeDao()
    {
        return alertMessgeDao;
    }
    
    public void setAlertMessgeDao(AlertMessageDao alertMessgeDao)
    {
        this.alertMessgeDao = alertMessgeDao;
    }
    
    public AlertRuleDao getAlertRuleDao() {
		return alertRuleDao;
	}

	public void setAlertRuleDao(AlertRuleDao alertRuleDao) {
		this.alertRuleDao = alertRuleDao;
	}

	
	public SettingDao getSettingDao() {
		return settingDao;
	}

	public void setSettingDao(SettingDao settingDao) {
		this.settingDao = settingDao;
	}

	@Override
    public int count(Map map)
    {
        // TODO Auto-generated method stub
        return alertMessgeDao.count(map);
    }
    
    @Override
    public SearchResult alertQuery(Map map, Page page)
    {
        // TODO Auto-generated method stub
        int rowsCount = alertMessgeDao.count(map);
        page.setTotalCount(rowsCount);
        // List<AlertRule> list = alertRuleDao.queryRule(map,
        // page.getStartIndex(), page.getPageSize());
        List<AlertMessage> list = alertMessgeDao.queryAlertMessage(map, page.getStartIndex(), page.getPageSize());
        // 对查找的审计列表做分页处理
        SearchResult sr = new SearchResult();
        sr.setList(list);
        sr.setPage(page);
        
        return sr;
    }
    
    @Override
    public void insertAlertMessage(AlertMessage alertMessage)
    {
        // TODO Auto-generated method stub
        alertMessgeDao.insertAlertMessage(alertMessage);
    }
    
    @Override
    public int queryCurrent(Map map)
    {
        // TODO Auto-generated method stub
        return alertMessgeDao.queryCurrent(map);
    }
    
    @Override
    public List<AlertMessage> queryCurrentEvents(long timestamp)
    {
        // TODO Auto-generated method stub
        return alertMessgeDao.queryCurrentEvents(timestamp);
    }
    
    /** {@inheritDoc} */
    
    @Override
    public List<AlertMessage> queryCurrentMessage(Map map)
    {
        // TODO Auto-generated method stub
        List<AlertMessage> alertMessageList = new ArrayList<AlertMessage>();
        
        alertMessageList = alertMessgeDao.queryCurrentMessage(map);
        
        for (AlertMessage alertMessage : alertMessageList)
        {
            Date dateTemp = new Date(alertMessage.getAlertCreateDatetime());
            
            alertMessage.setAlertDate(dateTemp);
        }
        
        return alertMessageList;
    }
    
    /** {@inheritDoc} */
    
    @Override
    public Object queryCurrentByGroup(Map map)
    {
    	// TODO Auto-generated method stub
        Object object = "";
        
        List<Map> mapList = new ArrayList<Map>();
        
        mapList = alertMessgeDao.queryCurrentMessageByGroup(map);
        
        int Serious = 0;		//严重
        
        int low = 0;			//轻微
        
        int common = 0;			//一般
        
        int height = 0;			//重要
        
        int light = 0;			//信息
        
        
        
        
        for (Map map1 : mapList)
        {
            int temp = Integer.parseInt(map1.get("key").toString());
            
            int count = Integer.parseInt(map1.get("count").toString());
            
            switch (temp)
            {
                case 0:
                    light += count;
                    break;
                case 1:
                    light += count;
                    break;
                case 2:
                	low += count;
                    break;
                case 3:
                    common += count;
                    break;
                case 4:
                	height += count;
                    break;
                case 5:
                    Serious += count;
                    break;
            }
        }
        
        StringBuffer buffer = new StringBuffer();
        StringBuffer bufferStr = new StringBuffer();
        StringBuffer bufferNo = new StringBuffer();
        StringBuffer buffer2 = new StringBuffer();
        		
        
      //  buffer.append("['严重'," + Serious + "]");
        
        buffer.append("[{name:'一般',data:[" + common + "]}");
        buffer.append(",{name:'严重',data:[" + Serious + "]}");
        buffer.append(",{name:'轻微',data:[" + light + "]}");
        buffer.append(",{name:'信息',data:[" + low + "]}");
        buffer.append(",{name:'重要',data:[" + height + "]}]");
        
        
        buffer2.append("[['一般',"+common+"],");
        buffer2.append("['严重',"+Serious+"],");
        buffer2.append("['轻微',"+light+"],");
        buffer2.append("['信息',"+low+"],");
        buffer2.append("['重要',"+height+"]]");
        
        bufferStr.append(" ['一般','严重' ,'轻微','信息','重要']");
        bufferNo.append("["+common+","+Serious+","+light+","+low+","+height+"]");
     
        if (common==0 && light==0 && Serious==0 && low==0 && height==0) {
        	object = bufferStr + "---" + bufferNo+"---[]---[]";
		}else {
			object = bufferStr + "---" + bufferNo+"---"+buffer+"---"+buffer2;
		}
        
        
        return object;
    }
    
    /** {@inheritDoc} */
    
    public Object queryCurrentByAsset(Map map)
    {
        // TODO Auto-generated method stub
        //return alertMessgeDao.queryCurrentMessageByAsset(map);
        Object object = "";
        
        int temp = 0;
        
        List<Map> mapList = new ArrayList<Map>();
        
        mapList = alertMessgeDao.queryCurrentMessageByAsset(map);
        
        StringBuffer buffer = new StringBuffer();
        StringBuffer bufferStr = new StringBuffer();
        StringBuffer bufferNo = new StringBuffer();
        
        buffer.append("[");
        bufferStr.append("[");
        bufferNo.append("[");
        
        for (Map map1 : mapList)
        {
            String assetTemp = map1.get("key").toString();
            
            long assetCount = Integer.parseInt(map1.get("value").toString());
            
            if (temp == 0)
            {
                buffer.append("{name:\'" + assetTemp + "\',data:[" + assetCount + "]}");
                bufferStr.append("'"+assetTemp);
                bufferNo.append(assetCount);
            }
            else
            {
                buffer.append(",{name:\'" + assetTemp + "\',data:[" + assetCount + "]}");
                bufferStr.append("','"+assetTemp);
                bufferNo.append(","+assetCount);
            }
            temp++;
        }
     
        buffer.append("]");
        bufferNo.append("]");
        if (mapList==null || mapList.size()<=0) {
        	bufferStr.append("]");
        	object = "[]---[]---[]" ;
		}else {
			bufferStr.append("']");
			object = bufferStr +"---"+ bufferNo +"---"+ buffer ;
		}
        
       
        
        return object;
        
    }

	@Override
	public List<Map> quertAlertRuleByID(Map mapRule) {
		return alertMessgeDao.quertAlertRuleByID(mapRule);
	}

	@Override
	public List<Map> exportExcel(Map map) {
		List<Map> list = new ArrayList<Map>();
		try {
			list=alertMessgeDao.exportExcel(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void updateAlertMassage(Map map) {
		alertMessgeDao.updateAlertMassage(map);
	}

	@Override
	public AlertMessage queryById(Map map) {
		return alertMessgeDao.queryById(map);
	}
	
	@Override
	public List<AlertMessage> queryByAssetId(Map map) {
		return alertMessgeDao.queryByAssetId(map);
	}

	@Override
	public String queryAepetitionMessage(Map map) {
		List<Map> mapList = alertMessgeDao.queryAepetitionMessage(map);
		int countNum = 0;
		StringBuffer sb = new StringBuffer();//图表需要的数据
		String charStr =null;
		
		for (Map map1 : mapList) {
			String name = map1.get("key").toString();
			try{
				long temp = Long.valueOf(name);
				
				name=GlobalConfig.eventTypeTag.get(temp);
			}catch(Exception e)
			{
				//log.warn("类型转换错误");
			}
			long count = Long.valueOf(map1.get("count").toString());
			if(countNum==0){
				sb.append("{name:'").append(name).append("',data:[").append(count).append("]}");
			}else{
				sb.append(",{name:'").append(name).append("',data:[").append(count).append("]}");
			}
			countNum++;
		}
	
		charStr= sb.toString();
		
		return charStr;
	}

	@Override
	public String lastTimeDaysAlarmTypeDistribution(Map map) {
		//总的告警数量
		double totalNum = alertMessgeDao.last7DaysAlarmTypeDistributionCount(map) ; 
		List<Map<String, Object>> list = this.alertMessgeDao.last7DaysAlarmTypeDistribution(map);
		//处理图表需要的数据
		StringBuffer reportData = new StringBuffer();
		// 图表要用的数据
		if (list.size()!=0) {
			for (Map<String, Object> map1 : list) {
				
				double proportion =  Double.parseDouble(map1.get("count").toString())/totalNum ; 
				String result = String.format("%.2f", proportion);
				
				if (map1.get("rank").toString().equals("1")) {
					reportData.append("{name:'信息',color:'#89A54E',y:"+result+"},");
				} else if (map1.get("rank").toString().equals("2")) {
					reportData.append("{name:'低级',color:'#80699B',y:"+result+"},");
				} else if (map1.get("rank").toString().equals("3")) {
					reportData.append("{name:'中级',color:'#FFFF00',y:"+result+"},");
				}else if (map1.get("rank").toString().equals("4")) {
					reportData.append("{name:'高级',color:'#DF7000',y:"+result+"},");
				} else {
					reportData.append("{name:'严重',color:'#AA4643',y:"+result+"},");
				}
			}

			reportData.delete(reportData.length() - 1,
					reportData.length());
		}
		return reportData.toString();
	}

	@Override
	public String lastTimeDaysUpTo10AlarmsAssets(Map map) {
      
		String charData = "";
        
        int temp = 0;
        
        List<Map> mapList = new ArrayList<Map>();
        
        mapList = alertMessgeDao.queryCurrentMessageByAsset(map);
        
        StringBuffer buffer = new StringBuffer();
        
        for (Map map1 : mapList)
        {
            String assetTemp = map1.get("key").toString();
            
            long assetCount = Integer.parseInt(map1.get("value").toString());
            
            if (temp == 0)
            {
                buffer.append("{name:'" + assetTemp + "',data:[" + assetCount + "]}");
            }
            else
            {
               buffer.append(",{name:'" + assetTemp + "',data:[" + assetCount + "]}");
            }
            temp++;
        }
        
        
        charData = "" + buffer + "";
        
        return charData;
	}

	@Override
	public List<AlertMessage> queryAllAlertInfo(Map map) {
		// TODO Auto-generated method stub
		return this.alertMessgeDao.queryAllAlertInfo(map);
	}

	@Override
	public String insertAlertMessageUseTopo(String deviceIP,String assetId,String assetName) {
		 
		 String levelStr = "_____1_____";
		 // 拼接查询条件 
	    String levelStrT1 = levelStr + ",___________,___________,___________";
	    String levelStrT2 = "___________," + levelStr + ",___________,___________";
	    String levelStrT3 = "___________,___________," + levelStr + ",___________";
	    String levelStrT4 = "___________,___________,___________," + levelStr;
	    Map<String, String> levelStrs = new HashMap<String, String>();
	    levelStrs.put("levelStrT1", levelStrT1);
	    levelStrs.put("levelStrT2", levelStrT2);
	    levelStrs.put("levelStrT3", levelStrT3);
	    levelStrs.put("levelStrT4", levelStrT4); 
		List<AlertRule> alarmRuleRankList =new ArrayList<AlertRule>();
		alarmRuleRankList=alertRuleDao.queryRankByLevel(levelStrs);
		 if(alarmRuleRankList.size()!=0){
			 Map map=new HashMap();
			 map.put("eventName", "设备被更换");
			 map.put("assetId", Integer.parseInt(assetId));
			 map.put("workorder", "01");
			 List<AlertMessage> amList=new ArrayList<AlertMessage>();
			 amList=alertMessgeDao.queryAlertMessageByEventName(map);
			 if(amList.size()==0){
				 AlertMessage alertMessage=new AlertMessage();
				 alertMessage.setAlertRank(5);
				 alertMessage.setSendMode("邮件提示,平台提示,短信提示,snmp,trap/syslog提示");
				 alertMessage.setAlertState(0);
				 alertMessage.setAlertEventName("设备被更换");
				 alertMessage.setAlertEventType("120023");
				 alertMessage.setAlertDeviceIp(deviceIP);
				 alertMessage.setAlertDeviceType("");
				 alertMessage.setUserRealName("");
				 alertMessage.setRelEventsIdentification(0L);
				 alertMessage.setAlertCreateDatetime(new Date().getTime());
				 alertMessage.setAlertAssetId(Long.valueOf(assetId));
				 alertMessage.setAlertAssetName(assetName);
				 SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
				 String date=sdf.format(new java.util.Date()); 
				 alertMessage.setAlertLogTableName(date);
				 alertMessage.setAlarmRuleId(alarmRuleRankList.get(alarmRuleRankList.size()-1).getRuleId());
				 alertMessage.setEventsSourceAdd(alertMessage.lIpTransitionStrIpRevert(deviceIP));
				 alertMessage.setEventsSourcePort(0L);
				 alertMessage.setAlertDate(new Date());
				 alertMessage.setAlertDeviceName(assetName);
				 alertMessage.setEventsTargetAdd(alertMessage.lIpTransitionStrIpRevert(deviceIP));
				 alertMessage.setMessageOperateDate(new Date());
				 alertMessage.setEventsTargetPort(0L);
				 alertMessage.setEventsSourceAddT(deviceIP);
				 alertMessage.setEventsTargetAddT(deviceIP);
				 Map map1=new HashMap();
				 map1.put("device_ip", deviceIP);
				 map1.put("netMark", 0);
				 Device device=commonDao.queryDeviceByIp(map1);
				 
				 alertMessage.setAlertMark(Integer.parseInt(String.valueOf(device.getDevice_mark())));
				 // 保存告警
				 alertMessgeDao.insertAlertMessage(alertMessage);
				 //把设备的状态改变
				 device.setDevice_status(5);
				 commonDao.updateDeviceByIp(device);
				 
				 String logString = "";
                // LogWriteServiceImpl logManager=new LogWriteServiceImpl();
				 int count=alertMessgeDao.queryCountAlert();
     			String  centerNatIp = settingDao.queryByKey("centerNatIp");
     			logString=centerNatIp+"::"+String.valueOf(count)+"::"+device.getDevice_name();
     			//logManager.writeSystemAuditLog(logString);
     			for(int i=0;i<=2;i++){
     				System.out.println(logString+"===========================");
     				SysLogSender.sender(logString);
     				System.out.println(logString+"===========为什么不发呢================");
     			}
     			return "2";
			 }else{//当设备从不在线到在线，同时设备的mac还不对，告警表中还有该设备的告警，则会走这条分支
				 return "3";
				 /*Device device=commonDao.queryDeviceByIp(deviceIP);
				 Ping ping=new Ping(deviceIP);
				 String	result=ping.pingIP();
				 if(StringUtil.isNotBlank(result)){
						device.setDevice_status(1);
				 }else{
						device.setDevice_status(0);
				 }
				 commonDao.updateDeviceByIp(device);*/
			 }
		 }else{
			 System.out.println("该告警没有与之匹配的告警规则");
		 }
		 return "";
	}
	
	

	 
	/**
	 * @param args
	 */
	public  String getMac(String ip,String macObj,String assetId,String displayName,String snmpCommunityName) {
		Snmp snmp = null;
		TransportMapping transport = null;
		CommunityTarget target = null;
		String[] oids = {"1.3.6.1.2.1.2.2.1.6"};
		String mac="";
		String macTem="";
		Set<String> setlist=new HashSet<String>();
		try{
			transport = new DefaultUdpTransportMapping();
			snmp = new Snmp(transport);
			snmp.listen();
			
			target = new CommunityTarget();
			target.setCommunity(new OctetString(snmpCommunityName));
			target.setRetries(2);
			target.setAddress(GenericAddress.parse("udp:"+ip+"/161"));
			target.setTimeout(8000);
			target.setVersion(SnmpConstants.version2c);
			
			TableUtils tableUtils = new TableUtils(snmp, new PDUFactory() {
				@Override
				public PDU createPDU(Target arg0) {
					PDU request = new PDU();
					request.setType(PDU.GET);
					return request;
				}
			});
			OID[] columns = new OID[oids.length];
			for (int i = 0; i < oids.length; i++)
				columns[i] = new OID(oids[i]);
			
			//CPU可能是多核的
			List<TableEvent> list = tableUtils.getTable(target, columns, null, null);
			if(list.size()==1 && list.get(0).getColumns()==null){
				//System.out.println("list is null");
				//mac="连接暂时中断，请重试。";
				//setlist.add(mac);
			}else{
				int percentage = 0;
				for(TableEvent event: list){
					VariableBinding[] values = event.getColumns();
					//System.out.println(values[0]);
					
					if("".endsWith(getStringValue(values[0]).trim())){
						
					}else{
						mac=getStringValue(values[0]).trim();
						setlist.add(mac);
						
					}
					
				}
				
			//	System.out.println("CPU利用率："+percentage/list.size());
			}
			if(setlist.size()!=0){//snmp开启
				macObj=StringUtil.replace(macObj, "-", ":");
				boolean flag=true;
				for (String str : setlist) {
					
					if(str.equalsIgnoreCase(macObj)){
						flag=false;
						return "1";
					}
				}
				if(flag){
					String str=insertAlertMessageUseTopo(ip,assetId,displayName) ;
					if("3".equals(str)){
						return "1";
					}
				}
			}else{//snmp没开启就直接返回
				return "1";
			}
			//return macTem;	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(transport!=null)
					transport.close();
				if(snmp!=null)
					snmp.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "2";
	}
	
	public static String getStringValue(VariableBinding vb){
		if(vb == null || vb.getVariable().toString() instanceof String == false)
			return "";
		return vb.getVariable().toString();
	}

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	

	@Override
	public int queryCountAlert() {
		// TODO Auto-generated method stub
		return alertMessgeDao.queryCountAlert();
	}

	@Override
	public  List<AlertMessage> alertQueryDoc(Map map) {
		 List<AlertMessage> list = new ArrayList<AlertMessage>();
		 
		 try {
			
			 list = alertMessgeDao.queryAlertMessageDoc(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	      
	       
	        
	        return list;
	}

	@Override
	public void closeAlertMessageById(Map map) {
		alertMessgeDao.closeAlertMessageById(map);
		
	}

	@Override
	public List<AlertMessage> queryNotCloseAlertMessage(Map map) {
		// TODO Auto-generated method stub
		return alertMessgeDao.queryNotCloseAlertMessage(map);
	}
	
}
