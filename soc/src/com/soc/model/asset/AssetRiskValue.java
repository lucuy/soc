package com.soc.model.asset;

public class AssetRiskValue
{
    private long riskId;
    
    private String riskName;
    
    private int riskPossibility;        //可能性
    
    private int riskInfluence;          //影响
    
    private int riskResult;             //威胁值结果
    
    private int riskThreatValue;        //威胁值
    
    private long riskAssetId;            //资产id
    
    private String riskAssetName;
    private long isdelete;
    public long getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(long isdelete) {
		this.isdelete = isdelete;
	}

	public long getRiskId()
    {
        return riskId;
    }

    public void setRiskId(long riskId)
    {
        this.riskId = riskId;
    }

    public String getRiskName()
    {
        return riskName;
    }

    public void setRiskName(String riskName)
    {
        this.riskName = riskName;
    }

    public int getRiskPossibility()
    {
        return riskPossibility;
    }

    public void setRiskPossibility(int riskPossibility)
    {
        this.riskPossibility = riskPossibility;
    }

    public int getRiskInfluence()
    {
        return riskInfluence;
    }

    public void setRiskInfluence(int riskInfluence)
    {
        this.riskInfluence = riskInfluence;
    }

    public int getRiskResult()
    {
        return riskResult;
    }

    public void setRiskResult(int riskResult)
    {
        this.riskResult = riskResult;
    }

    public int getRiskThreatValue()
    {
        return riskThreatValue;
    }

    public void setRiskThreatValue(int riskThreatValue)
    {
        this.riskThreatValue = riskThreatValue;
    }

	public long getRiskAssetId() {
		return riskAssetId;
	}

	public void setRiskAssetId(long riskAssetId) {
		this.riskAssetId = riskAssetId;
	}

	public String getRiskAssetName() {
		return riskAssetName;
	}

	public void setRiskAssetName(String riskAssetName) {
		this.riskAssetName = riskAssetName;
	}

	
    
}