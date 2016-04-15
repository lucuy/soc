package com.soc.service.homepagediv;

import java.util.List;
import java.util.Map;

import com.soc.model.home.HomePageDiv;

/**
 * 
 * <查询存储首页div显示情况的字符串>
 * <功能详细描述>
 * 
 * @author  gaosong
 * @version  [版本号, 2013-3-2]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface HomePageDivService {
	/**
	 * <首页编辑按钮后的保存状态>
	 * <功能详细描述>
	 * @param homePageDiv
	 * @param userId
	 * @see [类、类#方法、类#成员]
	 */
public void saveHomePageDiv(String homePageDiv, long userId);
public List<HomePageDiv> queryHomePageDiv(long userId);
public void savePosition(Map map);
public void updateHomePageDiv(Map map);
public void insertDiv(HomePageDiv homePageDiv);
/**
 * <删掉div>
 * <功能详细描述>
 * @param parseInt
 * @see [类、类#方法、类#成员]
 */
public void deleteDivByUserId(int parseInt);
}
