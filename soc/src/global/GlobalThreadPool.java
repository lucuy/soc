package global;

import com.soc.model.conf.GlobalConfig;
import com.util.ThreadPool;

public class GlobalThreadPool{

	public static ThreadPool pool = null;
	
	public static void init() {
		pool = new com.util.ThreadPool(GlobalConfig.MAX_THREADPOOL_NUMBER);
	}
}
