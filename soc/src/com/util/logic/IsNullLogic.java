package com.util.logic;

/**
 * 
 * isNull运算符操作
 * 
 * @author  jiadongxu
 * @version  [版本号, 2012-11-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class IsNullLogic extends LogicUnit
{
    /**
     * Isnull运算符方法
     * @param param
     * @return boolean
     * @see [类、类#方法、类#成员]
     */
    private boolean isNull(Object param)
    {
        if (param == null)
        {
            return true;
        }
        if (((param instanceof String)) && (((String)param).trim().length() == 0))
        {
            return true;
        }
        return false;
    }
    
    public boolean judge(Object left, Object right)
    {
        int flag = 1;
        if (right != null)
        {
            if ((right.toString().equalsIgnoreCase("yes")) || (right.toString().equalsIgnoreCase("y")))
                flag = 1;
            else
            {
                flag = 0;
            }
        }
        if (flag == 1)
        {
            if (isNull(left))
            {
                return true;
            }
            return false;
        }
        if (isNull(left))
        {
            return false;
        }
        return true;
    }
    
}
