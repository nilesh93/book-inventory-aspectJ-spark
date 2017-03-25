package com.mtit.aspects;

import com.mtit.logger.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Aspect to trace the activities in the dataacceess layer in the system
 */
@Aspect
public class TracingAspect {

    // all the functions in com.mtit.dataaccess package which has service implementations
    @Pointcut("execution(* com.mtit.dataaccess.*Impl.*(..))")
    public void dataAccessFunctions() {}

    // log the function method signature before execution
    @Before("dataAccessFunctions()")
    public void before(JoinPoint joinPoint) {
        new Log().writeToLog("Data Access Execution : " + joinPoint.toLongString());
    }

    // log the function method signature after execution
    @After("dataAccessFunctions()")
    public void after(JoinPoint joinPoint) {
        new Log().writeToLog("Data Access Execution Completed : " + joinPoint.toLongString());
    }

    // log the function method signature and exception message after exception throw
    @AfterThrowing(pointcut = "dataAccessFunctions()", throwing = "ex")
    public void throwExp(Exception ex, JoinPoint joinPoint) throws Throwable {
        Log log = new Log();
        log.writeToLog("************* Data Access EXCEPTION **************");
        log.writeToLog("Method  :" + joinPoint.toLongString());
        log.writeToLog("Message :" + ex.getMessage());
        log.writeToLog("**************** END *****************************");
    }
}
