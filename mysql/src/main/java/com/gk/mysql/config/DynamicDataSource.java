package com.gk.mysql.config;

import com.gk.mysql.constant.DsRouteConstant;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author gaot
 * @date 2021/7/24
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    public static final ThreadLocal<String> DS_SWITCH = ThreadLocal.withInitial(() -> DsRouteConstant.MASTER);
    @Override
    protected Object determineCurrentLookupKey() {
        return DS_SWITCH.get();
    }
}
