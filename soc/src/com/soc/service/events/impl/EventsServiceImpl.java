package com.soc.service.events.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.EventPullSource;

import com.push.services.SendMessage;
import com.soc.dao.events.EventsDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.events.Events;
import com.soc.service.events.EventsService;
import com.util.IpConvert;
import com.util.page.Page;
import com.util.page.SearchResult;

public class EventsServiceImpl extends EventPullSource implements Serializable , EventsService
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    private EventsDao eventsDao; //事件Dao
    public static String ceshi;//已经废弃
  //数据推送类
  	private SendMessage msg;//已经废弃
    public long insertEvents(ConcurrentLinkedQueue<Events> eventsQueue)
    {
     	String userid= "shishijiankong";
           eventsDao.insertEvents(eventsQueue,userid);
//           Events eve= eventsQueue.poll();
//           try{
//               
//        	  ceshi=this.ceshi(eve);
//        	  
//               msg.sendMessageAuto1(ceshi); 
//
//           }
//           catch(Exception e){
//               
//               Log.info("产生失败");
//           }
           
           return 0;

    }
    
    @Override
    public void insertOriginLog(ConcurrentLinkedQueue<Map<String, Object>> rawLogQueue)
    {
        eventsDao.insertOriginLog(rawLogQueue);
    }
    

    @Override
    public Events queryEvents(Map<String,Object> map)
    {
        return eventsDao.queryEvents(map);
    }
    
    /**
     * {@inheritDoc}
     */
    public int existsTable(String tableName)
    {
        return eventsDao.existsTable(tableName);
    }
    
    /**
     * {@inheritDoc}
     */
    public String existsSeq(String seqName)
    {
        return eventsDao.existsSeq(seqName);
    }
    
    /**
     * {@inheritDoc}
     */
    public void createLogSeq(String seqName)
    {
        eventsDao.createLogSeq(seqName);
    }
    
    /**
     * {@inheritDoc}
     */
    public void createLogTable(Map<String, String> map)
    {
        eventsDao.createLogTable(map);
    }
    
    /**
     * {@inheritDoc}
     */
    public void createNotAnalyticEventsTable(Map<String, String> map)
    {
        eventsDao.createNotAnalyticEventsTable(map);
    }
    
    public EventsDao getEventsDao()
    {
        return eventsDao;
    }
    
    public void setEventsDao(EventsDao eventsDao)
    {
        this.eventsDao = eventsDao;
    }

    @Override
    public List<Map<String, Object>> querProtocol()
    {
        return eventsDao.querProtocol();
    }

    /** {@inheritDoc} */
     
    @Override
    public int countRecentScreenEvents(Map map)
    {
        // TODO Auto-generated method stub
        return eventsDao.countRecentScreenEvents(map);
    }

    /** {@inheritDoc} */
     
    @Override
    public SearchResult queryRecentScreenEvents(Map map, Page page)
    {
        // TODO Auto-generated method stub
     // TODO Auto-generated method stub
        int rowsCount = eventsDao.countRecentScreenEvents(map);
        page.setTotalCount(rowsCount);
        
        List<Events> list = eventsDao.queryRecentScreenEvents(map, page.getStartIndex(), page.getPageSize());
        //对查找结果进行处理
     
        SearchResult sr = new SearchResult();
        sr.setList(list);
        sr.setPage(page);
        return sr;
    }
    
    @Override
	public Object getEventsByToday(Map map) {
		// TODO Auto-generated method stub

        // TODO Auto-generated method stub
    	 //return alertMessgeDao.queryCurrentMessageByAsset(map);
        Object object = "";
        
        int temp = 0;
        
        List<Map> mapList = new ArrayList<Map>();
        
        mapList = eventsDao.queryTodayEventsByPriority(map);
        
        StringBuffer buffer = new StringBuffer();
        
        for (Map map1 : mapList)
        {
            String assetTemp = map1.get("key").toString();
            //定义颜色
            String colorType = "" ; 
            long assetCount = Integer.parseInt(map1.get("value").toString());
            String assetTempName = "";
            if(assetTemp.equals("1")){
            	assetTempName  = "信息";
            	colorType = "#89A54E" ; //绿色  
            }else if(assetTemp.equals("2")){
            	assetTempName  = "低级";
            	colorType = "#80699B" ; //紫色
            }else if(assetTemp.equals("3")){
            	assetTempName  = "中级";
            	colorType = "#FFFF00" ;  //黄色
            }else if(assetTemp.equals("4")){
            	assetTempName  = "高级";
            	colorType = "#DF7000" ;  //橙色
            }else if(assetTemp.equals("5")){
            	assetTempName  = "严重";
            	colorType = "#AA4643" ;  //红色
            }
            if (temp == 0)
            {
                buffer.append("{name:'" + assetTempName + "',color:'"+colorType+"',data:[" + assetCount + "]}");
            }
            else
            {
               buffer.append(",{name:'" + assetTempName + "',color:'"+colorType+"',data:[" + assetCount + "]}");
            }
            temp++;
        }
        
        
        object = "" + buffer + "";
        
        return object;
    
	}

	/*@Override    
	public Events quertEventsByLog_ID(Map map) {
		return eventsDao.quertEventsByLog_ID(map);
	}*/
  //已经废弃
	@Override
	protected long getSleepTime() {
		
		return 1000;
	}
	//已经废弃
	@Override
	protected Event pullEvent() {
	    
		   Event event = Event.createDataEvent("/zhaoyang/hi");
	
		 
			event.setField("hi", ceshi);
			
			//event.setField("eventsid", list.toString());
		
		return event;
		
		
	}
	/*
	 * 拼接表格
	 */
	//已经废弃
	public String ceshi(Events eve){
		
		
		 StringBuffer sb =new StringBuffer();
		 sb.append("<tr class=\"back\" id=\"eventsTr_\"").append("onmousemove=\"this.className='hand'\" onmouseout=\"this.className='back'\">")
		 ;
//		 sb.append("<td></td>");
		 sb.append("<td valign=\"middle\" align=\"center\">  <div class=\"level\">");
			if (eve.getPriority() <= 1) {
				sb.append(
						"<div class=\"levelBa\" style=\"background-color#CCCCCC; width:")
						.append(eve.getPriority() * 8)
						.append("px;\"></div><span style=\"position:relative;left:0px;top:-20px;\">")
						.append(eve.getPriority()).append("</span>");
			}
			if (eve.getPriority() > 1&& eve.getPriority()<=3) {
				sb.append(
						"<div class=\"levelBa\" style=\"background-color:#ffcc33; width:")
						.append(eve.getPriority() * 8)
						.append("px;\"></div><span style=\"position:relative;left:0px;top:-20px;\">")
						.append(eve.getPriority()).append("</span>");
			}
			if (eve.getPriority() >= 4) {
				sb.append(
						"<div class=\"levelBa\" style=\"background-color:#ff3333; width:")
						.append(eve.getPriority() * 8)
						.append("px;\"></div><span style=\"position:relative;left:0px;top:-20px;\">")
						.append(eve.getPriority()).append("</span>");
			}
			sb.append("</div></td>");
			try{
				sb.append("<td valign=\"middle\" align=\"center\">").append(GlobalConfig.eventTypeTag.get(Long.parseLong(eve.getType()))).append("</td>");
			}catch (Exception e) {
				sb.append("<td valign=\"middle\" align=\"center\">").append(eve.getType()).append("</td>");
			}
			
			sb.append("<td valign=\"middle\" align=\"center\">").append(eve.getsAdd()).append("</td>");
			sb.append("<td valign=\"middle\" align=\"center\">").append(eve.getsPort()).append("</td>");
			sb.append("<td valign=\"middle\" align=\"center\">").append(eve.gettAdd()).append("</td>");
			sb.append("<td valign=\"middle\" align=\"center\">").append(eve.getdPort()).append("</td>");
			sb.append("<td valign=\"middle\" align=\"center\">").append(eve.getAggregatedCount()).append("</td>");
			sb.append("<td valign=\"middle\" align=\"center\">").append(eve.getReceptTimes()).append("</td></tr>");
			return sb.toString();
	}
	
	@Override
	public String queryEventsTrendAnalysis(Map map) {
		long time=new Date().getTime();
		//修改时间戳，只精确到小时
		long endTime=time/1000/60/60*60*60*1000;
		
		//时间段的开始时间
		long tempTime1=0;
		//时间段的结束时间
		long tempTime2=0;
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		
		StringBuffer sb=new StringBuffer();
		
		//传参数的map
		
		
		int count=0;
		//拼接数据
		for (int i = 0; i < 10; i++) {
			
			tempTime1=0;
			
			tempTime2=0;
			
			
			//为开始时间赋值
			tempTime1=endTime-((10-i)*60*60*1000);
			//为结束时间赋值
			tempTime2=endTime-((10-1-i)*60*60*1000);
			//查询的表名
			String tbl="tbl_"+sdf.format(new Date(tempTime1));
			map.put("tempTime1", tempTime1);
			map.put("tempTime2", tempTime2);
			map.put("tbl", tbl);
			if (eventsDao.existsTable(tbl)>0) {
				count=eventsDao.queryEventsTrendAnalysis(map);
			}else{
				count=0;
			}

			//开始拼接数据
			if (sb.length()>0) {
				sb.append(",");
			}
			sb.append("["+tempTime2+","+count+"]");
		}
		return sb.toString();
	}

	public SendMessage getMsg() {
		return msg;
	}

	public void setMsg(SendMessage msg) {
		this.msg = msg;
	}

	@Override
	public void insertEventsDrools(Events eventTemp) {
		this.eventsDao.insertEventsDrools(eventTemp);
		
	}

	@Override
	public int exsitsqlServerTable(String tableName) {
		return eventsDao.exsitsqlServerTable(tableName);
	}

	@Override
	public String queryEventByCategory(Map map) {
		StringBuffer sb=new StringBuffer();
		//获得当前日期的表
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String tbl="tbl_"+sdf.format(date);
		
		//查询事件，参数放在map里，用list接收
		
		map.put("tbl", tbl);
		List<Map<String, Object>> list=eventsDao.queryEventByCategory(map);
		
	
		String temp="";
		String key="";
		for (Map<String, Object> map2 : list) {
			key=(String) map2.get("key");
			
			//去除空格
			key=key.trim();
			
			//从第二个开始要在前面加“,”
			if (sb.length()>1) {
				sb.append(",");
			}
			
			//如果是空的就是未知类型
			if (key.isEmpty()) {
				temp="未定义类型";
			//用正则去匹配，如果是数字就转化
			}else if(key.matches("\\d{2}")){
				temp=GlobalConfig.eventCategoryTag.get(key);
			//最后一种情况是文字，直接使用
			}else{
				temp=key;
			}
			sb.append("['"+temp+"',"+map2.get("value")+"]");
			
		}
		
		return sb.toString();
	}

	@Override
	public String queryEventByIP(Map map) {
		//获取当前时间
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		
		//转换为表名，放进map里面
		String tbl="tbl_"+sdf.format(date);
		
		map.put("tbl", tbl);
		
		//查询结果
		List<Map<String, Object>> list=eventsDao.queryEventByIP(map);
		
		//开始拼接
		StringBuffer count=new StringBuffer();
		
		for (Map<String, Object> map2 : list) {
			//拼接数据
			if (count.length()>1) {
				count.append(",");
			}
			count.append(
					"{name: \""+
					IpConvert.IpString((long)Long.parseLong(map2.get("key").toString()))+
					"\",data: ["+
					map2.get("value").toString()+
					"]}"
					);
			
		}
		
		
		return count.toString();
	}

	@Override
	public void deleteTable(String tableName) {
		eventsDao.deleteTable(tableName);
		
	}

	@Override
	public void deleteSequence(String sequence) {
		eventsDao.deleteSequence(sequence);
		
	}

}

