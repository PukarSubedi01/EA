package bank.service.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
public class JMSLogAdvice {

    @After("execution(* bank.jms.*.*(..)) && args(text)")
    public void traceaftermethod(JoinPoint joinpoint, String text) {
        System.out.println("The message sent to the user is "+ text);
    }

}
