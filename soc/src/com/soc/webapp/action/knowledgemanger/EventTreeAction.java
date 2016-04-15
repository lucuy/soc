package com.soc.webapp.action.knowledgemanger;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.model.events.EventTree;
import com.soc.service.events.EventTreeService;
import com.soc.webapp.action.BaseAction;
import com.util.StringUtil;

public class EventTreeAction extends BaseAction {

	String ctx="soc";
	private StringBuffer htmlBuffer;
	private EventTreeService eventTreeService;
	List<EventTree> eventTreeList=null;
	
	public String queryTree(){
	    
		htmlBuffer=new StringBuffer("");
		
		eventTreeList = eventTreeService.queryEventTree();
		
		//根菜单数量
		int root=sonCount(eventTreeList.get(0).getId());
		drawSon(0,sonCount(0));
		
		return SUCCESS;
	}
	
	public void drawSon(int id,int count){
			int dsCount=0;
			for(int i =0 ;i<eventTreeList.size();i++){
				
				if(eventTreeList.get(i).getParent()==id){
					//html --隐藏类
					String clas="class=\"disply\"";
					//如果是根则正常显示
					if(eventTreeList.get(i).getParent()==0){
						clas="";
					}
					htmlBuffer.append("<ul "+clas+" ><li style=\"margin-top: 0px;\" id=\"query_"+eventTreeList.get(i).getAction()+"\">");
					if(sonCount(eventTreeList.get(i).getId())>0){
						htmlBuffer.append("<a href=\"javascript:action('"+eventTreeList.get(i).getAction()+"','img');\"> <img src=\"/soc/images/u321_normal.gif\" id=\"img_query_"+eventTreeList.get(i).getAction()+"\" ></a>"+
								"<a href=\"javascript:action('"+eventTreeList.get(i).getAction()+"','img')\">&nbsp;<span class=\"eventext\">&nbsp;"+eventTreeList.get(i).getName()+"</span></a>");
					}else{
						htmlBuffer.append("<a href=\"#\" class=\"common_node\""+
								" onclick=\"parent.mainFrame.location.href='/soc/vulnerability/queryEventLibraryList.action?eventLibraryType="+eventTreeList.get(i).getAction()+"';\">"+
								"<img src=\"/soc/images/arrow_03.gif\" /> <span style=\"vertical-align:top\">"+eventTreeList.get(i).getName()+"</span></a>");
					}
					drawSon(eventTreeList.get(i).getId(),sonCount(eventTreeList.get(i).getId()));
					htmlBuffer.append("</li></ul>");
					dsCount=dsCount+1;
					if(dsCount>=count){
						break;
					}
				}
			}
		
	}
	public int sonCount(int id){
		int sonCount=0;
		//判断是否有儿子
		for(int i=0;i<eventTreeList.size();i++){
			if(eventTreeList.get(i).getParent()==id){
				sonCount=sonCount+1;
			}
			if(sonCount>0 && eventTreeList.get(i).getParent()!=id){
				break;
			}
		}
		return sonCount;
	}
	public EventTreeService getEventTreeService() {
		return eventTreeService;
	}

	public void setEventTreeService(EventTreeService eventTreeService) {
		this.eventTreeService = eventTreeService;
	}

	public StringBuffer getHtmlBuffer() {
		return htmlBuffer;
	}

	public void setHtmlBuffer(StringBuffer htmlBuffer) {
		this.htmlBuffer = htmlBuffer;
	}
	
}
