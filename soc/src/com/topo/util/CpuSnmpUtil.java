package com.topo.util;

import java.io.IOException;
import java.util.List;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.PDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableUtils;

public class CpuSnmpUtil {

	/**
	 * @param args
	 */
	public static String getCpu(String ip,String snmpCommunityName) {
		Snmp snmp = null;
		TransportMapping transport = null;
		CommunityTarget target = null;
		String[] oids = {"1.3.6.1.2.1.25.3.3.1.2"};
		String cpu="";
		try{
			transport = new DefaultUdpTransportMapping();
			snmp = new Snmp(transport);
			snmp.listen();
			
			target = new CommunityTarget();
			target.setCommunity(new OctetString(snmpCommunityName));
			target.setRetries(2);
			target.setAddress(GenericAddress.parse("udp:"+ip+"/161"));
			target.setTimeout(8000);
			target.setVersion(SnmpConstants.version2c);
			
			TableUtils tableUtils = new TableUtils(snmp, new PDUFactory() {
				@Override
				public PDU createPDU(Target arg0) {
					PDU request = new PDU();
					request.setType(PDU.GET);
					return request;
				}
			});
			OID[] columns = new OID[oids.length];
			for (int i = 0; i < oids.length; i++)
				columns[i] = new OID(oids[i]);
			
			//CPU可能是多核的
			List<TableEvent> list = tableUtils.getTable(target, columns, null, null);
			if(list.size()==1 && list.get(0).getColumns()==null){
				//System.out.println("list is null");
				cpu+="连接暂时中断，请重试。";
			}else{
				int percentage = 0;
				for(TableEvent event: list){
					VariableBinding[] values = event.getColumns();
					//System.out.println(values[0]);
					
					if(values != null) 
					    percentage += getIntValue(values[0]);
					cpu+="使用值："+getIntValue(values[0]);
					cpu+="\n";
				}
				cpu+="CPU利用率："+percentage/list.size()+"%";
				
			//	System.out.println("CPU利用率："+percentage/list.size());
			}
			return cpu;	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(transport!=null)
					transport.close();
				if(snmp!=null)
					snmp.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return cpu;
	}
	
	public static int getIntValue(VariableBinding vb){
		if(vb == null || vb.getVariable() instanceof Integer32 == false)
			return 0;
		return ((Integer32)vb.getVariable()).getValue();
	}

	public static void main(String[] args) {
	String s=	getCpu("192.168.20.119","public");
	System.out.println(s);
	}

}
