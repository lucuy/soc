package com.soc.model.asset.system;

import java.util.List;

/**
 * 资产中所关联的操作系统
 * @author lc
 *
 */
public class AssetSystem {
	
	private long assetSystemId;			//系统
	private String assetSystemName;		//系统、版本、版本号、厂商
	private long assetSystemLvel;		//级别
	private String assetSystemComment;	//系统备注
	private long assetSystemNoLevel;
	
	private List<AssetSystem> versionlist;		//版本集合
	private List<AssetSystem> versionnolist;	//版本号集合
	private List<AssetSystem> brandlist;		//品牌集合
	
	public long getAssetSystemNoLevel() {
		return assetSystemNoLevel;
	}
	public void setAssetSystemNoLevel(long assetSystemNoLevel) {
		this.assetSystemNoLevel = assetSystemNoLevel;
	}
	public long getAssetSystemId() {
		return assetSystemId;
	}
	public void setAssetSystemId(long assetSystemId) {
		this.assetSystemId = assetSystemId;
	}
	public String getAssetSystemName() {
		return assetSystemName;
	}
	public void setAssetSystemName(String assetSystemName) {
		this.assetSystemName = assetSystemName;
	}
	public long getAssetSystemLvel() {
		return assetSystemLvel;
	}
	public void setAssetSystemLvel(long assetSystemLvel) {
		this.assetSystemLvel = assetSystemLvel;
	}
	public String getAssetSystemComment() {
		return assetSystemComment;
	}
	public void setAssetSystemComment(String assetSystemComment) {
		this.assetSystemComment = assetSystemComment;
	}
	public List<AssetSystem> getVersionlist() {
		return versionlist;
	}
	public void setVersionlist(List<AssetSystem> versionlist) {
		this.versionlist = versionlist;
	}
	public List<AssetSystem> getVersionnolist() {
		return versionnolist;
	}
	public void setVersionnolist(List<AssetSystem> versionnolist) {
		this.versionnolist = versionnolist;
	}
	public List<AssetSystem> getBrandlist() {
		return brandlist;
	}
	public void setBrandlist(List<AssetSystem> brandlist) {
		this.brandlist = brandlist;
	}
	
	
}
