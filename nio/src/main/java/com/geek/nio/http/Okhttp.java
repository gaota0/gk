package com.geek.nio.http;

import okhttp3.*;

import java.io.IOException;

/**
 * @author gaot
 * @date 2021/11/13
 */
public class Okhttp {
    public static void main(String[] args) throws IOException {
        final OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url("http://127.0.0.1:8801").build();
        final Response response = okHttpClient.newCall(request).execute();
        System.out.println(response.body().string());

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });
    }
}
