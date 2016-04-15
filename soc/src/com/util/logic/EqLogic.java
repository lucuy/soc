package com.util.logic;

/**
 * 
 * eq运算
 * 
 * @author  jiadongxu
 * @version  [版本号, 2012-11-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class EqLogic extends LogicUnit
{
    /**
     * eq运算符运算
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
        double rightD = transToDouble(right);
        return leftD == rightD;
    }
    
    /**
     * 测试
     * @param args
     * @see [类、类#方法、类#成员]
     */
    public static void main(String[] args)
    {
        EqLogic eql = new EqLogic();
        //System.out.println(eql.judge(new Double(45.009999999999998D), "45.01"));
        //System.out.println(true);
    }
    
}
