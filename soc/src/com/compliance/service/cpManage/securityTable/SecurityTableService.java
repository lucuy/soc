package com.compliance.service.cpManage.securityTable;

import java.util.List;

//合规管理
////差距分析
//差距分析报告
//表：安全领域符合度
public interface SecurityTableService {
	public List<Integer> getSecurityTableServiceTwo(int fkca);

	public List<Integer> getSecurityTableServiceThree(int fkca);

	public List<Integer> getSecurityTableServiceFour(int fkca);
}
