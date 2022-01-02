package com.gk.rpc.core;

import okhttp3.Request;

/**
 * @author gaot
 * @date 2021/7/24
 */
public interface Filter {
    void filter(Request.Builder builder);

}
