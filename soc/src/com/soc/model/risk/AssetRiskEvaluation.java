package com.soc.model.risk;

import java.util.Date;

public class AssetRiskEvaluation {

	/**
	 * 资产风险赋值编号
	 */
	private int assetRiskEvaluationId;
	/**
	 * 资产名称
	 */
	private String assetName;
	/**
	 * 保密性价值
	 */
	private int assetSecretValue;
	/**
	 * 完整性价值
	 */
	private int assetIntegrityValue;
	/**
	 * 资产可用性价值
	 */
	private int assetUsabilityValue;
	/**
	 * 资产值
	 */
	private int assetAssetValue;
    private long isdelete;
	//关联资产的ID
	private long relAssetId;
	
	//资产赋值更新时间
	private Date assetValueUpdateTime;
	
	public long getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(long isdelete) {
		this.isdelete = isdelete;
	}
	public int getAssetRiskEvaluationId() {
		return assetRiskEvaluationId;
	}
	public void setAssetRiskEvaluationId(int assetRiskEvaluationId) {
		this.assetRiskEvaluationId = assetRiskEvaluationId;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public int getAssetSecretValue() {
		return assetSecretValue;
	}
	public void setAssetSecretValue(int assetSecretValue) {
		this.assetSecretValue = assetSecretValue;
	}
	public int getAssetIntegrityValue() {
		return assetIntegrityValue;
	}
	public void setAssetIntegrityValue(int assetIntegrityValue) {
		this.assetIntegrityValue = assetIntegrityValue;
	}
	public int getAssetUsabilityValue() {
		return assetUsabilityValue;
	}
	public void setAssetUsabilityValue(int assetUsabilityValue) {
		this.assetUsabilityValue = assetUsabilityValue;
	}
	public int getAssetAssetValue() {
		return assetAssetValue;
	}
	public void setAssetAssetValue(int assetAssetValue) {
		this.assetAssetValue = assetAssetValue;
	}
	@Override
	public String toString() {
		return "AssetRiskEvaluation [assetRiskEvaluationId="
				+ assetRiskEvaluationId + ", assetName=" + assetName
				+ ", assetSecretValue=" + assetSecretValue
				+ ", assetIntegrityValue=" + assetIntegrityValue
				+ ", assetUsabilityValue=" + assetUsabilityValue
				+ ", assetAssetValue=" + assetAssetValue + "]";
	}
    public long getRelAssetId()
    {
        return relAssetId;
    }
    public void setRelAssetId(long relAssetId)
    {
        this.relAssetId = relAssetId;
    }
    public Date getAssetValueUpdateTime()
    {
        return assetValueUpdateTime;
    }
    public void setAssetValueUpdateTime(Date assetValueUpdateTime)
    {
        this.assetValueUpdateTime = assetValueUpdateTime;
    }
	
	
}
