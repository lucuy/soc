package com.soc.model.conf;

import java.io.Serializable;

/**
 * 邮件服务请求
 * @author zsa
 *
 */
public class MailserversInstance implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer msiId;// 实例ID
	private String msiServername;// 服务商
	private String msiPop3name;// pop3服务器
	private String msiPop3port;// pop3服务端口
	private Integer msiPop3ssl;// pop3是否使用ssl
	private Integer msiPop3entireaddress;// 完整的pop3地址
	private String msiSmtpname;// smtp服务器
	private String msiSmtpport;// smtp服务端口
	private Integer msiSmtpssl;// smtp是否使用ssl
	private String msiImapname;// imap服务器
	private String msiImapport;// imap服务端口
	private Integer msiImapssl;// imap是否使用ssl
	private Integer msiImapentireaddress;// 完整的imap地址
	private Integer msiImapmovetosentbox;// imap是否移至发件箱
	private String msiHttpname;// http服务器
	private Integer msiHttpentireaddress;// 完整的http地址

	public Integer getMsiId() {
		return msiId;
	}

	public void setMsiId(Integer msiId) {
		this.msiId = msiId;
	}

	public String getMsiServername() {
		return msiServername;
	}

	public void setMsiServername(String msiServername) {
		this.msiServername = msiServername;
	}

	public String getMsiPop3name() {
		return msiPop3name;
	}

	public void setMsiPop3name(String msiPop3name) {
		this.msiPop3name = msiPop3name;
	}

	public String getMsiPop3port() {
		return msiPop3port;
	}

	public void setMsiPop3port(String msiPop3port) {
		this.msiPop3port = msiPop3port;
	}

	public Integer getMsiPop3ssl() {
		return msiPop3ssl;
	}

	public void setMsiPop3ssl(Integer msiPop3ssl) {
		this.msiPop3ssl = msiPop3ssl;
	}

	public Integer getMsiPop3entireaddress() {
		return msiPop3entireaddress;
	}

	public void setMsiPop3entireaddress(Integer msiPop3entireaddress) {
		this.msiPop3entireaddress = msiPop3entireaddress;
	}

	public String getMsiSmtpname() {
		return msiSmtpname;
	}

	public void setMsiSmtpname(String msiSmtpname) {
		this.msiSmtpname = msiSmtpname;
	}

	public String getMsiSmtpport() {
		return msiSmtpport;
	}

	public void setMsiSmtpport(String msiSmtpport) {
		this.msiSmtpport = msiSmtpport;
	}

	public Integer getMsiSmtpssl() {
		return msiSmtpssl;
	}

	public void setMsiSmtpssl(Integer msiSmtpssl) {
		this.msiSmtpssl = msiSmtpssl;
	}

	public String getMsiImapname() {
		return msiImapname;
	}

	public void setMsiImapname(String msiImapname) {
		this.msiImapname = msiImapname;
	}

	public String getMsiImapport() {
		return msiImapport;
	}

	public void setMsiImapport(String msiImapport) {
		this.msiImapport = msiImapport;
	}

	public Integer getMsiImapssl() {
		return msiImapssl;
	}

	public void setMsiImapssl(Integer msiImapssl) {
		this.msiImapssl = msiImapssl;
	}

	public Integer getMsiImapentireaddress() {
		return msiImapentireaddress;
	}

	public void setMsiImapentireaddress(Integer msiImapentireaddress) {
		this.msiImapentireaddress = msiImapentireaddress;
	}

	public Integer getMsiImapmovetosentbox() {
		return msiImapmovetosentbox;
	}

	public void setMsiImapmovetosentbox(Integer msiImapmovetosentbox) {
		this.msiImapmovetosentbox = msiImapmovetosentbox;
	}

	public String getMsiHttpname() {
		return msiHttpname;
	}

	public void setMsiHttpname(String msiHttpname) {
		this.msiHttpname = msiHttpname;
	}

	public Integer getMsiHttpentireaddress() {
		return msiHttpentireaddress;
	}

	public void setMsiHttpentireaddress(Integer msiHttpentireaddress) {
		this.msiHttpentireaddress = msiHttpentireaddress;
	}

}
