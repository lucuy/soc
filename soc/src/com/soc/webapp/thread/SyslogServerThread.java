package com.soc.webapp.thread;

import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContextEvent;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.productivity.java.syslog4j.server.SyslogServerConfigIF;
import org.productivity.java.syslog4j.server.SyslogServerEventIF;
import org.productivity.java.syslog4j.server.SyslogServerIF;
import org.productivity.java.syslog4j.server.SyslogServerSessionEventHandlerIF;

import com.soc.model.conf.GlobalConfig;
import com.util.StringUtil;

public class SyslogServerThread implements Runnable, SyslogServerSessionEventHandlerIF
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    
    private transient Log Log = LogFactory.getLog(getClass());
    
    // 接收数据报包的套接字
    public static SyslogServerIF syslogServerIF;
    
    // syslog服务器协议
    public static String syslogServerProtocol;
    
    // 存储cache的容器
    public static CacheManager cacheManager;
    
    // 
    public static ServletContextEvent servletContextEvent;
    
    public static Cache cache = null;
    
    public static int count = 0;
    
    public static long startCountTime;
    
    public static List<String> list;
    
    /**
     * <默认构造函数>
     */
    public SyslogServerThread() {
        
    }
    
    /**
     * <默认构造函数>
     */
    @SuppressWarnings("static-access")
	public SyslogServerThread(SyslogServerIF syslogServerIF, String syslogServerProtocol, CacheManager cacheManager, ServletContextEvent servletContextEvent) {
        
        this.syslogServerIF = syslogServerIF;
        this.syslogServerProtocol = syslogServerProtocol;
        this.cacheManager = GlobalConfig.cacheManager;
        this.servletContextEvent = servletContextEvent;
        
        // 创建cache配置对象
        /*CacheConfiguration cacheConfig = new CacheConfiguration("events_count", 0);
        
        // 设置cache在内存中的element个数
        cacheConfig.setMaxElementsInMemory(GlobalConfig.CACHE_EVENT_NUM);
        
        // 设置cache在磁盘中的element个数
        cacheConfig.setMaxElementsOnDisk(GlobalConfig.CACHE_EVENT_NUM);
        
        // 设置是否持久化到磁盘
        cacheConfig.overflowToOffHeap(false);
        
        // 
        cacheConfig.memoryStoreEvictionPolicy("FIFO");
        
        // 设置缓存存活时间
        cacheConfig.setTimeToIdleSeconds(0);
        
        // 设置缓存的间隔时间
        cacheConfig.setTimeToLiveSeconds(0);
        
        // 将cache放入容器中
        cacheManager.addCache(new Cache(cacheConfig));
        
        // 获得cache
        cache = cacheManager.getCache("events_count");*/
        
    }

    /**
     * {@inheritDoc}
     */
    public void run()
    {
        Log.info("Monitor Listener Thread start...");
        
        SyslogServerConfigIF syslogServerConfigIF = null;
        if(syslogServerIF != null)
        {
            syslogServerConfigIF = syslogServerIF.getConfig();
        }
        
        if(syslogServerConfigIF != null)
        {
            if(StringUtil.isNotBlank(GlobalConfig.terracePort))
            {
                syslogServerConfigIF.setPort(Integer.parseInt(GlobalConfig.terracePort));
                syslogServerConfigIF.setCharSet("UTF-8");
                SyslogServerThread mlt = new SyslogServerThread();
                syslogServerConfigIF.addEventHandler(mlt);
                syslogServerIF.initialize(syslogServerProtocol, syslogServerConfigIF);
                syslogServerIF.run();
            }
            else
            {
                //System.out.println("未设置Syslog端口");
            }
        }
        
    }
    
    @Override
    public void initialize(SyslogServerIF syslogServer)
    {
        return;
    }

    @Override
    public void destroy(SyslogServerIF syslogServer)
    {
        syslogServer.shutdown();
    }

    @Override
    public Object sessionOpened(SyslogServerIF syslogServer, SocketAddress socketAddress)
    {
        return null;
    }

    @Override
    public void event(Object session, SyslogServerIF syslogServer, SocketAddress socketAddress,
        SyslogServerEventIF event)
    {
       // System.out.println("日志： " + event.getMessage());
        count++;
        
        // 统计每秒接收的事件数
       /* if(count == 0)
        {
            count++;
            startCountTime = new Date().getTime();
        }
        else
        {
            long currentTime = new Date().getTime();
            if((currentTime - startCountTime) >= 60000)
            {
                cache.put(new Element(currentTime/1000, count));
                //System.out.println("系统信息:   Syslog服务器每秒接收的事件数【" + count + "】");
                count = 0;
            }
            else
            {
                count++;
            }
        }*/
        
        
        
        
        //list.add(event.getMessage());
        
        //System.out.println("系统信息:   Syslog服务器接收的事件数【" + count + "】");
        
        //修改底层的日志的收到后的处理
       // GlobalConfig.logQueue.add(list.subList(0, list.size()));
        
        //GlobalConfig.logQueue.add(event.getMessage());
        if (GlobalConfig.diskFalg) {
		
       if(list == null)
        {
            list = new ArrayList<String>();
            list.add(event.getMessage());
            GlobalConfig.firstStoreMessageTime = new Date().getTime();
        }
        else
        {
            if((list.size() >= GlobalConfig.listMaxNum) || ((new Date().getTime() - GlobalConfig.firstStoreMessageTime) >= GlobalConfig.storeMessageInterval))
            {
            	list.add(event.getMessage());
                GlobalConfig.logQueue.add(list.subList(0, list.size()));
                GlobalConfig.logDroolsQueue.addAll(GlobalConfig.logQueue) ;
                list = null;
            }
            else
            {
                list.add(event.getMessage());
                //GlobalConfig.logQueue.add(event.getMessage());
            }
        }
        }
        //GlobalConfig.logQueue.add(event.getMessage());
        /*while(!GlobalConfig.logQueue.isEmpty())
        {
            if(CommunicationContextListener.threadPoolId <= GlobalConfig.MAX_THREADPOOL_NUMBER) { 
                // 启动数据处理线程
                GlobalThreadPool.pool.execute(DataProcessingCenterThread.createThread(CommunicationContextListener.threadPoolId++, GlobalConfig.logQueue.poll(), cacheManager, servletContextEvent));
            } else {
                // 超过最大线程不予处理
                //System.out.println("！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
                Log.info("listening thread " + CommunicationContextListener.threadPoolId + ",a new socket refuse!");
            }
            
            CommunicationContextListener.threadPoolId--;
        }*/
    }

    @Override
    public void exception(Object session, SyslogServerIF syslogServer, SocketAddress socketAddress, Exception exception)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void sessionClosed(Object session, SyslogServerIF syslogServer, SocketAddress socketAddress, boolean timeout)
    {
        // TODO Auto-generated method stub
        
    }

}
