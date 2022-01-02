package com.gk.rpc.consumer.config;

import com.gk.rpc.core.Filter;
import okhttp3.Request;

/**
 * @author gaot
 * @date 2021/7/24
 */
public class HeaderFilter implements Filter {
    @Override
    public void filter(Request.Builder builder) {
        builder.addHeader("hello", "world");
    }
}
