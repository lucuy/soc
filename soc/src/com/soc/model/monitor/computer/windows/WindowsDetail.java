package com.soc.model.monitor.computer.windows;

import java.io.IOException;
import java.util.Vector;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import com.soc.model.monitor.MonitorWindowsTask;

public class WindowsDetail {
	private String timeSpan;//连续工作时间
	private String hostName;//主机名称
	private String usedMemory;//内存使用
	private String unUsedMemory;//未使用
	private String diskName;//磁盘名称
	private String usedDisk;//磁盘利用率
	private String usedCpu;//cpu利用率
	private String unUsedCpu;//
	private String allProcess;//所有进程
    private String allSofts;//所有软件
    private String allServices;//所有服务
    private String interfaceName;//所有网络接口
    private String sendFlow;//发送流量
    private String arriveFlow;//接收流量
    private String sendError;//发送错误
    private String arriveError;//接收错误
    private String sendLose;//发送错误
    private String arriveLose;//接收错误
    private Snmp snmp = null;
    private Address targetAddress = null;

    public ResponseEvent sendPDU( MonitorWindowsTask mwt,String[] oids) throws IOException{
    	// 设置Agent方的IP和端口
        targetAddress = GenericAddress.parse("udp:"+mwt.getWinIp()+"/"+mwt.getWinPort());
        @SuppressWarnings("rawtypes")
		TransportMapping transport;
			try {
				transport = new DefaultUdpTransportMapping();
				snmp = new Snmp(transport);
			    transport.listen();
			          
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 // 设置 target
		           CommunityTarget target = new CommunityTarget();
		           target.setCommunity(new OctetString(mwt.getCommunityName()));
		           target.setAddress(targetAddress);
		           // 通信不成功时的重试次数
		           target.setRetries(2);
		           // 超时时间
		           target.setTimeout(1500);
		           if(mwt.getSnmpType()=="1"){
		   			target.setVersion(SnmpConstants.version1);
		   			}else if(mwt.getSnmpType()=="2c"){
		   				target.setVersion(SnmpConstants.version2c);
		   			}else if(mwt.getSnmpType()=="3"){
		   				target.setVersion(SnmpConstants.version3);
		   			}
		           // 创建 PDU
		           PDU pdu = new PDU();
		           pdu.add(new VariableBinding(new OID(oids[0])));
		           // MIB的访问方式
		           pdu.setType(PDU.GET);
		           // 向Agent发送PDU，并接收Response
		           ResponseEvent respEvnt = snmp.send(pdu, target);
		           return respEvnt;
    }
    public String getTime( MonitorWindowsTask mwt,String[] oids ){
    	String time = null;
    	 ResponseEvent respEvnt;
		try {
			respEvnt = sendPDU(mwt, oids);

	           // 解析Response
	           if (respEvnt != null && respEvnt.getResponse() != null) {
	                  @SuppressWarnings("unchecked")
					Vector<VariableBinding> recVBs = (Vector<VariableBinding>) respEvnt.getResponse().getVariableBindings();
	                  for (int i = 0; i < recVBs.size(); i++) {
	                         VariableBinding recVB = recVBs.elementAt(i);
	                         //System.out.println(recVB.getOid() + " : " + recVB.getVariable());
	                         time=recVB.getVariable()+"";
	                  }
	                  return time;
	           }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		          
		return time;
    }
    public String getHostName(MonitorWindowsTask mwt,String[] oids){
    	String hostName = null;
   	 ResponseEvent respEvnt;
		try {
			respEvnt = sendPDU(mwt, oids);

	           // 解析Response
	           if (respEvnt != null && respEvnt.getResponse() != null) {
	                  @SuppressWarnings("unchecked")
					Vector<VariableBinding> recVBs = (Vector<VariableBinding>) respEvnt.getResponse().getVariableBindings();
	                  for (int i = 0; i < recVBs.size(); i++) {
	                         VariableBinding recVB = recVBs.elementAt(i);
	                         //System.out.println(recVB.getOid() + " : " + recVB.getVariable());
	                         hostName=recVB.getVariable()+"";
	                  }
	                  return hostName;
	           }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		          
		return hostName;
    }
    
    public String getTimeSpan() {
		return timeSpan;
	}
	public void setTimeSpan(String timeSpan) {
		this.timeSpan = timeSpan;
	}
	
	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getUsedMemory() {
		return usedMemory;
	}
	public void setUsedMemory(String usedMemory) {
		this.usedMemory = usedMemory;
	}
	public String getUnUsedMemory() {
		return unUsedMemory;
	}
	public void setUnUsedMemory(String unUsedMemory) {
		this.unUsedMemory = unUsedMemory;
	}
	public String getDiskName() {
		return diskName;
	}
	public void setDiskName(String diskName) {
		this.diskName = diskName;
	}
	public String getUsedDisk() {
		return usedDisk;
	}
	public void setUsedDisk(String usedDisk) {
		this.usedDisk = usedDisk;
	}
	public String getUsedCpu() {
		return usedCpu;
	}
	public void setUsedCpu(String usedCpu) {
		this.usedCpu = usedCpu;
	}
	 public String getUnUsedCpu() {
			return unUsedCpu;
	}
	public void setUnUsedCpu(String unUsedCpu) {
			this.unUsedCpu = unUsedCpu;
	}
	
	public String getAllProcess() {
		return allProcess;
	}
	public void setAllProcess(String allProcess) {
		this.allProcess = allProcess;
	}
	public String getAllSofts() {
		return allSofts;
	}
	public void setAllSofts(String allSofts) {
		this.allSofts = allSofts;
	}
	public String getAllServices() {
		return allServices;
	}
	public void setAllServices(String allServices) {
		this.allServices = allServices;
	}
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public String getSendFlow() {
		return sendFlow;
	}
	public void setSendFlow(String sendFlow) {
		this.sendFlow = sendFlow;
	}
	public String getArriveFlow() {
		return arriveFlow;
	}
	public void setArriveFlow(String arriveFlow) {
		this.arriveFlow = arriveFlow;
	}
	public String getSendError() {
		return sendError;
	}
	public void setSendError(String sendError) {
		this.sendError = sendError;
	}
	public String getArriveError() {
		return arriveError;
	}
	public void setArriveError(String arriveError) {
		this.arriveError = arriveError;
	}
	public String getSendLose() {
		return sendLose;
	}
	public void setSendLose(String sendLose) {
		this.sendLose = sendLose;
	}
	public String getArriveLose() {
		return arriveLose;
	}
	public void setArriveLose(String arriveLose) {
		this.arriveLose = arriveLose;
	}
	
    
}
