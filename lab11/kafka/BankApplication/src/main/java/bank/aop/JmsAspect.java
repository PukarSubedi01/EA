package bank.aop;

import bank.logging.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class JmsAspect {
    @Autowired
    private ILogger logger;

    @After("execution(* bank.jms.IJMSSender.sendJMSMessage(..))")
    public void logJmsMessage(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String text = (String) args[0];
        logger.log("JMS message sent: message=" + text);
    }

}
