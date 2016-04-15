package com.util.logic;

/**
 * 
 * In运算符操作
 * 
 * @author  jiadongxu
 * @version  [版本号, 2012-11-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class InLogic extends LogicUnit
{
    /**
     * in运算符运算
     * @param left
     * @param right
     * @return boolean
     * @see 
     */
    public boolean judge(Object left, Object right)
    {
        if ((left == null) || (right == null))
            return false;
        if ((right instanceof Object[]))
        {
            Object[] inParams = (Object[])right;
            for (int i = 0; i < inParams.length; i++)
            {
                if (inParams[i].toString().equals(left.toString()))
                    return true;
            }
            return false;
        }
        
        throw new IllegalArgumentException("非法的参数. 需要转换成Object[]");
    }
    
}
