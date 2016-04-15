package com.compliance.service.rank;

import java.util.List;
import java.util.Map;

import com.compliance.model.rank.RecordHistory;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 备案历史业务实现类
 * @author quyongkun
 *
 */
public interface RecordHistoryService {
	
	/**
	 * 查询条数
	 * @param mapper
	 * @return
	 */
	int querycount(Map mapper);
	
	/**
	 * 查询备案历史
	 * @param map
	 * @param page
	 * @return
	 */
	SearchResult query(Map map, Page page);

	
	/**
	 * 
	 * 根据备案历史编号查询
	 * @param recordId 备案历史编号
	 * @return 备案历史信息
	 */
	RecordHistory queryById(int recordHistoryId);
	

	 /**
	  * 添加备案历史
	  * @param record 备案历史实体
	  * @return 添加是否成功
	  */
	boolean insert(RecordHistory recordHistory) ;
	/**
	 * 高级搜索备案历史数据条数
	 * @param mapper
	 * @return
	 */
	int precisequerycount(Map mapper);
	
	/**
	 * 高级搜索备案历史
	 * @param map
	 * @param page
	 * @return
	 */
	SearchResult precisequery(Map map, Page page);

}
