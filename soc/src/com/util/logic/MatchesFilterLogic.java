package com.util.logic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.soc.model.events.Events;
import com.soc.model.events.Filter;
import com.soc.model.events.FilterByGroup;
import com.soc.service.events.FilterService;
import com.util.FilterAssistant;
import com.util.StringUtil;

/**
 * 
 * <处理matchesfilter运算符的类>
 * <功能详细描述>
 * 
 * @author  王亚男
 * @version  [版本号, 2012-11-15]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MatchesFilterLogic
{
    
    /**
     * <运算符mathesfilter的处理方法>
     * <功能详细描述>
     * @param filterManager FilterService
     * @param filterId String
     * @param events Events
     * @param operatorList List<String>
     * @param eventAttributes Map<String, String>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean MatchesFilterHandling(FilterByGroup filterByGroup, Events events,
        List<String> operatorList, Map<String, String> eventAttributes)
    {
        /*// 标记事件events是否符合规则filter
        boolean flag = false;
        
        // 获得filter
        Filter filter = filterManager.queryFilterById(filterId);
        
        if(filter != null)
        {
            // 获取过滤条件
            String condition = filter.getFilterCondition();
            
            // 过滤条件以|作为分隔符
            String[] conditionElement = condition.split("\\|");
            
            // 将条件拆分后的每个元素压入此栈
            Stack<Object> stack = new Stack<Object>();
            
            // stack的备份栈，在处理or、and运算符时使用
            Stack<Object> stackCopy = new Stack<Object>();
            
            // 遍历表达式，将元素逐一压入栈
            for (int i = 0; i < conditionElement.length; i++)
            {
                
                String element = conditionElement[i];
                
                // 以遇到)为停止压栈操作的标识
                if (!StringUtil.equals(element, ")"))
                {
                    stack.push(element);
                    stackCopy.push(element);
                }
                else
                {
                    // 存放stack移除的元素的列表
                    List<Object> list = new ArrayList<Object>();
                    
                    // 获得并移除栈的头部元素
                    Object e1 = (Object)stack.pop();
                    
                    while (true)
                    {
                        if (!e1.equals("("))
                        {
                            list.add(e1);
                            e1 = (Object)stack.pop();
                        }
                        else
                        {
                            break;
                        }
                        
                    }
                    
                    // 运算符为or、and
                    if (list.contains("or") || list.contains("and"))
                    {
                        flag = orAndOperatorHandling(stackCopy);
                    }
                    else
                    {
                        // 运算符为matchesfilter
                        if (list.contains("matchesfilter"))
                        {
                            for(Object obj : list)
                            {
                                if(!StringUtil.equals(obj.toString(), "matchesfilter"))
                                {
                                    flag =
                                        MatchesFilterLogic.MatchesFilterHandling(filterManager,
                                            obj.toString(),
                                            events,
                                            operatorList,
                                            eventAttributes);
                                }
                            }
                            
                        }
                        else
                        {
                            Map<String, Object> map = obtainExpression(events, list, operatorList, eventAttributes);
                            //System.out.println("规则：" + filter.getFilterCondition());
                            flag = FilterAssistant.cal(map.get("left"), map.get("opr"), map.get("right"));
                        }
                    }
                    
                    if (i != conditionElement.length - 1)
                    {
                        stack.push(flag);
                        stackCopy.clear();
                        Stack<Object> clone = (Stack<Object>)stack.clone();
                        stackCopy = clone;
                        continue;
                    }
                    
                }
                
                //System.out.println("Rule execute result：" + flag);
            }
        }
        else
        {
            //System.out.println("系统信息：      未找到规则ID【" + filterId + "】的规则！！！！");
        }*/
        
     // 标记事件events是否符合规则filter
        boolean flag = false;
        
        // 获取事件匹配过滤规则的值
        if(filterByGroup != null)
        {
            // 获取过滤条件
            String condition = filterByGroup.getFilterByGroupCondition();
            
            // 过滤条件以|作为分隔符
            String[] conditionElement = condition.split("\\|");
            
            // 将条件拆分后的每个元素压入此栈
            Stack<Object> stack = new Stack<Object>();
            
            // stack的备份栈，在处理or、and运算符时使用
            Stack<Object> stackCopy = new Stack<Object>();
            
            // 遍历表达式，将元素逐一压入栈
            for (int i = 0; i < conditionElement.length; i++)
            {
                
                String element = conditionElement[i];
                
                // 以遇到)为停止压栈操作的标识
                if (!StringUtil.equals(element, ")"))
                {
                    stack.push(element);
                    stackCopy.push(element);
                }
                else
                {
                    // 存放stack移除的元素的列表
                    List<Object> list = new ArrayList<Object>();
                    
                    // 获得并移除栈的头部元素
                    Object e1 = (Object)stack.pop();
                    
                    while (true)
                    {
                        if (!e1.equals("("))
                        {
                            list.add(e1);
                            e1 = (Object)stack.pop();
                        }
                        else
                        {
                            break;
                        }
                        
                    }
                    
                    // 运算符为or、and
                    if (list.contains("or") || list.contains("and"))
                    {
                        flag = orAndOperatorHandling(stackCopy);
                    }
                    else
                    {
                        /*// 运算符为matchesfilter
                        if (list.contains("matchesfilter"))
                        {
                            for(Object obj : list)
                            {
                                if(!StringUtil.equals(obj.toString(), "matchesfilter"))
                                {
                                    flag =
                                        MatchesFilterLogic.MatchesFilterHandling(filterManager,
                                            obj.toString(),
                                            events,
                                            operatorList,
                                            eventAttributes);
                                }
                            }
                            
                        }
                        else
                        {*/
                            Map<String, Object> map = obtainExpression(events, list, operatorList, eventAttributes);
                            //System.out.println("规则：" + filterByGroup.getFilterByGroupCondition());
                            flag = FilterAssistant.cal(map.get("left"), map.get("opr"), map.get("right"));
                        //}
                    }
                    
                    if (i != conditionElement.length - 1)
                    {
                        stack.push(flag);
                        stackCopy.clear();
                        Stack<Object> clone = (Stack<Object>)stack.clone();
                        stackCopy = clone;
                        continue;
                    }
                    
                }
                
                //System.out.println("Rule execute result：" + flag);
            }
        }
        else
        {
            //System.out.println("系统信息：      未找到规则ID【" + filterByGroup.getFilterByGroupName() + "】的规则！！！！");
        }
        
        
        return flag;
    }
    
    /**
     * <处理or、and运算符的方法>
     * <功能详细描述>
     * @param stack
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean orAndOperatorHandling(Stack<Object> stack)
    {
        boolean sign = false;
        while (true)
        {
            Object value1 = stack.pop();
            Object value2 = stack.pop();
            
            if (!value2.toString().equals("("))
            {
                if (StringUtil.equals(value2.toString(), "or"))
                {
                    Object value3 = stack.pop();
                    stack.push(or(value1, value3));
                    continue;
                }
                if (StringUtil.equals(value2.toString(), "and"))
                {
                    Object value3 = stack.pop();
                    stack.push(and(value1, value3));
                    continue;
                }
            }
            else
            {
                sign = (Boolean)value1;
                break;
            }
        }
        
        return sign;
    }
    
    /**
     * <获得表达式>
     * <功能详细描述>
     * @param events
     * @param list
     * @param operatorList
     * @param eventAttributes
     * @see [类、类#方法、类#成员]
     */
    public static Map<String, Object> obtainExpression(Events events, List<Object> list, List<String> operatorList,
        Map<String, String> eventAttributes)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        
        // 获得events的所有方法
        Method[] eventsMethod = events.getClass().getMethods();
        
        Object left = new Object();
        Object opr = new Object();
        Object right = new Object();
        
        if (list.size() != 0)
        {
            for (Object str : list)
            {
                // 判断获得的元素是否是运算符
                if (operatorList.contains(str))
                {
                    opr = str;
                }
                else if (eventAttributes.containsKey(str))
                {
                    left = invokeMethod(eventsMethod, eventAttributes.get(str), events);
                }
                else
                {
                    if (str.toString().startsWith("{"))
                    {
                        String[] object =
                            str.toString()
                                .substring(str.toString().indexOf("{") + 1, str.toString().length() - 1)
                                .split(",");
                        right = object;
                    }
                    else
                    {
                        right = str;
                    }
                }
            }
            
            map.put("left", left);
            map.put("opr", opr);
            map.put("right", right);
        }
        
        return map;
    }
    
    /**
     * <获得表达式中表示的Events属性的值>
     * <功能详细描述>
     * @param eventsMethod
     * @param methodName
     * @param events
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Object invokeMethod(Method[] eventsMethod, String methodName, Events events)
    {
        // 存放属性值
        Object attributeValue = "";
        for (Method method : eventsMethod)
        {
            if (StringUtil.equals(method.getName(), methodName))
            {
                try
                {
                    attributeValue = method.invoke(events, new Object[] {});
                }
                catch (IllegalAccessException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                catch (IllegalArgumentException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                catch (InvocationTargetException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            }
        }
        return attributeValue;
    }
    
    /**
     * <处理or运算符>
     * <功能详细描述>
     * @param v1
     * @param v3
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean or(Object v1, Object v3)
    {
        boolean b1 = (Boolean)v1;
        boolean b2 = (Boolean)v3;
        boolean flag = false;
        if (b1)
        {
            flag = true;
        }
        else if(b2)
        {
            flag = true;
        }
        
        return flag;
    }
    
    /**
     * <处理and运算符>
     * <功能详细描述>
     * @param v1
     * @param v3
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean and(Object v1, Object v3)
    {
        boolean b1 = (Boolean)v1;
        boolean b2 = (Boolean)v3;
        boolean flag = false;
        if (b1 && b2)
        {
            flag = true;
        }
        
        return flag;
    }
    
}
