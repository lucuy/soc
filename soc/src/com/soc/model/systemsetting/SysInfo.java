package com.soc.model.systemsetting;

import java.util.List;

public class SysInfo {
	
    // 系统当前时间
	private String sysNowTime;
	
	// 获取系统运行时间
	private String runTime;
	
	// 总的物理内存
	private String totalMemorySize;
	
	// 内存使用率
	private String memoryRatio;
	
	// 空闲内存
	private String unuseMemory;
	
	// 已使用的物理内存.
	private String usedMemory;
	
	// cpu使用率.
	private String cpuRatio;
	
	// cpu峰值
	private String cpuPeak;
	
	// 磁盘信息
	private List diskList;

	public List getDiskList() {
		return diskList;
	}

	public void setDiskList(List diskList) {
		this.diskList = diskList;
	}

	public String getRunTime() {
		return runTime;
	}

	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}

	public String getTotalMemorySize() {
		return totalMemorySize;
	}

	public void setTotalMemorySize(String totalMemorySize) {
		this.totalMemorySize = totalMemorySize;
	}

	public String getUsedMemory() {
		return usedMemory;
	}

	public void setUsedMemory(String usedMemory) {
		this.usedMemory = usedMemory;
	}

	public String getCpuRatio() {
		return cpuRatio;
	}

	public void setCpuRatio(String cpuRatio) {
		this.cpuRatio = cpuRatio;
	}

	public String getCpuPeak() {
		return cpuPeak;
	}

	public void setCpuPeak(String cpuPeak) {
		this.cpuPeak = cpuPeak;
	}

	public String getSysNowTime() {
		return sysNowTime;
	}

	public void setSysNowTime(String sysNowTime) {
		this.sysNowTime = sysNowTime;
	}

	public String getMemoryRatio() {
		return memoryRatio;
	}

	public String getUnuseMemory() {

		return unuseMemory;
	}

	public void setMemoryRatio(String memoryRatio) {
		this.memoryRatio = memoryRatio;
	}

	public void setUnuseMemory(String unuseMemory) {
		this.unuseMemory = unuseMemory;
	}

}