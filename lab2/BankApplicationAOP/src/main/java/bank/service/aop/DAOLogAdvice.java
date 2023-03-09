package bank.service.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class DAOLogAdvice {
    @Before("execution(* bank.dao.*.*(..))")
    public void tracebeforemethod(JoinPoint joinpoint) {
        System.out.println("Calling "+joinpoint.getSignature().getName() + " repository");
    }
}
