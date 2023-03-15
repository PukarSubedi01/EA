package bank.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
public class ServiceExecutionTimeAspect {

    @Around("execution(* bank.service.*.*(..))")
    public Object executionTimeChecker(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        stopWatch.stop();
        System.out.println("Execution time: method=" + joinPoint.getSignature().getName() + ", execution time(ms)=" + stopWatch.getLastTaskTimeMillis());
        return result;
    }
}
