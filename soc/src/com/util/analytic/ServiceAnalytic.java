package com.util.analytic;

import com.soc.model.servicemsg.LinuxServiceMsg;
import com.soc.model.servicemsg.WinServiceMsg;
import com.util.StringUtil;

/**
 * 解析服务信息的类
 * 
 * @author gaosong
 * 
 */
//
public class ServiceAnalytic {
	//	Service:8|192.168.1.74|66/70/2013-05-13-10-19-28|66|Security Center|1|1|0|1
	// Service:8|192.168.1.74|2/70/2013-05-13-10-19-28|2|AFBAgent|1|1|1|1

	public static WinServiceMsg WinServiceAnalytic(String string,
			String ipUserForProgressAndService, String mac) {

		WinServiceMsg msg = null;
		if (StringUtil.isNotEmpty(string)) {
			msg = new WinServiceMsg();
			String[] data = string.split("\\|");// 要转移
			msg.setMac(mac);// 放mac
			msg.setIp(ipUserForProgressAndService);// 放ip
			msg.setTimestamp(data[2].split("/")[2]);	
			msg.setServiceNO(Integer.parseInt(data[3]));//放日志序号 作为cache中元素的key 数据里只有这个不一样,当然还有service名
			msg.setServiceName(data[4]);// 服务名
			if (data[5].equals("1")) {
				msg.setInstallStatus("已安装");// 安装状态
			}else {
				msg.setInstallStatus("未安装");// 安装状态
			}
			if (data[6].equals("1")) {
				msg.setOperatestatus("正在运行");// 操作状态 正在运行....未运行
			}else {
				msg.setOperatestatus("未运行");// 操作状态 正在运行....未运行
			}
			if (data[7].equals("1")) {
				msg.setCanBeUninstall("可卸载");// 是否可卸载
			}else {
				msg.setCanBeUninstall("不可卸载");// 是否可卸载
			}
			if (data[8].equals("1")) {
				msg.setCanBeStop("不可停止");// 是否可停止
			}else {
				msg.setCanBeStop("可以停止");// 是否可停止
			}
			

		}

		return msg;
	}
	
//	日志种类 | 主机ip | 日志序号/日志总数/时间标签 |日志序号 | 一行内容
//15|192.168.1.67|2/45/2013-05-25-13-33-05|2|[ + ]  atd
	public static LinuxServiceMsg LinuxServiceAnalytic(String string,
			String ipUserForProgressAndService, String mac) {

		LinuxServiceMsg msg = null;
		if (StringUtil.isNotEmpty(string)) {
			msg = new LinuxServiceMsg();
			String[] data = string.split("\\|");// 要转移
			msg.setMac(mac);// 放mac
			msg.setIp(ipUserForProgressAndService);// 放ip
			msg.setTimestamp(data[2].split("/")[2]);	
			msg.setServiceNO(Integer.parseInt(data[3]));//放日志序号 作为cache中元素的key 数据里只有这个不一样,当然还有service名
			msg.setContent(data[4]);

		}

		return msg;
	}
	
}