package com.compliance.service.files.impl;
import com.compliance.dao.files.FilesDao;
import java.util.List;
import java.util.Map;

import com.compliance.dao.files.FilesDao;
import com.compliance.model.basicinfo.assets.DocAssets;
import com.compliance.model.rank.Rank;
import com.compliance.model.rank.SysAbolish;
import com.compliance.service.files.FilesService;
import com.util.page.Page;
import com.util.page.SearchResult;

public class FilesServiceImpl implements FilesService {
	private FilesDao filedao;
	

	public FilesDao getFiledao() {
		return filedao;
	}


	public void setFiledao(FilesDao filedao) {
		this.filedao = filedao;
	}


	public SearchResult query(Map map, Page page, String flag) {
		// TODO Auto-generated method stub
		// 查询数据
		int rowCount=0;
		if("11".equals(flag)||"12".equals(flag)||"13".equals(flag)){
			rowCount=filedao.count(map, "abolish");
		}else{
			rowCount=filedao.count(map, "rank");
		}
		page.setTotalCount(rowCount);
		SearchResult sr = new SearchResult();
		if("11".equals(flag)||"12".equals(flag)||"13".equals(flag)){
			List<SysAbolish> listrank=filedao.queryabolish(map, page.getStartIndex(), page.getPageSize());
			sr.setList(listrank);
			sr.setPage(page);
		}else{
				List<Rank> listabolish=filedao.queryRank(map, page.getStartIndex(), page.getPageSize());
				sr.setList(listabolish);
				sr.setPage(page);
		}
		return sr;
	}

}
