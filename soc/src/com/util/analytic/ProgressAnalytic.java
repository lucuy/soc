package com.util.analytic;

import com.soc.model.progressmsg.LinuxProgressMsg;
import com.soc.model.progressmsg.WinProgressMsg;
import com.util.StringUtil;

/**
 * 
 * <windows进程字符串解析工具> <功能详细描述>
 * 
 * @author gaosong
 * @version [版本号, 2013-5-8]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ProgressAnalytic {
	/**
	 * <解析过来的windows进程字符串> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
//Process:6|192.168.1.74|21/35/2013-05-13-10-19-24|21|QQ.exe|3220|Console|1|68,856 K|Running|Hang\HXH|0:02:24|申慧栋

	public static WinProgressMsg winProgressAnalytic(String string,String ipUserForProgressAndService,String mac) {
		WinProgressMsg msg = null;
		if (StringUtil.isNotEmpty(string)) {
			String[] data = string.split("\\|");//要转义
			
			// 字符串就直接放,其他通过字符串转换的东西就要加判断是不是null
			msg = new WinProgressMsg();
			msg.setMac(mac);
			msg.setIp(ipUserForProgressAndService);// 放ip
			msg.setTimestamp(data[2].split("/")[2]);// 放时间戳
			msg.setProgressNO(Integer.parseInt(data[3]));
			msg.setProgressName(data[4]);// 放程序名
			msg.setProgressNamePID(data[5]);
			msg.setDialogueName(data[6]);
			if (StringUtil.isNotEmpty(data[7])) {
				//String a = data[7];
				msg.setDialogueCount(Integer.parseInt(data[7]));
			}
			msg.setMemoryUsage(data[8]);
			if (data[9].equals("Running")) {
				msg.setState(1);
			} else {
				msg.setState(0);
			}
			msg.setUsername(data[10]);
			msg.setTimeCPU(data[11]);
			msg.setWinTitle(data[12]);
		}
		return msg;
	}
	//13|192.168.1.67|3/12/2013-05-25-13-33-05|1|5659|root|20|0|17256|6616|1704|R|99.2|0.6|0:00.52|standard0
	//日志种类 | 主机ip | 日志序号/日志总数/时间标签 | 日志序号 | PID |USER | PR | NI | VIRT | RES | SHR | S | %CPU | %MEM | TIME+ | COMMAND	
		public static LinuxProgressMsg LinuxProgressAnalytic(String string,String ipUserForProgressAndService,String mac) {
			LinuxProgressMsg msg = null;
			if (StringUtil.isNotEmpty(string)) {
				String[] data = string.split("\\|");//要转义
				
				// 字符串就直接放,其他通过字符串转换的东西就要加判断是不是null
				msg = new LinuxProgressMsg();
				msg.setMac(mac);
				msg.setIp(ipUserForProgressAndService);// 放ip
				msg.setTimestamp(data[2].split("/")[2]);// 放时间戳
				msg.setProgressNO(Integer.parseInt(data[3]));
				msg.setProgressNamePID(data[4]);
				msg.setUSER(data[5]);
				msg.setPR(data[6]);
				msg.setNI(data[7]);
				msg.setVIRT(data[8]);
				msg.setRES(data[9]);
				msg.setSHR(data[10]);
				msg.setS(data[11]);
				msg.setCPU(data[12]);
				msg.setMEM(data[13]);
				msg.setTIME(data[14]);
				msg.setCOMMAND(data[15]);
			}
			return msg;
		}
}