package com.soc.service.workorder;

import java.util.Map;

import com.soc.model.workorder.WorkOrder;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <工单管理服务接口>
 * <功能详细描述>
 * 
 * @author  liruifeng
 * @version  [版本号, 2013-1-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface WorkOrderService
{
    /**
     * <查询总条数>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int count(Map map);
    /**
     * <查询>
     * <功能详细描述>
     * @param map
     * @param startRow
     * @param pageSize
     * @return
     * @see [类、类#方法、类#成员]
     */
    public SearchResult query(Map map, Page page);
    /**
     * <新增工单>
     * <功能详细描述>
     * @param workOrder
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int insertWorkOrder(WorkOrder workOrder);
    /**
     * <详细查看工单>
     * <功能详细描述>
     * @param workOrder
     * @return
     * @see [类、类#方法、类#成员]
     */
    public WorkOrder queryById(long  id);
    /**
     * <批量删除工单>
     * <功能详细描述>
     * @param result
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int deleteWorkOrder(String result);
    /**
     * <修改工单>
     * <功能详细描述>
     * @param workOrder
     * @see [类、类#方法、类#成员]
     */
    public void updateWorkOrder(WorkOrder workOrder);
    /**
     * <从告警发出请求的详细查看工单>
     * <功能详细描述>
     * @param workOrder
     * @return
     * @see [类、类#方法、类#成员]
     */
    public WorkOrder queryByAlarmId(long  id);
    /**
     * <关闭工单>
     * <功能详细描述>
     * @param workOrderId
     * @see [类、类#方法、类#成员]
     */
    public int closeWorkOrder(Map map);
    /**
     * <查询工单关闭原因>
     * <功能详细描述>
     * @param workOrderId
     * @see [类、类#方法、类#成员]
     */
    public String queryWorkOrderCloseReason(int workOrderId);
}
