package com.soc.dao.systemsetting;

import java.util.List;
import java.util.Map;

import com.soc.model.systemsetting.MemoryData;

public interface SystemMemoryDao
{
    public void insert(MemoryData memoryData);
    public int count(Map map);
    public List<MemoryData> memoryQuery(Map map, int startRow, int pageSize);
    public List<MemoryData> memoryQuery();
    public List<MemoryData> memoryQueryOne();
}