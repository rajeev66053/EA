package cs544.exercise13_2.bank;

import cs544.exercise13_2.bank.logging.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Aspect
@Component
public class LogAdvice {
    @Autowired
    @Qualifier("logger")
    private ILogger logger;

    @After("execution(* cs544.exercise13_2.bank.dao.AccountDAO.*(..)))")
    public void logAfterMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        logger.log("Method : " + methodName + " Is Executed");
    }

    @After("execution(* cs544.exercise13_2.bank.jms.JMSSender.sendJMSMessage(..)) && args(text)")
    public void logJmsMessage(JoinPoint joinPoint,String text){
        logger.log("JMSSender: sending JMS message ="+text);
    }


//    @Around("execution(* cs544.exercise13_2.bank.service.AccountService.*(..))")
//    public Object profile(ProceedingJoinPoint call) throws Throwable {
//        StopWatch clock = new StopWatch("");
//        clock.start(call.toShortString());
//
//        Object object = call.proceed();
//
//        clock.stop();
//
//        System.out.println(clock.prettyPrint());
//
//        return object;
//    }
}
