package com.util.logic;

/**
 * contains_in运算
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  jiadongxu
 * @version  [版本号, 2012-11-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ContainsIcLogic extends LogicUnit
{

    /**
     * contain_ic运算符运算
     * @param left
     * @param right
     * @return boolean
     * @see 
     */
    public boolean judge(Object left, Object right)
    {
        if ((left == null) || (right == null)) {
            return false;
          }
          return StringTools.contains(left.toString().toLowerCase(), right.toString().toLowerCase());
    }
    
}
