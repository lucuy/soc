package com.compliance.dao.rank.impl;

import java.util.List;

import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.dao.rank.RankReportDao;
import com.compliance.model.rank.RankReport;
import com.soc.model.conf.GlobalConfig;

public class RenkReportDaoImpl  extends BaseDaoiBatis  implements RankReportDao {

	@SuppressWarnings("unchecked")
	public List<RankReport> query() {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"rankReport.query");
	}

}
