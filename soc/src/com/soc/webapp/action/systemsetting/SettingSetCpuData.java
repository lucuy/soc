package com.soc.webapp.action.systemsetting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.record.PageBreakRecord.Break;

import com.soc.model.conf.GlobalConfig;
import com.soc.model.systemsetting.SysInfo;
import com.soc.service.systemsetting.impl.SystemInfoService;
import com.soc.webapp.action.BaseAction;
import com.util.OSUtil;
import com.util.SystemInfoHandle;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  yinhaiping
 * @version  [版本号, 2012-12-3]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SettingSetCpuData extends BaseAction
{
    private static final long serialVersionUID = 1L;
    
    public void CpuData() throws IOException, InterruptedException
    {
        if (OSUtil.getOSName().toLowerCase().contains("linux"))
        {
            if (GlobalConfig.sysInfoList.size() >= 9)
            {
                GlobalConfig.sysInfoList.remove(0);
            }
            GlobalConfig.sysInfoList.add(SystemInfoService.getSysInfo());
            GlobalConfig.sysInfoList.add(SystemInfoService.getSysInfo());
            GlobalConfig.sysInfoList.add(SystemInfoService.getSysInfo());
            GlobalConfig.sysInfoList.add(SystemInfoService.getSysInfo());
            GlobalConfig.sysInfoList.add(SystemInfoService.getSysInfo());
            GlobalConfig.sysInfoList.add(SystemInfoService.getSysInfo());
            GlobalConfig.sysInfoList.add(SystemInfoService.getSysInfo());
            GlobalConfig.sysInfoList.add(SystemInfoService.getSysInfo());
            GlobalConfig.sysInfoList.add(SystemInfoService.getSysInfo());
            GlobalConfig.sysInfoList.add(SystemInfoService.getSysInfo());
            
          //netWork
      /*      int networkSize = SystemInfoHandle.NetworkTraffic().size()/3;
            int nu = 1;
            if(GlobalConfig.networkList.size() >= 20){
            	GlobalConfig.networkList.remove(0);
            }
            while(nu<=21){
            switch(networkSize){
            	case 1:
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(0));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(1));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(2));
            		break;
            	case 2:
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(0));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(1));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(2));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(3));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(4));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(5));
            		break;
            	case 3:
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(0));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(1));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(2));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(3));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(4));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(5));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(6));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(7));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(8));
            		break;
            	case 4:
            		nw.setNetworkName(SystemInfoHandle.NetworkTraffic().get(0));
            		nw.setNetworkReceive(SystemInfoHandle.NetworkTraffic().get(1));
            		nw.setNetworkTransmit(SystemInfoHandle.NetworkTraffic().get(2));
            		nw.setNetworkName(SystemInfoHandle.NetworkTraffic().get(3));
            		nw.setNetworkReceive(SystemInfoHandle.NetworkTraffic().get(4));
            		nw.setNetworkTransmit(SystemInfoHandle.NetworkTraffic().get(5));
            		nw.setNetworkName(SystemInfoHandle.NetworkTraffic().get(6));
            		nw.setNetworkReceive(SystemInfoHandle.NetworkTraffic().get(7));
            		nw.setNetworkTransmit(SystemInfoHandle.NetworkTraffic().get(8));
            		nw.setNetworkName(SystemInfoHandle.NetworkTraffic().get(9));
            		nw.setNetworkReceive(SystemInfoHandle.NetworkTraffic().get(10));
            		nw.setNetworkTransmit(SystemInfoHandle.NetworkTraffic().get(11));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(0));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(1));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(2));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(3));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(4));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(5));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(6));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(7));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(8));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(9));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(10));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(11));
            		break;
            	case 5:
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(0));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(1));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(2));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(3));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(4));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(5));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(6));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(7));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(8));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(9));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(10));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(11));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(12));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(13));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(14));
            		break;
            	case 6:
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(0));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(1));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(2));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(3));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(4));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(5));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(6));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(7));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(8));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(9));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(10));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(11));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(12));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(13));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(14));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(15));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(16));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(17));
            		break;
            	case 7:
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(0));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(1));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(2));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(3));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(4));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(5));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(6));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(7));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(8));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(9));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(10));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(11));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(12));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(13));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(14));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(15));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(16));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(17));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(18));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(19));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(20));
            		break;
            	case 8:
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(0));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(1));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(2));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(3));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(4));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(5));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(6));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(7));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(8));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(9));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(10));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(11));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(12));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(13));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(14));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(15));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(16));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(17));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(18));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(19));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(20));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(21));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(22));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(23));
            		break;
            	case 9:
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(0));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(1));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(2));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(3));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(4));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(5));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(6));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(7));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(8));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(9));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(10));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(11));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(12));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(13));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(14));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(15));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(16));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(17));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(18));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(19));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(20));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(21));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(22));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(23));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(24));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(25));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(26));
            		break;
            	case 10:
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(0));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(1));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(2));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(3));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(4));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(5));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(6));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(7));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(8));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(9));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(10));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(11));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(12));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(13));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(14));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(15));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(16));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(17));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(18));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(19));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(20));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(21));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(22));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(23));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(24));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(25));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(26));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(27));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(28));
            		GlobalConfig.networkList.add(SystemInfoHandle.NetworkTraffic().get(29));
            		break;
            }
            ++nu;
        }*/
        }
        else
        {
//            //System.out.println("请部署到Linux服务器上");
        }
    }
}