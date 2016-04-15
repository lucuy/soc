package com.soc.model;

import java.util.List;
import java.util.Map;

public abstract class BaseObject {
	public abstract List<String> getDifferencesList(Object object);
	public abstract List<String> getFieldsList();
}
