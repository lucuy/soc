package com.util.logic;

/**
 * 
 *inNumber运算符
 * 
 * @author  jiadongxu
 * @version  [版本号, 2012-11-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class InNumberLogic extends LogicUnit
{
    /**
     * inNUmber运算符运算
     * @param left
     * @param right
     * @return boolean
     * @see 
     */
    public boolean judge(Object left, Object right)
    {
        if ((left == null) || (right == null) || left.toString().equals(""))
        {
            return false;
        }
        double leftD = transToDouble(left);
        
        if ((right instanceof Object[]))
        {
            Object[] inParams = (Object[])right;
            for (int i = 0; i < inParams.length; i++)
            {
                double element = transToDouble(inParams[i]);
                if (leftD == element)
                    return true;
            }
            return false;
        }
        
        throw new IllegalArgumentException("非法的参数. 需要转换成Object[]");
    }
    
}
