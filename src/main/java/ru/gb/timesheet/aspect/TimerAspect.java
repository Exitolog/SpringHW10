package ru.gb.timesheet.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TimerAspect {


    @Pointcut("annotation(ru.gb.timesheet.aspect.Timer)")
    public void timerPoinctcut(){}



    @Around(value = "timerPointcut()")
    public Object aroundTimesheetServiceMethods(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return pjp.proceed();
        } finally {
            long duration = System.currentTimeMillis() - start;
            log.info("TimesheetService#{} duration = {}ms", pjp.getSignature().getName(), duration);
        }
    }


}