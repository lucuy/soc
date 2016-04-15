package com.soc.service.events.exporteventmasage;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import com.soc.model.events.Events;
import com.soc.model.events.OriginalEvents;
import com.soc.model.events.SummaryEvents;
import com.soc.service.events.QueryEventsService;
import com.util.DateUtil;
import com.util.StringUtil;

public class ExcelEventMasage {
	private HSSFWorkbook gwb = null;

	/**
	 * 导出具体实现方法
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void export(List<SummaryEvents> list, String title,
			QueryEventsService queryEeventsManager) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		int index = list.size() / 65530 + 1;// 根据总数确定有多少个sheet每个sheet包含65530条记录
		for (int a = 0; a < index; a++) {// 根据sheet总数来迭代数据
			// 定义行
			HSSFRow row0 = null;
			HSSFRow row1 = null;
			HSSFRow row2 = null;

			// 定义列
			HSSFCell cell = null;
			// 记录行数
			int iLineNumber = 0;
			// 记录列数
			int iColNumber = 0;

			// 样式0：生成顶部样式
			HSSFCellStyle csTop = wb.createCellStyle();
			csTop.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			csTop.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			// TOP字体样式
			HSSFFont fTop = wb.createFont();
			fTop.setFontName("微软雅黑");
			fTop.setColor(HSSFColor.BLACK.index);
			fTop.setFontHeightInPoints(Short.valueOf(String.valueOf(16)));

			// 应用字体
			csTop.setFont(fTop);

			// 样式1：生成标题样式
			HSSFCellStyle csHeader = wb.createCellStyle();

			// 自定义标题背景色
			int[] iHeaderColor = { 23, 55, 93 };
			HSSFPalette palette = wb.getCustomPalette();
			palette.setColorAtIndex(HSSFColor.DARK_BLUE.index,
					(byte) iHeaderColor[0], (byte) iHeaderColor[1],
					(byte) iHeaderColor[2]);

			// 自定义正文背景色
			int[] iBodyColor = { 242, 242, 242 };
			palette.setColorAtIndex(HSSFColor.GREY_25_PERCENT.index,
					(byte) iBodyColor[0], (byte) iBodyColor[1],
					(byte) iBodyColor[2]);

			// 自定义统计背景色
			int[] iSumColor = { 146, 208, 80 };
			palette.setColorAtIndex(HSSFColor.BRIGHT_GREEN.index,
					(byte) iSumColor[0], (byte) iSumColor[1],
					(byte) iSumColor[2]);

			// 设置这些样式
			csHeader.setFillForegroundColor(HSSFColor.DARK_BLUE.index);
			csHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			csHeader.setWrapText(true); // 自动换行
			csHeader.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			csHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 边框样式
			csHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);
			csHeader.setTopBorderColor(HSSFColor.BLACK.index);
			csHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			csHeader.setLeftBorderColor(HSSFColor.BLACK.index);
			csHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			csHeader.setBottomBorderColor(HSSFColor.BLACK.index);
			csHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
			csHeader.setRightBorderColor(HSSFColor.BLACK.index);

			// 标题字体样式
			HSSFFont fHeader = wb.createFont();
			fHeader.setFontName("微软雅黑");
			fHeader.setColor(HSSFColor.WHITE.index);
			fHeader.setFontHeightInPoints(Short.valueOf(String.valueOf(11)));
			fHeader.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

			// 应用字体
			csHeader.setFont(fHeader);

			// 样式2:生成正文样式
			HSSFCellStyle csBody = wb.createCellStyle();
			csBody.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			csBody.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			csBody.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			csBody.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			// 边框样式
			csBody.setBorderTop(HSSFCellStyle.BORDER_THIN);
			csBody.setTopBorderColor(HSSFColor.BLACK.index);
			csBody.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			csBody.setLeftBorderColor(HSSFColor.BLACK.index);
			csBody.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			csBody.setBottomBorderColor(HSSFColor.BLACK.index);
			csBody.setBorderRight(HSSFCellStyle.BORDER_THIN);
			csBody.setRightBorderColor(HSSFColor.BLACK.index);

			// 样式custom:生成正文样式
			HSSFCellStyle csBody2 = wb.createCellStyle();
			csBody2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			csBody2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			csBody2.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			csBody2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			// 边框样式
			csBody2.setBorderTop(HSSFCellStyle.BORDER_THIN);
			csBody2.setTopBorderColor(HSSFColor.BLACK.index);
			csBody2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			csBody2.setLeftBorderColor(HSSFColor.BLACK.index);
			csBody2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			csBody2.setBottomBorderColor(HSSFColor.BLACK.index);
			csBody2.setBorderRight(HSSFCellStyle.BORDER_THIN);
			csBody2.setRightBorderColor(HSSFColor.BLACK.index);

			csBody2.setWrapText(true); // 自动换行

			// 设置这些样式
			csBody.setWrapText(true); // 自动换行

			// 正文字体
			HSSFFont fBody = wb.createFont();
			fBody.setFontName("微软雅黑");
			fBody.setColor(HSSFColor.BLACK.index);
			fBody.setFontHeightInPoints(Short.valueOf(String.valueOf(10)));

			// 应用字体
			csBody.setFont(fBody);

			// 样式3:合计标题样式
			HSSFCellStyle csSumTitle = wb.createCellStyle();
			csSumTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			csSumTitle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			csSumTitle.setFillForegroundColor(HSSFColor.DARK_BLUE.index);
			csSumTitle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			// 边框样式
			csSumTitle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			csSumTitle.setTopBorderColor(HSSFColor.BLACK.index);
			csSumTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			csSumTitle.setLeftBorderColor(HSSFColor.BLACK.index);
			csSumTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			csSumTitle.setBottomBorderColor(HSSFColor.BLACK.index);
			csSumTitle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			csSumTitle.setRightBorderColor(HSSFColor.BLACK.index);

			// 应用字体
			csSumTitle.setFont(fHeader);

			// 样式4:合计内容样式
			HSSFCellStyle csSumBody = wb.createCellStyle();
			csSumBody.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			csSumBody.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			csSumBody.setFillForegroundColor(HSSFColor.BRIGHT_GREEN.index);
			csSumBody.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			// 边框样式
			csSumBody.setBorderTop(HSSFCellStyle.BORDER_THIN);
			csSumBody.setTopBorderColor(HSSFColor.BLACK.index);
			csSumBody.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			csSumBody.setLeftBorderColor(HSSFColor.BLACK.index);
			csSumBody.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			csSumBody.setBottomBorderColor(HSSFColor.BLACK.index);
			csSumBody.setBorderRight(HSSFCellStyle.BORDER_THIN);
			csSumBody.setRightBorderColor(HSSFColor.BLACK.index);

			// 应用字体
			csSumBody.setFont(fBody);

			// 生成一个表格
			HSSFSheet sheet = wb.createSheet("事件信息-" + a);
			wb.setSheetName(0, "Default");
			sheet.setForceFormulaRecalculation(true);

			// 填充TOP区域
			// --------------------
			CellRangeAddress addr = new CellRangeAddress(0, 0, 0, 8);
			sheet.addMergedRegion(addr);

			// 生成一行
			row0 = sheet.createRow(iLineNumber++);
			// 应用样式
			row0.setRowStyle(csTop);
			row0.setHeightInPoints(Short.valueOf(String.valueOf(39)));

			// 填充TOP文字
			cell = row0.createCell(0);
			cell.setCellStyle(csTop);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(title + "(" + DateUtil.curDateStr8() + ")");

			// 生成一行
			row1 = sheet.createRow(iLineNumber++);
			// 应用样式
			row1.setHeightInPoints(Short.valueOf(String.valueOf(20)));

			// 填充基本信息标题

			cell = row1.createCell(iColNumber);
			cell.setCellStyle(csHeader);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			sheet.setColumnWidth(iColNumber++, (256 * 22));
			cell.setCellValue("事件级别");

			cell = row1.createCell(iColNumber);
			cell.setCellStyle(csHeader);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			sheet.setColumnWidth(iColNumber++, (256 * 22));
			cell.setCellValue("事件名称");

			cell = row1.createCell(iColNumber);
			cell.setCellStyle(csHeader);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			sheet.setColumnWidth(iColNumber++, (256 * 22));
			cell.setCellValue("事件类型");

			cell = row1.createCell(iColNumber);
			cell.setCellStyle(csHeader);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			sheet.setColumnWidth(iColNumber++, (256 * 22));
			cell.setCellValue("设备名称");

			cell = row1.createCell(iColNumber);
			cell.setCellStyle(csHeader);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			sheet.setColumnWidth(iColNumber++, (256 * 22));
			cell.setCellValue("接收时间");

			cell = row1.createCell(iColNumber);
			cell.setCellStyle(csHeader);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			sheet.setColumnWidth(iColNumber++, (256 * 22));
			cell.setCellValue("源IP");

			cell = row1.createCell(iColNumber);
			cell.setCellStyle(csHeader);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			sheet.setColumnWidth(iColNumber++, (256 * 22));
			cell.setCellValue("目标IP");

			cell = row1.createCell(iColNumber);
			cell.setCellStyle(csHeader);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			sheet.setColumnWidth(iColNumber++, (256 * 22));
			cell.setCellValue("源端口");

			cell = row1.createCell(iColNumber);
			cell.setCellStyle(csHeader);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			sheet.setColumnWidth(iColNumber++, (256 * 22));
			cell.setCellValue("目标端口");
			cell = row1.createCell(iColNumber);
			cell.setCellStyle(csHeader);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			sheet.setColumnWidth(iColNumber++, (1248 * 22));
			cell.setCellValue("原始事件");
			// 2、填充基本信息内容
			if (a == index - 1) {//
				for (int i = ((index - 1) * 65530); i < list.size(); i++) {
					try { // 生成一行
						row2 = sheet.createRow(iLineNumber++);

						// 填充内容
						iColNumber = 0;
						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(String.valueOf(list.get(i)
								.getEventsLevel()));

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(list.get(i).getEventsNameCustomd());

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(String.valueOf(list.get(i)
								.getEventsTypeCustomd()));

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(list.get(i).getEventsDevName());

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						if (list.get(i).getEventsTime() != null
								&& !"".equals(list.get(i).getEventsTime()
										.toString())) {
							cell.setCellValue(sdf.format(list.get(i)
									.getEventsTime()));
						} else {
							cell.setCellValue("");
						}

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(list.get(i).getEventsSourceAddT()
								.toString());

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(list.get(i).getEventsTargetAddT()
								.toString());

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(String.valueOf(list.get(i)
								.getEventsSourcePort()));

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(String.valueOf(list.get(i)
								.getEventsTargetPort()));
						Map map = new HashMap();
						if (StringUtil.isNotEmpty(list.get(i)
								.getRelLogTableName())) {
							map.put("tableName", "tbl_"
									+ list.get(i).getRelLogTableName()
									+ "_original_log");
						}
						if (list.get(i).getEventsLogIdentification() != 0) {
							map.put("identification", list.get(i)
									.getEventsLogIdentification());
						}
						List<OriginalEvents> originalEvents = queryEeventsManager
								.exportOriginalEvents(map);
						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						int falg = 0;
						StringBuilder sb = new StringBuilder();
						for (OriginalEvents originalEvents2 : originalEvents) {
							if (falg == 0) {
								sb.append(originalEvents2.getOriginalContent()+"\n"
										);
							} else {
								sb.append( originalEvents2.getOriginalContent()+"\n");
							}
						}
						cell.setCellValue(sb.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				for (int i = (0 + a * 65530); i < 65530 * (a + 1); i++) {
					try { // 生成一行
						row2 = sheet.createRow(iLineNumber++);

						// 填充内容
						iColNumber = 0;
						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(String.valueOf(list.get(i)
								.getEventsLevel()));

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(list.get(i).getEventsNameCustomd());

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(String.valueOf(list.get(i)
								.getEventsTypeCustomd()));

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(list.get(i).getEventsDevName());

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						if (list.get(i).getEventsTime() != null
								&& !"".equals(list.get(i).getEventsTime()
										.toString())) {
							cell.setCellValue(sdf.format(list.get(i)
									.getEventsTime()));
						} else {
							cell.setCellValue("");
						}

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(list.get(i).getEventsSourceAddT()
								.toString());

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(list.get(i).getEventsTargetAddT()
								.toString());

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(String.valueOf(list.get(i)
								.getEventsSourcePort()));

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(String.valueOf(list.get(i)
								.getEventsTargetPort()));
						Map map = new HashMap();
						if (StringUtil.isNotEmpty(list.get(i)
								.getRelLogTableName())) {
							map.put("tableName", "tbl_"
									+ list.get(i).getRelLogTableName()
									+ "_original_log");
						}
						if (list.get(i).getEventsLogIdentification() != 0) {
							map.put("identification", list.get(i)
									.getEventsLogIdentification());
						}
						List<OriginalEvents> originalEvents = queryEeventsManager
								.exportOriginalEvents(map);
						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						int falg = 0;
						StringBuilder sb = new StringBuilder();
						for (OriginalEvents originalEvents2 : originalEvents) {
							if (falg == 0) {
								sb.append(originalEvents2.getOriginalContent()+"\n"
										);
							} else {
								sb.append(originalEvents2.getOriginalContent()+"\n");
							}
						}
						cell.setCellValue(sb.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		// 填充完成，返回工作簿对象
		this.gwb = wb;
	}

	public void exportEvents(List<Events> list, String title,
			QueryEventsService queryEeventsManager) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		int index = list.size() / 65530 + 1;// 根据总数确定有多少个sheet每个sheet包含65530条记录
		for (int a = 0; a < index; a++) {// 根据sheet总数来迭代数据
			// 定义行
			HSSFRow row0 = null;
			HSSFRow row1 = null;
			HSSFRow row2 = null;

			// 定义列
			HSSFCell cell = null;
			// 记录行数
			int iLineNumber = 0;
			// 记录列数
			int iColNumber = 0;

			// 样式0：生成顶部样式
			HSSFCellStyle csTop = wb.createCellStyle();
			csTop.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			csTop.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			// TOP字体样式
			HSSFFont fTop = wb.createFont();
			fTop.setFontName("微软雅黑");
			fTop.setColor(HSSFColor.BLACK.index);
			fTop.setFontHeightInPoints(Short.valueOf(String.valueOf(16)));

			// 应用字体
			csTop.setFont(fTop);

			// 样式1：生成标题样式
			HSSFCellStyle csHeader = wb.createCellStyle();

			// 自定义标题背景色
			int[] iHeaderColor = { 23, 55, 93 };
			HSSFPalette palette = wb.getCustomPalette();
			palette.setColorAtIndex(HSSFColor.DARK_BLUE.index,
					(byte) iHeaderColor[0], (byte) iHeaderColor[1],
					(byte) iHeaderColor[2]);

			// 自定义正文背景色
			int[] iBodyColor = { 242, 242, 242 };
			palette.setColorAtIndex(HSSFColor.GREY_25_PERCENT.index,
					(byte) iBodyColor[0], (byte) iBodyColor[1],
					(byte) iBodyColor[2]);

			// 自定义统计背景色
			int[] iSumColor = { 146, 208, 80 };
			palette.setColorAtIndex(HSSFColor.BRIGHT_GREEN.index,
					(byte) iSumColor[0], (byte) iSumColor[1],
					(byte) iSumColor[2]);

			// 设置这些样式
			csHeader.setFillForegroundColor(HSSFColor.DARK_BLUE.index);
			csHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			csHeader.setWrapText(true); // 自动换行
			csHeader.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			csHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 边框样式
			csHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);
			csHeader.setTopBorderColor(HSSFColor.BLACK.index);
			csHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			csHeader.setLeftBorderColor(HSSFColor.BLACK.index);
			csHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			csHeader.setBottomBorderColor(HSSFColor.BLACK.index);
			csHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
			csHeader.setRightBorderColor(HSSFColor.BLACK.index);

			// 标题字体样式
			HSSFFont fHeader = wb.createFont();
			fHeader.setFontName("微软雅黑");
			fHeader.setColor(HSSFColor.WHITE.index);
			fHeader.setFontHeightInPoints(Short.valueOf(String.valueOf(11)));
			fHeader.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

			// 应用字体
			csHeader.setFont(fHeader);

			// 样式2:生成正文样式
			HSSFCellStyle csBody = wb.createCellStyle();
			csBody.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			csBody.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			csBody.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			csBody.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			// 边框样式
			csBody.setBorderTop(HSSFCellStyle.BORDER_THIN);
			csBody.setTopBorderColor(HSSFColor.BLACK.index);
			csBody.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			csBody.setLeftBorderColor(HSSFColor.BLACK.index);
			csBody.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			csBody.setBottomBorderColor(HSSFColor.BLACK.index);
			csBody.setBorderRight(HSSFCellStyle.BORDER_THIN);
			csBody.setRightBorderColor(HSSFColor.BLACK.index);

			// 样式custom:生成正文样式
			HSSFCellStyle csBody2 = wb.createCellStyle();
			csBody2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			csBody2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			csBody2.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			csBody2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			// 边框样式
			csBody2.setBorderTop(HSSFCellStyle.BORDER_THIN);
			csBody2.setTopBorderColor(HSSFColor.BLACK.index);
			csBody2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			csBody2.setLeftBorderColor(HSSFColor.BLACK.index);
			csBody2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			csBody2.setBottomBorderColor(HSSFColor.BLACK.index);
			csBody2.setBorderRight(HSSFCellStyle.BORDER_THIN);
			csBody2.setRightBorderColor(HSSFColor.BLACK.index);

			csBody2.setWrapText(true); // 自动换行

			// 设置这些样式
			csBody.setWrapText(true); // 自动换行

			// 正文字体
			HSSFFont fBody = wb.createFont();
			fBody.setFontName("微软雅黑");
			fBody.setColor(HSSFColor.BLACK.index);
			fBody.setFontHeightInPoints(Short.valueOf(String.valueOf(10)));

			// 应用字体
			csBody.setFont(fBody);

			// 样式3:合计标题样式
			HSSFCellStyle csSumTitle = wb.createCellStyle();
			csSumTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			csSumTitle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			csSumTitle.setFillForegroundColor(HSSFColor.DARK_BLUE.index);
			csSumTitle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			// 边框样式
			csSumTitle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			csSumTitle.setTopBorderColor(HSSFColor.BLACK.index);
			csSumTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			csSumTitle.setLeftBorderColor(HSSFColor.BLACK.index);
			csSumTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			csSumTitle.setBottomBorderColor(HSSFColor.BLACK.index);
			csSumTitle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			csSumTitle.setRightBorderColor(HSSFColor.BLACK.index);

			// 应用字体
			csSumTitle.setFont(fHeader);

			// 样式4:合计内容样式
			HSSFCellStyle csSumBody = wb.createCellStyle();
			csSumBody.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			csSumBody.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			csSumBody.setFillForegroundColor(HSSFColor.BRIGHT_GREEN.index);
			csSumBody.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			// 边框样式
			csSumBody.setBorderTop(HSSFCellStyle.BORDER_THIN);
			csSumBody.setTopBorderColor(HSSFColor.BLACK.index);
			csSumBody.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			csSumBody.setLeftBorderColor(HSSFColor.BLACK.index);
			csSumBody.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			csSumBody.setBottomBorderColor(HSSFColor.BLACK.index);
			csSumBody.setBorderRight(HSSFCellStyle.BORDER_THIN);
			csSumBody.setRightBorderColor(HSSFColor.BLACK.index);

			// 应用字体
			csSumBody.setFont(fBody);

			// 生成一个表格
			HSSFSheet sheet = wb.createSheet("事件信息-" + a);
			wb.setSheetName(0, "Default");
			sheet.setForceFormulaRecalculation(true);

			// 填充TOP区域
			// --------------------
			CellRangeAddress addr = new CellRangeAddress(0, 0, 0, 8);
			sheet.addMergedRegion(addr);

			// 生成一行
			row0 = sheet.createRow(iLineNumber++);
			// 应用样式
			row0.setRowStyle(csTop);
			row0.setHeightInPoints(Short.valueOf(String.valueOf(39)));

			// 填充TOP文字
			cell = row0.createCell(0);
			cell.setCellStyle(csTop);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(title + "(" + DateUtil.curDateStr8() + ")");

			// 生成一行
			row1 = sheet.createRow(iLineNumber++);
			// 应用样式
			row1.setHeightInPoints(Short.valueOf(String.valueOf(20)));

			// 填充基本信息标题

			cell = row1.createCell(iColNumber);
			cell.setCellStyle(csHeader);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			sheet.setColumnWidth(iColNumber++, (256 * 22));
			cell.setCellValue("事件级别");

			cell = row1.createCell(iColNumber);
			cell.setCellStyle(csHeader);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			sheet.setColumnWidth(iColNumber++, (256 * 22));
			cell.setCellValue("事件名称");

			cell = row1.createCell(iColNumber);
			cell.setCellStyle(csHeader);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			sheet.setColumnWidth(iColNumber++, (256 * 22));
			cell.setCellValue("事件类型");

			cell = row1.createCell(iColNumber);
			cell.setCellStyle(csHeader);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			sheet.setColumnWidth(iColNumber++, (256 * 22));
			cell.setCellValue("设备名称");

			cell = row1.createCell(iColNumber);
			cell.setCellStyle(csHeader);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			sheet.setColumnWidth(iColNumber++, (256 * 22));
			cell.setCellValue("接收时间");

			cell = row1.createCell(iColNumber);
			cell.setCellStyle(csHeader);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			sheet.setColumnWidth(iColNumber++, (256 * 22));
			cell.setCellValue("源IP");

			cell = row1.createCell(iColNumber);
			cell.setCellStyle(csHeader);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			sheet.setColumnWidth(iColNumber++, (256 * 22));
			cell.setCellValue("目标IP");

			cell = row1.createCell(iColNumber);
			cell.setCellStyle(csHeader);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			sheet.setColumnWidth(iColNumber++, (256 * 22));
			cell.setCellValue("源端口");

			cell = row1.createCell(iColNumber);
			cell.setCellStyle(csHeader);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			sheet.setColumnWidth(iColNumber++, (256 * 22));
			cell.setCellValue("目标端口");
			cell = row1.createCell(iColNumber);
			cell.setCellStyle(csHeader);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			sheet.setColumnWidth(iColNumber++, (1248 * 22));
			cell.setCellValue("原始事件");
			// 2、填充基本信息内容
			if (a == index - 1) {//
				for (int i = ((index - 1) * 65530); i < list.size(); i++) {
					try {
						// 生成一行
						row2 = sheet.createRow(iLineNumber++);

						// 填充内容
						iColNumber = 0;
						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(String.valueOf(list.get(i)
								.getPriority()));

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(list.get(i).getNameCustomd());

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(String.valueOf(list.get(i).getTypeCustomd()));

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(list.get(i).getCustoms5());

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						if (list.get(i).getReceptTimes() != null
								&& !"".equals(list.get(i).getReceptTimes()
										.toString())) {
							cell.setCellValue(list.get(i).getReceptTimes());
						} else {
							cell.setCellValue("");
						}

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(list.get(i).getsAdd().toString());

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(list.get(i).gettAdd().toString());

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(String
								.valueOf(list.get(i).getsPort()));

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(String
								.valueOf(list.get(i).getdPort()));
						Map map = new HashMap();
						if (StringUtil.isNotEmpty(list.get(i).getCustoms1())) {
							map.put("tableName", "tbl_"
									+ list.get(i).getCustoms1()
									+ "_original_log");
						}
						if (list.get(i).getIdentification() != 0) {
							map.put("identification", list.get(i)
									.getIdentification());
						}
						List<OriginalEvents> originalEvents = queryEeventsManager
								.exportOriginalEvents(map);
						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						int falg = 0;
						StringBuilder sb = new StringBuilder();
						for (OriginalEvents originalEvents2 : originalEvents) {
							if (falg == 0) {
								sb.append(originalEvents2.getOriginalContent()+"\n"
										);
							} else {
								sb.append(originalEvents2.getOriginalContent()+"\n");
							}
						}
						cell.setCellValue(sb.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				for (int i = (0 + a * 65530); i < 65530 * (a + 1); i++) {
					try { // 生成一行
							// 生成一行
						row2 = sheet.createRow(iLineNumber++);

						// 填充内容
						iColNumber = 0;
						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(String.valueOf(list.get(i)
								.getPriority()));

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(list.get(i).getNameCustomd());

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(String.valueOf(list.get(i).getTypeCustomd()));

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(list.get(i).getCustoms5());

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						if (list.get(i).getReceptTimes() != null
								&& !"".equals(list.get(i).getReceptTimes()
										.toString())) {
							cell.setCellValue(list.get(i).getReceptTimes());
						} else {
							cell.setCellValue("");
						}

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(list.get(i).getsAdd().toString());

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(list.get(i).gettAdd().toString());

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(String
								.valueOf(list.get(i).getsPort()));

						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(String
								.valueOf(list.get(i).getdPort()));
						Map map = new HashMap();
						if (StringUtil.isNotEmpty(list.get(i).getCustoms1())) {
							map.put("tableName", "tbl_"
									+ list.get(i).getCustoms1()
									+ "_original_log");
						}
						if (list.get(i).getIdentification() != 0) {
							map.put("identification", list.get(i)
									.getIdentification());
						}
						List<OriginalEvents> originalEvents = queryEeventsManager
								.exportOriginalEvents(map);
						cell = row2.createCell(iColNumber++);
						cell.setCellStyle(csBody);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						int falg = 0;
						StringBuilder sb = new StringBuilder();
						for (OriginalEvents originalEvents2 : originalEvents) {
							if (falg == 0) {
								sb.append(originalEvents2.getOriginalContent()+"\n"
										);
							} else {
								sb.append(originalEvents2.getOriginalContent()+"\n");
							}
						}
						cell.setCellValue(sb.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		// 2、填充基本信息内容
		for (int i = 0; i < list.size(); i++) {

		}

		// 填充完成，返回工作簿对象
		this.gwb = wb;
	}

	public HSSFWorkbook getGwb() {
		return gwb;
	}

	public void setGwb(HSSFWorkbook gwb) {
		this.gwb = gwb;
	}

	public void exportEvents12(QueryEventsService queryEeventsManager,
			String conditions, String title, String assetIds, String ids) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();

		List<List<Events>> allList = queryEeventsManager
				.queryCustomEventsRules1(conditions, assetIds, ids);

		for (int a = 0; a < allList.size(); a++) {
			// 定义行
			List<Events> list = allList.get(a);

			if (list.size() > 0) {
				int index = list.size() / 65530 + 1;// 根据总数确定有多少个sheet每个sheet包含65530条记录
				for (int i = 0; i < index; i++) {// 根据sheet总数来迭代数据
					HSSFRow row0 = null;
					HSSFRow row1 = null;
					HSSFRow row2 = null;

					// 定义列
					HSSFCell cell = null;
					// 记录行数
					int iLineNumber = 0;
					// 记录列数
					int iColNumber = 0;

					// 样式0：生成顶部样式
					HSSFCellStyle csTop = wb.createCellStyle();
					csTop.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
					csTop.setAlignment(HSSFCellStyle.ALIGN_CENTER);

					// TOP字体样式
					HSSFFont fTop = wb.createFont();
					fTop.setFontName("微软雅黑");
					fTop.setColor(HSSFColor.BLACK.index);
					fTop.setFontHeightInPoints(Short.valueOf(String.valueOf(16)));

					// 应用字体
					csTop.setFont(fTop);

					// 样式1：生成标题样式
					HSSFCellStyle csHeader = wb.createCellStyle();

					// 自定义标题背景色
					int[] iHeaderColor = { 23, 55, 93 };
					HSSFPalette palette = wb.getCustomPalette();
					palette.setColorAtIndex(HSSFColor.DARK_BLUE.index,
							(byte) iHeaderColor[0], (byte) iHeaderColor[1],
							(byte) iHeaderColor[2]);

					// 自定义正文背景色
					int[] iBodyColor = { 242, 242, 242 };
					palette.setColorAtIndex(HSSFColor.GREY_25_PERCENT.index,
							(byte) iBodyColor[0], (byte) iBodyColor[1],
							(byte) iBodyColor[2]);

					// 自定义统计背景色
					int[] iSumColor = { 146, 208, 80 };
					palette.setColorAtIndex(HSSFColor.BRIGHT_GREEN.index,
							(byte) iSumColor[0], (byte) iSumColor[1],
							(byte) iSumColor[2]);

					// 设置这些样式
					csHeader.setFillForegroundColor(HSSFColor.DARK_BLUE.index);
					csHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					csHeader.setWrapText(true); // 自动换行
					csHeader.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
					csHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					// 边框样式
					csHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);
					csHeader.setTopBorderColor(HSSFColor.BLACK.index);
					csHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					csHeader.setLeftBorderColor(HSSFColor.BLACK.index);
					csHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					csHeader.setBottomBorderColor(HSSFColor.BLACK.index);
					csHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
					csHeader.setRightBorderColor(HSSFColor.BLACK.index);

					// 标题字体样式
					HSSFFont fHeader = wb.createFont();
					fHeader.setFontName("微软雅黑");
					fHeader.setColor(HSSFColor.WHITE.index);
					fHeader.setFontHeightInPoints(Short.valueOf(String
							.valueOf(11)));
					fHeader.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

					// 应用字体
					csHeader.setFont(fHeader);

					// 样式2:生成正文样式
					HSSFCellStyle csBody = wb.createCellStyle();
					csBody.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
					csBody.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					csBody.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
					csBody.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					// 边框样式
					csBody.setBorderTop(HSSFCellStyle.BORDER_THIN);
					csBody.setTopBorderColor(HSSFColor.BLACK.index);
					csBody.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					csBody.setLeftBorderColor(HSSFColor.BLACK.index);
					csBody.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					csBody.setBottomBorderColor(HSSFColor.BLACK.index);
					csBody.setBorderRight(HSSFCellStyle.BORDER_THIN);
					csBody.setRightBorderColor(HSSFColor.BLACK.index);

					// 样式custom:生成正文样式
					HSSFCellStyle csBody2 = wb.createCellStyle();
					csBody2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
					csBody2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
					csBody2.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
					csBody2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					// 边框样式
					csBody2.setBorderTop(HSSFCellStyle.BORDER_THIN);
					csBody2.setTopBorderColor(HSSFColor.BLACK.index);
					csBody2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					csBody2.setLeftBorderColor(HSSFColor.BLACK.index);
					csBody2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					csBody2.setBottomBorderColor(HSSFColor.BLACK.index);
					csBody2.setBorderRight(HSSFCellStyle.BORDER_THIN);
					csBody2.setRightBorderColor(HSSFColor.BLACK.index);

					csBody2.setWrapText(true); // 自动换行

					// 设置这些样式
					csBody.setWrapText(true); // 自动换行

					// 正文字体
					HSSFFont fBody = wb.createFont();
					fBody.setFontName("微软雅黑");
					fBody.setColor(HSSFColor.BLACK.index);
					fBody.setFontHeightInPoints(Short.valueOf(String
							.valueOf(10)));

					// 应用字体
					csBody.setFont(fBody);

					// 样式3:合计标题样式
					HSSFCellStyle csSumTitle = wb.createCellStyle();
					csSumTitle
							.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
					csSumTitle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
					csSumTitle
							.setFillForegroundColor(HSSFColor.DARK_BLUE.index);
					csSumTitle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					// 边框样式
					csSumTitle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					csSumTitle.setTopBorderColor(HSSFColor.BLACK.index);
					csSumTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					csSumTitle.setLeftBorderColor(HSSFColor.BLACK.index);
					csSumTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					csSumTitle.setBottomBorderColor(HSSFColor.BLACK.index);
					csSumTitle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					csSumTitle.setRightBorderColor(HSSFColor.BLACK.index);

					// 应用字体
					csSumTitle.setFont(fHeader);

					// 样式4:合计内容样式
					HSSFCellStyle csSumBody = wb.createCellStyle();
					csSumBody
							.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
					csSumBody.setAlignment(HSSFCellStyle.ALIGN_LEFT);
					csSumBody
							.setFillForegroundColor(HSSFColor.BRIGHT_GREEN.index);
					csSumBody.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					// 边框样式
					csSumBody.setBorderTop(HSSFCellStyle.BORDER_THIN);
					csSumBody.setTopBorderColor(HSSFColor.BLACK.index);
					csSumBody.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					csSumBody.setLeftBorderColor(HSSFColor.BLACK.index);
					csSumBody.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					csSumBody.setBottomBorderColor(HSSFColor.BLACK.index);
					csSumBody.setBorderRight(HSSFCellStyle.BORDER_THIN);
					csSumBody.setRightBorderColor(HSSFColor.BLACK.index);

					// 应用字体

					// List<Events> list = sr.getList();

					HSSFSheet sheet = wb.createSheet(list.get(0).getCustoms1()
							+ "-" + i);
					// 生成一个表格
					// sheet = wb.createSheet("事件信息"+(index+1));
					sheet.setForceFormulaRecalculation(true);

					// 填充TOP区域
					// --------------------
					CellRangeAddress addr = new CellRangeAddress(0, 0, 0, 8);
					sheet.addMergedRegion(addr);

					// 生成一行
					row0 = sheet.createRow(iLineNumber++);
					// 应用样式
					row0.setRowStyle(csTop);
					row0.setHeightInPoints(Short.valueOf(String.valueOf(39)));

					// 填充TOP文字
					cell = row0.createCell(0);
					cell.setCellStyle(csTop);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(title + "(" + DateUtil.curDateStr8()
							+ ")");

					// 生成一行
					row1 = sheet.createRow(iLineNumber++);
					// 应用样式
					row1.setHeightInPoints(Short.valueOf(String.valueOf(20)));

					// 填充基本信息标题

					cell = row1.createCell(iColNumber);
					cell.setCellStyle(csHeader);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					sheet.setColumnWidth(iColNumber++, (256 * 22));
					cell.setCellValue("事件级别");

					cell = row1.createCell(iColNumber);
					cell.setCellStyle(csHeader);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					sheet.setColumnWidth(iColNumber++, (256 * 22));
					cell.setCellValue("事件名称");

					cell = row1.createCell(iColNumber);
					cell.setCellStyle(csHeader);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					sheet.setColumnWidth(iColNumber++, (256 * 22));
					cell.setCellValue("事件类型");

					cell = row1.createCell(iColNumber);
					cell.setCellStyle(csHeader);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					sheet.setColumnWidth(iColNumber++, (256 * 22));
					cell.setCellValue("设备名称");

					cell = row1.createCell(iColNumber);
					cell.setCellStyle(csHeader);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					sheet.setColumnWidth(iColNumber++, (256 * 22));
					cell.setCellValue("接收时间");

					cell = row1.createCell(iColNumber);
					cell.setCellStyle(csHeader);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					sheet.setColumnWidth(iColNumber++, (256 * 22));
					cell.setCellValue("源IP");

					cell = row1.createCell(iColNumber);
					cell.setCellStyle(csHeader);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					sheet.setColumnWidth(iColNumber++, (256 * 22));
					cell.setCellValue("目标IP");

					cell = row1.createCell(iColNumber);
					cell.setCellStyle(csHeader);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					sheet.setColumnWidth(iColNumber++, (256 * 22));
					cell.setCellValue("源端口");

					cell = row1.createCell(iColNumber);
					cell.setCellStyle(csHeader);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					sheet.setColumnWidth(iColNumber++, (256 * 22));
					cell.setCellValue("目标端口");
					cell = row1.createCell(iColNumber);
					cell.setCellStyle(csHeader);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					sheet.setColumnWidth(iColNumber++, (1248 * 22));
					cell.setCellValue("原始事件");
					// 2、填充基本信息内容
					if (i == index - 1) {//
						for (int j = ((index - 1) * 65530); j < list.size(); j++) {// 如果就一个sheet
																					// 变量j=0，直接遍历数据。如果是最后一个sheet，变量j=之前已经遍历过的总数+1下标开始
							try { // 生成一行
								row2 = sheet.createRow(iLineNumber++);

								// 填充内容
								iColNumber = 0;
								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(String.valueOf(list.get(j)
										.getPriority()));

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(list.get(j).getNameCustomd());

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(String.valueOf(list.get(j)
										.getTypeCustomd()));

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(list.get(j).getCustoms5());

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								if (StringUtil.isNotEmpty(list.get(j)
										.getReceptTimes())) {
									cell.setCellValue(list.get(j)
											.getReceptTimes());
								} else {
									cell.setCellValue("");
								}

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(list.get(j).getsAdd()
										.toString());

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(list.get(j).gettAdd()
										.toString());

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(String.valueOf(list.get(j)
										.getsPort()));

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(String.valueOf(list.get(j)
										.getdPort()));
								Map map = new HashMap();
								if (StringUtil.isNotEmpty(list.get(j)
										.getCustoms1())) {
									map.put("tableName", "tbl_"
											+ list.get(j).getCustoms1()
											+ "_original_log");
								}
								if (list.get(j).getIdentification() != 0) {
									map.put("identification", list.get(j)
											.getIdentification());
								}
								List<OriginalEvents> originalEvents = queryEeventsManager
										.exportOriginalEvents(map);
								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								int falg = 0;
								StringBuilder sb = new StringBuilder();
								for (OriginalEvents originalEvents2 : originalEvents) {
									if (falg == 0) {
										sb.append(originalEvents2
												.getOriginalContent()+"\n"
												);
									} else {
										sb.append(originalEvents2
														.getOriginalContent()+"\n");
									}
								}
								cell.setCellValue(sb.toString());
							} catch (Exception e) {
								System.out.println(e);
							}
						}
					} else {
						for (int j = (0 + 65530 * i); j < 65530 * (i + 1); j++) {
							try { // 生成一行
								row2 = sheet.createRow(iLineNumber++);

								// 填充内容
								iColNumber = 0;
								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(String.valueOf(list.get(j)
										.getPriority()));

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(list.get(j).getNameCustomd());

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(String.valueOf(list.get(j)
										.getTypeCustomd()));

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(list.get(j).getCustoms5());

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								if (StringUtil.isNotEmpty(list.get(j)
										.getReceptTimes())) {
									cell.setCellValue(list.get(j)
											.getReceptTimes());
								} else {
									cell.setCellValue("");
								}

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(list.get(j).getsAdd()
										.toString());

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(list.get(j).gettAdd()
										.toString());

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(String.valueOf(list.get(j)
										.getsPort()));

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(String.valueOf(list.get(j)
										.getdPort()));
								Map map = new HashMap();
								if (StringUtil.isNotEmpty(list.get(j)
										.getCustoms1())) {
									map.put("tableName", "tbl_"
											+ list.get(j).getCustoms1()
											+ "_original_log");
								}
								if (list.get(j).getIdentification() != 0) {
									map.put("identification", list.get(j)
											.getIdentification());
								}
								List<OriginalEvents> originalEvents = queryEeventsManager
										.exportOriginalEvents(map);
								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								int falg = 0;
								StringBuilder sb = new StringBuilder();
								for (OriginalEvents originalEvents2 : originalEvents) {
									if (falg == 0) {
										sb.append(originalEvents2
												.getOriginalContent()+"\n"
												);
									} else {
										sb.append( originalEvents2
														.getOriginalContent()+"\n");
									}
								}
								cell.setCellValue(sb.toString());
							} catch (Exception e) {
								System.out.println(e);
							}
						}
					}
				}

			}

			// allList.remove(a);
		}

		// 填充完成，返回工作簿对象
		this.gwb = wb;
	}

	public void exportEventsByAsset(QueryEventsService queryEeventsManager,
			String conditions, String title, String ids) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();

		List<List<Events>> allList = queryEeventsManager.excelEventsByAsset(
				conditions, ids);

		for (int a = 0; a < allList.size(); a++) {
			// 定义行
			List<Events> list = allList.get(a);

			if (list.size() > 0) {
				int index = list.size() / 65530 + 1;// 根据总数确定有多少个sheet每个sheet包含65530条记录
				for (int i = 0; i < index; i++) {// 根据sheet总数来迭代数据
					HSSFRow row0 = null;
					HSSFRow row1 = null;
					HSSFRow row2 = null;

					// 定义列
					HSSFCell cell = null;
					// 记录行数
					int iLineNumber = 0;
					// 记录列数
					int iColNumber = 0;

					// 样式0：生成顶部样式
					HSSFCellStyle csTop = wb.createCellStyle();
					csTop.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
					csTop.setAlignment(HSSFCellStyle.ALIGN_CENTER);

					// TOP字体样式
					HSSFFont fTop = wb.createFont();
					fTop.setFontName("微软雅黑");
					fTop.setColor(HSSFColor.BLACK.index);
					fTop.setFontHeightInPoints(Short.valueOf(String.valueOf(16)));

					// 应用字体
					csTop.setFont(fTop);

					// 样式1：生成标题样式
					HSSFCellStyle csHeader = wb.createCellStyle();

					// 自定义标题背景色
					int[] iHeaderColor = { 23, 55, 93 };
					HSSFPalette palette = wb.getCustomPalette();
					palette.setColorAtIndex(HSSFColor.DARK_BLUE.index,
							(byte) iHeaderColor[0], (byte) iHeaderColor[1],
							(byte) iHeaderColor[2]);

					// 自定义正文背景色
					int[] iBodyColor = { 242, 242, 242 };
					palette.setColorAtIndex(HSSFColor.GREY_25_PERCENT.index,
							(byte) iBodyColor[0], (byte) iBodyColor[1],
							(byte) iBodyColor[2]);

					// 自定义统计背景色
					int[] iSumColor = { 146, 208, 80 };
					palette.setColorAtIndex(HSSFColor.BRIGHT_GREEN.index,
							(byte) iSumColor[0], (byte) iSumColor[1],
							(byte) iSumColor[2]);

					// 设置这些样式
					csHeader.setFillForegroundColor(HSSFColor.DARK_BLUE.index);
					csHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					csHeader.setWrapText(true); // 自动换行
					csHeader.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
					csHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					// 边框样式
					csHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);
					csHeader.setTopBorderColor(HSSFColor.BLACK.index);
					csHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					csHeader.setLeftBorderColor(HSSFColor.BLACK.index);
					csHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					csHeader.setBottomBorderColor(HSSFColor.BLACK.index);
					csHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
					csHeader.setRightBorderColor(HSSFColor.BLACK.index);

					// 标题字体样式
					HSSFFont fHeader = wb.createFont();
					fHeader.setFontName("微软雅黑");
					fHeader.setColor(HSSFColor.WHITE.index);
					fHeader.setFontHeightInPoints(Short.valueOf(String
							.valueOf(11)));
					fHeader.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

					// 应用字体
					csHeader.setFont(fHeader);

					// 样式2:生成正文样式
					HSSFCellStyle csBody = wb.createCellStyle();
					csBody.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
					csBody.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					csBody.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
					csBody.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					// 边框样式
					csBody.setBorderTop(HSSFCellStyle.BORDER_THIN);
					csBody.setTopBorderColor(HSSFColor.BLACK.index);
					csBody.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					csBody.setLeftBorderColor(HSSFColor.BLACK.index);
					csBody.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					csBody.setBottomBorderColor(HSSFColor.BLACK.index);
					csBody.setBorderRight(HSSFCellStyle.BORDER_THIN);
					csBody.setRightBorderColor(HSSFColor.BLACK.index);

					// 样式custom:生成正文样式
					HSSFCellStyle csBody2 = wb.createCellStyle();
					csBody2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
					csBody2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
					csBody2.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
					csBody2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					// 边框样式
					csBody2.setBorderTop(HSSFCellStyle.BORDER_THIN);
					csBody2.setTopBorderColor(HSSFColor.BLACK.index);
					csBody2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					csBody2.setLeftBorderColor(HSSFColor.BLACK.index);
					csBody2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					csBody2.setBottomBorderColor(HSSFColor.BLACK.index);
					csBody2.setBorderRight(HSSFCellStyle.BORDER_THIN);
					csBody2.setRightBorderColor(HSSFColor.BLACK.index);

					csBody2.setWrapText(true); // 自动换行

					// 设置这些样式
					csBody.setWrapText(true); // 自动换行

					// 正文字体
					HSSFFont fBody = wb.createFont();
					fBody.setFontName("微软雅黑");
					fBody.setColor(HSSFColor.BLACK.index);
					fBody.setFontHeightInPoints(Short.valueOf(String
							.valueOf(10)));

					// 应用字体
					csBody.setFont(fBody);

					// 样式3:合计标题样式
					HSSFCellStyle csSumTitle = wb.createCellStyle();
					csSumTitle
							.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
					csSumTitle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
					csSumTitle
							.setFillForegroundColor(HSSFColor.DARK_BLUE.index);
					csSumTitle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					// 边框样式
					csSumTitle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					csSumTitle.setTopBorderColor(HSSFColor.BLACK.index);
					csSumTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					csSumTitle.setLeftBorderColor(HSSFColor.BLACK.index);
					csSumTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					csSumTitle.setBottomBorderColor(HSSFColor.BLACK.index);
					csSumTitle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					csSumTitle.setRightBorderColor(HSSFColor.BLACK.index);

					// 应用字体
					csSumTitle.setFont(fHeader);

					// 样式4:合计内容样式
					HSSFCellStyle csSumBody = wb.createCellStyle();
					csSumBody
							.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
					csSumBody.setAlignment(HSSFCellStyle.ALIGN_LEFT);
					csSumBody
							.setFillForegroundColor(HSSFColor.BRIGHT_GREEN.index);
					csSumBody.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					// 边框样式
					csSumBody.setBorderTop(HSSFCellStyle.BORDER_THIN);
					csSumBody.setTopBorderColor(HSSFColor.BLACK.index);
					csSumBody.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					csSumBody.setLeftBorderColor(HSSFColor.BLACK.index);
					csSumBody.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					csSumBody.setBottomBorderColor(HSSFColor.BLACK.index);
					csSumBody.setBorderRight(HSSFCellStyle.BORDER_THIN);
					csSumBody.setRightBorderColor(HSSFColor.BLACK.index);

					// 应用字体

					// List<Events> list = sr.getList();

					HSSFSheet sheet = wb.createSheet(list.get(0).getCustoms1()
							+ "-" + i);
					// 生成一个表格
					// sheet = wb.createSheet("事件信息"+(index+1));
					sheet.setForceFormulaRecalculation(true);

					// 填充TOP区域
					// --------------------
					CellRangeAddress addr = new CellRangeAddress(0, 0, 0, 8);
					sheet.addMergedRegion(addr);

					// 生成一行
					row0 = sheet.createRow(iLineNumber++);
					// 应用样式
					row0.setRowStyle(csTop);
					row0.setHeightInPoints(Short.valueOf(String.valueOf(39)));

					// 填充TOP文字
					cell = row0.createCell(0);
					cell.setCellStyle(csTop);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(title + "(" + DateUtil.curDateStr8()
							+ ")");

					// 生成一行
					row1 = sheet.createRow(iLineNumber++);
					// 应用样式
					row1.setHeightInPoints(Short.valueOf(String.valueOf(20)));

					// 填充基本信息标题

					cell = row1.createCell(iColNumber);
					cell.setCellStyle(csHeader);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					sheet.setColumnWidth(iColNumber++, (256 * 22));
					cell.setCellValue("事件级别");

					cell = row1.createCell(iColNumber);
					cell.setCellStyle(csHeader);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					sheet.setColumnWidth(iColNumber++, (256 * 22));
					cell.setCellValue("事件名称");

					cell = row1.createCell(iColNumber);
					cell.setCellStyle(csHeader);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					sheet.setColumnWidth(iColNumber++, (256 * 22));
					cell.setCellValue("事件类型");

					cell = row1.createCell(iColNumber);
					cell.setCellStyle(csHeader);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					sheet.setColumnWidth(iColNumber++, (256 * 22));
					cell.setCellValue("设备名称");

					cell = row1.createCell(iColNumber);
					cell.setCellStyle(csHeader);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					sheet.setColumnWidth(iColNumber++, (256 * 22));
					cell.setCellValue("接收时间");

					cell = row1.createCell(iColNumber);
					cell.setCellStyle(csHeader);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					sheet.setColumnWidth(iColNumber++, (256 * 22));
					cell.setCellValue("源IP");

					cell = row1.createCell(iColNumber);
					cell.setCellStyle(csHeader);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					sheet.setColumnWidth(iColNumber++, (256 * 22));
					cell.setCellValue("目标IP");

					cell = row1.createCell(iColNumber);
					cell.setCellStyle(csHeader);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					sheet.setColumnWidth(iColNumber++, (256 * 22));
					cell.setCellValue("源端口");

					cell = row1.createCell(iColNumber);
					cell.setCellStyle(csHeader);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					sheet.setColumnWidth(iColNumber++, (256 * 22));
					cell.setCellValue("目标端口");
					cell = row1.createCell(iColNumber);
					cell.setCellStyle(csHeader);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					sheet.setColumnWidth(iColNumber++, (1248 * 22));
					cell.setCellValue("原始事件");
					// 2、填充基本信息内容
					if (i == index - 1) {//
						for (int j = ((index - 1) * 65530); j < list.size(); j++) {// 如果就一个sheet
																					// 变量j=0，直接遍历数据。如果是最后一个sheet，变量j=之前已经遍历过的总数+1下标开始
							try { // 生成一行
								row2 = sheet.createRow(iLineNumber++);

								// 填充内容
								iColNumber = 0;
								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(String.valueOf(list.get(j)
										.getPriority()));

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(list.get(j).getNameCustomd());

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(String.valueOf(list.get(j)
										.getTypeCustomd()));

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(list.get(j).getCustoms5());

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								if (StringUtil.isNotEmpty(list.get(j)
										.getReceptTimes())) {
									cell.setCellValue(list.get(j)
											.getReceptTimes());
								} else {
									cell.setCellValue("");
								}

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(list.get(j).getsAdd()
										.toString());

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(list.get(j).gettAdd()
										.toString());

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(String.valueOf(list.get(j)
										.getsPort()));

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(String.valueOf(list.get(j)
										.getdPort()));
								Map map = new HashMap();
								if (StringUtil.isNotEmpty(list.get(j)
										.getCustoms1())) {
									map.put("tableName", "tbl_"
											+ list.get(j).getCustoms1()
											+ "_original_log");
								}
								if (list.get(j).getIdentification() != 0) {
									map.put("identification", list.get(j)
											.getIdentification());
								}
								List<OriginalEvents> originalEvents = queryEeventsManager
										.exportOriginalEvents(map);
								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								int falg = 0;
								StringBuilder sb = new StringBuilder();
								for (OriginalEvents originalEvents2 : originalEvents) {
									if (falg == 0) {
										sb.append(originalEvents2
												.getOriginalContent()+"\n"
												);
									} else {
										sb.append(originalEvents2
														.getOriginalContent()+"\n");
									}
								}
								cell.setCellValue(sb.toString());
							} catch (Exception e) {
								System.out.println(e);
							}
						}
					} else {
						for (int j = (0 + 65530 * i); j < 65530 * (i + 1); j++) {
							try { // 生成一行
								row2 = sheet.createRow(iLineNumber++);

								// 填充内容
								iColNumber = 0;
								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(String.valueOf(list.get(j)
										.getPriority()));

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(list.get(j).getNameCustomd());

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(String.valueOf(list.get(j)
										.getTypeCustomd()));

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(list.get(j).getCustoms5());

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								if (StringUtil.isNotEmpty(list.get(j)
										.getReceptTimes())) {
									cell.setCellValue(list.get(j)
											.getReceptTimes());
								} else {
									cell.setCellValue("");
								}

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(list.get(j).getsAdd()
										.toString());

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(list.get(j).gettAdd()
										.toString());

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(String.valueOf(list.get(j)
										.getsPort()));

								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								cell.setCellValue(String.valueOf(list.get(j)
										.getdPort()));
								Map map = new HashMap();
								if (StringUtil.isNotEmpty(list.get(j)
										.getCustoms1())) {
									map.put("tableName", "tbl_"
											+ list.get(j).getCustoms1()
											+ "_original_log");
								}
								if (list.get(j).getIdentification() != 0) {
									map.put("identification", list.get(j)
											.getIdentification());
								}
								List<OriginalEvents> originalEvents = queryEeventsManager
										.exportOriginalEvents(map);
								cell = row2.createCell(iColNumber++);
								cell.setCellStyle(csBody);
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								int falg = 0;
								StringBuilder sb = new StringBuilder();
								for (OriginalEvents originalEvents2 : originalEvents) {
									if (falg == 0) {
										sb.append(originalEvents2
												.getOriginalContent()+"\n"
												);
									} else {
										sb.append(originalEvents2
														.getOriginalContent()+"\n");
									}
								}
								cell.setCellValue(sb.toString());
							} catch (Exception e) {
								System.out.println(e);
							}
						}
					}
				}
			}

			// allList.remove(a);
		}

		// 填充完成，返回工作簿对象
		this.gwb = wb;
	}

	/*
	 * public static void main(String[] args) { int []
	 * array={1,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17}; int index =
	 * array.length/ 3+ 1; for (int i = 0; i <index; i++) { if (i==(index-1)) {
	 * for (int j = ((index-1)*3); j < array.length; j++) {
	 * System.err.println(array[j]); } }else{ for (int j = (0+3*i); j < 3*(i+1);
	 * j++) { System.out.println(array[j]); } } } }
	 */
}
