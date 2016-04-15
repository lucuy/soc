package com.util.reportForm.util.export;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class RrfDocument {
	long[] chapter_num = { 0, 0 }; // 标题1、标题2数字
	// String chcode="\\hich\\dbch\\loch\\f17";
	String chcode = "\\f17";

	public RrfDocument() {
	}

	// 标题1：标题名称，标题层数，标题号
	public String Chapter(String str) {
		chapter_num[0]++;
		chapter_num[1] = 0;
		return "\\par \\s1\\ql "
				+ PeportChunk(chapter_num[0] + ". " + str,
						null, 16, "B") + "\\par \\pard\\plain ";
	}

	// 标题2
	public String Section(String str) {
		chapter_num[1]++;
		return "\\par \\s2\\ql "
				+ PeportChunk(chapter_num[0] + "." + chapter_num[1] + ". "
						+ str, null, 14, "B")
				+ "\\par \\pard\\plain ";
	}

	String cs1 = Gfun.tounicode("｛");
	String cs2 = Gfun.tounicode("｝");
	String cs3 = Gfun.tounicode("＼");

	public String PeportChunk(String str, ReportColor rcolor, int rsize,
			String rstyle) {
		if (str == null)
			str = "";
		if (rstyle == null)
			rstyle = "";
		String style = "{" + chcode;
		if (rcolor != null)
			style += rcolor.color;
		if (rsize > 0)
			style += "\\fs" + 2 * rsize;
		else
			style += "\\fs22";
		if (rstyle.indexOf("B") >= 0)
			style += "\\b";
		// return style+" "+Gfun.tounicode(str)+"}";
		str = Gfun.replace(str, "{", cs1);
		str = Gfun.replace(str, "}", cs2);
		str = Gfun.replace(str, "\\b", "  ");
		str = Gfun.replace(str, "\b", "  ");
		str = Gfun.replace(str, "\\n", "＼＼par ");
		str = Gfun.replace(str, "\n", "＼＼par ");
		str = Gfun.replace(str, "\\", cs3);
		str = Gfun.replace(str, "＼＼par ", "\\par ");
		return style + " " + str + "}";
	}

	public String PeportChunk(String str, ReportColor color) {
		return PeportChunk(str, color, 0, "");
	}

	public String PeportChunk(String str, int size) {
		return PeportChunk(str, null, size, "");
	}

	public String PeportChunk(String str, String style) {
		return PeportChunk(str, null, 0, style);
	}

	public String PeportChunk(String str) {
		return PeportChunk(str, null, 0, "");
	}

	// 增加段落
	public String PeportParagraph(String str, ReportColor color, int size,
			String pstyle) {
		String style = "\\ql ";
		if (pstyle == null)
			pstyle = "";
		if (pstyle.indexOf("C") >= 0)
			style = "\\qc ";
		else if (pstyle.indexOf("R") >= 0)
			style = "\\qr ";
		return "\\par " + style + PeportChunk(str, color, size, style);
	}

	public String PeportParagraph(String str, int size, String style) {
		return PeportParagraph(str, null, size, style);
	}

	public String PeportParagraph(String str, ReportColor color) {
		return PeportParagraph(str, color, 0, "");
	}

	public String PeportParagraph(String str, int size) {
		return PeportParagraph(str, null, size, "");
	}

	public String PeportParagraph(String str, String style) {
		return PeportParagraph(str, null, 0, style);
	}

	public String PeportParagraph(String str) {
		return PeportParagraph(str, null, 0, "");
	}

	// 新页
	public String NewPage() {
		return "{\\page }";
	}

	// 写图片 scale:缩放比例
	public void write_img(BufferedOutputStream fwriter, String imgfile, int scale)
			throws FileNotFoundException, IOException {
		File file_img = new File(imgfile);
		if (!file_img.exists())
			return;
		// 写文件头
	    fwriter.write(("{\\*\\shppict"+
                "{\\pict"+
                "{\\*\\picprop\\shplid1025"+
                "{\\sp{\\sn shapeType}{\\sv 75}}"+
                "{\\sp{\\sn fFlipH}{\\sv 0}}"+
                "{\\sp{\\sn fFlipV}{\\sv 0}}"+
                "{\\sp{\\sn fLine}{\\sv 0}}"+
                "{\\sp{\\sn borderTopColor}{\\sv -16777216}}"+
                "{\\sp{\\sn borderLeftColor}{\\sv -16777216}}"+
                "{\\sp{\\sn borderBottomColor}{\\sv -16777216}}"+
                "{\\sp{\\sn borderRightColor}{\\sv -16777216}}"+
                "{\\sp{\\sn fLayoutInCell}{\\sv 1}}"+
                "{\\sp{\\sn fLayoutInCell}{\\sv 1}}"+
                "}"+
                "\\picscalex"+scale+"\\picscaley"+scale+"\\piccropl0\\piccropr0\\piccropt0\\piccropb0"+
                "\\jpegblip").getBytes());
	    fwriter.write(("\\bin"+file_img.length()).getBytes());
	    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(imgfile));
	    int a = 0;
		byte b[] = new byte[1024];
		while((a = bis.read(b)) != -1){
			fwriter.write(b, 0, a);
		}
		bis.close();
		fwriter.write("}}".getBytes());
	}

	// rtf文件头

	public String ReportHead() {
		java.util.Date nowdate = new java.util.Date();
		String datestr = "\\yr" + (1900 + nowdate.getYear()) + "\\mo"
				+ (nowdate.getMonth() + 1) + "\\dy" + nowdate.getDay() + "\\hr"
				+ nowdate.getMinutes() + "\\min" + nowdate.getSeconds();
		return "{\\rtf1\\ansi\\deff0"
				+ "{\\fonttbl"
				+ "{\\f0\\fnil\\fcharset134\\fprq2{\\*\\panose 02010600030101010101}\\'cb\\'ce\\'cc\\'e5{\\*\\falt SimSun};}"
				+ "{\\f1\\fdecor Symbol;}"
				+ "{\\f2\\fswiss Helv;}"
				+ "{\\f17\\fnil\\fcharset134\\fprq2{\\*\\panose 02010600030101010101}\\'cb\\'ce\\'cc\\'e5{\\*\\falt SimSun};}"
				+ "}"
				+ "{\\colortbl;"
				+ "\\red0\\green0\\blue0;"
				+ // 黑

				"\\red255\\green0\\blue0;"
				+ // 紧急风险：c
				"\\red255\\green51\\blue204;"
				+ // 高风险：h
				"\\red255\\green153\\blue0;"
				+ // 中风险：m
				"\\red0\\green0\\blue255;"
				+ // 低风险：l
				"\\red0\\green128\\blue0;"
				+ // 信息风险：i
				"\\red217\\green217\\blue217;"
				+ // 表格灰底：bg
				"\\red255\\green255\\blue0;"
				+ "\\red255\\green255\\blue255;"
				+ // 白色
				"}"
				+ "{\\stylesheet"
				+ "{\\fs24 \\snext0Normal;}"
				+ "{\\s1\\qj \\li0\\ri0\\sb340\\sa330\\sl578\\slmult1 \\keep\\keepn\\nowidctlpar\\aspalpha\\aspnum\\faauto\\adjustright\\rin0\\lin0\\itap0 \\b\\fs44\\lang1033\\langfe2052\\kerning44\\loch\\f0\\hich\\af0\\dbch\\af17\\cgrid\\langnp1033\\langfenp2052 \\sbasedon0 \\snext0 heading 1;}"
				+ "{\\s2\\qj \\li0\\ri0\\sb260\\sa260\\sl416\\slmult1 \\keep\\keepn\\nowidctlpar\\aspalpha\\aspnum\\faauto\\adjustright\\rin0\\lin0\\itap0 \\b\\fs32\\lang1033\\langfe2052\\kerning2\\loch\\f1\\hich\\af1\\dbch\\af21\\cgrid\\langnp1033\\langfenp2052 \\sbasedon0 \\snext0 heading 2;}"
				+ "}" + "{\\info" + "{\\author rjsoft rjitop}" + "{\\creatim"
				+ datestr + "}" + "{\\version1}" + "{\\edmins0}"
				+ "{\\nofpages1}" + "{\\nofwords0}" + "{\\nofchars0}"
				+ "{\\vern8351}" + "}"
				+ "\\widoctrl\\ftnbj \\sectd\\linex0\\endnhere"
				+ "\\pard\\plain ";
	}

	// rtf文件尾

	public String ReportEnd() {
		return " \\par }";
	}
	 //设置颜色：chmlit
	private class ReportColor {
		String color = "";

		public ReportColor(String fc) {
			if ("c".equals(fc))
				color = "\\cf2";
			else if ("h".equals(fc))
				color = "\\cf3";
			else if ("m".equals(fc))
				color = "\\cf4";
			else if ("l".equals(fc))
				color = "\\cf5";
			else if ("i".equals(fc))
				color = "\\cf6";
			else if ("t".equals(fc))
				color = "\\cf7"; // 标题颜色
			else
				color = "";
		}
	}
	//表格单元格

	class ReportCell {
		boolean isimg = false; // 是否图片 Img
		String content = ""; // 内容，如果type是图片，则为图片文件名

		String bgcolor = ""; // 背景色

		String align = "\\ql"; // 对齐方式 C、R、L
		String merged = ""; // 是否和上一行合并 M. 合并：\clvmrg，不合并：\clvmgf
		int scale = 100;

		public ReportCell() {
		}

		public ReportCell(String str, String fstaly, int imgscale) {
			set_cell(str, fstaly, imgscale);
		}

		public ReportCell(String str, String fstaly) {
			set_cell(str, fstaly, 100);
		}

		public ReportCell(String str) {
			set_cell(str, "", 100);
		}
		// 设置单元格，内容，样式－－Img:图片；T:标题；C：居中，R：右对齐，M：合并

		public void set_cell(String str, String fstaly, int imgscale) {
			if (str == null)
				str = "";
			if (fstaly == null)
				fstaly = "";
			if (fstaly.indexOf("Img") >= 0)
				isimg = true;
			if (!isimg && str.indexOf(chcode) < 0)
				content = PeportChunk(str);
			else
				content = str;
			if (fstaly.indexOf("T") >= 0)
				bgcolor = "\\clcbpat7";
			else
				bgcolor = "";
			if (fstaly.indexOf("M") >= 0)
				merged = "\\clvmrg";
			else
				merged = "\\clvmgf";
			if (fstaly.indexOf("C") >= 0)
				align = "\\qc";
			else if (fstaly.indexOf("R") >= 0)
				align = "\\qr";
			scale = imgscale;
		}

		// 设置单元格

		public void set_cell(String str) {
			set_cell(str, "", 100);
		}
	}
	
	class ReportTable {
		int width = 8500; //表格总宽度

		int col_num = 1; //表格列数
		int cell_with[]; //表格各列宽度
		String trkeep = ""; //是否允许跨页断行，表格带图片的为不允许：\\trkeep，其他的允许为空
		ArrayList celllist = new ArrayList(); //要写入的单元格内容列表

		boolean border_flag = true; //是否显示边框

		public ReportTable(int c) {
			col_num = c;
			cell_with = new int[col_num];
			for (int i = 0; i < col_num; i++) {
				cell_with[i] = (i + 1) * width / col_num;
			}
		}

		//将单元格内容添加到表格

		public void add_cell(ReportCell cell) {
			celllist.add(cell);
			if (cell.isimg)
				trkeep = "\\trkeep";
		}

		public void add_cell(String cellstr) {
			ReportCell cell = new ReportCell(cellstr);
			celllist.add(cell);
		}

		//向文件中绘制表格，fwriter文件，celllist单元格元素列表

		public void write_cell(BufferedOutputStream fwriter) throws IOException {
			int i = 0;
			ReportCell cell;
			while (celllist.size() > 0) {
				//不足行数，补足

				if (celllist.size() < col_num) {
					for (i = celllist.size(); i < col_num; i++)
						celllist.add(new ReportCell());
				}
				//写表格行
				fwriter.write(RowHead().getBytes("gb2312"));
				for (i = 0; i < col_num; i++) {
					cell = (ReportCell) celllist.get(i);
					fwriter.write(CellHead(cell_with[i], cell.bgcolor
							+ cell.merged).getBytes("gb2312"));
				}
				fwriter.write(CellEnd().getBytes("gb2312"));
				//表格单元格内容

				for (i = 0; i < col_num; i++) {
					cell = (ReportCell) celllist.get(i);
					fwriter.write(cell.align.getBytes("gb2312"));
					if (cell.isimg)
						write_img(fwriter, cell.content, cell.scale);//图片
					else
						fwriter.write(cell.content.getBytes("gb2312")); //文本
					fwriter.write("\\cell ".getBytes("gb2312"));
				}
				fwriter.write(RowEnd().getBytes("gb2312"));
				for (i = 0; i < col_num; i++)
					celllist.remove(0);
			}
			trkeep = "";
		}

		//设置表格单元格宽度，按百分比。例"20,20,60"
		public void set_width(String w) {
			border_flag = true;
			if (w == null || "".equals(w))
				return;
			String tw[] = Gfun.split(w, ",");
			if (tw.length != col_num) {
				col_num = tw.length;
				cell_with = new int[col_num];
			}
			for (int i = 0; i < col_num; i++) {
				cell_with[i] = width * Integer.parseInt(tw[i]) / 100;
				if (i > 0)
					cell_with[i] += cell_with[i - 1];
			}
		}

		//设置表格单元格宽度，按百分比。例"20,20,60"
		public void set_noborder() {
			border_flag = false;
		}

		//表格单元格头，几个单元格连续写几个，style格式
		public String CellHead(int cellx, String style) {
			String bdcorder = "1";
			if (!border_flag)
				bdcorder = "9";
			return "\\clvertalt\\clbrdrt\\brdrs\\brdrw10\\brdrcf" + bdcorder
					+ "\\clbrdrl\\brdrs\\brdrw10\\brdrcf" + bdcorder
					+ "\\clbrdrb\\brdrs\\brdrw10\\brdrcf" + bdcorder
					+ "\\clbrdrr\\brdrs\\brdrw10\\brdrcf" + bdcorder + style
					+ "\\cltxlrtb" + "\\cellx" + cellx + " ";
		}

		//表格单元格尾，所有单位格头结束后写

		public String CellEnd() {
			return "\\pard \\nowidctlpar\\intbl\\faauto ";
		}

		//表格行头
		public String RowHead() {
			return "\\trowd "
					+ "\\trqc\\trgaph10\\trleft-20\\trbrdrt\\brdrs\\brdrw20\\brdrcf1"
					+ trkeep
					+ " "
					+ "\\trbrdrl\\brdrs\\brdrw20\\brdrcf1 \\trbrdrb\\brdrs\\brdrw20\\brdrcf1 "
					+ "\\trbrdrr\\brdrs\\brdrw20\\brdrcf1 \\trbrdrh\\brdrs\\brdrw20\\brdrcf1 "
					+ "\\trbrdrv\\brdrs\\brdrw20\\brdrcf1 "
					+ "\\trpaddl20\\trpaddr20\\trpaddfl3\\trpaddfr3 ";
		}

		//表格行尾
		public String RowEnd() {
			return "\\pard \\nowidctlpar\\intbl\\aspalpha\\aspnum\\faauto\\adjustright \\row \\pard\\plain ";
		}
	}

}
