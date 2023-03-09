package bank.service.aop;

import bank.logging.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

//Creating an aspect
@Aspect
@Configuration
public class AOP {
//    injecting the logger
    @Autowired
    private ILogger logger;
    //  creating an advice
    @After("execution(* bank.dao.*.*(..))") // specifying pointcut
    public void DAOLogAdvice(JoinPoint joinPoint){
        logger.log("DAOMethod = "+joinPoint.getSignature().getName());
    }
    //  creating an advice
    @After("execution(* bank.jms.*.*(..))") // specifying pointcut
    public void JMSLogAdvice(JoinPoint joinPoint){
        logger.log("JMSMethod = "+joinPoint.getSignature().getName());
    }
    //  creating an advice
    @Around("execution(* bank.service.*.*(..))") // specifying pointcut
    public Object StopWatchAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StopWatch clock = new StopWatch();
        clock.start(proceedingJoinPoint.getSignature().getName());
        Object object= proceedingJoinPoint.proceed();
        clock.stop();
        System.out.println("Time required for execution is: "+clock.getLastTaskTimeNanos());
        return object;
    }
}
