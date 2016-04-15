package com.util;

import java.util.Comparator;

import com.soc.model.conf.AgentModel;

public class Compare implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		AgentModel a1=(AgentModel)o1;
		AgentModel a2 = (AgentModel)o2;
		if(a1.getSystemTime()>a2.getSystemTime())
		{
			return 1;
		}
		else
		{
		    return 0;
		}
	}

}
