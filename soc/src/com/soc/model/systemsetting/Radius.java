package com.soc.model.systemsetting;

import java.io.Serializable;

/**
 * Description: radius设置实体类
 * 
 * @author 郭煜玺
 * @Version 1.0
 * @Created at 2011-06-16
 * @Modified by
 */
public class Radius implements Serializable {
	private static final long serialVersionUID = 1L;

	private int radiusUse; // 是否开启Radius认证；0：关闭 | 1：开启
	private String radiusIp; // Radius服务器IP地址
	private String shareKey; // 共享密钥
	private String authType; // 认证方式：pap | chap
	private String authPort = "1812"; // 认证端口号

	public String getAuthPort() {
		return authPort;
	}

	public void setAuthPort(String authPort) {
		this.authPort = authPort;
	}

	public int getRadiusUse() {
		return radiusUse;
	}

	public void setRadiusUse(int radiusUse) {
		this.radiusUse = radiusUse;
	}

	public String getRadiusIp() {
		return radiusIp;
	}

	public void setRadiusIp(String radiusIp) {
		this.radiusIp = radiusIp;
	}

	public String getShareKey() {
		return shareKey;
	}

	public void setShareKey(String shareKey) {
		this.shareKey = shareKey;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}
}