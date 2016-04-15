package com.soc.webapp.thread;



import javax.servlet.ServletContextEvent;

import net.sf.ehcache.CacheManager;
import global.GlobalThreadPool;

import com.soc.model.conf.GlobalConfig;
import com.soc.webapp.listener.CommunicationContextListener;

/**
 * 
 * <处理接收到的信息的队列的类>
 * <功能详细描述>
 * 
 * @author  Tiny
 * @version  [版本号, 2012-12-3]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class QueueHandleThread
{
    /**
     * <处理接收到的信息的队列的方法>
     * <功能详细描述>
     * @param cacheManager
     * @param servletContextEvent
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Runnable handleThread(final CacheManager cacheManager,
        final ServletContextEvent servletContextEvent)
    {
        return new Runnable()
        {
            public void run()
            {
                while(true)
                {
                	if(GlobalConfig.lienceFlag!=0)
                	{
                    if(!GlobalConfig.logQueue.isEmpty())
                    {
                        if(CommunicationContextListener.threadPoolId <= GlobalConfig.MAX_THREADPOOL_NUMBER) { 
                            // 启动数据处理线程
                            GlobalThreadPool.pool.execute(DataProcessingCenterThread.createThread(CommunicationContextListener.threadPoolId++, GlobalConfig.logQueue.poll(), cacheManager, servletContextEvent));
                        } else {
                            // 超过最大线程不予处理
                        }
                        
                        CommunicationContextListener.threadPoolId--;
                        
                        /*DataProcessingCenterThread dpc = new DataProcessingCenterThread(GlobalConfig.logQueue.poll(), cacheManager, servletContextEvent);
                        ForkJoinPool fjpool = new ForkJoinPool();
                        fjpool.submit(dpc);
                        fjpool.shutdown();
                        try {
                            fjpool.awaitTermination(1, TimeUnit.MILLISECONDS);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }*/
                        //System.out.println("处理第【" + GlobalConfig.handleCount + "】条");
                        GlobalConfig.handleCount++;
                    }
                    
                	}
                    try {
                    	
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            }
        };
    }
}
