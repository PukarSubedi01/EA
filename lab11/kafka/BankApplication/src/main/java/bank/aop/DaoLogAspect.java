package bank.aop;

import bank.logging.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class DaoLogAspect {
    @Autowired
    private ILogger logger;

    @Before("execution (* bank.dao.*.*(..))")
    public void logMethodCalls(JoinPoint joinPoint){
        logger.log("method call to bank.dao, method=" + joinPoint.getSignature().getName());
    }
}
