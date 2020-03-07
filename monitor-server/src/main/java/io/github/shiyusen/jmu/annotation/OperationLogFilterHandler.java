package io.github.shiyusen.jmu.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * method接口日志切面
 *
 * @author 石玉森
 * @create 2020-03-08 10:13
 **/

@Slf4j
@Aspect
@Component
public class OperationLogFilterHandler {


    /**
     * operationable
     */
    @Pointcut("@annotation(io.github.shiyusen.jmu.annotation.Operationable)")
    public void operationable() {
    }


    @Around("operationable()")
    public Object aroundOperationable(ProceedingJoinPoint joinPoint) throws Exception {

        Object[] args = joinPoint.getArgs();
        Method method = getMethod(joinPoint);
        Operationable annotation = method.getAnnotation(Operationable.class);
        Object result = null;
        try {
            result = joinPoint.proceed(args);
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        } finally {
            log.info("invoke method={},request={},response={}", args, result);
        }
        return result;
    }

    private Method getMethod(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature ms = (MethodSignature) signature;
        Method method = ms.getMethod();
        return method;
    }
}
