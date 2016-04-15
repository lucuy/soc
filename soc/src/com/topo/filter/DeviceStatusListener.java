package com.topo.filter;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * 设备状态定时器，定时检查设备是否在线
 * @author 裴秀梅
 *
 */
public class DeviceStatusListener implements ServletContextListener {

	private Timer timer=null;
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		timer.cancel();
		System.out.println("计时器被关闭！");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		timer=new Timer();
		System.out.println("计时器已经开启！");
		timer.schedule(new DeviceTask(arg0),  1*1000,120000);
	}

}
