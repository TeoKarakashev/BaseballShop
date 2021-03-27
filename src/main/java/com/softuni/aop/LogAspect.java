package com.softuni.aop;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {


    @Pointcut("execution(* com.softuni.web.TeamController.details(  ))")
    public void trackDbInitialization(){}

    @After("trackDbInitialization()")
    public void afterAdvice(){

    }
}
