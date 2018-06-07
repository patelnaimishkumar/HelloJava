package com.hello;

import org.apache.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyHandler implements InvocationHandler {
    private Object proxyObj;
    private static Logger log = Logger.getLogger(MyHandler.class);

    public Object bind(Object obj) {
        this.proxyObj = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object ret = null;
        try {
            System.out.println("Call log: " + method.getName());
            ret = method.invoke(proxyObj, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
}
