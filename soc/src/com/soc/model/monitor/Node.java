package com.soc.model.monitor;

public class Node {
	
	//资产id
	
	private	int id;
	
	//父结点编号
	
	private int fatherId;
	
	//显示名称
	
	private String name;
	
	//运行状态(0=正常;1=故障)
	
	private int state;
	
	//显示图片
	
	private String url;
	//资产ip
    private String assetIp;
    //位置 top
    private int top;
    //位子 left
    private int left;
    //登录需要的名字
    private String loginName;
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFatherId() {
		return fatherId;
	}

	public void setFatherId(int fatherId) {
		this.fatherId = fatherId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getAssetIp() {
		return assetIp;
	}

	public void setAssetIp(String assetIp) {
		this.assetIp = assetIp;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

}
