package com.util.logic;

/**
 * 
 *StartSwith运算符操作
 * 
 * @author  jiadongxu
 * @version  [版本号, 2012-11-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class StartSwithLogic extends LogicUnit
{
    /**
     * startswith运算符运算
     * @param left
     * @param right
     * @return boolean
     * @see 
     */
    public boolean judge(Object left, Object right)
    {
        if ((left == null) || (right == null))
            return false;
        return left.toString().startsWith(right.toString());
    }
    
}
