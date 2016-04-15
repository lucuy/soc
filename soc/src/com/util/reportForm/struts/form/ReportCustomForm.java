package com.util.reportForm.struts.form;

import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import com.util.reportForm.datadeal.model.ReportCustom;

/**
 * 自定义报表form
 * @author zsa
 *
 */
public class ReportCustomForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private ReportCustom rc = new ReportCustom();
	private List<ReportCustom> list;//自定义报表列表
	private List<String>titles;//报表表头
	private List<Map>val;//报表value

	public List<Map> getVal() {
		return val;
	}

	public void setVal(List<Map> val) {
		this.val = val;
	}

	public List<ReportCustom> getList() {
		return list;
	}

	public void setList(List<ReportCustom> list) {
		this.list = list;
	}

	public ReportCustom getRc() {
		return rc;
	}

	public void setRc(ReportCustom rc) {
		this.rc = rc;
	}

	public List<String> getTitles() {
		return titles;
	}

	public void setTitles(List<String> titles) {
		this.titles = titles;
	}

	
}
