package com.soc.service.knowledge.importLeak;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.soc.model.knowledge.Leak;

/**
 * 
 * <一句话功能简述> <功能详细描述>
 * 
 * @author gaosong
 * @version [版本号, 2013-1-27]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ImportLeak {

	public static List<Leak> readRead(File path) {
		String sheetName = null;
		int sheetCount = -1;
		int rowCount = -1;
		int cellCount = -1;

		List<List> list1 = new ArrayList<List>();

		Leak leak = null;

		List<Leak> list = new ArrayList<Leak>();

		try {
			HSSFWorkbook wk = new HSSFWorkbook(new FileInputStream(path));

			sheetCount = wk.getNumberOfSheets();
			HSSFSheet sheet = wk.getSheetAt(0);

			rowCount = sheet.getLastRowNum();
			for (int i = 2; i <= rowCount; i++) {

				HSSFRow row = sheet.getRow(i);

				cellCount = row.getLastCellNum();
				List<HSSFCell> lists = new ArrayList<HSSFCell>();
				for (int k = 0; k <= cellCount; k++) {

					HSSFCell cell = row.getCell(k);

					lists.add(cell);

				}

				leak = new Leak();
				// 给漏洞类的各个属性赋值添加顺序按照数据库设计字段的顺序
				leak.setLeakId(Integer.valueOf(lists.get(0)
						.getStringCellValue()));
				leak.setLeakName(lists.get(1).getStringCellValue());
				leak.setLeakType(lists.get(2).getStringCellValue());
				leak.setLeakLevel(lists.get(3).getStringCellValue());
				leak.setCNCVENO(lists.get(4).getStringCellValue());
				leak.setCVENO(lists.get(5).getStringCellValue());
				leak.setBUGTRAQNO(lists.get(6).getStringCellValue());
				leak.setsDES(lists.get(7).getStringCellValue());
				leak.setlDes(lists.get(8).getStringCellValue());
				leak.setAdvice(lists.get(9).getStringCellValue());
				/*
				 * // 资产id asset.setAssetId(Long
				 * .valueOf(lists.get(0).getStringCellValue()));
				 * 
				 * // 资产名称
				 * asset.setAssetName(lists.get(1).getStringCellValue());
				 * 
				 * // 资产ip String ip = lists.get(2).getStringCellValue();
				 * asset.setAssetIp(StrIpTransitionLip(ip));
				 * 
				 * // 资产网关 String netWork = lists.get(3).getStringCellValue();
				 * asset.setAssetGateWay(StrIpTransitionLip(netWork));
				 * 
				 * // 资产ip段 String ips = lists.get(4).getStringCellValue();
				 * asset.setAssetSegMent(StrIpTransitionLip(ips));
				 * 
				 * // 资产组Id asset.setAssetGroupId(Integer.parseInt(lists.get(5)
				 * .getStringCellValue()));
				 * 
				 * // 资产组架构
				 * asset.setAssetGroupFeature(lists.get(6).getStringCellValue
				 * ());
				 * 
				 * // 资产重要性 String important =
				 * lists.get(7).getStringCellValue();
				 * 
				 * int flag = 0; if (important.equals("一般")) { flag = -1; } if
				 * (important.equals("重要")) { flag = 0; } if
				 * (important.equals("比较重要")) { flag = 1; } if
				 * (important.equals("非常重要")) { flag = 2; }
				 * asset.setAssetImportance(flag);
				 * 
				 * // 资产主机名称
				 * asset.setAssetHostName(lists.get(8).getStringCellValue());
				 * 
				 * // 资产mac地址 asset.setAssetMac(ip);
				 * 
				 * // 创建者登录名
				 * asset.setAssetUserLoginName(lists.get(10).getStringCellValue
				 * ());
				 * 
				 * // 资产节点id asset.setAssetNodeId(Integer.parseInt(lists.get(11)
				 * .getStringCellValue()));
				 * 
				 * // 资产责任人
				 * asset.setAssetAliasName(lists.get(12).getStringCellValue());
				 * 
				 * // 采集类型 int flag2 = 0; String collectType =
				 * lists.get(13).getStringCellValue(); if
				 * (collectType.equals("Syslog")) { flag2 = -1; } if
				 * (collectType.equals("snmp")) { flag2 = 0; } if
				 * (collectType.equals("代理")) { flag2 = 1; }
				 * asset.setAssetUnName(flag2);
				 * 
				 * // 团体名
				 * asset.setOrganizationName(lists.get(14).getStringCellValue
				 * ());
				 * 
				 * // 版本号 asset.setVersion(lists.get(15).getStringCellValue());
				 * 
				 * // 监控目录
				 * asset.setDirectorise(lists.get(16).getStringCellValue());
				 * 
				 * // 监控文件 asset.setFile(lists.get(17).getStringCellValue());
				 * 
				 * // 资产标识
				 * asset.setAssetWorkIdent(lists.get(18).getStringCellValue());
				 * 
				 * // 资产描述
				 * asset.setAssetMemo(lists.get(19).getStringCellValue());
				 * 
				 * // 所属资产组名称
				 * asset.setAssetGroupName(lists.get(20).getStringCellValue());
				 * 
				 * // 采集器id
				 * asset.setAssetCollectorId(Integer.parseInt(lists.get(21)
				 * .getStringCellValue()));
				 * 
				 * // 采集器名称
				 * asset.setAssetCollectorName(lists.get(22).getStringCellValue
				 * ());
				 * 
				 * // 采集器Mac
				 * asset.setAssetCollectorMac(lists.get(23).getStringCellValue
				 * ());
				 * 
				 * // 采集器类型
				 * asset.setAssetCollectType(lists.get(24).getStringCellValue
				 * ());
				 * 
				 * // 设备类型id
				 * asset.setAssetDeviceTypeId(Integer.parseInt(lists.get(25)
				 * .getStringCellValue()));
				 * 
				 * // 设备类型名称
				 * asset.setAssetDeviceType(lists.get(26).getStringCellValue());
				 * 
				 * // 支持设备类型名称
				 * asset.setAssetSupportDevice(lists.get(27).getStringCellValue
				 * ());
				 * 
				 * // 支持设备id
				 * asset.setAssetSupportDeviceId(Integer.parseInt(lists.get(28)
				 * .getStringCellValue()));
				 * 
				 * // 特权命令 asset.setAssetPrivilegeCommand(lists.get(29)
				 * .getStringCellValue());
				 * 
				 * // 资产状态 String assetStatus =
				 * lists.get(30).getStringCellValue();
				 * 
				 * int flag3 = 0; if (assetStatus.equals("启用")) { flag3 = 1; }
				 * else { flag3 = 0; } asset.setAssetStatus(flag3);
				 * 
				 * // 设置为资产未删除 asset.setAssetIsDelete(0);
				 * 
				 * // 资产关联的规则
				 * asset.setAssetDeviceRules(lists.get(31).getStringCellValue
				 * ());
				 */
				list.add(leak);

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

}
