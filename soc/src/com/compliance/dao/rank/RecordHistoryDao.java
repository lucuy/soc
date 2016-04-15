package com.compliance.dao.rank;


import java.util.List;
import java.util.Map;
import com.compliance.model.rank.RecordHistory;
/**
 * 备案历史dao接口
 * @author quyongkun
 *
 */
public interface RecordHistoryDao {

	/**
	 * 查询备案历史条数
	 * @param mapper map集合
	 * @return 备案历史条数
	 */
	 int querycount(Map mapper);
	
	/**
	 * 查询备案历史信息
	 * @param map map集合
	 * @param startRow 起始行号
	 * @param pageSize 页面大小
	 * @return 备案历史信息集合
	 */
	 List<RecordHistory> query(Map map, int startRow, int pageSize);
	
	
	/**
	 * 根据id查询
	 */
	 RecordHistory queryById(int recordHistoryId);
	
	/**
	 * 根据系统编号查询
	 */
	 List<RecordHistory> queryBySysId(String sysInFoId);
	 
	 /**
	  * 修改备案历史
	  * @param record 备案历史实体
	  * @return 修改是否成功
	  */
	 boolean update(RecordHistory recordHistory);
	 
	 /**
	  * 添加备案历史
	  * @param record 备案历史实体
	  * @return 添加是否成功
	  */
	 boolean insert(RecordHistory recordHistory);
	 /**
		 * 高级搜索备案历史条数
		 * @param mapper map集合
		 * @return 备案历史条数
		 */
	int precisequerycount(Map mapper);
	/**
	 * 高级搜索备案历史信息
	 * @param map map集合
	 * @param startRow 起始行号
	 * @param pageSize 页面大小
	 * @return 备案历史信息集合
	 */
	 List<RecordHistory> precisequery(Map map, int startRow, int pageSize);
	
}
