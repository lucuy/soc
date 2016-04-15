package com.util.logic;

/**
 * 
 * 进行转换
 * 
 * @author  jiadongxu
 * @version  [版本号, 2012-11-13]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class LogicUnit
{
    /**
     * 转化成Double类型
     * @param operand
     * @return
     * @see [类、类#方法、类#成员]
     */
    public double transToDouble(Object operand)
    {
        if ((operand instanceof String))
        {
            return Double.parseDouble((String)operand);
        }
        if ((operand instanceof Number))
        {
            return ((Number)operand).doubleValue();
        }
        
        throw new IllegalArgumentException("当计算逻辑表达式 [" + operand + "]遇到非法参数");
    }
    
    /**
     * 转换成long类型
     * @param operand
     * @return long
     * @see [类、类#方法、类#成员]
     */
    public long transToLong(Object operand)
    {
        if ((operand instanceof String))
        {
            return Long.parseLong((String)operand);
        }
        if ((operand instanceof Number))
        {
            return ((Number)operand).longValue();
        }
        
        throw new IllegalArgumentException("当计算逻辑表达式 [" + operand  + "]遇到非法参数");
    }
    
    public abstract boolean judge(Object paramObject1, Object paramObject2);
    
}
