package com.bus.map.demo.osrmlistener;

import com.bus.map.demo.osrmmodels.OSRMDirectionResult;
import com.bus.map.demo.osrmmodels.OsrmItem;

public interface OSRMRouteListener {

    public void onResult(OsrmItem result);
    public void onFailure(Throwable e);
}
