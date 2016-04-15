package com.soc.junit.test.workorder;

import org.junit.Before;

import com.soc.junit.test.base.BaseTest;
import com.soc.model.workorder.WorkOrder;
import com.soc.service.workorder.WorkOrderService;

public class WorkOrderTest extends BaseTest{
	private WorkOrderService workorder;
	
	@Before
	public void init(){
		super.init();
		workorder = (WorkOrderService) super.getBean("workOrderManager");
	}
	
	public void queryByAlarmID(){
		WorkOrder work=new WorkOrder();
		work=workorder.queryByAlarmId(2);
	}
}
