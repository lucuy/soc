package com.soc.junit.test.sql.sysbase;

public class SyBaseTableSpace {
	private String name;//表空间名字
	private int total;//总空间M
	private double inuse;//已使用M
	private double usage;//使用百分比
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public double getInuse() {
		return inuse;
	}
	public void setInuse(double inuse) {
		this.inuse = inuse;
	}
	public double getUsage() {
		return usage;
	}
	public void setUsage(double usage) {
		this.usage = usage;
	}
	
	
}
