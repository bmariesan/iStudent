package ro.ubb.istudent.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogginAspect {

    private Logger logger = LoggerFactory.getLogger(LogginAspect.class);

    @Before("execution(public * *(..)) && @annotation(ro.ubb.istudent.aspects.Loggable)")
    public void logBefore(JoinPoint joinPoint) throws Throwable {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        String logInfo = "user: " + userName + " called method: " + joinPoint.getSignature().getName() +
                "with params: " + Arrays.toString(joinPoint.getArgs());

        logger.info(logInfo);

    }

}
