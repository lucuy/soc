package com.util;

import java.util.HashMap;
import java.util.Map;

import com.util.logic.AndLogic;
import com.util.logic.BetweenLogic;
import com.util.logic.ContainsIcLogic;
import com.util.logic.EqLogic;
import com.util.logic.EqStrIcLogic;
import com.util.logic.EqStrLogic;
import com.util.logic.InIcLogic;
import com.util.logic.InNumberLogic;
import com.util.logic.IsNullLogic;
import com.util.logic.LogicUnit;
import com.util.logic.MatchesFilterLogic;
import com.util.logic.OrLogic;
import com.util.logic.StartSwithLogic;

/**
 * 
 * 运算工具类
 * 
 * @author  jiadongxu
 * @version  [版本号, 2012-11-13]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class FilterAssistant
{
    private static Map<Object, LogicUnit> oprs = new HashMap<Object,LogicUnit>();
    static{
        oprs.put("between", new BetweenLogic());
        oprs.put("eq", new EqLogic());
        oprs.put("eq_str", new EqStrLogic());
        oprs.put("eq_str_ic", new EqStrIcLogic());
        oprs.put("in_ic", new InIcLogic());
        oprs.put("contains_ic", new ContainsIcLogic());
        oprs.put("in", new InIcLogic());
        oprs.put("startswith", new StartSwithLogic());
        oprs.put("innumber", new InNumberLogic());
        oprs.put("isnull", new IsNullLogic());
        
    }
    /**
     * 调用运算符运算
     * @param left
     * @param opr
     * @param right
     * @return boolean
     * @see [类、类#方法、类#成员]
     */
    public static boolean cal(Object left, Object opr, Object right)
    {
      LogicUnit lu = (LogicUnit)oprs.get(opr);
      if (lu == null)
        throw new IllegalArgumentException("没有找到:" + opr);
      return lu.judge(left, right);
    }
    
}
