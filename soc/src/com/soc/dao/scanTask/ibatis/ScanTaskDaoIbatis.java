package com.soc.dao.scanTask.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.scanTask.ScanTaskDao;
import com.soc.model.ScanTask.ScanTask;
import com.soc.model.conf.GlobalConfig;

public class ScanTaskDaoIbatis extends BaseDaoIbatis implements ScanTaskDao {

	@SuppressWarnings({ "rawtypes", "unused" })
	@Override
	public int count(Map map) {
		Object obj = null;
		try {
			obj = super.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"scanTask.count", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(obj!=null){
			return Integer.valueOf(obj.toString());
		}else{
		return 0;}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<ScanTask> queryAllScanTasks(Map map, int startRow, int pageSize) {
		List<ScanTask> list = null;
		try {
			list = this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"scanTask.queryAll", map, startRow, pageSize);
		} catch (Exception e) {
			list = new ArrayList<ScanTask>();
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ScanTask queryScanTaskById(long id) {
		
		return (ScanTask) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"scanTask.queryById", id);
	}

	@Override
	public ScanTask queryScanTaskByName(String name) {
		return (ScanTask) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"scanTask.queryByName", name);
	}

	@Override
	public String queryScanTaskByFileName(String fileName) {
		
		return  (String) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"scanTask.queryByFileName", fileName);
	}

	@Override
	public void deleteById(long id) {
		this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"scanTask.deleteById", id);

	}

	@Override
	public void updateScanTask(ScanTask scanTask) {
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"scanTask.update", scanTask);

	}

	@Override
	public void updateStateById(long id) {
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"scanTask.updateStateById", id);
		

	}

	@Override
	public void updateStateByFileName(String fileName) {
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"scanTask.updateByFileName", fileName);

	}

	@Override
	public void insertScanTask(ScanTask scanTask) {
		super.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"scanTask.insert", scanTask);
	}

}
