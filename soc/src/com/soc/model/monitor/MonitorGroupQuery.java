package com.soc.model.monitor;

import java.io.Serializable;
import java.util.List;


public class MonitorGroupQuery implements Serializable{

		private long queryGroupId; //组ID
	    
	    private String queryGroupName; //组名称
	    
	    private long queryGroupParentId; //组父ID
	    
	    private int queryGroupType; //组类型（0：内置事件组   1：自定义事件组）
	    
	    private String queryConditions; //事件查询条件
	    
	    private List<MonitorGroupQuery> groupNodeList; //组二级以下的组节点对象集合

		public long getQueryGroupId() {
			return queryGroupId;
		}

		public void setQueryGroupId(long queryGroupId) {
			this.queryGroupId = queryGroupId;
		}

		public String getQueryGroupName() {
			return queryGroupName;
		}

		public void setQueryGroupName(String queryGroupName) {
			this.queryGroupName = queryGroupName;
		}

		public long getQueryGroupParentId() {
			return queryGroupParentId;
		}

		public void setQueryGroupParentId(long queryGroupParentId) {
			this.queryGroupParentId = queryGroupParentId;
		}

		public int getQueryGroupType() {
			return queryGroupType;
		}

		public void setQueryGroupType(int queryGroupType) {
			this.queryGroupType = queryGroupType;
		}

		public String getQueryConditions() {
			return queryConditions;
		}

		public void setQueryConditions(String queryConditions) {
			this.queryConditions = queryConditions;
		}

		public List<MonitorGroupQuery> getGroupNodeList() {
			return groupNodeList;
		}

		public void setGroupNodeList(List<MonitorGroupQuery> groupNodeList) {
			this.groupNodeList = groupNodeList;
		}

		
	    
}
