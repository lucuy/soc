package com.soc.service.systemsetting.impl;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import com.soc.model.systemsetting.SysInfo;
import com.soc.webapp.quartz.CpuRateStatistics;
import com.util.StringUtil;
import com.util.SystemInfoHandle;

public class SystemInfoService {
	public static SysInfo getSysInfo() throws IOException, InterruptedException {
		DecimalFormat dformat = (DecimalFormat) NumberFormat.getPercentInstance();
		int[] memInfo = SystemInfoHandle.getMemInfo();
		float cupInfo = SystemInfoHandle.getCpuInfo();
		List diskInfo = SystemInfoHandle.getDiskInfo();
		dformat.applyPattern("#0%");
		SysInfo sysInfo = new SysInfo();
		sysInfo.setTotalMemorySize(StringUtil.formate(memInfo[0]));
		sysInfo.setUnuseMemory(StringUtil.formate(memInfo[1]));
		sysInfo.setUsedMemory(StringUtil.formate(memInfo[0] - memInfo[1]));
		float meMoryRatio = (float) (memInfo[0] - memInfo[1])/((float) memInfo[0])*100;
		sysInfo.setMemoryRatio(String.valueOf(meMoryRatio));
		sysInfo.setRunTime(SystemInfoHandle.getSysRuntime());
		sysInfo.setCpuRatio(dformat.format(SystemInfoHandle.getCpuInfo()));
		sysInfo.setCpuPeak(CpuRateStatistics.getCpuRatePeak(cupInfo));
		sysInfo.setDiskList(diskInfo);
		return sysInfo;
	}
}
