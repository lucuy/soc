package com.compliance.dao.rank;


import java.util.List;
import java.util.Map;
import com.compliance.model.rank.Record;
/**
 * 备案dao接口
 * @author quyongkun
 *
 */
public interface RecordDao {

	/**
	 * 查询备案条数
	 * @param mapper map集合
	 * @return 备案条数
	 */
	 int querycount(Map mapper);
	
	/**
	 * 查询备案信息
	 * @param map map集合
	 * @param startRow 起始行号
	 * @param pageSize 页面大小
	 * @return 备案信息集合
	 */
	 List<Record> query(Map map, int startRow, int pageSize);
	
	
	/**
	 * 根据id查询
	 */
	 Record queryById(int recordId);
	
	/**
	 * 根据系统编号查询
	 */
	 List<Record> queryBySysId(String sysInFoId);
	 
	 /**
	  * 修改备案
	  * @param record 备案实体
	  * @return 修改是否成功
	  */
	 boolean update(Record record);
	 
	 /**
	  * 添加备案
	  * @param record 备案实体
	  * @return 添加是否成功
	  */
	 boolean insert(Record record);
	 /**
	  * 高级搜索数据条数
	  */
	 int precisequerycount(Map mapper);
	 /**
		 * 查询备案信息
		 * @param map map集合
		 * @param startRow 起始行号
		 * @param pageSize 页面大小
		 * @return 备案信息集合
		 */
	List<Record> precisequery(Map map, int startRow, int pageSize);
	
}
