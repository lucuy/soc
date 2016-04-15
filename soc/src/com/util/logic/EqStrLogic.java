package com.util.logic;

/**
 * 
 * eq_str运算符比较操作
 * 
 * @author jiadongxu
 * @version  [版本号, 2012-11-13]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class EqStrLogic extends LogicUnit
{
    /**
     * eq_str运算符运算
     * @param left
     * @param right
     * @return boolean
     * @see 
     */
    public boolean judge(Object left, Object right)
    {
        if (left == null)
        {
            if (right == null)
            {
                return true;
            }
            return false;
        }
        if (right == null)
        {
            return false;
        }
        return left.toString().equals(right.toString());
    }
    
}
