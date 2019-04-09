package com.fjd.ssm.learn;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;

@Aspect
@Component
public class SystemLogAspect {
//    @Around("execution(org.apache.olingo.odata2.api.processor.ODataResponse org.apache.olingo.odata2.api.processor.ODataSingleProcessor+.*(..) ) "
//            + "&& bean(oDataCompositeProcessor)")
//    public ODataResponse asp(ProceedingJoinPoint pjp) throws Throwable {
//    }
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public  void controllerAspect() {}

    @Pointcut("@annotation(com.tj.common.log.system.SystemLog)")
    public  void serviceAspect() {}
//
//    @Pointcut("@annotation(com.tj.common.log.system.SystemLog)")
//    public  void repositoryAspect() {}
//
    @After("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa");
//        try {
//            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//            String ip = request.getRemoteAddr();
//            String description = getControllerMethodDescription(joinPoint);
//            Object obj = request.getSession().getAttribute("loginUser");
//            LogUser user = new LogUser(null, null);
//            /*对象obj中必须拥有属性account、userName*/
//            BeanUtils.copyProperties(user, obj);
//            if(StringUtils.isBlank(user.getAccount())){
//                user = new LogUser("Anonymous", "匿名用户");
//            }
//        } catch (Exception e) {
//
//        }
    }
//
//    @SuppressWarnings("rawtypes")
//    private static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {
//        String targetName = joinPoint.getTarget().getClass().getName();
//        String methodName = joinPoint.getSignature().getName();
//        Object[] arguments = joinPoint.getArgs();
//        Class targetClass = Class.forName(targetName);
//        Method[] methods = targetClass.getMethods();
//        String description = "";
//        for (Method method : methods) {
//            if (method.getName().equals(methodName)) {
//                Class[] clazzs = method.getParameterTypes();
//                if (clazzs.length == arguments.length) {
//                    description = method.getAnnotation(SystemLog.class).description();
//                    break;
//                }
//            }
//        }
//        return description;
//    }
    @Pointcut("execution(com.fjd.ssm.config.*.*(..) org.springframework.beans.factory.support.DefaultListableBeanFactory)")
    public void recordLog(){}

    @Before("recordLog()")
    public void before() {
        this.printLog("已经记录下操作日志@Before 方法执行前");
    }

    @Around("recordLog()")
    public void around(ProceedingJoinPoint pjp) throws Throwable{
        this.printLog("已经记录下操作日志@Around 方法执行前");
        pjp.proceed();
        this.printLog("已经记录下操作日志@Around 方法执行后");
    }

    @After("recordLog()")
    public void after() {
        this.printLog("已经记录下操作日志@After 方法执行后");
    }

    private void printLog(String str){
        System.out.println(str);
    }

}