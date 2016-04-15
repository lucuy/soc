package com.soc.model.monitor.servers.tomcat;

public class TomcatDetail{
	//private MonitorTomcatTask montt;
	private String startTime;//启动时间
	private String timeSpan;//连续工作时间
	private double maxMemory;//最大内存
	private String usedMemory;//内存使用
	private String unUsedMemory;//未使用
	private String protocolPort;//通信协议端口
    //private Integer maxThreads;//最大线程数
    private Integer currentThreadCount;//当前线程数
    private Integer currentThreadsBusy;//当前活跃线程数
    private String projectName;//应用会话应用名称集合
    private String portName;//线程通信协议端口集合
    private String currentThread;//当前线程数集合
    private String busyThread;//当前活跃线程数集合
    private String useAble;//可用性标识集合
    private String sessionUse;//应用上活动会话数集合
    

	public String getUnUsedMemory() {
		return unUsedMemory;
	}

	public void setUnUsedMemory(String unUsedMemory) {
		this.unUsedMemory = unUsedMemory;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPortName() {
		return portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	public String getCurrentThread() {
		return currentThread;
	}

	public void setCurrentThread(String currentThread) {
		this.currentThread = currentThread;
	}

	public String getBusyThread() {
		return busyThread;
	}

	public void setBusyThread(String busyThread) {
		this.busyThread = busyThread;
	}

	public String getUseAble() {
		return useAble;
	}

	public void setUseAble(String useAble) {
		this.useAble = useAble;
	}

	public String getSessionUse() {
		return sessionUse;
	}

	public void setSessionUse(String sessionUse) {
		this.sessionUse = sessionUse;
	}

	public TomcatDetail() {
		super();
	}

    public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getTimeSpan() {
		return timeSpan;
	}

	public void setTimeSpan(String timeSpan) {
		this.timeSpan = timeSpan;
	}

	public double getMaxMemory() {
		return maxMemory;
	}

	public void setMaxMemory(double maxMemory) {
		this.maxMemory = maxMemory;
	}

	public String getUsedMemory() {
		return usedMemory;
	}

	public void setUsedMemory(String usedMemory) {
		this.usedMemory = usedMemory;
	}

	public String getProtocolPort() {
		return protocolPort;
	}

	public void setProtocolPort(String protocolPort) {
		this.protocolPort = protocolPort;
	}

	public Integer getCurrentThreadCount() {
		return currentThreadCount;
	}

	public void setCurrentThreadCount(Integer currentThreadCount) {
		this.currentThreadCount = currentThreadCount;
	}

	public Integer getCurrentThreadsBusy() {
		return currentThreadsBusy;
	}

	public void setCurrentThreadsBusy(Integer currentThreadsBusy) {
		this.currentThreadsBusy = currentThreadsBusy;
	}
}
