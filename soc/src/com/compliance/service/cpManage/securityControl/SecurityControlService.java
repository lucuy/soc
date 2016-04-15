package com.compliance.service.cpManage.securityControl;

import java.util.List;

public interface SecurityControlService {
	public List<Integer> getSecurityControl2(int pkca);
	public List<Integer> getSecurityControl3(int pkca);
	public List<Integer> getSecurityControl4(int pkca);
	public List<Integer> getControlTask4(String sysId);
	public List<Integer> getControlTask3(String sysId);
	public List<Integer> getControlTask2(String sysId);
}
