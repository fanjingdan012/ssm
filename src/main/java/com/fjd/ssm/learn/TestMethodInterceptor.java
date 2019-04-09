package com.fjd.ssm.learn;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

import java.util.concurrent.ConcurrentHashMap;
//@annotation(org.springframework.web.bind.annotation.RequestMapping)
public class TestMethodInterceptor  {

    public static void main(String[] args) {
        ProxyFactory proxyFactory=new ProxyFactory();
        //proxyFactory.setTarget(new TestMethodInterceptor());
        proxyFactory.setTarget(new ConcurrentHashMap());
        proxyFactory.addAdvice(new AdviseMethodInterceptor());

        Object proxy = proxyFactory.getProxy();
        //TestMethodInterceptor methodInterceptor = (TestMethodInterceptor) proxy;

        //methodInterceptor.doSomeThing("通过代理工厂设置代理对象，拦截代理方法");
        ConcurrentHashMap c = (ConcurrentHashMap) proxy;
        c.put("a","b");
    }

    public static class AdviseMethodInterceptor implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation methodInvocation) throws Throwable {

            Object result=null;
            try{
                System.out.println("方法执行之前："+methodInvocation.getMethod().toString());

                result= methodInvocation.proceed();

                System.out.println("方法执行之后："+methodInvocation.getMethod().toString());
                System.out.println("方法正常运行结果："+result);

                return result;

            }catch (Exception e){
                System.out.println("方法出现异常:"+e.toString());
                System.out.println("方法运行Exception结果："+result);
                return result;
            }

        }


    }

    public String doSomeThing(String someThing){

        //int i=5/0;
        return "执行被拦截的方法："+someThing;
    }
}