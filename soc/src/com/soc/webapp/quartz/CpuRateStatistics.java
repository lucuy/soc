package com.soc.webapp.quartz;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import com.util.OSUtil;
import com.util.SystemInfoHandle;

public class CpuRateStatistics {
	private static float cpuPeak=0F;
	
	//启动定时器方法
	public void cpuRateStore() throws IOException, InterruptedException{
		if(OSUtil.getOSName().toLowerCase().contains("linux")){
			if(cpuPeak<SystemInfoHandle.getCpuInfo()){
				cpuPeak = SystemInfoHandle.getCpuInfo();
			}
		}
	}
	//获得cpu峰值
	public static String getCpuRatePeak(float cpuRate) throws IOException, InterruptedException{
		DecimalFormat dformat = (DecimalFormat)NumberFormat.getPercentInstance();
		dformat.applyPattern("#0%");
		if(cpuPeak<cpuRate){
			cpuPeak = cpuRate;
		}
		return dformat.format((double)cpuPeak);
	}
}