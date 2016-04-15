package com.soc.dao.workorder;

import java.util.List;
import java.util.Map;

import com.soc.dao.BaseDao;
import com.soc.model.workorder.WorkOrder;

/**
 * 
 * <工作单dao>
 * <功能详细描述>
 * 
 * @author  liruifeng
 * @version  [版本号, 2013-1-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface WorkOrderDao extends BaseDao
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
    public List<WorkOrder> query(Map map, int startRow, int pageSize);
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
     * <查询工单状态>
     * <功能详细描述>
     * @param workOrderId
     * @see [类、类#方法、类#成员]
     */
    public int queryWorkOrderStatus(int workOrderId);
    /**
     * <关闭工单>
     * <功能详细描述>
     * @param workOrderId
     * @see [类、类#方法、类#成员]
     */
    public void closeWorkOrder(Map map);
    /**
     * <查询工单关闭原因>
     * <功能详细描述>
     * @param workOrderId
     * @see [类、类#方法、类#成员]
     */
    public String queryWorkOrderCloseReason(int workOrderId);
}
