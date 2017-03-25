package com.mtit.aspects;

import com.mtit.logger.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import spark.Response;
@Aspect
public class LoginAspect {

    // point cutting every Implementation class in com.mtit.service package
    @Pointcut("execution(* com.mtit.service.*Impl.*(..))")
    public void BookDataAccessLayerImpl() {}

    @Pointcut("execution(* com.mtit.service.*Impl.get*(..))")
    public void list(){}

    @Pointcut("execution(* com.mtit.service.*Impl.create(..)) && args(String, spark.Response)")
    public void create(){}

    @Pointcut("execution(* com.mtit.service.*Impl.update(..)) && args(String, spark.Response, int)")
    public void update(){}
//
    @Pointcut("execution(* com.mtit.service.*Impl.del(..)) && args(String, spark.Response, int)")
    public void delete(){}

    @Pointcut("execution(* com.mtit.service.*Impl.view(..)) && args(String, spark.Response,int)")
    public void view(){}

    // Log exceptions in any service
    @AfterThrowing(pointcut = "BookDataAccessLayerImpl()", throwing = "ex")
    public void logAfterThrowingAllMethods(Exception ex, JoinPoint joinPoint) throws Throwable
    {
        Log log = new Log();
        log.writeToLog("************* EXCEPTION **************");
        log.writeToLog("Method  :" + joinPoint.toLongString());
        log.writeToLog("Message :" + ex.getMessage());
        log.writeToLog("**************** END ******************");
    }

	// log service functions after execution
	@After("BookDataAccessLayerImpl()")
	public void after(JoinPoint joinPoint) {
        new Log().writeToLog("Execution Completed : " + joinPoint.toLongString());
	}

	// log listing service calls
    @Before("list()")
    public void beforeList(JoinPoint joinPoint) {
        new Log().writeToLog("Execution : " + joinPoint.toLongString());
        new Log().writeToLog("Listing requested");
    }

    // log view calls and requesting id
    @Before("view()")
    public void beforeView(JoinPoint joinPoint) {
        new Log().writeToLog("Execution : " + joinPoint.toLongString());
        new Log().writeToLog("View requested : ID ->" + joinPoint.getArgs()[2]);
    }

    @Before("create()")
    public void beforeCreate(JoinPoint joinPoint) {
        new Log().writeToLog("Execution : " + joinPoint.toLongString());
        new Log().writeToLog("Create Object requested : " + joinPoint.getArgs()[0]);
    }

    @Before("update()")
    public void beforeUpdate(JoinPoint joinPoint) {
        new Log().writeToLog("Execution : " + joinPoint.toLongString());
        new Log().writeToLog("Update requested : ID ->" + joinPoint.getArgs()[2]);
        new Log().writeToLog("Update Object : " + joinPoint.getArgs()[0]);
    }

    @Before("delete()")
    public void beforeDelete(JoinPoint joinPoint) {
        new Log().writeToLog("Execution : " + joinPoint.toLongString());
        new Log().writeToLog("Delete requested : ID ->" + joinPoint.getArgs()[2]);
    }

}
