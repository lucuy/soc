package com.topo.util;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.soc.service.alert.AlertMessageSerive;
import com.util.StringUtil;

public class MacSnmpUtil {

	
	/**
	 * @param args
	 */
	public static String getMac(String ip,String snmpCommunityName) {
		Snmp snmp = null;
		TransportMapping transport = null;
		CommunityTarget target = null;
		String[] oids = {"1.3.6.1.2.1.2.2.1.6"};
		String mac="";
		String macTem="";
		Set<String> setlist=new HashSet<String>();
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
				mac="连接暂时中断，请重试。";
				setlist.add(mac);
			}else{
				int percentage = 0;
				for(TableEvent event: list){
					VariableBinding[] values = event.getColumns();
					//System.out.println(values[0]);
					
					if("".endsWith(getStringValue(values[0]).trim())){
						
					}else{
						mac=getStringValue(values[0]).trim();
						setlist.add(mac);
					}
					
				}
				
			}
			
			for (String str : setlist) {
				
				macTem+="Mac地址："+str;
				macTem+="\n";
			}		
			return macTem;	
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
		return mac;
	}
	
	public static String getStringValue(VariableBinding vb){
		if(vb == null || vb.getVariable().toString() instanceof String == false)
			return "";
		return vb.getVariable().toString();
	}

	public static void main(String[] args) {
	String s=	getMac("192.168.12.55","socpublic");
	System.out.println(s);
	}

}
