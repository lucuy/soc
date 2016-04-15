package com.soc.webapp.action.layout;

import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.soc.webapp.action.BaseAction;


/**
 * Description: 头部Action			
 * @author 郭煜玺                         
 * @Version 1.0                            
 * @Created at 2010-12-25                
 * @Modified by         
 */
public class FrameAction extends BaseAction {
	
	private transient Log log = LogFactory.getLog(getClass());
	
	private static final long serialVersionUID = 1L;

	private long dateTime;
	private String todayWeekday;
	
	private String message;
   
	private String runTime;

	/**
	 * 显示系统头部
	 * @return
	 */
	public String frameHeader(){
		
		// 初始化日期时间
		dateTime = new Date().getTime();
		//todayWeekday = DateUtil.curDateWeekday();
		
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		 
	    // String nowDate = sdf.format(new Date());
	     
	     
	   //runTime = DateCompare.compareYMD(GlobalConfig.installTime, nowDate);
	     
		//初始化通知公告
		//在此实现.............................
		//System.out.println("server ip addr: " + super.getRequest().getRemoteAddr());
		//System.out.println("local ip addr: " + super.getRequest().getLocalAddr());
		
		return SUCCESS;
	}
	
	/**
	 * 显示系统框架
	 * @return
	 */
	public String frame(){
		return SUCCESS;
	}

	public long getDateTime() {
		return dateTime;
	}

	public void setDateTime(long dateTime) {
		this.dateTime = dateTime;
	}

	public String getTodayWeekday() {
		return todayWeekday;
	}

	public void setTodayWeekday(String todayWeekday) {
		this.todayWeekday = todayWeekday;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

    public String getRunTime()
    {
        return runTime;
    }

    public void setRunTime(String runTime)
    {
        this.runTime = runTime;
    }
}
