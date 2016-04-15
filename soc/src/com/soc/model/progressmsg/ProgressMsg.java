package com.soc.model.progressmsg;
/**
 * 
 * <封装c过来的监控进程的信息>
 * <功能详细描述>
 * 
 * @author  gaosong
 * @version  [版本号, 2013-5-8]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ProgressMsg {
//信息格式
	//6|192.168.1.18|22/43/2013-04-21-14-55-25|22|SogouExplorer.exe|5112|Console|1|57,008 K|Running|Hang\HXH|0:05:12|主页 - 我的最爱 - 搜狗高速浏览器
	//第一个是日志类型 这里舍弃,类型在处理数据的时候判断过了
	//Running|Hang\HXH|0:05:12|主页 - 我的最爱 - 搜狗高速浏览器
	// mac地址
	private String mac;
	//ip
	private String ip;
	//时间戳
	private String timestamp;
	//进程名
	private String progressName;
	//进程pid
	private String progressNamePID;
	//会话名
	private String dialogueName;
	//会话数
	private int dialogueCount;
	//内存使用 单位KB
	private String memoryUsage;
	//状态  运行状态 Running : 1 
	private int state;
	//用户名
	private String username;
	//CPU时间 是不是运行了多长时间? 应该是获得cpu时间片的时间吧
	private String timeCPU;
	//窗口标题
	private String  winTitle;
	//进程号
	private	int progressNO;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getProgressName() {
		return progressName;
	}
	public void setProgressName(String progressName) {
		this.progressName = progressName;
	}
	public String getProgressNamePID() {
		return progressNamePID;
	}
	public void setProgressNamePID(String progressNamePID) {
		this.progressNamePID = progressNamePID;
	}
	public String getDialogueName() {
		return dialogueName;
	}
	public void setDialogueName(String dialogueName) {
		this.dialogueName = dialogueName;
	}
	public int getDialogueCount() {
		return dialogueCount;
	}
	public void setDialogueCount(int dialogueCount) {
		this.dialogueCount = dialogueCount;
	}
	public String getMemoryUsage() {
		return memoryUsage;
	}
	public void setMemoryUsage(String memoryUsage) {
		this.memoryUsage = memoryUsage;
	}


	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getTimeCPU() {
		return timeCPU;
	}
	public void setTimeCPU(String timeCPU) {
		this.timeCPU = timeCPU;
	}
	public String getWinTitle() {
		return winTitle;
	}
	public void setWinTitle(String winTitle) {
		this.winTitle = winTitle;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	@Override
	public String toString() {
		return "ProgressMsg [mac=" + mac + ", ip=" + ip + ", timestamp="
				+ timestamp + ", progressName=" + progressName
				+ ", progressNamePID=" + progressNamePID + ", dialogueName="
				+ dialogueName + ", dialogueCount=" + dialogueCount
				+ ", memoryUsage=" + memoryUsage + ", state=" + state
				+ ", username=" + username + ", timeCPU=" + timeCPU
				+ ", winTitle=" + winTitle + "]";
	}
	public int getProgressNO() {
		return progressNO;
	}
	public void setProgressNO(int progressNO) {
		this.progressNO = progressNO;
	}
	

	
}
