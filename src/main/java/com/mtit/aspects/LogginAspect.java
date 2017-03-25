package com.mtit.aspects;

import com.mtit.logger.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import spark.Response;

/**
 * Aspect to trace the activities in the service layer in the system
 */
@Aspect
public class LogginAspect {

    // point cutting every Implementation class in com.mtit.service package
    @Pointcut("execution(* com.mtit.service.*Impl.*(..))")
    public void serviceFunctions() {}

    // point cutting function names starting with get in com.mtit.service package
    @Pointcut("execution(* com.mtit.service.*Impl.get*(..))")
    public void list(){}

    // point cutting function names of create in com.mtit.service package
    @Pointcut("execution(* com.mtit.service.*Impl.create(..)) && args(String, spark.Response)")
    public void create(){}

    // point cutting function names of update in com.mtit.service package
    @Pointcut("execution(* com.mtit.service.*Impl.update(..)) && args(String, spark.Response, int)")
    public void update(){}

    // point cutting function names of del in com.mtit.service package
    @Pointcut("execution(* com.mtit.service.*Impl.del(..)) && args(String, spark.Response, int)")
    public void delete(){}

    // point cutting function names of view in com.mtit.service package
    @Pointcut("execution(* com.mtit.service.*Impl.view(..)) && args(String, spark.Response,int)")
    public void view(){}

    // Log exceptions in any service
    @AfterThrowing(pointcut = "serviceFunctions()", throwing = "ex")
    public void logAfterThrowingAllMethods(Exception ex, JoinPoint joinPoint) throws Throwable
    {
        Log log = new Log();
        log.writeToLog("************* Service EXCEPTION **************");
        log.writeToLog("Method  :" + joinPoint.toLongString());
        log.writeToLog("Message :" + ex.getMessage());
        log.writeToLog("**************** END *************************");
    }

	// log service functions after execution
	@After("serviceFunctions()")
	public void after(JoinPoint joinPoint) {
        new Log().writeToLog("Service Execution Completed : " + joinPoint.toLongString());
	}

	// log listing service calls
    @Before("list()")
    public void beforeList(JoinPoint joinPoint) {
        new Log().writeToLog("Service Execution : " + joinPoint.toLongString());
        new Log().writeToLog("Listing requested");
    }

    // log view calls and requesting id
    @Before("view()")
    public void beforeView(JoinPoint joinPoint) {
        new Log().writeToLog("Service Execution : " + joinPoint.toLongString());
        new Log().writeToLog("View requested : ID ->" + joinPoint.getArgs()[2]);
    }

    // log create requests with object
    @Before("create()")
    public void beforeCreate(JoinPoint joinPoint) {
        new Log().writeToLog("Service Execution : " + joinPoint.toLongString());
        new Log().writeToLog("Create Object requested : " + joinPoint.getArgs()[0]);
    }

    // log update requests with object and id
    @Before("update()")
    public void beforeUpdate(JoinPoint joinPoint) {
        new Log().writeToLog("Service Execution : " + joinPoint.toLongString());
        new Log().writeToLog("Update requested : ID ->" + joinPoint.getArgs()[2]);
        new Log().writeToLog("Update Object : " + joinPoint.getArgs()[0]);
    }

    // log delete requests with id
    @Before("delete()")
    public void beforeDelete(JoinPoint joinPoint) {
        new Log().writeToLog("Service Execution : " + joinPoint.toLongString());
        new Log().writeToLog("Delete requested : ID ->" + joinPoint.getArgs()[2]);
    }

}
