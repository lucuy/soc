package com.soc.model.asset.system;

/**
 * 系统所关联的品牌类
 * 
 * @author lc
 *
 */
public class AssetSystemBrand {
	
	private long assetSystemBrand;
	private long assetSystemBrandId;    //系统的主键id
	private String assetSystemBrandName;//系统的品牌
	
	
	
	public long getAssetSystemBrand() {
		return assetSystemBrand;
	}
	public void setAssetSystemBrand(long assetSystemBrand) {
		this.assetSystemBrand = assetSystemBrand;
	}
	public long getAssetSystemBrandId() {
		return assetSystemBrandId;
	}
	public void setAssetSystemBrandId(long assetSystemBrandId) {
		this.assetSystemBrandId = assetSystemBrandId;
	}
	public String getAssetSystemBrandName() {
		return assetSystemBrandName;
	}
	public void setAssetSystemBrandName(String assetSystemBrandName) {
		this.assetSystemBrandName = assetSystemBrandName;
	}
	
	
}
