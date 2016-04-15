package com.util.reportForm.service;


public class AdapderSqlServer {

	public static String adapder(String sql) {
		// //System.out.println(sql);
		// group by语法转换
		if (sql.indexOf("group by") > -1) {
			// 分析MySql SQL语句，将group by取出
			String sqlGroup = sql.substring(sql.indexOf("group by") + 9, sql
					.length());
			String[] sqlGroups = sqlGroup.split(",");
			String group = "";
			for (String g : sqlGroups) {
				if (g.indexOf(".") > -1) {
					if (g.trim().indexOf(" ") > -1)
						g = g.trim().substring(0, g.trim().indexOf(" "));
					group += "(" + g + "),";
				} else
					break;
			}

			// //System.out.println(group);

			// 分析MySql SQL语句，将查询字段取出
			String select = sql.substring(sql.indexOf("select ") + 7, sql
					.indexOf(" from "));
			String[] selects = select.split(",");
			String gTemp = "";
			for (String s : selects)
				if (s.startsWith("("))
					gTemp += s.substring(0, s.indexOf(")") + 1) + ",";

			// //System.out.println(gTemp);

			String[] groups = group.split(",");
			String[] gTemps = gTemp.split(",");
			String addGroup = group;
			for (String s : gTemps) {
				if ("".equals(s))
					continue;
				int i = 0;
				for (String g : groups)
					if (!"".equals(s) && s.equals(g))
						i++;
				if (i == 0)
					addGroup += s + ",";
			}
			addGroup = addGroup.substring(0, addGroup.length() - 1);

			// //System.out.println(addGroup);

			// 进行SQL Server的group by语法转换
			StringBuffer beforGroup = new StringBuffer("");
			for (String g : sqlGroups) {
				if (g.indexOf(".") > -1) {
					if (g.trim().indexOf(" ") > -1)
						g = g.trim().substring(0, g.trim().indexOf(" "));
					beforGroup.append(g + ",");
				} else
					break;
			}
			beforGroup.deleteCharAt(beforGroup.length() - 1);
			String afterSql = sql.replace(beforGroup.toString(), addGroup);
			sql = afterSql;
			// //System.out.println(sql);
		}

		// 分页语法转换
		if (sql.indexOf("limit") > -1) {
			if (sql.indexOf(" count(") > -1) {
				sql = sql.substring(0, sql.indexOf("limit "));
			} else {
				String limit = sql.substring(sql.indexOf("limit ") + 6, sql
						.length());
				Integer startIndex = 0;
				Integer size = 0;
				if (limit.indexOf(",") > -1) {
					startIndex = Integer.valueOf(limit.split(",")[0]);
					size = Integer.valueOf(limit.split(",")[1]);
				} else {
					startIndex = 0;
					size = Integer.valueOf(limit);
				}

				sql = sql.substring(0, sql.indexOf(" limit"));

				String limitsql = "select top "
						+ size
						+ " * from(select top "
						+ size
						+ " * from(select top "
						+ ((startIndex / size + 1) * size)
						+ " * from("
						+ sql
						+ ") temp_0 order by 1 asc) temp_1 order by 1 desc) temp_2 order by 1 asc";

				//System.out.println(limitsql);
				sql = limitsql;
			}
		}
		return sql;
	}

	public static void main(String[] args) {
		String sql = "select count(1)  from serverlog serverlog where 1=1  group by serverlog.datetime,serverlog.event_type having 1=1";
		sql = "select (serverlog.user_name) \"用户\",count(serverlog.eventid) \"事件类型\",(serverlog.event_status) \"操作结果\" from serverlog serverlog where 1=1  group by serverlog.datetime,serverlog.event_type having 1=1 limit 0,20";
		String hibernate = "select top 40 serverlog0_.id as col_0_0_, serverlog0_.user_name as col_1_0_, serverlog0_.event_status as col_2_0_, serverlog0_.datetime as col_3_0_, serverlog0_.event_type as col_4_0_, serverloge1_.eventconent as col_5_0_ from serverlog serverlog0_, serverlogevent serverloge1_ where serverlog0_.eventid=serverloge1_.id";
		String after = "select top 5 (computer.NowIp) \"IP地址\",(computer.ComputerName) \"计算机名称\",count(computer.MemorySize) \"物理内存\",(device.ClassName) \"名称\",(software.DisplayName) \"软件名称\" from computer computer,device device,software software where 1=1 and computer.guid = device.guid and computer.guid = software.guid  group by (computer.NowIp), (computer.ComputerName), (device.ClassName), (software.DisplayName)";
		AdapderSqlServer.adapder(sql);
	}

}
