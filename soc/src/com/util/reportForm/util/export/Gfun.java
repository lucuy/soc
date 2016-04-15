package com.util.reportForm.util.export;

import java.io.File;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


public class Gfun {
	private static Logger log = Logger.getLogger(Gfun.class.getName());

	public static String china_charset = "UTF-8";

	public String userlimit = "";

	public void set_limit(String limit) {
		userlimit = limit;
	}

	public String get_limit() {
		return userlimit;
	}

	public static String strnull(String strn) {
		if (strn == null) {
			strn = "";
		}
		return strn.trim();
	}

	public static String strnull(Date strn) {
		String str = "";
		if (strn == null) {
			str = "0000-00-00 00:00:00";
		} else {
			str = strn.toString();
		}
		return str;
	}

	public static String strnull(BigDecimal strn) {
		String str = "";
		if (strn == null) {
			str = "0";
		} else {
			str = strn.toString();
		}
		return str;
	}

	public static String[] strnull(String[] strIn) {
		String[] strOut = strIn;
		int i = 0;
		if (strIn == null) {
			return null;
		}
		for (i = 0; i < strIn.length; i++) {
			if (strIn[i] == null || strIn[i].trim().equals("")) {
				strOut[i] = "";
			}
		}
		return strOut;
	}

	public static String strzero(String strz) {
		if (strz == null) {
			strz = "0";
		} else if (strz.trim().length() <= 0) {
			strz = "0";
		}
		return strz;
	}

	public static Timestamp toTimestamp(String str)
			throws IllegalArgumentException {
		try {
			if (str == null || str.trim().equals("")) {
				return null;
			}
			String dt = str.trim();
			int len = dt.length();
			if (len == 10) {
				dt = dt.concat(" 00:00:00.0");
			} else if (len == 16) {
				dt = dt.concat(":00.0");
			} else if (len == 19) {
				dt = dt.concat(".0");
			} else if (len == 8) {
				dt = String.valueOf(new StringBuffer("20").append(dt).append(
						" 00:00:00.0"));
			} else if (len == 14) {
				dt = String.valueOf(new StringBuffer("20").append(dt).append(
						":00.0"));
			} else if (len == 17) {
				dt = String.valueOf(new StringBuffer("20").append(dt).append(
						".0"));
			} else {
				dt = "0000-00-00 00:00:00.0";
			}
			return Timestamp.valueOf(dt);
		} catch (Exception e) {
			return null;
		}

	}

	public String cutzero(String zstr) {
		int i = 0;
		int j = 0;
		int count = zstr.length();
		for (i = count - 1; i >= 0 && zstr.charAt(i) == '0'; i--) {
			j = i - 1;
		}
		String str1 = zstr.substring(0, j + 1);
		return str1;
	}

	public static int getpage(String btn, int curpage, int maxpage) {
		if (btn != null) {
			if (btn.equals("\u4e0a\u4e00\u9875") && curpage >= 2) {
				curpage--;
			} else if (btn.equals("\u4e0b\u4e00\u9875") && curpage < maxpage) {
				curpage++;
			} else if (btn.equals("\u9996\u9875")) {
				curpage = 1;
			} else if (btn.equals("\u5c3e\u9875")) {
				curpage = maxpage;
			}
		}
		return curpage;
	}

	public static int getpage(String btn, int curpage, int maxpage,
			String topage) {
		if (btn != null) {
			if (btn.equals("\u4e0a\u4e00\u9875") && curpage >= 2) {
				curpage--;
			} else if (btn.equals("\u4e0b\u4e00\u9875") && curpage < maxpage) {
				curpage++;
			} else if (btn.equals("\u9996\u9875")) {
				curpage = 1;
			} else if (btn.equals("\u5c3e\u9875")) {
				curpage = maxpage;
			} else if (btn.equals("\u8f6c \u5230") && topage != null
					&& topage.length() > 0) {
				curpage = (int) Float.parseFloat(topage);
			}
		}
		if (curpage > maxpage) {
			curpage = maxpage;
		}
		if (curpage < 1) {
			curpage = 1;
		}
		return curpage;
	}

	public static String tounicode(String strIn) {
		String strOut = strIn;
		if (strIn == null || strIn.trim().equals("")) {
			return strIn;
		}
		try {
			strOut = new String(strIn.getBytes(china_charset), "ISO8859_1");
			
//			String str = new String(strOut.getBytes("ISO8859_1"),china_charset);
//			//System.out.println("--->"+str);
		} catch (Exception exception) {

		}
		
		return strOut;

	}

	public static String gbk2iso(String strIn) {
		if (strIn == null) {
			return "";
		}
		String strOut = "";
		try {
			strOut = new String(strIn.getBytes("gb2312"), "ISO8859_1");
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		return strOut;
	}

	public static String tochinese(String strIn) {
		if (strIn == null) {
			return "";
		}
		return strIn;
		/*
		 * Properties prop = System.getProperties(); String encode =
		 * prop.getProperty("file.encoding"); String strOut = strIn; if
		 * (!encode.substring(0, 2).equals("GB")) { try { try { strOut = new
		 * String(strIn.getBytes("ISO8859_1"), "GBK"); } catch
		 * (UnsupportedEncodingException ex) { } } catch (Exception exception) { } }
		 * return strOut;
		 */
	}

	public static String[] tochinese(String[] strIn) {
		String[] strOut = strIn;
		int i = 0;
		if (strIn == null) {
			return null;
		}
		return strOut;
		/*
		 * try { for (i = 0; i < strIn.length; i++) { if (strIn[i] == null ||
		 * strIn[i].trim().equals("")) { strOut[i] = ""; } else { strOut[i] =
		 * new String(strIn[i].getBytes("ISO8859_1"), china_charset); } } }
		 * catch (Exception exception) { } return strOut;
		 */
	}

	public String cndate(String date1) {
		String str1;
		if (date1 == null) {
			str1 = "&nbsp;";
		} else {
			String year1 = date1.substring(0, 4);
			String month1 = date1.substring(5, 7);
			String day1 = date1.substring(8, 10);
			str1 = String.valueOf(new StringBuffer(year1).append("\u5e74")
					.append(month1).append("\u6708").append(day1).append(
							"\u65e5"));
		}
		return str1;
	}

	public String cndateEx(String date1) {
		String str1;
		if (strnull(date1).equals("")) {
			str1 = "&nbsp;";
		} else {
			String year1 = date1.substring(0, 4);
			String month1 = date1.substring(4, 6);
			String day1 = date1.substring(6, 8);
			str1 = String.valueOf(new StringBuffer(year1).append("\u5e74")
					.append(month1).append("\u6708").append(day1).append(
							"\u65e5"));
		}
		return str1;
	}

	public String cndateEx2(String date1) {
		String str1;
		if (strnull(date1).equals("")) {
			str1 = "&nbsp;";
		} else {
			String year1 = date1.substring(0, 4);
			String month1 = date1.substring(4, 6);
			String day1 = date1.substring(6, 8);
			str1 = String.valueOf(new StringBuffer(year1).append("-").append(
					month1).append("-").append(day1));
		}
		return str1;
	}

	public static String getDateTime() {
		String datetime = "";
		String month = "";
		String date = "";
		String hour = "";
		String min = "";
		Calendar calendar = Calendar.getInstance();
		month = String.valueOf(new StringBuffer("0").append(calendar
				.get(Calendar.MONTH) + 1));
		month = month.substring(month.length() - 2, month.length());
		date = "0".concat(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
		date = date.substring(date.length() - 2, date.length());
		hour = "0".concat(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)));
		hour = hour.substring(hour.length() - 2, hour.length());
		min = "0".concat(String.valueOf(calendar.get(Calendar.MINUTE)));
		min = min.substring(min.length() - 2, min.length());
		datetime = String.valueOf(new StringBuffer(String.valueOf(calendar
				.get(Calendar.YEAR))).append(month).append(date).append("_")
				.append(hour).append("h").append(min).append("m"));
		return datetime;
	}

	public static String getdate() {
		String datetime = "";
		String month = "";
		String date = "";
		Date now = new Date();
		Calendar calendar = new GregorianCalendar();
		month = String.valueOf(new StringBuffer("0").append(calendar
				.get(Calendar.MONTH) + 1));
		month = month.substring(month.length() - 2, month.length());
		date = "0".concat(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
		date = date.substring(date.length() - 2, date.length());
		datetime = String.valueOf(new StringBuffer(String.valueOf(calendar
				.get(Calendar.YEAR))).append(month).append(date));
		return datetime;
	}

	public String changeDate(String sdate) {
		String edate = "";
		if (strnull(sdate).equals("")) {
			return "";
		}
		for ( /**/; sdate.indexOf("-") > 0; sdate = sdate.substring(sdate
				.indexOf("-") + 1)) {
			edate = edate.concat(sdate.substring(0, sdate.indexOf("-")));
		}
		edate = edate.concat(sdate);
		return edate;
	}

	public String[] changeDate(String[] sdate) {
		String[] edate = sdate;
		int i = 0;
		for (i = 0; i < edate.length; i++) {
			edate[i] = changeDate(sdate[i]);
		}
		return edate;
	}

	public String changeblank(String str) {
		str = str.replace(' ', '\ue5e5');
		str = str.replace('\'', '\uff07');
		return str;
	}

	public String get_quanxian(HttpServletRequest request, String a) {
		String[] access = request.getParameterValues(a);
		char[] limit = new char[200];
		int i = 0;
		int col = 0;
		int maxl = 0;
		if (access == null) {
			return "";
		}
		for (i = 0; i < 200; i++) {
			limit[i] = '0';
		}
		for (i = 0; i < access.length; i++) {
			if (access[i] != null) {
				col = Integer.parseInt(access[i]);
				if (maxl < col) {
					maxl = col;
				}
				limit[col - 1] = '1';
			}
		}
		String limitaccess = new String(limit, 0, maxl);
		return limitaccess;
	}

	public String get_checkvalue(HttpServletRequest request, String strname) {
		String[] array = request.getParameterValues(strname);
		String strreturn = "";
		int i = 0;
		if (array == null) {
			return "";
		}
		for (i = 0; i < array.length; i++) {
			strreturn = strreturn.concat(",".concat(array[i]));
		}
		return strreturn.substring(1);
	}

	public String get_name(String code, String type) {
		String name = "";
		String sqlstr = "";
		String err = "\u9519\u8bef\uff1a";
		if (type.equals("nodid")) {
			sqlstr = "select name from nod where id=".concat(code);
		} else if (type.equals("objectid")) {
			sqlstr = "select name from object where id=".concat(code);
		} else if (type.equals("user")) {
			sqlstr = "select user_name from User where user_id=".concat(code);
		} else {
			sqlstr = String.valueOf(new StringBuffer(
					"select name from c_code where code='").append(code)
					.append("' and type='").append(type).append("'"));
		}
		try {
			String[][] array = ShareProcedure.getResultSet(sqlstr);
			if (array != null) {
				name = array[0][0];
			} else {
				name = code;
				err = err.concat("\u6ca1\u6709\u76f8\u5e94\u8bb0\u5f55");
			}
		} catch (Exception e) {
			// //System.out.print(err.concat(e.getMessage()));
			return "�û�������";
		}
		return name;
	}

	public void shenhe_quanxian(HttpSession session,
			HttpServletResponse response, String xiangmudaima, String flag) {
		/* empty */
	}

	public boolean limit(HttpSession session, HttpServletResponse response,
			int[] limitno) throws Exception {
		try {
			int i = 0;
			String code = (String) session.getAttribute("MM_Username");
			if (code == null) {
				response.sendRedirect("restrict.jsp");
				boolean bool = false;
				return bool;
			}
			userlimit = (String) session.getAttribute("MM_UserAuthorization");
			if (userlimit == null || userlimit.equals("")) {
				response.sendRedirect("restrict.jsp");
				boolean bool = false;
				return bool;
			}
			int llen = userlimit.length();
			if (limitno == null) {
				boolean bool = true;
				return bool;
			}
			for (i = 0; i < limitno.length; i++) {
				if (limitno[i] > 0 && llen >= limitno[i]
						&& userlimit.charAt(limitno[i] - 1) != '0') {
					boolean bool = true;
					return bool;
				}
			}
			response.sendRedirect("restrict.jsp");
			boolean bool = false;
			return bool;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public boolean limit(HttpSession session, HttpServletResponse response,
			int limitno) throws Exception {
		int[] limitlist = null;
		if (limitno > 0) {
			limitlist = new int[1];
			limitlist[0] = limitno;
		}
		try {
			boolean bool = limit(session, response, limitlist);
			return bool;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public boolean limit(HttpSession session, HttpServletResponse response)
			throws Exception {
		try {
			boolean bool = limit(session, response, null);
			return bool;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public boolean limit(int limitno) {
		int llen = userlimit.length();
		if (limitno > 0 && llen >= limitno
				&& userlimit.charAt(limitno - 1) != '0') {
			return true;
		}
		return false;
	}

	public static String replace(String source, String str_old, String str_new) {
		String str = "";
		int pos = 0;
		if (source == null) {
			return "";
		}
		str = source;
		for (pos = str.indexOf(str_old); pos >= 0; pos = str.indexOf(str_old,
				pos + str_new.length())) {
			if (pos < str.length()) {
				str = (String.valueOf(new StringBuffer(str.substring(0, pos))
						.append(str_new).append(
								str.substring(pos + str_old.length()))));
			} else {
				str = str.substring(0, pos).concat(str_new);
			}
		}
		return str;
	}

	public static String joinstr(String[] str, String br) {
		String results = "";
		StringBuffer stra = new StringBuffer();
		if (str == null) {
			return "";
		}
		for (int i = 0; i < str.length; i++) {
			stra.append(str[i].concat(br));
		}
		results = stra.toString();
		return results;
	}

	public static String[] split(String str, String space) {
		Vector vectors = new Vector();
		String temp = "";
		String[] results = null;
		for (int i = str.indexOf(space); i > 0; i = str.indexOf(space)) {
			temp = str.substring(0, i);
			str = str.substring(i + 1);
			vectors.addElement(temp);
		}
		if (!str.equalsIgnoreCase("")) {
			vectors.addElement(str);
		}
		results = new String[vectors.size()];
		for (int j = 0; j < vectors.size(); j++) {
			results[j] = (String) vectors.get(j);
		}
		return results;
	}

	public static String get_a_line_of_file(String file_path, int line_number) {
		File objFile = new File(file_path);
		String soft_edition = "";
		if (objFile.exists()) {
			try {
				FileReader objFileReader = new FileReader(objFile);
				for (int i = 0; i < line_number; i++) {
					soft_edition = "";
					for (int intchar = objFileReader.read(); intchar != 10
							&& intchar != -1; intchar = objFileReader.read()) {
						soft_edition = soft_edition.concat(String
								.valueOf((char) intchar));
					}
				}
				objFileReader.close();
			} catch (Exception exception) {
				/* empty */
			}
			return soft_edition;
		}
		return null;
	}

	public static String scanTime() {
		String[] dayNames = new String[7];
		dayNames[0] = "\u661f\u671f\u65e5";
		dayNames[1] = "\u661f\u671f\u4e00";
		dayNames[2] = "\u661f\u671f\u4e8c";
		dayNames[3] = "\u661f\u671f\u4e09";
		dayNames[4] = "\u661f\u671f\u56db";
		dayNames[5] = "\u661f\u671f\u4e94";
		dayNames[6] = "\u661f\u671f\u516d";
		Date newDate = new Date();
		Calendar calendar = new GregorianCalendar();
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);
		String timeValue = hours >= 12 ? " \u4e0b\u5348 " : " \u4e0a\u5348 ";
		timeValue = timeValue.concat(" ".concat(String
				.valueOf(hours > 12 ? hours - 12 : hours)));
		timeValue = timeValue.concat((minutes < 10 ? ":0" : ":").concat(String
				.valueOf(minutes)));
		timeValue = timeValue.concat((seconds < 10 ? ":0" : ":").concat(String
				.valueOf(seconds)));
		int month = calendar.get(Calendar.MONTH) + 1;
		int date = calendar.get(Calendar.DAY_OF_WEEK);
		String newStr = String
				.valueOf(new StringBuffer(String.valueOf(calendar
						.get(Calendar.YEAR))).append("\u5e74").append(
						month < 10 ? "0" : "").append(month).append("\u6708")
						.append(date < 10 ? "0" : "").append(date).append(
								"\u65e5 ").append(
								dayNames[calendar.get(Calendar.DAY_OF_WEEK)])
						.append(timeValue));
		return newStr;
	}

	public static char[] HexToBin(String str_h) {
		if (str_h == null || "".equals(str_h)) {
			return null;
		}
		int length = str_h.length();
		char[] bin_char = new char[length * 4];
		int m = 0;
		int t = 0;
		for (m = 0; m < length; m++) {
			switch (str_h.charAt(m)) {
			case '0':
				bin_char[t++] = '0';
				bin_char[t++] = '0';
				bin_char[t++] = '0';
				bin_char[t++] = '0';
				break;
			case '1':
				bin_char[t++] = '0';
				bin_char[t++] = '0';
				bin_char[t++] = '0';
				bin_char[t++] = '1';
				break;
			case '2':
				bin_char[t++] = '0';
				bin_char[t++] = '0';
				bin_char[t++] = '1';
				bin_char[t++] = '0';
				break;
			case '3':
				bin_char[t++] = '0';
				bin_char[t++] = '0';
				bin_char[t++] = '1';
				bin_char[t++] = '1';
				break;
			case '4':
				bin_char[t++] = '0';
				bin_char[t++] = '1';
				bin_char[t++] = '0';
				bin_char[t++] = '0';
				break;
			case '5':
				bin_char[t++] = '0';
				bin_char[t++] = '1';
				bin_char[t++] = '0';
				bin_char[t++] = '1';
				break;
			case '6':
				bin_char[t++] = '0';
				bin_char[t++] = '1';
				bin_char[t++] = '1';
				bin_char[t++] = '0';
				break;
			case '7':
				bin_char[t++] = '0';
				bin_char[t++] = '1';
				bin_char[t++] = '1';
				bin_char[t++] = '1';
				break;
			case '8':
				bin_char[t++] = '1';
				bin_char[t++] = '0';
				bin_char[t++] = '0';
				bin_char[t++] = '0';
				break;
			case '9':
				bin_char[t++] = '1';
				bin_char[t++] = '0';
				bin_char[t++] = '0';
				bin_char[t++] = '1';
				break;
			case 'a':
				bin_char[t++] = '1';
				bin_char[t++] = '0';
				bin_char[t++] = '1';
				bin_char[t++] = '0';
				break;
			case 'b':
				bin_char[t++] = '1';
				bin_char[t++] = '0';
				bin_char[t++] = '1';
				bin_char[t++] = '1';
				break;
			case 'c':
				bin_char[t++] = '1';
				bin_char[t++] = '1';
				bin_char[t++] = '0';
				bin_char[t++] = '0';
				break;
			case 'd':
				bin_char[t++] = '1';
				bin_char[t++] = '1';
				bin_char[t++] = '0';
				bin_char[t++] = '1';
				break;
			case 'e':
				bin_char[t++] = '1';
				bin_char[t++] = '1';
				bin_char[t++] = '1';
				bin_char[t++] = '0';
				break;
			case 'f':
				bin_char[t++] = '1';
				bin_char[t++] = '1';
				bin_char[t++] = '1';
				bin_char[t++] = '1';
				break;
			}
		}
		return bin_char;
	}

	public static String HexToStr(String str_h) {
		if (str_h == null || "".equals(str_h)) {
			return "";
		}
		int length = str_h.length();
		String str = "";
		int m = 0;
		int t = 0;
		for (m = 0; m < length; m++) {
			switch (str_h.charAt(m)) {
			case '0':
				t += 4;
				break;
			case '1':
				str = str.concat(String.valueOf(new StringBuffer(",")
						.append(t + 3)));
				t += 4;
				break;
			case '2':
				str = str.concat(String.valueOf(new StringBuffer(",")
						.append(t + 2)));
				t += 4;
				break;
			case '3':
				str = str.concat(String.valueOf(new StringBuffer(",").append(
						t + 2).append(",").append(t + 3)));
				t += 4;
				break;
			case '4':
				str = str.concat(String.valueOf(new StringBuffer(",")
						.append(t + 1)));
				t += 4;
				break;
			case '5':
				str = str.concat(String.valueOf(new StringBuffer(",").append(
						t + 1).append(",").append(t + 3)));
				t += 4;
				break;
			case '6':
				str = str.concat(String.valueOf(new StringBuffer(",").append(
						t + 1).append(",").append(t + 2)));
				t += 4;
				break;
			case '7':
				str = str.concat(String.valueOf(new StringBuffer(",").append(
						t + 1).append(",").append(t + 2).append(",").append(
						t + 3)));
				t += 4;
				break;
			case '8':
				str = str.concat(",".concat(String.valueOf(t)));
				t += 4;
				break;
			case '9':
				str = str.concat(String.valueOf(new StringBuffer(",").append(t)
						.append(",").append(t + 3)));
				t += 4;
				break;
			case 'a':
				str = str.concat(String.valueOf(new StringBuffer(",").append(t)
						.append(",").append(t + 2)));
				t += 4;
				break;
			case 'b':
				str = str.concat(String.valueOf(new StringBuffer(",").append(t)
						.append(",").append(t + 2).append(",").append(t + 3)));
				t += 4;
				break;
			case 'c':
				str = str.concat(String.valueOf(new StringBuffer(",").append(t)
						.append(",").append(t + 1)));
				t += 4;
				break;
			case 'd':
				str = str.concat(String.valueOf(new StringBuffer(",").append(t)
						.append(",").append(t + 1).append(",").append(t + 3)));
				t += 4;
				break;
			case 'e':
				str = str.concat(String.valueOf(new StringBuffer(",").append(t)
						.append(",").append(t + 1).append(",").append(t + 2)));
				t += 4;
				break;
			case 'f':
				str = str.concat(String.valueOf(new StringBuffer(",").append(t)
						.append(",").append(t + 1).append(",").append(t + 2)
						.append(",").append(t + 3)));
				t += 4;
				break;
			}
		}
		return str;
	}

	// ��������ת�������ݲ���ϵͳ�����Զ��ж��Ƿ���ת���������ڴ���ݿ�ȡ��ݵ�ʱ
	public static String gbk(String strIn) {
		if (strIn == null) {
			return "";
		}
		java.util.Properties prop = System.getProperties();
		String encode = prop.getProperty("file.encoding");
		log.error("gbk encode =" + encode);
		String strOut = strIn;
		log.error("gbk strOut qian =" + strOut);
		if (!(encode.substring(0, 2)).equals("GB")) {
			try {
				strOut = new String(strIn.getBytes("GBK"), "ISO8859_1");
			} catch (Exception e) {
			}
		}
		log.error("gbk strOut hou =" + strOut);
		return strOut;
	}

	public static String iso(String strIn) {
		if (strIn == null) {
			return "";
		}

		java.util.Properties prop = System.getProperties();
		String encode = prop.getProperty("file.encoding");
		String strOut = strIn;
		log.error("iso encode =" + encode);
		log.error("iso strOut qian =" + strOut);
		if (!(encode.substring(0, 2)).equals("IS")) {
			try {
				strOut = new String(strIn.getBytes("ISO8859_1"), "GBK");
			} catch (Exception e) {
			}
		}
		log.error("iso strOut hou =" + strOut);
		return strOut;
	}

	public static String isof(String strIn) {
		if (strIn == null) {
			return "";
		}
		String strOut = strIn;
		log.error("isof strOut qian =" + strOut);
		try {
			strOut = new String(strIn.getBytes("ISO8859_1"), "GBK");
		} catch (Exception e) {
		}
		log.error("isof strOut hou =" + strOut);
		return strOut;
	}

	// ��������ת�������Զ��жϣ�ǿ��ת������������ҳ�������
	public static String gbkf(String strIn) {
		if (strIn == null) {
			return "";
		}
		String strOut = strIn;
		log.error("gbkf strOut qian =" + strOut);
		try {
			strOut = new String(strIn.getBytes("GBK"), "ISO8859_1");
		} catch (Exception e) {
		}
		log.error("gbkf strOut hou =" + strOut);
		return strOut;
	}

	// ȡ��ǰʱ��
	public static String getNow() {
		Date newDate = new Date();
		Calendar calendar = new GregorianCalendar();
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int month = calendar.get(Calendar.MONTH) + 1;
		int date = calendar.get(Calendar.DAY_OF_MONTH);
		return (calendar.get(Calendar.YEAR)) + "-" + (month < 10 ? "0" : "")
				+ month + "-" + (date < 10 ? "0" : "") + date + " "
				+ (hours < 10 ? "0" : "") + hours + ":"
				+ (minutes < 10 ? "0" : "") + minutes;
	}

	public static String iso2GBK(String str) {
		try {
			String strOut = new String(str.getBytes("ISO8859_1"), "GBK");
			return strOut;
		} catch (Exception e) {
		}
		return null;

	}

	public static String getField(String msg, String field) {
		String fvalue = "";
		int find_s = 0, find_e = 0;
		find_s = msg.indexOf("<" + field + ">");
		find_e = msg.indexOf("</" + field + ">");
		if (find_e > find_s && find_s >= 0) {
			fvalue = msg.substring(find_s + field.length() + 2, find_e);
		}
		return fvalue;
	}

	public void main(String[] a) {

	}

}
