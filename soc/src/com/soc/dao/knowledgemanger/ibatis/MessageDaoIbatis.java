package com.soc.dao.knowledgemanger.ibatis;

import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.knowledgemanger.MessageDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.knowledge.MessageCenter;

/**
 * 消息中心Dao类的实现	 
 * @author lichen
 * @version [版本号, 2013-6-25]
 */
public class MessageDaoIbatis extends BaseDaoIbatis implements MessageDao{

	public List<MessageCenter> queryMessageCenters(Map map, int startIndex, int pageSize) {
		return getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"message.query",map,startIndex,pageSize);
	}

	public MessageCenter queryMessageCenterById(long id) {
		return (MessageCenter) getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"message.queryMessage",id);
	}

	public int queryMessageCenterCount(Map map) {
		Object ob = null;
        
        // 根据map中存储的条件查询符合条件的用户的记录数
        try
        {
            ob = getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"message.count",map);
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

	public void updMessageStatus(Map map) {
		getSqlMapClientTemplate().update(GlobalConfig.sqlId+"message.updateMessageStatus",map);
	}

	public void delMessageById(long id) {
		getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"message.delMessage",id);
	}

	public long insertMessage(MessageCenter message) {
		long no = (Long) create(GlobalConfig.sqlId+"message.insert", message);
		return no;		
		
	}

	public void updateMessage(Map map) {
		getSqlMapClientTemplate().update(GlobalConfig.sqlId+"message.updateMessage",map);
	}

	public void updateMessageRead(Map map) {
		getSqlMapClientTemplate().update(GlobalConfig.sqlId+"message.updateMessageRead",map);
	}

	
}
