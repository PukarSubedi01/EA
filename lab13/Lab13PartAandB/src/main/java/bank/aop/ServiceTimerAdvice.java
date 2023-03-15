package bank.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
public class ServiceTimerAdvice {
    Logger logger = LoggerFactory.getLogger(ServiceTimerAdvice.class);

    @Around("execution(* bank.service.*.*(..))")
    public Object time(ProceedingJoinPoint call) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object retVal = call.proceed();
        sw.stop();

        long totaltime = sw.getTotalTimeMillis();
        logger.info("Time to execute " + call.getSignature().getName() + " = " + totaltime + " ms");

        return retVal;
    }

}
