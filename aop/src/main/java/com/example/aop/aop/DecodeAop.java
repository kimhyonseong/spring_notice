package com.example.aop.aop;

import com.example.aop.dto.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Aspect
@Component
public class DecodeAop {
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut() {}

    @Pointcut("@annotation(com.example.aop.anotation.Decode)")
    public void  enableDecode() {}

    @Before("cut() && enableDecode()")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            if (arg instanceof User) {
                User user = (User) arg;
                String base64 = user.getEmail();

                String email = new String(Base64.getDecoder().decode(base64), StandardCharsets.UTF_8);
                user.setEmail(email);
            }
        }
    }

    @AfterReturning(value = "cut() && enableDecode()", returning = "returnObj")
    public void after(JoinPoint joinPoint, Object returnObj) {
        if (returnObj instanceof User) {
            User user = (User) returnObj;
            String email = user.getEmail();

            String base64 = Base64.getEncoder().encodeToString(email.getBytes());
            user.setEmail(base64);
        }
    }
}
