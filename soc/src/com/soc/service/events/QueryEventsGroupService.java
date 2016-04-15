package com.soc.service.events;

import java.io.Serializable;


public interface QueryEventsGroupService  extends Serializable
{
    /**
     * 构造事件查询人条件组
     * @return
     */
    public String createTrre(String objectBath);
}
