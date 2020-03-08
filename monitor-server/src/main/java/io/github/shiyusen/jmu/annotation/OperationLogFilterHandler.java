package io.github.shiyusen.jmu.annotation;

import io.github.shiyusen.jmu.business.hook.JmuAlarmHook;
import io.github.shiyusen.jmu.business.hook.impl.JmuAlarmHookService;
import io.github.shiyusen.jmu.business.model.OperationLogPo;
import io.github.shiyusen.jmu.business.service.OperationLoggerService;
import io.github.shiyusen.jmu.common.LogLevelEnum;
import io.github.shiyusen.jmu.util.LocalHostUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.ServiceLoader;

/**
 * method接口日志切面
 *
 * @author 石玉森
 * at created 2020-03-08 10:13
 **/

@Slf4j
@Aspect
@Component
public class OperationLogFilterHandler {


    @Autowired
    private OperationLoggerService operationLoggerService;

    @Autowired
    private JmuAlarmHookService jmuAlarmHookService;

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
        String methodName = method.getName();
        Operationable annotation = method.getAnnotation(Operationable.class);
        Object result = null;
        String level = LogLevelEnum.INFO.name();
        try {
            result = joinPoint.proceed(args);
        } catch (Throwable throwable) {
            level = LogLevelEnum.ERROR.name();
            if (annotation.alarm()) {
                jmuAlarmHookService.alarm(args, result);
            }
            log.error("error invoke method={},request={},response={}", methodName, args, result);
            throw new RuntimeException(throwable);
        } finally {
            log.info("invoke method={},request={},response={}", methodName, args, result);
            try {
                operationLoggerService.insertOperationLogInfo(getOperationLogPo(args, result, level));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private OperationLogPo getOperationLogPo(Object[] request, Object result, String level) {
        OperationLogPo operationLogPo = new OperationLogPo();
        operationLogPo.setName(LocalHostUtil.getHostName());
        operationLogPo.setIp(LocalHostUtil.getLocalIP());
        operationLogPo.setLevel(level);
        operationLogPo.setCreated(new Date());
        if (null != request && request.length > 0) {
            operationLogPo.setInput(JSONArray.fromObject(request).toString());
        }
        if (null != result) {
            operationLogPo.setOutput(JSONObject.fromObject(result).toString());
        }
        return operationLogPo;
    }

    private Method getMethod(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature ms = (MethodSignature) signature;
        Method method = ms.getMethod();
        return method;
    }
}
