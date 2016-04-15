package com.compliance.service.files;

import java.util.Map;

import com.util.page.Page;
import com.util.page.SearchResult;

public interface FilesService {
	/**
     * 查询数据
     * 
     * @param map,page
     * @return SearchResult
     */
	public SearchResult query(Map map,Page page,String flag);

}
