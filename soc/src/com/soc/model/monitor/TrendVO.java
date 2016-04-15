package com.soc.model.monitor;

public class TrendVO {
	private String trendTime;
	private String trendIp;
	private long trendCount;
	public String getTrendTime() {
		return trendTime;
	}
	public void setTrendTime(String trendTime) {
		this.trendTime = trendTime;
	}
	public String getTrendIp() {
		return trendIp;
	}
	public void setTrendIp(String trendIp) {
		this.trendIp = trendIp;
	}
	public long getTrendCount() {
		return trendCount;
	}
	public void setTrendCount(long trendCount) {
		this.trendCount = trendCount;
	}
	
}
