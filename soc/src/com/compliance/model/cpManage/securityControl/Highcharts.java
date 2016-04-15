package com.compliance.model.cpManage.securityControl;

import java.util.ArrayList;
import java.util.List;

public class Highcharts {
	private String name;
	private List data = new ArrayList();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
}
