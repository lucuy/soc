package com.util.analytic;

import com.soc.model.progressmsg.WinSoftMsg;
import com.util.StringUtil;

/**
 * 解析软件信息的类
 * 
 * @author gaosong
 * 
 */
//
public class WinSoftAnalytic {
	//16|1.1.1.1|1/174/2014-04-27-21-35-45|1|360驱动大师|1.0.0.1065||360安全中心|C:\\Program Files (x86)\\360\\360Drvmgr
	public static WinSoftMsg SoftAnalytic(String string,
			String ipUserForProgressAndService, String mac) {

		WinSoftMsg msg = null;
		if (StringUtil.isNotEmpty(string)) {
			msg = new WinSoftMsg();
			System.out.println("String:" + "\n" + string);
			String[] data = string.split("\\|");// 要转移
			msg.setMac(mac);// 放mac
			msg.setIp(ipUserForProgressAndService);// 放ip
			msg.setTimestamp(data[2].split("/")[1]);	//放总数
			msg.setTimestamp(data[2].split("/")[2]);	//放时间戳
			msg.setSoftId(Integer.parseInt(data[3]));//放软件的序列
			msg.setSoftName(data[4]);//软件名
			if (".".equals(data[5])||"".equals(data[5].trim())) {
				msg.setSoftVerstion("未知");//软件版本
			}else {
				msg.setSoftVerstion(data[5]);//软件版本
			}
			if ("".equals(data[6])) {
				msg.setInstallDate("未知");//时间
			}else {
				msg.setInstallDate(data[6]);//时间
			}
		
			msg.setSoftFirm(data[7]);//厂商４
			try {
				msg.setSoftPath(data[8]);//路径
			} catch (Exception e) {
				msg.setSoftPath("未知");//路径
			}
		

		}

		return msg;
	}
}