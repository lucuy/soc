package com.soc.dao.workorder.ibatis;

import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.workorder.WorkOrderDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.workorder.WorkOrder;

/**
 * 
 * <工作单dao实现类>
 * <功能详细描述>
 * 
 * @author  liruifeng
 * @version  [版本号, 2013-1-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class WorkOrderDaoIbatis extends BaseDaoIbatis implements WorkOrderDao
{
    /**
     * 查询数量{@inheritDoc}
     */
    @Override
    public int count(Map map)
    {
        // TODO Auto-generated method stub
        Object ob = null;
        // 根据map中存储的条件查询符合条件的密码策略的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+"workOrder.count", map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        // 总条数
        int totalRows = 0;
        
        if (ob != null)
        {
            totalRows = ((Integer)ob).intValue();
        }
        
        return totalRows;
    }
    /**
     * 根据条件查询工单{@inheritDoc}
     */
    @Override
    public List<WorkOrder> query(Map map, int startRow, int pageSize)
    {
        // TODO Auto-generated method stub
         return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"workOrder.query",map, startRow, pageSize);
    }
    /**
     * 新增工单{@inheritDoc}
     */
    @Override
    public int insertWorkOrder(WorkOrder workOrder)
    {
        // TODO Auto-generated method stub
        
        return (Integer) this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"workOrder.insert", workOrder);
    }
    /**
     * 通过id查询{@inheritDoc}
     */
    @Override
    public WorkOrder queryById(long id)
    {
        // TODO Auto-generated method stub
        return (WorkOrder)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"workOrder.queryById",id);
    }
    /**
     * 批量删除{@inheritDoc}
     */
    @Override
    public int deleteWorkOrder(String result)
    {
        // TODO Auto-generated method stub
        this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"workOrder.delete",result);
        
        return 1;
    }
    /**
     * 更新工单{@inheritDoc}
     */
    @Override
    public void updateWorkOrder(WorkOrder workOrder)
    {
        // TODO Auto-generated method stub
        this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"workOrder.update", workOrder);
    }
	@Override
	public WorkOrder queryByAlarmId(long id) {
		return (WorkOrder) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"workOrder.queryByAlarmId", id);
	}
	@Override
	public int queryWorkOrderStatus(int workOrderId) {
		try{
			return (Integer) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"workOrder.status", workOrderId);
			}catch (Exception e) {
				e.printStackTrace();
				return 4;//代表工单不存在
			}
	}
	@Override
	public void closeWorkOrder(Map map) {
		 this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"workOrder.close", map);
	}
	@Override
	public String queryWorkOrderCloseReason(int workOrderId) {
		try{
			return (String) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"workOrder.closeReason", workOrderId);
			}catch (Exception e) {
				return null;//代表工单不存在
			}
	}
    
}
