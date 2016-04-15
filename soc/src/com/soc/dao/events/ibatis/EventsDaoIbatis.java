package com.soc.dao.events.ibatis;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.push.services.SendMessage;
import com.soc.dao.events.EventsDao;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.events.Events;

/**
 * 事件Dao实现类
 * @author 王纯
 *
 */
public class EventsDaoIbatis extends BaseDaoIbatis implements EventsDao
{
	//数据推送类
  	private SendMessage msg;
    @Override
    public long insertEvents(ConcurrentLinkedQueue<Events> eventsQueue,String userid)
    {
        /*long start = new Date().getTime();
        int length = eventsQueue.size();
        
        // 注意使用同一个SqlMapClient会话  
        SqlMapClient sqlMapClient = this.getSqlMapClient();
        try
        {
            // 开始事务  
            sqlMapClient.startTransaction();
            
            // 开始批处理  
            sqlMapClient.startBatch();
            
            while (!eventsQueue.isEmpty())
            {
                // 插入操作  
                sqlMapClient.insert("insert.events", eventsQueue.poll());
            }
            
            // 执行批处理  
            sqlMapClient.executeBatch();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                // 结束事务  
                sqlMapClient.endTransaction();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        
        //System.out.println("系统信息:   日志事件队列中的元素个数【" + length + "】执行了" + (new Date().getTime() - start) + "ms");*/
    	Events events = eventsQueue.poll();
    	//System.out.println(events.getTableName());
    	 
      this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"insert.events", events);

       try{
    	   String msgs=this.ceshi(events);
       	//实时监控用
       	msg.sendMessageAuto1("admin",msgs);
       }catch (Exception e) {
		//System.out.println(GlobalConfig.sqlId+"this a error");
	}
        return 0;
    }
    
    @Override
    public void insertOriginLog(ConcurrentLinkedQueue<Map<String, Object>> rawLogQueue)
    {
        long start = new Date().getTime();
        int length = rawLogQueue.size();
        
        // 注意使用同一个SqlMapClient会话  
        SqlMapClient sqlMapClient = this.getSqlMapClient();
        try
        {
            // 开始事务  
            sqlMapClient.startTransaction();
            
            // 开始批处理  
            sqlMapClient.startBatch();
            
            while (!rawLogQueue.isEmpty())
            {
                // 插入操作  
                sqlMapClient.insert(GlobalConfig.sqlId+"insert.originLog", rawLogQueue.poll());
            }
            
            // 执行批处理  
            sqlMapClient.executeBatch();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                // 结束事务  
                sqlMapClient.endTransaction();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        
        //System.out.println("系统信息:   原始日志队列中的元素个数【" + length + "】执行了" + (new Date().getTime() - start) + "ms");
    }
    
    @Override
    public Events queryEvents(Map<String, Object> map)
    {
        return (Events)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"query.events", map);
    }
    
    /**
     * {@inheritDoc}
     */
    public int existsTable(String tableName)
    {
        return (Integer)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"exists.log", tableName);
    }
    
    /**
     * {@inheritDoc}
     */
    public String existsSeq(String seqName)
    {
        return (String)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"exists.seq", seqName);
    }
    
    /**
     * {@inheritDoc}
     */
    public void createLogSeq(String seqName)
    {
        this.create(GlobalConfig.sqlId+"create.seq", seqName);
    }
    
    /**
     * {@inheritDoc}
     */
    public void createLogTable(Map<String, String> map)
    {
        this.create(GlobalConfig.sqlId+"create.log", map);
    }
    
    /**
     * {@inheritDoc}
     */
    public void createNotAnalyticEventsTable(Map<String, String> map)
    {
        this.create(GlobalConfig.sqlId+"create.notAnalyticEvent", map);
    }
    @Override
    public List<Map<String, Object>> querProtocol()
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.protocol");
    }

    
    /** {@inheritDoc} */
    
    @Override
    public List<Map> queryMointorEvents(Map map)
    {
        // TODO Auto-generated method stub
        return super.queryForList(GlobalConfig.sqlId+"queryMonitor.events", map);
    }
    
    /** {@inheritDoc} */
    
    @Override
    public void insertMonitorFtpEvents(List<Map> list)
    {
        SqlMapClient sqlMapClient = this.getSqlMapClient();
        try
        {
            sqlMapClient.startBatch();
            for (int i = 0; i < list.size(); i++)
            {
                sqlMapClient.insert(GlobalConfig.sqlId+"insert.monitorFtp", list.get(i));
            }
            // 执行批处理  
            sqlMapClient.executeBatch();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO Auto-generated method stub
        finally
        {
            try
            {
                // 结束事务  
                sqlMapClient.endTransaction();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    /** {@inheritDoc} */
     
    @Override
    public void insertMonitorTelnetEvents(List<Map> list)
    {
        // TODO Auto-generated method stub
        SqlMapClient sqlMapClient = this.getSqlMapClient();
        try
        {
            sqlMapClient.startBatch();
            for (int i = 0; i < list.size(); i++)
            {
                sqlMapClient.insert(GlobalConfig.sqlId+"insert.monitorTelnet", list.get(i));
            }
            // 执行批处理  
            sqlMapClient.executeBatch();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO Auto-generated method stub
        finally
        {
            try
            {
                // 结束事务  
                sqlMapClient.endTransaction();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    /** {@inheritDoc} */
     
    @Override
    public void insertMonitorConnectRefuse(List<Map> list)
    {
        // TODO Auto-generated method stub
        SqlMapClient sqlMapClient = this.getSqlMapClient();
        try
        {
            sqlMapClient.startBatch();
            for (int i = 0; i < list.size(); i++)
            {
                sqlMapClient.insert(GlobalConfig.sqlId+"insert.monitorConnectRefuse", list.get(i));
            }
            // 执行批处理  
            sqlMapClient.executeBatch();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO Auto-generated method stub
        finally
        {
            try
            {
                // 结束事务  
                sqlMapClient.endTransaction();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    /** {@inheritDoc} */
     
    @Override
    public void insertMonitorAccountLocked(List<Map> list)
    {
        // TODO Auto-generated method stub
        SqlMapClient sqlMapClient = this.getSqlMapClient();
        try
        {
            sqlMapClient.startBatch();
            for (int i = 0; i < list.size(); i++)
            {
                sqlMapClient.insert(GlobalConfig.sqlId+"insert.monitorAccountLocked", list.get(i));
            }
            // 执行批处理  
            sqlMapClient.executeBatch();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO Auto-generated method stub
        finally
        {
            try
            {
                // 结束事务  
                sqlMapClient.endTransaction();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    /** {@inheritDoc} */
     
    @Override
    public void insertMonitorAccountLogin(List<Map> list)
    {
        // TODO Auto-generated method stub
        SqlMapClient sqlMapClient = this.getSqlMapClient();
        try
        {
            sqlMapClient.startBatch();
            for (int i = 0; i < list.size(); i++)
            {
                sqlMapClient.insert(GlobalConfig.sqlId+"insert.monitorAccountLogin", list.get(i));
            }
            // 执行批处理  
            sqlMapClient.executeBatch();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO Auto-generated method stub
        finally
        {
            try
            {
                // 结束事务  
                sqlMapClient.endTransaction();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    /** {@inheritDoc} */
     
    @Override
    public void insertMonitorAccountManage(List<Map> list)
    {
        // TODO Auto-generated method stub
        // TODO Auto-generated method stub
        SqlMapClient sqlMapClient = this.getSqlMapClient();
        try
        {
            sqlMapClient.startBatch();
            for (int i = 0; i < list.size(); i++)
            {
                sqlMapClient.insert(GlobalConfig.sqlId+"insert.monitorAccountManage", list.get(i));
            }
            // 执行批处理  
            sqlMapClient.executeBatch();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO Auto-generated method stub
        finally
        {
            try
            {
                // 结束事务  
                sqlMapClient.endTransaction();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    /** {@inheritDoc} */
     
    @Override
    public void insertMonitorPrivilge(List<Map> list)
    {
        // TODO Auto-generated method stub
        SqlMapClient sqlMapClient = this.getSqlMapClient();
        try
        {
            sqlMapClient.startBatch();
            for (int i = 0; i < list.size(); i++)
            {
                sqlMapClient.insert(GlobalConfig.sqlId+"insert.monitorPrivilege", list.get(i));
            }
            // 执行批处理  
            sqlMapClient.executeBatch();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO Auto-generated method stub
        finally
        {
            try
            {
                // 结束事务  
                sqlMapClient.endTransaction();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    /** {@inheritDoc} */
     
    @Override
    public int countRecentScreenEvents(Map map)
    {
        // TODO Auto-generated method stub
        Object ob = null;
        //根据map中存储的条件查询符合条件的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+"query.recentLogsCount", map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        //总条数
        int totalRows = 0;
        
        if (ob != null)
        {
            totalRows = ((Integer)ob).intValue();
        }
        return totalRows;
    }

    /** {@inheritDoc} */
     
    @Override
    public List<Events> queryRecentScreenEvents(Map map, int startRow, int pageSize)
    {
        // TODO Auto-generated method stub
        
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.recentLogs", map, startRow, pageSize);
    }
    @Override
	public List<Map> queryTodayEventsByPriority(Map map) {
		// TODO Auto-generated method stub
		return super.queryForList(GlobalConfig.sqlId+"homepage.queryEvents",map);
	}

	@Override
	public List<Map> getEventsStatistics(Map map) {
		// TODO Auto-generated method stub
		return super.queryForList(GlobalConfig.sqlId+"home.eventsStatistics",map);
	}
	/*
	 * 拼接表格
	 */
	public String ceshi(Events eve){
		String name =eve.getTableName().split("_")[1];
		
		 StringBuffer sb =new StringBuffer();
		 Integer in = null;
		 try {
        	in= Integer.parseInt(eve.getType());
		} catch (Exception e) {
			//log.info("事件类型转化失败 原因:事件类型原本是汉字无需转化");
		
		}
		 sb.append("<tr class=\"back\" id=\"eventsTr_\"")
		 .append("onmousemove=\"this.className='hand'\" onmouseout=\"this.className='back'\"" +
				 "ondblclick=\"dblclick('"+eve.getIdentification()+"','"+in+"','"+name+"','"+eve.getType()+"');"+"\">")
		 ;
//		 sb.append("<td></td>");
		 sb.append("<td valign=\"middle\" align=\"center\">  <div class=\"level\">");
			if (eve.getPriority() <= 1) {
				sb.append(
						"<div class=\"levelBa\" style=\"background-color#CCCCCC; width:")
						.append(eve.getPriority() * 8)
						.append("px;\"></div><span style=\"position:relative;left:0px;top:-20px;\">")
						.append(eve.getPriority()).append("</span>");
			}
			if (eve.getPriority() > 1&& eve.getPriority()<=3) {
				sb.append(
						"<div class=\"levelBa\" style=\"background-color:#ffcc33; width:")
						.append(eve.getPriority() * 8)
						.append("px;\"></div><span style=\"position:relative;left:0px;top:-20px;\">")
						.append(eve.getPriority()).append("</span>");
			}
			if (eve.getPriority() >= 4) {
				sb.append(
						"<div class=\"levelBa\" style=\"background-color:#ff3333; width:")
						.append(eve.getPriority() * 8)
						.append("px;\"></div><span style=\"position:relative;left:0px;top:-20px;\">")
						.append(eve.getPriority()).append("</span>");
			}
			sb.append("</div></td>");
			//修改事件名称为数字的地方，在监控管理处显示为数字的问题。
			String nameTemp=null;
			try{
				nameTemp=GlobalConfig.eventTypeTag.get(Long.parseLong(eve.getName()));
			}catch(Exception e)
			{
				nameTemp= eve.getName();
			}
			
			sb.append("<td valign=\"middle\" align=\"center\">").append(nameTemp).append("</td>");
			String type = null;
			try{
				type =GlobalConfig.eventTypeTag.get(Long.parseLong(eve.getType()));
			
				
			}catch (Exception e) {
				type =eve.getType();
			}
			sb.append("<td valign=\"middle\" align=\"center\">").append(type).append("</td>");
			
			sb.append("<td valign=\"middle\" align=\"center\">").append(eve.getsAdd()).append("</td>");
			sb.append("<td valign=\"middle\" align=\"center\">").append(eve.getsPort()).append("</td>");
			sb.append("<td valign=\"middle\" align=\"center\">").append(eve.gettAdd()).append("</td>");
			sb.append("<td valign=\"middle\" align=\"center\">").append(eve.getdPort()).append("</td>");
			sb.append("<td valign=\"middle\" align=\"center\">").append(eve.getAggregatedCount()).append("</td>");
			sb.append("<td valign=\"middle\" align=\"center\">").append(eve.getReceptTimes()).append("</td></tr>");
			return sb.toString();
	}

	public SendMessage getMsg() {
		return msg;
	}

	public void setMsg(SendMessage msg) {
		this.msg = msg;
	}
	@Override
	public void insertEventsDrools(Events e){
		super.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"relevanceEvents.insert", e);

	}

	@Override
	public int exsitsqlServerTable(String tableName) {
		// TODO Auto-generated method stub
		  Object ob = null;
	        
	        // 根据map中存储的条件查询符合条件的用户的记录数
	        try
	        {
	            ob = super.queryForObject(GlobalConfig.sqlId+"exists.log", tableName);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        
	        //总条数
	        int totalRows = 0;
	        
	        if (ob != null)
	        {
	            totalRows = ((Integer)ob).intValue();
	        }
	        
	        return totalRows;
	}

	@Override
	public int queryEventsTrendAnalysis(Map map) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"Events.trendAnalysis",map);
	}

	@Override
	public List<Map<String, Object>> queryEventByCategory(Map map) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"Query.EventByCategory",map);
	}

	@Override
	public List<Map<String, Object>> queryEventByIP(Map map) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"Query.EventByIP",map);
	}

	@Override
	public void deleteTable(String tableName) {
		try {
			this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"Delete.table", tableName);
		} catch (Exception e) {
			System.err.println("删除的表："+tableName+"不存在！");
		}
		
	}

	@Override
	public void deleteSequence(String sequence) {
try {
	this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"Delete.sequence", sequence);
			
		} catch (Exception e) {
			System.err.println("删除的序列："+sequence+"不存在！");
		}
		
	}

}
