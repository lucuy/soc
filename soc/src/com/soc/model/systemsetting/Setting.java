package com.soc.model.systemsetting;

import com.soc.model.systemsetting.BaseConf;
/**
 * Description: 系统设置实体类
 * 
 * @author 郭煜玺
 * @Version 1.0
 * @Created at 2010-12-31
 * 
 */
public class Setting extends BaseConf {

	private Integer type; // 类型

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public static void main(String[] argv) {
		Setting inst = new Setting();
		inst.setId(1);

		//System.out.println(inst.getId());
	}

}
