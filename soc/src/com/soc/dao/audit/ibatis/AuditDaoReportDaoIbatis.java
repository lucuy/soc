package com.soc.dao.audit.ibatis;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.audit.AuditReportDao;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.model.asset.AssetGroup;
import com.soc.model.audit.AuditReport;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.events.Events;

/**
 * 
 * <审计报表> <功能详细描述>
 * 
 * @author gaosong
 * @version [版本号, 2013-3-8]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class AuditDaoReportDaoIbatis extends BaseDaoIbatis implements
		AuditReportDao {

	@Override
	public List<AuditReport> queryByParentId(Map map) {
		return getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+	"auditReportSQL.queryByParentId", map);
	}

	@Override
	public List<Map<String, Object>> queryEquipmentDetailList(Map map) {

		return getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+	"auditReportSQL.queryEquipmentDetailList", map, 0, 500);
	}

	@Override
	public List<Map<String, Object>> report6Table1(String tableName) {

		return getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+"auditReportSQL.report6Table1", tableName);
	}

	@Override
	public List<Map<String, Object>> report6Table2(String tableName) {
		return getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+"auditReportSQL.report6Table2", tableName);
	}

	@Override
	public List<Map<String, Object>> report6Table3(String tableName) {

		return getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+"auditReportSQL.report6Table3", tableName);
	}

	@Override
	public List<Map<String, Object>> report6Table4(String tableName) {

		return getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+"auditReportSQL.report6Table4", tableName,0,20);
	}

	@Override
	public List<Map<String, Object>> report6Table5(String tableName) {

		return getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+"auditReportSQL.report6Table5", tableName, 0, 10);
	}

	@Override
	public int existsTable(String tableName) {

		return (Integer) getSqlMapClientTemplate().queryForObject(
				GlobalConfig.sqlId+"auditReportSQL.existsTable", tableName);
	}

	@Override
	public List<Map<String, Object>> report2Table1(String tableName) {

		return getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+	"auditReportSQL.report2Table1", tableName);
	}

	@Override
	public List<Map<String, Object>> report2Table12(String tableName) {
		return getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+"auditReportSQL.report2Table12", tableName);
	}

	@Override
	public List<Map<String, Object>> report2Table2(String tableName) {
		return getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+"auditReportSQL.report2Table2", tableName);
	}

	@Override
	public List<Map<String, Object>> report2Table3(String tableName) {
		return getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+"auditReportSQL.report2Table3", tableName, 0, 10);
	}

	@Override
	public List<Map<String, Object>> report2Table4(String tableName) {
		return getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+"auditReportSQL.report2Table4", tableName);
	}

	@Override
	public List<Map<String, Object>> report2Table5(String tableName) {
		return getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+"auditReportSQL.report2Table5", tableName, 0, 20);
	}

	@Override
	public List<Map<String, Object>> report2Table6(String tableName) {
		return getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+	"auditReportSQL.report2Table6", tableName, 0, 20);
	}

	@Override
	public List<Map<String, Object>> report2Table7(String tableName) {
		return getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+"auditReportSQL.report2Table7", tableName, 0, 20);
	}

	@Override
	public List<Map<String, Object>> report2Table8(String tableName) {
		return getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+"auditReportSQL.report2Table8", tableName, 0, 20);
	}

	@Override
	public List<Map<String, Object>> report2Table9(String tableName) {
		return getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+	"auditReportSQL.report2Table9", tableName, 0, 20);
	}

	@Override
	public List<Map<String, Object>> report2Table10(String tableName) {
		return getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+	"auditReportSQL.report2Table10", tableName, 0, 20);
	}

	@Override
	public List<Map<String, Object>> report8Table1(String tableName) {
		return getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+	"auditReportSQL.report8Table1", tableName);
	}

	@Override
	public List<Map<String, Object>> report8Table3(String tableName) {
		return getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+	"auditReportSQL.report8Table3", tableName);
	}

	@Override
	public List<Map<String, Object>> report8Table2(String tableName) {
		return getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+"auditReportSQL.report8Table2", tableName);
	}

	@Override
	public List<Map<String, Object>> report8Table4(String tableName) {
		return getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+	"auditReportSQL.report8Table4", tableName, 0, 20);
	}

	@Override
	public List<Map<String, Object>> report8Table5(String tableName) {
		return getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+	"auditReportSQL.report8Table5", tableName, 0, 10);
	}

	@Override
	public List<Map<String, Object>> report9Table1(long groupid) {
		Map map = new HashMap();
		if (groupid!=1) {
			map.put("groupid", groupid);
		}
		return getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+	"auditReportSQL.report9Table1",map);
	}

	@Override
	public int report9Table1Count(long groupid) {
		Map map = new HashMap();
		if (groupid!=1) {
			map.put("groupid", groupid);
		}
		return  (Integer) getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"auditReportSQL.report9Table1Count",map);
	}

	@Override
	public List<Map<String, Object>> report9Table2(long groupid) {
		Map map = new HashMap();
		if (groupid!=1) {
			map.put("groupid", groupid);
		}
		return getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+	"auditReportSQL.report9Table2",map);
	}

	@Override
	public int report9Table2Count(Map map) {
		return  (Integer) getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"auditReportSQL.report9Table2Count",map);
	}

	@Override
	public List<Map<String, Object>> report7Table1() {
		return    getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"auditReportSQL.report7Table1");
		
	}

	@Override
	public List<AssetGroup> queryAllAssetGroup() {
		
		return getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"auditReportSQL.queryAllAssetGroup");
	}

	@Override
	public int countByAssetGroupId(Map map) {
		 Object ob = null;
	        //根据map中存储的条件查询符合条件的记录数
	        try
	        {
	            ob = super.queryForObject(GlobalConfig.sqlId+"auditReportSQL.queryAssets", map);
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
	@Override
	public List<Map<String, Object>> report10Table2(Map map) {
		return  getSqlMapClientTemplate().queryForList("auditReportSQL.report10Table1",map);
	}

	@Override
	public String AssetGroupNameById(long id) {
		return (String) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"auditReportSQL.queryAssetGroup",id);
	}

	@Override
	public List<Map<String, Object>> report12Table1(Map map) {
		return  getSqlMapClientTemplate().queryForList("auditReportSQL.report12Table1",map);
	}
	@Override
	public List<Map<String, Object>> report12Table2(Map map) {
		return  getSqlMapClientTemplate().queryForList("auditReportSQL.report12Table2",map);
	}
	@Override
	public List<Map<String, Object>> report12Table3(Map map) {
		return  getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"auditReportSQL.report12Table3",map,0,10);
	}

	@Override
	public List<Map<String, Object>> report13Table1(Map map) {
		return  getSqlMapClientTemplate().queryForList("auditReportSQL.report13Table1",map);
	}

	@Override
	public List<Map<String, Object>> report13Table2(Map map) {
		return  getSqlMapClientTemplate().queryForList("auditReportSQL.report13Table2",map);
	}

	@Override
	public List<Map<String, Object>> report14Table1(Map map) {
		return  getSqlMapClientTemplate().queryForList("auditReportSQL.report14Table1",map);
		}

	@Override
	public HashMap<String, Object> report14Table2Col1(Map<String, Object> map) {
		return  (HashMap<String, Object>) getSqlMapClientTemplate().queryForObject("auditReportSQL.report14Table2Col1",map);
	}

	@Override
	public HashMap<String, Object> report14Table2Col2(Map<String, Object> map) {
		return  (HashMap<String, Object>) getSqlMapClientTemplate().queryForObject("auditReportSQL.report14Table2Col2",map);
	}

	@Override
	public HashMap<String, Object> report14Table2Col3(Map<String, Object> map) {
		return  (HashMap<String, Object>) getSqlMapClientTemplate().queryForObject("auditReportSQL.report14Table2Col3",map);
	}

	@Override
	public HashMap<String, Object> report14Table2Col4(Map<String, Object> map) {
		return  (HashMap<String, Object>) getSqlMapClientTemplate().queryForObject("auditReportSQL.report14Table2Col4",map);
	}

	@Override
	public List<Map<String, Object>> report14Table2(String tableName) {
		return  getSqlMapClientTemplate().queryForList("auditReportSQL.report14Table2",tableName);
	}
	
	
}
