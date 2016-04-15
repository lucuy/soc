package com.soc.junit.test.sql.psql;

public class PGdb {
	private String datname;//数据库名称
	private String datdba;//拥有者
	private String datcollate;//编码
	private String datctype;//编码
	public String getDatname() {
		return datname;
	}
	public void setDatname(String datname) {
		this.datname = datname;
	}
	public String getDatdba() {
		return datdba;
	}
	public void setDatdba(String datdba) {
		this.datdba = datdba;
	}
	public String getDatcollate() {
		return datcollate;
	}
	public void setDatcollate(String datcollate) {
		this.datcollate = datcollate;
	}
	public String getDatctype() {
		return datctype;
	}
	public void setDatctype(String datctype) {
		this.datctype = datctype;
	}
	
	
}
