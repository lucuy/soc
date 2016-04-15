package com.compliance.dao.cpManage.gapStatisticsUnit;

import java.util.List;

public interface GapStatisticsUnitDao {
	// / 6.1%的不同符合情况
	public List<Integer> getGapStatisticsUnitTwo0(int fkca);

	public List<Integer> getGapStatisticsUnitTwo1(int fkca);

	public List<Integer> getGapStatisticsUnitTwo2(int fkca);

	public List<Integer> getGapStatisticsUnitTwo1And2(int fkca);

	// / 7.1%的不同符合情况
	public List<Integer> getGapStatisticsUnitThree0(int fkca);

	public List<Integer> getGapStatisticsUnitThree1(int fkca);

	public List<Integer> getGapStatisticsUnitThree2(int fkca);

	public List<Integer> getGapStatisticsUnitThree1And2(int fkca);

	// / 8.1%的不同符合情况
	public List<Integer> getGapStatisticsUnitFour0(int fkca);

	public List<Integer> getGapStatisticsUnitFour1(int fkca);

	public List<Integer> getGapStatisticsUnitFour2(int fkca);

	public List<Integer> getGapStatisticsUnitFour1And2(int fkca);

}
