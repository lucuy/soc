package com.soc.dao.knowledgemanger;

import java.util.List;
import java.util.Map;

import com.soc.model.knowledge.MessageCenter;

/**
 * 消息中心Dao类	 
 * @author lichen
 * @version [版本号, 2013-6-25]
 */
public interface MessageDao {

	/**
	 * 消息中心查询功能	
	 * @param map
	 * @param startIndex 
	 * @param pageSize
	 * @return
	 */
	public List<MessageCenter> queryMessageCenters(Map map, int startIndex, int pageSize);
	
	/**
	 * 根据Id查询MessageCenter对象
	 * @param id
	 * @return
	 */
	public MessageCenter queryMessageCenterById(long id);
	
	/**
	 * 获得要查询的消息集合的记录数
	 * @param map
	 * @return
	 */
	public int queryMessageCenterCount(Map map);
	
	/**
	 * 删除 (实际上是做了个修改)
	 * @param id
	 */
	public void updMessageStatus(Map map);
	
	/**
	 * 根据id彻底删除Message
	 * @param id
	 */
	public void delMessageById(long id);
	
	/**
	 * 添加新的消息数据
	 * @param message MessageCenter对象
	 */
	public long insertMessage(MessageCenter message);
	/**
	 * 修改消息
	 * @param message
	 */
	public void updateMessage(Map map);
	
	/**
	 * 修改消息的阅读状态
	 * @param map
	 */
	public void updateMessageRead(Map map);
}
