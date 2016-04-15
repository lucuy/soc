package com.compliance.service.rank;


import java.util.List;
import java.util.Map;
import com.compliance.model.rank.Record;
import com.util.page.Page;
import com.util.page.SearchResult;
/**
 * 备案业务接口
 * @author quyongkun
 *
 */
public interface RecordService {
	/**
	 * 查询条数
	 * @param mapper
	 * @return
	 */
	int querycount(Map mapper);
	
	/**
	 * 查询备案
	 * @param map
	 * @param page
	 * @return
	 */
	SearchResult query(Map map, Page page);

	
	/**
	 * 
	 * 根据备案编号查询
	 * @param recordId 备案编号
	 * @return 备案信息
	 */
	Record queryById(int recordId);
	
	/**
	 * 
	 * 根据系统编号查询
	 * @param sysInFoId 系统编号
	 * @return 备案集合
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
	boolean insert(Record record) ;
	/**
	 * 高级搜索查询条数
	 * @param mapper
	 * @return
	 */
	int precisequerycount(Map mapper);
	/**
	 * 高级搜索备案信息
	 * @param map
	 * @param page
	 * @return
	 */
	SearchResult precisequery(Map map, Page page);
}
