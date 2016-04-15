package com.soc.model.progressmsg;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * <封装c过来的windows软件的信息>
 * <功能详细描述>
 * 
 * @author  gaosong
 * @version  [版本号, 2014-4-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class WinSoftMsg implements Serializable{
	//信息格式
		//16|1.1.1.1|1/174/2014-04-27-21-35-45|1|360驱动大师|1.0.0.1065||360安全中心|C:\\Program Files (x86)\\360\\360Drvmgr
		//第一个是日志类型 这里舍弃,类型在处理数据的时候判断过了
		//mac地址
		private String mac;
		// ip地址
		private String ip;
		//软件总数
		private int softTotalCount;
		//时间戳
		private String timestamp;
		//软件顺序
		private int softId;
		//软件名
		private String softName;
		//软件版本
		private String softVerstion;
		//软件厂商
		private String softFirm;
		//软件路径
		private String softPath;
		//软件安装时间
		private String installDate;
		//
		private Date fromDate;
		
		public String getInstallDate() {
			return installDate;
		}
		public void setInstallDate(String installDate) {
			this.installDate = installDate;
		}
		public String getIp() {
			return ip;
		}
		public void setIp(String ip) {
			this.ip = ip;
		}
		public int getSoftTotalCount() {
			return softTotalCount;
		}
		public void setSoftTotalCount(int softTotalCount) {
			this.softTotalCount = softTotalCount;
		}
		public String getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}
		public int getSoftId() {
			return softId;
		}
		public void setSoftId(int softId) {
			this.softId = softId;
		}
		public String getSoftName() {
			return softName;
		}
		public void setSoftName(String softName) {
			this.softName = softName;
		}
		public String getSoftVerstion() {
			return softVerstion;
		}
		public void setSoftVerstion(String softVerstion) {
			this.softVerstion = softVerstion;
		}
		public String getSoftFirm() {
			return softFirm;
		}
		public void setSoftFirm(String softFirm) {
			this.softFirm = softFirm;
		}
		public String getSoftPath() {
			return softPath;
		}
		public void setSoftPath(String softPath) {
			this.softPath = softPath;
		}
		public String getMac() {
			return mac;
		}
		public void setMac(String mac) {
			this.mac = mac;
		}
		public Date getFromDate() {
			return fromDate;
		}
		public void setFromDate(Date fromDate) {
			this.fromDate = fromDate;
		}
		
}
