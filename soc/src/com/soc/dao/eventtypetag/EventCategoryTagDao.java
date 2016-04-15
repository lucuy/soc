package com.soc.dao.eventtypetag;

import java.util.List;

import com.soc.model.eventtypetag.eventcategorytag;

/**
 * 
 * <事件分类对应的业务类>
 * <主要实现对事件分类的查询>
 * 
 * @author  admin
 * @version  [版本号, 2013-6-26]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface EventCategoryTagDao {
   
    /**
    * <查询所有的事件类别数据>
    * <功能详细描述>
    * @return
    * @see [类、类#方法、类#成员]
    */
   public List<eventcategorytag> queryall();
   
   /**
    * <根据传递的keys数组查询对应的list>
    * <功能详细描述>
    * @param keys
    * @return
    * @see [类、类#方法、类#成员]
    */
   public List<eventcategorytag> queryBykeys(String keys);
   
   /**
    <根据传递过来的字符串，查询到事件类别的KEY>
	 * @prama String
	 * @return int
    */
   public String queryKeyBYCategoryName(String name) ; 
}
