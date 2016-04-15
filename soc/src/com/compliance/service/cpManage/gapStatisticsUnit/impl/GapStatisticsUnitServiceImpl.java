package com.compliance.service.cpManage.gapStatisticsUnit.impl;

import java.util.List;

import com.compliance.dao.cpManage.gapStatisticsUnit.GapStatisticsUnitDao;
import com.compliance.service.cpManage.gapStatisticsUnit.GapStatisticsUnitService;

public class GapStatisticsUnitServiceImpl implements GapStatisticsUnitService {

	public GapStatisticsUnitDao gapStatisticsUnitDao;

	public GapStatisticsUnitDao getGapStatisticsUnitDao() {
		return gapStatisticsUnitDao;
	}

	public void setGapStatisticsUnitDao(GapStatisticsUnitDao gapStatisticsUnitDao) {
		this.gapStatisticsUnitDao = gapStatisticsUnitDao;
	}

	public List<Integer> getGapStatisticsUnitTwo0(int fkca) {
		List<Integer> list = gapStatisticsUnitDao
				.getGapStatisticsUnitTwo0(fkca);
		return list;
	}

	public List<Integer> getGapStatisticsUnitTwo1(int fkca) {
		List<Integer> list = gapStatisticsUnitDao
				.getGapStatisticsUnitTwo1(fkca);
		return list;
	}

	public List<Integer> getGapStatisticsUnitTwo2(int fkca) {
		List<Integer> list = gapStatisticsUnitDao
				.getGapStatisticsUnitTwo2(fkca);
		return list;
	}

	public List<Integer> getGapStatisticsUnitTwo1And2(int fkca) {
		List<Integer> list = gapStatisticsUnitDao
				.getGapStatisticsUnitTwo1And2(fkca);
		return list;
	}

	public List<Integer> getGapStatisticsUnitThree0(int fkca) {
		List<Integer> list = gapStatisticsUnitDao
				.getGapStatisticsUnitThree0(fkca);
		return list;
	}

	public List<Integer> getGapStatisticsUnitThree1(int fkca) {
		List<Integer> list = gapStatisticsUnitDao
				.getGapStatisticsUnitThree1(fkca);
		return list;
	}

	public List<Integer> getGapStatisticsUnitThree2(int fkca) {
		List<Integer> list = gapStatisticsUnitDao
				.getGapStatisticsUnitThree2(fkca);
		return list;
	}

	public List<Integer> getGapStatisticsUnitThree1And2(int fkca) {
		List<Integer> list = gapStatisticsUnitDao
				.getGapStatisticsUnitThree1And2(fkca);
		return list;
	}

	public List<Integer> getGapStatisticsUnitFour0(int fkca) {
		List<Integer> list = gapStatisticsUnitDao
				.getGapStatisticsUnitFour0(fkca);
		return list;
	}

	public List<Integer> getGapStatisticsUnitFour1(int fkca) {
		List<Integer> list = gapStatisticsUnitDao
				.getGapStatisticsUnitFour1(fkca);
		return list;
	}

	public List<Integer> getGapStatisticsUnitFour2(int fkca) {
		List<Integer> list = gapStatisticsUnitDao
				.getGapStatisticsUnitFour2(fkca);
		return list;
	}

	public List<Integer> getGapStatisticsUnitFour1And2(int fkca) {
		List<Integer> list = gapStatisticsUnitDao
				.getGapStatisticsUnitFour1And2(fkca);
		return list;
	}
}
