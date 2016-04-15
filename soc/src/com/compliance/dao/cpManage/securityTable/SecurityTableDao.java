package com.compliance.dao.cpManage.securityTable;

import java.util.List;

//合规管理
////差距分析
//差距分析报告
//表：安全领域符合度
public interface SecurityTableDao {
	public List<Integer> getSecurityTableTwo(int fkca);

	public List<Integer> getSecurityTableThree(int fkca);

	public List<Integer> getSecurityTableFour(int fkca);
}
