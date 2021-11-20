package com.gk.gateway.http;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author gaot
 * @date 2021/11/20
 */
@Slf4j
public class HttpExecutor {
    private OkHttpClient client = new OkHttpClient();

    public void get(String url, Callback callback) {
        log.info("url:{}", url);
        final Request request = new Request.Builder().url(url).build();
        final Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
