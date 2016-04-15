package com.util.reportForm.service;

public class ReportFormQueryService {

	// 解析条件 返回 解析后的格式 oerderby groupby 以数组形式返回
	public String[] getCond(String cond) {
		String[] selCond = new String[4];
		String orderby = "";
		String groupby = "";
		String condValue = "";
		String having = "";
		if (cond != null && cond.length() > 0) {
			String[] conds = cond.split("\\|\\|");
			for (String s : conds) {
				cond = cond.replaceAll(s + "\\|\\|", "\\:\\*").replaceFirst(
						"\\:\\*", s + "\\|\\|");
			}
			cond = cond.replaceAll("\\:\\*", "");
			conds = cond.split("\\|\\|");
			for (int i = 0; i < conds.length; i++) {
				String temp = conds[i].split("\\;")[1];
				if (!"".equals(conds[i])) {
					if (conds[i].indexOf("order by") != -1) {
						if (conds[i].indexOf(" desc ") != -1) {
							orderby += temp.substring(
									temp.indexOf("order by") + 9, temp
											.indexOf(" desc ") + 5)
									+ ",";
						} else if (conds[i].indexOf(" asc ") != -1) {
							orderby += temp.substring(
									temp.indexOf("order by") + 9, temp
											.indexOf(" asc ") + 4)
									+ ",";
						}
					
					} else if (conds[i].indexOf("group by") != -1) {
						groupby += temp.substring(temp.indexOf("group by") + 9)
								+ ",";
					} else if (conds[i].indexOf("having") != -1) {
						having += " and "
								+ temp.substring(temp.indexOf("having ") + 7);
					} else {
						condValue += conds[i] + "||";
					}
				}
			}

		}
		if (!"".equals(condValue)) {
			selCond[0] = condValue.substring(0, condValue.length() - 1);
		} else {
			selCond[0] = "";
		}
		if (!"".equals(orderby)) {
			selCond[1] = orderby.substring(0, orderby.length() - 1);
		} else {
			selCond[1] = "";
		}
		if (!"".equals(groupby)) {
			selCond[2] = groupby; // .substring(0,groupby.length()-1);
		} else {
			selCond[2] = "";
		}
		if (!"".equals(having)) {
			selCond[3] = having;
		} else {
			selCond[3] = "";
		}
		return selCond;
	}

	public String tableLink(String tableName) {
		tableName = "," + tableName + ",";
		String tablelink = "";
		int temp = 0;
		String tempTable = "";
		if (tableName.indexOf(",computer,") != -1) {
			tempTable = "and computer.guid ";
			temp++;
		}
		if (tableName.indexOf(",partition,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= partition.guid ";
			tempTable = tempTable.equals("") ? " and partition.guid "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",device,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= device.guid ";
			tempTable = tempTable.equals("") ? " and device.guid " : tempTable;
			temp++;
		}
		if (tableName.indexOf(",software,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= software.guid ";
			tempTable = tempTable.equals("") ? " and software.guid "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",processinfo,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= processinfo.guid ";
			tempTable = tempTable.equals("") ? " and processinfo.guid "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",systemshare,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= systemshare.guid ";
			tempTable = tempTable.equals("") ? " and systemshare.guid "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",sysuser,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= sysuser.guid ";
			tempTable = tempTable.equals("") ? " and sysuser.guid " : tempTable;
			temp++;
		}
		if (tableName.indexOf(",flow,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable + "= flow.guid ";
			tempTable = tempTable.equals("") ? " and flow.guid " : tempTable;
			temp++;
		}
		if (tableName.indexOf(",patchinfo,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= patchinfo.ClientGuid ";
			tempTable = tempTable.equals("") ? " and patchinfo.ClientGuid "
					: tempTable;
			temp++;
		}

		if (tableName.indexOf(",netstat,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= netstat.guid ";
			temp++;
		}

		// 联想内容
		if (tableName.indexOf(",apache_info,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= apache_info.ClientID ";
			tempTable = tempTable.equals("") ? " and apache_info.ClientID "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",app_event,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= app_event.ClientID ";
			tempTable = tempTable.equals("") ? " and app_event.ClientID "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",autorun_info,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= autorun_info.ClientID ";
			tempTable = tempTable.equals("") ? " and autorun_info.ClientID "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",client_change_info,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= client_change_info.GUID ";
			tempTable = tempTable.equals("") ? " and client_change_info.GUID "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",device_info,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= device_info.ClientID ";
			tempTable = tempTable.equals("") ? " and device_info.ClientID "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",iis_info,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= iis_info.ClientID ";
			tempTable = tempTable.equals("") ? " and iis_info.ClientID "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",mysql_info,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= mysql_info.ClientID ";
			tempTable = tempTable.equals("") ? " and mysql_info.ClientID "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",netcard_info,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= netcard_info.ClientID ";
			tempTable = tempTable.equals("") ? " and netcard_info.ClientID "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",netstat_info,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= netstat_info.ClientID ";
			tempTable = tempTable.equals("") ? " and netstat_info.ClientID "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",oracle_info,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= oracle_info.ClientID ";
			tempTable = tempTable.equals("") ? " and oracle_info.ClientID "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",os_event,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= os_event.ClientID ";
			tempTable = tempTable.equals("") ? " and os_event.ClientID "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",os_policy,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= os_policy.ClientID ";
			tempTable = tempTable.equals("") ? " and os_policy.ClientID "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",osuser_info,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= osuser_info.ClientID ";
			tempTable = tempTable.equals("") ? " and osuser_info.ClientID "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",osversion_info,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= osversion_info.ClientID ";
			tempTable = tempTable.equals("") ? " and osversion_info.ClientID "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",patch_info,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= patch_info.ClientID ";
			tempTable = tempTable.equals("") ? " and patch_info.ClientID "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",process_info,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= process_info.ClientID ";
			tempTable = tempTable.equals("") ? " and process_info.ClientID "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",safe_event,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= safe_event.ClientID ";
			tempTable = tempTable.equals("") ? " and safe_event.ClientID "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",security_event,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= security_event.ClientGuid ";
			tempTable = tempTable.equals("") ? " and security_event.ClientGuid "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",security_operate_record,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= security_operate_record.ClientGuid ";
			tempTable = tempTable.equals("") ? " and security_operate_record.ClientGuid "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",service_info,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= service_info.ClientID ";
			tempTable = tempTable.equals("") ? " and service_info.ClientID "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",share_info,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= share_info.ClientID ";
			tempTable = tempTable.equals("") ? " and share_info.ClientID "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",software_info,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= software_info.ClientID ";
			tempTable = tempTable.equals("") ? " and software_info.ClientID "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",secureoption_info,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= secureoption_info.ClientID ";
			tempTable = tempTable.equals("") ? " and secureoption_info.ClientID "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",sqlserver_info,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= sqlserver_info.ClientID ";
			tempTable = tempTable.equals("") ? " and sqlserver_info.ClientID "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",system_event,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= system_event.ClientID ";
			tempTable = tempTable.equals("") ? " and system_event.ClientID "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",tomcat_info,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= tomcat_info.ClientID ";
			tempTable = tempTable.equals("") ? " and tomcat_info.ClientID "
					: tempTable;
			temp++;
		}
		if (tableName.indexOf(",weblogic_info,") != -1) {
			tablelink += tempTable.equals("") ? "" : tempTable
					+ "= weblogic_info.ClientID ";
			tempTable = tempTable.equals("") ? " and weblogic_info.ClientID "
					: tempTable;
			temp++;
		}

		if (temp == 0 || temp == 1) {
			tablelink = " ";
		}

		return tablelink;
	}

	public boolean isEmpty(char[] value) {
		int n = 0;
		int b = 0;
		int d = 0;
		for (char c : value) {

			if (c == '\'') {
				n++;
			} else {
				if (n == 2) {
					break;
				}
				n = 0;
			}

			if (c == '\'' || c == '？') {
				b++;
			} else {
				if (b == 3)
					break;
				b = 0;
			}

			if (c == '\'' || c == '%' || c == '？') {
				d++;
			} else {
				if (d >= 5)
					break;
				d = 0;
			}

		}

		return n == 2 || b == 3 || d >= 4;
	}

}
