package com.gk.gateway.server.route;

import io.netty.handler.codec.http.FullHttpRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.gk.gateway.constant.WebsiteSearchUrlConstant.*;

/**
 * @author gaot
 * @date 2021/11/20
 */
public class RandomEndpointRouter implements HttpEndpointRouter{
    private static final List<String> WEBSITE_LIST = Arrays.asList( BAIDU, BING, BILIBILI);
    private static final Random RANDOM=new Random();
    @Override
    public String route(FullHttpRequest request) {
        final int index = RANDOM.nextInt(WEBSITE_LIST.size());
        return WEBSITE_LIST.get(index);
    }
}
