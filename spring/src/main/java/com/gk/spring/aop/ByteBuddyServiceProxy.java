package com.gk.spring.aop;

import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author gaot
 * @date 2021/12/5
 */
public class ByteBuddyServiceProxy {
    public  static final Logger log = LoggerFactory.getLogger(ByteBuddyServiceProxy.class);
    @SneakyThrows
    public static IService getServiceProxy() {
        return new ByteBuddy().subclass(ServiceImpl.class).method(ElementMatchers.any())
            .intercept(Advice.to(ByteBuddyServiceProxy.class)).make().load(ServiceImpl.class.getClassLoader())
            .getLoaded().getConstructor().newInstance();
    }

    @Advice.OnMethodEnter
    public static void before(@Advice.Origin Method method) {
        log.info("method {} before", method);
    }

    @Advice.OnMethodExit
    public static void after(@Advice.Origin Method method) {
        log.info("method {} after", method);
    }
}
