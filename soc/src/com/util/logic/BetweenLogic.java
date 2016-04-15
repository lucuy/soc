package com.util.logic;

/**
 * 
 * between运算
 * 
 * @author  jiadongxu
 * @version  [版本号, 2012-11-13]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BetweenLogic extends LogicUnit
{
   /**
    * 判断left是否在right数组中
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
            Object[] betweenParams = (Object[])right;
            if (betweenParams.length < 2)
            {
                throw new IllegalArgumentException("参数过短");
            }
            double leftRange = transToDouble(betweenParams[0]);
            double rightRange = transToDouble(betweenParams[1]);
            
            if ((leftRange <= leftD) && (leftD <= rightRange))
            {
                return true;
            }
            return false;
        }
        
        throw new IllegalArgumentException("非法的参数. 需要转换成 Object[]");
    }
    
    /**
     * 测试
     * @param args
     * @see [类、类#方法、类#成员]
     */
    public static void main(String[] args)
    {
        BetweenLogic bl = new BetweenLogic();
        //System.out.println(bl.judge(Integer.valueOf(40), new Object[] {"33", Integer.valueOf(45)}));
    }
}
