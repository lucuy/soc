package com.compliance.service.rank;

import java.util.List;

import com.compliance.model.rank.RecordDocList;

/**
 * 备案集业务接口
 * @author quyongkun
 *
 */
public interface RecordDocListService {
	
	/**
	 * 根据备案集编号查询
	 * @param recordDocListId 备案集编号
	 * @return 备案集信息
	 */
	RecordDocList queryById(int recordDocListId);
	
	/**
	 * 根据备案编号查询备案集
	 * @param recordId 备案编号
	 * @return 备案集合
	 */
	List<RecordDocList> queryByRecordId(int recordId);
	
	/**
	 * 插入备案记录
	 * @param recordDocList 备案记录
	 * @return 插入是否成功
	 */
	boolean insert(RecordDocList recordDocList);
	
	/**
	 * 修改备案记录
	 * @param recordDocList 备案信息
	 * @return 修改是否成功
	 */
	boolean update(RecordDocList recordDocList);
	


}
