package com.soc.model.satellite;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 卫星直播中心审计系统事件类
 * 
 * @author gaosong
 * 
 */
public class Events {
	private int eventsId;// 事件id
	private long countSuccess;// 推送成功数量
	private String synStartTime;//开始同步时间
	private String synType;//同步类型
	private String synSys;//同步系统
	private String customs1;
	private String customs2;
	private String customs3;
	private String customs4;
	private int customs5;
	private int customs6;
	private int customs7;
	private long synCount;//同步数量
	private String synStartTimeFormat;//开始同步时间 字符串类型的时间
	public int getEventsId() {
		return eventsId;
	}
	public void setEventsId(int eventsId) {
		this.eventsId = eventsId;
	}
	public long getSynCount() {
		return synCount;
	}
	public void setSynCount(long synCount) {
		this.synCount = synCount;
	}
	public long getCountSuccess() {
		return countSuccess;
	}
	public void setCountSuccess(long countSuccess) {
		this.countSuccess = countSuccess;
	}
	

	public String getSynStartTime() {
		return synStartTime;
	}
	public void setSynStartTime(String synStartTime) {
		this.synStartTime = synStartTime;
	}
	public String getSynType() {
		return synType;
	}
	public void setSynType(String synType) {
		this.synType = synType;
	}
	public String getSynSys() {
		return synSys;
	}
	public void setSynSys(String synSys) {
		this.synSys = synSys;
	}
	public String getCustoms1() {
		return customs1;
	}
	public void setCustoms1(String customs1) {
		this.customs1 = customs1;
	}
	public String getCustoms2() {
		return customs2;
	}
	public void setCustoms2(String customs2) {
		this.customs2 = customs2;
	}
	public String getCustoms3() {
		return customs3;
	}
	public void setCustoms3(String customs3) {
		this.customs3 = customs3;
	}
	public String getCustoms4() {
		return customs4;
	}
	public void setCustoms4(String customs4) {
		this.customs4 = customs4;
	}
	public int getCustoms5() {
		return customs5;
	}
	public void setCustoms5(int customs5) {
		this.customs5 = customs5;
	}
	public int getCustoms6() {
		return customs6;
	}
	public void setCustoms6(int customs6) {
		this.customs6 = customs6;
	}
	public int getCustoms7() {
		return customs7;
	}
	public void setCustoms7(int customs7) {
		this.customs7 = customs7;
	}
	public String getSynStartTimeFormat() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(this.synStartTime));
	}
	public void setSynStartTimeFormat(String synStartTimeFormat) {
		this.synStartTimeFormat = synStartTimeFormat;
	}

	
}
