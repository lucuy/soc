package com.topo.util;
import java.io.IOException;
import java.text.DecimalFormat;
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
public class MemorySnmpUtil {

	public static String getMemory(String ip,String snmpCommunityName) {
		Snmp snmp = null;
		TransportMapping transport = null;
		CommunityTarget target = null;
		String[] oids = {"1.3.6.1.2.1.25.2.3.1.2",  //type
			     "1.3.6.1.2.1.25.2.3.1.3",  //descr
			     "1.3.6.1.2.1.25.2.3.1.4",  //unit
			     "1.3.6.1.2.1.25.2.3.1.5",  //size
			     "1.3.6.1.2.1.25.2.3.1.6"}; //used 
		String memory="";
		try{
			transport = new DefaultUdpTransportMapping();
			snmp = new Snmp(transport);
			snmp.listen();
			
			target = new CommunityTarget();
			target.setCommunity(new OctetString(snmpCommunityName));
			target.setRetries(2);
			target.setAddress(GenericAddress.parse("udp:"+ip+"/161"));
			target.setTimeout(80);
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
			
			List<TableEvent> list = tableUtils.getTable(target, columns, null, null);
			if(list.size()==1 && list.get(0).getColumns()==null){
			//	System.out.println("list is null");
				memory+="连接暂时中断，请重试。";
			}else{
				int percentage = 0;
				for(TableEvent event: list){
					VariableBinding[] values = event.getColumns();
				System.out.println(values[2]);	
					if(values!=null){
						String oid = getValue(values[0]);
						if ("1.3.6.1.2.1.25.2.1.2".equals(oid)){
							float[] temp = compute(values);
							memory+="占用内存："+temp[0]+",比例："+temp[1]+"%";
							memory+="\n";
						//	System.out.println("占用内存："+temp[0]+",比例："+temp[1]);
						}else if("1.3.6.1.2.1.25.2.1.3".equals(oid)){
							float[] temp = compute(values);
							memory+="占用虚拟内存："+temp[0]+",比例："+temp[1]+"%";
							memory+="\n";
							//System.out.println("占用虚拟内存："+temp[0]+",比例："+temp[1]);
						}
					}
				}
			}
				return memory;
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
		return memory;
	}
	
	public static String getValue(VariableBinding vb){
		if(vb == null || vb.getVariable() == null)
			return null;		
		return getRidQuote(vb.getVariable().toString());
	}

	public static String getRidQuote(String value){
		if(value != null && value.indexOf("'") > 0)
			return value.replace("'","_");
		return value;		
	}
	
	public static int getIntValue(VariableBinding vb){
		if(vb == null || vb.getVariable() instanceof Integer32 == false)
			return 0;
		return ((Integer32)vb.getVariable()).getValue();
	}
	
	private static float[] compute(VariableBinding[] values){
		int unit = getIntValue(values[2]);
		int totalSize = getIntValue(values[3]);
		int usedSize = getIntValue(values[4]);		
		float value = formatFloat((long)usedSize * unit, 1024 * 1024);
		float percentage = formatFloat((long)usedSize * 100, totalSize);				

		return new float[]{value,percentage};
    }
	
	public static final DecimalFormat floatFormatter = new DecimalFormat("#.00");
	
	public static float formatFloat(double val1, double val2) {
		return val2 == 0 ? 0 : Float.parseFloat(floatFormatter.format(val1 / val2));
	}

public static void main(String[] args) {
String s=	getMemory("192.168.20.119","public");
System.out.println(s);
}
}
