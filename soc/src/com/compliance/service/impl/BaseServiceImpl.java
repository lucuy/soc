package com.compliance.service.impl;

/**
 * base 公共业务实现类
 * <P>
 * 
 * 
 * @author zhangjinxin
 * @version 1.0,2009-05-13
 * @since 1.0
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.compliance.service.BaseService;


public class BaseServiceImpl implements BaseService {
	protected transient Log log = LogFactory.getLog(getClass().getName());
}
