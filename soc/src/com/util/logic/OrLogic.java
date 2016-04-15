package com.util.logic;

/**
 * 
 * or运算符运算
 * 
 * @author  jiadongxu
 * @version  [版本号, 2012-11-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OrLogic extends LogicUnit
{

    /**
     * or运算符运算
     * @param left
     * @param right
     * @return boolean
     * @see 
     */
    public boolean judge(Object left, Object right)
    {
        if(left.equals(false) && right.equals(false)){
            return false;
         }
         return true;
     }
    /**
     * 测试
     * @param args
     * @see [类、类#方法、类#成员]
     */
    public static void main(String[] args)
    {
        OrLogic bl = new OrLogic();
        //System.out.println("===="+bl.judge(false, false));
    }
}
