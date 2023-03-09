package customers;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;

@Aspect
@Configuration
public class TraceAdvice {


    @After("execution(* customers.EmailSender.sendEmail(String, String)) && args(email,message)")
    public void traceaftermethod(JoinPoint joinpoint, String message, String email) {
        EmailSender target = (EmailSender) joinpoint.getTarget();
        System.out.println(LocalDateTime.now() + " method= " + joinpoint.getSignature().getName());
        System.out.println("message= " + message + " outgoing mail server = " + target.outgoingMailServer);
    }

    @Around("execution(* customers.EmailSender.sendEmail(String, String))")
    public Object invoke(ProceedingJoinPoint call ) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object retVal = call.proceed();
        sw.stop();

        long totaltime = sw.getLastTaskTimeNanos();
        System.out.println(totaltime);
        return retVal;
    }
}
