package com.soc.service.systemsetting;

import java.util.List;
import java.util.Map;
import com.soc.model.systemsetting.MemoryData;
import com.util.page.Page;
import com.util.page.SearchResult;

public interface SystemMemoryService
{
    public void insert(MemoryData memoryData);
    public SearchResult memoryQuery(Map map, Page page);
    public List<MemoryData> memoryQuery();
    public List<MemoryData> memoryQueryOne();
}