package com.softuni.aop;


import com.softuni.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    private final LogService logService;

    public LogAspect(LogService logService) {
        this.logService = logService;
    }

    @Pointcut("execution(* com.softuni.web.TeamController.details(..))")
    public void trackDbInitialization(){}

    @After("trackDbInitialization()")
    public void afterAdvice(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        String id = (String) args[1];
        String action = joinPoint.getSignature().getName();

        this.logService.createLog(action, id);
    }
}
