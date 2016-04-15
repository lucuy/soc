package com.compliance.service.cpManage.generalPhysical.impl;

import java.util.List;
//差距分析报告导出word文档
//表：信息系统总体符合度
import com.compliance.dao.cpManage.generalPhysical.GeneralPhysicalDao;
import com.compliance.service.cpManage.generalPhysical.GeneralPhysicalService;

public class GeneralPhysicalServiceImpl implements GeneralPhysicalService {
	private GeneralPhysicalDao generalPhysicalDao;

	public GeneralPhysicalDao getGeneralPhysicalDao() {
		return generalPhysicalDao;
	}

	public void setGeneralPhysicalDao(GeneralPhysicalDao generalPhysicalDao) {
		this.generalPhysicalDao = generalPhysicalDao;
	}

	public int getGeneralPhysicalCount() {
		int list = generalPhysicalDao.getGeneralPhysicalCount();
		return list;
	}

	public int getManagementCount() {
		int list = generalPhysicalDao.getManagementCount();
		return list;
	}

	public int getTechnologyCount(int pca) {
		int list = generalPhysicalDao.getTechnologyCount(pca);
		return list;
	}

	public int getTongYongWuLi() {
		int num = generalPhysicalDao.getTongYongWuLi();
		return num;
	}

	public int getTongYongGuanLi() {
		int num = generalPhysicalDao.getTongYongGuanLi();
		return num;

	}

	// 通用物理符合度

	public int getTyWuliFuHeNum() {
		int i = generalPhysicalDao.getTyWuliFuHeNum();
		return i;
	}

	public int getTyWuliBufenFuHeNum() {
		int i = generalPhysicalDao.getTyWuliBufenFuHeNum();
		return i;
	}

	public int getTyWuliBuFuHeNum() {
		int i = generalPhysicalDao.getTyWuliBuFuHeNum();
		return i;
	}

	public int getTyWuliChaJuNum() {
		int i = generalPhysicalDao.getTyWuliChaJuNum();
		return i;
	}

	// 通用管理符合度

	public int getTyGuanLiFuHeNum() {
		int i = generalPhysicalDao.getTyGuanLiFuHeNum();
		return i;
	}

	public int getTyGuanLiFuFenHeNum() {
		int i = generalPhysicalDao.getTyGuanLiFuFenHeNum();
		return i;
	}

	public int getTyGuanLiBuFuHeNum() {
		int i = generalPhysicalDao.getTyGuanLiBuFuHeNum();
		return i;
	}

	public int getTyGuanLiChaJuNum() {
		int i = generalPhysicalDao.getTyGuanLiChaJuNum();
		return i;

	}

}
