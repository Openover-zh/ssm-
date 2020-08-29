package com.itheim.ssm.controller;

import com.itheim.SysLog;
import com.itheim.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * <h3>heima_ssm</h3>
 * <p>日志信息切面类</p>
 *
 * @author : Dell
 * @date : 2020-08-27 16:52
 **/
@Aspect
@Component
public class logAop {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService service;

    private Date startTime; // 访问时间
    private Class executionClass;// 访问的类
    private Method executionMethod; // 访问的方法
    // 主要获取访问时间、访问的类、访问的方法
    @Before("execution(* com.itheim.ssm.controller.*.*(..))")
    public void before(JoinPoint jp) throws NoSuchMethodException {
        startTime=new Date();//获取访问时间

//        获取访问的类
        executionClass=jp.getTarget().getClass();
        String methodName = jp.getSignature().getName();
        //获取访问方法的参数
        Object[] args = jp.getArgs();
        if (args==null || args.length==0){
            //无参数
            executionMethod=executionClass.getMethod(methodName);

        }else{
            Class[] classes=new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classes[i]=args[i].getClass();
            }
            executionMethod= executionClass.getMethod(methodName, classes);

        }


    }


    @After("execution(* com.itheim.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp){
        SysLog sysLog = new SysLog();

        //主要获取ip、用户名、访问时间、访问路径
        Long executionTime=new Date().getTime()-startTime.getTime();  //获取访问时长
        sysLog.setExecutionTime(executionTime);
        //获取ip地址
        String remoteAddr = request.getRemoteAddr();
        sysLog.setIp(remoteAddr);

        // 可以通过securityContext获取，也可以从request.getSession中获取
        //获取当前访问的用户名
        SecurityContext context = SecurityContextHolder.getContext(); //获取上下文对象
        //request.getSession().getAttribute("SPRING_SECURITY_CONTEXT")
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();
        sysLog.setUsername(username);

        //放置方法
        sysLog.setMethod("[类名]"+executionClass.getName()+"[方法名]"+executionMethod.getName());
        //放置开始时间
        sysLog.setVisitTime(startTime);

        //获取访问路径
        if (executionClass!=SysLogController.class) {
            RequestMapping annotationClass = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
            if (annotationClass!=null){
                RequestMapping annotationMethod = executionMethod.getAnnotation(RequestMapping.class);
                if (annotationMethod!=null){
                    String url="";
                    url= annotationClass.value()[0]+annotationMethod.value()[0];
                    sysLog.setUrl(url);

                    service.save(sysLog);




                }
            }


        }




    }



}
