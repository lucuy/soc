package com.soc.model.systemsetting.rules;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * <解析规则>
 * <功能详细描述>
 * 
 * @author  yinhaiping
 * @version  [版本号, 2012-11-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AnalysisRules implements Serializable
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    
    // 过滤规则ID
    private String analysisId;
    
    // 过滤规则名称
    private String analysisName;
    
    // 过滤条件
    private String analysisCondition;
    
    // 描述
    private String analysisDescription;
    
    // 创建时间
    private Date analysisCreateTime;
    
    // 创建者
    private String analysisCreator;
    
    // 更新时间
    private Date analysisUpdateTime;
    
    //类型
    private Integer analysisType;

    public String getAnalysisId()
    {
        return analysisId;
    }

    public void setAnalysisId(String analysisId)
    {
        this.analysisId = analysisId;
    }

    public String getAnalysisName()
    {
        return analysisName;
    }

    public void setAnalysisName(String analysisName)
    {
        this.analysisName = analysisName;
    }

    public String getAnalysisCondition()
    {
        return analysisCondition;
    }

    public void setAnalysisCondition(String analysisCondition)
    {
        this.analysisCondition = analysisCondition;
    }

    public String getAnalysisDescription()
    {
        return analysisDescription;
    }

    public void setAnalysisDescription(String analysisDescription)
    {
        this.analysisDescription = analysisDescription;
    }

    public Date getAnalysisCreateTime()
    {
        return analysisCreateTime;
    }

    public void setAnalysisCreateTime(Date analysisCreateTime)
    {
        this.analysisCreateTime = analysisCreateTime;
    }

    public String getAnalysisCreator()
    {
        return analysisCreator;
    }

    public void setAnalysisCreator(String analysisCreator)
    {
        this.analysisCreator = analysisCreator;
    }

    public Date getAnalysisUpdateTime()
    {
        return analysisUpdateTime;
    }

    public void setAnalysisUpdateTime(Date analysisUpdateTime)
    {
        this.analysisUpdateTime = analysisUpdateTime;
    }

    public Integer getAnalysisType()
    {
        return analysisType;
    }

    public void setAnalysisType(Integer analysisType)
    {
        this.analysisType = analysisType;
    }
}