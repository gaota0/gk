package com.gk.mysql.aspect;

import com.gk.mysql.config.DynamicDataSource;
import com.gk.mysql.constant.DsRouteConstant;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author gaot
 * @date 2021/7/24
 */
@Component
@Slf4j
@Aspect
public class DsRouteAspect {
    @Pointcut("@annotation(DS)")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        final MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        final Method method = signature.getMethod();
        final DS ds = method.getAnnotation(DS.class);
        if (Objects.equals(ds.value(), DsRouteConstant.MASTER)) {
            DynamicDataSource.DS_SWITCH.set(DsRouteConstant.MASTER);
        } else {
            DynamicDataSource.DS_SWITCH.set(DsRouteConstant.SLAVE);
        }
        log.info("设置数据源路由标识:{}", DynamicDataSource.DS_SWITCH.get());
    }

    @After("pointCut()")
    public void after() {
        DynamicDataSource.DS_SWITCH.remove();
        log.debug("清理数据源路由标识...");
    }

}
