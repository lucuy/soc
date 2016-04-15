package com.soc.dao.events.ibatis;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.soc.dao.events.NotAnalyticEventsDao;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.model.asset.Asset;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.events.NotAnalyticEvents;
import com.util.GetDateUtil;

public class NotAnalyticEventsDaoIbatis extends BaseDaoIbatis implements NotAnalyticEventsDao
{
    
    /**
     * {@inheritDoc}
     */
    public void insertNotAnalyticEvents(NotAnalyticEvents notAnalyticEvents)
    {
        super.create(GlobalConfig.sqlId+"insert.notAnalyticEvents", notAnalyticEvents);
    }
    
    /**
     * {@inheritDoc}
     */
    public NotAnalyticEvents queryNotAnalyticEvents(Map<String, Object> map)
    {
        return (NotAnalyticEvents)super.queryForObject(GlobalConfig.sqlId+"query.notAnalyticEvent", map);
    }
    
    /**
     * {@inheritDoc}
     */
    public int existsTable(String tableName)
    {
        return (Integer)super.queryForObject(GlobalConfig.sqlId+"exists.notAnalyticEvent", tableName);
    }
    
    /**
     * {@inheritDoc}
     */
    public String existsSeq(String seqName)
    {
        return (String)super.queryForObject(GlobalConfig.sqlId+"exists.notAnalyticEventSeq", seqName);
    }
    
    /**
     * {@inheritDoc}
     */
    public void createNotAnalyticEventsSeq(String seqName)
    {
        super.create(GlobalConfig.sqlId+"create.notAnalyticEventSeq", seqName);
    }
    
    /**
     * {@inheritDoc}
     */
    public void createNotAnalyticEventsTable(Map<String, String> map)
    {
        super.create(GlobalConfig.sqlId+"create.notAnalyticEvent", map);
    }

    /** {@inheritDoc} */
     
    @Override
    public int count(Map map)
    {
        // TODO Auto-generated method stub
        Object ob = null;
        
        // 根据map中存储的条件查询符合条件的用户的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+"query.countAnalys", map);
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

    /** {@inheritDoc} */
     
    @Override
    public List<NotAnalyticEvents> query(Map map,int startRow, int pageSize)
    {
        // TODO Auto-generated method stub
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.notAnalysEventsList",map,startRow, pageSize);
    }

	@Override
	public List<Integer> queryListOfAssetID(Map map) {
		List<Integer> list=this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.queryListOfAssetID", map);
		
		Set<Integer> set=new HashSet<Integer>();
		//去除重复项
		set.addAll(list);
		
		list.addAll(set);
		return list;
	}

	@Override
	public List<Asset> queryAssetList(Map map,
			int startRow, int pageSize) {
		List<String> list = GetDateUtil.getDate((Integer) map.get("day"));
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM tbl_asset where \"ASSET_IS_DELETE\" = 0 AND \"ASSET_STATUS\" = 1 ");
		
		if (map.get("keyword") != null) {
			sb.append("AND \"ASSET_NAME\" LIKE '%");
			sb.append((String)map.get("keyword"));
			sb.append("%'");
		}
		if (map.get("assetId") != null &&!map.get("assetId").equals("")) {
			sb.append("AND \"ASSET_ID\" not in(");
			sb.append((String)map.get("assetId"));
			sb.append(")");
		}
		if(map.get("assetIds")!=null){
			sb.append("AND \"ASSET_ID\" in(");
			sb.append((String)map.get("assetIds"));
			sb.append(")");
		}else{
				sb.append("AND \"ASSET_ID\" in(0)");
			}
		
		map.put("sql", sb.toString());
		
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.assetList",map,startRow,pageSize);
		
	}

	@Override
	public int countOfAsset(Map map) {
		List<String> list = GetDateUtil.getDate((Integer) map.get("day"));
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(\"ASSET_ID\") FROM tbl_asset where \"ASSET_IS_DELETE\" = 0 AND \"ASSET_STATUS\" = 1 ");
		
		if (map.get("keyword") != null) {
			sb.append("AND \"ASSET_NAME\" LIKE '%");
			sb.append((String)map.get("keyword"));
			sb.append("%'");
		}
		if (map.get("assetId") != null &&!map.get("assetId").equals("")) {
			sb.append("AND \"ASSET_ID\" not in(");
			sb.append((String)map.get("assetId"));
			sb.append(")");
		}
		if(map.get("assetIds")!=null){
			sb.append("AND \"ASSET_ID\" in(");
			sb.append((String)map.get("assetIds"));
			sb.append(")");
		}else{
			sb.append("AND \"ASSET_ID\" in(0)");
		}
		map.put("sql", sb.toString());
		if (this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"query.countOfAsset",map)==null) {
			return 0;
		}else{
			return (Integer)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"query.countOfAsset",map);
		}
	}

	@Override
	public List<NotAnalyticEvents> queryAllNotAnaly(Map map,int startRow, int pageSize) {
		
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.notAnalysEventsLists", map, startRow,pageSize);
	}

	@Override
	public int queryAllNotAnalyCont(Map map) {
		 Object ob = null;
	        
	        // 根据map中存储的条件查询符合条件的用户的记录数
	        try
	        {
	            ob = super.queryForObject(GlobalConfig.sqlId+"query.notAnalysEventsCount", map);
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
	
	
	
	
	
    
}
