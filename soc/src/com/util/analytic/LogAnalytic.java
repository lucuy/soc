package com.util.analytic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.soc.model.asset.Asset;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.events.Events;
import com.soc.model.events.SummaryEvents;
import com.soc.service.asset.AssetService;
import com.util.DateUtil;
import com.util.StringUtil;


/**
 * 日志解析工具类
 * 
 * @author 王纯
 * 
 */
public class LogAnalytic {

	/**
	 * 将日志字符串以｜分隔解析后将数据封装为对象
	 * 
	 * @param logString
	 * @return Map<String,Object>
	 */
	public static Map logAnalytic(String logString, AssetService assetManager,String collectorMac) {
		Map<String, Object> eventsObject = null;
		if (StringUtil.isNotEmpty(logString)) {
			eventsObject = new HashMap<String, Object>();
			String[] logStrings = logString.split("\\|");
			if (logStrings.length > 0) {
				Asset asset = GlobalConfig.assetGlobleMap.get(Long.parseLong(logStrings[59]));
				if (asset==null) {
					return eventsObject;
				}
				Events events = new Events();
				//collectorMac放进事件类
				events.setCustoms7(collectorMac);
				// 设置事件的唯一标示
				if (StringUtil.isNotEmpty(logStrings[1])) {
					events.setIdentification(Long.valueOf(logStrings[1]));
				}
				// 设置事件的名称
				events.setName(logStrings[2]);

				// 设置事件的采集器名称
				events.setCollectorName("");

				/*
				 * if (StringUtil.isNotEmpty(logStrings[3])) {
				 * events.setAggregatedCount(Long.valueOf(logStrings[3])); }
				 */
				// 设置事件的类型
				events.setType(logStrings[4]);

				// 设置事件的种类
				events.setCateGory(logStrings[5]);

				// 设置事件的协议
				if (StringUtil.isNotEmpty(logStrings[6])) {
					try{
					  events.setProtocol(Integer.valueOf(logStrings[6]));
					}
					catch(Exception e)
					{
						//System.out.println("转化失败!");
					}
				}

				// 设置事件的应用协议
				if (StringUtil.isNotEmpty(logStrings[7])) {
					try{
					events.setAppProtocol(Integer.valueOf(logStrings[7]));
					}
					catch(Exception e)
					{
						//System.out.println("转化失败!");
					}
				}

				// 设置事件的目的
				events.setIntent(logStrings[8]);

				// 设置事件的策略
				events.setPolicy(logStrings[9]);

				// 设置事件的动作
				events.setAction(logStrings[10]);

				// 设置事件的优先级
				if (StringUtil.isNotEmpty(logStrings[11])) {
					
					events.setPriority(Long.valueOf(logStrings[11]));

					// 设置事件的等级的字符串类型
					events.setOriPriority(logStrings[11]);

				}
				// 设置事件的结果
				events.setResult(logStrings[12]);

				// 设置事件的对象
				events.setObject(logStrings[13]);

				// 设置事件的资源
				events.setResource(logStrings[14]);

				// 设置事件的原始等级
			   //events.setOriPriority(logStrings[15]);

				// 设置事件本身编号
				if (StringUtil.isNotEmpty(logStrings[16])) {
					try{
					events.setRawId(Integer.valueOf(logStrings[16]));
					}
					catch(Exception e)
					{
						
					}
				}

				// 设置事件的发送字节
				if (StringUtil.isNotEmpty(logStrings[17])) {
					try{
					events.setSend(Long.valueOf(logStrings[17]));
					}
					catch(Exception e)
					{
						
					}
				}

				// 设置事件的接收字节
				if (StringUtil.isNotEmpty(logStrings[18])) {
					try{
					events.setReceive(Integer.valueOf(logStrings[18]));
					}
					catch(Exception e)
					{
						
					}
				}
				// 设置事件的持续时间
				if (StringUtil.isNotEmpty(logStrings[19])) {
					try{
					events.setDuration(Integer.valueOf(logStrings[19]));
					}
					catch(Exception e)
					{
						//System.out.println("转化失败!");
					}
				}

				events.setRequesturi(logStrings[20]);
                
				events.setMsg(logStrings[21]);
                
				events.setCustoms1(logStrings[22]);
				
				events.setCustoms2(logStrings[23]);
				
				events.setCustoms3(logStrings[24]);
				
				events.setCustoms4(logStrings[25]);
				events.setCustoms5(logStrings[26]);
				events.setCustoms6(logStrings[27]);
				//events.setCustoms7(collectorMac);
				if (StringUtil.isNotEmpty(logStrings[29])) {
					events.setCustomd1(Long.valueOf(logStrings[29]));
				}
				if (StringUtil.isNotEmpty(logStrings[30])) {
					events.setCustomd2(Long.valueOf(logStrings[30]));
				}
				if (StringUtil.isNotEmpty(logStrings[31])) {
					events.setCustomd3(Double.valueOf(logStrings[31]));
				}
				if (StringUtil.isNotEmpty(logStrings[32])) {
					events.setCustomd4(Double.valueOf(logStrings[32]));
				}
				if (StringUtil.isNotEmpty(logStrings[33])) {
					events.setsAddr(iPTransition(logStrings[33]));
				}

				events.setsName(logStrings[34]);

				if (StringUtil.isNotEmpty(logStrings[35])) {
					events.setsPort(Long.valueOf(logStrings[35]));
				}
				events.setSprocess(logStrings[36]);

				events.setSuserId(logStrings[37]);

				events.setSuserName(logStrings[38]);

				if (StringUtil.isNotEmpty(logStrings[39])) {
					events.setsTAddr(iPTransition(logStrings[39]));
				}

				if (StringUtil.isNotEmpty(logStrings[40])) {
					events.setsTPort(Integer.valueOf(logStrings[40]));
				}

				if (StringUtil.isNotEmpty(logStrings[41])) {
					events.setdAddr(iPTransition(logStrings[41]));
				}

				events.setdName(logStrings[42]);

				if (StringUtil.isNotEmpty(logStrings[43])) {
					events.setdPort(Long.valueOf(logStrings[43]));
				}

				events.setdProcess(logStrings[44]);

				events.setdUserId(logStrings[45]);

				events.setdUserName(logStrings[46]);

				if (StringUtil.isNotEmpty(logStrings[47])) {
					events.setdTAddr(iPTransition(logStrings[47]));
				}

				if (StringUtil.isNotEmpty(logStrings[48])) {
					events.setdTPort(Integer.valueOf(logStrings[48]));
				}

				if (StringUtil.isNotEmpty(logStrings[49])) {
					events.setDevAdd(iPTransition(logStrings[49]));
				}

				
				if ("".equals(logStrings[50])) {
					//C那边没办法分别是漏扫还是堡垒 java这边根据资产的id去判断下
					if (StringUtil.isNotEmpty(logStrings[59])) {//
						// 设置资产的资产名称
						
							
						
							events.setDevName(asset.getAssetSupportDevice());
						
					
						
						//events.setDevproduct();
					}
				}else {
					events.setDevName(logStrings[50]);
				}
				
				

				if (StringUtil.isNotEmpty(logStrings[51])) {
					
					events.setDevCategory(Long.valueOf(logStrings[51]));
				}

				events.setDevType(logStrings[52]);

				events.setDevVendor(logStrings[53]);

	
				events.setDevproduct(logStrings[54]);

				events.setProgramType(logStrings[55]);

				events.setProgram(logStrings[56]);

				// 刘延旭时间的修改
				if (StringUtil.isNotEmpty(logStrings[57])) {
					// events.setReceptTime(dateTransition(logStrings[57]));
					// 判断linux传过来的日期格式
					
					logStrings[57] = logStrings[57].trim();
					
					//System.out.println(logStrings[57].length());
					
					//对应类似May 23 12:12类型的日志
					boolean b =Pattern.matches("(\\w+)\\s+(\\d+)\\s+(\\d+):(\\d+).*", logStrings[57]);
					
					boolean c = Pattern.matches("(\\d+)\\D+(\\d+)\\D+(\\d+)\\D+(\\d+)\\D+(\\d+)\\D+(\\d+).*", logStrings[57]);
				
					if(b== true)
					{
						 Pattern p = Pattern.compile("(\\w+)\\s+(\\d+)\\s+(\\d+):(\\d+).*");
						 
						 Matcher m = p.matcher(logStrings[57]);
						 
						 try{
						 m.find();
						 
						 String date="";
						 
						 Calendar cal = Calendar.getInstance();
						 
						 int year = cal.get(Calendar.YEAR);
						 
						 date = date + year;
						 
						 String month = m.group(1);
						 
						 if (month.equals("Jan")) {
								date = date + "-01";
							} else if (month.equals("Feb")) {
								date = date + "-02";
							} else if (month.equals("Mar")) {
								date = date + "-03";
							} else if (month.equals("Apr")) {
								date = date + "-04";
							} else if (month.equals("May")) {
								date = date + "-05";
							} else if (month.equals("Jun")) {
								date = date + "-06";
							} else if (month.equals("Jul")) {
								date = date + "-07";
							} else if (month.equals("Aug")) {
								date = date + "-08";
							} else if (month.equals("Sep")) {
								date = date + "-09";
							} else if (month.equals("Oct")) {
								date = date + "-10";
							} else if (month.equals("Dec")) {
								date = date + "-11";
							} else {
								date = date + "-12";
							}
						 
						  String day = m.group(2);
						  
						  if(day.length()<2)
						  {
							  day = "0"+day;
						  }
						  
						  String hour = m.group(3);
						  
						  if(hour.length()<2)
						  {
							  hour = "0"+hour;
						  }
						  
						  String min = m.group(4);
						  
						  if(min.length()<2)
						  {
							  min ="0"+min;
						  }
						  
						  date =date+"-"+day+"-"+hour+"-"+min+"-00";
						  
						 
						   events.setSendTimes(date);
							
						   events.setSendTime(dateTransition(date));
						   
						  
						  
						 }
						 catch(Exception e)
						 {
							 //System.out.println("转换错误");
						 }
					}
					else if(c==true)
					{
						Pattern p = Pattern.compile("(\\d+)\\D+(\\d+)\\D+(\\d+)\\D+(\\d+)\\D+(\\d+)\\D+(\\d+).*");
						 
						Matcher m = p.matcher(logStrings[57]);
						
						String date="";
						
						try{
							
							m.find();
							
							String month="";
							
							String day="";
							
							String year="";
							
							String hour = m.group(4);
							
							String min = m.group(5);
							
							String sec = m.group(6);
							
							 if(hour.length()<2)
							  {
								  hour = "0"+hour;
							  }
							 if(min.length()<2)
							  {
								  min = "0"+min;
							  }
							 if(sec.length()<2)
							  {
								  sec = "0"+sec;
							  }
							Calendar cal = Calendar.getInstance();
						    
							int temp1 =cal.get(Calendar.MONTH)+1;
							
							String monthTrue = String.valueOf(temp1);
							
							if(monthTrue.length()<2)
							{
								monthTrue = "0" + monthTrue;
							}
							
							if(m.group(1).length()<4 && m.group(1).equals(monthTrue))
							{
								month = m.group(1);
								
								if(m.group(2).length()<4)
								{
									day = m.group(2);
									
									year = m.group(3);
								}
								else
								{
									day = m.group(3);
									
									year=m.group(2);
								}
							}else
							if(m.group(2).length()<4&&m.group(2).equals(monthTrue))
							{
								month = m.group(2);
								if(m.group(1).length()<4)
								{
									day = m.group(1);
									year = m.group(3);
								}
								else
								{
									day = m.group(3);
									year=m.group(1);
								}
							}
							else if(m.group(3).length()<4&&m.group(3).equals(monthTrue))
							{
								month = m.group(3);
								if(m.group(1).length()<4)
								{
									day = m.group(1);
									year = m.group(2);
								}
								else
								{
									day = m.group(2);
									year=m.group(1);
								}
							}
							else
							{
								String temp3= m.group(1);
								String temp4 =m.group(2);
								String temp5 =m.group(3);
								if(temp3.length()<4&&temp4.length()<4)
								{
									year = temp5;
									if(Integer.valueOf(temp3)<=12)
									{
										month = temp3;
										day = temp4;
									}
									else
									{
										month = temp4;
										day = temp3;
									}
								}
								else if(temp3.length()<4&&temp5.length()<4)
								{
									year = temp4;
									if(Integer.valueOf(temp3)<=12)
									{
										month = temp3;
										day = temp5;
									}
									else
									{
										month = temp5;
										day = temp3;
									}
								}
								else
								{
									year = temp3;
									if(Integer.valueOf(temp4)<=12)
									{
										month = temp4;
										day = temp5;
									}
									else
									{
										month = temp5;
										day = temp4;
									}
								}
							}
							
							if(day.length()<2)
							{
								day = "0"+day;
							}
							if(month.length()<2)
							{
								month ="0"+month;
							}
							
							 date =year+"-"+month+"-"+day+"-"+hour+"-"+min+"-"+sec;
							
							 events.setSendTimes(date);
								
							 events.setSendTime(dateTransition(date));
							
						}catch(Exception e)
						{
							//System.out.println("转换错误");
						}
						
					}
					else
					{
						events.setSendTimes(logStrings[58]);
						events.setSendTime(dateTransition1(logStrings[58]));
					}
					
					// Pattern p =
					// Pattern.compile("^\\w+\\s+\\d+\\s+\\d+:\\d+\\s*$");
					// Matcher m = p.matcher(logStrings[57]);
					
					/*boolean b = Pattern.matches(
							"(\\w+)\\s+(\\d+)\\s+(\\d+:\\d+)\\s*$", logStrings[57]);
					
					 * Pattern t =Pattern.compile("^\\d+-\\d+-\\d+$"); Matcher c
					 * = t.matcher(logStrings[57]);
					 
					boolean c = Pattern.matches("^\\d+-\\d+-\\d+$",
							logStrings[57]);
					
					//匹配类似：20-05-2013 12:21:57
					boolean d =  Pattern.matches("^\\d{1,2}-\\d{1,2}-\\d{4}\\s+\\d+:\\d+:\\d+$",
							logStrings[57]);
					
					//匹配类似 2013-02-12 12:12:12
					boolean f = Pattern.matches("^\\d{4}-\\d{1,2}-\\d{1,2}\\s+\\d+:\\d+:\\d+$",logStrings[57]);
					
					boolean g =Pattern.matches("^\\d{4}-\\d{1,2}-\\d{1,2}_\\d+:\\d+:\\d+$", logStrings[57]);
					
					if (b == true) {
						String date = "";
						Calendar cal = Calendar.getInstance();
						int year = cal.get(Calendar.YEAR);
						date = date + year;
						try {
							String[] temps = logStrings[57].split(" ");
							String month = temps[0];
							if (month.equals("Jan")) {
								date = date + "-01";
							} else if (month.equals("Feb")) {
								date = date + "-02";
							} else if (month.equals("Mar")) {
								date = date + "-03";
							} else if (month.equals("Apr")) {
								date = date + "-04";
							} else if (month.equals("May")) {
								date = date + "-05";
							} else if (month.equals("Jun")) {
								date = date + "-06";
							} else if (month.equals("Jul")) {
								date = date + "-07";
							} else if (month.equals("Aug")) {
								date = date + "-08";
							} else if (month.equals("Sep")) {
								date = date + "-09";
							} else if (month.equals("Oct")) {
								date = date + "-10";
							} else if (month.equals("Dec")) {
								date = date + "-11";
							} else {
								date = date + "-12";
							}
							
							String day = temps[2];
							
							if(day.length()<2)
							{
								day = "0"+day;
							}
							date = date + "-" + day;
							
							date = date + " " + temps[3] + ":00";

							events.setSendTimes(date);
							
							events.setSendTime(dateTransition1(date));
						} catch (Exception e) {
							//System.out.println("时间转换错误");
						}
					} else if (c == true) {
						
						events.setSendTimes(logStrings[58]);
						
						events.setSendTime(dateTransition1(logStrings[58]));
					}else if(d==true) 
					{
					    String date ="";
					    
					    Calendar cal = Calendar.getInstance();
					    
					   int temp1 =cal.get(Calendar.MONTH)+1;
					   String month="";
					   
					   if(temp1<10)
					   {
						   month= "0"+temp1;
					   }else
					   {
						   month=String.valueOf(temp1);
					   }
					    
					    try{
					     String []days = logStrings[57].split(" ");
					     
					     String []day = days[0].split("-");
					     if(day[0].equals(month))
					     {
					    	 date = day[2]+"-"+day[0]+"-"+day[1];
					    	 date = date + " "+days[1];
					     }else if(day[1].equals(month))
					     {
					    	 date = day[2]+"-"+day[1]+"-"+day[0];
					    	 date = date + " "+days[1];
					     }
					     else
					     {
					         int temp = Integer.valueOf(day[0]);
					         
					         if(temp>12)
					         {
					        	 date = day[2]+"-"+day[1]+"-"+day[0];
					        	 
						    	 date = date + " "+days[1];
					         }
					         else
					         {
					        	 date = day[2]+"-"+day[0]+"-"+day[1];
						    	 date = date + " "+days[1]; 
					         }
					     }
					     
					     try{
					    	 events.setSendTimes(date);
					    	 events.setSendTime(dateTransition1(date));
					     }
					     catch(Exception e)
					     {
					    	 //System.out.println("转化时间失败");
					     }
					     
					    }
					    catch(Exception e)
					    {
					    	//System.out.println("时间截取失败");
					    }
					}
					else if(f==true){
						events.setSendTimes(logStrings[57]);
						
						events.setSendTime(dateTransition1(logStrings[57]));
					}
					else if(g==true)
					{
						String []dateresult = logStrings[57].split("_");
						String []dayResult = dateresult[0].split("-");
						String month =dayResult[1];
						if(month.length()<2)
						{
							month = "0"+month;
						}
						String day =dayResult[2];
						if(day.length()<2)
						{
							day ="0"+day;
						}
						
						String []timeResult=dateresult[1].split(":");
						String hour = timeResult[0];
						if(hour.length()<2)
						{
							hour = "0"+hour;
						}
						String min = timeResult[1];
						if(min.length()<2)
						{
							min = "0"+min;
						}
						String sec = timeResult[2];
						if(sec.length()<2)
						{
							sec = "0"+sec;
						}
						
						String date1 =dayResult[0]+"-"+month+"-"+day+"-"+hour+"-"+min+"-"+sec;
						events.setSendTimes(date1);
						events.setSendTime(dateTransition(date1));
					}
					else {
						
						events.setSendTimes(logStrings[57]);
						events.setSendTime(dateTransition(logStrings[57]));
					}*/
					
				}else{
					//如果57个字段为null，则将第58个值赋值到sendTimes字段
					events.setSendTimes(logStrings[58]);
					events.setSendTime(dateTransition1(logStrings[58]));//李长红修改，之前没有添加。
				}
				if (StringUtil.isNotEmpty(logStrings[58])) {
					events.setReceptTimes(logStrings[58]);
					events.setReceptTime(dateTransition1(logStrings[58]));
				}
				
				
				
				if (StringUtil.isNotEmpty(logStrings[59])) {//

					events.setAssetId(Long.valueOf(logStrings[59]));

					// 设置资产的资产名称
					
						
						events.setCustoms5(asset.getAssetName());
					
					
					
				}

				// 设置聚合数目
				if (StringUtil.isNotEmpty(logStrings[60])) {
					events.setAggregatedCount(Long.valueOf(logStrings[60]));
				}

				// 事件对象不为空添加到Map中
				if (events != null) {
					
					String tableName= "";
				//	System.out.println("date=="+new Date()+"===================");
					    tableName = "tbl_"
							+ DateUtil.curDateStr8();
					
					events.setTableName(tableName);
					events.setCustoms1( DateUtil.curDateStr8());
					
						eventsObject.put("events", events);
					
					
					
				}

				// 概要事件
				SummaryEvents summaryEvents = new SummaryEvents();
				if (StringUtil.isNotEmpty(logStrings[1])) {
					summaryEvents.setEventsLogIdentification(Long
							.valueOf(logStrings[1]));
				}
				summaryEvents.setEventsName(logStrings[2]);
				// summaryEvents.setEventsNumber(Integer.valueOf(1));
				summaryEvents.setEventsType(logStrings[4]);
				if (StringUtil.isNotEmpty(logStrings[11])) {
					summaryEvents.setEventsLevel(Integer
							.valueOf(logStrings[11]));
				}
				summaryEvents.setEventsSource(logStrings[14]);

				if (StringUtil.isNotEmpty(logStrings[16])) {
					try{
					summaryEvents.setEventsRawid(Integer
							.valueOf(logStrings[16]));
					}catch(Exception e)
					{
						//System.out.println("转化失败!");
					}
				}
				if (StringUtil.isNotEmpty(logStrings[29])) {
					
					summaryEvents.setEventsCustomd1(Integer
							.valueOf(logStrings[29]));
				}

				if (StringUtil.isNotEmpty(logStrings[33])) {
					
					summaryEvents
							.setEventsSourceAdd(iPTransition(logStrings[33]));
				}

				summaryEvents.setEventsSourceAddT(logStrings[39]);

				if (StringUtil.isNotEmpty(logStrings[35])) {
					
					summaryEvents.setEventsSourcePort(Integer
							.valueOf(logStrings[35]));
				}
				if (StringUtil.isNotEmpty(logStrings[41])) {
					
					summaryEvents
							.setEventsTargetAdd(iPTransition(logStrings[41]));
				}
				summaryEvents.setEventsTargetAddT(logStrings[47]);

				if (StringUtil.isNotEmpty(logStrings[43])) {
					
					summaryEvents.setEventsTargetPort(Integer
							.valueOf(logStrings[43]));
				}

				if (events != null) {
					summaryEvents.setEventsDevName(events.getCustoms5());
				}

				// summaryEvents.setEventsDevName(logStrings[51]);
				summaryEvents.setEventsDevvendor(logStrings[53]);

				if ("".equals(logStrings[54])) {
					//C那边没办法分别是漏扫还是堡垒 java这边根据资产的id去判断下
					if (StringUtil.isNotEmpty(logStrings[59])) {//
						// 设置资产的资产名称
						
						
							summaryEvents.setEventsDevProduct(asset.getAssetSupportDevice());
						
					}
				}else {
					summaryEvents.setEventsDevProduct(logStrings[54]);
				}
				
				
				
				summaryEvents.setEventsProgramtype(logStrings[55]);
				summaryEvents.setEventsProgram(logStrings[56]);
				summaryEvents.setEventsDevType(logStrings[52]);

				// 设置事件数量
				if (StringUtil.isNotEmpty(logStrings[60])) {
					summaryEvents.setEventsNumber(Integer
							.parseInt(logStrings[60]));
				}

				// 设置策略
				if (StringUtil.isNotEmpty(logStrings[9])) {
					summaryEvents.setPolicy(logStrings[9]);
				}

				if (StringUtil.isNotEmpty(logStrings[58])) {
				    
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					try {
						summaryEvents.setEventsTime(sdf.parse(logStrings[58]));
						
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				if (StringUtil.isNotEmpty(logStrings[59])) {
					
					
						summaryEvents.setAssetName(asset.getAssetName());
						summaryEvents.setAssetId(Integer.parseInt(logStrings[59]));

				}
				// 如果概要事件对象不为空添加到Map中并设置入库日期
				if (summaryEvents != null) {
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					//System.out.println(new Date()+"summaryEvents=====");
					String tableName =  DateUtil.curDateStr8();
					summaryEvents.setRelLogTableName(tableName);
					
					summaryEvents.setEventsPutTime(new Date().getTime());
					
					
					    eventsObject.put("summaryEvents", summaryEvents);
					
					
				}

			}

		}

		return eventsObject;
	}

	/**
	 * 将字符串IP地址转换为long型
	 * 
	 * @param strIp
	 * @return
	 */
	public static long iPTransition(String strIp) {
		long longIP = 0;
		if (StringUtil.isNotEmpty(strIp)) {
			String[] strIps = strIp.split("\\.");
			long lIp0 = Long.parseLong(strIps[0]);
			long lIp1 = Long.parseLong(strIps[1]);
			long lIp2 = Long.parseLong(strIps[2]);
			long lIp3 = Long.parseLong(strIps[3]);
			longIP = (lIp0 << 24) + (lIp1 << 16) + (lIp2 << 8) + lIp3;
		}

		return longIP;
	}

	/**
	 * 将字符串日期/时间转换为long型
	 * 
	 * @param Strdate
	 * @return
	 */
	public static long dateTransition(String Strdate) {
		long longDate = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		if (StringUtil.isNotEmpty(Strdate)) {
			try {
				longDate = sdf.parse(Strdate).getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return longDate;
	}

	public static long dateTransition1(String Strdate) {
		long longDate = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (StringUtil.isNotEmpty(Strdate)) {
			try {
				longDate = sdf.parse(Strdate).getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return longDate;
	}
}
