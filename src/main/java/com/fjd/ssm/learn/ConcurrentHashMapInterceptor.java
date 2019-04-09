package com.fjd.ssm.learn;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ConcurrentHashMapInterceptor implements MethodInterceptor{
 

	public Object intercept(Object obj, Method method, Object[] params,
                            MethodProxy proxy) throws Throwable {
		System.out.println("Before");
		Object result = proxy.invokeSuper(obj, params);
		System.out.println("After "+result);
		return result;
	}
 
 
}