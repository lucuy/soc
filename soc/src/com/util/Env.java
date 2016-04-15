package com.util;

import java.io.InputStream;
import java.util.Properties;

public class Env extends Properties {

	private static Env env;

	private Env() {
		InputStream is = this.getClass().getResourceAsStream(
				"/prop/assetValue.properties");
		try {
			this.load(is);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static Env getInstrance() {
		if (env != null) {
			return env;
		} else {
			newInstrance();
			return env;
		}
	}

	synchronized private static void newInstrance() {
		if (env == null) {
			env = new Env();
		}
	}

	public static String getString(String key) {
		return Env.getInstrance().getProperty(key);
	}
}
