package com.util.drools;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.drools.core.base.RuleNameMatchesAgendaFilter;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderConfiguration;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import com.soc.model.alert.AlertRule;
import com.soc.model.asset.Asset;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.events.Events;
import com.soc.model.systemsetting.rules.RelevanceRule;
import com.soc.model.systemsetting.rules.RelevanceRuleGroup;
import com.soc.service.alert.AlertRuleService;
import com.soc.service.events.EventsService;
import com.soc.service.systemsetting.rules.RelevanceRuleGroupService;
import com.util.StringUtil;

public class Drools {
	private StatefulKnowledgeSession statefulSession;
	//private StatefulKnowledgeSession statefulSession1;
	private Collection collection;
	//放条件规则session的map
	private Map<String,StatefulKnowledgeSession> mapCon;
	//放结果规则session的map
	private Map<String,StatefulKnowledgeSession> mapRes;
	//放这个关联后事件对应的资产的名字 
	//用在下一级中产生告警的时候用 存放的是产生这一级规则的初始状态的规则出发事件的资产名称
	private Map<String,String> mapAssetName;
	private KnowledgeBase knowledgeBase;
	private KnowledgeBuilder kb;
	public  List<RelevanceRuleGroup> relevanceRuleGroups;
	//定义一个变量i用作 生成结果规则的名字用 规则需要一个唯一名字 定义一个全局的int 每生成一个结果规则i++ 放入名字中
	private static int I =0;
	//定义map放结果规则的名字 一级规则后生成的所有二级规则的名字 去启动
	//private Map ruleNameMap;
	private List<String> resultRuleNameList;
	//待移除规则名字的list 
	private List<String> resultRuleNameListDel;
	public  RelevanceRuleGroupService relevanceRuleGroupManager;
	/**
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param relevanceRuleGroups
	 * @see [类、类#方法、类#成员]
	 */
	public void initEngine(List<String> ruleNameList,RelevanceRuleGroupService relevanceRuleGroupManager) {

	KnowledgeBuilderConfiguration kbConf = KnowledgeBuilderFactory.newKnowledgeBuilderConfiguration();
	kbConf.setProperty("org.drools.sequential","true");
	mapCon = new HashMap<String,StatefulKnowledgeSession>();
	mapRes = new HashMap<String,StatefulKnowledgeSession>();
	mapAssetName = new HashMap<String, String>();
	LoadConDRL(relevanceRuleGroups);//把规则类写成drl文件的形式,并放在KnowledgeBuilder里面
	this.resultRuleNameList = new ArrayList<String>();
	this.resultRuleNameListDel = new ArrayList<String>();
	this.relevanceRuleGroupManager = relevanceRuleGroupManager;
	//放参数
	for (String ruleName : ruleNameList) {
			StatefulKnowledgeSession s = mapCon.get("statefulSessionCon"+ruleName);
			setGlobalParamList(ruleName,s);
		}
	}
	
	private void setGlobalParamCount(Vector<Long> vector, final String ruleName,StatefulKnowledgeSession statefulSession) {
		 vector.add(new Long(0));
		 statefulSession.setGlobal(ruleName+"countList",vector);
	
	}
	private void setGlobalParamTime(Vector<Long> vector, final String ruleName,StatefulKnowledgeSession statefulSession) {
		statefulSession.setGlobal(ruleName+"timeNow",vector);

		
	}
	private void setGlobalParamStrBuf(StringBuffer stringBuffer, final String ruleName,StatefulKnowledgeSession statefulSession) {
		statefulSession.setGlobal(ruleName+"strBuf",stringBuffer);

		
	}
	private void setGlobalParamList(Vector<Events> events, final String ruleName,StatefulKnowledgeSession statefulSession) {
		statefulSession.setGlobal(ruleName+"events",events);

		
	}
	private void setGlobalParamEvent(Events event, final String ruleName,StatefulKnowledgeSession statefulSession) {
		statefulSession.setGlobal(ruleName+"event", event);

		
	}
	/**
	 * <这启动下级条件的规则>
	 * <需要传入条件型规则的名字>
	 * @param event
	 * @param string
	 * @see [类、类#方法、类#成员]
	 */
	public void executeRuleEngineCondition(Events event,EventsService eventsManager,Asset asset) {
			for (String ruleName : GlobalConfig.ruleNameList) {
				StatefulKnowledgeSession s = (StatefulKnowledgeSession)mapCon.get("statefulSessionCon"+ruleName);
				s.insert(event);
				s.fireAllRules(new RuleNameMatchesAgendaFilter("^"+ruleName+"[\\D]*"));
					Events eventTemp = (Events) s.getGlobal(ruleName+"event");
					if (StringUtil.isNotBlank(eventTemp.getCustoms7())) {
						//名字的规则是group 组id Rule 规则id 根据规则id找它下面的结果类型的规则 去写规则文件
						//RelevanceRule relevanceRuleResult = this.relevanceRuleGroupManager.queryResultRuleByConRuleId(ruleName.substring(ruleName.indexOf('e')+1, ruleName.length()));
						List<RelevanceRule> relevanceRuleResultList = this.relevanceRuleGroupManager.queryRuleBuGroupId(Integer.parseInt(ruleName.substring(ruleName.indexOf('p')+1, ruleName.indexOf('R'))));
						if (relevanceRuleResultList.size() == 1) {//说明只有一级 直接入库了
							s.setGlobal(ruleName+"events", new Vector<Events>());
							s.setGlobal(ruleName+"event", new Events());
							Vector<Long> countList = new Vector<Long>();//声明一个新的放count的队列 放进去
							countList.add(0L);
							s.setGlobal(ruleName+"timeNow",new Vector<Long>());
							//设置关联后事件的产生事件
							eventTemp.setSendTime(new Date().getTime());
							eventTemp.setReceptTime(new Date().getTime());
							//数量
							eventTemp.setAggregatedCount(1);
							eventTemp.setAssetId(asset.getAssetId());
							//放当前时间的毫秒数做唯一标示
							eventTemp.setIdentification(new Date().getTime());
							//放当天时间
							eventTemp.setCustoms1(new SimpleDateFormat("yyyyMMdd").format(new Date()));
							s.setGlobal(ruleName+"countList", countList);
							//入库之前看是否符合告警规则
							logAlarmHandling(
									GlobalConfig.alertRuleManager,
									eventTemp.getIdentification(),
									eventTemp.getName(),
									eventTemp.getType(),
									eventTemp.getCateGory(),
									eventTemp.getsAddr(),
									eventTemp.getdAddr(),
									eventTemp.getDevAdds(),
									eventTemp.getDevproduct(),
									String.valueOf(eventTemp
											.getPriority()),
									"tbl_relevance_events",
									eventTemp.getAssetId(),
									asset.getAssetName(),
									eventTemp.getAggregatedCount(),
									eventTemp.getsAddr(),
									eventTemp.getsPort(),
									eventTemp.getdAddr(),
									eventTemp.getdPort(),
									"关联后事件",eventTemp.getSendTime()
									);
							eventsManager.insertEventsDrools(eventTemp);
						}
						for (int i = 1; i < relevanceRuleResultList.size(); i++) {
							RelevanceRule	relevanceRuleResult = relevanceRuleResultList.get(i);
							String ruleNameRes = "Rule"+relevanceRuleResult.getRelevanceRuleId()+"result"+I;
							this.LoadResultDrl( event,relevanceRuleResult,ruleNameRes);
							this.resultRuleNameList.add(ruleNameRes);//再结果类型的规则名字list中加入规则名字 备用
							//向结果的map中放入session对象
							if (I>100000000) {//大于这个 变成0
								I= 0;
							}
							I++;
							this.mapAssetName.put(ruleNameRes, asset.getAssetName());
							StatefulKnowledgeSession statefulSessionRes = mapRes.get("statefulSessionRes"+ruleNameRes);
							//放全局变量
							setGlobalParamList(ruleNameRes,statefulSessionRes) ;
						}
			}
		}
	}
	
	public  void logAlarmHandling(AlertRuleService alertRuleManager,
			long identification, String eventsName, String eventsType,String category,long sDDr,long Daddr,
			String DeviceIp, String DeviceType, String oriPriority,
			String logTableName, long assetId, String assetName, long num,long sAddr,long sPort,long dAddr,long dPort,String DevName,long sendtime) {
		 Map<String, Object> dataMap = new HashMap<String, Object>();

		 //资产ID
		 List<AlertRule> ruleList = alertRuleManager.queryAlertRule(assetId, Integer.parseInt(oriPriority));
		 if(ruleList == null || ruleList.isEmpty()){
			 return ;
		 }
		 
			if (StringUtil.isNotEmpty(String.valueOf(identification))) {
				dataMap.put("identification", String.valueOf(identification));
			}

			if (StringUtil.isNotEmpty(eventsName)) {
				dataMap.put("eventsName", eventsName);
			}
			if (StringUtil.isNotEmpty(eventsType)) {
				dataMap.put("eventsType", eventsType);
			}
			
		    dataMap.put("SAddr",sDDr);
		    
		    dataMap.put("DAddr",Daddr);
			
			if(StringUtil.isNotEmpty(category))
			{
			    dataMap.put("category",category);
			}
			if (StringUtil.isNotEmpty(DeviceIp)) {
				dataMap.put("deviceIp", DeviceIp);
			}
			if (StringUtil.isNotEmpty(DeviceType)) {
			    
				dataMap.put("deviceType", DeviceType);
			}
			if (StringUtil.isNotEmpty(oriPriority)) {
				dataMap.put("oriPriority", oriPriority);
			}
			if (StringUtil.isNotEmpty(logTableName)) {
				dataMap.put("logTableName",logTableName.substring(logTableName.indexOf("_") + 1));
			}
			if (assetId != 0) {
				dataMap.put("assetId", assetId);
			}
			if (StringUtil.isNotEmpty(assetName)) {
				dataMap.put("assetName", assetName);
			}
		
				dataMap.put("sAddr", sAddr);
			
			
		    dataMap.put("sPort", sPort);
			
		
				dataMap.put("dAddr", dAddr);
			
			
			dataMap.put("dPort", dPort);
			
			if (StringUtil.isNotEmpty(DevName)) {
				dataMap.put("DevName", DevName);
			}
			// 临时加的数量
			dataMap.put("num", num);
			dataMap.put("sendTime", sendtime);
			alertRuleManager.logAlarmHandling(dataMap,ruleList);
		}

	private void setGlobalParamList(String ruleNameRes,StatefulKnowledgeSession statefulSession) {
		this.setGlobalParamEvent(new Events(), ruleNameRes,statefulSession);
		this.setGlobalParamList(new Vector<Events>(),ruleNameRes,statefulSession);
		this.setGlobalParamStrBuf(new StringBuffer(), ruleNameRes,statefulSession);
		this.setGlobalParamTime(new Vector<Long>(),ruleNameRes,statefulSession);
		this.setGlobalParamCount(new Vector<Long>(),ruleNameRes,statefulSession);
		
	}
	/**
	 * <一句水电费的爽肤水的范德萨发的说法话功能简述>
	 * <功能详细描述>
	 * @param event
	 * @param string
	 * @param i
	 * @param eventsManager
	 * @see [类、类#方法、类#成员]
	 */
	public void executeRuleEngineResult(Events event,EventsService eventsManager,Asset asset) {
			
		for (String ruleName : this.resultRuleNameList) {
			StatefulKnowledgeSession s = (StatefulKnowledgeSession)mapRes.get("statefulSessionRes"+ruleName);
			
			//this.setGlobalParamList(GlobalConfig.ruleNameList,statefulSession1);
			
			s.insert(event); 
			s.fireAllRules(new RuleNameMatchesAgendaFilter("^"+ruleName+"[\\D]*"));
				Events eventTemp = (Events) s.getGlobal(ruleName+"event");
				if (StringUtil.isNotBlank(eventTemp.getCustoms7())) {
					//设置关联后事件的产生事件
					eventTemp.setSendTime(new Date().getTime());
					eventTemp.setReceptTime(new Date().getTime());
					eventTemp.setAssetId(asset.getAssetId());
					//数量
					eventTemp.setAggregatedCount(1);
					//放当前时间的毫秒数做唯一标示
					eventTemp.setIdentification(new Date().getTime());
					//放当天时间
					eventTemp.setCustoms1(new SimpleDateFormat("yyyyMMdd").format(new Date()));
					//放当前时间的毫秒数做唯一标示
					eventTemp.setIdentification(new Date().getTime());
					//加入多告警的处理
					logAlarmHandling(
							GlobalConfig.alertRuleManager,
							eventTemp.getIdentification(),
							eventTemp.getName(),
							eventTemp.getType(),
							eventTemp.getCateGory(),
							eventTemp.getsAddr(),
							eventTemp.getdAddr(),
							eventTemp.getDevAdds(),
							eventTemp.getDevproduct(),
							String.valueOf(eventTemp
									.getPriority()),
							"tbl_relevance_events",
							eventTemp.getAssetId(),
							mapAssetName.get(ruleName),
							eventTemp.getAggregatedCount(),
							eventTemp.getsAddr(),
							eventTemp.getsPort(),
							eventTemp.getdAddr(),
							eventTemp.getdPort(),
							"关联后事件",eventTemp.getSendTime()
							);
					eventsManager.insertEventsDrools(eventTemp);
					//入库后把规则从内存中移除,还要把规则名字从名字的list中移除
					this.resultRuleNameListDel.add(ruleName);
					mapRes.remove("statefulSessionRes"+ruleName);
					this.mapAssetName.remove(ruleName);
		}
		}
		if (resultRuleNameListDel.size()!=0) {
			this.resultRuleNameList.removeAll(resultRuleNameListDel);
			resultRuleNameListDel.clear();
		}
	}
	
	
	/**
	 * <根据第一级生成的关联事件生成结果关联规则>
	 * <功能详细描述>
	 * @param eventTemp
	 * @param ruleName
	 * @see [类、类#方法、类#成员]
	 */
	private void LoadResultDrl(Events eventTemp,RelevanceRule  relevanceRule2,String ruleName) {
		StringBuffer sb = new StringBuffer();
		StringBuffer sbCon  = new StringBuffer();		//作为拼接等级中使用
		sb.append("package com.drools \r\n");
		sb.append("import java.util.Vector;\r\n");
		sb.append("import com.soc.model.events.Events;\r\n");
		sb.append("global Vector Rule").append(relevanceRule2.getRelevanceRuleId()).append("result"+I).append("events;\r\n");
		sb.append("global Vector Rule").append(relevanceRule2.getRelevanceRuleId()).append("result"+I).append("timeNow;\r\n");
		sb.append("global StringBuffer Rule").append(relevanceRule2.getRelevanceRuleId()).append("result"+I).append("strBuf;\r\n");
		sb.append("global Events Rule").append(relevanceRule2.getRelevanceRuleId()).append("result"+I).append("event;\r\n");
		sb.append("global Vector Rule").append(relevanceRule2.getRelevanceRuleId()).append("result"+I).append("countList;\r\n");
		sb.append("function Vector initTime(Vector list,long time){\r\n");
		sb.append("if(list.size()==0){\r\n");
		sb.append("list.add(time);\r\n");
		sb.append("}return list;\r\n}\r\n");
	
		sb.append("rule \"Rule").append(relevanceRule2.getRelevanceRuleId()).append("result"+I).append("Count\"\r\n");
		sb.append("when\r\n");
		sb.append("$event:Events();\r\n");
		sb.append("&&eval(Long.parseLong(Rule").append(relevanceRule2.getRelevanceRuleId()).append("result"+I).append("countList.get(0).toString()) >=").append(relevanceRule2.getCount()).append("L);\r\n")  ;//);\r\n");
		sb.append("then\r\n");
		//放关联后事件等级
		sb.append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("result"+I).append("event.setPriority(").append(relevanceRule2.getLevel()).append(");\r\n");           
		//放关联后事件的事件类别
		sb.append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("result"+I).append("event.setCateGory(\"").append(relevanceRule2.getEventsCategoryIdsRe()).append("\");\r\n");           
		//放关联后事件的类型
		sb.append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("result"+I).append("event.setType(\"").append(relevanceRule2.getEventsTypeIdsRe()).append("\");\r\n");           
		//放事件描述
		sb.append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("result"+I).append("event.setCustoms4(\"").append(relevanceRule2.getRelevanceRuleDes()).append("\");\r\n");           
		sb.append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("result"+I).append("event.setCustoms7(Rule").append(relevanceRule2.getRelevanceRuleId()).append("result"+I).append("strBuf.toString());\r\n");
		sb.append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("result"+I).append("event.setName(\"").append(relevanceRule2.getEventsName()).append("\");\r\n");           
		sb.append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("result"+I).append("strBuf.setLength(0);\r\n");
		sb.append("end\r\n");
		sb.append("rule \"Rule").append(relevanceRule2.getRelevanceRuleId()).append("result"+I).append("Insert\"\r\n");
		sb.append("when\r\n");
		sb.append("$event:Events(");
		//拼接设备部分
		String assetIds = relevanceRule2.getAssetIds();
		if (!"".equals(assetIds)) {
			sbCon.append("(").append(this.getDevName(assetIds)).append("),");
		}
		String typeIds = relevanceRule2.getEventsTypeIds();
		if (!"".equals(typeIds)) {
			sbCon.append("(");
			String [] typeIdsArr = typeIds.split(","); //把id串拼接成规则要用的形式  看id串能不能是 ,1,2的形式
			for (int i = 0; i < typeIdsArr.length; i++) {
				if (!"".equals(typeIdsArr[i])) {
					sbCon.append("type == \"").append(typeIdsArr[i]).append("\"");
					if (i<typeIdsArr.length-1) {
						sbCon.append("||");
					}
				}
			}
			sbCon.append("),");
		}
		String cateGoryIds = relevanceRule2.getEventsCategoryIds();
		if (!"".equals(cateGoryIds)) {
			sbCon.append("(");
			String [] cateGoryIdsArr = cateGoryIds.split(","); //把id串拼接成规则要用的形式  看id串能不能是 ,1,2的形式
			for (int i = 0; i < cateGoryIdsArr.length; i++) {
				if (!"".equals(cateGoryIdsArr[i])) {
					sbCon.append("cateGory == \"").append(cateGoryIdsArr[i]).append("\"");
					if (i<cateGoryIdsArr.length-1) {
						sbCon.append("||");
					}
				}
			}
			sbCon.append("),");
		}
		String priorityIds = relevanceRule2.getPriorityIds();
		if (!"".equals(priorityIds)) {
			sbCon.append("(");
			String [] priorityIdsArr = priorityIds.split(","); //把id串拼接成规则要用的形式  看id串能不能是 ,1,2的形式
			for (int i = 0; i < priorityIdsArr.length; i++) {
				if (!"".equals(priorityIdsArr[i])) {
					sbCon.append("priority == ").append(priorityIdsArr[i]);
					if (i<priorityIdsArr.length-1) {
						sbCon.append("||");
					}
				}
			}
			sbCon.append("),");
		}
	
		//源地址 格式   等于:前一状态的源地址
		sbCon.append(operatingAddOrPort(relevanceRule2,eventTemp));
		
/*		String sTPort = relevanceRule2.getSourcePort();
		if (!"".equals(sTPort)) {
			sbCon.append("(");
			String [] sTPortArr = sTPort.split(","); //把id串拼接成规则要用的形式  看id串能不能是 ,1,2的形式
			for (int i = 0; i < sTPortArr.length; i++) {
				if (!"".equals(sTPortArr[i])) {
					sbCon.append("sPort == ").append(sTPortArr[i]);
					if (i<sTPortArr.length-1) {
						sbCon.append("||");
					}
				}
			}
			sbCon.append("),");
		}*/
/*		String dPort = relevanceRule2.getTargetPort();
		if (!"".equals(dPort)) {
			sbCon.append("(");
			String [] dPortArr = dPort.split(","); //把id串拼接成规则要用的形式  看id串能不能是 ,1,2的形式
			for (int i = 0; i < dPortArr.length; i++) {
				if (!"".equals(dPortArr[i])) {
					sbCon.append("dPort == ").append(dPortArr[i]);
					if (i<dPortArr.length-1) {
						sbCon.append("||");
					}
				}
			}
			sbCon.append("),");
		}*/
		//去掉最后一个逗号
		sb.append(	sbCon.substring(0, sbCon.length()-1));
		sb.append(");\r\n");
		sb.append("then\r\n");
		sb.append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("result"+I).append("events.add($event);\r\n");
		sb.append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("result"+I).append("strBuf.append($event.getIdentification()).append(\",\");\r\n");
		
		sb.append("long countTemp = Long.parseLong(Rule").append(relevanceRule2.getRelevanceRuleId()).append("result"+I).append("countList.get(0).toString());\r\n");
		
		sb.append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("result"+I).append("countList.clear();\r\n");
		sb.append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("result"+I).append("countList.add(countTemp+$event.getAggregatedCount());\r\n");
		sb.append("end\r\n");
		sb.append("rule \"Rule").append(relevanceRule2.getRelevanceRuleId()).append("result"+I).append("Time\"\r\n");
		sb.append("when\r\n");
		sb.append("Events($time:sendTime)&&eval($time-Long.parseLong(initTime(Rule").append(relevanceRule2.getRelevanceRuleId()).append("result"+I).append("timeNow,$time).get(0).toString())>").append(relevanceRule2.getTime()).append("000);\r\n");
		sb.append("then\r\n");
		sb.append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("result"+I).append("events.clear();\r\n");
		sb.append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("result"+I).append("timeNow.clear();\r\n");
		sb.append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("result"+I).append("timeNow.add($time);\r\n");
		sb.append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("result"+I).append("countList.clear();\r\n");
		sb.append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("result"+I).append("countList.add(0L);\r\n");
		sb.append("end\r\n");
		KnowledgeBuilder kb=KnowledgeBuilderFactory.newKnowledgeBuilder();
		kb.add(ResourceFactory.newReaderResource(new StringReader(sb.toString())), ResourceType.DRL);
		
		if(kb.hasErrors()){
			System.out.println("有错误 检查");
			KnowledgeBuilderErrors kbuidlerErrors=kb.getErrors();
			for(Iterator
			iter=kbuidlerErrors.iterator();iter.hasNext();){
			System.out.println(iter.next());
			}
			}
		Collection collection=kb.getKnowledgePackages();
		if(kb.hasErrors()){
		System.out.println("有错误 检查");
		KnowledgeBuilderErrors kbuidlerErrors=kb.getErrors();
		for(Iterator
		iter=kbuidlerErrors.iterator();iter.hasNext();){
		System.out.println(iter.next());
		}
		}
		KnowledgeBase knowledgeBase=KnowledgeBaseFactory.newKnowledgeBase();
		knowledgeBase.addKnowledgePackages(collection);
		mapRes.put("statefulSessionRes"+ruleName, knowledgeBase.newStatefulKnowledgeSession());
	}
	/**
	 * <处理地址和端口的方法>
	 * <功能详细描述>
	 * @param relevanceRule2
	 * @param eventTemp
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private String operatingAddOrPort(RelevanceRule relevanceRule2,
			Events eventTemp) {
		StringBuffer sCon = new StringBuffer();//拼接条件的字符串
		
		//获得规则里的源ip 格式 等于:前一状态....
		String sourceAddTemp = relevanceRule2.getSourceAddr();
		if (!"".equals(sourceAddTemp)) {//如果不是空  
			sCon.append("(");
			String sourceAdd = sourceAddTemp.split(",")[0]; //这里只要第一个数据 
			//判断是不是前一状态这个类型
			if (sourceAdd.contains("前一")) {//是这个类型的话拼接前一状态的事件
				String[] sourceAddArr = sourceAdd.split(":");//按照:截取 第一个是等于或者不等于 第二个是前一状态的..
				if (sourceAddArr[0].equals("等于")) {
					sCon.append("sAdd == \"");
					if (sourceAddArr[0].contains("源地址")) {
						sCon.append(eventTemp.getsAdd()).append("\"");
					}else{
						sCon.append(eventTemp.getsAdd()).append("\"");
					}
				}else{
					sCon.append("sAdd != \"");
					if (sourceAddArr[0].contains("源地址")) {
						sCon.append(eventTemp.getsAdd()).append("\"");
					}else{
						sCon.append(eventTemp.getsAdd()).append("\"");
					}
				}
			}else{
				//不是前一...这个格式 按照正常方式解析
				String[] sourceAddArr = sourceAddTemp.split(","); //这里只要第一个数据 
				for (int i = 0; i < sourceAddArr.length; i++) {
					String[] stringArr = sourceAddArr[i].split(":");
					if (stringArr[0].equals("等于")) {
						sCon.append("sAdd == \"").append(stringArr[1]).append("\"");
					}else{
						sCon.append("sAdd != \"").append(stringArr[1]).append("\"");
					}
					if (i<sourceAddArr.length-1) {
						sCon.append("||");
					}
				}
			}
			sCon.append("),");
		}
	//拼接目的地址
		
		//获得规则里的目的ip 格式 等于:前一状态....
		String targetAddTemp = relevanceRule2.getTargetAddr();
		if (!"".equals(targetAddTemp)) {//如果不是空  
			sCon.append("(");
			String targetAdd = targetAddTemp.split(",")[0]; //这里只要第一个数据 
			//判断是不是前一状态这个类型
			if (targetAdd.contains("前一")) {//是这个类型的话拼接前一状态的事件
				String[] targetAddArr = targetAdd.split(":");//按照:截取 第一个是等于或者不等于 第二个是前一状态的..
				if (targetAddArr[0].equals("等于")) {
					sCon.append("tAdd == \"");
					if (targetAddArr[0].contains("源地址")) {
						sCon.append(eventTemp.getsAdd()).append("\"");
					}else{
						sCon.append(eventTemp.gettAdd()).append("\"");
					}
				}else{
					sCon.append("tAdd != \"");
					if (targetAddArr[0].contains("源地址")) {
						sCon.append(eventTemp.getsAdd()).append("\"");
					}else{
						sCon.append(eventTemp.gettAdd()).append("\"");
					}
				}
			}else{
				//不是前一...这个格式 按照正常方式解析
				String[] targetAddArr = targetAddTemp.split(","); //这里只要第一个数据 
				for (int i = 0; i < targetAddArr.length; i++) {
					String[] stringArr = targetAddArr[i].split(":");
					if (stringArr[0].equals("等于")) {
						sCon.append("tAdd == \"").append(stringArr[1]).append("\"");
					}else{
						sCon.append("tAdd != \"").append(stringArr[1]).append("\"");
					}
					if (i<targetAddArr.length-1) {
						sCon.append("||");
					}
				}
			}
			sCon.append("),");
		}
		
		//拼接源端口
		String sourcePortTemp = relevanceRule2.getSourcePort();
		if (!"".equals(sourcePortTemp)) {//如果不是空  
			sCon.append("(");
			String sourcePort = sourcePortTemp.split(",")[0]; //这里只要第一个数据 
			//判断是不是前一状态这个类型
			if (sourcePort.contains("前一")) {//是这个类型的话拼接前一状态的事件
				String[] sourcePortArr = sourcePort.split(":");//按照:截取 第一个是等于或者不等于 第二个是前一状态的..
				if (sourcePortArr[0].equals("等于")) {
					sCon.append("sPort == ");
					if (sourcePortArr[0].contains("源端口")) {
						sCon.append(eventTemp.getsPort());
					}else{
						sCon.append(eventTemp.getsPort());
					}
				}else{
					sCon.append("sPort != ");
					if (sourcePortArr[0].contains("源端口")) {
						sCon.append(eventTemp.getsPort());
					}else{
						sCon.append(eventTemp.getsPort());
					}
				}
			}else{
				//不是前一...这个格式 按照正常方式解析
				String[] sourcePortArr = sourcePortTemp.split(","); //这里只要第一个数据 
				for (int i = 0; i < sourcePortArr.length; i++) {
					String[] stringArr = sourcePortArr[i].split(":");
					if (stringArr[0].equals("等于")) {
						sCon.append("sPort == ").append(stringArr[1]);
					}else{
						sCon.append("sPort != ").append(stringArr[1]);
					}
					if (i<sourcePortArr.length-1) {
						sCon.append("||");
					}
				}
			}
			sCon.append("),");
		}
		
		//拼接目标端口
				String targetPortTemp = relevanceRule2.getTargetPort();
				if (!"".equals(targetPortTemp)) {//如果不是空  
					sCon.append("(");
					String targetPort = targetPortTemp.split(",")[0]; //这里只要第一个数据 
					//判断是不是前一状态这个类型
					if (targetPort.contains("前一")) {//是这个类型的话拼接前一状态的事件
						String[] targetPortArr = targetPort.split(":");//按照:截取 第一个是等于或者不等于 第二个是前一状态的..
						if (targetPortArr[0].equals("等于")) {
							sCon.append("dPort == ");
							if (targetPortArr[0].contains("源端口")) {
								sCon.append(eventTemp.getsPort());
							}else{
								sCon.append(eventTemp.getsPort());
							}
						}else{
							sCon.append("dPort != ");
							if (targetPortArr[0].contains("源端口")) {
								sCon.append(eventTemp.getsPort());
							}else{
								sCon.append(eventTemp.getsPort());
							}
						}
					}else{
						//不是前一...这个格式 按照正常方式解析
						String[] targetPortArr = targetPortTemp.split(","); //这里只要第一个数据 
						for (int i = 0; i < targetPortArr.length; i++) {
							String[] stringArr = targetPortArr[i].split(":");
							if (stringArr[0].equals("等于")) {
								sCon.append("dPort == ").append(stringArr[1]);
							}else{
								sCon.append("dPort != ").append(stringArr[1]);
							}
							if (i<targetPortArr.length-1) {
								sCon.append("||");
							}
						}
					}
					sCon.append("),");
				}
		return sCon.toString();
	}

	public void setGlobalParamList(List<String> ruleName,Map<String,StatefulKnowledgeSession> mapCon) {
			for (String string : ruleName) {
				this.setGlobalParamEvent(new Events(), string,mapCon);
				this.setGlobalParamList(new Vector<Events>(),string,mapCon);
				this.setGlobalParamStrBuf(new StringBuffer(), string,mapCon);
				this.setGlobalParamTime(new Vector<Long>(),string,mapCon);
				this.setGlobalParamCount(new Vector<Long>(),string,mapCon);
		}
	}
	private void setGlobalParamCount(Vector<Long> vector, final String string,Map<String,StatefulKnowledgeSession> mapCon) {
		 for (Map.Entry<String, StatefulKnowledgeSession> entry : mapCon.entrySet()) {
			 StatefulKnowledgeSession s = entry.getValue();
			 vector.add(new Long(0));
				s.setGlobal(string+"countList",vector);
	
			  }
		
	}

	/**
	 * 设置时间
	 * @param queue
	 */
	private void setGlobalParamTime(List<Long> queue,final String ruleName,Map<String,StatefulKnowledgeSession> mapCon) {
		 for (Map.Entry<String, StatefulKnowledgeSession> entry : mapCon.entrySet()) {
			 StatefulKnowledgeSession s = entry.getValue();
				s.setGlobal(ruleName+"timeNow",queue);
		
			  }
	}
	/**
	 * 设置放解析后的队列
	 * @param queue
	 */
	private void setGlobalParamList(List<Events> events,final String ruleName,Map<String,StatefulKnowledgeSession> mapCon) {
		 for (Map.Entry<String, StatefulKnowledgeSession> entry : mapCon.entrySet()) {
			 StatefulKnowledgeSession s = entry.getValue();
				s.setGlobal(ruleName+"events",events);
			
			  }
	}
	/**
	 * 拼接解析后事件id穿的StringBuffer
	 * @param queue
	 */
	private void setGlobalParamStrBuf(StringBuffer strBuf,final String ruleName,Map<String,StatefulKnowledgeSession> mapCon) {
		 for (Map.Entry<String, StatefulKnowledgeSession> entry : mapCon.entrySet()) {
			 StatefulKnowledgeSession s = entry.getValue();
			 s.setGlobal(ruleName+"strBuf",strBuf);
		
			  }
	}
	/**
	 * 放关联后事件
	 * @param queue
	 */
	private void setGlobalParamEvent(Events event,final String ruleName,Map<String,StatefulKnowledgeSession> mapCon) {
		 for (Map.Entry<String, StatefulKnowledgeSession> entry : mapCon.entrySet()) {
			 StatefulKnowledgeSession s = entry.getValue();
			 s.setGlobal(ruleName+"event", event);
		
			  }
}
	/**
	 * <清空会话>
	 * <功能详细描述>
	 * @see [类、类#方法、类#成员]
	 */
	public void closeSesssion (){
		if (mapCon!=null) {
			 for (StatefulKnowledgeSession session : mapCon.values()) {
					session.dispose();
				}
				
		}
		if (mapRes!=null) {
			 for (StatefulKnowledgeSession session : mapCon.values()) {
					session.dispose();
				}
				
		}
	}
	/**
	 * <一句话功能简述>
	 * <把规则类写成drl文件的形式,并放在KnowledgeBuilder里面>
	 * @param kb
	 * @param relevanceRuleGroups
	 * @see [类、类#方法、类#成员]
	 */
	private void LoadConDRL(List<RelevanceRuleGroup> relevanceRuleGroups) {
		for (RelevanceRuleGroup relevanceRuleGroup : relevanceRuleGroups) {
			RelevanceRule  relevanceRule2 = relevanceRuleGroup.getRelevanceRules().get(0);
			StringBuffer sb = new StringBuffer();
			StringBuffer sbCon  = new StringBuffer();		//作为拼接等级中使用
			sb.append("package com.drools \r\n");
			sb.append("import java.util.Vector;\r\n");
			sb.append("import com.soc.model.events.Events;\r\n");
			sb.append("global Vector group").append(relevanceRuleGroup.getId()).append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("events;\r\n");
			sb.append("global Vector group").append(relevanceRuleGroup.getId()).append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("timeNow;\r\n");
			sb.append("global StringBuffer group").append(relevanceRuleGroup.getId()).append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("strBuf;\r\n");
			sb.append("global Events group").append(relevanceRuleGroup.getId()).append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("event;\r\n");
			sb.append("global Vector group").append(relevanceRuleGroup.getId()).append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("countList;\r\n");
			sb.append("function Vector initTime(Vector list,long time){\r\n");
			sb.append("if(list.size()==0){\r\n");
			sb.append("list.add(time);\r\n");
			sb.append("}return list;\r\n}\r\n");
		
			sb.append("rule \"group").append(relevanceRuleGroup.getId()).append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("Count\"\r\n");
			sb.append("when\r\n");
			sb.append("$event:Events();\r\n");
			sb.append("&&eval(Long.parseLong(group").append(relevanceRuleGroup.getId()).append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("countList.get(0).toString()) >=").append(relevanceRule2.getCount()).append("L);\r\n")  ;//);\r\n");
			sb.append("then\r\n");
			//放关联后事件等级
			sb.append("group").append(relevanceRuleGroup.getId()).append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("event.setPriority(").append(relevanceRule2.getLevel()).append(");\r\n");           
			//放关联后事件的事件类别
			sb.append("group").append(relevanceRuleGroup.getId()).append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("event.setCateGory(\"").append(relevanceRule2.getEventsCategoryIdsRe()).append("\");\r\n");           
			//放关联后事件的类型
			sb.append("group").append(relevanceRuleGroup.getId()).append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("event.setType(\"").append(relevanceRule2.getEventsTypeIdsRe()).append("\");\r\n");           
			//放事件描述
			sb.append("group").append(relevanceRuleGroup.getId()).append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("event.setCustoms4(\"").append(relevanceRule2.getRelevanceRuleDes()).append("\");\r\n");           
			sb.append("group").append(relevanceRuleGroup.getId()).append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("event.setCustoms7(group").append(relevanceRuleGroup.getId()).append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("strBuf.toString());\r\n");
			sb.append("group").append(relevanceRuleGroup.getId()).append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("event.setName(\"").append(relevanceRule2.getEventsName()).append("\");\r\n");           
			sb.append("group").append(relevanceRuleGroup.getId()).append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("strBuf.setLength(0);\r\n");
			sb.append("end\r\n");
			sb.append("rule \"group").append(relevanceRuleGroup.getId()).append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("Insert\"\r\n");
			sb.append("when\r\n");
			sb.append("$event:Events(");
			//拼接设备部分
			String assetIds = relevanceRule2.getAssetIds();
			if (!"".equals(assetIds)) {
				sbCon.append("(").append(this.getDevName(assetIds)).append("),");
			}
			String typeIds = relevanceRule2.getEventsTypeIds();
			if (!"".equals(typeIds)) {
				sbCon.append("(");
				String [] typeIdsArr = typeIds.split(","); //把id串拼接成规则要用的形式  看id串能不能是 ,1,2的形式
				for (int i = 0; i < typeIdsArr.length; i++) {
					if (!"".equals(typeIdsArr[i])) {
						sbCon.append("type == \"").append(typeIdsArr[i]).append("\"");
						if (i<typeIdsArr.length-1) {
							sbCon.append("||");
						}
					}
				}
				sbCon.append("),");
			}
			String cateGoryIds = relevanceRule2.getEventsCategoryIds();
			if (!"".equals(cateGoryIds)) {
				sbCon.append("(");
				String [] cateGoryIdsArr = cateGoryIds.split(","); //把id串拼接成规则要用的形式  看id串能不能是 ,1,2的形式
				for (int i = 0; i < cateGoryIdsArr.length; i++) {
					if (!"".equals(cateGoryIdsArr[i])) {
						sbCon.append("cateGory == \"").append(cateGoryIdsArr[i]).append("\"");
						if (i<cateGoryIdsArr.length-1) {
							sbCon.append("||");
						}
					}
				}
				sbCon.append("),");
			}
			String priorityIds = relevanceRule2.getPriorityIds();
			if (!"".equals(priorityIds)) {
				sbCon.append("(");
				String [] priorityIdsArr = priorityIds.split(","); //把id串拼接成规则要用的形式  看id串能不能是 ,1,2的形式
				for (int i = 0; i < priorityIdsArr.length; i++) {
					if (!"".equals(priorityIdsArr[i])) {
						sbCon.append("priority == ").append(priorityIdsArr[i]);
						if (i<priorityIdsArr.length-1) {
							sbCon.append("||");
						}
					}
				}
				sbCon.append("),");
			}
			//条件部分
			sbCon.append(operatingAddOrPortCon(relevanceRule2));
			
			//去掉最后一个逗号
			if (sbCon.length()!=0) { //如果不填条件就不处理了
				sb.append(	sbCon.substring(0, sbCon.length()-1));
			}
		
			sb.append(");\r\n");
			sb.append("then\r\n");
			sb.append("group").append(relevanceRuleGroup.getId()).append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("events.add($event);\r\n");
			sb.append("group").append(relevanceRuleGroup.getId()).append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("strBuf.append($event.getIdentification()).append(\",\");\r\n");
			
			sb.append("long countTemp = Long.parseLong(group").append(relevanceRuleGroup.getId()).append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("countList.get(0).toString());\r\n");
			
			sb.append("group").append(relevanceRuleGroup.getId()).append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("countList.clear();\r\n");
			sb.append("group").append(relevanceRuleGroup.getId()).append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("countList.add(countTemp+$event.getAggregatedCount());\r\n");
			sb.append("end\r\n");
			sb.append("rule \"group").append(relevanceRuleGroup.getId()).append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("Time\"\r\n");
			sb.append("when\r\n");
			sb.append("Events($time:sendTime)&&eval($time-Long.parseLong(initTime(group").append(relevanceRuleGroup.getId()).append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("timeNow,$time).get(0).toString())>").append(relevanceRule2.getTime()).append("000);\r\n");
			sb.append("then\r\n");
			sb.append("group").append(relevanceRuleGroup.getId()).append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("events.clear();\r\n");
			sb.append("group").append(relevanceRuleGroup.getId()).append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("timeNow.clear();\r\n");
			sb.append("group").append(relevanceRuleGroup.getId()).append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("timeNow.add($time);\r\n");
			sb.append("group").append(relevanceRuleGroup.getId()).append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("countList.clear();\r\n");
			sb.append("group").append(relevanceRuleGroup.getId()).append("Rule").append(relevanceRule2.getRelevanceRuleId()).append("countList.add(0L);\r\n");
			sb.append("end\r\n");
			
		//	 drls.add(sb.toString());
				KnowledgeBuilder kb=KnowledgeBuilderFactory.newKnowledgeBuilder();
				kb.add(ResourceFactory.newReaderResource(new StringReader(sb.toString())), ResourceType.DRL);
			
				Collection collection=kb.getKnowledgePackages();
				if(kb.hasErrors()){
				System.out.println("有错误 检查");
				KnowledgeBuilderErrors kbuidlerErrors=kb.getErrors();
				for(Iterator
				iter=kbuidlerErrors.iterator();iter.hasNext();){
				System.out.println(iter.next());
				}
				}
				KnowledgeBase knowledgeBase=KnowledgeBaseFactory.newKnowledgeBase();
				knowledgeBase.addKnowledgePackages(collection);
				mapCon.put("statefulSessionCongroup"+relevanceRuleGroup.getId()+"Rule"+relevanceRule2.getRelevanceRuleId(), knowledgeBase.newStatefulKnowledgeSession());
		}
	}
	private Object operatingAddOrPortCon(RelevanceRule relevanceRule2) {
	StringBuffer sCon = new StringBuffer();//拼接条件的字符串
	
	//获得规则里的源ip 格式 等于:前一状态....
	String sourceAddTemp = relevanceRule2.getSourceAddr();
	if (!"".equals(sourceAddTemp)&&!sourceAddTemp.contains("前")) {//如果不是空  
		sCon.append("(");
		String sourceAdd = sourceAddTemp.split(",")[0]; //这里只要第一个数据 
			//不是前一...这个格式 按照正常方式解析
			String[] sourceAddArr = sourceAddTemp.split(","); //这里只要第一个数据 
			for (int i = 0; i < sourceAddArr.length; i++) {
				String[] stringArr = sourceAddArr[i].split(":");
				if (stringArr[0].equals("等于")) {
					sCon.append("sAdd == \"").append(stringArr[1]).append("\"");
				}else{
					sCon.append("sAdd != \"").append(stringArr[1]).append("\"");
				}
				if (i<sourceAddArr.length-1) {
					sCon.append("||");
				}
			}
		
		sCon.append("),");
	}
//拼接目的地址
	
	//获得规则里的目的ip 格式 等于:前一状态....
	String targetAddTemp = relevanceRule2.getTargetAddr();
	if (!"".equals(targetAddTemp)&&!targetAddTemp.contains("前")) {//如果不是空  
		sCon.append("(");
		String targetAdd = targetAddTemp.split(",")[0]; //这里只要第一个数据 
			//不是前一...这个格式 按照正常方式解析
			String[] targetAddArr = targetAddTemp.split(","); //这里只要第一个数据 
			for (int i = 0; i < targetAddArr.length; i++) {
				String[] stringArr = targetAddArr[i].split(":");
				if (stringArr[0].equals("等于")) {
					sCon.append("tAdd == \"").append(stringArr[1]).append("\"");
				}else{
					sCon.append("tAdd != \"").append(stringArr[1]).append("\"");
				}
				if (i<targetAddArr.length-1) {
					sCon.append("||");
				}
			}
		
		sCon.append("),");
	}
	
	//拼接源端口
	String sourcePortTemp = relevanceRule2.getSourcePort();
	if (!"".equals(sourcePortTemp)&&!sourcePortTemp.contains("前")) {//如果不是空  
		sCon.append("(");
		String sourcePort = sourcePortTemp.split(",")[0]; //这里只要第一个数据 
			//不是前一...这个格式 按照正常方式解析
			String[] sourcePortArr = sourcePortTemp.split(","); //这里只要第一个数据 
			for (int i = 0; i < sourcePortArr.length; i++) {
				String[] stringArr = sourcePortArr[i].split(":");
				if (stringArr[0].equals("等于")) {
					sCon.append("sPort == ").append(stringArr[1]);
				}else{
					sCon.append("sPort != ").append(stringArr[1]);
				}
				if (i<sourcePortArr.length-1) {
					sCon.append("||");
				}
			}
		
		sCon.append("),");
	}
	
	//拼接目标端口
			String targetPortTemp = relevanceRule2.getTargetPort();
			if (!"".equals(targetPortTemp)&&!targetPortTemp.contains("前")) {//如果不是空  
				sCon.append("(");
				String targetPort = targetPortTemp.split(",")[0]; //这里只要第一个数据 
					//不是前一...这个格式 按照正常方式解析
					String[] targetPortArr = targetPortTemp.split(","); //这里只要第一个数据 
					for (int i = 0; i < targetPortArr.length; i++) {
						String[] stringArr = targetPortArr[i].split(":");
						if (stringArr[0].equals("等于")) {
							sCon.append("dPort == ").append(stringArr[1]);
						}else{
							sCon.append("dPort != ").append(stringArr[1]);
						}
						if (i<targetPortArr.length-1) {
							sCon.append("||");
						}
					}
			
				sCon.append("),");
			}
	return sCon.toString();
}

	/**
	 * <过得events类中的devname的字符串>
	 * <传入一个设备父类的id 从tbl_device_category表中找他的下一级的DEVICE_CATEGORY_NAME 拼接成字符串,然后再拼接规则方法里用>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private String getDevName(String assetIds){
		// 设备种类名称
		StringBuffer strCate = new StringBuffer();
		String[] assetIdsArr = assetIds.split(",");
		for (int i = 0; i < assetIdsArr.length; i++) {
			if (!"".equals(assetIdsArr[i])) {
				try {
					List<Map> categoryName = GlobalConfig.assetManager.queryCategory(Long.parseLong(assetIdsArr[i]));
					for (Map map : categoryName) {
						strCate.append("devName == \"").append(map.get("eventsdevname")).append("\"||");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return strCate.substring(0, strCate.length()-2);
	}
		}


