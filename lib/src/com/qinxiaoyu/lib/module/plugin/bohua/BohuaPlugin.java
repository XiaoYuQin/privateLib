package com.qinxiaoyu.lib.module.plugin.bohua;

import java.util.Vector;

public interface BohuaPlugin {

	/*目前的objes的顺序为，Bundle，broadcastPool，deviceType*/
	public int method(Vector<Object> objs);

}
