package com.mtit.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class FooAspect {
//	@Pointcut("execution(* com.mtit.models.Foo.foo(..))")
//	public void methodFooFromTypeFoo() {}

    @Pointcut("execution(* com.mtit.dataaccess.BookDataAccessLayerImpl.*(..))")
    public void methodFooFromTypeFoo() {}

//    @Pointcut("execution(* com.mtit.service.BookServiceImpl.*(..))")
//    public void methodFooFromTypeFoo() {}

	@Before("methodFooFromTypeFoo()")
	public void before(JoinPoint joinPoint) {
		System.out.println("before " + joinPoint.toLongString());
	}

	@After("methodFooFromTypeFoo()")
	public void after(JoinPoint joinPoint) {
		System.out.println("After " + joinPoint.toLongString());
	}
}
