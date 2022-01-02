package com.gk.rpc.core;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gaot
 * @date 2021/7/24
 */
@Data
public class RpcRequest implements Serializable {
    private String clazzName;
    private String methodName;
    private Class<?>[] params;
    private Object[] values;
}
