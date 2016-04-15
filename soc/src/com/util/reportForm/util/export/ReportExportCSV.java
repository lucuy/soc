package com.util.reportForm.util.export;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

/**
 * 导出csv格式
 * 
 * @author zsa
 * 
 */
public class ReportExportCSV {
	/**
	 * csv
	 * 
	 * @param list
	 * @param path
	 */
	public void ExportCSV(List list, String path) {
		try {
			File file = new File(path);
			FileWriter fw = new FileWriter(file);
			PrintWriter bw = new PrintWriter(fw);
			int[] leng = this.getLengths(list);
			for (int i = 0; i < list.size(); i++) {
				List it = (List) list.get(i);
				bw.println(this.getLine(it, leng));
			}
			bw.flush();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int[] getLengths(List list) {
		int[] lengths = new int[((List) list.get(0)).size()];
		for (int i = 0; i < lengths.length; i++) {
			lengths[i] = 0;
			for (int j = 0; j < list.size(); j++) {
				List it = (List) list.get(j);
				if (it.get(i) != null) {
					if (it.get(i).toString().getBytes().length > lengths[i]) {
						lengths[i] = it.get(i).toString().getBytes().length;
					}
				}
			}
			lengths[i] = lengths[i] + 3;
		}
		return lengths;
	}

	// dgf update 得到每一行的内容,修改原因，原来的增加$,没有必要，浪费内存和性能
	public String getLine(List it, int[] leng) {
		StringBuffer lines = new StringBuffer();
		for (int i = 0; i < leng.length; i++) {
			if (it.get(i) != null) {
				lines.append(it.get(i));
				for (int j = it.get(i).toString().getBytes().length; j < leng[i]; j++) {
					lines.append(" ");
				}
			} else {
				for (int j = 0; j < leng[i]; j++) {
					lines.append(" ");
				}
			}
		}
		return lines.toString();
	}

}
