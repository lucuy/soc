package com.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  郭煜玺
 * @version  V001R001C01
 * @since  V001R001C01
 */
public class ClassUtil
{
    
    /**
     * 统一打印日志
     */
    private static final Log LOGGER = LogFactory.getLog(ClassUtil.class);
    
   /**
    * 审计模块新增字段特征字符串
    * <功能详细描述>
    * @param name String
    * @param newStr String
    * @return String
    */
    public static String fieldStr(String name, String newStr)
    {
        return name + ":" + newStr;
    }
    
    /**
     * 审计模块修改字段特征字符串
     * <功能详细描述>
     * @param name String
     * @param srcStr String
     * @param newStr String
     * @return String
     */
    public static String diffStr(String name, String srcStr, String newStr)
    {
        return name + ":" + srcStr + " ->" + newStr;
    }
    
    /**
     * 通过类名以及属性名取得属性值
     * <功能详细描述>
     * @param object Object
     * @param propertyName String
     * @return Object
     */
    public static Object property(Object object, String propertyName)
    {
        Object obj = null;
        try
        {
            // 查找Public属性
            Class clazz = object.getClass();
            Field f = clazz.getDeclaredField(propertyName);
            obj = f.get(object);
        }
        catch (SecurityException e)
        {
            LOGGER.error("", e);
        }
        catch (IllegalArgumentException e)
        {
            LOGGER.error("", e);
        }
        catch (NoSuchFieldException e)
        {
            LOGGER.error("", e);
        }
        catch (IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            // 如果捕获权限访问异常，查找get方法
            propertyName = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
            propertyName = "get" + propertyName;
            try
            {
                obj = ClassUtil.invoke(object, propertyName);
            }
            catch (Exception e1)
            {
                LOGGER.error("", e1);
            }
        }
        return obj;
    }
    
    /**
     * 通过类名以及方法名执行方法并返回返回值（待完善：1、未加入参数支持；2、未支持重载；）
     * <功能详细描述>
     * @param object Object
     * @param methodName String
     * @return Object
     */
    public static Object invoke(Object object, String methodName)
    {
        Object obj = null;
        try
        {
            //getMethod第一个参数是方法名，第二个参数是该方法的参数类型，
            //因为存在同方法名不同参数这种情况，所以只有同时指定方法名和参数类型才能唯一确定一个方法
            Method method = object.getClass().getMethod(methodName, new Class[0]);
            //接下来就该执行该方法了，解释一下参数
            //第一个参数是具体调用该方法的对象
            //第二个参数是执行该方法的具体参数
            
            obj = method.invoke(object, new Object[0]);
        }
        catch (SecurityException e)
        {
            LOGGER.error("", e);
        }
        catch (IllegalArgumentException e)
        {
            LOGGER.error("", e);
        }
        catch (NoSuchMethodException e)
        {
            LOGGER.error("", e);
        }
        catch (IllegalAccessException e)
        {
            LOGGER.error("", e);
        }
        catch (InvocationTargetException e)
        {
            LOGGER.error("", e);
        }
        return obj;
    }
}
