package com.gk.jvm.homework.week1;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author gaot
 * @date 2021/11/2
 */
public class CustomClassLoader extends ClassLoader {
    public static void main(String[] args) throws Exception {
        final Class<?> helloClazz = new CustomClassLoader().loadClass("Hello");
        final Object helloObj = helloClazz.newInstance();
        for (Method method : helloClazz.getDeclaredMethods()) {
            //            System.out.println(method);
            if (Objects.equals(method.getName(), "hello")) {
                method.invoke(helloObj);
            }
        }
    }

    @Override
    protected Class<?> findClass(String name) {
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Hello.xlass")) {
            final byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);

            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte)(255 - bytes[i]);
            }
            return defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
