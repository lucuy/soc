package com.soc.model.asset;

public class AssetRiskGroup 
{
    private long riskGroupId;       //id
    
    private String riskGroupName;     //name

    public long getRiskGroupId()
    {
        return riskGroupId;
    }

    public void setRiskGroupId(long riskGroupId)
    {
        this.riskGroupId = riskGroupId;
    }

    public String getRiskGroupName()
    {
        return riskGroupName;
    }

    public void setRiskGroupName(String riskGroupName)
    {
        this.riskGroupName = riskGroupName;
    }

}