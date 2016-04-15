package com.soc.dao;

import java.util.List;

public interface BaseDao {
	public Object create(String sqlKey, Object obj);
	public int delete(String sqlKey, Integer pk);
	public int update(String sqlKey, Object obj);
	public List search(String sqlKey, Object param);
	public List search(String sqlKey, Object param, int skipResults, int maxResults);
	public Object queryForObject(String sqlKey, Object param);
}
