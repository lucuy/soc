package com.soc.dao.alert.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.soc.dao.alert.AlertMessageDao;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.model.alert.alertMessage.AlertMessage;
import com.soc.model.conf.GlobalConfig;

/**
 * 
 * <告警信息Dao的实现类> <实现告警信息的查看，列表展示，搜索，快速搜索>
 * 
 * @author liuyanxu
 * @version [版本号, 2012-11-4]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class AlertMessageDaoIbatis extends BaseDaoIbatis implements
		AlertMessageDao {

	@Override
	public int count(Map map) {
		// TODO Auto-generated method stub
		Object ob = null;

		try {
			ob = super.queryForObject(GlobalConfig.sqlId+"alertMessage.count", map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 总条数
		int totalRows = 0;

		if (ob != null) {
			totalRows = ((Integer) ob).intValue();
		}

		return totalRows;
	}

	@Override
	public List<AlertMessage> queryAlertMessage(Map map, int startRow,
			int pageSize) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+"alertMessage.query", map, startRow, pageSize);
	}

	@Override
	public void insertAlertMessage(AlertMessage alertMessage) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"alertMessage.insert",
				alertMessage);
	}

	@Override
	public int queryCurrent(Map map) {
		// TODO Auto-generated method stub
		Object ob = null;

		try {
			ob = super.queryForObject(GlobalConfig.sqlId+"alertMessage.current", map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 总条数
		int totalRows = 0;

		if (ob != null) {
			totalRows = ((Integer) ob).intValue();
		}

		return totalRows;
	}

	@Override
	public List<AlertMessage> queryCurrentEvents(long timestamp) {
		// TODO Auto-generated method stub
		return super.queryForList(GlobalConfig.sqlId+"alertMessage.currentEvent", timestamp);
	}

    /** {@inheritDoc} */
     
    @Override
    public List<AlertMessage> queryCurrentMessage(Map map)
    {
        // TODO Auto-generated method stub
        return super.queryForList(GlobalConfig.sqlId+"alertMessage.currentMessage",map);
    }

    /** {@inheritDoc} */
     
    @Override
    public List<Map> queryCurrentMessageByGroup(Map map)
    {
        // TODO Auto-generated method stub
        return super.queryForList(GlobalConfig.sqlId+"alertMessage.groupByRank",map);
    }

    /** {@inheritDoc} */
     
    @Override
    public List<Map> queryCurrentMessageByAsset(Map map)
    {
        // TODO Auto-generated method stub
        return super.queryForList(GlobalConfig.sqlId+"alertMessage.countAsset",map);
    }

	@Override
	public List<Map> quertAlertRuleByID(Map mapRule) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"quertAlertRuleByID.query", mapRule);
	}

	@Override
	public List<Map> exportExcel(Map map) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"alertMessage.exportExcel", map);
	}

	@Override
	public void updateAlertMassage(Map map) {
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"alertMessage.updateAlertMassage", map);
	}

	@Override
	public AlertMessage queryById(Map map) {
		return (AlertMessage) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"alertMessage.queryById",map);
	}
	
	@Override
	 public List<AlertMessage> queryByAssetId(Map map) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"alertMessage.queryByAssetId", map);
	}

	@Override
	public List<Map> queryAepetitionMessage(Map map) {
		
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"alertMessage.repetition",map);
	}
	
	@Override
	public long last7DaysAlarmTypeDistributionCount(Map map) {
		//return  Integer.parseInt((String)getSqlMapClientTemplate().queryForObject("auditReportSQL.report12Table2Count",map));
		Object o = getSqlMapClientTemplate().queryForObject("auditReportSQL.report12Table2Count",map);
		return Long.parseLong(String.valueOf(o));
	}

	@Override
	public List<Map<String, Object>> last7DaysAlarmTypeDistribution(Map map) {
		return  getSqlMapClientTemplate().queryForList("auditReportSQL.report12Table2",map);

	}

	@Override
	public List<AlertMessage> queryAllAlertInfo(Map map) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("pgAdminalertMessage.queryAllAlertInfo",map);
	}

	@Override
	public int queryCountAlert() {
		
		return (Integer)this.getSqlMapClientTemplate().queryForObject("pgAdminalertMessage.queryCountAlert");
	}

	@Override
	public List<AlertMessage> queryAlertMessageByEventName(Map map) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("pgAdminalertMessage.queryAlertMessageByEventName", map);
	}

	@Override
	public List<AlertMessage> queryAlertMessageDoc(Map map) {
	
		return this.getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+"alertMessage.query", map);
	}

	@Override
	public void closeAlertMessageById(Map map) {
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"alertMessage.closeAlertMassage", map);
		
	}

	@Override
	public List<AlertMessage> queryNotCloseAlertMessage(Map map) {
		List<AlertMessage> list =null;
				try{
					list= this.getSqlMapClientTemplate().queryForList(
							GlobalConfig.sqlId+"alertMessage.query", map);
				}catch (Exception e) {
					list = new ArrayList<AlertMessage>();
					e.printStackTrace();
				}
		return list;
	}
}
