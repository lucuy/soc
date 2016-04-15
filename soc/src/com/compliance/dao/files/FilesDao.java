package com.compliance.dao.files;

import java.util.List;
import java.util.Map;

import com.compliance.model.basicinfo.assets.DocAssets;
import com.compliance.model.rank.Rank;
import com.compliance.model.rank.SysAbolish;

public interface FilesDao {
	/**
	 * 查询数据条数
	 * 
	 * @param map
	 * @return int
	 */
	public int count(Map map,String flag);
	
	/**
     * 查询系统废止文档数据
     * 
     * @param map,int,int
     * @return List<Audit>
     */
	public List<SysAbolish> queryabolish(Map map,int startRow, int pageSize);
	 /**
     * 查询系统定级文档数据
     * 
     * @param map,int,int
     * @return List<Audit>
     */
	public List<Rank> queryRank(Map map, int startIndex, int pageSize);

}
