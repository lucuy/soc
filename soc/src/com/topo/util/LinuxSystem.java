package com.topo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
/**
 * 得到linux系统下的CPU，内存，进程，硬盘的使用情况
 * @author pxm
 *
 */
public class LinuxSystem {

		public static String message() {
			
			Double totalUsedCpuRate=0.0;
			Double totalUsedMemRate=0.0;
			Double totalUsedMem=0.0;
			Double totalMem=0.0;
			Double totalFreeMem=0.0;
			Double totalBufferMem = 0.0;
			String totalTask="";
			String runningTask="";
			String sleepingTask="";
			Double totalDesk=0.0;
			Double totalUsedDeskRate=0.0;
			StringBuffer ssb=new StringBuffer();
			Runtime rt=Runtime.getRuntime();
			Process p;
			try {
				p = rt.exec("top -b -n 1");
			
			BufferedReader in=null;
			in=new BufferedReader(new InputStreamReader(p.getInputStream()));
			String str="";
			int linecount=0;
			while((str=in.readLine())!=null){
				linecount++;
				if (linecount ==2){
					 String[] s = str.split("\\s+");
					 totalTask = s[1];
					 runningTask = s[3];
					 sleepingTask = s[5];
				}
				if (linecount ==3) {
					 String[] s = str.split("\\s+");
					 String[] cpustr = s[1].split("%");
					 totalUsedCpuRate=Double.parseDouble(cpustr[0]);
				}
				if (linecount ==4) {
					 String[] s = str.split("\\s+");
					 String[] memstr={s[1].substring(0,s[1].length()-1),s[3].substring(0,s[3].length()-1),s[5].substring(0,s[5].length()-1),s[7].substring(0,s[7].length()-1)};
					 totalMem = Double.parseDouble(memstr[0]);
					 totalUsedMem = Double.parseDouble(memstr[1]);
					 totalFreeMem = Double.parseDouble(memstr[2]);
					 totalBufferMem = Double.parseDouble(memstr[3]);
					 totalUsedMemRate=(Double.parseDouble(memstr[1])/Double.parseDouble(memstr[0]))*100;
			   }
			}
			
			Process p1=rt.exec("df -lh");
			BufferedReader in1=null;
			in1=new BufferedReader(new InputStreamReader(p1.getInputStream()));
			String str1="";
			int linecount1=0;
			StringBuffer sb=new StringBuffer();
			while((str1=in1.readLine())!=null){
				linecount1++;
				if(linecount1!=1){
					 String[] s = str1.split("\\s+");
					 sb.append(s[0]+"\t");
					/* sb.append(s[1]+"\t");
					 sb.append(s[2]+"\t");
					 sb.append(s[3]+"\t");*/
					 sb.append(s[4]+"\t");
					 //sb.append("\n");换行
					 
				}
			}
			DecimalFormat df=new DecimalFormat("#0.00");
			if(totalUsedCpuRate>90 || totalUsedMemRate>90){
				ssb.append("   所有启动的进程数："+totalTask+"  正在运行的进程数："+runningTask);
				ssb.append("   CPU使用情况："+totalUsedCpuRate+"%");
				ssb.append("   内存使用率："+df.format(totalUsedMemRate)+"%"+"   总内存："+totalMem+"   内存使用："+totalUsedMem);
				ssb.append("   硬盘使用情况："+sb.toString());
				return ssb.toString();
			}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "";
		}
		
		
}
