package com.util.logic;

/**
 * 
 * 运算符in_ic处理
 * 
 * @author  jiadongxu
 * @version  [版本号, 2012-11-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class InIcLogic extends LogicUnit
{
    
    /**
     * in_ic运算符运算
     * @param left
     * @param right
     * @return boolean
     * @see 
     */
    public boolean judge(Object left, Object right)
    {
        if ((left == null) || (right == null))
        {
            return false;
        }
        if ((right instanceof Object[]))
        {
            Object[] inParams = (Object[])right;
            for (int i = 0; i < inParams.length; i++)
            {
                if (inParams[i].toString().toLowerCase().equals(left.toString().toLowerCase()))
                    return true;
            }
            return false;
        }
        
        throw new IllegalArgumentException("非法的参数. 需要转换成Object[]");
    }
}
