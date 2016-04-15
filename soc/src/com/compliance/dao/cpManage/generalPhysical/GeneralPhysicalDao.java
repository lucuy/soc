package com.compliance.dao.cpManage.generalPhysical;

import java.util.List;
//差距分析报告导出word文档
//表：信息系统总体符合度

public interface GeneralPhysicalDao {
	public int getGeneralPhysicalCount();

	public int getManagementCount();

	public int getTechnologyCount(int pca);

	public int getTongYongWuLi();

	public int getTongYongGuanLi();

	// 通用物理符合度
	public int getTyWuliFuHeNum();

	public int getTyWuliBufenFuHeNum();

	public int getTyWuliBuFuHeNum();

	public int getTyWuliChaJuNum();

	// 通用管理符合度
	public int getTyGuanLiFuHeNum();

	public int getTyGuanLiFuFenHeNum();

	public int getTyGuanLiBuFuHeNum();

	public int getTyGuanLiChaJuNum();
}
