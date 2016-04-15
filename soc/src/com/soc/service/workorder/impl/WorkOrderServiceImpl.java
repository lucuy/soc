package com.soc.service.workorder.impl;

import java.util.List;
import java.util.Map;

import com.soc.dao.workorder.WorkOrderDao;
import com.soc.model.workorder.WorkOrder;
import com.soc.service.impl.BaseServiceImpl;
import com.soc.service.workorder.WorkOrderService;
import com.util.page.Page;
import com.util.page.SearchResult;
/**
 * 
 * <工单管理服务实现类 对工单的增删改查>
 * <功能详细描述>
 * 
 * @author  liruifeng
 * @version  [版本号, 2013-1-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class WorkOrderServiceImpl extends BaseServiceImpl implements WorkOrderService
{
    private WorkOrderDao workOrder;
    
    
    public WorkOrderDao getWorkOrder()
    {
        return workOrder;
    }

    public void setWorkOrder(WorkOrderDao workOrder)
    {
        this.workOrder = workOrder;
    }
    /**
     * 查询条数{@inheritDoc}
     */
    @Override
    public int count(Map map)
    {
        // TODO Auto-generated method stub
        return workOrder.count(map);
    }

    /**
     * 新增工单{@inheritDoc}
     */
    @Override
    public int insertWorkOrder(WorkOrder orkOrder)
    {
        // TODO Auto-generated method stub
        int i =  workOrder.insertWorkOrder(orkOrder);
        
        return i;
    }
    /**
     * 通过id查询{@inheritDoc}
     */
    @Override
    public WorkOrder queryById(long id)
    {
        // TODO Auto-generated method stub 
        return workOrder.queryById(id);
    }
    /**
     * 删除工单{@inheritDoc}
     */
    @Override
    public int deleteWorkOrder(String result)
    {
        // TODO Auto-generated method stub
        workOrder.deleteWorkOrder(result);
       
        return 1;
    }
    /**
     * 修改工单{@inheritDoc}
     */
    @Override
    public void updateWorkOrder(WorkOrder orkOrder)
    {
        // TODO Auto-generated method stub
        workOrder.updateWorkOrder(orkOrder);
    }
    /**
     * 查询工单{@inheritDoc}
     */
    @Override
    public SearchResult query(Map map, Page page)
    {
        // TODO Auto-generated method stub
        int rowsCount = workOrder.count(map);
        
        page.setTotalCount(rowsCount);
        
        List<WorkOrder> list = workOrder.query(map, page.getStartIndex(),page.getPageSize());
        
        // 对查找的预警列表做分页处理
        SearchResult<WorkOrder> rs = new SearchResult<WorkOrder>();
        
        rs.setList(list);
        rs.setPage(page);
        return rs;
    }

	@Override
	public WorkOrder queryByAlarmId(long id) {
		return workOrder.queryByAlarmId(id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int closeWorkOrder(Map map) {
		int status =workOrder.queryWorkOrderStatus(Integer.parseInt(map.get("workOrderId").toString()));
		if(status==0){
			workOrder.closeWorkOrder(map);
		}
		return status;
	}

	@Override
	public String queryWorkOrderCloseReason(int workOrderId) {
		return workOrder.queryWorkOrderCloseReason(workOrderId);
	}
   
}
